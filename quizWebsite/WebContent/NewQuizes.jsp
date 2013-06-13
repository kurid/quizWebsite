<%@page import="web.Account"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="quiz.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>

	<h3>New Quizzes</h3>
	<ol>

		<%
			List<QuizDB> newQuizzes = (List<QuizDB>) request
					.getServletContext().getAttribute("newQuizzes");
			for (QuizDB quiz : newQuizzes) {
				Account autor = new Account(quiz.getAuthor());
				out.println("<li><a href=\"StartTackingQuiz?ID=" + quiz.getID()
						+ "\" target =\"AccountWindow\"> " + quiz.getName()
						+ "</a> © "+autor.getName()+"</li>");
			}
		%>
	</ol>
</body>
</html>