<%--
  Created by IntelliJ IDEA.
  User: srezrez
  Date: 26.02.2022
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link href="resources/css/style.css" type="text/css" rel="stylesheet" />
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="body-div">
    <form action="MyController" method="post">
        <input type="hidden" name="command" value="SEND_REQUEST">
        <input type="hidden" name="id-cat" value=${cat.idPk}>
        <input type="submit" value="Отправить заявку">
    </form>
    <img src='${cat.photoPath}' style="width:70px;height:90px;"/>
    <label>${cat.name}</label>
    <label>${cat.age}</label>
    <label>${cat.breed}</label>
    <label>${cat.description}</label>
</div>
</body>
</html>
