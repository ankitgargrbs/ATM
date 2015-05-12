package com.mc.atm.services;

import com.mc.atm.ATMCard;

public class ATMCardAuthenticationService implements AuthenticationService {

	@Override
	public boolean authenticateUser(ATMCard card, String userPin) {
		return true;
	}

}
