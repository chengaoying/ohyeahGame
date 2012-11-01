<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//System.out.println(basePath);
%>
 
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="Keywords" content="">
<meta name="Description" content="">
<title>欧耶游戏充值统计后台</title>

<script language="JavaScript">
function switchSysBar()
{
   if (switchPoint.innerText==3)
   {
      switchPoint.innerText=4
      document.all("frameTitle").style.display="none"
   }
   else
   {
      switchPoint.innerText=3
      document.all("frameTitle").style.display=""
   }
}
</script>
<base href="<%=basePath %>">
<link href="css/admin_css.css" rel="stylesheet" type="text/css">
</head>
<body scroll="no" topmargin="0" bottom="0" leftmargin="0" rightmargin="0">
<table border="0" cellpadding="0" cellspacing="0" height="100%" width="100%">
  
  <tbody>
 
  <tr>
    <td rowspan="2" id="frameTitle" align="center" height="100%" width="250">
    <iframe style="z-index: 2; visibility: inherit; width: 250px; height: 100%;" name="leftFrame" id="leftFrame" marginwidth="0" marginheight="0" src="web/admin/left.jsp"  frameborder="0" noResize scrolling="no">	</iframe>
	</td>
    <td rowspan="2" onclick="switchSysBar()" align="center" bgcolor="#7d7c7b" height="86%" width="10">
	<font style="FONT-SIZE: 10px; CURSOR: hand; COLOR: #ffffff; FONT-FAMILY: Webdings">
	  <span id="switchPoint"></span>	</font>	</td>
    <td height="100%">
	
	<iframe style="z-index: 4; visibility: inherit; width: 100%; height: 96%;" name="mainFrame" id="mainFrame" marginwidth="16" marginheight="16" src="welcome.jsp" frameborder="0" noresize scrolling="yes">
	</iframe>
	</td>
  </tr>
  
</tbody></table>

</body></html>