package com.ned.finalProject.successresponse;

import com.ned.finalProject.model.Bank;

public class BankCreateSuccessResponse {
	
	private Bank bank;
	private String message;
	private boolean success;
	
	public BankCreateSuccessResponse(Bank bank) {
		this.bank = bank;
		this.message = "Created Successfully";
		this.success = true;
	}
	
	public Bank getBank() {
		return bank;
	}
	
	public void setBank(Bank bank) {
		this.bank = bank;
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
	
	
	
	
	
}
