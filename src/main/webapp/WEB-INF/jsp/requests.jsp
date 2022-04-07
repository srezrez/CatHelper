<%--
  Created by IntelliJ IDEA.
  User: srezrez
  Date: 14.02.2022
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <link href="resources/css/style.css" type="text/css" rel="stylesheet" />
    <title>Title</title>
    <script src="resources/js/jquery.js"></script>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="body-div">
    <select id="req-select"> <!--Supplement an id here instead of using 'name'-->
        <option value="1">Созданные заявки</option>
        <option value="2" selected>Одобренные заявки</option>
        <option value="3">Отклоненные заявки</option>
    </select>
    <table class ="table" id="req-table">
        <tr>
            <th width="25%">Имя</th>
            <th width="20%">Дата заявки</th>
            <th width="25%">Номер в очереди</th>
            <th width="25%">Статус</th>
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
                    <input class="table-btn" type="submit" value="Подробнее...">
                </form>
                    <form action="MyController" method="get">
                        <input type="hidden" name="command" value="CANCEL_REQUEST">
                        <input type="hidden" name="id-cat" value=${request.idPk}>
                        <input class="table-btn" type="submit" value="Отменить">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<script src="resources/js/requests-filter.js"></script>
</body>
</html>
