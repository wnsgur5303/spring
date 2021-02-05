package kr.or.ddit.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.qos.logback.core.joran.spi.DefaultClass;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.ioc.vo.UserVo;
import kr.or.ddit.user.service.UserServiceImpl;
import kr.or.ddit.validator.UserVoValidator;

@RequestMapping("user")
@Controller
public class User {
	private static final Logger logger = LoggerFactory.getLogger(User.class);

	@Resource(name = "userService")
	private UserServiceImpl userService;

	@RequestMapping(path = "/allUser", method = { RequestMethod.GET })
	public String view(Model model) {

		model.addAttribute("userList", userService.selectAllUser());

		return "user/allUser";
	}
	
	
	@RequestMapping(path = "/allUserTiles", method = { RequestMethod.GET })
	public String viewTailes(Model model) {

		model.addAttribute("userList", userService.selectAllUser());

		return "tiles.user.allUser";
	}
	

	@RequestMapping("/pagingUser")
	public String pagingUser(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize,
			Model model) {
		logger.debug("page : {},pageSize : {},price : {}", page, pageSize);

		PageVo pageVo = new PageVo(page, pageSize);

		int cnt = userService.selectAllUserCnt();
		int allpage = (int) Math.ceil((double) cnt / pageSize);

		model.addAttribute("page", page);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageVo", pageVo);
		model.addAttribute("allpage", allpage);
		model.addAttribute("userList", userService.selectPagingUser(pageVo));
		//
		return "user/pagingUser";
	}
	
	
	
	
	
	
	
	@RequestMapping("/pagingUserAjax")
	public String pagingUserAjax(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize,
			Model model) {

		PageVo pageVo = new PageVo(page, pageSize);

		int cnt = userService.selectAllUserCnt();
		int allpage = (int) Math.ceil((double) cnt / pageSize);

		model.addAttribute("page", page);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageVo", pageVo);
		model.addAttribute("allpage", allpage);
		model.addAttribute(userService.selectPagingUser(pageVo));
		//
		return "jsonView";
	}
	
	
	
	@RequestMapping("pagingUserAjaxHtml")
	public String pagingUserAjaxHtml(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize,
			Model model) {

		PageVo pageVo = new PageVo(page, pageSize);

		int cnt = userService.selectAllUserCnt();
		int allpage = (int) Math.ceil((double) cnt / pageSize);

		model.addAttribute("page", page);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageVo", pageVo);
		model.addAttribute("allpage", allpage);
		model.addAttribute("userList",userService.selectPagingUser(pageVo));
		//
		return "user/pagingUserAjaxHtml";
	}
	
	/*
	 pagingUserAjaxHtml ==> /WEB-INF/views/user/pagingUserAjaxHtml.jsp
	*/
	
	
	
//여기
	@RequestMapping(path = "/user", method = { RequestMethod.GET })
	public String user(String userid, Model model) {

		model.addAttribute("user", userService.selectUser(userid));

		return "tiles.user.user";
	}
//	
//	@RequestMapping(path = "/user", method = { RequestMethod.GET })
//	public String user(String userid, Model model) {
//
//		model.addAttribute("user", userService.selectUser(userid));
//
//		return "user/user";
//	}

	@RequestMapping(path = "/userModify", method = { RequestMethod.GET })
	public String getuserModify(String userid, Model model) {

		model.addAttribute("user", userService.selectUser(userid));

		return "user/userModify";
	}

