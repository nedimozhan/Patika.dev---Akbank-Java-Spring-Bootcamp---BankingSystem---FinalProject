package com.ned.finalProject.model;

import java.sql.Timestamp;

import com.ned.finalProject.createrequest.AccountCreateRequest;

public class Account {

	private int id;
	private int userId;
	private int bankId;
	private int number;
	private String type;
	private float balance;
	private Timestamp creationDate;
	private Timestamp lastUpdateDate;
	private boolean isDeleted;
	
	public Account() {
		
	}
	
	public Account(AccountCreateRequest accountCreateRequest,int number,int userId) {
		
		this.number = number;
		this.userId = userId;
		this.bankId = accountCreateRequest.getBankId();
		this.type = accountCreateRequest.getType();		
		this.balance = 0f;
		this.creationDate = new Timestamp(System.currentTimeMillis());
		this.lastUpdateDate = new Timestamp(System.currentTimeMillis());
		this.isDeleted = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public Timestamp getLastUpdatedDate() {
		return lastUpdateDate;
	}

	public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
		this.lastUpdateDate = lastUpdatedDate;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
