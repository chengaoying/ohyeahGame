<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>    
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
	//System.out.println(basePath);
	String time = (String)request.getAttribute("time");
	//游戏某时段充值金额
	Map<String,Integer> countMap = (Map<String,Integer>)request.getAttribute("countMap");
	//游戏某时段充值的总额
	Map<String,Integer> allCountMap = (Map<String,Integer>)request.getAttribute("allCountMap");
	//某时段充值游戏名
	Map<String,String> nameMap = (Map<String,String>)request.getAttribute("nameMap");
%>
<%!
  private  boolean isShow(int i,Map<String,String> nameMap, Map<String,Integer> countMap){
	Iterator nameIt = nameMap.entrySet().iterator();
	  while(nameIt.hasNext()){
   	 	 Map.Entry nameEntry = (Map.Entry)nameIt.next();    
   	   	 String productId = (String)nameEntry.getKey();
   	   	 if(countMap.get(i+productId) != null && countMap.get(i+productId) != 0 ){
   	   		 return true;
   	   	 }
 	  }
	return false;
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
<style type="text/css">

</style>
<base href="<%=basePath %>">
<link href="css/admin_css.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<table style="margin: 0pt auto; padding-top: 10px;" height="101" bgcolor="#cccccc" border="0" cellpadding="3" cellspacing="1" width="98%">
  <tbody><tr bgcolor="#879999">
    <td colspan="9" height="24"><font color="#FFFFFF">&nbsp;<strong><%=time %>&nbsp;游戏充值实时查询（注意：统计后台正在升级，由于上海电信统一货币，充值统计截至2013年6月20号消费单位为元，21号之后消费的单位为TV币。1元=10TV币） </strong></font>
    </td>
  </tr>
  <tr bgcolor="#879999">
    <td colspan="9" height="24">
    	<form action="servlet/RealTimeStatisticServlet" method="post">
		         &nbsp;&nbsp; 日期查询:<input type=text name="time" value="<%=time %>" onClick="WdatePicker()"/>
		    <input type="submit" value="查询"/>
       </form>
    </td>
  </tr>
  
  <tr bgcolor="#f5f5f5">
    <td height="24" align="center" valign="middle" >实时统计</td>
    
     <% //显示已充值的游戏名称
     if(nameMap.size() > 0){
       for(Iterator it = nameMap.entrySet().iterator();it.hasNext();){
    	   Map.Entry entry = (Map.Entry)it.next();    
    	   String value = (String)entry.getValue(); 
    	  
    %>
		<td align="center" valign="middle" ><%=value %>(元)</td>
		<%} %>
			
	<% }  else{	%>
        <td align="center" valign="middle" >无记录</td>
     <%} %>   
  </tr>

   <tr bgcolor="#f5f5f5">
    <td height="24" align="center" valign="middle" >合计</td>
     <% //显示已充值的游戏对应的总额
       if(nameMap.size() > 0){
	       for(Iterator it2 = nameMap.entrySet().iterator();it2.hasNext();){
	    	   Map.Entry entry2 = (Map.Entry)it2.next();    
	    	   String productId = (String)entry2.getKey();    
	    %>
		<td align="center" valign="middle" ><%=allCountMap.get(productId) %> </td>
		<%} %>
	<% }  else{	%>
        <td align="center" valign="middle" ></td>
     <%} %> 

    </tr>
    
  </tbody></table>

<table style="margin: 0pt auto; padding-top: 10px;" height="101" bgcolor="#cccccc" border="0" cellpadding="3" cellspacing="1" width="98%">
  <tbody><tr bgcolor="#879999">
    <td colspan="9" height="24"><font color="#FFFFFF">&nbsp;<strong>分时段分析</strong></font></td>
  </tr>

  <tr bgcolor="#f5f5f5">
    <td height="24" align="center" valign="middle" >充值时间段</td>
   <% //显示已充值的游戏名称
     if(nameMap.size() > 0){
       for(Iterator it = nameMap.entrySet().iterator();it.hasNext();){
    	   Map.Entry entry = (Map.Entry)it.next();    
    	   String value = (String)entry.getValue(); 
    	  
    %>
		<td align="center" valign="middle" ><%=value %>(元)</td>
		<%} %>
			
	<% }  else{	%>
        <td align="center" valign="middle" >无记录</td>
     <%} %> 
  </tr>

    <%
    
       for(int i = 0; i <= 23;i++){
    	   if(nameMap.size() > 0){
    		   if(isShow(i, nameMap, countMap)){
    	    	   
    %>
    <tr onmouseover="this.style.background='#f5f5f5'" onmouseout="this.style.background='#ffffff';" bgcolor="#f5f5f5">
    <td height="24" align="center" valign="middle" ><%=i %></td>
    	<%
    	 for(Iterator it2 = nameMap.entrySet().iterator();it2.hasNext();){
	    	   Map.Entry entry2 = (Map.Entry)it2.next();    
	    	   String productId = (String)entry2.getKey();
	    	   int count = 0;
	    	   if(countMap.get(i+productId)==null){
	    		   count = 0;
	    	   } else{
	    	       count = countMap.get(i+productId);
	    	   } 
	    	%>
			<td align="center" valign="middle" >
			<%
			  if (count != 0){
			%>
				<strong><%=count %></strong>
			
			 <%} else{ %>
			 	<font color="#999999">0</font>
			 <%} %>
				</td>
		<%} %>
    </tr>
		<% 
	    	   
	    	 } 
	       }
         }  
      %>
	
    <tr bgcolor="#f5f5f5">
    <td height="24" align="center" valign="middle" >合计</td>
     <% //显示已充值的游戏对应的总额
     if(nameMap.size() > 0){
	       for(Iterator it2 = nameMap.entrySet().iterator();it2.hasNext();){
	    	   Map.Entry entry2 = (Map.Entry)it2.next();    
	    	   String productId = (String)entry2.getKey();    
	    %>
		<td align="center" valign="middle" ><b><%=allCountMap.get(productId) %></b> </td>
		
		<%} %>
	<% }  else{	%>
	        <td align="center" valign="middle" ></td>
	 <%} %> 	

    </tr>

</tbody></table>

</body></html>