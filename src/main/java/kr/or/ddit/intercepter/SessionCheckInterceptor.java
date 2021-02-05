package kr.or.ddit.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionCheckInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//정상접속인지 확인하는 로직:session에 S_USER 속성이 있는지 검사
		if(request.getSession().getAttribute("S_USER") == null){
			
			response.sendRedirect(request.getContextPath()+"/login/view");
			return false;
			
		}
		
		
		return true;
	}

}
