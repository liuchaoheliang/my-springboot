package com.cloud.lc.springboot.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cloud.lc.springboot.base.mapper.BaseMapper;
import com.cloud.lc.springboot.dao.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
