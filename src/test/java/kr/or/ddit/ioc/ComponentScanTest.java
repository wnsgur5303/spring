package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.vo.UserVo;
import kr.or.ddit.user.repository.UserDaoI;
import kr.or.ddit.user.service.UserServiceI;



@ContextConfiguration(locations=
		{"classpath:/kr/or/ddit/ioc/ioc.xml",
		 "classpath:/kr/or/ddit/config/spring/datasource-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ComponentScanTest {
	
	@Resource(name="userDao")
	private UserDaoI userDao;
	
//	@Resource(name="userServiceImpl")
	@Resource(name="userService")
	private UserServiceI userService;

	@Test
	public void userDaoImplSpringBeanTest() {
		assertNotNull(userDao);
		
		UserVo userVo = userDao.selectUser("brown");
		assertEquals("����", userVo.getUsernm());
	}
	
	@Test
	public void userServiceImplSpringBeanTest() {
		assertNotNull(userService);
		
		UserVo userVo = userService.selectUser("brown");
		assertEquals("브라운", userVo.getUsernm());
	}
}






