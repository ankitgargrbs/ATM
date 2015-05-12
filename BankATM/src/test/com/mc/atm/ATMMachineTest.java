package com.mc.atm;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mc.atm.services.AuthenticationService;
import com.mc.atm.services.BankATMService;
import com.mc.atm.services.BankTransactionStatusCode;
import com.mc.atm.services.ConsoleOutput;
import com.mc.atm.services.InputService;

public class ATMMachineTest {

	@Mock
	private AuthenticationService authService;

	@Mock
	private CashDispenser dispenser;

	@Mock
	private BankATMService atmService;

	@Mock
	private InputService inputService;

	@Mock
	private ConsoleOutput consoleService;

	@Mock
	private Transaction txn;

	private ATMMachine atmMachine;

	@Before
	public void setup() throws IOException {
		MockitoAnnotations.initMocks(this);
		atmMachine = new ATMMachine(authService, dispenser, atmService,
				inputService, consoleService);
		
		when(inputService.inputATMCardNumber()).thenReturn(
				"2321-4343-2343-4545");
		when(inputService.inputPin()).thenReturn("3434");
		when(inputService.inputAmount()).thenReturn(1000);
		when(
				authService.authenticateUser(any(ATMCard.class),
						any(String.class))).thenReturn(true);
		when(txn.getBankTxnStatusCode()).thenReturn(
				BankTransactionStatusCode.BALANCE_AVAILABLE,
				BankTransactionStatusCode.BALANCE_DEDUCTED);
		when(
				atmService.checkAccountBalance(any(Transaction.class),
						any(ATMCard.class))).thenReturn(txn);

		when(
				atmService.deductBalance(any(ATMCard.class),
						any(Transaction.class))).thenReturn(txn);
		when(dispenser.checkATMBal(any(Integer.class))).thenReturn(true);
	}

	@Test
	public void shouldAuthenticateUser() {
		

		atmMachine.startProcess();

		verify(authService).authenticateUser(any(ATMCard.class),
				any(String.class));
		
	}

	@Test
	public void shouldBeAbleToCheckBalanceForAnAuthenticateUser() {
	

		atmMachine.startProcess();

		verify(authService).authenticateUser(any(ATMCard.class),
				any(String.class));
		verify(atmService).checkAccountBalance(any(Transaction.class),
				any(ATMCard.class));
	}
	
	@Test
	public void shouldCheckBalanceInATMBeforeStartProcessingForAnAuthenticateUser()  {
	
		when(dispenser.checkATMBal(any(Integer.class))).thenReturn(false);
		atmMachine.startProcess();

		verify(authService).authenticateUser(any(ATMCard.class),
				any(String.class));
		verify(consoleService).displayMessage("Not having enough money in ATM Machine.");
		verify(atmService, times(0)).checkAccountBalance(any(Transaction.class),
				any(ATMCard.class));
	}

	@Test
	public void shouldNotCheckBalanceForUnAutheriseUser()  {
	
		when(
				authService.authenticateUser(any(ATMCard.class),
						any(String.class))).thenReturn(false);
		

		atmMachine.startProcess();

		verify(authService).authenticateUser(any(ATMCard.class),
				any(String.class));
		verify(atmService, times(0)).checkAccountBalance(
				any(Transaction.class), any(ATMCard.class));
		verify(consoleService).displayMessage("Invalid Pin. Please try again.");
	}

	@Test
	public void shouldBeAbleToDeductBalanceIfAvalaibleForAnAutheriseUser() {

		atmMachine.startProcess();

		verify(authService).authenticateUser(any(ATMCard.class),
				any(String.class));
		verify(atmService).checkAccountBalance(
				any(Transaction.class), any(ATMCard.class));
		verify(atmService).deductBalance(any(ATMCard.class),
				any(Transaction.class));
	}
	
	@Test
	public void shouldBeAbleToDisplayMessageForAnAutheriseUserIfNotHavingSufficientBalance() {
		
		when(txn.getBankTxnStatusCode()).thenReturn(
				BankTransactionStatusCode.NO_BAL_AVAILABLE);

		atmMachine.startProcess();

		verify(authService).authenticateUser(any(ATMCard.class),
				any(String.class));
		verify(atmService).checkAccountBalance(
				any(Transaction.class), any(ATMCard.class));
		verify(consoleService).displayMessage("Insufficient Balance in your account");
		verify(atmService, times(0)).deductBalance(any(ATMCard.class),
				any(Transaction.class));
	}
	
	@Test
	public void shouldBeAbleToDisplayMaessageIfUbaleToDeductBalanceIfAvalaibleForAnAutheriseUser() {

		when(txn.getBankTxnStatusCode()).thenReturn(
				BankTransactionStatusCode.BALANCE_AVAILABLE,
				BankTransactionStatusCode.FAILURE);


		atmMachine.startProcess();

		verify(authService).authenticateUser(any(ATMCard.class),
				any(String.class));
		verify(atmService).checkAccountBalance(
				any(Transaction.class), any(ATMCard.class));
		verify(atmService).deductBalance(any(ATMCard.class),
				any(Transaction.class));
		verify(consoleService).displayMessage("Bank communication failure");
	}
	
	@Test
	public void shouldBeAbleToRecieveCashIfBalanceAvalaibleForAnAutheriseUser() {

		atmMachine.startProcess();

		verify(consoleService).displayMessage("Starting ATM...");
		verify(authService).authenticateUser(any(ATMCard.class),
				any(String.class));
		verify(atmService).checkAccountBalance(
				any(Transaction.class), any(ATMCard.class));
		verify(atmService).deductBalance(any(ATMCard.class),
				any(Transaction.class));
		verify(dispenser).dispenseCash(any(Transaction.class));
		verify(consoleService).displayMessage("Please collect you cash...");
	}

	@After
	public void tearDown() {

	}
}