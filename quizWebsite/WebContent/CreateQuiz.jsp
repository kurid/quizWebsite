<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<hr>
	<h2 align="center"> Quiz Creation </h2>
	<form action="CreateQuiz" method="post">
		<table align="center">
			<tr>
				<td> Enter quiz name: </td>
				<td> <input type="text" name="quizName"> </td>
			<tr>
			<tr>
				<td> Enter description: </td>
				<td> <input type="text" name="description"> </td>
			<tr>
			<tr>
				<td> Number Of Questions: </td>
				<td> <select name = "numberOfQuestions"> 
				<%
					for(int i=2;i<=15;i++){
						out.println("<option value=\""+i+"\">"+i+"</option>");
					}
				%> </select> </td>
				<td> <button type="submit">Start adding questions</button> </td>
			<tr>
		</table>
	</form>
</body>
</html>