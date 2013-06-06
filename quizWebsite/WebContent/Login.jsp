<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	p {color:grey}
</style>


</head>
<body>
	<jsp:include page="Header.jsp">

	<form action="LoginServlet" method="POST" >
      <p> enter your username and password: </p>
      <p> username:  <input type="text" name="username" /> </p>
      <p> password:  <input type="password" name="password" /> </p>
      <p>
        <input type="submit" name="Submit" value="Submit name" />
        <input type="reset" name="Reset" value="Reset form" />
      </p>
    </form>

</body>
</html>