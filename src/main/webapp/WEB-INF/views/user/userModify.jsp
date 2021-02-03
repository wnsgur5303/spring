<%@page import="kr.or.ddit.common.model.PageVo"%>
<%@page import="kr.or.ddit.ioc.vo.UserVo"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp</title>
	<%@include file="../common/common_lib.jsp"%>
<%-- 	<link href="${cp}/css/bootstrap.css" rel="stylesheet">
	<script src="/js/bootstrap.js"></script> --%>
	<link href="<%=request.getContextPath() %>/css/dashboard.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/css/blog.css" rel="stylesheet">
		
		
		
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>		
<script type="text/javascript">
//문서 로딩이 완료되고 나서 실행되는 영역
	$(function(){
		//주소 검색 버튼이 클릭되었을 때 다음주소 api 팝업을 연다
		$("#addrBtn").on("click",function(){
			new daum.Postcode({
				oncomplete : function(data) {
					// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
					// 예제를 참고하여 다양한 활용법을 확인해 보세요.
					console.log(data)
					
					$("#addr1").val(data.roadAddress);
					$("#zipcode").val(data.zonecode);
					//data.roadAddress; //도로주소
					//data.zonecode; //우편번호
					
					//사용자 편의성을 위해 상세주소 입력 input 태그로 focus 설정
					$("#addr2").focus();
				}
			}).open();
		})
		
		
		$("#abtn").on("click",function(){
			
			//this : 클릭 이베트가 발생한 element
			//data-userId ==> data-userid, 속성명은 대소분자 무시하고 소분자로 인식
			$("#frm").submit();
		});
		
	});
	

</script>		
</head>

<body>
<form id ="frm2" action="${cp}/user">
	<input type="hidden" id="userid" name = "userid" value=""/>
</form>
	
<%@include file = "../common/header.jsp"%>

<div class="container-fluid">
		<div class="row">
			
<div class="col-sm-3 col-md-2 sidebar">
	<%@include file="../common/left.jsp"%>
</div><div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				
<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">사용자</h2>
		<div class="table-responsive">
		<%UserVo user = (UserVo)request.getAttribute("user");%>
			<form id = "frm" class="form-horizontal" role="form" action="<%=request.getContextPath() %>/user/userModify" method="post" enctype="multipart/form-data">
				<input type="hidden" name ="userid" value="${user.userid}"/>
				<input type="hidden" name="filename" value="${user.filename}">
				<input type="hidden" name="realfilename" value="${user.realfilename}">	
				
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 사진</label>
						<div class="col-sm-10">
						<img src="${cp}/profile/${user.userid}.png">
						<input type="file" class="form-control" id="profile" name="profile"/>

						</div>
						
					</div>
					
					
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
							<label class="control-label">${user.userid}</label>
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="usernm" name="usernm"
								placeholder="사용자 이름" value="${user.usernm}">
						</div>
					</div>
					
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">비밀번호</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="pass" name="pass"
								placeholder="비밀번호" value="${user.pass}">
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">등록일시</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="strdate" name="strdate"
								placeholder="등록일시" value="<fmt:formatDate value="${user.reg_dt}" pattern="yyyy.MM.dd"/>">
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">별명</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="alias" name="alias"
								placeholder="별명" value="${user.alias}">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">도로주소</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="addr1" name="addr1"
								placeholder="사용자 아이디" <%=user.getAddr1()%>>
								
						</div>						
							<div class="clo-sm-2">
								<button type="button" id="addrBtn" class="btn btn-default">주소검색</button>
							</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">상세주소</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="addr2" name="addr2"
								placeholder="사용자 아이디" value="<%=user.getAddr2()%>">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">우편번호 코드</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="zipcode" name="zipcode"
								placeholder="사용자 아이디" value="<%=user.getZipcode()%>">
						</div>
					</div>
					<a class="btn btn-default pull-right" id ="abtn">수정완료</a>							
				</form>

		</div>

	
		<div class="text-center">

		</div>
	</div>
</div>
	</div>
		</div>
	</div>
</body>
</html>