package kr.or.ddit.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;

import ch.qos.logback.classic.Logger;

public class PerformanceCheckIntercepter extends HandlerInterceptorAdapter{
	private static final Logger logger = (Logger) LoggerFactory.getLogger(PerformanceCheckIntercepter.class);

	
		//요청을 다음 인터셉터나 컨트롤러에게 위임할지 여부를 반환
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			
			//시작시간을 기록
			long startTime = System.nanoTime();
			
			//요청을 다음 intercepter or controller에게 위임할지 여부를 반환
			request.setAttribute("startTime", startTime);
			return true;
		}
	
		@Override
		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
			//controller 메소드가 실행된 이후 실행되는 영역
			long endTime = System.nanoTime();
			long startTime = (long)request.getAttribute("startTime");
			
			logger.debug("uri : {} , during : {}",request.getRequestURI(),endTime-startTime);
		}
}
