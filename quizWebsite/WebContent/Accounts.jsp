<%@page import="web.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<% List<Account> searchedAccounts = (List<Account>)request.getAttribute("searchedAccount"); %>
<body>
	<h2 align="center"> Accounts </h2>
	<form action="SearchAccount" method=POST>
	<table align="center">
		<tr>
			<td> Enter account name: </td>
			<td> <input type="text" name="accountName"/> </td>
			<td> <input type="submit" name="search" value="Search"/> </td> 
		</tr>
	</table>
	<% if(searchedAccounts != null){
				for(Account account : searchedAccounts){
				out.println("<li><a href=\"http://localhost:8080/quizWebsite/test1.jsp?ID="); 
				out.println(account.getId() + "\">" + account.getNickname()+ "</a></li>");
			}
		}%>
	</form>
	
</body>
</html>