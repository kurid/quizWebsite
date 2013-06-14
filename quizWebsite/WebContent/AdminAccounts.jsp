<%@page import="web.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<% List<Account> searchedAccounts = (List<Account>)request.getAttribute("searchedAccount"); %>

<body>
	<h2 align="center"> Accounts </h2>
	<form action="SearchAccount" method="POST">
	<table align="center">
		<tr>
			<td> Enter account name: </td>
			<td> <input type="text" name="accountName"/> </td>
			<td> <input type="submit" name="search" value="Search"/> </td> 
		</tr>
	</table>
	</form>
	
	<table align="center">
		<% if(searchedAccounts != null){
			for (Account acc : searchedAccounts) {
				out.println("<tr><td>"
						+ "<form action=\"DeleteAccount\" method=\"post\"> "
						+ acc.getNickname()
						+ "<input type = \"submit\" value = \"delete\" /> <input type=\"hidden\" name=\"name\" value=\""
						+ acc.getId() + "\"> </form></td></tr>");
				out.println("<tr><td>"
						+ "<form action=\"PromoteAccount\" method=\"post\"> "
						+ acc.getNickname()
						+ "<input type = \"submit\" value = \"promote\" /> <input type=\"hidden\" name=\"name\" value=\""
						+ acc.getId() + "\"> </form></td></tr>");
			}
		}
		%>
	</table>
	
	
	
	
</body>
</html>