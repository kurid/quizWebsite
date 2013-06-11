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

	<h3>Popular Quizzes</h3>
	<ol>

		<%
			List<QuizDB> popularQuizzes = (List<QuizDB>) request
					.getServletContext().getAttribute("popularQuizzes");
			for (QuizDB quiz : popularQuizzes) {
				out.println("<li><a href=\"StartTackingQuiz?ID=" + quiz.getID()
						+ "\" target =\"AccountWindow\"> " + quiz.getName()
						+ "</a></li>");
			}
		%>
	</ol>
</body>
</html>