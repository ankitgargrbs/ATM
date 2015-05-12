package com.mc.atm.services;

public enum BankTransactionStatusCode {

	SUCCESS, NEW, BALANCE_AVAILABLE, NO_BAL_AVAILABLE, DAILY_WITHDRAWAL_LIMIT_EXC, BALANCE_DEDUCTED, CASH_DISPENSED, CASH_DISPENSE_FAILURE, FAILURE;
}