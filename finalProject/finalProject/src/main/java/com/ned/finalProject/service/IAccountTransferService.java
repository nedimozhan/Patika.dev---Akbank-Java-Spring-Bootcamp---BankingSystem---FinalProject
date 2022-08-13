package com.ned.finalProject.service;

import com.ned.finalProject.createrequest.AccountTransferRequest;

public interface IAccountTransferService {
	public void transferAccount(int senderId, AccountTransferRequest accountTransferRequest);
}
