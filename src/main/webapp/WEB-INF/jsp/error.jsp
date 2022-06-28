<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="loc" var="loc"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="resources/css/styles.css" type="text/css" rel="stylesheet" />
<title><fmt:message bundle="${loc}" key="ttl.error" /></title>
</head>
<body>
<div class="welcome-div" id="main-div">
<%
	String errorMs = (String)request.getParameter("error-ms");
	if(errorMs != null) { 
%>
		<h1>
			<% out.println(errorMs);
		
	}
%>
</h1>
</div>
</body>
</html>