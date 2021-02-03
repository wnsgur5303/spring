package kr.or.ddit.mvc.web;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.ioc.vo.UsersVo;

@RequestMapping("mvc")
@Controller
public class MvcController {
	
	private static final Logger logger = LoggerFactory.getLogger(MvcController.class);

	//jsp 생성
	//테스트 코드
	@RequestMapping("fileupload/view")
	public String fileuploadView() {
		
		return "file/view";
	}
	
	
	@RequestMapping("fileupload/upload")
	public String fileupload(String userid, MultipartFile picture) {
		
		logger.debug("userid : {}",userid);
		System.out.println(picture);
		logger.debug("filesize : {}, name: {}, originalFilename : {}",picture.getSize(), picture.getName(),picture.getOriginalFilename());
		
		
		try {
			picture.transferTo(new File("d:\\upload\\"+picture.getOriginalFilename()));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		return "file/view";
	}
	
	@RequestMapping("multi/view")
	public String multyView() {
		return "multi/view";
	}
	
	
	public String muliParam(String[] userid) {
		
		logger.debug("userid : {}",(Object)userid);
		
		return "multi/view";
	}
	
	@RequestMapping("multi/param")
	public String muliParam(UsersVo usersVo) {
		
		logger.debug("userid : {}",usersVo);
		
		return "multi/view";
	}
}
