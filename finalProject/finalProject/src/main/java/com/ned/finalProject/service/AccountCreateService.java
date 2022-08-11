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
import com.ned.finalProject.exception.UnknownErrorException;
import com.ned.finalProject.model.Account;
import com.ned.finalProject.model.User;
import com.ned.finalProject.repository.ILocalAccountRepository;
import com.ned.finalProject.repository.ILocalUserRepository;

@Component
@Qualifier("AccountCreateService")
public class AccountCreateService implements IAccountCreateService {

	private ILocalAccountRepository localAccountRepository;
	private ILocalUserRepository localUserRepository;

	@Autowired
	public AccountCreateService(ILocalAccountRepository localAccountRepository,
			ILocalUserRepository localUserRepository) {
		this.localAccountRepository = localAccountRepository;
		this.localUserRepository = localUserRepository;
	}

	@Override
	public Account createAccount(AccountCreateRequest accountCreateRequest) {
		System.out.println("1111BANK ID REQUEST: " + accountCreateRequest.getBankId());
		String currency = "";

		// Invalid type control
		for (EType type : EType.values()) {
			if (accountCreateRequest.getType().equals(type.toString())) {
				currency = type.toString();
			}
		}

		if (currency == "") {
			throw new AccountTypeInvalidException(accountCreateRequest.getType());
		}

		try {
			// Generate token
			int generatedNumber = this.generateToken();

			// Authentication user
			Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
			String username = loggedInUser.getName();
			User user = this.localUserRepository.getUserByUsername(username);

			// Account created and recorded
			Account account = new Account(accountCreateRequest, generatedNumber, user.getId());
			this.localAccountRepository.createAccount(account);

			return account;
		} catch (Exception e) {
			throw new UnknownErrorException();
		}

	}

	/*
	 * Generate token (10 digits random number)
	 */
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
