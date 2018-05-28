package com.cloud.lc.springboot.aop;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.cloud.lc.springboot.logger.Logger;
import com.cloud.lc.springboot.util.ReflectHelper;


@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MyAspect {
	
	
	

	 @Pointcut("execution(* com.cloud.lc.springboot.dao.mapper..insert*(..))")  
	 public void insert() { }  

	 @Pointcut("execution(* com.cloud.lc.springboot.dao.mapper.*.updateById(..))")  
	 public void update() { }  


	//声明一个切入点第一个*号返回类型,test后面..代表包及下面子包【不写就只代表当前包】,*所有方法,(..)方法的参数  
	@Before("execution(* com.cloud.lc.springboot.controller..*(..))")
	public void  method() throws Throwable{
		Logger.info("=====Aspect处理=======@before");
	}
	
	@Around("execution(* com.cloud.lc.springboot.controller..*(..))")
	public Object  method2(ProceedingJoinPoint pjp) throws Throwable{
		Logger.info("=====Aspect处理=======@around");
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            Logger.info("参数为:" + arg);
        }
        long start = System.currentTimeMillis();
        Object object = pjp.proceed();
        Logger.info("=====Aspect处理=======@around 耗时:" + (System.currentTimeMillis() - start));
        return object;
	}
	
	
	@After("execution(* com.cloud.lc.springboot.controller..*(..))")
	public void  method1() throws Throwable{
		Logger.info("=====Aspect处理=======@after");
	}
	
	
	@AfterReturning(value="execution(* com.cloud.lc.springboot.controller..*(..))",returning="result")  
	public void  method3(String result) throws Throwable{
		Logger.info("=====Aspect处理=======@AfterReturning");  
		Logger.info("returning : "+ result);  
	}
	
	//声明一个切入点第一个*号返回类型,test后面..代表包及下面子包【不写就只代表当前包】,*所有方法,(..)方法的参数  
	@Before("execution(* com.cloud.lc.springboot.service.impl.*.insert*(..))")
	public void  insertAop(JoinPoint joinPoint) throws Throwable{
		Object[] originalArgs = joinPoint.getArgs();
		//如果参数列表不是一个，则不符合自动反射规则
        if (originalArgs.length != 1) { 
            return;
        }
        Object arg = originalArgs[0];
        Object nullValue = null;
        ReflectHelper.setFieldVal(arg, "id", nullValue);
        ReflectHelper.setFieldVal(arg, "createTime", new Date());
        ReflectHelper.setFieldVal(arg, "updateTime", new Date());
	}
	
	
	//声明一个切入点第一个*号返回类型,test后面..代表包及下面子包【不写就只代表当前包】,*所有方法,(..)方法的参数  
	@Before("update()")
	public void  updateAop(JoinPoint joinPoint) throws Throwable{
		Object[] originalArgs = joinPoint.getArgs();
		//如果参数列表不是一个，则不符合自动反射规则
        if (originalArgs.length != 1) { 
            return;
        }
        Object arg = originalArgs[0];
        ReflectHelper.setFieldVal(arg, "updateTime", new Date());
	}
	
	
}
