package com.ned.finalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ned.finalProject.exception.BankNameAlreadyUsedException;
import com.ned.finalProject.exception.UnknownErrorException;
import com.ned.finalProject.model.Bank;
import com.ned.finalProject.repository.ILocalBankRepository;

@Component
@Qualifier("BankCreateService")
public class BankCreateService implements IBankCreateService {

	private ILocalBankRepository localBankRepository;

	@Autowired
	public BankCreateService(ILocalBankRepository localBankRepository) {
		this.localBankRepository = localBankRepository;
	}

	@Override
	public Bank createBank(String name) {

		/*
		 * Control if entered bank name already exist or not If the bank name already
		 * exist we should throw exception If the bank name doesnt exist we can create a
		 * new Bank
		 */
		
		Bank bank = this.localBankRepository.getDataByName(name);

		if (bank == null) {
			bank = new Bank();
			bank.setName(name);
			this.localBankRepository.insertData(bank);
			return bank;
		} else if(bank != null){
			throw new BankNameAlreadyUsedException(name);
		}
		else {
			throw new UnknownErrorException();
		}
	}
}
