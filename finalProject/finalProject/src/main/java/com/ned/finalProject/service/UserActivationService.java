package com.ned.finalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ned.finalProject.createrequest.UserActivationRequest;
import com.ned.finalProject.exception.UnknownErrorException;
import com.ned.finalProject.exception.UserNotFoundException;
import com.ned.finalProject.model.User;
import com.ned.finalProject.repository.ILocalUserRepository;

@Component
@Qualifier("UserActivationService")
public class UserActivationService implements IUserActivationService {

	private ILocalUserRepository localUserRepository;

	@Autowired
	public UserActivationService(ILocalUserRepository localUserRepository) {
		this.localUserRepository = localUserRepository;
	}

	@Override
	public boolean enableOrDisableUser(UserActivationRequest userActivationRequest, int id) {

		User user = this.localUserRepository.getUserById(id);

		// If there is no user with given id
		if (user == null) {
			throw new UserNotFoundException();
		} else {
			try {
				boolean isEnabled = userActivationRequest.isEnabled();
				user.setEnabled(isEnabled);
				this.localUserRepository.updateUser(user);
				return user.isEnabled();
			} catch (Exception e) {
				throw new UnknownErrorException();
			}
		}
	}
}
