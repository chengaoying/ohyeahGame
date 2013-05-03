<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="cn.oyeah.domain.*" %>
<%@ page import="cn.oyeah.util.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
int id = Integer.parseInt(request.getParameter("id"));
String name = request.getParameter("name");
int authority = Integer.parseInt(request.getParameter("authority"));
String role = new String (request.getParameter("role").getBytes("iso-8859-1"),"utf-8");
int providerId = Integer.parseInt(request.getParameter("providerId"));
//System.out.println("name:" + name);
//System.out.println("authority:" + authority);
//System.out.println("role:"+role);
%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
 <base href="<%=basePath%>">
<link href="css/admin_css.css" rel="stylesheet" type="text/css">
<script language="JavaScript">
	function checkreg(field)
	{
		if (field.name.value=="") {
			window.alert("名称不能为空");
			field.name.focus();		
			 return (false);
			}
		if (field.password.value=="") {
			window.alert("密码不能为空");
			field.password.focus();		
			 return (false);
			}
		if (field.password2.value=="") {
			window.alert("确认密码不能为空");
			field.password2.focus();		
			 return (false);
			}
		if (field.password.value != field.password2.value) {
			alert("密码不一致,请重新输入!");
			field.password2.focus();
			return (false);
			}
		if (field.providerId.value=="请选择产品提供者") {
			window.alert("请选择产品提供者");
			field.providerId.focus();		
			 return (false);
		}
		if (field.role.value=="请分配角色") {
			window.alert("请分配角色");
			field.role.focus();		
			 return (false);
		}
	}	
</script>	
</head>
<body>
<table style="margin: 0pt auto; padding-top: 10px;" height="101" bgcolor="#cccccc" border="0" cellpadding="3" cellspacing="1" width="98%">
  <form name="myform" action="servlet/UserUpdateServlet" method="post" onsubmit="return checkreg(this);">
    <tbody><tr>
      <td colspan="2" height="24" bgcolor="#879999"><font color="#FFFFFF">&nbsp;<strong>管理员修改</strong></font></td>
    </tr>
    <tr>
      <td height="24" align="right" bgcolor="#f8f8f8" valign="middle" width="13%">用户名：</td>
	       <td height="24" align="left" bgcolor="#f8f8f8" valign="middle" width="87%">
	       <input name="id" value="<%=id %>" type="hidden" />
	       <input name="name" id="name" size="20" value="<%=name %>" type="text"></td>
    </tr>
    <tr>
      <td height="24" align="right" bgcolor="#f8f8f8" valign="middle" width="13%">产品提供者:</td>
      <td height="24" align="left" bgcolor="#f8f8f8" valign="middle" width="87%">
           <select id="providerId" name="providerId">
	            <option value="请选择产品提供者" selected="selected">请选择产品提供者</option>
	             <%
	               List<ProductProvider> providers = DataDictionaryManager.getInstance().getAllProductProvider();
	               for(Iterator<ProductProvider> it = providers.iterator(); it.hasNext();){
	            	   ProductProvider pp = it.next();	 
	            	   String selectedStr= "";
	            	   if(providerId == pp.getProviderID()){
	            		   selectedStr = "selected";
	            	   }
	            %>
	            <option value="<%=pp.getProviderID() %>"<%=selectedStr %>><%=pp.getProviderName() %></option>
	            <%} %>
	            
	         </select>
      </td>
    </tr>
	     <tr>
      <td height="24" align="right" bgcolor="#f8f8f8" valign="middle" width="13%">角色:</td>
      <td height="24" align="left" bgcolor="#f8f8f8" valign="middle" width="87%">
           <select id="role" name="role">
	            <option value="请分配角色" >请分配角色</option>
	             <%
	               List<DataDictionary> roles = DataDictionaryManager.getInstance().getDictionaryByName("role");
	               for(Iterator<DataDictionary> it = roles.iterator(); it.hasNext();){
						DataDictionary dataDictionary = it.next();	 
						String selectedStr= "";
						if (dataDictionary.getValue().equals(role)) {
							selectedStr = "selected";
						}
	            %>
	            <option value="<%=dataDictionary.getValue() %>" <%=selectedStr %>><%=dataDictionary.getValue() %></option>
	            <%} %>
	         </select>
      </td>
    </tr><%--
    <tr>
      <td height="24" align="right" bgcolor="#f8f8f8" valign="middle" width="13%">权限:</td>
      <td height="24" align="left" bgcolor="#f8f8f8" valign="middle" width="87%">
           <select id="authority" name="authority">
	            <option value="请给权限值" >请给权限值</option>
	             <%
	           	   List<DataDictionary> authoritys = DataDictionaryManager.getInstance().getDictionaryByName("authority");
	               for(Iterator<DataDictionary> it = authoritys.iterator(); it.hasNext();){
						DataDictionary dataDictionary = it.next();	 
						String selectedStr= "";
						if (authority == Integer.parseInt(dataDictionary.getValue())) {
							selectedStr = "selected";
						}
	            %>
	            <option value="<%=dataDictionary.getValue() %>" <%=selectedStr %> ><%=dataDictionary.getValue() %></option>
	            <%} %>
	         </select>
      </td>
    </tr>
    --%><tr>
      <td height="24" align="right" bgcolor="#f8f8f8" valign="middle" width="13%">请输入密码：</td>
      <td height="24" align="left" bgcolor="#f8f8f8" valign="middle" width="87%">
      <input name="password" id="password" value="" size="20" type="password"></td>
    </tr>
    <tr>
      <td height="24" align="right" bgcolor="#f8f8f8" valign="middle" width="13%">请再次输入密码：</td>
      <td height="24" align="left" bgcolor="#f8f8f8" valign="middle" width="87%">
      <input name="password2" id="password2" value="" size="20" type="password">
      </td>
    </tr>
    <tr>
      <td height="24" bgcolor="#f8f8f8">&nbsp;</td>
        <td height="24" bgcolor="#f8f8f8">
       <input name="button" id="button" value="确认" type="submit">
       <input name="back" id="back" value="返回" onclick="javascript:window.history.go(-1);" type="button"></td>
    </tr>
  </form>
</tbody>
</table>
</body>
</html>
