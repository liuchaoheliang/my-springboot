package com.cloud.lc.springboot.controller;

import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.logback.LogbackLoggingSystem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cloud.lc.springboot.logger.Logger;
import com.cloud.lc.springboot.ro.Result;

/**
 * 	添加页面视图转换器
 * @author LiuChao
 *
 */
@Controller
@RequestMapping("/freemarker")
public class HelloWordController {
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(){
		Logger.info("访问freemarker index 页面 ");
		return "index";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public Object helloWord(){
		Logger.info("收到请求");
		return Result.succ("copy that");
	}
	
	
	@RequestMapping(value="/helloWord",method=RequestMethod.GET)
	public String helloWord1(Map<String, Object> map){
		map.put("msg", "hello Charise !!!");
		Logger.info("访问freemarker hello word 页面 ");
		return "helloWord";
	}
	
	
	
}
