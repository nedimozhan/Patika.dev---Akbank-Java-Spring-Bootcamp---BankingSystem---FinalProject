package com.ned.finalProject.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.invocation.reactive.ReturnValueHandlerConfigurer;
import org.springframework.stereotype.Component;

import com.ned.finalProject.createrequest.UserRegisterRequest;
import com.ned.finalProject.exception.UsernamePasswordEmailAlreadyUsedException;
import com.ned.finalProject.model.User;
import com.ned.finalProject.repository.ILocalUserRepository;
import com.ned.finalProject.successresponse.UserCreateSuccess;

@Component
@Qualifier("UserRegisterService")
public class UserRegisterService implements IUserRegisterService{

	private ILocalUserRepository localUserRepository;
	
	@Autowired
	public UserRegisterService(ILocalUserRepository localUserRepository) {
		this.localUserRepository = localUserRepository;
	}

	@Override
	public UserCreateSuccess insertUser(UserRegisterRequest userRegisterRequest) {
		
		User userControlUsername = this.localUserRepository.getUserByUsername(userRegisterRequest.getUsername());
		User userControlEmail = this.localUserRepository.getUserByEmail(userRegisterRequest.getEmail());
		
		if (userControlUsername != null) {
			throw new UsernamePasswordEmailAlreadyUsedException(userControlUsername.getUsername());
		}
		else if(userControlEmail != null) {
			throw new UsernamePasswordEmailAlreadyUsedException(userControlEmail.getEmail());
		}
		else {
			User user = new User(userRegisterRequest);
			this.localUserRepository.insertUser(user);
			System.out.println("USER ID : " + user.getId());
			UserCreateSuccess userCreateSuccess = new UserCreateSuccess(user);
			return userCreateSuccess;
		}
	}

	@Override
	public User getDataById(int id) {	
		User user = this.localUserRepository.getUserById(id);
		return user;
	}	
}
