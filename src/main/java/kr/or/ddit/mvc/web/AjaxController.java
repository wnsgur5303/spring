package kr.or.ddit.mvc.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@RequestMapping("ajax")
@Controller
public class AjaxController {
	
	//리퀘스트 매핑 메소드 실행전 이것을 실행하고 이것이 반환하는값을 모델에 자동으로 넣어준다
	@ModelAttribute(name="rangers")
	public List<String> rangers(){
		
		List<String> rangers = new ArrayList<String>();
		rangers.add("brown");
		rangers.add("cony");
		rangers.add("james");
		rangers.add("sally");
		rangers.add("moon");
		
		return rangers;
	}
	
	//localhost/ajax/jsonView
	@RequestMapping("jsonView")
	public String jsonView() {
		
		return "jsonView";
	}
	
	@RequestMapping("jsonViewViewObj")
	public View jsonViewViewOjb() {
		
		//그리 올바른 방법은 아니다.(view객체 리턴)
		
		return new MappingJackson2JsonView();
		
	}
	
	
	@RequestMapping("jsonViewMav")
	public ModelAndView jsonViewMav() {
		
		ModelAndView mav = new ModelAndView();
		//내부적으로 모델이랑 합쳐서 하나로만듬  이제 많이쓰는 타입은 아님
		
		mav.setViewName("jsonView");
		
		return mav;
	}
	
	
}
