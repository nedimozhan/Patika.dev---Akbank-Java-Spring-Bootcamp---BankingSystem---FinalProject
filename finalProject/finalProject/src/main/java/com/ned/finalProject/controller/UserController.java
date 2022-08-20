package com.ned.finalProject.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ned.finalProject.createrequest.UserActivationRequest;
import com.ned.finalProject.errorresponse.UnknownErrorResponse;
import com.ned.finalProject.errorresponse.UserNotFoundResponse;
import com.ned.finalProject.exception.UnknownErrorException;
import com.ned.finalProject.exception.UserNotFoundException;
import com.ned.finalProject.service.IUserActivationService;
import com.ned.finalProject.successresponse.UserActivationSuccessResponse;

@RestController
public class UserController {

	private IUserActivationService userActivationService;

	public UserController(@Qualifier("UserActivationService") IUserActivationService userActivationService) {
		this.userActivationService = userActivationService;
	}
	
	
	/*
	 * Account manager and admin can enable or disable to users
	 */
	@PatchMapping("/users/{id}")
	public ResponseEntity<?> enableDisableUser(@PathVariable int id,
			@RequestBody UserActivationRequest userActivationRequest) {

		try {
			boolean isEnabled = this.userActivationService.enableOrDisableUser(userActivationRequest, id);
			UserActivationSuccessResponse userActivationSuccessResponse = new UserActivationSuccessResponse(isEnabled);
			return new ResponseEntity<>(userActivationSuccessResponse, null, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			UserNotFoundResponse userNotFoundResponse = new UserNotFoundResponse();
			return new ResponseEntity<>(userNotFoundResponse, null, HttpStatus.NOT_FOUND);
		} catch (UnknownErrorException e) {
			UnknownErrorResponse unknownErrorResponse = new UnknownErrorResponse();
			return new ResponseEntity<>(unknownErrorResponse, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
