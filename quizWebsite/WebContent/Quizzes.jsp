<%@page import="quiz.*"%>
<%@page import="web.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<% List<QuizDB> searchedQuizzes = (List<QuizDB>)request.getAttribute("searchedQuizzes"); %>

</head>
<body>
	
	<form action="SearchQuiz" method=POST>
	<table>
		<tr>
			<td> Enter quiz name: </td>
			<td> <input type="text" name="quizzName"/> </td>
			<td> <input type="submit" name="search" value="Search"/> </td> 
		</tr>
	</table>
	<% 
	if(searchedQuizzes != null){
				for(QuizDB quiz : searchedQuizzes){
					System.out.println(quiz.getName());
				out.println("<li><a href=\"http://localhost:8080/quizWebsite/test1.jsp?ID="); 
				out.println(quiz.getId() + "\">" + quiz.getName()+ "</a></li>");
			}
		}%>
	
	</form>

</body>
</html>