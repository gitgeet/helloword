<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<!--   <head>
    
    <title>下载列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<style type="text/css">
		p{
			text-align:center;
		}
	</style>
  </head> -->
  
  <body>	 
   	<table border="1" align="center">
   		<tr>
	   		<th>序号</th>
	   		<th>文件名</th>
	   		<th>操作</th>
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
	<p>
   		<a href="${pageContext.request.contextPath }/index.jsp">返回首页</a> 
	</p>	
  </body>
</html>