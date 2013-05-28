<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% 
	String text = request.getParameter("text");
%>
<title> Enter </title>
</head>
<body>
	<!-- text -shi jer araferi ar weria shesabamisi teqtebi gadmoecema servletidan an homePage-dan  -->
	<h> <%= text %></h>
	<form action="Enter" method="post">
		<p>
		User Name: <input type="text" name="userName" />		
			<br /><br />
			Password: <input type="text" name="password" /> <input type="submit"
				value="Enter" />
		</p>
	
	</form>
</body>
</html>