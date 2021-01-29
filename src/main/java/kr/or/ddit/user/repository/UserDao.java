package kr.or.ddit.user.repository;

import kr.or.ddit.ioc.vo.UserVo;

public interface UserDao{
	
	UserVo getUser(String userid);

}
