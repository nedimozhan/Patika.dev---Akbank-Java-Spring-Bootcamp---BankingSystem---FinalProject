package com.ned.finalProject.service;

import com.ned.finalProject.createrequest.AccountCreateRequest;
import com.ned.finalProject.model.Account;

public interface IAccountCreateService {
	public Account createAccount(AccountCreateRequest accountCreateRequest);
	public int generateToken();
}
