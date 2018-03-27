package com.wiley.common.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.wiley.config.DataSourceContextHandler;

@Aspect
@Component
public class DataSourceAop {

	private static Logger log=LoggerFactory.getLogger(DataSourceAop.class);
	   @Before("execution(* com.wiley.modules.database.dao..*.find*(..))  || execution(* com.wiley.modules.database.dao..*.get*(..))")
	    public void setReadDataSourceType() {
		   DataSourceContextHandler.read();
	        log.info("dataSource切换到：Read");
	    }

	    @Before("execution(* com.wiley.modules.database.dao..*.insert*(..)) or execution(* com.wiley.modules.database.dao..*.update*(..))")
	    public void setWriteDataSourceType() {
	        DataSourceContextHandler.write();
	        log.info("dataSource切换到：write");
	    }
}
