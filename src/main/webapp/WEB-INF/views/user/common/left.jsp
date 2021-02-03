<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<ul class="nav nav-sidebar">
	<li class="active"><a href="#">Main <span class="sr-only">(current)</span></a></li>
	<li class="active"><a href="<%=request.getContextPath() %>/allUser">전체 사용자</a></li>
	<li class="active"><a href="<%=request.getContextPath() %>/allUserTiles">전체 사용자(타일즈)</a></li>
	
	<li class="active"><a href="<%=request.getContextPath() %>/user/pagingUser">사용자 페이징 리스트</a></li>
	<li class="active"><a href="<%=request.getContextPath() %>/user/pagingUserTiles">사용자 페이징 리스트</a></li>

	<%-- 
		/allUser 요청을 처리할 servlet(controller)
		kr.or.ddit.user.controller.AllUser
			doGet(){
			1. service 객체를 통해 전체 사용자 정보를 조회
			2. request객체에 userList라는 송성명으로 1번에서 조회한 데이터를 설정
			3. webapp/user/allUser.jsp 로 응답을 생성하도록 forward
				allUser.jsp는 user.html 참고 하여 생성
				header.jsp, left.jsp 재활용 하여 생성
				
				user.html의 사용자 정보를 표현하는 테마를 태그의 tr 부분을 request에 저장된 userList속성 값으로 정적 생성하여
				화면에 출력
			}
	 --%>
</ul>