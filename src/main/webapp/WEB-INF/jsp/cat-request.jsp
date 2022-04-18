<%--
  Created by IntelliJ IDEA.
  User: srezrez
  Date: 02.04.2022
  Time: 20:38
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
    <h1><fmt:message bundle="${loc}" key="msg.catrequests" /> ${cat.name}</h1>
    <table class ="table">
        <tr>
            <th width="25%"><fmt:message bundle="${loc}" key="tbl.activereqamount" /></th>
            <th width="20%"><fmt:message bundle="${loc}" key="tbl.currentuser" /></th>
            <th width="25%"><fmt:message bundle="${loc}" key="tbl.contactemail" /></th>
            <th width="25%"><fmt:message bundle="${loc}" key="tbl.decision" /></th>
        </tr>
            <tr>
                <td>${catRequest.requestAmount}</td>
                <td>${catRequest.requester.name} ${catRequest.requester.name}</td>
                <td>${catRequest.requester.email}</td>
                <td>
                    <form action="MyController" method="post">
                        <input type="hidden" name="command" value="APPROVE_REQUEST">
                        <input type="hidden" name="id-request" value=${catRequest.idPk}>
                        <input type="submit" class="table-btn" value=<fmt:message bundle="${loc}" key="btn.accept" />>
                    </form>
                    <form action="MyController" method="post">
                        <input type="hidden" name="command" value="REJECT_REQUEST">
                        <input type="hidden" name="id-request" value=${catRequest.idPk}>
                        <input type="submit" class="table-btn" value=<fmt:message bundle="${loc}" key="btn.reject" />>
                    </form>
                </td>
            </tr>
    </table>
</div>
</body>
</html>
