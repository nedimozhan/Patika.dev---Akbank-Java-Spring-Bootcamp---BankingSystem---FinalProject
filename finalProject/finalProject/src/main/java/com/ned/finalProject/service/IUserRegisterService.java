package com.ned.finalProject.service;

import com.ned.finalProject.createrequest.UserRegisterRequest;
import com.ned.finalProject.model.User;
import com.ned.finalProject.successresponse.UserCreateSuccess;

public interface IUserRegisterService {
	public User insertUser(UserRegisterRequest userRegisterRequest);
}
