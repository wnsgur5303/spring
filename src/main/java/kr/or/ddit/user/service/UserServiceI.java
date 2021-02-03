package kr.or.ddit.user.service;

import java.util.List;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.ioc.vo.UserVo;

public interface UserServiceI {

	
	List<UserVo> selectAllUser();
	
	UserVo selectUser(String userid);
	
	List<UserVo> selectPagingUser(PageVo vo);
	
	int selectAllUserCnt();
	
	int modifyUser(UserVo uservo);
	
	int registUser(UserVo uservo);
	
	int deleteUser(String userid);

}
