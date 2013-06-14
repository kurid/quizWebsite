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
	
	<table align="center">
		<tr>
			<td width="20%"> <a href="UserProfile.jsp" target ="ActualInfo"> Profile </a> </td>
			<td width="20%"> <a href="GetFriendsServlet" target ="ActualInfo"> Friends </a> </td>
			<td width="20%"> <a href="QuizzesDone" target ="ActualInfo"> Quizzes Done </a> </td>
			<td width="20%"> <a href="QuizzesCreated" target = "ActualInfo"> Quizzes Created </a> </td>
		</tr>
	</table>
</body>
</html>