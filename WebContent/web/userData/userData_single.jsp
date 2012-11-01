<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="cn.oyeah.util.*" %>
<%@ page import="cn.oyeah.domain.*" %>
<%@ page import="java.util.*" %>    

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String sTime = (String)request.getAttribute("sTime");
	String eTime = (String)request.getAttribute("eTime");
	String command = (String)request.getAttribute("command");
 	List<Product> productList = (List<Product>)request.getAttribute("productList");
 	PageModel<UserDataAnalysis> pageModel = (PageModel<UserDataAnalysis>)request.getAttribute("pageModel");
	//System.out.println(basePath);
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

<table style="margin: 0pt auto; padding-top: 10px;" height="101" bgcolor="#cccccc" border="0" cellpadding="3" cellspacing="1" width="98%">
  <tbody><tr bgcolor="#879999">
    <td colspan="9" height="24"><font color="#FFFFFF">&nbsp;<strong>用户充值数据</strong></font></td>
  </tr>
   <tr bgcolor="#879999">
    <td colspan="9" height="24">
      <form action="servlet/UserDataServlet?pageNo=1" method="post" onsubmit="return check()" >
      		<input type="hidden" name="command" id="command" value="single" />
	         <select name="productId" id="game">
	            <option value="请选择查询的游戏" selected="selected">选择查询的游戏</option>
	             <%
	               int productId = 0;
	               for(Iterator<Product> it = productList.iterator(); it.hasNext();){
	            	   Product product = it.next();	 
						String selectedStr= "";
						if(command.equals("single")){ 
			           	   productId = Integer.parseInt(request.getAttribute("productId").toString());
			           	   if(productId == product.getProductId()){
			           		selectedStr = "selected";
			           	   }
			           	 }
	            %>
	            <option value="<%=product.getProductId() %>" <%=selectedStr %> ><%=product.getProductName() %></option>
	            <%} %>
	         </select>
        
          &nbsp;&nbsp; 
		    开始时间:<input type=text name="startTime" value="<%=sTime %>" onClick="WdatePicker()"/>
		    结束时间:<input type=text name="endTime" value="<%=eTime %>" onClick="WdatePicker()"/>
		    <input type="submit" value="查询"/>
       </form>
    </td>
	</tr>
  
  <tr bgcolor="#f5f5f5" >
    <td height="24" align="center" valign="middle" width="30%">用户ID</td>
    <td height="24" align="center" valign="middle" width="30%">游戏名称</td>
	<td align="center" valign="middle" width="30%">充值总金额(元)</td>

  </tr>
	<%
	  for(Iterator<UserDataAnalysis> it=pageModel.getList().iterator();it.hasNext();){
		  UserDataAnalysis userDataAnalysis = it.next();
	%>
    <tr bgcolor="#f5f5f5">
    <td height="24" align="center" valign="middle" width="30%" ><a id="id" href="servlet/UserRecordServlet?pageNo=1&subscribeId=subscribe&userId=<%=userDataAnalysis.getUserId() %>"><%=userDataAnalysis.getUserId() %></a></td>
    <td height="24" align="center" valign="middle" width="30%"><%=userDataAnalysis.getGameName() %></td>
	<td align="center" valign="middle" width="30%" ><%=userDataAnalysis.getPrice() %></td>

  </tr>
  <%} %>
   <tr bgcolor="#f5f5f5">
    <td height="24" align="center" valign="middle" width="30%">合计</td>
    <td height="24" align="center" valign="middle" width="30%"></td>
	<td align="center" valign="middle" width="30%"><%=pageModel.getTotalPrice() %></td>

  </tr>

  <tr>
    <td colspan="14" height="24" bgcolor="#F8FCF6"><div class="page"><span><strong><%=pageModel.getPageNo() %>/<%=pageModel.getTotalPages()%></strong></span>
    <a href="servlet/UserDataServlet?command=<%=command %>&pageNo=<%=pageModel.getPreviousPageNo() %>&startTime=<%=sTime %>&endTime=<%=eTime %>&productId=<%=productId %>">
    <img src="image/pre.gif" height="16" width="21"/></a>&nbsp;
    <a href="servlet/UserDataServlet?command=<%=command %>&pageNo=<%=pageModel.getNextPageNo() %>&startTime=<%=sTime %>&endTime=<%=eTime %>&productId=<%=productId %>">
    <img src="image/next.gif" height="16" width="21"/></a></div></td>
	</tr>
</tbody></table>


</body></html>