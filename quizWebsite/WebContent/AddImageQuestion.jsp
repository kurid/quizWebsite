<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="AddImageQuestion" method = "post">
		Image URL :  <input type="text" name="imageUrl"><br> 
		QuestionText: <input type="text" name="questionText"><br> 
		Answer1: <input type="text" name="answer1"><br> 
		Answer2: <input type="text" name="answer2"><br> 
		Answer3: <input type="text" name="answer3"><br> 
		Answer4: <input type="text" name="answer4"><br>  
		<%@include file="ChooseScore.jsp" %>
		<button type="submit" name = "answersNum" value ="one">AddQuestion</button>
	</form>
</body>
</html>