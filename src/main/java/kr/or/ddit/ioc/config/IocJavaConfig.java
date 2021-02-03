package kr.or.ddit.ioc.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import kr.or.ddit.ioc.DbConfig;
import kr.or.ddit.user.repository.UserDaoI;
import kr.or.ddit.user.repository.UserDaoImpl;
import kr.or.ddit.user.service.UserServiceImpl;


//스프링 프레임워크에게 해당 자바 파일이
//스프링 설정 파일임을 알려준다
@ImportResource("classpath:/kr/or/ddit/config/spring/datasource-context.xml")
@PropertySource(value = {"classpath:/kr/or/ddit/config/db/dbinfo.properties"})
@Configuration
public class IocJavaConfig {
	
	//필드에서 먼저 주입을 받고 사용해야함 프로퍼티스는
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	
	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.password}")
	private String password;

	//메소드 : 스프링 빈으로 만들 객체를 반환하는 메소드를 생성
	//	메소드에 @Bean 어노테이션을 적용
	//			@Bean 어노테이션에 별다른 옵션을 적용하지 않으면 생성된 스프링 빈의 이름은
	//			메소드 이름으로 적용된다. (@Bean 어노테이션 name 속성을 통해 스프링 빈 이름 설정 가능)
				
	//bean id="userDao" class="kr.or.ddit.user.repository.UserDaoImpl"/>
	
	@Bean
	public UserDaoI userDao() {
		return new UserDaoImpl();
		
	}
//	<bean id="userService"
//			class="kr.or.ddit.user.service.UserServiceImpl">
//		</bean>
	
	@Bean
	public UserServiceImpl userServiceImpl() {
		UserServiceImpl userService = new UserServiceImpl();
		userService.setUserDao(userDao());
		
		return userService;
	}
	
	
	@Bean
	public UserServiceImpl userService() {
		return new UserServiceImpl(userDao());
	}
	
	@Bean
	public UserServiceImpl userServiceCons() {
		return new UserServiceImpl(userDao());
	}

	@Scope("prototype")
	@Bean
	public UserServiceImpl userServicePrototype() {
		UserServiceImpl userService = new UserServiceImpl();
		userService.setUserDao(userDao());
		
		return userService;
	}
	
	@Bean
	public CollectionBean collectionBean() {
	CollectionBean collectionBean = new CollectionBean();
	List<String> list = new ArrayList<String>();
	list.add("brown");
	list.add("sally");
	list.add("cony");
	
	collectionBean.setList(list);
	return collectionBean;
	}
	
	@Bean
	public DbConfig dbConfig() {
		DbConfig dbConfig = new DbConfig();
		dbConfig.setUrl(url);
		dbConfig.setDriverClassName(driverClassName);
		dbConfig.setUsername(username);
		dbConfig.setPassword(password);
		
		return dbConfig;
	}
}
