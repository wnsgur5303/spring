package kr.or.ddit.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import kr.or.ddit.ioc.vo.UserVo;
import kr.or.ddit.user.repository.UserDao;


@Repository("userService")
public class UserServiceImpl implements UserServiceI{

	
	@Resource(name = "userDao")
	private UserDao userDao;
	
	
	public UserServiceImpl() {}//인자가 없는 것도 같이 해줘야함 인자 있는생성자 만들었으면
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	@Override
	public UserVo getUser(String userid) {
		return userDao.getUser(userid);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
}
