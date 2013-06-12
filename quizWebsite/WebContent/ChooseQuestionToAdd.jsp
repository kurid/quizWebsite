<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="ChooseQuestionToAdd" method="post">
		<select name = "question">
			<option value="SingleAnswer">Single answer question</option>
			<option value="AddImageQuestion">Image question</option>
			<option value="AddMultipleChoiceQuestion">Multiple choice</option>
			<option value="AddMatchingQuestion">Matching Question</option>
		</select>
		<input type="submit" value="CreateQestion" />
	</form>
</body>
</html>