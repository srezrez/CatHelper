<%--
  Created by IntelliJ IDEA.
  User: srezrez
  Date: 07.12.2021
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="loc" var="loc"/>
<html>
<head>
    <title>Title</title>
    <script src="resources/js/jquery.js"></script>
</head>
<body>

<div class="header">
    <a href="#default" class="logo">Cat Helper</a>
    <div class="header-right">
        <a id="change-locale-url" href="">RU/ENG</a>
        <a class="active" href="MyController?command=GO_TO_PROFILE_PAGE">Name Surname</a>
        <a href="#contact"><fmt:message bundle="${loc}" key="menu.contact" /></a>
        <a href="#about"><fmt:message bundle="${loc}" key="menu.about" /></a>
        <a href="MyController?command=LOG_OUT"><fmt:message bundle="${loc}" key="menu.logout" /></a>
    </div>
</div>
<ul >
    <li><a id="all-cats" href="MyController?command=GO_TO_MAIN_PAGE"><fmt:message bundle="${loc}" key="menu.allcats" /></a></li>
    <li><a id="added-cats" href="MyController?command=GO_TO_ADDED_CAT_LIST_PAGE"><fmt:message bundle="${loc}" key="menu.addedcats" /></a></li>
    <li><a id="requests" href="MyController?command=FILTER_REQUESTS&status=1"><fmt:message bundle="${loc}" key="menu.myrequests" /></a></li>
    <li><a id="users" href="MyController?command=GO_TO_USERS_PAGE"><fmt:message bundle="${loc}" key="menu.users" /></a></li>
    <li><a id="add-cat" href="MyController?command=GO_TO_ADD_CAT_PAGE"><fmt:message bundle="${loc}" key="msg.addcat" /></a></li>
</ul>
<script src="resources/js/menu-script.js"></script>
<script src="resources/js/url-addition.js"></script>
</body>
</html>
