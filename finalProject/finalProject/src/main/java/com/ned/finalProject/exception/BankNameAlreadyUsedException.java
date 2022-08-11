package com.ned.finalProject.exception;

public class BankNameAlreadyUsedException extends RuntimeException{
	public BankNameAlreadyUsedException(String message) {
		super(message);
	}
}
