package com.ned.finalProject.exception;

public class UsernamePasswordEmailAlreadyUsedException extends RuntimeException{
	
	public UsernamePasswordEmailAlreadyUsedException(String message) {
		super(message);
	}
	
}
