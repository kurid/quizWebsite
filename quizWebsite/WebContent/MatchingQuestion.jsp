<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Question.*" %>
<%@page import="quiz.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		
		int index = (Integer) session.getAttribute("qIndex");	
		List<Question> qList = (ArrayList<Question>) session.getAttribute("qList");
		MatchingQuestion MQ = (MatchingQuestion) qList.get(index);
		String questionText = MQ.getQuestionText();
		session.setAttribute("qIndex", index+1);
		List<List<String> > matchings = (List<List<String> >)MQ.getCorrectAnswer().getAnswer();
		
	%>
	<h2 align="center"> Question <%=(index + 1) %> </h2>
	<p> <%=questionText %> </p>
	<br>
	<form action="CheckSingleFieldAnswer" method="POST">
		
	</form>
</body>
</html>