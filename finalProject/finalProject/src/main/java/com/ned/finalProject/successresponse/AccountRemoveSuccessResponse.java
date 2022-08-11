package com.ned.finalProject.successresponse;

public class AccountRemoveSuccessResponse {

	private boolean success;
	private String message;

	public AccountRemoveSuccessResponse() {
		this.message = "Account Deleted";
		this.success = true;
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

}
