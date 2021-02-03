package kr.or.ddit.user.repository;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.ioc.vo.UserVo;
import kr.or.ddit.test.config.ModelTestConfig;

//이클립스에서 자체적으로 내장되있자 메인이. /메이븐

//스프링 환경에서 junit 코드를 실행--> junit 코드도 스프링 번으로 등록

public class UserDaoTest extends ModelTestConfig{
	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	
	@Resource(name="userDao")
	private UserDaoI userDao;
	
	@Test
	public void getUserTest() {
		/***Given***/
		String userid = "brown";

		/***When***/
		UserVo userVo = userDao.selectUser(userid);
		
		/***Then***/
		assertEquals("브라운", userVo.getUsernm());
	}
	
	@Test
	public void getAllUserTest() {
		/***Given***/
		String userid = "brown";

		/***When***/
		List<UserVo> userList = userDao.selectAllUser();
		
		/***Then***/
		assertEquals(17, userList.size());
	}
	
	
	@Test
	public void getPagingUserTest() {
		/***Given***/
		PageVo vo = new PageVo();
		vo.setPage(1);
		vo.setPageSize(5);

		/***When***/
		List<UserVo> userList = userDao.selectPagingUser(vo);
		
		/***Then***/
		assertEquals(5, userList.size());
	}
	
	
	@Test
	public void selectAllUserCntTest() {
		/***Given***/

		/***When***/
		int cnt = userDao.selectAllUserCnt();
		
		/***Then***/
		assertEquals(17, cnt);
	}
	
	
	@Test
	public void getregistUserTest() {
		/***Given***/
		Date date = new Date();
		UserVo vo = new UserVo();
		vo.setUserid("testid");
		vo.setUsernm("테스트");
		vo.setPass("123");
		vo.setReg_dt(date);
		vo.setAlias("실험쥐");
		vo.setAddr1("ok");
		vo.setAddr2("ok");
		vo.setZipcode("ok");
		vo.setFilename("ok");
		vo.setRealfilename("ok");

		/***When***/
		int cnt = userDao.registUser(vo);
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
	@Test
	public void modifyUserTest() {
		/***Given***/
		Date date = new Date();
		UserVo vo = new UserVo();
		vo.setUserid("testid");
		vo.setUsernm("테스트2");
		vo.setPass("1232");
		vo.setReg_dt(date);
		vo.setAlias("실험쥐2");
		vo.setAddr1("ok2");
		vo.setAddr2("ok2");
		vo.setZipcode("ok2");
		vo.setFilename("ok2");
		vo.setRealfilename("ok2");

		/***When***/
		int cnt = userDao.modifyUser(vo);
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
	@Test
	public void deleteUserTest() {
		/***Given***/
		

		/***When***/
		int cnt = userDao.deleteUser("testid");
		
		/***Then***/
		assertEquals(1, cnt);
	}

}
