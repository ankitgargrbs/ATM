package com.mc.atm.services;

import com.mc.atm.ATMCard;
import com.mc.atm.Transaction;

public interface BankATMService {

	Transaction checkAccountBalance(Transaction transaction, ATMCard atmCard);

	Transaction deductBalance(ATMCard atmCard, Transaction transaction);

	Transaction acknowledgeCashDispense(Transaction transaction);
}
