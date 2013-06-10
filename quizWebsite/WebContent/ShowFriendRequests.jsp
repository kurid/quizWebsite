<%@page import="web.Account"%>
<%@page import="web.MyDB"%>
<%@page import="web.FriendRequest"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Friend Request</title>
<% List<FriendRequest> friendRequests = (List<FriendRequest> ) request.getAttribute("friendRequests"); %>
</head>
<body>

	<table>
		<% for(FriendRequest friendRequest : friendRequests ){
		Account sender = friendRequest.sender();
		String name = sender.getNickname();
		out.println("<tr><td> Friend Request From: "+ name );
		out.println("<input type=\"submit\"  name=\"YES\" value=\"YES\"  /><td>");
		out.println("<td> <input type=\"submit\" name=\"NO\" value=\"NO\" /> </td> </tr>");
		}%>
	</table>
	

</body>
</html>