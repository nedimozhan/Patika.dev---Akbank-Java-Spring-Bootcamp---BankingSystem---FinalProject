package com.ned.finalProject.exception;

public class AccountTypeInvalidResponse {

	private boolean success;
	private String message;

	public AccountTypeInvalidResponse(String message) {
		this.message = "Invalid Account Type : " + message.toString();
		this.success = false;
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

}
