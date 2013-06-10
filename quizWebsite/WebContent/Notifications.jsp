<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Notifications</title>
</head>
<body>
	<% 
		int[] notifications = (int[])request.getAttribute("notifications"); 
		String messages = "New messages(" + notifications[0]+ ")";
		String requests = "New friend requests(" + notifications[1] + ")";
		String challanges = "New challenges( " + notifications[2] + ")";
	%>
	<h2 align="center">Notifications</h2>
	<table align="center">
		<tr><td> <a href="MessageServlet"><%= messages %></a> </td> </tr>
		<tr><td> <a href="FriendRequestServlet"><%= requests %></a> </td></tr>
		<tr><td> <a href="ChallengeServlet"><%= challanges %></a> </td></tr>
	</table>
	
<body>
</html>