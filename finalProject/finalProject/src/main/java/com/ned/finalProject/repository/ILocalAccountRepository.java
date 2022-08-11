package com.ned.finalProject.repository;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ned.finalProject.model.Account;

@Mapper
public interface ILocalAccountRepository {
	public void createAccount(@Param("account")Account account);
	public Account getAccountById(int id);
	public Account getAccountByNumber(int number);
	public void updateAccount(@Param("account")Account account);
	public void deleteAccount(int id,boolean isDeleted);
	public void depositAccount(@Param("account")Account account);
}
