package com.cg.walletapp.test;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cg.walletapp.beans.Customer;
import com.cg.walletapp.beans.Transaction;
import com.cg.walletapp.beans.Wallet;
import com.cg.walletapp.exception.InsufficientBalanceException;
import com.cg.walletapp.exception.InvalidInputException;
import com.cg.walletapp.service.WalletService;
import com.cg.walletapp.service.WalletServiceImpl;


public class TestClass {

	
	WalletService service = new WalletServiceImpl();
	Customer user;
	
	@Test
	public void testCreateAccount() {
		user=service.createAccount("Vaibhav","8058999999",new BigDecimal(1000));
		assertEquals("Vaibhav",user.getName());
	}
	
	@Test
	public void testShowBalance () {
		user=service.showBalance("9900112200");
		assertEquals("Vijay",user.getName());
	}
	
	@Test
	public void testFundTransfer() {
		user=service.fundTransfer("9963242411","9900112200",new BigDecimal(500));
		assertEquals("9963242411",user.getMobileNo());
	}
	
	@Test
	public void testDepositAmount () {
		user=service.depositAmount("9900112200",new BigDecimal(6000));
		assertEquals("Vijay",user.getName());
	}
	
	@Test
	public void testWithdrawAmount() {
		user=service.withdrawAmount("9900112200",new BigDecimal(6000));
		assertEquals("Vijay",user.getName());
	}
	
	@Test
	public void testPrintTransaction () {
		assertEquals(service.printTransaction("9900112200"),null);
	}

	@Test
	public void testCustomer() {
		Customer customer = new Customer("Vaibhav","8058999999",new Wallet(new BigDecimal(8000)));
		assertEquals("Vaibhav",customer.getName());
		assertEquals("8058999999",customer.getMobileNo());
		assertEquals(new BigDecimal(8000),customer.getWallet().getBalance());
	}
	
	@Test
	public void testWallet() {
		Wallet wallet = new Wallet(new BigDecimal(8000));
		assertEquals(new BigDecimal(8000),wallet.getBalance());
	}
	
	@Test
	public void testTransaction() {
		Transaction trans = new Transaction(new BigDecimal(8000),"8058999999","Deposit");
		assertEquals(new BigDecimal(8000),trans.getMoney());
		assertEquals("8058999999",trans.getPhno());
		assertEquals("Deposit",trans.getTransType());
	}
	
	@Test(expected = InsufficientBalanceException.class)
	public void testIBE() {
		
	}
	
	@Test(expected = InvalidInputException.class)
	public void testIIE() {
		
	}
	
	
	

}
