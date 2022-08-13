package com.ned.finalProject.exception;

public class AccountInsufficientBalanceResponse {

	private String message;

	public AccountInsufficientBalanceResponse() {
		this.message = "Insufficient Balance";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
