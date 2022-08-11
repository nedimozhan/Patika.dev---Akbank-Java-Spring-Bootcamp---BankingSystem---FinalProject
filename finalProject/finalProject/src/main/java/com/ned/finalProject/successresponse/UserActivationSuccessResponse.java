package com.ned.finalProject.successresponse;

public class UserActivationSuccessResponse {
	
	private String status;
	private String message;
	
	public UserActivationSuccessResponse(boolean enabled) {
		
		this.status = "success";
		
		if(enabled == true) {
			this.message = "User Enabled";
		}
		else {
			this.message = "User Disabled";
		}
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
