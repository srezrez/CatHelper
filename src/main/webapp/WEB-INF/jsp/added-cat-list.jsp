<%--
  Created by IntelliJ IDEA.
  User: srezrez
  Date: 16.02.2022
  Time: 19:00
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
    <table class ="table">
        <tr>
            <th width="25%"><fmt:message bundle="${loc}" key="tbl.name" /></th>
            <th width="20%"><fmt:message bundle="${loc}" key="tbl.age" /></th>
            <th width="25%"><fmt:message bundle="${loc}" key="tbl.breed" /></th>
            <th width="25%"><fmt:message bundle="${loc}" key="tbl.requestsamount" /></th>
            <th width="30%"></th>
        </tr>
        <c:forEach items="${addedCatList}" var="cat">
            <tr>
                <td>${cat.name}</td>
                <td>${cat.age}</td>
                <td>${cat.breed}</td>
                <td>${cat.requestAmount}</td>
                <td>
                    <c:if test="${cat.requestAmount > 0}">
                        <form action="MyController" method="get">
                            <input type="hidden" name="command" value="GO_TO_CAT_REQUEST_PAGE">
                            <input type="hidden" name="id-cat" value=${cat.idPk}>
                            <input type="submit" class="table-btn" value=<fmt:message bundle="${loc}" key="btn.more" />>
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
