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
	<script type="text/javascript">
	function delUser(delID){
		var status=window.confirm("您确定要删除吗?");
		if(status){
			//创建XMLHttpRequest对象
			var xmlhttp;
			if(window.ActiveXObject){//IE浏览器
				xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			}else if(window.XMLHttpRequest){
				xmlhttp=new XMLHttpRequest();
			}else{xmlhttp
				alert("请求对象创建失败!")
			}
			//创建请求
			/*
			xmlhttp.open("post","UserServlet?actionType=del",true);//参1: 请求方式  参2:请求路径 参3: 是否异步请求
			xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");//告诉服务器,请求实体中含有参数
			*/
			xmlhttp.open("get","UserServlet?actionType=del&id="+delID",true);//参1: 请求方式  参2:请求路径 参3: 是否异步请求
			//创建回调函数
			xmlhttp.onreadystatechange=function(){
				if(xmlhttp.readyState==4){//表示服务器处理请求结束: 处理异常 处理正常
					if(xmlhttp.status==200){//表示处理请求成功 :  不代表业务处理结果(-1  非-1)
						var rs=xmlhttp.responseText;//获得服务器返回的数据
						if(rs>=0){//删除成功
						alert("删除成功");
						var delTr=document.getElementById("t"+delID);
						delTr.parentNode.removeChild(delTr);
						}else{//删除失败
						alert("删除失败");
						}
					}
				}	
			}
			//发送请求: send("参数名=值&参数名=值&...")
			//xmlhttp.send("id="+delID);
			xmlhttp.send(null);
		}
	}
	
	/*
	 * post请求是通过请求实体传递参数
	 * get请求是通过地址传递参数
	 */
	</script>
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
	<tr><th>ID</th><th>姓名</th><th>密码</th><th>年龄</th><th>性别</th><th>操作</th></tr>
	<c:forEach items="${requestScope.userList }" var="user">
	<tr id="t${user.id }">
	<td>${user.id }</td>
	<td>${user.name }</td>
	<td>${user.password }</td>
	<td>${user.age }</td>
	<td>${user.sex }</td>
	<td>
	<%--<a href="UserServlet?actionType=del&id=${user.id }" onclick="delUser()">&nbsp;删除&nbsp;</a>--%>
	<a href="javascript:void(0)" onclick="delUser(${user.id })">&nbsp;删除&nbsp;</a>
	</td>
	</tr>
	</c:forEach>
	</table>
  </body>
</html>
