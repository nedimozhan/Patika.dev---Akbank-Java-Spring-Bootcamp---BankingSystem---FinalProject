package com.ned.finalProject.service;

import java.util.List;

import com.ned.finalProject.model.Account;

public interface IAccountDetailService {
	public Account accountDetail(int id);
	public List<Account> allAccountByUserId();
}
