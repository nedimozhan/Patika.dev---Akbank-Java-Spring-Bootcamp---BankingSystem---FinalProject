package com.ned.finalProject.successresponse;

public class UserLoginSuccess {
	
	private boolean success;
	private String message;
	private String token;
	
	public UserLoginSuccess(String token) {
		this.success = true;
		this.message = "Logged-In Successfully";
		this.token = token;
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
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
