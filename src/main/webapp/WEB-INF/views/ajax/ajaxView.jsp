<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ include file="/WEB-INF/views/common/common_lib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	$(function (){
		
		$("#makeBtn").on("click", function(){
// 			alert($("input[name=userid]").val());
//  			$("#reqData").text($("input[name=userid]").val());
 			
			if($("#type").val() == "form"){
				$("#reqData").text($("input[name=userid]").val());
				$("#reqData").text(("userid="+ ($("input[name=userid]").val()) +"&usernm=" + ($("input[name=usernm]").val))	);
			}else{	
				
			}
			
		});

			$("#send").on("click",function(){
				
				$.ajax({
					url : '<%=request.getContextPath()%>/ajax/form',
					type : 'post',
					data : $("#form").serialize(),
					dataType:'json',
					success:function(res){
						console.log(res.rangers[1]);
					}
				})
			});
				
	})
</script>

<title>Insert title here</title>
</head>
<body>
<form id="form">
	사용자 아이디 : <input type="text" name="userid" value="brown"/><br>
	사용자 이름  :<input type="text" name="usernm" value="브라운"/>
	
	<select id="type">
		<option value="form">form전송</option>
		<option value="json">json전송</option>
	</select><br>
	<input type="button" id="makeBtn" value="전송데이터생성"/>
	</form>
	<h4>전송 데이터</h4>
	<span id ="reqData">
	</span>
	
	<h4>응답 데이터</h4>
	<span id="respData"></span>
	
	<input type="button" id="send" value="전송">
</body>
</html>