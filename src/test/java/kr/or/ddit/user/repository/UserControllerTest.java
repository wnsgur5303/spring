package kr.or.ddit.user.repository;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Date;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.testconfig.WebTestConfig;

public class UserControllerTest extends WebTestConfig{

	@Test
	public void allUserTest() throws Exception {
		mockMvc.perform(get("/user/allUser"))
				.andExpect(view().name("user/allUser"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("userList"))
				.andDo(print());
	}
	
	@Test
	public void pagingUserTest() throws Exception{
		mockMvc.perform(get("/user/pagingUser").param("page","2"))
		.andExpect(view().name("user/pagingUser"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("userList"))
		.andExpect(model().attributeExists("page"))
		.andExpect(model().attributeExists("allpage"))
		.andDo(print());
		
	}
	
	@Test
	public void userTest() throws Exception{
		mockMvc.perform(get("/user/user").param("userid","brown"))
		.andExpect(view().name("user/user"))
		.andExpect(status().isOk())
		.andDo(print());
		
	}
	
	@Test
	public void DeleteuserTest() throws Exception{
		mockMvc.perform(post("/user/userDelete").param("userid","mmmmmmmmmmmmmm"))
		.andExpect(view().name("redirect:/user/pagingUser"))
		.andExpect(status().isFound())
		.andDo(print());
		
	}
	
	
	
	
	
	
	@Test
	public void RegistuserTest() throws Exception{
		
		ClassPathResource resource = new ClassPathResource("kr/or/ddit/upload/sally.png");
		
		MockMultipartFile profile = 
				new MockMultipartFile("profile", "sally.png","image/png", resource.getInputStream());
		
		
		mockMvc.perform(fileUpload("/user/registUser").file(profile)
				.param("userid","qwert222775")
				.param("usernm","qwert")
				.param("pass","qwert")
				.param("alias","qwert")
				.param("addr1","qwerrt")
				.param("addr2","qwet")
				.param("zipcode","qwet")
				.param("strdate","2020.01.01")
				)
		.andExpect(view().name("redirect:/user/allUser"))
		.andExpect(status().isFound())
		.andDo(print());
	}
	
	@Test
	public void MouserTest() throws Exception{
		
		ClassPathResource resource = new ClassPathResource("kr/or/ddit/upload/sally.png");
		
		MockMultipartFile profile = 
				new MockMultipartFile("profile", "sally.png","image/png", resource.getInputStream());
		
		
		mockMvc.perform(fileUpload("/user/userModify").file(profile)
				.param("userid","qwert222775")
				.param("usernm","qwert888")
				.param("pass","qwert888")
				.param("alias","qwert888")
				.param("addr1","qwerrt88")
				.param("addr2","qwet88")
				.param("zipcode","qwet")
				.param("strdate","2020.01.01")
				)
		.andExpect(model().attributeExists("userid"))
		.andExpect(view().name("redirect:/user/user"))
		.andExpect(status().isFound())
		.andDo(print());
	}

}
