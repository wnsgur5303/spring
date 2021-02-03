package kr.or.ddit.test.config;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration({"classpath:kr/or/ddit/config/spring/root-context.xml",
	"classpath:kr/or/ddit/config/spring/datasource-context.xml",
	"classpath:kr/or/ddit/config/spring/application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ModelTestConfig {

	//service, repository ==> root-context.xml
	@Ignore
	@Test
	public void dummy() {
		
	}
	
}
