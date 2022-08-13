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
@Qualifier("ValidateService")
public class ValidateService implements IValidateService {

	private ILocalAccountRepository localAccountRepository;
	private ILocalUserRepository localUserRepository;

	public ValidateService(ILocalAccountRepository localAccountRepository, ILocalUserRepository localUserRepository) {
		this.localAccountRepository = localAccountRepository;
		this.localUserRepository = localUserRepository;
	}

	/*
	 * Control Account is found && Account.userId equals to User.Id
	 */
	@Override
	public boolean isAccountRelatedToUser(int id) {

		try {

			Account account = this.localAccountRepository.getAccountById(id);

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

		} catch (AccountNotFoundException e) {

			throw e;
		} catch (AccountAccessDeniedException e) {
			throw e;
		} catch (UnknownErrorException e) {
			throw new UnknownErrorException();
		}

	}

	/*
	 * Control Account is found
	 */
	@Override
	public boolean isAccountFound(int id) {

		try {
			Account account = this.localAccountRepository.getAccountById(id);

			if (account == null) {
				throw new AccountNotFoundException();
			}
			return true;
		} catch (AccountNotFoundException e) {
			throw e;
		} catch (UnknownErrorException e) {
			throw new UnknownErrorException();
		}

	}

}
