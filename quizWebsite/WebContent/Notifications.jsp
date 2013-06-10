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
	<a href="MessageServlet"><%= messages %></a>
	<br>
	<a href="FriendRequestServlet"><%= requests %></a>
	<br>
	<a href="ChallengeServlet"><%= challanges %></a>
</body>
</html>