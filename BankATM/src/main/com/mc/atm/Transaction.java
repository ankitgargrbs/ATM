package com.mc.atm;

import java.io.Serializable;

import com.mc.atm.services.BankTransactionStatusCode;

public class Transaction implements Serializable {

	private static final long serialVersionUID = 7792375728881621655L;
	private String transactionId;
	private BankTransactionStatusCode bankTxnStatusCode;
	private Integer amount;

	public Transaction(String transactionId,
			BankTransactionStatusCode bankTxnStatusCode, Integer amount) {
		this.transactionId = transactionId;
		this.bankTxnStatusCode = bankTxnStatusCode;
		this.amount = amount;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public BankTransactionStatusCode getBankTxnStatusCode() {
		return bankTxnStatusCode;
	}

	public void setBankTxnStatusCode(BankTransactionStatusCode code) {
		this.bankTxnStatusCode = code;
	}

	public Integer getAmount() {
		return amount;
	}

}
