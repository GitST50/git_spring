package com.co.kr.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration // 설정클래스임을 나타내고 밑에서 Bean을 선언
@MapperScan( //MyBatis 맵퍼 인터페이스를 스캔
		basePackages = {"com.co.kr.mapper"}, //이부분은 각자 패키지구조로  //basePackages를 사용하여 맵퍼 인터페이스 위치한 패키지를 지정
		annotationClass = org.apache.ibatis.annotations.Mapper.class, //annotationClass를 사용하여 MyBatis 맵퍼 어노테이션 클래스를 지정
		sqlSessionFactoryRef = "sqlSessionFactory" //MyBatis에서 사용할 SqlSessionFactory 빈의 이름을 지정
		)
public class MybatisConfig {//DataSource는 JDBC를 사용하여 DB와 연결을 설정하고 관리하는 객체, SpringBoot에선 해당객체를 Bean으로 등록하여 애플리케이션 전체에서 공유하여 DB를 캐시하고 관리
	
	@Bean(name = "sqlSessionFactory") //Bean 어노테이션으로 SqlSessionFactory 를 생성
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception { //Qualifier 어노테이션으로 DataSource를 주입, DataSource정보를 설정하는 메소드
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();  //Bean으로 등록할 sqlSessionFactory객체 생성
		sqlSessionFactoryBean.setDataSource(dataSource); //sqlSessionFactoryBean에 dataSource정보를 set(설정)함 (Datasource정보는 application.yml에 명시함)
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/*Mapper.xml")); //Mapper.xml파일위치 명시(MyBatis매퍼 파일의 위치를 지정하며 resources/mybatis 안에있는 모든 xml파일을 매퍼파일로 등록함)
		return sqlSessionFactoryBean.getObject();  //getObject() 사용하여 SqlSessionFactory를 반환
	}
	//Spring컨테이너에서 MyBatis의 SqlSession을 생성하고 관리가능해짐

}
