<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/WEB-INF/views/common/common_lib.jsp"%>
<!--common_lib.jsp 의 내용을 지금 기술되는 부분에 코드를 복사해서 붙여넣기 -->

<script
	src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>
<link href="../css/signin.css" rel="stylesheet">

<script>
	$(function() {
		<c:if test="${msg != null}">
			alert("${msg}" + "ra");
		</c:if>
		//userid, rememberme 쿠키를 확인하여 존재할 경우 값설정, 체크
		if (Cookies.get("rememberme") == "Y") {

			var cbox = document.getElementById("rememberme");
			cbox.checked = true;
			$('#userid').val(Cookies.get("userid"))

		}
	})
	function getCookieValue(cookieStr, cookieName) {
		var cookies = cookieStr.split("; ");

		for ( var i in cookies) {

			var cookie = cookies[i].split("=");

			if (cookie[0] == cookieName) {
				return cookie[1];
			}
		}
		return "";
	}

	//추가하고자 하는 쿠키이름
	//쿠키의 값
	//유효기간(일수)

	function addCookie(cookieName, cookieValue, expires) {
		var dt = new Date(); //현재 날짜 => expires 값을 미래 날짜로 변경
		dt.setDate(dt.getDate() + parseInt(expires));
		console.log(dt)

		document.cookie = cookieName + "=" + cookieValue + "; "
				+ "path=/; expires=" + dt.toGMTString();
	}

	function deleteCookieValue(cookieName, cookieValue, expires) {
		addCookie(cookieName, "", -1);
		// 			var dt = new Date();  //현재 날짜 => expires 값을 미래 날짜로 변경
		// 			dt.setDate(dt.getDate()+parseInt(expires));
		// 			console.log(dt)

		// 			document.cookie = cookieName +"=" + cookieValue + "; " +
		// 			"path=/; expires=" + dt.toGMTString();
	}

	$(function() {
		$("#signin").on("click", function() {
			//rememberme 체크박스가 체크 되어있는지 확인
			//체크되어있을 경우

			if ($("#rememberme").is(":checked") == true) {
				//userid input에 있는 값을 userid쿠키로 저장
				//rememberme 쿠키로 Y 값을 저장

				Cookies.set("userid", $("#userid").val());

				//rememberme 쿠키로 y 값을 저장
				Cookies.set("rememberme", "Y");

			}
			//체크 해제가 되어 있는 경우 더이상 사용하지 않는다는 의미 이므로 userid, rememberme 쿠키 삭제
			else {
				Cookies.remove("userid");
				Cookies.remove("rememberme");
			}

			//체크 해제되어 있는 경우 : 더이상 사용하지 않는다는 의미 이므로 userid, rememberme 쿠키 삭제

			//form태그를 이용하여 sigin 요청
			$("#frm").submit();
		});
		
		
	});
	var cookieValue = getCookieValue(document.cookie, "userid");
	console.log(cookieValue);
</script>
</head>

<body>

	<div class="container">
		

		<form id="frm" class="form-signin" action="<%=request.getContextPath() %>/login/process"
			method="post">
			<h2 class="form-signin-heading">Please sign in</h2>
			<label for="inputid" class="sr-only">userid</label> <input
				type="text" id="userid" class="form-control" placeholder="사용자 아이디"
				required autofocus name="userid"> <label for="inputPassword"
				class="sr-only">Password</label> <input type="password"
				id="inputPassword" class="form-control" placeholder="Password"
				required="" name="pass" value="brownpass">
			<div class="checkbox">
				<label> <input type="checkbox" id="rememberme"
					value="remember-me"> Remember me
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="button"
				id="signin">Sign in</button>
		</form>
</body>
</html>