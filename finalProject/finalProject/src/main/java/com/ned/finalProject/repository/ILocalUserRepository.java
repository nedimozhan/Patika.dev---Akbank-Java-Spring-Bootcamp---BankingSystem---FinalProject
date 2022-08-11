package com.ned.finalProject.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ned.finalProject.createrequest.UserRegisterRequest;
import com.ned.finalProject.model.User;

@Mapper
public interface ILocalUserRepository {
	
	public void insertUser(@Param("user")User user);
	public User getUserById(int id);
	public User getUserByUsername(String username);
	public User getUserByEmail(String email);
	public void updateUser(@Param("user")User user);
}
