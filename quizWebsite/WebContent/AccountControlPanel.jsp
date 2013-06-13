<%@page import="web.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">a {text-decoration: none}</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		Account account  = (Account)session.getAttribute("account");
		String accountName = account.getNickname();
	%>
	<table>
		<tr>
			<td><a href="ShowHome" target="AccountWindow"> <%=accountName %> </a></td>
		</tr>
		<tr>
			<td><a href="StartCreatingQuiz" target="AccountWindow">
					Create Quiz! </a></td>
		</tr>
		<tr> 
			<td><a href="LogoutServlet" target="_top">  Logout</a></td>
		</tr>
	</table>
	<hr>
	<hr>

</body>
</html>