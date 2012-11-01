<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="cn.oyeah.domain.*" %>   
<%@ page import="cn.oyeah.util.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
	String startTime = (String)request.getAttribute("startTime");
	String endTime = (String)request.getAttribute("endTime");
	String command = (String)request.getAttribute("command");
	List<Product> productList = (List<Product>)request.getAttribute("productList");
	//System.out.println(basePath);
	PageModel<UserSubscribeStatistic> pageModel = (PageModel<UserSubscribeStatistic>)request.getAttribute("pageModel");
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
		var slct = document.getElementById("game");
		//alert(slct.value);
		if(slct.value == "请选择查询的游戏"){ 
		   document.getElementById("command").value="all";
		   return true;
		 } 
		
	}
</script>
<base href="<%=basePath %>">
<link href="css/admin_css.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<table style="margin: 0pt auto; padding-top: 10px;"  bgcolor="#cccccc" border="0" cellpadding="3" cellspacing="1" width="98%">
  <tbody><tr bgcolor="#879999">
    <td colspan="9" height="24"><font color="#FFFFFF">&nbsp;<strong>充值用户统计</strong></font></td>
  </tr>
  
  	 <tr bgcolor="#879999">
    <td colspan="9" height="24">
        <form action="servlet/SubscribeStatisticServlet?pageNo=1" method="post" onsubmit="return check()">
        <input type="hidden" name="command" id="command" value="single" />
          &nbsp;&nbsp; 
           <select name="productId" id="game">
	            <option value="请选择查询的游戏" selected="selected">选择查询的游戏</option>
	             <%
	               for(Iterator<Product> it = productList.iterator(); it.hasNext();){
	            	   Product product = it.next();	 
	            %>
	            <option value="<%=product.getProductId() %>"  ><%=product.getProductName() %></option>
	            <%} %>
	         </select>
	         
		    开始时间:<input type=text name="startTime" value="<%=startTime %>" onClick="WdatePicker()"/>
		    结束时间:<input type=text name="endTime" value="<%=endTime %>" onClick="WdatePicker()"/>
		    <input type="submit" value="查询"/>
	    </form> 
    </td>
	</tr>
  
  <tr bgcolor="#f5f5f5">
    <td height="24" align="center" valign="middle" >截止日期</td>
	<td align="center" valign="middle" >总充值用户</td>
	<td align="center" valign="middle" >充值总金额(元)</td>
  </tr>
    <tr style="background: none repeat scroll 0% 0% rgb(255, 255, 255);" onmouseover="this.style.background='#f5f5f5'" onmouseout="this.style.background='#ffffff';" bgcolor="#ffffff">
    <td height="7%" align="center" valign="middle" ><%=endTime %></td>
    <td height="7%" align="center" valign="middle" ><%=pageModel.getTotalUsers() %></td>
	<td height="7%" align="center" valign="middle" ><%=pageModel.getTotalPrice() %></td>
 </tr>

</tbody></table>

<table style="margin: 0pt auto; padding-top: 10px;"  bgcolor="#cccccc" border="0" cellpadding="3" cellspacing="1" width="98%">
  <tbody><tr bgcolor="#879999">
    <td colspan="14" height="24"><font color="#FFFFFF">&nbsp;<strong>充值用户统计详细</strong></font></td>
  </tr>

  <tr bgcolor="#f5f5f5">
    <td height="24" align="center" valign="middle" >日期</td>
	<td align="center" valign="middle" >新增充值用户</td>
	<td align="center" valign="middle" >总充值用户</td>
	<td align="center" valign="middle" >充值金额(元)</td>
  </tr>
  <%
  	for(Iterator it = pageModel.getList().iterator();it.hasNext();){
  		UserSubscribeStatistic userSubscribe = (UserSubscribeStatistic)it.next();
  %>
  <tr style="background: none repeat scroll 0% 0% rgb(255, 255, 255);" onmouseover="this.style.background='#f5f5f5'" onmouseout="this.style.background='#ffffff';" bgcolor="#ffffff">
    <td height="7%" align="center" valign="middle" ><%=userSubscribe.getTime() %></td>
	<td height="7%" align="center" valign="middle" ><%=userSubscribe.getNewCount() %></td>
    <td height="7%" align="center" valign="middle" ><%=userSubscribe.getTotalCount() %></td>
	<td height="7%" align="center" valign="middle" ><%=userSubscribe.getPrice() %></td>
 </tr>
 <%} %>
  <tr>
    <td colspan="14" height="24" bgcolor="#F8FCF6"><div class="page"><span><strong><%=pageModel.getPageNo() %>/<%=pageModel.getTotalPages() %></strong></span>
    <a href="servlet/SubscribeStatisticServlet?pageNo=<%=pageModel.getPreviousPageNo() %>&startTime=<%=startTime %>&endTime=<%=endTime %>&command=<%=command %>"><img src="image/pre.gif" height="16" width="21"/></a>&nbsp;
    <a href="servlet/SubscribeStatisticServlet?pageNo=<%=pageModel.getNextPageNo() %>&startTime=<%=startTime %>&endTime=<%=endTime %>&command=<%=command %>"><img src="image/next.gif" height="16" width="21"/></a></div></td>
  </tr>
</tbody></table>


</body></html>