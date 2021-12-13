<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="resources/css/style.css" type="text/css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body>
<div class="welcome-div" id="main-div">
<%
	String regInfo = (String)request.getParameter("info-message");
	if(regInfo != null) { 
%>
		<h2>
			<% out.println(regInfo);
		
	}
%>
</h2>
<form id="1" action="MyController" method="post">
	<input type="hidden" name="command" value="GO_TO_INDEX_PAGE">
	<input type="submit" value="MAIN PAGE">
</form>
</div>
</body>
</html>