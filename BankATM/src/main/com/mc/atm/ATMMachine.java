package com.mc.atm;

import java.io.IOException;

import com.mc.atm.services.AuthenticationService;
import com.mc.atm.services.BankATMService;
import com.mc.atm.services.BankTransactionStatusCode;
import com.mc.atm.services.ConsoleOutput;
import com.mc.atm.services.InputService;

public class ATMMachine {

	private AuthenticationService authService;
	private CashDispenser dispenser;
	private BankATMService atmService;
	private InputService inputService;
	private ConsoleOutput consoleService;

	public ATMMachine(AuthenticationService authService,
			CashDispenser dispenser, BankATMService atmService,
			InputService inputService, ConsoleOutput consoleService) {
		this.authService = authService;
		this.dispenser = dispenser;
		this.atmService = atmService;
		this.inputService = inputService;
		this.consoleService = consoleService;
	}

	public void startProcess() {
		consoleService.displayMessage("Starting ATM...");
		consoleService.displayMessage("Please enter your ATM card");

		try {
			String atmNumber = inputService.inputATMCardNumber();
			
			ATMCard atmCard = new ATMCard.ATMCardBuilder()
					.cardNumber(atmNumber).build();
			consoleService.displayMessage("Please enter your pin");
			String atmPin = inputService.inputPin();

			boolean isAuthenticed = authService.authenticateUser(atmCard,
					atmPin);
			if (isAuthenticed) {
				consoleService
						.displayMessage("Enter the amount you want to withdraw");
				Integer amount = inputService.inputAmount();
				Transaction transaction = initiateTranscation(atmCard, amount);

				boolean isHavingEnoughBalanceInATM = dispenser
						.checkATMBal(amount);

				if (isHavingEnoughBalanceInATM) {

					transaction = atmService.checkAccountBalance(transaction,
							atmCard);
					
					if (BankTransactionStatusCode.BALANCE_AVAILABLE
							.equals(transaction.getBankTxnStatusCode())) {
						
						transaction = atmService.deductBalance(atmCard,
								transaction);
						if (BankTransactionStatusCode.BALANCE_DEDUCTED
								.equals(transaction.getBankTxnStatusCode())) {

							transaction = dispenser.dispenseCash(transaction);
							consoleService
									.displayMessage("Please collect you cash...");
							atmService.acknowledgeCashDispense(transaction);
						} else {
							consoleService
									.displayMessage("Bank communication failure");
						}
					} else {
						consoleService
								.displayMessage("Insufficient Balance in your account");
					}

				} else {
					consoleService
							.displayMessage("Not having enough money in ATM Machine.");
				}

			} else {
				consoleService.displayMessage("Invalid Pin. Please try again.");
			}
		} catch (IOException e) {
			consoleService.displayMessage("Unable to read ATM card.");
		}
		
		consoleService.displayMessage("Please collect your Card");

	}

	private Transaction initiateTranscation(ATMCard card, Integer amount) {
		Transaction transcation = new Transaction(Long.toString(System
				.nanoTime()), BankTransactionStatusCode.NEW, amount);
		return transcation;
	}
}
