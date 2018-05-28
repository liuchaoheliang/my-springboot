package com.cloud.lc.springboot.ro;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class User {
	
	private String name;
	
	private int age;
	
	@JSONField(format="yyyy-MM-dd")
	private Date birthday;
	
	

	public User() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public User(String name, int age, Date birthday) {
		this.name = name;
		this.age = age;
		this.birthday = birthday;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
