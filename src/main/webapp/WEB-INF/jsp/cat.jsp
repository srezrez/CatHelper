<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div id="cat-div">
        <section class="container">
            <div class="one"> <img src='${cat.photoPath}' class='cat-photo'/> </div>
            <div class="two">
                <h1>Информация о котике</h1>
                <div class='cat-info-class'>
                    <label>Имя:</label>
                    <label>${cat.name}</label><br/>
                    <label>Возраст:</label>
                    <label>${cat.age}</label><br/>
                    <label>Порода:</label>
                    <label>${cat.breed}</label><br/>
                    <label>Описание:</label>
                    <label>${cat.description}</label><br/>
                </div>
                <c:if test="${cat.ownerId != sessionScope.idUser}">
                    <form action="MyController" method="post">
                        <input type="hidden" name="command" value="SEND_REQUEST">
                        <input type="hidden" name="id-cat" value=${cat.idPk}>
                        <input type="submit" value="Отправить заявку">
                    </form>
                </c:if>
            </div>
        </section>
    </div>
</div>
</body>
</html>
