<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
	<%
		Boolean isLogged = (Boolean)session.getAttribute("isLoggedIn");
		
		String name = (String)session.getAttribute("name");
		String JspName = new String();
		if(name == null) { JspName = "News.jsp"; }
		else { JspName = "AccountInfo.jsp"; }
	%>
	<frameset cols="10%, 60%,*, 10%" frameborder="no" border="0" framespacing="0">
		<frame src="BlankPage.jsp">
		<frame src=<%=JspName%> name="AccountWindow"> 
		<frame src="Statistics.jsp" name="ActualInfo">
		<frame src="BlankPage.jsp">
	</frameset>


</html>