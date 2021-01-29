package kr.or.ddit.ioc;

import javax.swing.Spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.ioc.vo.UserVo;
import kr.or.ddit.user.repository.UserDao;
import kr.or.ddit.user.service.UserServiceI;

public class IocMain {
	private static final Logger logger = LoggerFactory.getLogger(IocMain.class);

	
	//IocMain은 빈으로 등록이 안되서 주입을 받지 못함
	
	public static void main(String[] args) {
		// 1. 스프링 설정 파일을 이용하여 스프링 컨테이너를 생성(kr/or/ddit/ioc/ioc.xml)
		// 스프링 컨테이너 타입 : ApplicationContext
		// 2. 스프링 컨테이너에게 만들어진 스프링 빈(객체)을 요청
		//	DL(디펜던시 룩업) : 스프링 컨테이너에게 스프링 번을 요청하는 과정
		//	DL(요청)?
		// 3.스프링 컨테이너에서 관리되고 있는 빈이 잘 만들어 졌는지 확인
//			d:\\upload file:
//					class classPath:
		
		ApplicationContext context 
			= new ClassPathXmlApplicationContext("classpath:kr/or/ddit/ioc/ioc.xml");//여기까지 1번
		
		UserDao userDao = (UserDao)context.getBean("userDao");//get빈은 오브젝트라 형변환 필요
		
		UserVo userVo = userDao.getUser("brown");
		
		logger.debug("userVo : {}",userVo);
		
		
		//스프링 컨테이너로 부터 userService 스프링 번을 DL을 통해 얻어오고
		//getUser 메소드를 call, 반환된 값(UserVo)을 logger를 통해 출력

		UserServiceI userService = (UserServiceI)context.getBean("userService");//get빈은 오브젝트라 형변환 필요
		
		UserVo userVo2 = userService.getUser("brown");
		
		logger.debug("userService : {}",userVo2);
		
		
		for(String beanName : context.getBeanDefinitionNames()) {
			logger.debug("beanName : {}",beanName);
		}

	}

}
