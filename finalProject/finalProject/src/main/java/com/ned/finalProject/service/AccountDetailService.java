package com.ned.finalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ned.finalProject.exception.AccountAccessDeniedException;
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
		this.localUserRepository = localUserRepository;
	}

	@Override
	public Account accountDetail(int id) {

		Account account = (Account) this.localAccountRepository.getAccountById(id);

		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();

		User user = this.localUserRepository.getUserByUsername(username);

		if ((account != null) && (user.getId() == account.getUserId())) {
			return account;
		} else {
			throw new AccountAccessDeniedException();
		}

	}

}
