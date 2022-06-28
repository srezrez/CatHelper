<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="loc" var="loc"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration page</title>
<link href="resources/css/styles.css" type="text/css" rel="stylesheet" />
</head>
<body>

<form id="sign-up-Form" action="MyController" method="post">
	<input type="hidden" name="command" value="sign-up">
	<h1><fmt:message bundle="${loc}" key="msg.signup" /></h1>
	<input id="name" type="text" placeholder="<fmt:message bundle="${loc}" key="input.name" />" name="name" minlength="2" maxlength="25"/>
	<span class="error" aria-live="polite"></span>
	<br />
	<input id="surname" type="text" placeholder="<fmt:message bundle="${loc}" key="input.surname" />" name="surname" minlength="2" maxlength="25"/>
	<span class="error" aria-live="polite"></span>
	<br />
	<input id="birth-date" type="date" name="birth-date" min="2018-01-01" max="2018-12-31" value="2018-07-22">
	<br />
	<input id="email" type="email" placeholder="<fmt:message bundle="${loc}" key="input.email" />" name="email" minlength="8" maxlength="50"/>
	<span class="error" aria-live="polite"></span>
	<br />
	<input id="password" type="password" placeholder="<fmt:message bundle="${loc}" key="input.password" />" name="password" minlength="10" maxlength="25"/>
	<span class="error" aria-live="polite"></span>
	<br />
	<input id="confirm-password" placeholder="<fmt:message bundle="${loc}" key="input.confirmpassword" />" name="confirm-password" type="password" />
	<span class="error" aria-live="polite"></span>
	<br />
	<div id="buttons">
	<input type="submit" value=<fmt:message bundle="${loc}" key="btn.signup" />/>
	</div>
	<h5><fmt:message bundle="${loc}" key="msg.haveaccount" /> <a href="MyController?command=GO_TO_SIGN_IN_PAGE"><fmt:message bundle="${loc}" key="msg.signin" /></a></h5>
	<h5><fmt:message bundle="${loc}" key="msg.gotomain" /> <a href="MyController?command=GO_TO_INDEX_PAGE"><fmt:message bundle="${loc}" key="msg.mainpage" /></a></h5>
	
</form>
	<script src="resources/js/sign-up-validation.js"></script>
</body>
</html>