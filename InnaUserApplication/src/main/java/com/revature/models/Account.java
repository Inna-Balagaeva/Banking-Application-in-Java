package com.revature.models;

public class Account {
private int accountNo;
private double accountBalance;
private int refUserId;
private AccountType type;
private AccountStatus status;

public Account() {
	super();
}

public Account(int accountNo, double accountBalance, int refUserId, AccountType type, AccountStatus status) {
	super();
	this.accountNo = accountNo;
	this.accountBalance = accountBalance;
	this.refUserId = refUserId;
	this.type = type;
	this.status = status;
}

public int getAccountNo() {
	return accountNo;
}

public void setAccountNo(int accountNo) {
	this.accountNo = accountNo;
}

public double getAccountBalance() {
	return accountBalance;
}

public void setAccountBalance(double accountBalance) {
	this.accountBalance = accountBalance;
}

public int getRefUserId() {
	return refUserId;
}

public void setRefUserId(int refUserId) {
	this.refUserId = refUserId;
}

public AccountType getType() {
	return type;
}

public void setType(AccountType type) {
	this.type = type;
}

public AccountStatus getStatus() {
	return status;
}

public void setStatus(AccountStatus status) {
	this.status = status;
}

@Override
public String toString() {
	return "Account [accountNo=" + accountNo + ", accountBalance=" + accountBalance + ", refUserId=" + refUserId
			+ ", type=" + type + ", status=" + status + "]";
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	long temp;
	temp = Double.doubleToLongBits(accountBalance);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result + accountNo;
	result = prime * result + refUserId;
	result = prime * result + ((status == null) ? 0 : status.hashCode());
	result = prime * result + ((type == null) ? 0 : type.hashCode());
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
	Account other = (Account) obj;
	if (Double.doubleToLongBits(accountBalance) != Double.doubleToLongBits(other.accountBalance))
		return false;
	if (accountNo != other.accountNo)
		return false;
	if (refUserId != other.refUserId)
		return false;
	if (status == null) {
		if (other.status != null)
			return false;
	} else if (!status.equals(other.status))
		return false;
	if (type == null) {
		if (other.type != null)
			return false;
	} else if (!type.equals(other.type))
		return false;
	return true;
}




}
