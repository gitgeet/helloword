<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<head>
    
    <title>成功文件上传与下载操作</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<style type="text/css">
		p{
			text-align:center;
		}
	</style>
  </head>
</head>
<body>

<h1 sytle="color:greed">文件上传成功！</h1>
<a href = "${pageContext.request.contextPath }/FileUpload?method=downlist">返回首页</a> 

</body>
</html>