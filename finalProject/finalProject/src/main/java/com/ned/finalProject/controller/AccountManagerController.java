package com.ned.finalProject.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ned.finalProject.createrequest.UserActivationRequest;
import com.ned.finalProject.service.IUserActivationService;
import com.ned.finalProject.successresponse.UserActivationSuccessResponse;

@RestController
public class AccountManagerController {
	
	IUserActivationService userActivationService;
	
	public AccountManagerController(@Qualifier("UserActivationService") IUserActivationService userActivationService) {
		this.userActivationService = userActivationService;
	}
	
	@PatchMapping("/users/{id}")
	public ResponseEntity<?> enableDisableUser(@PathVariable int id, @RequestBody UserActivationRequest userActivationRequest){
		
		boolean isEnabled = this.userActivationService.enableOrDisableUser(userActivationRequest, id);
		
		UserActivationSuccessResponse userActivationSuccessResponse = new UserActivationSuccessResponse(isEnabled);
		return new ResponseEntity<>(userActivationSuccessResponse,null,HttpStatus.OK);
	}
}
