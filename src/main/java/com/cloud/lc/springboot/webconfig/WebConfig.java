package com.cloud.lc.springboot.webconfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.cloud.lc.springboot.filter.MyFilter;
import com.cloud.lc.springboot.interceptor.MyInterceptor;
import com.cloud.lc.springboot.servlet.MyServlet;
import com.cloud.lc.springboot.websocket.WebSocketServer;

@Configuration
@EnableWebSocket
public class WebConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
	
	@Autowired
    private MyInterceptor myInterceptor;
	
	
	
	/**
	 * 数据json转换
	 * @return
	 */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        
        HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;
        
        return new HttpMessageConverters(converter);
    }
	
	
	/**
	 * 自定义servlet
	 * @return
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
	    return new ServletRegistrationBean(new MyServlet(),"/myServlet");
	}
	
	/**
	 * 自定义filter
	 * @return
	 */
	@Bean
	public FilterRegistrationBean myFilter() {
	    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	    
	    MyFilter timeFilter = new MyFilter();
	    registrationBean.setFilter(timeFilter);
	    
	    //过滤器拦截路径配置
	    List<String> urls = new ArrayList();
	    urls.add("/*");
	    
	    registrationBean.setUrlPatterns(urls);
	    
	    return registrationBean;
	}

	/**
	 * 添加自定义拦截器
	 * @return
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//添加拦截器，并制定拦截器路径
		registry.addInterceptor(myInterceptor).addPathPatterns("/*");
	}

	/**
	 * 添加接口访问限制
	 * 粗粒度访问限制
	 * @return
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//registry.addMapping("/json/**").allowedOrigins("http://192.168.5.156:8880");
		registry.addMapping("/json/**").allowedOrigins("http://localhost:8880");
		
	}
	

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketServer(), "/webSocketServer/*");
	}
	
	@Bean
	public WebSocketHandler webSocketServer() {
		return new WebSocketServer();
	}
	
	
/*	@Bean
    @ConditionalOnMissingBean // 当容器里没有指定的 Bean 的情况下创建该对象
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);

        // 设置mybatis的主配置文件
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));

        // 设置mapper映射文件
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] mapperXml;
        try {
            mapperXml = resolver.getResources("classpath:mybatis/mapper/*.xml");
            sqlSessionFactoryBean.setMapperLocations(mapperXml);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 设置别名包
        //sqlSessionFactoryBean.setTypeAliasesPackage("com.cloud.lc.springboot.dao.entity");

        return sqlSessionFactoryBean;
    }

    @Bean
    @ConditionalOnBean(SqlSessionFactoryBean.class) // 当 SqlSessionFactoryBean 实例存在时创建对象
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.cloud.lc.springboot.dao.mapper");
        return mapperScannerConfigurer;
    }*/
	
}
