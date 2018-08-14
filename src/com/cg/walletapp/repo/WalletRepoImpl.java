package com.cg.walletapp.repo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.cg.walletapp.beans.Customer;
import com.cg.walletapp.beans.Wallet;

public class WalletRepoImpl implements WalletRepo {

	Map<String, Customer> data = new HashMap<String, Customer>();

	public WalletRepoImpl() {

		data.put("9900112200", new Customer("Vijay", "9900112200", new Wallet(
				new BigDecimal(9000))));
		data.put("9963242411", new Customer("Ajay", "9963242411", new Wallet(
				new BigDecimal(6000))));
		data.put("9922950520", new Customer("Jay", "9922950520", new Wallet(
				new BigDecimal(7000))));
	}

	public boolean save(Customer customer) {
		data.put(customer.getMobileNo(), customer);
		System.out.println("Saving Data");
		return true;
	}

	public Customer findOne(String mobileNo) {
		if (data.containsKey(mobileNo))
			return data.get(mobileNo);
		return null;
	}
}