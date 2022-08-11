package com.ned.finalProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ned.finalProject.repository.ILocalAccountRepository;

@Component
@Qualifier("AccountRemoveService")
public class AccountRemoveService implements IAccountRemoveService{
	
	private ILocalAccountRepository localAccountRepository;
	
	@Autowired
	public AccountRemoveService(ILocalAccountRepository localAccountRepository) {
		this.localAccountRepository = localAccountRepository;
	}
	
	@Override
	public void removeAccount(int id) {
		
		this.localAccountRepository.deleteAccount(id,true);
	}
}
