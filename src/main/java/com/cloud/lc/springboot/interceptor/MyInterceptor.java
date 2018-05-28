package com.cloud.lc.springboot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cloud.lc.springboot.logger.Logger;

/**
 * 自定义拦截器
 * @author LiuChao
 *
 */

@Component
public class MyInterceptor implements HandlerInterceptor {

	/**
	 *最终 finish 之后
	 * @author LiuChao
	 *
	 */
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		Logger.info("========afterCompletion=========");
        Long start = (Long) arg0.getAttribute("startTime");
        Logger.info("耗时:"+(System.currentTimeMillis() - start));
        
	}

	/**
	 *  执行返回视图的时候
	 * @author LiuChao
	 *
	 */
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		Logger.info("========postHandle=========");
        Long start = (Long) arg0.getAttribute("startTime");
        if(arg3 != null){
        	Logger.info("model param: "+ JSON.toJSONString(arg3.getModel()) );
        	Logger.info("model  view: "+ arg3.getViewName() );
        }
        Logger.info("耗时:"+(System.currentTimeMillis() - start));
	}

	/**
	  * 执行之前
	 * @author LiuChao
	 *
	 */
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		
		Logger.info("========preHandle=========");
        //Logger.info(((HandlerMethod)arg2).getBean().getClass().getName());
        //Logger.info(((HandlerMethod)arg2).getMethod().getName());
        
        arg0.setAttribute("startTime", System.currentTimeMillis());
        
        return true;
	}

}
