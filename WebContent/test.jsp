<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">

var xmlHttp;

function createXMLHttpRequest() {
	//表示当前浏览器不是ie,如ns,firefox
	if(window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
}

function validate() {
	//alert(document.getElementById("userId").value);
	//alert(field.value);
		//创建Ajax核心对象XMLHttpRequest
		createXMLHttpRequest();
		
		var url = "http://192.168.16.70:8080/itvgame/point?ProductID=1000a&UserId=igsuper001&transactionid=000&Sign=111";
		alert(url)
		
		//设置请求方式为GET，设置请求的URL，设置为异步提交
		xmlHttp.open("GET", url, true);
		
		//将方法地址复制给onreadystatechange属性
		xmlHttp.onreadystatechange=callback;
		
		//将设置信息发送到Ajax引擎
		xmlHttp.send(null);
}

function callback() {
	alert(xmlHttp.readyState);
	//Ajax引擎状态为成功
	if (xmlHttp.readyState == 4) {
		//HTTP协议状态为成功
		if (xmlHttp.status == 200 || xmlHttp.status == 0) {
			alert(xmlHttp.responseText);
			if (trim(xmlHttp.responseText) != "") {
				document.getElementById("spanUserId").innerHTML = "<font color='red'>" + xmlHttp.responseText + "</font>"
				document.getElementById("name").focus();	
			}else {
				document.getElementById("spanUserId").innerHTML = "";
			}
		}else {
			alert("请求失败，错误码=" + xmlHttp.status);
		}
	}
}

</script>
<body>
<a href="" onclick="validate()">积分兑换</a>
</body>
</html>