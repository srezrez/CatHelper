<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="loc" var="loc"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="resources/css/styles.css" type="text/css" rel="stylesheet" />
</head>
<body>
<c:if test="${sessionExpired}" var="testif">
	<h3>
		Session expired. Please, sign in again
	</h3>
</c:if>
<form id="sign-in-Form" action="MyController" method="post">
	<input type="hidden" name="command" value="sign-in">
	<h1><fmt:message bundle="${loc}" key="msg.signin" /></h1>
	<input id="email" type="email" name ="email" placeholder="<fmt:message bundle="${loc}" key="input.email" />" minlength="8" maxlength="50" />
	<span class="error" aria-live="polite"></span>
	<br />
	<input id="password" type="password" name="password" placeholder=<fmt:message bundle="${loc}" key="input.password" /> minlength="10" maxlength="25" />
	<span class="error" aria-live="polite"></span>
	<div id="buttons">
	<input type="submit" value=<fmt:message bundle="${loc}" key="btn.signin"/>>
	</div>
	<h5><fmt:message bundle="${loc}" key="msg.noaccount" /> <a href="MyController?command=GO_TO_SIGN_UP_PAGE"><fmt:message bundle="${loc}" key="msg.signup" /></a></h5>
	<h5><fmt:message bundle="${loc}" key="msg.gotomain" /> <a href="MyController?command=GO_TO_INDEX_PAGE"><fmt:message bundle="${loc}" key="msg.mainpage" /></a></h5>
</form>
<script src="resources/js/sign-in-validation.js"></script>
</body>
</html>