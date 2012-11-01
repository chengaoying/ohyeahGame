<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="cn.oyeah.util.*" %>    
<%@ page import="cn.oyeah.domain.*" %>
  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	int authority = (Integer)session.getAttribute("authority"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
<base href="<%=basePath %>">
<link href="css/admin_css.css" rel="stylesheet" type="text/css">
<script language="javascript1.2">
function showmenu(sid)
{
whichEl = eval("menuname" + sid);
if (whichEl.style.display == "none")
{
eval("menuname" + sid + ".style.display=\"\";");
}
else
{
eval("menuname" + sid + ".style.display=\"none\";");
}
}

function showsubmenu(sid)
{
whichEl = eval("submenu" + sid);
if (whichEl.style.display == "none")
{
eval("submenu" + sid + ".style.display=\"\";");
}
else
{
eval("submenu" + sid + ".style.display=\"none\";");
}
}
</script></head>

<body>
<table align="center" border="0" cellpadding="0" cellspacing="0" width="249">
  <tbody><tr>
    <td align="center" valign="middle" height="103" width="250" bgcolor="#7d7c7b" ><span style="font-size:24px; font-weight:bold; "> 欧耶游戏充值管理 </span></td>
  </tr>
</tbody></table>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody><tr>
    <td align="center" valign="top" width="100%"><table class="left_main_table" border="0" cellpadding="0" cellspacing="0" height="158" width="100%">
        <tbody><tr>
          <td align="center" bgcolor="#e1e1e1" valign="top" width="100%">
         
            <table align="center" cellpadding="0" cellspacing="0" width="99%">
              <tbody>
                <tr>
                  <td class="menu_title" id="menuTitle1" style="" onclick="showsubmenu(0)" onmouseover="this.className='menu_title2';" onmouseout="this.className='menu_title';" align="center" background="image/admin_left-titlebg.gif" height="25" valign="middle">
				  <table border="0" cellpadding="0" cellspacing="0" height="26" width="100%">
                      <tbody><tr  bgcolor="#879999">
                         <td align="center"  valign="middle" width="80" bgcolor="#879999"></td>
                        <td class="bai12" align="left"  valign="middle"><strong class="bai">管理员中心</strong></td>
                      </tr>
                    </tbody></table></td>
                </tr>
                <tr>
                  <td id="submenu0" align="center" valign="middle"><table class="sub_table" align="center" cellpadding="0" cellspacing="0" width="100%">
                      <tbody>
                    <%
                      if(authority > 1){
                     %>
                        <tr class="sub_table_tr">
                          <td class="sub_table_td" align="center" height="26" width="80"></td>
                          <td class="sub_table_td" align="left" height="26"><a href="web/admin/admin_add.jsp" target="mainFrame">添加管理员</a></td>
                        </tr>
                        <%} %>
                        <tr>
                          <td class="sub_table_td" align="center" height="26" width="80"></td>
                          <td class="sub_table_td" align="left" height="26"><a href="servlet/UserQueryList" target="mainFrame">管理员列表</a></td>
                        </tr>
                        <tr>
                          <td class="sub_table_td" align="center" height="26" width="80"></td>
                          <td class="sub_table_td" align="left" height="26" valign="middle"><a href="" target="_parent" onclick="javascript:return confirm('确实要退出管理吗?')">退出管理</a></td>
                        </tr>
                      </tbody>
                    </table></td>
                </tr>
              </tbody>
            </table>
            <table align="center" cellpadding="0" cellspacing="0" width="99%">
              <tbody>
                <tr>
                  <td class="menu_title" id="menuTitle1" style="" onclick="showsubmenu(3)" onmouseover="this.className='menu_title2';" onmouseout="this.className='menu_title';" align="center" background="image/admin_left-titlebg.gif" height="25" valign="middle"><table border="0" cellpadding="0" cellspacing="0" height="24" width="100%">
                      <tbody><tr  bgcolor="#879999">
                        <td align="center"  height="24" valign="middle" width="80" bgcolor="#879999"></td>
                        <td class="bai12" align="left"  valign="middle"><strong class="bai">IPTV游戏</strong></td>
                      </tr>
                    </tbody></table></td>
                </tr>
                <tr>
                  <td id="submenu3" align="center" valign="middle"><table class="sub_table" align="center" bgcolor="#dde3ec" cellpadding="0" cellspacing="0" width="100%">
                      <tbody>
                        <tr>
                          <td class="sub_table_td" align="center" height="26"></td>
                          <td class="sub_table_td" align="left" height="26" valign="middle"><a href="servlet/RechargeGameServlet" target="mainFrame">游戏充值统计</a></td>
                        </tr>
                      <tr>
                          <td class="sub_table_td" align="center" height="26" width="80"></td>
                          <td class="sub_table_td" align="left" height="26" valign="middle"><a href="servlet/PropPurchaseServlet?command=all&pageNo=1" target="mainFrame">道具购买统计</a></td>
                        </tr> 
                
                        <tr>
                          <td class="sub_table_td" align="center" height="26" width="80"></td>
                          <td class="sub_table_td" align="left" height="26" valign="middle"><a href="servlet/RealTimeStatisticServlet" target="mainFrame">实时统计</a></td>
                        </tr>  
                           <tr>
                          <td class="sub_table_td" align="center" height="26" width="80"></td>
                          <td class="sub_table_td" align="left" height="26" valign="middle"><a href="servlet/SubscribeStatisticServlet?pageNo=1&command=all" target="mainFrame">充值用户统计</a></td>
                        </tr>
            		  <tr>
                          <td class="sub_table_td" align="center" height="26" width="80"></td>
                          <td class="sub_table_td" align="left" height="26" valign="middle"><a href="web/userQuery/index.jsp" target="mainFrame">用户记录查询</a></td>
                        </tr>
                        
                        <tr>
                          <td class="sub_table_td" align="center" height="26" width="80"></td>
                          <td class="sub_table_td" align="left" height="26" valign="middle"><a href="servlet/UserDataServlet?pageNo=1&command=all" target="mainFrame">用户数据分析</a></td>
                        </tr>
                      </tbody>
                      <tbody>
                      </tbody>
                    </table>
                 
        </td></tr>
      </tbody></table>
                 
        </td></tr>
      </tbody></table>
      <div style="display:none"></div></td>
  </tr>
</tbody></table>



</body></html>