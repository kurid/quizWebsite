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
<style>
	table, td, th{border:1px solid DarkBlue;}
	th{background-color:DarkBlue; color:white;}
</style>
<title>Insert title here</title>
</head>

<body>
<table>
	<tr> 
	<th>Popular Quizzes </th>
	</tr>

		<%
			List<QuizDB> popularQuizzes = (List<QuizDB>) request
					.getServletContext().getAttribute("popularQuizzes");
			for (QuizDB quiz : popularQuizzes) {
				out.println("<tr> ");
				Account autor = new Account(quiz.getAuthor());
				out.println("<td><a href=\"StartTackingQuiz?ID=" + quiz.getID()
						+ "\" target =\"AccountWindow\"> " + quiz.getName()
						+ "</a> © "+autor.getNickname()+"</td>");
				out.println(" </tr> ");
			}
		%>
</table>
</body>
</html>