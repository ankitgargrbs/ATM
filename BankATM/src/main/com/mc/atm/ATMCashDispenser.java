package com.mc.atm;

import com.mc.atm.services.BankTransactionStatusCode;

public class ATMCashDispenser implements CashDispenser {

	@Override
	public Transaction dispenseCash(Transaction transcation) {
		transcation
				.setBankTxnStatusCode(BankTransactionStatusCode.CASH_DISPENSED);
		return transcation;
	}

	@Override
	public boolean checkATMBal(Integer amount) {
		return true;
	}
}
