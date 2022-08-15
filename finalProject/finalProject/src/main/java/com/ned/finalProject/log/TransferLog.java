package com.ned.finalProject.log;

public class TransferLog extends AbstractLog {

	private String logMessage;
	private int senderAccountNumber;
	private int receiverAccountNumber;
	private float amount;
	
	public TransferLog() {
		
	}
	
	public TransferLog(int senderAccountNumber, int receiverAccountNumber, float amount) {
		this.senderAccountNumber = senderAccountNumber;
		this.receiverAccountNumber = receiverAccountNumber;
		this.amount = amount;
		
		formatToLog();
	}

	@Override
	public String getLogMessage() {
		return this.logMessage;
	}
	
	private void formatToLog() {
		this.logMessage = String.valueOf(this.amount) + ", " + String.valueOf(this.senderAccountNumber) + " to "
				+ String.valueOf(this.receiverAccountNumber) + " : transferred";
	}

}
