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
	<form action="AddMatchingQuestion" method="post">
		QuestionText: <input type="text" name="questionText"><br> 
		<%
			for (int i = 2; i <= 8; i += 2) {
				out.println("Matching " + (i / 2)
						+ " : <input type=\"text\" name=\"field" + (i - 1)
						+ "\"> : <input type=\"text\" name=\"field" + i
						+ "\"><br>");
			}
		%>
		  
		<%@include file="ChooseScore.jsp" %>
		<button type="submit">AddQuestion</button>
	</form>
</body>
</html>