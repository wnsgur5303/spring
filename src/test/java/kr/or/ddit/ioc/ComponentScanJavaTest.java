package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.config.ComponentScanJavaConfig;
import kr.or.ddit.ioc.vo.UserVo;
import kr.or.ddit.user.repository.UserDaoI;
import kr.or.ddit.user.service.UserServiceI;


@ContextConfiguration(classes = {ComponentScanJavaConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ComponentScanJavaTest {
	
	@Resource(name="userDao")
	private UserDaoI userDao;
	
	@Resource(name="userService")
	private UserServiceI userService;


	@Test
	public void userDaoImplSpringBeanTest() {
		assertNotNull(userDao);
		
		UserVo userVo = userDao.selectUser("brown");
		assertEquals("브라운", userVo.getUsernm());
	}
	

	@Test
	public void userServiceImplSpringBeanTest() {
		assertNotNull(userService);
		
		UserVo userVo = userService.selectUser("brown");
		assertEquals("브라운", userVo.getUsernm());
	}
}






