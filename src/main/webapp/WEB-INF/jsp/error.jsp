<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="resources/css/styles.css" type="text/css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div class="welcome-div" id="main-div">
<%
	String errorMs = (String)request.getParameter("error-ms");
	if(errorMs != null) { 
%>
		<h2>
			<% out.println(errorMs);
		
	}
%>
</h2>
</div>
</body>
</html>