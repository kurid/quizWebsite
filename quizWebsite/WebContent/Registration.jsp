<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="RegisterServlet" method = "post">
		First name: <input type="text" name="name"><br> 
		Surname: <input type="text" name="surname"><br> 
		Nickname: <input type="text" name="nickname"><br> 
		Password: <input type="password" name="password"><br> 
		mail: <input type="text" name="mail"><br> 
		<button type="submit">Register</button>
	</form>
</body>
</html>