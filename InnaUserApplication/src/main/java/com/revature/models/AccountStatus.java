package com.revature.models;

public class AccountStatus {
	private int accountStatusId;
	private String accountStatusName;

	public AccountStatus() {
		super();
	}

	public AccountStatus(int accountStatusId, String accountStatusName) {
		super();
		this.accountStatusId = accountStatusId;
		this.accountStatusName = accountStatusName;
	}

	public int getAccountStatusId() {
		return accountStatusId;
	}

	public void setAccountStatusId(int accountStatusId) {
		this.accountStatusId = accountStatusId;
	}

	public String getAccountStatusName() {
		return accountStatusName;
	}

	public void setAccountStatusName(String accountStatusName) {
		this.accountStatusName = accountStatusName;
	}

	@Override
	public String toString() {
		return "AccountStatus [accountStatusId=" + accountStatusId + ", accountStatusName=" + accountStatusName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountStatusId;
		result = prime * result + ((accountStatusName == null) ? 0 : accountStatusName.hashCode());
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
		AccountStatus other = (AccountStatus) obj;
		if (accountStatusId != other.accountStatusId)
			return false;
		if (accountStatusName == null) {
			if (other.accountStatusName != null)
				return false;
		} else if (!accountStatusName.equals(other.accountStatusName))
			return false;
		return true;
	}
	
	
	
}
