package com.mc.atm;

public class ATMCard {
	private final String cardNumber;
	private final String displayName;
	private final String bankName;
	/**
	 * e.g VISA, MASTERCARD
	 */
	private final String cardType;

	private ATMCard(String cardNumber, String displayName, String bankName,
			String cardType) {
		this.cardNumber = cardNumber;
		this.displayName = displayName;
		this.bankName = bankName;
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getBankName() {
		return bankName;
	}

	public String getCardType() {
		return cardType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result
				+ ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result
				+ ((cardType == null) ? 0 : cardType.hashCode());
		result = prime * result
				+ ((displayName == null) ? 0 : displayName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ATMCard other = (ATMCard) obj;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (cardType == null) {
			if (other.cardType != null)
				return false;
		} else if (!cardType.equals(other.cardType))
			return false;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		return true;
	}

	public static class ATMCardBuilder {
		private String cardNumber = "XXXX-XXXX-XXXX-9768";
		private String displayName = "Default User";
		private String bankName = "Sample Bank";
		/**
		 * e.g VISA, MASTERCARD
		 */
		private String cardType = "VISA";

		public ATMCardBuilder cardNumber(String cardNumber) {
			this.cardNumber = cardNumber;
			return this;
		}

		public ATMCardBuilder displayName(String displayName) {
			this.displayName = displayName;
			return this;
		}

		public ATMCardBuilder bankName(String bankName) {
			this.bankName = bankName;
			return this;
		}

		public ATMCardBuilder cardType(String cardType) {
			this.cardType = cardType;
			return this;
		}

		public ATMCard build() {
			return new ATMCard(cardNumber, displayName, bankName, cardType);
		}
	}

}
