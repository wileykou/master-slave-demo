package com.wiley.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wiley.config.enums.DataSourceType;

public class DataSourceContextHandler {

	private Logger  log=LoggerFactory.getLogger(this.getClass());
    private static final  ThreadLocal<String>  local=new ThreadLocal();

    public static ThreadLocal<String> getLocal() {  
        return local;  
    } 
    public static void read(){
    	local.set(DataSourceType.read.getType());
    }
    
    public static void write(){
    	local.set(DataSourceType.write.getType());
    }
    
    public static String getJdbcType(){
    	return local.get();
    }
}
