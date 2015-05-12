package com.mc.atm.services;

import java.io.IOException;

public interface InputService {
	String inputATMCardNumber() throws IOException;

	String inputPin() throws IOException;

	Integer inputAmount() throws IOException;
}
