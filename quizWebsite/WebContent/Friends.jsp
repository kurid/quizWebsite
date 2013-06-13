<%@page import="web.Account"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="quiz.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h2 align="center"> Friends </h2>
	<%
		List<Account> friendList = (List<Account>)request.getAttribute("friendList");
		for (Account acc : friendList) {
			out.println("<li><a href=\"AccountServlet?ID=" + acc.getId()
				+ "\" target =\"AccountWindow\"> " + acc.getNickname()
				+ "</a></li>");
		}
	%>

</body>
</html>