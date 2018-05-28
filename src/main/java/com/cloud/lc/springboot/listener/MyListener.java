package com.cloud.lc.springboot.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cloud.lc.springboot.logger.Logger;

public class MyListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		Logger.info("容器监听已销毁");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		Logger.info("容器监听正在初始化...");
	}

}
