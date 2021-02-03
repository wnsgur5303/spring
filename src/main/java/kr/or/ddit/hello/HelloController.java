package kr.or.ddit.hello;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.or.ddit.user.service.UserServiceI;
import kr.or.ddit.user.service.UserServiceImpl;

@SessionAttributes("rangers")
@RequestMapping("hello")
@Controller
public class HelloController {
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@Resource(name="userService")
	private UserServiceImpl userService;
	
	//@RequestMapping("hello")주석 처리 할경우
	//localhost/hello/view ==> localhost/view
	//localhost/hello/view.do
	
	
	@ModelAttribute(name = "rangers")
	public List<String> rangers(){
		logger.debug("helloController.rangers()");
		
		List<String> list = new ArrayList<String>();
		list.add("brown");
		list.add("sally");
		list.add("james");
		list.add("cony");
		list.add("moon");
		
		return list;
	}//아래가 실행되기 전에 이게 실행됨

	//localhost/hello/view => localhost/view
	@RequestMapping("view")
	public String view(Model model, @ModelAttribute(name="rangers") List<String> rangers,HttpServletRequest request) {
		logger.debug("hello {} : ", userService.selectUser("brown"));
		logger.debug("reangers : {}" , rangers);
		
		
//		request.setAttribute("userVo",userService.getUser("brown"));
		model.addAttribute("userVo", userService.selectUser("brown"));
		return "hello";
	}
	
	//hello/path/subpath
	//hello/path/cony
	//hello/path/brown   뭐가 나올지 모른다
	
	@RequestMapping("path/{subpath}")
	public String pathVariable(@PathVariable("subpath") String subpath, Model model,
			/*User-Agent 헤더 값 바인딩*/
			@RequestHeader(name="User-Agent") String userAgent) {
		//User-Agent값 로거로 출력
		logger.debug("User-Agent : {}", userAgent);
		
		model.addAttribute("subpath",subpath);
		
		return "hello";
		
	}
	

}
