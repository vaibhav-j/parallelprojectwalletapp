package com.cg.walletapp.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cg.walletapp.beans.Customer;
import com.cg.walletapp.beans.Transaction;
import com.cg.walletapp.beans.Wallet;
import com.cg.walletapp.exception.InsufficientBalanceException;
import com.cg.walletapp.exception.InvalidInputException;
import com.cg.walletapp.repo.WalletRepoImpl;

public class WalletServiceImpl implements WalletService {

	WalletRepoImpl repo;
	
	public WalletServiceImpl() {
		repo = new WalletRepoImpl();
	}
	
	public WalletServiceImpl(Map<String,Customer> newmap) {
		
	}

	List<Transaction> datalist = new ArrayList<Transaction>();

	public Customer createAccount(String name, String mobileNo,
			BigDecimal amount) {
		Wallet cash = new Wallet(amount);
		Customer customer = new Customer(name, mobileNo, cash);
		repo.save(customer);
		return customer;
	}

	public Customer showBalance(String mobileNo) {
		Customer customer = repo.findOne(mobileNo);
		System.out.println(customer);
		if (customer != null) {
			System.out.println(customer);
		} else
			throw new InvalidInputException("Invalid Phone Number");
		return customer;
	}

	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo,
			BigDecimal amount) {
		Customer sender = repo.findOne(sourceMobileNo);
		Customer receiver = repo.findOne(targetMobileNo);
		if (sender != null && receiver != null) {
			BigDecimal transfer = sender.getWallet().getBalance();
			if (transfer.compareTo(amount) == 1) {
				BigDecimal senderbal = sender.getWallet().getBalance()
						.subtract(amount);
				BigDecimal receiverbal = receiver.getWallet().getBalance()
						.add(amount);
				sender.getWallet().setBalance(senderbal);
				receiver.getWallet().setBalance(receiverbal);
				repo.save(sender);
				repo.save(receiver);
				datalist.add(new Transaction(amount.negate(), sourceMobileNo,
						"Transfered to " + receiver.getName()));
				datalist.add(new Transaction(amount, targetMobileNo,
						"Transfered by " + sender.getName()));
			} else
				throw new InsufficientBalanceException("Cannot transfer");

		} else
			throw new InvalidInputException("Invalid Phone Number");
		return sender;
	}

	public Customer depositAmount(String mobileNo, BigDecimal amount) {
		Customer customer = repo.findOne(mobileNo);
		if (customer != null) {
			BigDecimal deposit = customer.getWallet().getBalance().add(amount);
			customer.getWallet().setBalance(deposit);
			repo.save(customer);
			datalist.add(new Transaction(amount, mobileNo, "Self-Deposit"));
		} else
			throw new InvalidInputException("Invalid Phone Number");
		return customer;
	}

	public Customer withdrawAmount(String mobileNo, BigDecimal amount) {
		Customer customer = repo.findOne(mobileNo);
		if (customer != null) {
			BigDecimal withdraw = customer.getWallet().getBalance();
			if (withdraw.compareTo(amount) == 1) {
				withdraw = customer.getWallet().getBalance().subtract(amount);
				customer.getWallet().setBalance(withdraw);
				repo.save(customer);
				datalist.add(new Transaction(amount.negate(), mobileNo,
						"Self-Withdrawl"));
			} else
				throw new InsufficientBalanceException("Cannot Withdraw");
		} else
			throw new InvalidInputException("Invalid Phone Number");
		return customer;
	}

	public Customer printTransaction(String mobileNo) {
		Customer customer = repo.findOne(mobileNo);
		
		if (customer != null) {
		for (Transaction e : datalist)
		{
			if(mobileNo.equals(e.getPhno()))
				System.out.println(e.toString());
		}
		}
		else
			throw new InvalidInputException("Invalid Phone Number");
		return null;
	}
}
