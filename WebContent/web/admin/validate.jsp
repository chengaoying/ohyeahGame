<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="cn.oyeah.service.impl.*" %>
<%@ page import="cn.oyeah.service.*" %>  
<%@ page import="cn.oyeah.domain.*" %>    
<%
	//Thread.currentThread().sleep(5000);
    //System.out.println("ajax:::");
	String name = request.getParameter("name");
	IUserService userService = UserServiceImpl.getInstance();
	User user = userService.loginValidate(name);
	if (user != null) {
		out.println("用户名已经存在");
	}	
%>