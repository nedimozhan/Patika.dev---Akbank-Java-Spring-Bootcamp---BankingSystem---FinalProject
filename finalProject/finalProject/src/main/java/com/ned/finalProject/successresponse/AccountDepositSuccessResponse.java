package com.ned.finalProject.successresponse;

public class AccountDepositSuccessResponse {

	private boolean success;
	private String message;
	private float balance;

	public AccountDepositSuccessResponse(float balance) {
		this.success = true;
		this.message = "Deposit Successfully";
		this.balance = balance;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

}
