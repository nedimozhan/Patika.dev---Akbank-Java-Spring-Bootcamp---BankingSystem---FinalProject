package com.ned.finalProject.successresponse;

import com.ned.finalProject.model.Account;

public class AccountDetailSuccessResponse {
	
	private Account account;
	
	public AccountDetailSuccessResponse(Account account) {
		this.account = account;
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
