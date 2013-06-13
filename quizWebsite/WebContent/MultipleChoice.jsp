<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Question.*"%>
<%@page import="quiz.*"%>
<%@page import="java.util.HashSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		int index = (Integer) session.getAttribute("qIndex");
		List<Question> qList = (ArrayList<Question>) session
				.getAttribute("qList");
		MultipleChoiceQuestion question = (MultipleChoiceQuestion) qList
				.get(index);
		HashSet<String> pAnswers = (HashSet<String>) question
				.getPossibleAnswers();
		String questionText = question.getQuestionText();
		session.setAttribute("qIndex", index + 1);
	%>
	<h2 align="center">
		Question
		<%=(index + 1)%>
	</h2>
	<p align="center">
		<%=questionText%>
	</p>
	<br>
	<form action="CheckSingleFieldAnswer" method="POST">
		<fieldset>
			<%
				//boolean check = false;
				String checked = "checked=\"yes\"" ;
				for (String pAns : pAnswers) {					
					
					out.println("<p align=\"center\"> <input type=\"radio\" name=\"field1\" "+checked+" value = \""
							+ pAns + "\"/>" + pAns + "</p>");
					checked="";
					
				}
			%>
		</fieldset>
		<input type="submit" name="but" value="submit">
	</form>
</body>
</html>