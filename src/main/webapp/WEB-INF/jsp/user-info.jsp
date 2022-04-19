<%--
  Created by IntelliJ IDEA.
  User: srezrez
  Date: 07.04.2022
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="loc" var="loc"/>
<html>
<head>
  <meta charset="utf-8">
  <link href="resources/css/styles.css" type="text/css" rel="stylesheet" />
  <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="body-div">
<form action="MyController" method="post">
  <input type="hidden" name="command" value="CHANGE_PASSWORD">
  <h1><fmt:message bundle="${loc}" key="msg.userprofile" /></h1>
  <div class='user-info-class'>
    <label><fmt:message bundle="${loc}" key="lbl.name" /></label>
    <label>${userInfo.name} </label><br/>
    <br/>
    <label><fmt:message bundle="${loc}" key="lbl.surname" /></label>
    <label>${userInfo.surname} </label><br/>
    <br/>
    <label><fmt:message bundle="${loc}" key="lbl.email" /></label>
    <label>${userInfo.email}</label><br/>
    <br/>
  </div>
  <h5><fmt:message bundle="${loc}" key="lbl.passchange" /> </h5>
  <input id="password" type="password" placeholder=<fmt:message bundle="${loc}" key="input.password" /> name="password" minlength="10" maxlength="25"/>
  <span class="error" aria-live="polite"></span>
  <br />
  <input id="confirm-password" placeholder=<fmt:message bundle="${loc}" key="input.confirmpassword" /> name="confirm-password" type="password" />
  <span class="error" aria-live="polite"></span>
  <br />
  <div id="buttons">
    <input type="submit" value=<fmt:message bundle="${loc}" key="btn.changepass" />/>
  </div>
</form>
</div>
<script src="resources/js/sign-up-validation.js"></script>
</body>
</html>
