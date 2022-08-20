package com.ned.finalProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ned.finalProject.exception.UnknownErrorException;
import com.ned.finalProject.model.Account;
import com.ned.finalProject.model.User;
import com.ned.finalProject.repository.ILocalAccountRepository;
import com.ned.finalProject.repository.ILocalUserRepository;

@Component
@Qualifier("AccountDetailService")
public class AccountDetailService implements IAccountDetailService {

	private ILocalAccountRepository localAccountRepository;
	private ILocalUserRepository localUserRepository;

	@Autowired
	public AccountDetailService(ILocalAccountRepository localAccountRepository,
								ILocalUserRepository localUserRepository) {
		this.localAccountRepository = localAccountRepository;
		this.localUserRepository=localUserRepository;
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

	@Override
	public List<Account> allAccountByUserId() {
		
		try {
			Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
			String username = loggedInUser.getName();
			User user = this.localUserRepository.getUserByUsername(username);
			List<Account> accounts = this.localAccountRepository.getAllAccountByUserId(user.getId());
			return accounts;
			
		} catch (Exception e) {
			throw new UnknownErrorException();
		}
		
		
	}

}
