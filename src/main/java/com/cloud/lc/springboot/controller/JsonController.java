package com.cloud.lc.springboot.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cloud.lc.springboot.logger.Logger;
import com.cloud.lc.springboot.ro.Result;
import com.cloud.lc.springboot.ro.User;

/**
 * 添加json转换器
 * @author LiuChao
 *
 */
@Controller
@RequestMapping("json")
public class JsonController {
	
	
	/**
	 * 添加细粒度访问限制
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/test")
	@CrossOrigin(origins="http://localhost:8088")
	public Object test(){
		User u = new User("liuchao", 27, new Date());
		return u;
	}
	
	@ResponseBody
	@RequestMapping("/testInput")
	public Object input(@RequestBody User user){
		Logger.info("收到消息："+ JSON.toJSONString(user));
		return Result.succ("ok", "添加用户成功");
	}
	
	
}
