<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort() + path + "/";
	
%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>欧耶游戏充值统计后台</title>
<link rel="shortcut icon" href="/favicon.ico">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script src="js/client_validate.js"></script>
<SCRIPT language=JavaScript>
		
		if (window.self != window.top) {
			window.top.location = window.self.location;
		}
		function init(){
			loginForm.name.focus();
		}
			
		function submitForm(form)
		{
			//alert(111);
			if (trim(form.name.value).length == 0 || trim(form.passWord.value).length == 0) {
				alert("用户代码或密码不能为空！");
				form.name.focus();
				return false;
			} else {
				return true;
			}
		
		}
</SCRIPT>
</head>
<body onload=init()>
<form name="loginForm" id="loginForm" action="<%=basePath %>login" method="post" onsubmit="return submitForm(this)">
  <fieldset>
  <legend>请登入</legend>
  <label for="login">用户名:</label>
  <input type="hidden" name="login" value="login"/>
  <input type="text" id="name" name="name" />
  <div class="clear"></div>
  <label for="password">密码:</label>
  <input type="password" id="passWord" name="passWord" />
  <div class="clear"></div>
  <label for="remember_me" style="padding: 0;"></label>
  
  <div class="clear"></div>
  <br />
  <input type="submit" style="margin: -20px 0 0 287px;" class="button" name="commit" value="登入"/>
  </fieldset>
</form>
<p></p>
<p align="center"><strong>欧耶游戏充值统计后台</strong></p>
</body>
</html>