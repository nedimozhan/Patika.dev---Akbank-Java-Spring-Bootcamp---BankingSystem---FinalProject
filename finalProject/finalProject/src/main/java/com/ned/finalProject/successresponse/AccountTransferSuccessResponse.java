package com.ned.finalProject.successresponse;

public class AccountTransferSuccessResponse {

	private boolean success;
	private String message;

	public AccountTransferSuccessResponse() {
		this.success = true;
		this.message = "Transferred Succesfully";
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
