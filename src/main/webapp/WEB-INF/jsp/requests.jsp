<%--
  Created by IntelliJ IDEA.
  User: srezrez
  Date: 14.02.2022
  Time: 12:01
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
    <title><fmt:message bundle="${loc}" key="ttl.myrequests" /></title>
    <script src="resources/js/jquery.js"></script>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="body-div">
    <select id="req-select"> <!--Supplement an id here instead of using 'name'-->
        <option value="1" selected><fmt:message bundle="${loc}" key="option.createdRequests" /></option>
        <option value="2"><fmt:message bundle="${loc}" key="option.approvedRequests" /></option>
        <option value="3"><fmt:message bundle="${loc}" key="option.deniedRequests" /></option>
    </select>
    <table class ="table" id="req-table">
        <tr>
            <th width="25%"><fmt:message bundle="${loc}" key="tbl.name" /></th>
            <th width="20%"><fmt:message bundle="${loc}" key="tbl.requestdate" /></th>
            <th width="25%"><fmt:message bundle="${loc}" key="tbl.numberinqueue" /></th>
            <th width="25%"><fmt:message bundle="${loc}" key="tbl.status" /></th>
            <th width="30%"></th>
        </tr>
        <c:forEach items="${requests}" var="request">
            <tr>
                <td>${request.name}</td>
                <td>${request.dateRequest}</td>
                <td>${request.numberInQueue}</td>
                <td>${request.status.title}</td>
                <td><form action="MyController" method="get">
                    <input type="hidden" name="command" value="GO_TO_CAT_PAGE">
                    <input type="hidden" name="id-cat" value=${request.idCat}>
                    <input class="table-btn" type="submit" value=<fmt:message bundle="${loc}" key="btn.more" />>
                </form>
                    <form action="MyController" method="get">
                        <input type="hidden" name="command" value="CANCEL_REQUEST">
                        <input type="hidden" name="id-cat" value=${request.idPk}>
                        <input class="table-btn" type="submit" value=<fmt:message bundle="${loc}" key="btn.cancel" />>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<script src="resources/js/requests-filter.js"></script>
</body>
</html>
