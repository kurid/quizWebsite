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
    			out.println("<li><a href=\"http://localhost:8080/quizWebsite/test1.jsp?ID="); 
   	 			out.println(quiz.getId() + "\">" + quiz.getName()+ "</a></li>");
   			}
  		%> 
 	</ol>
</body>
</html>