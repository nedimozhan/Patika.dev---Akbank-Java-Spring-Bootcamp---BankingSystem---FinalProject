package com.ned.finalProject.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ned.finalProject.createrequest.UserRegisterRequest;
import com.ned.finalProject.service.IUserRegisterService;
import com.ned.finalProject.successresponse.UserCreateSuccess;

@RestController
public class UserRegisterController {
	
	private IUserRegisterService userRegisterService;
	
	public UserRegisterController(@Qualifier("UserRegisterService")IUserRegisterService userRegisterService) {
		this.userRegisterService = userRegisterService;
	}
	
	@PostMapping(path = "/register")
	public ResponseEntity<?> userRegister(@RequestBody UserRegisterRequest request){	
		UserCreateSuccess userCreateSuccess = this.userRegisterService.insertUser(request);
		return new ResponseEntity<>(userCreateSuccess, null, HttpStatus.CREATED);
	}
}
