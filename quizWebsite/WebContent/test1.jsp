<%@page import="java.util.List"%>
<%@page import="quiz.DoneQuize"%>
<%@page import="quiz.QuizDB"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="web.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a> <%
 	Integer text = (Integer)request.getAttribute("ID");
 		session.setAttribute("account", new Account(1));
 %></a>
	<h1> <%= text%> 
		dasatesti gverdi
		<a href="QuizzesHistory" > notifications </a>
		
		<%
					List<DoneQuize> quizzesDone = (List<DoneQuize>) request.getAttribute("quizzesDone");
						
					for(DoneQuize doneQuiz : quizzesDone){
						out.println("<p> "+ doneQuiz.getAccountID() +"  " + doneQuiz.getQuiz().getName() 
								+ "  " + doneQuiz.getTime() +"  " + doneQuiz.getQuizTakeDate() +" " + "</p>");
					}
				%> 
		
	</h1>
</body>
</html>