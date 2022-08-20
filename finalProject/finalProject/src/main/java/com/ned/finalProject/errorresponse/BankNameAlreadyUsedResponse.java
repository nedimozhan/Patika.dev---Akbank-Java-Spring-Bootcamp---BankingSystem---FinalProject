package com.ned.finalProject.errorresponse;

public class BankNameAlreadyUsedResponse {

	private boolean success;
	private String message;

	public BankNameAlreadyUsedResponse(String message) {
		this.message = "Given name Already Used : " + message;
		this.success = false;
	}

	public String getMessage() {
		return this.message;
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
