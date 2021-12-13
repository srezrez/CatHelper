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
	String username = (String) session.getAttribute("username");
	if(username != null) { 
%>
		<h2>
			Hello, <% out.println(username);
		
	}
%>
</div>
</h2>
</body>
</html>