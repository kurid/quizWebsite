<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% String registerText = (String)session.getAttribute("registerText"); 
System.out.println(registerText);%>
<title> Registration </title>
</head>
<body>
	<h1> <%= registerText %></h1>
	<form action="RegisterServlet" method = "post">
	
		<table>
			<tr>
				<td> First name: </td>
				<td> <input type="text" name="name"> </td>
			</tr>
			<tr>
				<td> Surname: </td>
				<td> <input type="text" name="surname"> </td>
			</tr>
			<tr>
				<td> Nickname: </td>
				<td> <input type="text" name="nickname"> </td>
			</tr>
			<tr>
				<td> Password: </td>
				<td>  <input type="password" name="password"> </td>
			</tr>
			<tr>
				<td> mail: </td>
				<td> <input type="text" name="mail"> </td>
			</tr>
			
		</table>
		 
		<button type="submit">Register</button>
	</form>
</body>
</html>