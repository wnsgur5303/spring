package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.test.config.ModelTestConfig;


public class collectionBeanTest extends ModelTestConfig{
	
	//collectionBean 스프링번이 정상적으로 생성 되었는지.
	@Resource(name="collectionBean")
	private CollectionBean collectionBean;
	
	
	@Test
	public void collectionBeanTest() {
		
		assertNotNull(collectionBean);
		assertNotNull(collectionBean.getList());
		
		assertEquals(3, collectionBean.getList().size());
		assertEquals("sally",collectionBean.getList().get(1));
		assertEquals("브라운",collectionBean.getMap().get("usernm"));
	}

}
