<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="cn.oyeah.domain.*" %>   
<%@ page import="cn.oyeah.util.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	PageModel<UserSubscribeRecord> userPurchaseModel = (PageModel<UserSubscribeRecord>)request.getAttribute("userPurchaseModel");
	String userId = (String)request.getAttribute("userId");

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
    <td colspan="14" height="24"><font color="#FFFFFF">&nbsp;<strong>用户道具购买记录查询</strong></font></td>
  </tr>
  
  	 <tr bgcolor="#879999">
    <td colspan="9" height="24">
        <form action="servlet/UserRecordServlet?pageNo=1" method="post" onsubmit="return check()">
          &nbsp;
         	<font color="#FFFFFF"> 用户ID:</font> 
         	<input type="text" name="userId" id="userId" value="<%=userId %>" />
         	<input type="radio" checked="checked" name="subscribeId" id="subscribeId" value="subscribe" >
         		<font color="#FFFFFF"> 游戏充值查询</font> 
         	</input>
         	<input type="radio" checked="checked" name="subscribeId" id="subscribeId" value="propPurchase" >
         		<font color="#FFFFFF"> 道具购买查询</font> 
         	</input>
		   		<input type="submit" value="查询"/>
	    </form> 
    </td>
	</tr>
  
  <tr bgcolor="#f5f5f5">
    <td height="24" align="center" valign="middle" >截止日期</td>
	<td align="center" valign="middle" >购买道具总额(元宝)</td>
  </tr>
  
  <tr style="background: none repeat scroll 0% 0% rgb(255, 255, 255);" onmouseover="this.style.background='#f5f5f5'" onmouseout="this.style.background='#ffffff';" bgcolor="#ffffff">
    <td height="7%" align="center" valign="middle"><%=DateTimeUtils.getCurrentDate() %></td>
	<td height="7%" align="center" valign="middle"><%=userPurchaseModel.getTotalPrice() %></td>
	
 </tr>

</tbody></table>

<table style="margin: 0pt auto; padding-top: 10px;" height="101" bgcolor="#cccccc" border="0" cellpadding="3" cellspacing="1" width="98%">
  <tbody><tr bgcolor="#879999">
    <td colspan="14" height="24"><font color="#FFFFFF">&nbsp;<strong>用户道具购买记录</strong></font></td>
  </tr>

  <tr bgcolor="#f5f5f5">
    <td height="24" align="center" valign="middle" >日期</td>
    <td align="center" valign="middle" >道具名称</td>
	<td align="center" valign="middle" >购买道具金额(元宝)</td>
  </tr>


  <%
   for(Iterator it = userPurchaseModel.getList().iterator();it.hasNext();){
	   UserSubscribeRecord userSubscribeRecord = new UserSubscribeRecord();
	   userSubscribeRecord = (UserSubscribeRecord)it.next();
  %>
    <tr style="background: none repeat scroll 0% 0% rgb(255, 255, 255);" onmouseover="this.style.background='#f5f5f5'" onmouseout="this.style.background='#ffffff';" bgcolor="#ffffff">
    <td height="7%" align="center" valign="middle"><%=DateTimeUtils.formatDate(userSubscribeRecord.getTime(),DateTimeUtils.PATTERN_1) %></td>
    <td align="center" valign="middle" ><%=userSubscribeRecord.getName() %></td>
    <td height="7%" align="center" valign="middle"><%=userSubscribeRecord.getPurchaseAmount() %></td>
 </tr>
 <%} %>

  <tr>
    <td colspan="14" height="24" bgcolor="#F8FCF6"><div class="page"><span><strong><%=userPurchaseModel.getPageNo() %>/<%=userPurchaseModel.getTotalPages() %></strong></span>
    <a href="servlet/UserRecordServlet?pageNo=<%=userPurchaseModel.getPreviousPageNo() %>&userId=<%=userId %>&subscribeId=propPurchase"><img src="image/pre.gif" height="16" width="21"/></a>&nbsp;
    <a href="servlet/UserRecordServlet?pageNo=<%=userPurchaseModel.getNextPageNo() %>&userId=<%=userId %>&subscribeId=propPurchase"><img src="image/next.gif" height="16" width="21"/></a></div></td>
  </tr>
</tbody></table>


</body></html>