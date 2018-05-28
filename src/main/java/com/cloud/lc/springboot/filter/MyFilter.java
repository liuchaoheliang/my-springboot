package com.cloud.lc.springboot.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


import com.cloud.lc.springboot.logger.Logger;

public class MyFilter implements Filter {

	public void destroy() {
		Logger.info("======销毁过滤器=========");
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		long start = System.currentTimeMillis();
		Logger.info("filter start ..." );
		arg0.setCharacterEncoding("utf-8");
		arg1.setCharacterEncoding("utf-8");
		arg2.doFilter(arg0, arg1);
		//charset encode 
		
		Logger.info("filter end , 耗时：" + (System.currentTimeMillis() - start));
	}

	public void init(FilterConfig arg0) throws ServletException {
		Logger.info("======初始化过滤器=========");
	}

}
