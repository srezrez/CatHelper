<%--
  Created by IntelliJ IDEA.
  User: srezrez
  Date: 02.04.2022
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <link href="resources/css/style.css" type="text/css" rel="stylesheet" />
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="body-div">
    <h1>Информация о котике: ${cat.name}</h1>
    <table class ="table">
        <tr>
            <th width="25%">Количество активных запросов</th>
            <th width="20%">Текущий пользователь</th>
            <th width="25%">Электронная почта для связи</th>
            <th width="25%">Решение</th>
        </tr>
            <tr>
                <td>${catRequest.requestAmount}</td>
                <td>${catRequest.requester.name} ${catRequest.requester.name}</td>
                <td>${catRequest.requester.email}</td>
                <td>
                    <form action="MyController" method="get">
                        <input type="hidden" name="command" value="APPROVE_REQUEST">
                        <input type="hidden" name="id-cat" value=${catRequest.idPk}>
                        <input type="submit" class="table-btn" value="Принять">
                    </form>
                    <form action="MyController" method="get">
                        <input type="hidden" name="command" value="REJECT_REQUEST">
                        <input type="hidden" name="id-cat" value=${catRequest.idPk}>
                        <input type="submit" class="table-btn" value="Отказать">
                    </form>
                </td>
            </tr>
    </table>
</div>
</body>
</html>
