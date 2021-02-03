package kr.or.ddit.user.repository;

import java.util.List;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.ioc.vo.UserVo;


public interface UserDaoI {
	//전체 사용자 정보 조회
	/*
	 * 전체 사용자 정보 조회
	 * SELECT * FROM users
	 * WHERE users
	 * */
	
	
	List<UserVo> selectAllUser();
	
	//userid에 해당하는 사용자 한명의 정보 조회
	UserVo selectUser(String userid);
	
	//사용자 페이징 조회
	List<UserVo> selectPagingUser(PageVo vo);
	
	//사용자 전체 수 조회
	int selectAllUserCnt();
	
	int insertUser(UserVo vo);
	
	//사용자 정보 수정
	int modifyUser(UserVo uservo);
	
	int registUser(UserVo uservo);

	int deleteUser(String userid);
}
