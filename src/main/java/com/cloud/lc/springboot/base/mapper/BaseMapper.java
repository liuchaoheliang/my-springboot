package com.cloud.lc.springboot.base.mapper;

import java.util.List;

public interface BaseMapper<T> {
	
	public void insert(T object);
	
	public T getById(long id);
	
	public int updateById(T object);
	
	public int deleteById(long id);
	
	public List<T> getByConditions(T object);
	
}
