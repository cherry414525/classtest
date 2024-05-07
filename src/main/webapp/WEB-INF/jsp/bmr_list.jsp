<%@ page import="java.util.List" %>
<%@ page  import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
//java區
List<Map<String, Object>> bmrList=(List)request.getAttribute("bmrList");
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>bmr list</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/JavaWeb/css/button.css">
	</head>
	<body>
		<!-- BMR list:<%=bmrList %> -->
		<h2>BMR list:</h2>
		<table class="pure-table pure-table-bordered">
			<thead>
				<tr>
					<th>id</th><th>name</th><th>gender</th><th>age</th><th>height</th><th>weight</th><th>BMR</th><th>delete</th>
				</tr>
			</thead>
			<tbody>
				<% for(int i=0;i<bmrList.size();i++){ %>
				<tr>
					<% Map<String,Object> map=bmrList.get(i);%>
					<td><%=i %></td>
					<td><%=map.get("name") %></td>
					<td><%=map.get("gender") %></td>
					<td><%=map.get("age") %></td>
					<td><%=map.get("height") %></td>
					<td><%=map.get("weight") %></td>
					<td><%=map.get("bmr") %></td>
					<td><a href="/JavaWeb/servlet/bmr?deleteid=<%=i%>" class="button-error pure-button">Delete</a></td>
				</tr>
				<% } %>
			</tbody>
				
		</table>
		<p>
		<a href="/JavaWeb/bmr.html" class="pure-button pure-button-primary">回上一頁</a>
		
	</body>
</html>