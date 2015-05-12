package com.mc.atm.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputServiceImpl implements InputService {

	private BufferedReader bufferRead = new BufferedReader(
			new InputStreamReader(System.in));

	@Override
	public String inputATMCardNumber() throws IOException {
		return bufferRead.readLine();
	}

	@Override
	public String inputPin() throws IOException {
		return bufferRead.readLine();
	}

	@Override
	public Integer inputAmount() throws IOException {
		return Integer.parseInt(bufferRead.readLine());
	}

}
