<%@page import="kr.or.ddit.ioc.vo.UserVo"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="kr.or.ddit.common.model.PageVo"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		
<script type="text/javascript">
//문서 로딩이 완료되고 나서 실행되는 영역
	$(function(){
		
		$(".user").on("click",function(){
			
			//this : 클릭 이베트가 발생한 element
			//data-userId ==> data-userid, 속성명은 대소분자 무시하고 소분자로 인식
			var userid = $(this).data("userid");
			$("#userid").val(userid);
			$("#frm").submit();
		});
		
	});
</script>		

<form id ="frm" action="<%=request.getContextPath() %>/user/user">
	<input type="hidden" id="userid" name = "userid" value=""/>
</form>
	

<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">사용자(tiles)</h2>
		<div class="table-responsive">
			<table class="table table-striped">
				<tr>
					<th>사용자 아이디</th>
					<th>사용자 이름</th>
					<th>사용자 별명</th>
					<th>등록일시</th>
				</tr>
				<c:forEach items="${userList}" var = "user">
				<tr class ="user" data-userid="${user.userid}">
					<td>${user.userid}</td>
					<td>${user.usernm}</td>
					<td>${user.alias}</td>
					<td><fmt:formatDate value="${user.reg_dt}" pattern="yyyy.MM.dd"/></td>
				</tr>
				</c:forEach>

			</table>
		</div>

		<a class="btn btn-default pull-right" href="<%=request.getContextPath() %>/user/registUser">사용자 등록</a>
		<a class="btn btn-default pull-right" href="<%=request.getContextPath() %>/user/excelDownload">사용자 엑셀다운로드</a>
		<div class="text-center">
			<ul class="pagination">
			
			
			<!-- pagination 값이 4이므로 1부터 4까지 4번 반복된다.
				햔재 사용자수	: 16명
				페이지 사이즈	: 5
				전체 페이지 수	: 4페이지 -->
				<li class="prev">
				<a href="<%=request.getContextPath() %>/user/pagingUser?page=1&pageSize=${pageVo.pageSize}">«</a>
				</li>
				<c:set var = "cnt" value="${allpage}"/>	
				<c:forEach begin ="1" end="${pageVo.pageSize-1}" var="i">
					<c:choose>
						<c:when test ="${pageVo.page == i}">
						<li class="active"><span>${i}</span></li>
						</c:when>
						<c:otherwise>
						<li><a href="<%=request.getContextPath() %>/user/pagingUser?page=${i}&pageSize=${pageVo.pageSize}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			<li class="next">
				<a href="<%=request.getContextPath() %>/user/pagingUser?page=${cnt}&pageSize=${pageVo.pageSize}">»</a>
			</li>
			</ul>
		</div>
	</div>
</div>
