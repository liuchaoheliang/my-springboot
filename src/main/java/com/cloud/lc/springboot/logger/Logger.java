package com.cloud.lc.springboot.logger;

import org.slf4j.LoggerFactory;


public class Logger {
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Logger.class) ;
	
	
	public static void debug(String message ) {
		LOGGER.debug( getInvokerInfo() + " " + message );			
	}

	public static void info(String message ) {
		LOGGER.info( getInvokerInfo() + " " + message );	
	}

	public static void error( String message) {
		LOGGER.error( getInvokerInfo() + " " + message );	
	}

	public static void error( String message, Exception e ) {
		LOGGER.error( getInvokerInfo() + " " + message , e  );	
	}
	
	
	/**
	 * 取得源类的栈调用信息
	 */
	private static String getInvokerInfo() {
		StackTraceElement[] stes = Thread.currentThread().getStackTrace();
		StackTraceElement tgt = stes[3];
		return tgt.getClassName() + ":" + tgt.getMethodName() + "(...):" + tgt.getLineNumber() + " ";
	}
	
}
