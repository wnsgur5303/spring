package kr.or.ddit.user.repository;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.vo.UserVo;

//이클립스에서 자체적으로 내장되있자 메인이. /메이븐

//스프링 환경에서 junit 코드를 실행--> junit 코드도 스프링 번으로 등록

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/ioc.xml")
public class UserDaoTest {
	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Test
	public void getUserTest() {
		/***Given***/
		String userid = "borwn";

		/***When***/
		UserVo userVo = userDao.getUser(userid);
		logger.debug(userVo.getUsernm());
		/***Then***/
		assertEquals("브라운", userVo.getUsernm());
	}
	
	

}
