package com.ned.finalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ned.finalProject.model.User;
import com.ned.finalProject.security.UserDetailsImplement;
import com.ned.finalProject.repository.ILocalUserRepository;

@Component
@Qualifier("UserDetailServiceDatabase")
public class UserDetailServiceDatabase implements UserDetailsService{
	
	private ILocalUserRepository localUserRepository;
	
	@Autowired
	public UserDetailServiceDatabase(ILocalUserRepository localUserRepository) {
		this.localUserRepository = localUserRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.localUserRepository.getUserByUsername(username);
		//User user = this.localUserRepository.loadUserByUsername(username);
		UserDetailsImplement userDetailsImplement = new UserDetailsImplement(user);

		return (UserDetails)userDetailsImplement;
	}

}
