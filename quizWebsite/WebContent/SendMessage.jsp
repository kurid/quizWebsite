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
	<%Account receiver = (Account)session.getAttribute("userAccount"); %>
	<h4>you are sending message to <%=receiver.getNickname() %></h4>
	<form action="SendMessage" method = "post">
		<input type="text" name="messageText">
		<input type="submit" value="Submit"/>
	</form>
</body>
</html>