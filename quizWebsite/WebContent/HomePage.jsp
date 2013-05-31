<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Home Page</title>
<%
	request.getSession().setAttribute("RegisterText", "Registration");
	request.getSession().setAttribute("EnterText", "Enter");
%>
</head>
<body>
	<a href="http://localhost:8080/quizWebsite/enter.jsp"> Enter</a>
	<a href="http://localhost:8080/quizWebsite/Registration.jsp"> Registration </a>
</body>
</html>