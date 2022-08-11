package com.ned.finalProject.exception;

public class AccountAccessDeniedResponse {

	private String message;

	public AccountAccessDeniedResponse() {
		this.message = "Access Denied";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
