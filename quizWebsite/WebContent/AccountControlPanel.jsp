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
		String accountName = (String) session.getAttribute("name");
		
	%>
	<table>
		<tr> 
			<td>
				<a href="LowerAlignment.jsp" target="LowerAlignment"> <%=accountName %> </a>
			</td>
		</tr> 
		<tr> 
			<td>
				<form action="LogoutServlet">
					<input type="submit" name="logout" value="Log Out">
				</form>
			</td>
			<td> 
				<form action="createQuizzServlet">
					<input type="submit" name="createQuizzServlet" value="Create Quizz">
				</form>
			</td>
		</tr>
	</table>
	
	
</body>
</html>