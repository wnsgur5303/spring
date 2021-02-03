package kr.or.ddit.mvc.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.ioc.vo.UserVo;
import kr.or.ddit.user.service.UserServiceI;
import kr.or.ddit.user.service.UserServiceImpl;

@RequestMapping("file")
@Controller
public class FileDownloadController {
	
	@Resource(name = "userService")
	private UserServiceImpl userService;
	
	@RequestMapping("/fileDownloadView")
	public String fileDownloadView(Model model, String userid) {
		//1. 다운로드 파일의 경로 => realFilename
		//2. 다운로드시 보여줄 파일명 ==> filename
		//1,2을 model에 넣어준다
		//userid 파라미터를 보낸다고 가정
		//파라미터를 이용하여 해당 사용자의 사진정보 (realFilename,filename)을 조회하여 처리
		
		UserVo userVo = userService.selectUser(userid);
		model.addAttribute("realFilename", userVo.getRealfilename());
		model.addAttribute("Filename", userVo.getFilename());
		
		return "fd";
		
	}
	
	@RequestMapping("fileDownload")
	public void fileDownload(String userid, HttpServletResponse response){
		
		UserVo userVo = userService.selectUser(userid);
		
		String realFilename = userVo.getFilename();
		String filename = userVo.getRealfilename();
		
		response.setHeader("Content-Disposition", "attachment; filename=" + filename);

		ServletOutputStream sos;

		try {
			sos = response.getOutputStream();
			FileInputStream fis = new FileInputStream(new File(realFilename));
			byte[] buf = new byte[512];
			
			while (fis.read(buf) != -1) {
				sos.write(buf);
			}

			fis.close();
			sos.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
