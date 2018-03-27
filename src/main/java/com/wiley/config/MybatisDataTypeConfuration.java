package com.wiley.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.wiley.config.enums.DataSourceType;

@Configuration
public class MybatisDataTypeConfuration {



	   @Value("${datasource.readSize}")  
	    private String dataSourceSize;  
	   @Value("${mybatis.mapperLocations}")
	   private String mapperLocations;
	    @Resource(name = "writeDataSource")  
	    private DataSource dataSource;  
	    @Resource(name = "readDataSources")  
	    private List<DataSource> readDataSources; 
	    
	    
	      
	    @Bean  
	     @ConditionalOnMissingBean  
	    public SqlSessionFactory sqlSessionFactory() throws Exception {  
	        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();  
	        sqlSessionFactoryBean.setDataSource(roundRobinDataSouceProxy());  
	      PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
	        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);  
	        return sqlSessionFactoryBean.getObject();  
	    
	    } 
	    
	    /** 
	     * 有多少个数据源就要配置多少个bean 
	     * @return 
	     */  
	    @Bean  
	    public AbstractRoutingDataSource roundRobinDataSouceProxy() {  
	        int size = Integer.parseInt(dataSourceSize);  
	        MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource(size);  
	        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();  
	        // 写  
	        targetDataSources.put(DataSourceType.write.getType(),dataSource);  
	        //多个读数据库时  
	        for (int i = 0; i < size; i++) {  
	            targetDataSources.put(i, readDataSources.get(i));  
	        }  
	        proxy.setDefaultTargetDataSource(dataSource);  
	        proxy.setTargetDataSources(targetDataSources);  
	        return proxy;  
	    }  
	
}
