package com.revature.models;

public class AccountType {
	private int accountTypeId;
	private String accountTypeName;

	public AccountType() {
		super();
	}

	public AccountType(int accountTypeId, String accountTypeName) {
		super();
		this.accountTypeId = accountTypeId;
		this.accountTypeName = accountTypeName;
	}

	public int getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(int accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public String getAccountTypeName() {
		return accountTypeName;
	}

	public void setAccountTypeName(String accountTypeName) {
		this.accountTypeName = accountTypeName;
	}

	@Override
	public String toString() {
		return "AccountType [accountTypeId=" + accountTypeId + ", accountTypeName=" + accountTypeName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountTypeId;
		result = prime * result + ((accountTypeName == null) ? 0 : accountTypeName.hashCode());
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
		AccountType other = (AccountType) obj;
		if (accountTypeId != other.accountTypeId)
			return false;
		if (accountTypeName == null) {
			if (other.accountTypeName != null)
				return false;
		} else if (!accountTypeName.equals(other.accountTypeName))
			return false;
		return true;
	}

	
	
	
}
