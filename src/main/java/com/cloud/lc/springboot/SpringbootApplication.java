package com.cloud.lc.springboot;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.cloud.lc.springboot.filter.MyFilter;
import com.cloud.lc.springboot.listener.MyListener;
import com.cloud.lc.springboot.logger.Logger;
import com.cloud.lc.springboot.servlet.MyServlet;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class SpringbootApplication  extends  SpringBootServletInitializer{
	
	
	
	
    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringbootApplication.class);
	}
    
    

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		Logger.info( "Application is starting !!!" );
	    // 配置 Servlet
        //servletContext.addServlet("servletTest",new MyServlet()).addMapping("/myServlet");
		
        // 配置过滤器 + 过滤器路径
        //servletContext.addFilter("timeFilter",new MyFilter()).addMappingForUrlPatterns( EnumSet.of(DispatcherType.REQUEST) , true , "/*" );
        
		// 配置监听器
        //servletContext.addListener(new MyListener());
		
		
	}



	public static void main( String[] args ){
    	SpringApplication.run(SpringbootApplication.class, args);
        Logger.info( "Application is running !!!" );
    }
}
