package kr.or.ddit.test.login;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/*
 * 
 * java - gui swing, awt, java fx, swt
 * */

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.testconfig.WebTestConfig;



public class LoginControllerTest extends WebTestConfig{
private static final Logger logger = LoggerFactory.getLogger(LoginControllerTest.class);

//	@Test
//	public void viewTest() throws Exception{
//		//localhost/login/view
//		
//		mockMvc.perform(get("/login/view")).andExpect(status().isOk())
//		.andExpect(view().name("login"));
//		
//		
//	}

	@Test
	public void processTest() throws Exception{
		mockMvc.perform(post("/login/process").param("userid","brown").param("pass","brownPass")
				.param("price", "1000")).andExpect(view().name("main"));
	}
	
	@Test
	public void processSuccessTest() throws Exception{
		mockMvc.perform(post("/login/process").param("userid","brown").param("pass","brownPass")
				.param("price", "1000")).andExpect(view().name("main")).andDo(print());
	}
	
	@Test
	public void processFailTest() throws Exception{
		mockMvc.perform(post("/login/process").param("userid","brown").param("pass","failPass")
				.param("price", "1000")).andExpect(view().name("redirect:/login/view")).andDo(print());
	}

}
