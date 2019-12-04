<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>index</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<style type="text/css">

		div{
		
		width:100px;/*定宽*/
		
		margin: 10px auto;
		
		}

	</style>
  </head>
  
  <body>	
 
  	<form name="frm_upload" action="${pageContext.request.contextPath }/FileUpload?method=upload" method="post" enctype="multipart/form-data">
  	 <div>
		<!-- 文件命名：<input type="text" name="userName"> -->
  	 	文件：   <input type="file" name="file_img">   <br/>
  	 	<br>
  	 	<input type="submit" value="上传">
  	 	<br>
  	 	<br>
  	 	<br>
  	 	<a href="${pageContext.request.contextPath }/index.jsp">返回首页</a> 
  	 </div>
  	 
  	</form>
  		
  </body>
</html>
