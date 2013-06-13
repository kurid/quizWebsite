<%@page import="quiz.DoneQuize"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2 align="center">Quizzes History</h2>

	<%
		List<DoneQuize> quizzesDone = (List<DoneQuize>) request
				.getAttribute("quizzesDone");
		for (int i=quizzesDone.size()-1;i>=0;i--) {
			DoneQuize doneQuiz = quizzesDone.get(i);
			out.println("<li><a href=\"StartTackingQuiz?ID="
					+ doneQuiz.getQuiz().getID()
					+ "\" target =\"AccountWindow\"> "
					+ doneQuiz.getQuiz().getName() + "</a> .  time : "
					+ doneQuiz.getTime() + "  date :  "
					+ doneQuiz.getQuizTakeDate() + " . score: "
					+ doneQuiz.getTotalScore() + " </li>");

		}
	%>
</body>
</html>