<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title> Registration </title>
</head>
<body>

<% 
	String errorText = (String) session.getAttribute("registerText");
	if(errorText == "") errorText = "Registration Form:";
%>
	<form action="RegisterServlet" method = "post">
	
		<h2 align="center"> <%=errorText %>	</h2>
		<table align="center">
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
				<td> Mail: </td>
				<td> <input type="text" name="mail"> </td>
			</tr>
			<tr> 
				<td> <input type="submit" name="Submit" value="Submit"/> </td>
				<td> <input type="reset" name="Reset" value="Reset form"/> </td>
			</tr>
		</table>
	</form>
	<p align="center"> <a href="HomePage.jsp" target="_top" > HomePage </a> </p>
</body>
</html>