<%--
  Created by IntelliJ IDEA.
  User: srezrez
  Date: 12.04.2022
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <link href="resources/css/style.css" type="text/css" rel="stylesheet"/>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div id="body-div">
    <table class="table">
        <tr>
            <th width="25%">Имя и фамилия</th>
            <th width="20%">Дата рождения</th>
            <th width="25%">Электронная почта</th>
            <th width="25%">Роль</th>
            <th width="25%">Активность</th>
            <th width="30%"></th>
        </tr>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.name}</td>
                <td>${user.birthDate}</td>
                <td>${user.email}</td>
                <td>${user.role.title}</td>
                <td>${user.activity.title}</td>
                <td>
                    <form action="MyController" method="post">
                        <input type="hidden" name="command" value="CHANGE_USER_ACTIVITY">
                        <input type="hidden" name="idUser" value=${user.idPk}>
                        <input type="submit" class="table-btn" value="Изменить активность">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
