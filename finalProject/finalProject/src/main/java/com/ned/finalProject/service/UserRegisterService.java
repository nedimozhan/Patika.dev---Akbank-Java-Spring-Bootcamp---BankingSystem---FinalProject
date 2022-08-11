package com.ned.finalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ned.finalProject.createrequest.UserRegisterRequest;
import com.ned.finalProject.exception.UnknownErrorException;
import com.ned.finalProject.exception.UsernameEmailAlreadyUsedException;
import com.ned.finalProject.model.User;
import com.ned.finalProject.repository.ILocalUserRepository;
import com.ned.finalProject.successresponse.UserCreateSuccess;

@Component
@Qualifier("UserRegisterService")
public class UserRegisterService implements IUserRegisterService {

	private ILocalUserRepository localUserRepository;

	@Autowired
	public UserRegisterService(ILocalUserRepository localUserRepository) {
		this.localUserRepository = localUserRepository;
	}

	@Override
	public User insertUser(UserRegisterRequest userRegisterRequest) {

		User userControlUsername = this.localUserRepository.getUserByUsername(userRegisterRequest.getUsername());
		User userControlEmail = this.localUserRepository.getUserByEmail(userRegisterRequest.getEmail());
		
		/*
		 * Control username and email
		 * If username and email are unique we create new user
		 * Otherwise we throw some exceptions
		 */
		if (userControlUsername != null) {
			throw new UsernameEmailAlreadyUsedException(userControlUsername.getUsername());
		} else if (userControlEmail != null) {
			throw new UsernameEmailAlreadyUsedException(userControlEmail.getEmail());
		} else {
			try {
				User user = new User(userRegisterRequest);
				this.localUserRepository.insertUser(user);
				return user;
			} catch (Exception e) {
				throw new UnknownErrorException();
			}

		}
	}
}
