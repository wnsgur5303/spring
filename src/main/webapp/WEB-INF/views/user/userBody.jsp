<%@page import="kr.or.ddit.ioc.vo.UserVo"%>
<%@page import="kr.or.ddit.common.model.PageVo"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
//문서 로딩이 완료되고 나서 실행되는 영역
	$(function(){
		
		$("#modifyBtn").on('click',function(){
			$("#frm").attr("method","get");
			$("#frm").attr("action","<%=request.getContextPath() %>/user/userModify");
			$("#frm").submit();
		});
		
		$("#deleteBtn").on('click',function(){
			$("#frm").attr("method","post");
			$("#frm").attr("action","<%=request.getContextPath() %>/user/userDelete");
			$("#frm").submit();
		});
		
	});
</script>		
</head>

<%UserVo user = (UserVo)request.getAttribute("user");%>

<form id = "frm">
<input type="hidden" id="userid" name = "userid" value="<%=user.getUserid() %>"/>
</form>

<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">사용자(Tiles)</h2>
		<div class="table-responsive">
			
			<form class="form-horizontal" role="form">	
			
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 사진</label>
						<div class="col-sm-10">
						<a href="<%=request.getContextPath() %>/user/profileDownload?userid=${user.userid}">
						<img src="<%=request.getContextPath() %>/user/profile?userid=${user.userid}"/>
						</a>
						</div>
					</div>
			
			
				<input type="hidden" name ="userid" value="${user.userid}"/>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-10">
							<label class="control-label">${user.userid}</label>
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
						<div class="col-sm-10">
							<label class="control-label">${user.usernm}</label>
						</div>
					</div>
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">비밀번호</label>
						<div class="col-sm-10">
							<label class="control-label">${user.pass}</label>
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">등록일시</label>
						<div class="col-sm-10">
							<label class="control-label"><fmt:formatDate value="${user.reg_dt}" pattern="yyyy.MM.dd"/></label>
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">별명</label>
						<div class="col-sm-10">
							<label class="control-label">${user.alias}</label>
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">도로주소</label>
						<div class="col-sm-10">
							<label class="control-label">${user.addr1}</label>
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">상세주소</label>
						<div class="col-sm-10">
							<label class="control-label">${user.addr2}</label>
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">우편번호 코드</label>
						<div class="col-sm-10">
							<label class="control-label">${user.zipcode}</label>
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">업로드파일명</label>
						<div class="col-sm-10">
							<label class="control-label">${user.filename}</label>
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">실제파일경로</label>
						<div class="col-sm-10">
							<label class="control-label">${user.realfilename}</label>
						</div>
					</div>							
				</form>

		</div>
<div class="form-group"></div>
		<button type ="button" id = "modifyBtn" class="btn btn-default pull-right">사용자 수정</button>
		<button type ="button" id = "deleteBtn" class="btn btn-default pull-right">사용자 삭제</button>
</div>
	</div>
