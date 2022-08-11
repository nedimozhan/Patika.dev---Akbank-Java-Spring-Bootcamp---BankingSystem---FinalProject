package com.ned.finalProject.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ned.finalProject.createrequest.AccountCreateRequest;
import com.ned.finalProject.enummodel.EType;
import com.ned.finalProject.exception.AccountTypeInvalidException;
import com.ned.finalProject.model.Account;
import com.ned.finalProject.model.User;
import com.ned.finalProject.repository.ILocalAccountRepository;
import com.ned.finalProject.repository.ILocalUserRepository;

@Component
@Qualifier("AccountCreateService")
public class AccountCreateService implements IAccountCreateService{
	
	private ILocalAccountRepository localAccountRepository;
	private ILocalUserRepository localUserRepository;
	
	@Autowired
	public AccountCreateService(ILocalAccountRepository localAccountRepository,ILocalUserRepository localUserRepository) {
		this.localAccountRepository = localAccountRepository;
		this.localUserRepository = localUserRepository;
	}
	
	@Override
	public Account createAccount(AccountCreateRequest accountCreateRequest) {
		
		String currency = "";
		
		for (EType type : EType.values()) { 
		    if(accountCreateRequest.getType().equals(type.toString())) {
		    	currency = type.toString();
		    }
		}
		
		if(currency == "") {
			throw new AccountTypeInvalidException(accountCreateRequest.getType());
		}
		
		int generatedNumber = this.generateToken();
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		
		User user = this.localUserRepository.getUserByUsername(username);
		int userId = user.getId();
		Account account = new Account(accountCreateRequest, generatedNumber, userId);
		
		this.localAccountRepository.createAccount(account);
		
		return account;
	}

	@Override
	public int generateToken() {
		
		int min_number = 1000000000;
		int number;
		Account account;
		
		do {
			number = ThreadLocalRandom.current().nextInt(min_number, Integer.MAX_VALUE);
			account = this.localAccountRepository.getAccountByNumber(number);
		} while (account != null);
		
		return number;
	}

}
