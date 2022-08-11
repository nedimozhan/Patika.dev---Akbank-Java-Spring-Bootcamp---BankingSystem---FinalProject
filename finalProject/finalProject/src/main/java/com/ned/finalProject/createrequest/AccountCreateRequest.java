package com.ned.finalProject.createrequest;

public class AccountCreateRequest {

	private int bankId;
	private String type;

	public AccountCreateRequest() {

	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
