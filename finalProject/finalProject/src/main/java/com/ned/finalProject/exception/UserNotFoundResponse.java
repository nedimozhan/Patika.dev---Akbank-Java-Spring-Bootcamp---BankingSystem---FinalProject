package com.ned.finalProject.exception;

public class UserNotFoundResponse {
	
	private String message;

	public UserNotFoundResponse() {
		this.message = "User Not Found";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
