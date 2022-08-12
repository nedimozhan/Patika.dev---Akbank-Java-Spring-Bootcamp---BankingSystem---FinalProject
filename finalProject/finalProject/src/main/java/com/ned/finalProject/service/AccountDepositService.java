package com.ned.finalProject.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ned.finalProject.exception.AccountAccessDeniedException;
import com.ned.finalProject.exception.AccountNotFoundException;
import com.ned.finalProject.exception.UnknownErrorException;
import com.ned.finalProject.model.Account;
import com.ned.finalProject.repository.ILocalAccountRepository;

@Component
@Qualifier("AccountDepositService")
public class AccountDepositService implements IAccountDepositService {

	private ILocalAccountRepository localAccountRepository;
	
	@Autowired
	public AccountDepositService(ILocalAccountRepository localAccountRepository) {
		this.localAccountRepository = localAccountRepository;
	}

	@Override
	@Transactional
	public Account depositAccount(int id, float balance) {

		try {

			Account account = this.localAccountRepository.getAccountById(id);

			// Update Balance
			float totalBalance = account.getBalance() + balance;
			account.setBalance(totalBalance);

			// Update Last Update Date
			Timestamp updateTimestamp = new Timestamp(System.currentTimeMillis());
			account.setLastUpdatedDate(updateTimestamp);

			// Update Account in Database
			this.localAccountRepository.depositAccount(account);

			return account;

		} catch (Exception e) {
			throw new UnknownErrorException();
		}

	}
}
