package com.ned.finalProject.successresponse;

import com.ned.finalProject.model.User;

public class UserCreateSuccess {
	
	private boolean success;
	private String message;
	private User user;
	
	public UserCreateSuccess(User user) {
		this.user = user;
		this.message = "Created Successfully";
		this.success = true;
	}
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	
	
	
	
}
