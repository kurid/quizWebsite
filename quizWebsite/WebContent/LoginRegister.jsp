<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<%
	session.setAttribute("enterText", "Enter Username and Password:");
	session.setAttribute("registerText", "Registration Form:");
%>

<body>
		<table>
			<tr>
				<td> 
					<a href="Login.jsp" target="_top"> Login </a>
				</td>
				<td> 
					<a href="Registration.jsp" target="_top"> Register</a>
				</td>
			</tr>
			<tr>
				<td> 
					<% if((Boolean)session.getAttribute("isLoggedIn")){
						out.println("<a href=\"StartCreatingQuiz\" target =\"AccountWindow\"> Create Quiz! </a>");
					}else{
						out.println("<a href=\"StartCreatingQuiz\" target =\"_top\"> Create Quiz! </a>");
					}%>
					
					
				</td>
			</tr>
		</table>
</body>
</html>