package com.wiley.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy(false)
public class SpringContextHandler implements ApplicationContextAware ,DisposableBean {

	private static  ApplicationContext  applicationContext=null;
	private static Logger logger = LoggerFactory.getLogger(SpringContextHandler.class);
	
	
	
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static  <T>T  getBean(String name){
		return (T)applicationContext.getBean(name);
	}
	
	public static <T>T  getBean(Class<T> requiredType){
		return applicationContext.getBean(requiredType);
	}
	
	/***
	 *  清除SpringContextHandler中的ApplicationContext为Null.
	 */
	public static void clearHandler(){
		if(logger.isDebugEnabled()){
			logger.debug("清除SpringContextHandler中的ApplicationContext:" + applicationContext);
		}
		logger.info("清除SpringContextHandler中的ApplicationContext:" + applicationContext);
		applicationContext=null;
		
	}
	/**
	 * 实现DisposableBean接口, 在Context关闭时清理静态变量.
	 */
	
	@Override
	public void destroy() throws Exception {
		SpringContextHandler.clearHandler();
		
	}
/***
 * 设置 springContext对象
 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
		
	}

}