<%@page import="web.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		Account account  = (Account)session.getAttribute("account");
		String accountName = account.getName();
	%>
	<table>
		<tr>
			<td><a href="AccountInfo.jsp" target="AccountWindow"> <%=accountName %> </a></td>
			<td><a href="LogoutServlet" target="AccountWindow">
					Logout</a></td>
		</tr>
		<tr>
			<td><a href="StartCreatingQuiz" target="AccountWindow">
					Create Quiz! </a></td>
		</tr>
	</table>


</body>
</html>