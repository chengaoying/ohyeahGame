<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>   
<%@ page import="cn.oyeah.util.*" %>
<%@ page import="cn.oyeah.domain.*" %>
<%@ page import="java.util.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
	//System.out.println(basePath);
	int authority = (Integer)session.getAttribute("authority");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
<base href="<%=basePath %>">
<link href="css/admin_css.css" rel="stylesheet" type="text/css">
</head>
<body>
<table style="margin: 0pt auto; padding-top: 10px;" height="101" bgcolor="#cccccc" border="0" cellpadding="3" cellspacing="1" width="98%">
  <tbody><tr>
    <td colspan="7" height="22" bgcolor="#879999"><font color="#FFFFFF">&nbsp;<strong>管理员列表</strong></font></td>
  </tr>
  <tr>
    <td height="24" align="center" bgcolor="#f8f8f8" valign="middle" width="4%">ID</td>
    <td height="24" align="center" bgcolor="#f8f8f8" valign="middle" width="23%">用户名</td>
    <td height="24" align="center" bgcolor="#f8f8f8" valign="middle" width="23%">角色</td>
    <td align="center" bgcolor="#f8f8f8" valign="middle" width="20%">最后登录时间</td>
    
    <td height="24" align="center" bgcolor="#f8f8f8" valign="middle" width="19%">添加时间</td>
    <td align="center" bgcolor="#f8f8f8" valign="middle" width="8%">操作</td>
  </tr>
  <%--
  <c:forEach var="user" items="${pageModel.list }">
	    <tr style="background: none repeat scroll 0% 0% rgb(235, 242, 249);" onmouseover="this.style.background='#D7E4F7'" onmouseout="this.style.background='#EBF2F9';" bgcolor="#EBF2F9">
		    <td height="24" align="center" bgcolor="#f8f8f8" valign="middle" width="4%">${user.id }</td>
		    <td height="24" align="center" bgcolor="#f8f8f8" valign="middle"><a href="">${user.name }</a></td>
		    
		    <td height="24" align="center" bgcolor="#f8f8f8" valign="middle">${user.loginTime }</td>
		    
		    <td height="24" align="center" bgcolor="#f8f8f8" valign="middle">${user.createTime }</td>
		    <td height="24" align="center" bgcolor="#f8f8f8" valign="middle"><a href=""><img src="image/edit.gif" border="0"></a>&nbsp;<a href="" onclick="javascript:return confirm('确实要删除吗?')"><img src="image/del.gif" border="0"></a></td>
	    </tr>
  </c:forEach>
    --%>
    <%
     PageModel<User> pageModel = (PageModel<User>)request.getAttribute("pageModel");
     List list = pageModel.getList();
     User user = null;
     for(Iterator it = list.iterator();it.hasNext();){
    	 user = (User)it.next();
     
    %>
      <tr style="background: none repeat scroll 0% 0% rgb(235, 242, 249);" onmouseover="this.style.background='#D7E4F7'" onmouseout="this.style.background='#EBF2F9';" bgcolor="#EBF2F9">
		    <td height="24" align="center" bgcolor="#f8f8f8" valign="middle" width="4%"><%=user.getId() %></td>
		    <td height="24" align="center" bgcolor="#f8f8f8" valign="middle"><%=user.getName() %></td>
		    <td height="24" align="center" bgcolor="#f8f8f8" valign="middle"><%=user.getRole() %></td>
		    <td height="24" align="center" bgcolor="#f8f8f8" valign="middle"><%=user.getLoginTime()== null?"":user.getLoginTime() %></td>
		    
		    <td height="24" align="center" bgcolor="#f8f8f8" valign="middle"><%=user.getCreateTime() %></td>
		    <td height="24" align="center" bgcolor="#f8f8f8" valign="middle">
		      <%
		        if(authority > 1){
		       %>
		        <a href="web/admin/admin_update.jsp?id=<%=user.getId() %>&name=<%= user.getName()%>&authority=<%=user.getAuthority() %>&role=<%=user.getRole() %>"><img src="image/edit.gif" border="0"/></a>&nbsp;
					<a href="servlet/UserDeleteServlet?id=<%=user.getId() %>" onclick="javascript:return confirm('确实要删除吗?')"><img src="image/del.gif" border="0"/></a>
			  	<% }%>   
		    </td>
	    </tr>
	   <% } %>
    <tr>
    <td colspan="7" height="24" align="center" bgcolor="#F8FCF6" valign="middle"><div class="page"><span><strong><%=pageModel.getPageNo() %>/<%=pageModel.getTotalPages() %></strong></span>
      <a href="servlet/UserQueryList?pageNo=<%=pageModel.getPreviousPageNo() %>"><img src="image/pre.gif" height="16" width="21"/></a>&nbsp;<a href="servlet/UserQueryList?pageNo=<%=pageModel.getNextPageNo() %>"><img src="image/next.gif" height="16" width="21"/></a></div></td>
  </tr>
</tbody></table>


</body></html>