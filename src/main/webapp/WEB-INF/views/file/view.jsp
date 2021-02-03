<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath() %>/mvc/fileupload/upload" enctype="multipart/form-data" method="post">
	userid : <input type="text" name = "userid" value="brown">
	picture: <input type="file" name = "picture">
	<input type="submit" value="전송">
</form>
</body>
</html>