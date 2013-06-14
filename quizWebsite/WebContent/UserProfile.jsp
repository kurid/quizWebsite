<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="web.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		Account userAcc = (Account)session.getAttribute("userAccount");
		Boolean isFriend = (Boolean)session.getAttribute("isFriend");
		String statement = new String();
		if(!isFriend){
			if((Boolean)session.getAttribute("counterFriendRequestExists")){
				statement = "<tr><td> <form action=\"FriendRequestResponse\" method=\"post\">" 
						+ "<input type=\"submit\" name=\"act\" value=\"Accept\"/>" + "</form> </td></tr>"
					+ "<tr><td> <form action=\"FriendRequestResponse\" method=\"post\">" 
						+ "<input type=\"submit\" name=\"act\" value=\"Reject\"/>" + "</form> </td></tr>";
			}else if((Boolean)session.getAttribute("friendRequestExists")){
				statement = "<tr><td> Friend request already sent, waiting for response... </td></tr>";
			}else statement = "<tr><td> <form action=\"SendFriendRequest\" method=\"post\">" 
				+"<input type=\"submit\" value=\"AddFriend\"/>" + "</form> </td></tr>";
		}else{
			statement = "<tr><td> <form action=\"DeleteFriend\" method=\"post\">" 
					+"<input type=\"submit\" value=\"Delete Friend\"/>" + "</form> </td></tr>";
		}
		String achievements = (String)session.getAttribute("achievement");
	%>	
	<h2 align="center"> <%=userAcc.getNickname() %> </h2>
		<table align="center" width="40%">
			<tr>
				<td> Account Nickname:  </td>
				<td><%=userAcc.getNickname() %></td>	
			</tr>
			<tr>
				<td> User Name: </td>
				<td><%=userAcc.getName() %></td>
			</tr>
			<tr>
				<td> User Surname: </td>
				<td><%=userAcc.getSurname() %></td>
			</tr>
			<tr>
				<td> achievements: </td>
				<td> <%=achievements %> </td>
			</tr>
			<%=statement %>
		</table>
</body>
</html>