package kr.or.ddit.user.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.qos.logback.core.joran.spi.DefaultClass;
import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.ioc.vo.UserVo;
import kr.or.ddit.user.service.UserServiceImpl;

@RequestMapping("user")
@Controller
public class User {
	private static final Logger logger = LoggerFactory.getLogger(User.class);
	
	@Resource(name = "userService")
	private UserServiceImpl userService;


	@RequestMapping(path = "/allUser" , method = {RequestMethod.GET})
	public String view(Model model) {
		

		model.addAttribute("userList",userService.selectAllUser());
		
		return "user/allUser";
	}
	

	@RequestMapping("/pagingUser")
	public String pagingUser(@RequestParam(defaultValue = "1") int page,
							 @RequestParam(defaultValue = "5") int pageSize,
							 Model model) {
		logger.debug("page : {},pageSize : {},price : {}",page,pageSize);
		
		PageVo pageVo = new PageVo(page,pageSize);
		
		
		int cnt = userService.selectAllUserCnt();
		int allpage = (int)Math.ceil((double)cnt/pageSize);
		
		model.addAttribute("page",page);
		model.addAttribute("pageSize",pageSize);
		model.addAttribute("pageVo",pageVo);
		model.addAttribute("allpage",allpage);
		model.addAttribute("userList",userService.selectPagingUser(pageVo));
		//
		return "user/pagingUser";
	}
	

	@RequestMapping(path = "/user" , method = {RequestMethod.GET})
	public String user(String userid ,Model model) {
	
		model.addAttribute("user",userService.selectUser(userid));
		
		return "user/user";
	}
	
	@RequestMapping(path = "/userModify" , method = {RequestMethod.GET})
	public String getuserModify(String userid ,Model model) {
	
		model.addAttribute("user",userService.selectUser(userid));
		
		return "user/userModify";
	}
	
	@RequestMapping(path = "/userModify" , method = {RequestMethod.POST})
	public String postuserModify(Model model, MultipartFile profile,
			UserVo userVo) {
		
		if(profile.isEmpty() && profile.getSize() > 0) {
			
		try {
			userVo.setFilename(profile.getOriginalFilename());
			userVo.setRealfilename(UUID.randomUUID().toString()+profile.getOriginalFilename());
			
			profile.transferTo(new File("d:\\upload\\"+userVo.getRealfilename()));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		}else {
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
		
		model.addAttribute("user",userService.modifyUser(userVo));
		
		model.addAttribute("userid",userVo.getUserid());
		return "redirect:/user/user";
	}
	
	@RequestMapping(path = "/userDelete" , method = {RequestMethod.POST})
	public String postuserModify(Model model,String userid) {
		
		userService.deleteUser(userid);
		
		return "redirect:/user/pagingUser";
	}
	
	
	@RequestMapping(path = "/registUser" , method = {RequestMethod.GET})
	public String getRegistUser(Model model, MultipartFile profile,
			RedirectAttributes ra, UserVo userVo) {
		
		
		ra.addAttribute("profile", profile);
		ra.addAttribute("userid", userVo.getUserid());
		ra.addAttribute("usernm", userVo.getUsernm());
		ra.addAttribute("pass", userVo.getPass());
		ra.addAttribute("alias", userVo.getAlias());
		ra.addAttribute("addr1", userVo.getAddr1());
		ra.addAttribute("addr2", userVo.getAddr2());
		ra.addAttribute("zipcode", userVo.getZipcode());
		
		return "user/registUser";
	}
	
	@RequestMapping(path = "/registUser" , method = {RequestMethod.POST})
	public String getRegistUser(Model model, MultipartFile profile,
			UserVo userVo) {
		
		if(profile.isEmpty() && profile.getSize() > 0) {
			
		try {
			userVo.setFilename(profile.getOriginalFilename());
			
			userVo.setRealfilename(UUID.randomUUID().toString()+profile.getOriginalFilename());
			
			profile.transferTo(new File("d:\\upload\\"+userVo.getRealfilename()));
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		}else {
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
		
		if(cnt == 1) {
		return "redirect:/user/allUser";
		}else {
			
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

}
