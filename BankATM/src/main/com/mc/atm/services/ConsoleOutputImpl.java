package com.mc.atm.services;


public class ConsoleOutputImpl implements ConsoleOutput {

	@Override
	public void displayMessage(String message) {
		System.out.println(message);
	}

}
