package com.ned.finalProject.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ned.finalProject.createrequest.UserRegisterRequest;
import com.ned.finalProject.enummodel.EAuthorities;

public class User {
	
	private int id;
	private String username;
	private String password;
	private String email;
	private boolean enabled;
	private String authorities;
	
	public User() {
		
	}
	 
	public User(UserRegisterRequest userRegisterRequest) {
		this.username = userRegisterRequest.getUsername();
		this.email = userRegisterRequest.getEmail();
		this.password = userRegisterRequest.getPassword();
		this.enabled = false;
		this.authorities = EAuthorities.CREATE_ACCOUNT.toString();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
	
	public List<String> getPermissionList() {
		if (this.authorities.length() > 0) {
			return Arrays.asList(this.authorities.split(","));
		}
		return new ArrayList<>();
	}
}
