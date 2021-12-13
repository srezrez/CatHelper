<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1" errorPage="error.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	String errorMs = (String)request.getParameter("error-ms");
	if(errorMs != null) { 
%>
		<h2>
			<% out.println(errorMs);
		
	}
%>
</h2>
	<c:set var="message" scope="page" value="${2*2000}" />
	<c:out value="${message}" />
	<c:set var="role" scope="session" value="admin" />
	<c:if test="${not empty role and role eq 'admin'}" var="testif">
		<p>
			My salary is:
			<c:out value="${message}" />
		</p>
	</c:if>
	<c:forEach var="b" items="${books}" >
		<p> Item: <c:out value="${b.title}" /> </p>
	</c:forEach>
<form id="1" action="MyController" method="get">
	<input type="hidden" name="command" value="GO_TO_INDEX_PAGE">
	<input type="submit" value="MAIN PAGE">
</form>
</div>
</body>
</html>