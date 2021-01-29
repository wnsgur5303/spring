package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.security.Provider.Service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.vo.UserVo;
import kr.or.ddit.user.repository.UserDao;
import kr.or.ddit.user.service.UserServiceI;

@ContextConfiguration("classpath:kr/or/ddit/ioc/ioc.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ComponentScanTest {

	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="userService")
	private UserServiceI userService;
	
	//@Repository 어노테이션을 적용한 userDaoImpl 스프링 빈이 정상적으로 컨테이너에 등록 되었는지 확인
	@Test
	public void userDaoImplSpringBeanTest() {
		assertNotNull(userDao);
		
		UserVo userVo = userDao.getUser("brown");
		assertEquals("브라운", userVo.getUsernm());
	}

	
	@Test
	public void userServiceImplSpringBeanTest() {
		assertNotNull(userService);
		
		UserVo userVo = userService.getUser("brown");
		assertEquals("브라운", userVo.getUsernm());
	}
	
}
