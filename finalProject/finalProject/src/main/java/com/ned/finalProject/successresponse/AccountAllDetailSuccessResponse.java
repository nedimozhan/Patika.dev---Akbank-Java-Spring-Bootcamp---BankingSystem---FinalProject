package com.ned.finalProject.successresponse;

import java.util.List;

import com.ned.finalProject.model.Account;

public class AccountAllDetailSuccessResponse {

	private List<Account> accounts;

	public AccountAllDetailSuccessResponse(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

}
