package com.ned.finalProject.createrequest;

public class UserActivationRequest {

	private String enabled;
	
	public UserActivationRequest() {
	
	}
	
	public UserActivationRequest(String enabled) {
		this.enabled = enabled;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
}
