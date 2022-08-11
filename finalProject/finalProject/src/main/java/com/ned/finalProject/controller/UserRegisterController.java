package com.ned.finalProject.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ned.finalProject.createrequest.UserRegisterRequest;
import com.ned.finalProject.exception.UnknownErrorException;
import com.ned.finalProject.exception.UnknownErrorResponse;
import com.ned.finalProject.exception.UsernameEmailAlreadyUsedException;
import com.ned.finalProject.exception.UsernameEmailAlreadyUsedResponse;
import com.ned.finalProject.model.User;
import com.ned.finalProject.service.IUserRegisterService;
import com.ned.finalProject.successresponse.UserCreateSuccess;

@RestController
public class UserRegisterController {

	private IUserRegisterService userRegisterService;

	public UserRegisterController(@Qualifier("UserRegisterService") IUserRegisterService userRegisterService) {
		this.userRegisterService = userRegisterService;
	}

	/*
	 * Create a user with request Request contains Username, Password, Email
	 * Username and email have to be unique
	 */
	@PostMapping(path = "/register")
	public ResponseEntity<?> userRegister(@RequestBody UserRegisterRequest request) {

		try {
			User user = this.userRegisterService.insertUser(request);
			UserCreateSuccess userCreateSuccess = new UserCreateSuccess(user);
			return new ResponseEntity<>(userCreateSuccess, null, HttpStatus.CREATED);
		} catch (UsernameEmailAlreadyUsedException e) {
			UsernameEmailAlreadyUsedResponse alreadyUsedResponse = new UsernameEmailAlreadyUsedResponse(e.getMessage());
			return new ResponseEntity<>(alreadyUsedResponse, null, HttpStatus.CONFLICT);
		} catch (UnknownErrorException e) {
			UnknownErrorResponse unknownErrorResponse = new UnknownErrorResponse();
			return new ResponseEntity<>(unknownErrorResponse, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
