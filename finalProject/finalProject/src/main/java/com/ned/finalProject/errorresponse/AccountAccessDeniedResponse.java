package com.ned.finalProject.errorresponse;

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
