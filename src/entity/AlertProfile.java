package entity;

public class AlertProfile {
	private int alertProfileID;
	private String name;
	private Account accountID;

	public AlertProfile() {
	}

	public AlertProfile(String name, Account accountID) {
		this.name = name;
		this.accountID = accountID;
	}

	public int getAlertProfileID() {
		return alertProfileID;
	}

	public void setAlertProfileID(int alertProfileID) {
		this.alertProfileID = alertProfileID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Account getAccountID() {
		return accountID;
	}

	public void setAccountID(Account accountID) {
		this.accountID = accountID;
	}
}
