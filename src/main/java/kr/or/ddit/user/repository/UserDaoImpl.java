package kr.or.ddit.user.repository;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.ioc.vo.UserVo;


// <bean id="" class=""
// @Repository에서 별다른 설정을 하지 않으면 스프링 빈 이름으로 class 이름에서 첫글자를 소문자로 한
// 문자열이 스프링 빈의 이름으로 설정된다
// UserDaoImpl --> userDaoImpl

// UserDao / UserDaoImpl ==> @Resource(name = "userDaoImpl")
// UserDaoI / UserDao	==> @Resource(name="userDao")


@Repository("userDao")
public class UserDaoImpl implements UserDaoI{

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate template;

	@Override
	public UserVo selectUser(String userid) {

		return template.selectOne("users.selectUser",userid);
	}
	@Override
	public List<UserVo> selectAllUser() {
		return template.selectList("users.selectAllUser");
	}
	@Override
	public List<UserVo> selectPagingUser(PageVo vo) {
		return template.selectList("users.selectPagingUser",vo);
	}
	@Override
	public int selectAllUserCnt() {
		return template.selectOne("users.selectAllUserCnt");
	}
	@Override
	public int insertUser(UserVo vo) {
		return template.insert("users.insertUser",vo);
	}
	@Override
	public int modifyUser(UserVo uservo) {
		return template.update("users.modifyUser",uservo);
	}
	@Override
	public int registUser(UserVo uservo) {
		return template.insert("users.registUser",uservo);
	}
	@Override
	public int deleteUser(String userid) {
		return template.delete("users.deleteUser",userid);
	}
}
