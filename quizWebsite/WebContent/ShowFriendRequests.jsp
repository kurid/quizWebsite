<%@page import="web.FriendRequest"%>
<%@page import="web.Account"%>
<%@page import="web.MyDB"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Friend Request</title>
<% 
	List<FriendRequest> friendRequests = (List<FriendRequest>) request.getAttribute("friendRequests");
%>
</head>
<body>
	
	<form action="FriendRequestResponse" method="post">
		<table>
		<% 
		List<String> names = (List<String>)request.getAttribute("names");
		int i = 0;
		for(FriendRequest friendRequest : friendRequests ){
			int sender = friendRequest.sender();
			String name = names.get(i);
			i++;
			out.println("<tr><td> Friend Request From: "+ name );
			out.println("<input type=\"submit\"  name=\"act\" value=\"Accept\"  /><td>");
			out.println("<input type=\"hidden\"  name=\"sender\" value=\"" + friendRequest.sender() +"\"");
			out.println("<td> <input type=\"submit\" name=\"act\" value=\"Reject\" /> </td> </tr>");
			
		}%>
		
		</table>
	</form>
	
	

</body>
</html>