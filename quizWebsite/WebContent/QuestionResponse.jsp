<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Question.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		
		int index = (Integer) session.getAttribute("qIndex");	
		List<Question> qList = (ArrayList<Question>) session.getAttribute("qList");
		String questionText = qList.get(index).getQuestionText();
		
	%>
		<h2 align="center"> Question <%=(index + 1) %> </h2>
		<p> <%=questionText %> </p>
		<br>
		<form action="TakeQuizServlet" method="post">
			<input type="text" name="field1">
			<input type="button" value="submit">	
		</form>
</body>
</html>