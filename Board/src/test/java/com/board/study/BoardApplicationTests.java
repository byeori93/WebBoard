package com.board.study;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class BoardApplicationTests {

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	@Test
	void contextLoads() {
	}
	
	@Test	//bean name 지정해놔서 메소드 이름으로 부르는거 불가능
	public void testByApplicatoinContext() {
		try {
			System.out.println("========================");
			System.out.println(context.getBean("sqlSessionFactory"));
			System.out.println("========================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testByName() {
		try {
			System.out.println("========================");
			System.out.println(context.getBean("abc"));
			System.out.println("========================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBySqlSessionFactory() {
		try {
			System.out.println("========================");
			System.out.println(sessionFactory.toString());
			System.out.println("========================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
