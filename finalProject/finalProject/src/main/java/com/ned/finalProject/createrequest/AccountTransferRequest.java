package com.ned.finalProject.createrequest;

public class AccountTransferRequest {

	private float amount;
	private int receiverAccountId;

	public AccountTransferRequest() {

	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getReceiverAccountId() {
		return receiverAccountId;
	}

	public void setReceiverAccountId(int receiverAccountId) {
		this.receiverAccountId = receiverAccountId;
	}

}
