package com.co.kr.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@EnableTransactionManagement
public class DbConfig { //DataSource와 DataSourceTransactionManager를 생성하고 관리 , 웹애플리케이션에서 DB에 트랜잭션 적용

	@Bean(destroyMethod="close")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/jsp?autoReconnect=true&serverTimezone=UTC&characterEncoding=UTF-8");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setMaxIdle(5);
		dataSource.setMinIdle(0);
		dataSource.setDefaultAutoCommit(false);
		return dataSource;
	}

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
        //dataSource() 사용하여 DataSourceTransactionManager 에 DataSource 등록
    }
    
}
