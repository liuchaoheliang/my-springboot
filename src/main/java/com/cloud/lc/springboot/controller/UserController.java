package com.cloud.lc.springboot.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.lc.springboot.dao.entity.User;
import com.cloud.lc.springboot.ro.Result;
import com.cloud.lc.springboot.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;

	@RequestMapping("/add")
	@ResponseBody
	public Object addUser(@RequestBody User u){
		long id = userService.insertUser(u);
		return Result.succ(id);
	}
	
	
}
