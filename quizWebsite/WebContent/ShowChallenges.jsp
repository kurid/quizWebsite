<%@page import="web.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Challenges</title>
<% List<Challenge> challenges = (List<Challenge>)request.getAttribute("challenges"); %>
</head>
<body>
	<% for(Challenge challenge : challenges ){
		Account sender = challenge.sender();
		String name = sender.getNickname();
		out.println("Challange From: "+ name + "</br>");
		out.println("Quiz : "+ challenge.getQuiz().getName() + "</br>");
		out.println("<input type=\"submit\"  name=\"YES\" value=\"YES\"  />");
		out.println("<td> <input type=\"submit\" name=\"NO\" value=\"NO\" /> </td>");
	}%>


</body>
</html>