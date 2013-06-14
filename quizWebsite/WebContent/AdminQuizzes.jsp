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
<%
	List<QuizDB> searchedQuizzes = (List<QuizDB>) request
			.getAttribute("searchedQuizzes");
%>

</head>
<body>
	<h2 align="center">Quizzes</h2>
	<form action="SearchQuiz" method="POST">
		<table align="center">
			<tr>
				<td>Enter quiz name:</td>
				<td><input type="text" name="quizzName" /></td>
				<td><input type="submit" name="search" value="Search" /></td>
			</tr>
		</table>
	</form>
	<table align="center">
		<%
			if (searchedQuizzes != null) {
				for (QuizDB quiz : searchedQuizzes) {
					out.println("<tr><td>"
							+ "<form action=\"DeleteQuiz\" method=\"post\"> "
							+ quiz.getName()
							+ "<input type = \"submit\" value = \"delete\" /> <input type=\"hidden\" name=\"name\" value=\""
							+ quiz.getID() + "\"> </form></td></tr>");

				}
			}
		%>
	</table>



</body>
</html>