<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="CreateQuiz" method="post">
		Enter quiz name: <input type="text" name="quizName"><br> 
		Enter description: <input type="text" name="description"><br>
		Number Of Questions: <select name = "numberOfQuestions">
			<%
				for(int i=2;i<=15;i++){
					out.println("<option value=\""+i+"\">"+i+"</option>");
				}
			%>
		</select>
		<button type="submit">Start adding questions</button>
	</form>
</body>
</html>