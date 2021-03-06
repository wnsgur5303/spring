package kr.or.ddit.ioc;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.vo.UserVo;
import kr.or.ddit.test.config.ModelTestConfig;

@ContextConfiguration("classpath:/kr/or/ddit/config/spring/application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class typeConversionTest{
	private static final Logger logger = LoggerFactory.getLogger(typeConversionTest.class);

	@Resource(name = "user")
	private UserVo user;
	
	@Test
	public void userTest() {
		logger.debug("user.getReg_dt() : {}" ,user.getReg_dt());
		logger.debug("user.getHire_dt() : {}" ,user.getHire_dt());
		logger.debug("user.getPrice() : {}" ,user.getPrice());
	
		
	}
	

}
