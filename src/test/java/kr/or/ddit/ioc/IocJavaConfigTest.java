package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.config.IocJavaConfig;
import kr.or.ddit.user.service.UserServiceI;

@ContextConfiguration(classes = {IocJavaConfig.class} )
@RunWith(SpringJUnit4ClassRunner.class)
public class IocJavaConfigTest {
	
	@Resource(name="userService")
	private UserServiceI userService;
	
	@Resource(name="userService")
	private UserServiceI userService2;
	
	@Resource(name="userServiceCons")
	private UserServiceI userServiceCons;
	
	@Resource(name="userServicePrototype")
	private UserServiceI userServicePrototype;
	
	@Resource(name="userServicePrototype")
	private UserServiceI userServicePrototype2;
	
	@Resource(name="dbConfig")
	private DbConfig dbConfig;

	@Test
	public void userServiceConsTest() {
		/***Given***/
		
		/***When***/

		/***Then***/
		assertNotNull(userServiceCons);
	}

	@Test
	public void beanScopeTest() {
		
		//������ ������ signleton �������� ���� �ΰ��� ��ü�� �� Ŭ������ ���� �������Ƿ� ���� �ؾ���
		//������ �������� singleon ������ bean ������Ʈ�� �������� �ϳ��� ��ü�� �����ȴ�
		assertNotEquals(userService, userServiceCons);		
	}
	
	@Test
	public void beanScopeTest2() {
		
		//������ ������ ���� ���Թ޾����Ƿ� userService, userService2�� ���� ��ü��
		assertEquals(userService, userService2);
	}
	
	@Test
	public void beanScopePrototypeTest() {
		//������ userServicePrototype ���� ���� (scope : prototype)
		assertNotEquals(userServicePrototype, userServicePrototype2);
	}
	
	@Test
	public void propertyPlaceholderTest() {
		assertNotNull(dbConfig);
		assertEquals("wnsgur5303", dbConfig.getUsername());
		assertEquals("java", dbConfig.getPassword());
		assertEquals("jdbc:oracle:thin:@localhost:1521:xe", dbConfig.getUrl());
		assertEquals("oracle.jdbc.driver.OracleDriver", dbConfig.getDriverClassName());
	}
}








