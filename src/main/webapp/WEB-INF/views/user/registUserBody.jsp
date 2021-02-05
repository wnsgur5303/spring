<%@page import="kr.or.ddit.common.model.PageVo"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

	<%@include file="../common/common_lib.jsp"%>

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

			$("#frm").submit();
		});
		
	});
	

</script>		


<form id ="frm2" action="${cp}/user">
	<input type="hidden" id="userid" name = "userid" value=""/>
</form>
<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">사용자</h2>
		<div class="table-responsive">
			<form id = "frm" class="form-horizontal" role="form" 
			action="<%=request.getContextPath() %>/user/registUser" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="USERID"/></label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="userid" name="userid"
								placeholder="사용자 아이디" value="${param.userid}">
								
								<span style="color: red"><form:errors path="userVo.userid"/></span><br>
								
			spring message : <spring:message code="GREETING" arguments="brown"/>
								
							<input type="file" class="form-control" name="profile"/>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="USERNM"/></label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="usernm" name="usernm"
								placeholder="사용자 이름" value="${param.usernm}">
						</div>
					</div>

					
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label"><spring:message code="PASS"/></label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="pass" name="pass"
								placeholder="사용자 비밀번호" value="${param.pass}">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="REG_DT"/></label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="strdate" name="strdate"
								placeholder="등록일시" value="${param.reg_dt}">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="ALIAS"/></label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="alias" name="alias"
								placeholder="사용자 별명" value="${param.alias}">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="ADDR1"/></label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="addr1" name="addr1"
								placeholder="사용자 도로주소" readonly value="${param.addr1}">
						</div>						
							<div class="clo-sm-2">
								<button type="button" id="addrBtn" class="btn btn-default">주소검색</button>
							</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label"><spring:message code="ADDR2"/></label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="addr2" name="addr2"
								placeholder="사용자 상세주소" value="${param.addr2}">
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label" ><spring:message code="ZIPCODE"/></label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="zipcode" name="zipcode"
								placeholder="사용자 우편번호 코드" readonly value="${param.zipcode}">
						</div>
					</div>

					<a class="btn btn-default pull-right" id ="abtn">등록하기</a>
						
				</form>
					<select name="lang">
						<option value="">언어설정</option>
						<option value="ko">한국어</option>
						<option value="en">영어</option>
					</select>	
				<script type="text/javascript">
					$(function() {
						$("select[name=lang]").on("change",function(){
							
							console.log("on change");
							document.location="<%=request.getContextPath()%>/user/registUser?lang="+ $(this).val();
							
						});
						$("select[name=lang]").val("${param.lang}");
						
					})
				</script>
		</div>
	</div>
</div>