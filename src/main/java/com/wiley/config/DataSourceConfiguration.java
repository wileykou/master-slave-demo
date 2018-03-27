package com.wiley.config;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfiguration {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "writeDataSource", destroyMethod = "close", initMethod = "init")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource writeDataSource() {

        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "readDataSource1")
    @ConfigurationProperties(prefix = "spring.slave1")
    public DataSource readDataSourceOne() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "readDataSource2")
    @ConfigurationProperties(prefix = "spring.slave2")
    public DataSource readDataSourceSecond() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "readDataSources")
    public List<DataSource> readDataSources() {
        List<DataSource> lists = new ArrayList<DataSource>();
        lists.add(readDataSourceOne());
        lists.add(readDataSourceSecond());
        return lists;
    }


}
