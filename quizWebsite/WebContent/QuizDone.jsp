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
			String quizName = (String) request.getAttribute("quizName");
			int score = (Integer) request.getAttribute("score");
			int maxScore = (Integer) request.getAttribute("fullScore");
			long time = (Long) request.getAttribute("quizTimeInSeconds");
		%>
		
		<h2 align="center">  You Completed <%=quizName %>!</h2>
		<p align="center"> For completing this quiz you spent <%=time %> seconds! </p>
		<p align="center"> You had <%=score %> points out of <%=maxScore %>! </p>
		<p align="center"> We hope you enjoyed our quiz! </p>
</body>
</html>