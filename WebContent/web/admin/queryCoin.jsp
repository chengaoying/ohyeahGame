<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="cn.oyeah.util.*"%>
<%@ page import="cn.oyeah.domain.*"%>
<%@ page import="java.util.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Map<String, Integer> map = (Map<String, Integer>)request.getAttribute("map");
	Map<String, Integer> rows = (Map<String, Integer>)request.getAttribute("rows");
	Map<String, String> columns = (Map<String, String>)request.getAttribute("columns");
	int amount = Integer.parseInt(String.valueOf(request.getAttribute("amount")));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
<base href="<%=basePath%>">
<script language="javascript" type="text/javascript"
	src="My97DatePicker/WdatePicker.js"></script>
<link href="css/admin_css.css" rel="stylesheet" type="text/css">
</head>
<body>

	<table style="margin: 0pt auto; padding-top: 10px;" bgcolor="#cccccc" 
		border="0" cellpadding="3" cellspacing="1" width="98%">
		<tbody>
			<tr bgcolor="#879999">
				<td colspan="9" height="24"><font color="#FFFFFF">&nbsp;<strong>用户余额查询，单位：游戏币(0.1元)</strong></font></td>
			</tr>

			<tr bgcolor="#f5f5f5">
				<td height="24" align="center" valign="middle">用户ID</td>
				<% 
					for(Iterator it = columns.entrySet().iterator();it.hasNext();){
						Map.Entry entry = (Map.Entry)it.next();    
				    	String value = (String)entry.getValue();     
				%>
				<td align="center" valign="middle"><%=value %></td>
				<% 
					}
				%>
			</tr>

			<% 
				for(Iterator it = rows.entrySet().iterator();it.hasNext();){
					Map.Entry entry = (Map.Entry)it.next();
					String userId = (String)entry.getKey();
					int accountId = (Integer)entry.getValue();
			%>
			<tr bgcolor="#f5f5f5">
				<td height="24" align="center" valign="middle"><%=userId %></td>

				<% 
					for(Iterator it2 = columns.entrySet().iterator();it2.hasNext();){
						Map.Entry entry2 = (Map.Entry)it2.next();    
				    	String productId = (String)entry2.getKey();     
				%>
				<td align="center" valign="middle"><%=map.get(accountId+productId)==null?0:map.get(accountId+productId)  %></td>
				<% 
					}
				%>
			</tr>
			<% 
				}
			%>

		</tbody>
	</table>
	<table style="margin: 0pt auto; padding-top: 1px;" bgcolor="#cccccc"
		border="0" cellpadding="3" cellspacing="1" width="98%">
		<tbody>
			<tr bgcolor="#f5f5f5">
				<td height="24" width="50%" align="center" valign="middle">总额</td>
				<td height="24" width="50%" align="center" valign="middle"><%=amount %></td>
			</tr>
		</tbody>
	</table>
</body>
</html>