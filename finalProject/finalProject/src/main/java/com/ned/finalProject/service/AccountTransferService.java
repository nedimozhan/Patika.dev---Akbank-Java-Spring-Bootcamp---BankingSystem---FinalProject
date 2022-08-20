package com.ned.finalProject.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ned.finalProject.createrequest.AccountTransferRequest;
import com.ned.finalProject.enummodel.EType;
import com.ned.finalProject.exception.AccountInsufficientBalanceException;
import com.ned.finalProject.exception.UnknownErrorException;
import com.ned.finalProject.log.AbstractLog;
import com.ned.finalProject.log.TransferLog;
import com.ned.finalProject.model.Account;
import com.ned.finalProject.repository.ILocalAccountRepository;
import com.ned.finalProject.repository.IRemoteCurrencyRepository;

@Component
@Qualifier("AccountTransferService")
public class AccountTransferService implements IAccountTransferService {

	private ILocalAccountRepository localAccountRepository;
	private IRemoteCurrencyRepository remoteCurrencyRepository;
	private KafkaTemplate<String, AbstractLog> producer;

	@Autowired
	public AccountTransferService(ILocalAccountRepository localAccountRepository,
			@Qualifier("RemoteCurrencyRepository") IRemoteCurrencyRepository remoteCurrencyRepository) {
		this.localAccountRepository = localAccountRepository;
		this.remoteCurrencyRepository = remoteCurrencyRepository;
	}

	@Autowired
	public void setProducer(KafkaTemplate<String, AbstractLog> producer) {
		this.producer = producer;
	}

	@Override
	@Transactional
	public void transferAccount(int senderId, AccountTransferRequest accountTransferRequest) {
		
		try {
			Account senderAccount = this.localAccountRepository.getAccountById(senderId);
			Account receiverAccount = this.localAccountRepository
					.getAccountById(accountTransferRequest.getReceiverAccountId());

			float senderTLEFT = 3f;
			float senderDolarEFT = 1f;
			float senderAltınEFT = 0f;

			float eft = 0f;

			// Transfer to different banks
			if (senderAccount.getBankId() != receiverAccount.getBankId()) {

				if (senderAccount.getType().equals(EType.TL.toString())) {
					eft = senderTLEFT;
				} else if (senderAccount.getType().equals(EType.Dolar.toString())) {
					eft = senderDolarEFT;
				} else if (senderAccount.getType().equals(EType.Altın.toString())) {
					eft = senderAltınEFT;
				}

			}

			float totalAmountForSender = eft + accountTransferRequest.getAmount();

			if (senderAccount.getBalance() < totalAmountForSender) {
				throw new AccountInsufficientBalanceException();
			}

			// Assume that account types are the same types
			float exchangeRate = 1f;

			// Account types are different, calculate the exchange rate
			if (senderAccount.getType().equals(EType.TL.toString())
					&& receiverAccount.getType().equals(EType.Dolar.toString())) {
				exchangeRate = 1f / this.remoteCurrencyRepository.currencyUSD_TL();
			} else if (senderAccount.getType().equals(EType.Dolar.toString())
					&& receiverAccount.getType().equals(EType.TL.toString())) {
				exchangeRate = this.remoteCurrencyRepository.currencyUSD_TL();
			} else if (senderAccount.getType().equals(EType.TL.toString())
					&& receiverAccount.getType().equals(EType.Altın.toString())) {
				exchangeRate = 1f / this.remoteCurrencyRepository.currencyGOLD_TL();
			} else if (senderAccount.getType().equals(EType.Altın.toString())
					&& receiverAccount.getType().equals(EType.TL.toString())) {
				exchangeRate = this.remoteCurrencyRepository.currencyGOLD_TL();
			} else if (senderAccount.getType().equals(EType.Dolar.toString())
					&& receiverAccount.getType().equals(EType.Altın.toString())) {
				exchangeRate = this.remoteCurrencyRepository.currencyUSD_TL()
						/ this.remoteCurrencyRepository.currencyGOLD_TL();
			} else if (senderAccount.getType().equals(EType.Altın.toString())
					&& receiverAccount.getType().equals(EType.Dolar.toString())) {
				exchangeRate = this.remoteCurrencyRepository.currencyGOLD_TL()
						/ this.remoteCurrencyRepository.currencyUSD_TL();
			}

			// New account balance for sender
			senderAccount.setBalance(senderAccount.getBalance() - totalAmountForSender);

			// New account balance for receiver
			receiverAccount
					.setBalance(receiverAccount.getBalance() + (exchangeRate * accountTransferRequest.getAmount()));

			// Sender account last update
			senderAccount.setLastUpdatedDate(new Timestamp(System.currentTimeMillis()));

			// Update on database
			this.localAccountRepository.updateAccount(senderAccount);
			this.localAccountRepository.updateAccount(receiverAccount);

			TransferLog transferLog = new TransferLog(senderAccount.getNumber(), receiverAccount.getNumber(),
					accountTransferRequest.getAmount());
			producer.send("logs", transferLog);

		} catch (AccountInsufficientBalanceException e) {
			throw e;
		} catch (RuntimeException e) {
			throw new UnknownErrorException();
		}

	}

}
