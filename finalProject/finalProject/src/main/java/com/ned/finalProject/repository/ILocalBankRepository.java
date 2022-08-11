package com.ned.finalProject.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.ned.finalProject.model.Bank;

@Mapper
public interface ILocalBankRepository {
	public void insertData(@Param("bank")Bank bank);
	public Bank getDataById(int id);
	public Bank getDataByName(String name);
}
