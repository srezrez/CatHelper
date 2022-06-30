<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="loc" var="loc"/>
<div class="header">
    <a href="#default" class="logo">Cat Helper</a>
    <div class="header-right">
        <a id="change-locale-url" href="">RU/ENG</a>
        <a class="active" href="MyController?command=GO_TO_PROFILE_PAGE">${user.name} ${user.surname}</a>
        <a href="#contact"><fmt:message bundle="${loc}" key="menu.contact" /></a>
        <a href="#about"><fmt:message bundle="${loc}" key="menu.about" /></a>
        <a href="MyController?command=LOG_OUT"><fmt:message bundle="${loc}" key="menu.logout" /></a>
    </div>
</div>
<ul class="header">
    <li><a class="menu-toggle" data-theme="default" id="all-cats" href="MyController?command=GO_TO_MAIN_PAGE"><fmt:message bundle="${loc}" key="menu.allcats" /></a></li>
    <li><a class="menu-toggle" data-theme="dark" id="added-cats" href="MyController?command=GO_TO_ADDED_CAT_LIST_PAGE"><fmt:message bundle="${loc}" key="menu.addedcats" /></a></li>
    <li><a class="menu-toggle" data-theme="light" id="requests" href="MyController?command=FILTER_REQUESTS&status=1"><fmt:message bundle="${loc}" key="menu.myrequests" /></a></li>
    <c:if test="${sessionScope.user.role.idPk == 1}">
        <li><a class="menu-toggle" id="users" href="MyController?command=GO_TO_USERS_PAGE"><fmt:message bundle="${loc}" key="menu.users" /></a></li>
    </c:if>
    <li><a class="menu-toggle" id="add-cat" href="MyController?command=GO_TO_ADD_CAT_PAGE"><fmt:message bundle="${loc}" key="msg.addcat" /></a></li>
</ul>

<script src="resources/js/jquery.js"></script>
<script src="resources/js/menu-script.js"></script>
<script src="resources/js/url-addition.js"></script>