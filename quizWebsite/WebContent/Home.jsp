<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="web.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<%
			Account acc = (Account)session.getAttribute("account");
		%>
		<h2 align="center"> Home </h2>
		<table align="center" width="40%">
			<tr>
				<td> Account Nickname:  </td>
				<td><%=acc.getNickname() %></td>	
			</tr>
			<tr>
				<td> User Name: </td>
				<td><%=acc.getName() %></td>
			</tr>
			<tr>
				<td> User Surname: </td>
				<td><%=acc.getSurname() %></td>
			</tr>
		</table>
</body>
</html>