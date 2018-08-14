package com.cg.walletapp.pl;

import java.math.BigDecimal;
import java.util.Scanner;

import com.cg.walletapp.exception.InvalidInputException;
import com.cg.walletapp.service.*;

public class Client {
	public static void main(String[] args) {
		
		Scanner choice = new Scanner(System.in);
		WalletServiceImpl action = new WalletServiceImpl();
		String name;
		String mobileno, sourceMobileNo, targetMobileNo;
		BigDecimal amount;

		do{
		System.out.println("Welcome to the Payment Wallet. Choose an action : ");
		System.out.println("1. Create Account");
		System.out.println("2. Show Balance");
		System.out.println("3. Transfer Fund");
		System.out.println("4. Deposit Money");
		System.out.println("5. Withdraw Money");
		System.out.println("6. View Transactions");
		System.out.println("7. Exit");

		switch (choice.nextInt()) {

		case 1:

			System.out.println("Enter Name : ");
			name = choice.next();
			System.out.println("Enter Mobile Number : ");
			mobileno = choice.next();
			System.out.println("Enter the Amount : ");
			amount = choice.nextBigDecimal();
			if (amount.intValue() < 0)
				extracted();
			else
				action.createAccount(name, mobileno, amount);
			break;

		case 2:

			System.out.println("Enter Mobile Number : ");
			mobileno = choice.next();
			action.showBalance(mobileno);
			break;

		case 3:

			System.out.println("Enter Source Mobile Number : ");
			sourceMobileNo = choice.next();
			System.out.println("Enter Target Mobile Number : ");
			targetMobileNo = choice.next();
			System.out.println("Enter the Amount : ");
			amount = choice.nextBigDecimal();
			if (amount.intValue() < 0)
				extracted();
			else
				action.fundTransfer(sourceMobileNo, targetMobileNo, amount);
			break;

		case 4:

			System.out.println("Enter Mobile Number : ");
			mobileno = choice.next();
			System.out.println("Enter the Amount : ");
			amount = choice.nextBigDecimal();
			if (amount.intValue() < 0)
				extracted();
			else
				action.depositAmount(mobileno, amount);
			break;

		case 5:

			System.out.println("Enter Mobile Number : ");
			mobileno = choice.next();
			System.out.println("Enter the Amount : ");
			amount = choice.nextBigDecimal();
			if (amount.intValue() < 0)
				extracted();
			else
				action.withdrawAmount(mobileno, amount);
			break;

		case 6:
			System.out.println("Enter Mobile Number : ");
			mobileno = choice.next();
			action.printTransaction(mobileno);
			break;

		case 7:
			break;

		default:
			extracted1();
		}

		System.out.println("Press 1 to do other operation. Press any other key to confirm exit");
		
		}while(choice.next().equals("1"));
		
		choice.close();
	}

	private static void extracted1() {
		throw new InvalidInputException("Wrong Choice, buddy!");
	}

	private static void extracted() {
		throw new InvalidInputException("Negative Amount entered");
	}
}
