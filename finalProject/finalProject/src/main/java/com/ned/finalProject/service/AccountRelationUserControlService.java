package com.ned.finalProject.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ned.finalProject.exception.AccountAccessDeniedException;
import com.ned.finalProject.exception.AccountNotFoundException;
import com.ned.finalProject.exception.UnknownErrorException;
import com.ned.finalProject.model.Account;
import com.ned.finalProject.model.User;
import com.ned.finalProject.repository.ILocalAccountRepository;
import com.ned.finalProject.repository.ILocalUserRepository;

@Component
@Qualifier("AccountRelationUserControlService")
public class AccountRelationUserControlService implements IAccountRelationUserControlService {

	private ILocalAccountRepository localAccountRepository;
	private ILocalUserRepository localUserRepository;

	public AccountRelationUserControlService(ILocalAccountRepository localAccountRepository,
			ILocalUserRepository localUserRepository) {
		this.localAccountRepository = localAccountRepository;
		this.localUserRepository = localUserRepository;
	}

	@Override
	public boolean isAccountRelatedToUser(int accountId) {

		try {

			Account account = (Account) this.localAccountRepository.getAccountById(accountId);

			if (account == null) {
				throw new AccountNotFoundException();
			}

			Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
			String username = loggedInUser.getName();
			User user = this.localUserRepository.getUserByUsername(username);

			if (user.getId() == account.getUserId()) {
				return true;
			} else {
				throw new AccountAccessDeniedException();
			}

		} catch (Exception e) {
			throw new UnknownErrorException();
		}

	}
}