<%@page import="Question.Finals"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Choose Score:
	<select name="Score">
		<%
			for (int i = Finals.lowestScore; i <= Finals.highestScore; i++) {
				out.println("<option value=\"" + i + "\">" + i + "</option>");
			}
		%>
	</select>
</body>
</html>