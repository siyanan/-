<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!-- 导入外部标签库 -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
        欢迎来到xx系统. <br>
    <!-- 
    CURD 增[c]删[d]改[u]查[r]操作
     -->
    <a href="addUser.html">添加用户</a><br>
    <a href="upUser.html">修改用户</a><br>
    <a href="delUser.html">删除用户</a><br>
    <a href="UserServlet?actionType=sel">查询用户</a><br>
    <br>
   
    查询结果如下:<table border='2px' bordercolor='blue'>
	<tr><th>ID</th><th>姓名</th><th>密码</th><th>年龄</th><th>性别</th></tr>
	<c:forEach items="" var="user">
	<tr><td>${user.id }</td><td>${user.name }</td><td>${user.password }</td><td>${user.age }</td><td>${user.sex }</td></tr>
	</c:forEach>
	</table>
  </body>
</html>
