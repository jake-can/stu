<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>入门案例</title>
</head>
<body>
	<h1>文件上传入门案例</h1>
	<!-- enctype="multipart/form-data":开启多媒体标签 -->
	<form action="http://localhost/file" method="post" enctype="multipart/form-data">
		<input name="photo" type="file"/>
		<button type="submit" />提交
	</form>
</body>
</html>