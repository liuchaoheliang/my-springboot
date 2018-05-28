package com.cloud.lc.springboot.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloud.lc.springboot.dao.entity.User;
import com.cloud.lc.springboot.dao.mapper.UserMapper;
import com.cloud.lc.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserMapper  userMapper;
	
	@Override
	public long insertUser(User u) {
		userMapper.insert(u);
		return u.getId();
	}

	@Override
	public User getUserById(long id) {
		return userMapper.getById(id);
	}

	@Override
	public List<User> getUserByConditions(User u) {
		return userMapper.getByConditions(u);
	}

}
