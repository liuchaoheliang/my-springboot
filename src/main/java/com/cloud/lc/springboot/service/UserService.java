package com.cloud.lc.springboot.service;

import java.util.List;

import com.cloud.lc.springboot.dao.entity.User;

public interface UserService {
	
	public long  insertUser( User u);
	
	public User  getUserById(long id);
	
	public List<User>  getUserByConditions( User u );
}
