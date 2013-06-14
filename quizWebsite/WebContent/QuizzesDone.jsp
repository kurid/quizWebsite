<%@page import="quiz.DoneQuize"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<% List<DoneQuize> quizzesDone = (List<DoneQuize>) request.getAttribute("quizzesDone"); %>
</head>
<style>
	table, td, th{border:1px solid blue;}
	th{background-color:blue; color:white;}
</style>
</head>
<body>
	<h2 align="center">Quizzes History</h2>
	<table>
	<tr>
		<th>QUIZ NAME</th>
		<th>TIME</th>
		<th>DATE</th>
		<th>SCORE</th>
		</tr>
	<%
		for (int i=quizzesDone.size()-1;i>=0;i--) {
			DoneQuize doneQuiz = quizzesDone.get(i);
			out.println("<tr>");
			out.println("<td><a href=\"StartTackingQuiz?ID="+ doneQuiz.getQuiz().getID()
					+ "\" target =\"AccountWindow\"> "+ doneQuiz.getQuiz().getName() + "</a></td>");
		out.println("<td>"+ doneQuiz.getTime() + "</td>");  
		out.println("<td>" + doneQuiz.getQuizTakeDate()+ "</td>");
		out.println("<td>" + doneQuiz.getTotalScore()+ "</td>");
			out.println("<tr>");
		}
	%>
</body>
</html>