<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>index</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<!-- <style type="text/css">

		div{
		
		width:800px;/*定宽*/
		
		margin: 10px auto;
		
		}

	</style> -->
  </head>
 
  <body>	
  

 
  	<form name="frm_upload" action="${pageContext.request.contextPath }/FileUpload?method=upload" method="post" enctype="multipart/form-data">
  	 <div>
		<!-- 文件命名：<input type="text" name="userName"> -->
  	 	文件：   <input type="file" name="file_img"><input type="submit" value="本地上传">
  	 	<br>
  	 	<br>
  	 	<%-- <a href="${pageContext.request.contextPath }/index.jsp">返回首页</a>  --%>
  	 </div>
  	 
  	</form>
  <%-- 	
  	<form name="frm_upload" action="${pageContext.request.contextPath }/FileUpload?method=createFolder" method="post" enctype="multipart/form-data">
  	 <div>
		<!-- 文件命名：<input type="text" name="userName"> -->
  	 	文件：   <input type="file" name="file_img">   <br/>
  	 	<br>
  	 	<input type="submit" value="新建文件夹">
  	 	<br>
  	 	<br>
  	 	<br>
  	 	<a href="${pageContext.request.contextPath }/index.jsp">返回首页</a> 
  	 </div>
  	 
  	</form>
  	<form name="frm_upload" action="${pageContext.request.contextPath }/FileUpload?method=preList" method="post" enctype="multipart/form-data">
  	 <div>
		<!-- 文件命名：<input type="text" name="userName"> -->
  	 	文件：   <input type="file" name="file_img">   <br/>
  	 	<br>
  	 	<input type="submit" value="返回上一级">
  	 	<br>
  	 	<br>
  	 	<br>
  	 	<a href="${pageContext.request.contextPath }/index.jsp">返回首页</a> 
  	 </div>
  	 
  	</form>
  	<form name="frm_upload" action="${pageContext.request.contextPath }/FileUpload?method=currentList" method="post" enctype="multipart/form-data">
  	 <div>
		<!-- 文件命名：<input type="text" name="userName"> -->
  	 	文件：   <input type="file" name="file_img">   <br/>
  	 	<br>
  	 	<input type="submit" value="刷新">
  	 	<br>
  	 	<br>
  	 	<br>
  	 	<a href="${pageContext.request.contextPath }/index.jsp">返回首页</a> 
  	 </div> --%>
  	 
  	</form>
  	<!-- <table border="1" align="center"> -->
  	<table border="1" align="">
   		<tr>
	   		<th>序号</th>
	   		<th>文件名</th>
	   		<th>操作</th>
	   		<th><a href="${pageContext.request.contextPath }/FileUpload?method=downlist">刷新</a></th>
   		</tr>
   		<c:forEach var="en" items="${requestScope.fileNames }"  varStatus="vs">
   			<tr>
   				<td>${vs.count }</td>
		   		<td>${en.value }</td>
		   		<td>
			   		<!-- 构建一个地址 -->
			   		<c:url var="url" value="FileUpload">
			   			<c:param name="method" value="down"></c:param>
			   			<c:param name="fileName" value="${en.key }"></c:param>
			   		</c:url>
			   		<a href="${url }">下载</a>
		   		</td>
		   	</tr>
   		</c:forEach>
   		
	</table>
	<br>
	<br>
	<a href="${pageContext.request.contextPath }/FileUpload?method=downlist">返回首页</a> 
<%-- 	<a href="${pageContext.request.contextPath }/test.jsp">返回首页</a>  --%>
	
  		
  </body>
</html>
