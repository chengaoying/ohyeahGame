<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
	//System.out.println(basePath);
%>
 用户名或密码错误！<br>
 <a href="<%=basePath %>">返回</a>
