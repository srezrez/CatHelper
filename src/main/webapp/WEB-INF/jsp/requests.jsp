<%--
  Created by IntelliJ IDEA.
  User: srezrez
  Date: 14.02.2022
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="body-div">
    <form action="MyController" method="get">
        <input type="hidden" name="command" value="GO_TO_ADD_CAT_PAGE">
        <input type="submit" value="Добавить кота">
    </form>
    <table class ="table">
        <tr>
            <th width="25%">Имя</th>
            <th width="20%">Возраст</th>
            <th width="25%">Порода</th>
            <th width="25%">Количество заявок</th>
            <th width="25%">Статус</th>
            <th width="30%"></th>
        </tr>
        <c:forEach items="${addedCatList}" var="cat">
            <tr>
                <td>${cat.name}</td>
                <td>${cat.age}</td>
                <td>${cat.breed}</td>
                <td>${cat.requestAmount}</td>
                <td>${cat.status}</td>
                <td><button class="table-btn"> Подробнее... </button></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
