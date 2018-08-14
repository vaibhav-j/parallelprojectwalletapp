package com.cg.walletapp.repo;

import com.cg.walletapp.beans.Customer;

public interface WalletRepo {
	public boolean save(Customer customer);

	public Customer findOne(String mobileNo);
}
