<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
	<%
		Boolean isLookingUp = (Boolean) session.getAttribute("isLookingUp");
		String upperJsp = "AccountInfo.jsp";
		String lowerJsp = "Home.jsp";
		if(isLookingUp){
			upperJsp = "UserNavigator.jsp";
			lowerJsp = "UserProfile.jsp";
		}
	%>
	<frameset rows="17%,*"  frameborder="no" border="0" framespacing="0">
		<frame src= <%=upperJsp %> name = "AccountInfo"> 
		<frame src= <%=lowerJsp %> name = "ActualInfo">
	</frameset>


</html>