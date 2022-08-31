package com.board.study.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration	//자바 기반의 설정파일로 인식하기 위한 어노테이션
@PropertySource("classpath:/application.properties")	//클래스에서 참조할 propertise 파일의 위치를 지정
public class DatabaseConfig {
	
	@Autowired	//빈으로 등록된 인스턴스를 클래스에 주입하는데 사용, @Resource, @Injection 등이 존재
	private ApplicationContext context;
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
	}
	
	@Bean(name = "sqlSessionFactory") //name속성 지정 가능 단 name속성을 지정하면 메소드 이름으로 빈을 가져올 수 없음
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
//		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/*Mapper.xml"));
		return factoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
}
