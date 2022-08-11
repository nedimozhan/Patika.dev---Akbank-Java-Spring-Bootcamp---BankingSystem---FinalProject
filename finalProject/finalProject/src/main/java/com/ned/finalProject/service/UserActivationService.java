package com.ned.finalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ned.finalProject.createrequest.UserActivationRequest;
import com.ned.finalProject.model.User;
import com.ned.finalProject.repository.ILocalUserRepository;

import kafka.server.ClientQuotaManager.ThrottledChannelReaper;

@Component
@Qualifier("UserActivationService")
public class UserActivationService implements IUserActivationService{
	
	private ILocalUserRepository localUserRepository;
	
	@Autowired
	public UserActivationService(ILocalUserRepository localUserRepository) {
		this.localUserRepository = localUserRepository;
	}
	
	@Override
	public boolean enableOrDisableUser(UserActivationRequest userActivationRequest,int id) {
		
			User user = this.localUserRepository.getUserById(id);
			
			/*
			 * boolean control;
					
			if(userActivationRequest.getEnabled().equals("true")) {
				control = true;
			}
			else {
				control = false;
			}
			 */
			boolean isEnabled = Boolean.parseBoolean(userActivationRequest.getEnabled());  
			user.setEnabled(isEnabled);
			this.localUserRepository.updateUser(user);
			return user.isEnabled();		
	}
}
