package kr.or.ddit.hello;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.user.service.UserServiceI;
import kr.or.ddit.user.service.UserServiceImpl;

@RequestMapping("hello")
@Controller
public class HelloController {
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@Resource(name="userService")
	private UserServiceImpl userService;
	
	//@RequestMapping("hello")주석 처리 할경우
	//localhost/hello/view ==> localhost/view
	//localhost/hello/view.do

	@RequestMapping("view")
	public String view(Model model) {
		logger.debug("hello {} : ", userService.getUser("brown"));
		
		
//		request.setAttribute("userVo",userService.getUser("brown"));
		model.addAttribute("userVo", userService.getUser("brown"));
		return "hello";
	}

}
