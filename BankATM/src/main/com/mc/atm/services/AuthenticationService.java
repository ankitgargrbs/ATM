package com.mc.atm.services;

import com.mc.atm.ATMCard;

public interface AuthenticationService {

	boolean authenticateUser(ATMCard card, String userPin);
}
