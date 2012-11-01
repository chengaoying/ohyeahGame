<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="cn.oyeah.util.*" %>
<%@ page import="cn.oyeah.domain.*" %>
<%@ page import="java.util.*" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
	String sTime = (String)request.getAttribute("sTime");
	String eTime = (String)request.getAttribute("eTime");
	//充值金额<time+productId,count>
	Map<String,Integer> allSubscribeMap = (Map<String,Integer>)request.getAttribute("allSubscribeMap");
	//充值游戏名<productId,name>
	Map<String,String> productList = (Map<String,String>)request.getAttribute("productList");

	//所有游戏的总额
	int allTotalPrice = 0;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
<base href="<%=basePath %>">
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<link href="css/admin_css.css" rel="stylesheet" type="text/css">
</head>
<body>


<table style="margin: 0pt auto; padding-top: 10px;"  bgcolor="#cccccc" border="0" cellpadding="3" cellspacing="1" width="98%">
  <tbody><tr bgcolor="#879999">
    <td colspan="9" height="24"><font color="#FFFFFF">&nbsp;<strong>游戏充值统计</strong></font></td>
  </tr>
  
	 <tr bgcolor="#879999">
    <td colspan="9" height="24">
        <form action="servlet/RechargeGameServlet" method="post">
          &nbsp;&nbsp; 
		    开始时间:<input type=text name="startTime" value="<%=sTime %>" onClick="WdatePicker()"/>
		    结束时间:<input type=text name="endTime" value="<%=eTime %>" onClick="WdatePicker()"/>
		    <input type="submit" value="查询"/>
	    </form> 
    </td>
	</tr>
	
	
  <tr bgcolor="#f5f5f5">
    <td height="24" align="center" valign="middle" >游戏充值</td>
    <% //显示已充值的游戏名称
       for(Iterator it = productList.entrySet().iterator();it.hasNext();){
    	   Map.Entry entry = (Map.Entry)it.next();    
    	   String value = (String)entry.getValue();      	   
    %>
	<td align="center" valign="middle"><%=value %>(元)</td>
	<%} %>
	<td height="24" align="center" valign="middle" >总额(元)</td>
  </tr>
  
    <tr bgcolor="#f5f5f5">
    <td height="24" align="center" valign="middle" >合计:</td>
	 <% //显示已充值的游戏对应的总额
	       for(Iterator it2 = productList.entrySet().iterator();it2.hasNext();){
	    	   Map.Entry entry2 = (Map.Entry)it2.next();    
	    	   String productId = (String)entry2.getKey(); 
	    	   if(allSubscribeMap.get(productId) != null){
	    		   allTotalPrice += allSubscribeMap.get(productId);
	    	   } 
	    %>
		<td height="24" align="center" valign="middle"><%=allSubscribeMap.get(productId) %> </td>
		<%} %>
		<td height="24" align="center" valign="middle" ><%=allTotalPrice %></td>
  </tr>
  </tbody></table>

<table style="margin: 0pt auto; padding-top: 10px;"  bgcolor="#cccccc" border="0" cellpadding="3" cellspacing="1" width="98%">
  <tbody><tr bgcolor="#879999">
    <td colspan="9" height="24"><font color="#FFFFFF">&nbsp;<strong>游戏充值详细</strong></font></td>
  </tr>
  <tr bgcolor="#f5f5f5">
     <td height="24" align="center" valign="middle" >日期</td>
     
    <% //显示已充值的游戏名称
       for(Iterator it = productList.entrySet().iterator();it.hasNext();){
    	   Map.Entry entry = (Map.Entry)it.next();    
    	   String value = (String)entry.getValue();      	   
    %>
		<td height="24" align="center" valign="middle" ><%=value %>(元)</td>
	<%} %>
	<td height="24" align="center" valign="middle" >总额(元)</td>
	</tr>
   <%
   	Map<String,Integer> subscribeMap = (Map<String,Integer>)request.getAttribute("subscribeMap");
    List<String> timeList = (List<String>)request.getAttribute("timeList");
    for(Iterator it = timeList.iterator();it.hasNext();){
    	String time = (String)it.next();
   %>
    <tr onmouseover="this.style.background='#f5f5f5'" onmouseout="this.style.background='#ffffff';" bgcolor="#ffffff">
    <td height="24" align="center" valign="middle" >
	<%=time %>
	</td>
		 <% //显示已充值的游戏对应的金额
		   int totalPrice = 0;
	       for(Iterator it2 = productList.entrySet().iterator();it2.hasNext();){
	    	   Map.Entry entry2 = (Map.Entry)it2.next();    
	    	   String productId = (String)entry2.getKey();  
	    	   if(subscribeMap.get(time+productId) != null){
	    		   totalPrice += subscribeMap.get(time+productId);
	    	   } 
	    %>
		<td height="24" align="center" valign="middle" ><%=subscribeMap.get(time+productId)==null?0:subscribeMap.get(time+productId) %> </td>
		<%} %>
		<td height="24" align="center" valign="middle" ><%=totalPrice %></td>
	</tr>

  <% }%>
    <tr bgcolor="#f5f5f5">
    <td height="24" align="center" valign="middle" >合计:</td>
    
	 <% //显示已充值的游戏对应的总额
	       for(Iterator it2 = productList.entrySet().iterator();it2.hasNext();){
	    	   Map.Entry entry2 = (Map.Entry)it2.next();    
	    	   String productId = (String)entry2.getKey();    
	    %>
		<td height="24" align="center" valign="middle" ><%=allSubscribeMap.get(productId) %> </td>
		<%} %>
		<td height="24" align="center" valign="middle" ><%=allTotalPrice %></td>
  </tr>

</tbody></table>



</body></html>