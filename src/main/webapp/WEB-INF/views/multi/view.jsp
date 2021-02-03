<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath() %>/mvc/multi/param" method="post">
	userid :<input type="text" name ="userid" value="brown"><br>
	userid :<input type="text" name ="userid" value="sally"><br>
	<input type="submit" value="전송">
</form>

<h3>List&lt;UesrVo&gt;</h3>
<form action="<%=request.getContextPath() %>/mvc/multi/param" method="post">
	userid :<input type="text" name ="userVoList[0].userid" value="brown"><br>
	userid :<input type="text" name ="userVoList[1].userid" value="sally"><br>
	
	usernm :<input type="text" name ="userVoList[0].usernm" value="브라운"><br>
	usernm :<input type="text" name ="userVoList[1].usernm" value="셀리"><br>
	<input type="submit" value="전송">
</form>
</body>
</html>