<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<%String errotText =(String)session.getAttribute("errorText");%>
<h4><%=errotText %></h4>
<body>
	<form action="AddMultipleChoiceQuestion" method="post">
		Question : <input type="text" name="questionText"><br>
		First choice : <input type="text" name="1">
		<input type="radio" name="choice" value="1"><br>
		Second choice: <input type="text" name="2">
		<input type="radio" name="choice" value="2"><br>
		Third choice: <input type="text" name="3">
		<input type="radio" name="choice" value="3"><br>
		Fourth choice: <input type="text" name="4">
		<input type="radio" name="choice" value="4"><br>
		Fifth choice: <input type="text" name="5">
		<input type="radio" name="choice" value="5"><br>
		<%@include file="ChooseScore.jsp" %>
		<input type="submit" value="submit" ><br>
	</form>
</body>
</html>