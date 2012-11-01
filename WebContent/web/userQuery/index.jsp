<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
<style type="text/css">

</style>

<script language="JavaScript">
	function check()
	{
		var slct = document.getElementById("userId");
		//alert(slct.value);
		if(slct.value == ""){ 
		   alert("用户ID不能为空!");
		   return false;
		 } 
		return true;
	}
</script>
<base href="<%=basePath %>">
<link href="css/admin_css.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<table style="margin: 0pt auto; padding-top: 10px;" height="101" bgcolor="#cccccc" border="0" cellpadding="3" cellspacing="1" width="98%">
  <tbody><tr bgcolor="#879999">
    <td colspan="14" height="24"><font color="#FFFFFF">&nbsp;<strong>用户充值记录查询</strong></font></td>
  </tr>
  
  	 <tr bgcolor="#879999">
    <td colspan="9" height="24">
        <form action="servlet/UserRecordServlet?pageNo=1" method="post" onsubmit="return check()">
          &nbsp;
         	<font color="#FFFFFF"> 用户ID:</font> 
         	<input type="text" name="userId" id="userId" value="" />
         	<input type="radio" checked="checked" name="subscribeId" id="subscribeId" value="subscribe" >
         		<font color="#FFFFFF"> 游戏充值查询</font> 
         	</input>
         	<input type="radio" name="subscribeId" id="subscribeId" value="propPurchase" >
         		<font color="#FFFFFF"> 道具购买查询</font> 
         	</input>
		   		<input type="submit" value="查询"/>
	    </form> 
    </td>
	</tr>
</tbody>
</table>



</body></html>