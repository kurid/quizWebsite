<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page import="quiz.QuizDB" %>
<title>Insert title here</title>
</head>
<%QuizDB quiz = (QuizDB)session.getAttribute("quizDB"); 
String name = quiz.getName();
String target = "AccountWindow";
if(!(Boolean)session.getAttribute("isLoggedIn")){
	target = "_top";
}
String description = quiz.getDescription();%>
<body>
	<p><%=name %></p>
	<p><%=description %></p>
	<form action="TakeQuizServlet" method = "post" target = <%=target %>>
		<input type="submit" name="Submit"  value="Start Quiz!"/>
	</form>
</body>
</html>