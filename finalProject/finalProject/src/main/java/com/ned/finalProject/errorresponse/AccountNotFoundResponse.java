package com.ned.finalProject.errorresponse;

public class AccountNotFoundResponse {
	
	private String message;

	public AccountNotFoundResponse() {
		this.message = "Account Not Found";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
