<%@page import="web.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Massages</title>
<%
	List<Message> messages = (List<Message>) request
			.getAttribute("messages");
%>
</head>
<body>
	<table>
		<%
			List<String> names = (List<String>) request.getAttribute("names");
			//int i = 0;
			for (int i=messages.size()-1;i>=0;i--) {
				Message message = messages.get(i);
				int sender = message.sender();
				String name = names.get(i);
				
				if (message.isUnread()) {
					out.println("<tr><td><b> From: " + name + "</br> message: "
							+ message.getText() + "</b><td><tr> ");
				}else{
					out.println("<tr><td> From: " + name + "</br> message: "
							+ message.getText() + "<td><tr> ");
				}
			}
		%>
	</table>
</body>
</html>