package com.ned.finalProject.exception;

public class UsernameEmailAlreadyUsedException extends RuntimeException{
	
	public UsernameEmailAlreadyUsedException(String message) {
		super(message);
	}
	
}
