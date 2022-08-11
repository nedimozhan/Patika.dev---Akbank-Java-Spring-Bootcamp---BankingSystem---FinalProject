package com.ned.finalProject.service;

import com.ned.finalProject.createrequest.UserActivationRequest;

public interface IUserActivationService {
	public boolean enableOrDisableUser(UserActivationRequest userActivationRequest, int id);
}
