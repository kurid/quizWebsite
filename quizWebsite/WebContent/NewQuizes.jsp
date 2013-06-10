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

	
	<ol>
		
		<%  
 			List <QuizDB> newQuizzes  = (List<QuizDB>)request.getServletContext().getAttribute("newQuizzes");
			for (QuizDB quiz: newQuizzes){
    			//out.print("<li><a href=\"StartTackingQuiz\" "+"target=\"AccountWindow\""+"?ID=\""); 
   	 			//out.println(quiz.getId() + "\">" + quiz.getName()+ "</a></li>");
   	 			//System.out.println(quiz.getId());
   	 			session.setAttribute("ID", quiz.getID());
   	 			out.println("<li><a href=\"StartTackingQuiz\" target =\"AccountWindow\"> " +quiz.getName()+ "</a></li>");
   			}
  		%> 
 	</ol>
</body>
</html>