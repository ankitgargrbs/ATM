package com.mc.atm.services;

import com.mc.atm.ATMCard;
import com.mc.atm.Transaction;

public class BankATMServiceImpl implements BankATMService {

	@Override
	public Transaction checkAccountBalance(Transaction transaction, ATMCard atmCard) {
		transaction
				.setBankTxnStatusCode(BankTransactionStatusCode.BALANCE_AVAILABLE);
		return transaction;
	}

	@Override
	public Transaction deductBalance(ATMCard atmCard, Transaction transaction) {
		transaction
				.setBankTxnStatusCode(BankTransactionStatusCode.BALANCE_DEDUCTED);
		return transaction;
	}

	@Override
	public Transaction acknowledgeCashDispense(Transaction transaction) {
		return transaction;
	}

}
