<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Home Page</title>
<%
	session.setAttribute("registerText", "Please Register");
	session.setAttribute("enterText", "Enter");
%>

</head>
<body>
	<a href="enter.jsp"> Enter</a>
	<a href="Registration.jsp"> Registration </a>
	<a class = "disabled" href="StartCreatingQuiz"> QreateQuiz </a>
</body>
</html>