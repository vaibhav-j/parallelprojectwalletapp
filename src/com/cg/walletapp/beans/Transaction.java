package com.cg.walletapp.beans;

import java.math.BigDecimal;

public class Transaction {
	@Override
	public String toString() {
		return "Transaction [Amount = " + money + ", Phone Number = " + phno + ", Account Type = " + transType + "]";
	}
	private BigDecimal money;
	private String phno;
	private String transType;
	
	public Transaction(BigDecimal money, String phno, String transType){
		this.money = money;
		this.phno = phno;
		this.transType=transType;
	}
	
	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	
	
}
