package kr.or.ddit.hello;

import static org.junit.Assert.*;

import javax.annotation.Resource;

/*
 * 
 * java - gui swing, awt, java fx, swt
 * */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(locations = { "classpath:/kr/or/ddit/config/spring/application-context.xml",
		"classpath:/kr/or/ddit/config/spring/root-context.xml" })
@WebAppConfiguration // 스프링 환경를 web기반의 application Context로 생성하는것
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloControllerTest {

//	@Resource(name = "helloController")
	//스프링 빈중에 대입 가능한 타입의 스프링 빈을 주입한다.
	//만약 동일한 타입의 스프링 빈이 여러개 있을 경우 @Qulifier 어노테이션을 통해
	//특정 스프링 빈의 이름을 저장할 수 있다.
	//=>@Resource 어노테이션 하나를 사용 했을 때와 동일 하다.
	@Autowired 
	private HelloController helloController;
	//타입으로 찾아간다.. 적용이 가능한 대상이 있으면 적용을 하는것이다. 호완될 수 있는 대상이 컨트롤러
		//하나라서 그걸 찾아간것
	
	
	@Test
	public void helloControllerTest() {

		assertNotNull(helloController);
	}

}
