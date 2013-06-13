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
	
	<table>
		<%
		List<String> names = (List<String>)request.getAttribute("names");
		int i = 0;
		for(Challenge challenge : challenges ){
			int sender = challenge.sender();
			String name = names.get(i);
			i++;
			out.println("<tr> <td> Challange From: "+ name + "</td></tr>");
			out.println("<tr> <td> Quiz : "+ challenge.getQuiz().getName() + "</td></tr>");
			out.println("<tr> <td> <input type=\"submit\"  name=\"YES\" value=\"Reject\"/> </td></tr>");
			out.println("<tr> <td> <input type=\"submit\" name=\"Reject\" value=\"Reject\" /> </td></tr>");
		}
		%>
	</table>
	


</body>
</html>