package com.cg.walletapp.exception;

public class InsufficientBalanceException extends RuntimeException{

	public InsufficientBalanceException(String msg) {
		super(msg);
		System.out.println("Insufficient Balance in Account !!");
	}
}
