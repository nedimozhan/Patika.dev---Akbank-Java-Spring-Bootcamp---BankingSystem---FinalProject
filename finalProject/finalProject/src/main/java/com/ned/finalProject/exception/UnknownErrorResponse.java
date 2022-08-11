package com.ned.finalProject.exception;

public class UnknownErrorResponse {
	
	private String message;

	public UnknownErrorResponse() {
		this.message = "Unknown Error";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
