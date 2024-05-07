<%@page import="guestbook.controller.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Boolean state = (Boolean)request.getAttribute("state");
%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="UTF-8">
		<title>guestbook_esult</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/JavaWeb/css/button.css">
</head>
<body style="padding: 15px">
	<form class="pure-form">
		<fieldset>
			<legend>guestbook_result</legend>
			<%=state?"新增成功":"新增失敗" %><p>
			<a href="/JavaWeb/guestbook" class="pure-button pure-button-primary">返回上頁</a>
		</fieldset>
	</form>
</body>
</html>