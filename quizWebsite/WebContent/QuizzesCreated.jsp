<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="quiz.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<ul>
	<%
		ArrayList<QuizDB> quizzes = (ArrayList<QuizDB>)request.getAttribute("quizList");
		for(QuizDB quiz: quizzes){
			String statement = "<li><a href=\"StartTackingQuiz?ID=" + quiz.getID() +
					"\" target =\"AccountWindow\"> " + quiz.getName() + " </a></li>";
			out.println(statement);
		}
	%>
	</ul>
</body>
</html>