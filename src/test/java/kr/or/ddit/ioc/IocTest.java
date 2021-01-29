package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.service.UserServiceI;


@ContextConfiguration("classpath:kr/or/ddit/ioc/ioc.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class IocTest {
	
	@Resource(name="userServiceCons")
	private UserServiceI userServiceCons;
	
	@Resource(name="userService")
	private UserServiceI userService;
	
	@Resource(name="userService")
	private UserServiceI userService2;
	
	@Resource(name="userServicePrototype")
	private UserServiceI userServicePrototype;
	
	@Resource(name="userServicePrototype")
	private UserServiceI userServicePrototype2;
	
	@Resource(name="dbConfig")
	private DbConfig dbConfig;
	
	//userServiceCons 스프링 빈으로 정상적으로 생성 되었는지 테스트
//	@Test
//	public void userServiceConsTest() {
//
//		assertNotNull(userServiceCons);
//	}
	
//	@Test
//	public void beanScopeTest() {
//		/***Given***/
//		
//
//		/***When***/
//
//		/***Then***/
//		//디자인 패턴의 싱글톤 개념으로 보면 두개의 객체는 하나의 클래스로 부터 나왔으므로 동일행함
//		//하지만 스프링의 싱글톤 개념은 엘레멘트를 기준으로 하나의 객체가 생성된다
//		assertEquals(userService, userServiceCons);
//		
//		}
	
	
//	@Test
//	public void beanScopeTest2() {
//
//		//동일한 스프링 번을 주입받았으므로 userService, userService2는 같은 객체다
//		assertEquals(userService, userService2);
//		}
//	
//	
//	@Test
//	public void beanScopePrototypeTest() {
//
//		//동일한 userServicePrototype 번을 주입(scope:prototype) 같지않다는 조건 통과했다.
//		assertNotEquals(userServicePrototype, userServicePrototype2);
//		}
	
	@Test
	public void propertyPlaceholderTest() {
		assertNotNull(dbConfig);
		assertEquals("wnsgur5303", dbConfig.getUsername());
		assertEquals("java", dbConfig.getPassword());
		assertEquals("jdbc:oracle:thin:@localhost:1521:xe",dbConfig.getUrl() );
		assertEquals("oracle.jdbc.driver.OracleDriver", dbConfig.getDriverClassName());
	}
	
}
