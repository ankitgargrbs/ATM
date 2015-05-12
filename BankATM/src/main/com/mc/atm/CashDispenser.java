package com.mc.atm;

public interface CashDispenser {

	Transaction dispenseCash(Transaction transcation);

	boolean checkATMBal(Integer amount);
}
