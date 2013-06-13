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
				<td> 
					<form action="AddFriendServlet" method="post">
						<input type="submit" value="Add Friend"/> 
					</form> 
				</td>
			</tr>
		</table>
</body>
</html>