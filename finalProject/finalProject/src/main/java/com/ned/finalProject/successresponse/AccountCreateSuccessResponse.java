package com.ned.finalProject.successresponse;

import com.ned.finalProject.model.Account;

public class AccountCreateSuccessResponse {

	private String message;
	private boolean success;
	private Account account;

	public AccountCreateSuccessResponse(Account account) {
		this.account = account;
		this.message = "Account Created";
		this.success = true;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
