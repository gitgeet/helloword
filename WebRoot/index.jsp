<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- <%
    response.sendRedirect("test.jsp");
%> --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>文件上传与下载操作</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<style type="text/css">
		p{
			text-align:center;
		}
	</style>
  </head>
  
  <body>	
  	<p>
  		<%-- <a href="${pageContext.request.contextPath }/upload.jsp">文件上传</a> &nbsp;&nbsp;&nbsp; --%>
  		
  		<a href="${pageContext.request.contextPath }/FileUpload?method=downlist">进入</a>
  		 
	</p>
  		
  </body>
</html>
