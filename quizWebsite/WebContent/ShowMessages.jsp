<%@page import="web.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Massages</title>
<% List<Message> messages  = (List<Message>)request.getAttribute("messages");%>
</head>
<body>
	<% for(Message message : messages ){
		Account sender = message.sender();
		String name = sender.getNickname();
		out.println("From: "+ name + "</br> message: "+ message.getText());
	}%>

</body>
</html>