	@RequestMapping(path = "/userModify", method = { RequestMethod.POST })
	public String postuserModify(@Valid UserVo userVo, BindingResult result, Model model, MultipartFile profile) {

		if (profile.getSize() > 0) {

			try {
				userVo.setFilename(profile.getOriginalFilename());

				userVo.setRealfilename("d:\\upload\\" +UUID.randomUUID().toString() + profile.getOriginalFilename());

				profile.transferTo(new File(userVo.getRealfilename()));

			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} else {
			userVo.setFilename(userVo.getFilename());
			userVo.setRealfilename(userVo.getRealfilename());
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		Date reg_dt = new Date();

		try {
			reg_dt = sdf.parse(userVo.getStrdate());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		userVo.setReg_dt(reg_dt);

		model.addAttribute("user", userService.modifyUser(userVo));

		model.addAttribute("userid", userVo.getUserid());
		return "redirect:/user/user";
	}

	@RequestMapping(path = "/userDelete", method = { RequestMethod.POST })
	public String postuserModify(Model model, String userid) {

		userService.deleteUser(userid);

		return "redirect:/user/pagingUser";
	}

	@RequestMapping(path = "/registUser", method = { RequestMethod.GET })
	public String getRegistUser(Model model, MultipartFile profile, RedirectAttributes ra, UserVo userVo) {

		ra.addAttribute("profile", profile);
		ra.addAttribute("userid", userVo.getUserid());
		ra.addAttribute("usernm", userVo.getUsernm());
		ra.addAttribute("pass", userVo.getPass());
		ra.addAttribute("alias", userVo.getAlias());
		ra.addAttribute("addr1", userVo.getAddr1());
		ra.addAttribute("addr2", userVo.getAddr2());
		ra.addAttribute("zipcode", userVo.getZipcode());

		return "tiles.user.registUser";
	}

	@RequestMapping(path = "/registUser", method = { RequestMethod.POST })
	public String getRegistUser(@Valid UserVo userVo, BindingResult result, Model model, MultipartFile profile) {

		// ----
//		new UserVoValidator().validate(userVo, result);
		// 이 코드에 의해서 델리게이트 메소드가 실행이 된다.
		// 이제 해당 자바로가서 검증을 작성
		// 2021 1교시

		if (result.hasErrors()) {

			logger.debug("result has error");
			return "user/registUser";

		}

		// userid 길이 제한?
//		if(userVo.getUserid().length() >0) {
//			
//		}
		// ----

		if (profile.getSize() > 0) {

			try {
				userVo.setFilename(profile.getOriginalFilename());

				userVo.setRealfilename("d:\\upload\\" +UUID.randomUUID().toString() + profile.getOriginalFilename());

				profile.transferTo(new File(userVo.getRealfilename()));

			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} else {
			userVo.setFilename("");
			userVo.setRealfilename("");
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		Date reg_dt = new Date();

		try {
			reg_dt = sdf.parse(userVo.getStrdate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		userVo.setReg_dt(reg_dt);

		int cnt = userService.registUser(userVo);

		if (cnt == 1) {
			return "redirect:/user/allUser";
		} else {

			model.addAttribute("profile", profile);
			model.addAttribute("userid", userVo.getUserid());
			model.addAttribute("usernm", userVo.getUsernm());
			model.addAttribute("pass", userVo.getPass());
			model.addAttribute("alias", userVo.getAlias());
			model.addAttribute("addr1", userVo.getAddr1());
			model.addAttribute("addr2", userVo.getAddr2());
			model.addAttribute("zipcode", userVo.getZipcode());

			return "redirect:/user/registUser";
		}
	}
	
	@RequestMapping("excelDownload")
	public String excelDownload(Model model) {
		List<String> header = new ArrayList<String>();
		header.add("사용자 아이디");
		header.add("사용자 이름");
		header.add("사용자 별명");
		
		model.addAttribute("header",header);
		model.addAttribute("data",userService.selectAllUser());
		
		
		return "userExcelDownloadView";
	}
	
	//**
	@RequestMapping("/pagingUserTiles")
	public String pagingUserTiles(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int pageSize,
			Model model) {
		logger.debug("page : {},pageSize : {},price : {}", page, pageSize);

		PageVo pageVo = new PageVo(page, pageSize);

		int cnt = userService.selectAllUserCnt();
		int allpage = (int) Math.ceil((double) cnt / pageSize);

		model.addAttribute("page", page);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageVo", pageVo);
		model.addAttribute("allpage", allpage);
		model.addAttribute("userList", userService.selectPagingUser(pageVo));
		//
		return "tiles.user.pagingUser";
	}
	
	//0205 - 사용자 리스트가 없는 상태의 화면만 응답으로 생성
	@RequestMapping("pagingUserAjaxView")
	public String pagingUserAjaxView() {
		
		return "tiles.user.pagingUserAjax";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//localhost/user/profile
	@RequestMapping("profile")
	public void profile(HttpServletResponse resp,String userid,HttpServletRequest req) {
		resp.setContentType("image");
		
		//userid 파라미터를 이용하여
		//userService 객체를 통해 사용자의 사진 파일 이름을 획득
		//파일 입출력을 통해 사진을 읽어들여 resp객체의 outputStream으로 응답 생성

		UserVo userVo = userService.selectUser(userid);
		
		String path = "";
		logger.debug("dddd {}",userVo.getRealfilename());
		if(userVo.getRealfilename() == null) {
		path = req.getServletContext().getRealPath("/image/unknown.png");
		}
		else 
		{
		path = userVo.getRealfilename();
//		path = "d:\\upload\\"+ userVo.getRealfilename();
		}
		logger.debug("path : {}",path);
		try {
			FileInputStream fis = new FileInputStream(path);
			ServletOutputStream sos = resp.getOutputStream();
			byte[] buff = new byte[512];
			while(fis.read(buff)!=-1) {
				sos.write(buff);
			}
			fis.close();
			sos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//localhost/user/profile
		@RequestMapping("profileDownload")
		public void profiledownload(HttpServletResponse resp,String userid,HttpServletRequest req) {
			//userid 파라미터를 이용하여
			//userService 객체를 통해 사용자의 사진 파일 이름을 획득
			//파일 입출력을 통해 사진을 읽어들여 resp객체의 outputStream으로 응답 생성

			UserVo userVo = userService.selectUser(userid);
			
			resp.setHeader("Content-Disposition", "attachment; filename=" + userVo.getFilename());
			
			String path = "";
			if(userVo.getRealfilename() == null) {
			path = req.getServletContext().getRealPath("/image/unknown.png");
			}
			else 
			{
			path = userVo.getRealfilename();
//			path = "d:\\upload\\"+ userVo.getRealfilename();
			}
			logger.debug("path : {}",path);
			try {
				FileInputStream fis = new FileInputStream(path);
				ServletOutputStream sos = resp.getOutputStream();
				byte[] buff = new byte[512];
				while(fis.read(buff)!=-1) {
					sos.write(buff);
				}
				fis.close();
				sos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
}
