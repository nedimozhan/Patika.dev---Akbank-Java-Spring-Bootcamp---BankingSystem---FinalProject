package com.ned.finalProject.log;

public class DepositLog extends AbstractLog {

	private String logMessage;
	private float amount;
	private int accountNumber;
	
	public DepositLog() {
		
	}
	
	public DepositLog(float amount, int accountNumber) {
		this.accountNumber = accountNumber;
		this.amount = amount;
		
		formatToLog();
	}

	@Override
	public String getLogMessage() {
		return this.logMessage;
	}

	private void formatToLog() {
		this.logMessage = String.valueOf(this.accountNumber) + ", " + String.valueOf(this.amount) + " : deposited";

	}

}
