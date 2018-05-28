package com.cloud.lc.springboot.dao.entity;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.cloud.lc.springboot.base.entity.BaseEntity;

/**
 * 用户实体
 * @author LiuChao
 *
 */
@Alias("user")
public class User extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7101658285714391154L;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 年龄
	 */
	private Integer age;
	
	/**
	 * 备注
	 */
	private String remark;
	
	
	/**
	 * 性别 0：男 1：女
	 */
	private int sex;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public int getSex() {
		return sex;
	}


	public void setSex(int sex) {
		this.sex = sex;
	}


}
