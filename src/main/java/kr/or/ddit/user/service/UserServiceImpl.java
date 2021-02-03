package kr.or.ddit.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.ioc.vo.UserVo;
import kr.or.ddit.user.repository.UserDaoI;



@Repository("userService")
public class UserServiceImpl implements UserServiceI{

	
	@Resource(name = "userDao")
	private UserDaoI userDao;
	
	
	public UserServiceImpl() {}//인자가 없는 것도 같이 해줘야함 인자 있는생성자 만들었으면
	
	public UserServiceImpl(UserDaoI userDao) {
		this.userDao = userDao;
	}
	
	
	@Override
	public UserVo selectUser(String userid) {
		return userDao.selectUser(userid);
	}

	public UserDaoI getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoI userDao) {
		this.userDao = userDao;
	}

	
	
	
	@Override
	public List<UserVo> selectAllUser() {
		return userDao.selectAllUser();
	}

	@Override
	public List<UserVo> selectPagingUser(PageVo vo) {
		return userDao.selectPagingUser(vo);
	}

	@Override
	public int selectAllUserCnt() {
		return userDao.selectAllUserCnt();
	}

	@Override
	public int modifyUser(UserVo uservo) {
		return userDao.modifyUser(uservo);
	}

	@Override
	public int registUser(UserVo uservo) {
		return userDao.registUser(uservo);
	}

	@Override
	public int deleteUser(String userid) {
		return userDao.deleteUser(userid);
	}
	
	
}
