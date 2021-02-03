package kr.or.ddit.upload;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import kr.or.ddit.testconfig.WebTestConfig;

public class FileUpload extends WebTestConfig{

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void allUserTest() throws Exception {
		mockMvc.perform(get("/user/allUser"))
				.andExpect(view().name("user/allUser"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("userList"))
				.andDo(print());
	}

}
