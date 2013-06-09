<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% 
	String welcomeText = (String)session.getAttribute("enterText");
%>
<title> Enter </title>
</head>
<body>
	<!-- text -shi jer araferi ar weria shesabamisi teqtebi gadmoecema servletidan an homePage-dan  -->
	<h2 align="center"> <%= welcomeText %></h2>
	
	<form action="Enter" method="POST" >
      <table align="center">
			<tr>
				<td> Nickname: </td>
				<td> <input type="text" name="nickname" /> </td>
			</tr>
			<tr>
				<td> Password: </td>
				<td> <input type="password" name="password"/> </td>
			</tr>
			<tr> 
				<td> <input type="submit" name="Submit" value="Submit" /> </td>
				<td> <input type="reset" name="Reset" value="Reset form" /> </td>
			</tr>
			
		</table>
    </form>
</body>
</html>