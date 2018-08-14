package com.cg.walletapp.exception;

public class InvalidInputException extends RuntimeException {

	public InvalidInputException(String msg) {
		super(msg);
		System.out.println("Invalid Input given !!");
	}
}
