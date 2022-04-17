<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="resources/css/style.css" type="text/css" rel="stylesheet" />
<title>Insert title here</title>
	<fmt:setLocale value="${sessionScope.locale}" />
</head>
<body>
<c:set var="local" scope="session" value="ru"/>
<div class="welcome-div">
<h1>Welcome to my application</h1>

<form class="welcome-btn" action="MyController" method="get">
	<input type="hidden" name="command" value="GO_TO_SIGN_UP_PAGE">
	<input type="submit" value="SIGN UP">
</form>

<form class="welcome-btn" action="MyController" method="get">
	<input type="hidden" name="command" value="GO_TO_SIGN_IN_PAGE">
	<input type="submit" value="SIGN IN">
</form>
</div>
<!-- <button onclick="location.href='MyController?command=GO_TO_SIGN_UP_PAGE'" type="button">
         sign up</button> -->
</body>
</html>