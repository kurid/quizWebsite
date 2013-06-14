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
	<%
		boolean isFriend = (Boolean)session.getAttribute("isFriend");
		String statement;
		if(isFriend){
			statement = "<td> <a href=\"GetFriendsServlet\" target =\"ActualInfo\"> Friends </a> </td>"
					+ "<td> <a href=\"QuizzesDone\" target =\"ActualInfo\"> Quizzes Done </a> </td>" 
					+ "<td> <a href=\"QuizzesCreated\" target = \"ActualInfo\"> Quizzes Created </a> </td>"
					+ "<td> <a href=\"SendMessage.jsp\" target = \"ActualInfo\"> Send Message </a> </td>";
		}else statement = ""; 
	%>
	<table align="center" cellspacing="20">
		<tr>
			<td> <a href="UserProfile.jsp" target ="ActualInfo"> Profile </a> </td>
			<%=statement %>
		</tr>
	</table>
</body>
</html>