package com.ned.finalProject.exception;

public class UsernamePasswordEmailAlreadyUsedResponse {
	
	private String message;
	private boolean success;
	
	public UsernamePasswordEmailAlreadyUsedResponse(String message) {
		this.success = false;
		this.message = "Given username or email already used : " + message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
