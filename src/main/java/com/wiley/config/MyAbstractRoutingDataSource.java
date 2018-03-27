package com.wiley.config;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.wiley.config.enums.DataSourceType;

public class MyAbstractRoutingDataSource extends  AbstractRoutingDataSource{

	private AtomicInteger  count=new AtomicInteger(0);
	
	private int dataSourceNumber;
	
	public  MyAbstractRoutingDataSource(int dataSourceNumber) {
		this.dataSourceNumber=dataSourceNumber;
	}
	
	
	@Override
	protected Object determineCurrentLookupKey() {
		  String type=DataSourceContextHandler.getJdbcType();
			if(type.equals(DataSourceType.write.getType())){
				return DataSourceType.write.getType();
			}
		int number=(count.getAndAdd(1))%dataSourceNumber;
		return new Integer(number);
	}

}
