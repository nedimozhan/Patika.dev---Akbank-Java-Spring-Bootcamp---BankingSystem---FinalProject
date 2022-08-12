package com.ned.finalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ned.finalProject.exception.UnknownErrorException;
import com.ned.finalProject.model.Account;
import com.ned.finalProject.repository.ILocalAccountRepository;

@Component
@Qualifier("AccountDetailService")
public class AccountDetailService implements IAccountDetailService {

	private ILocalAccountRepository localAccountRepository;

	@Autowired
	public AccountDetailService(ILocalAccountRepository localAccountRepository) {
		this.localAccountRepository = localAccountRepository;
	}

	@Override
	public Account accountDetail(int id) {

		try {
			Account account = this.localAccountRepository.getAccountById(id);
			return account;
		} catch (Exception e) {
			throw new UnknownErrorException();
		}

	}

}
