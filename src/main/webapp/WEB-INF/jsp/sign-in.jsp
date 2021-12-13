<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="resources/css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
<c:if test="${sessionExpired}" var="testif">
	<h3>
		Session expired. Please, sign in again
	</h3>
</c:if>
<jsp:include page="header.jsp" />
<form id="sign-in-Form" action="MyController" method="post">
	<input type="hidden" name="command" value="sign-in">
	<h1>Login</h1>
	<input id="email" type="email" name ="email" placeholder="Email" minlength="8" maxlength="50" />
	<span class="error" aria-live="polite"></span>
	<br />
	<input id="password" type="password" name="password" placeholder="Password" minlength="10" maxlength="25" />
	<span class="error" aria-live="polite"></span>
	<div id="buttons">
	<input type="submit" value="SIGN IN"/>
	</div>
	<h5>Don't have an account? <a href="MyController?command=GO_TO_SIGN_UP_PAGE">Sign up</a></h5>
	<h5>Or you can go back to <a href="MyController?command=GO_TO_INDEX_PAGE">main page</a></h5>
</form>
<script src="resources/js/sign-in-validation.js"></script>
</body>
</html>