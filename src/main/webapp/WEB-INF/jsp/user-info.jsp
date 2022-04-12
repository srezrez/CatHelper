<%--
  Created by IntelliJ IDEA.
  User: srezrez
  Date: 07.04.2022
  Time: 22:01
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
<form id="sign-up-Form" action="MyController" method="post">
  <input type="hidden" name="command" value="CHANGE_PASSWORD">
  <h1>Ваш профиль</h1>
  <div class='user-info-class'>
    <label>Имя:</label>
    <label>${userInfo.name} </label><br/>
    <br/>
    <label>Фамилия:</label>
    <label>${userInfo.surname} </label><br/>
    <br/>
    <label>email:</label>
    <label>${userInfo.email}</label><br/>
    <br/>
  </div>
  <h5>Изменение пароля: </h5>
  <input id="password" type="password" placeholder="Password" name ="password" minlength="10" maxlength="25"/>
  <span class="error" aria-live="polite"></span>
  <br />
  <input id="confirm-password" placeholder="Confirm password" name ="confirm-password" type="password" />
  <span class="error" aria-live="polite"></span>
  <br />
  <div id="buttons">
    <input type="submit" value="Изменить пароль"/>
  </div>
</form>
<script src="resources/js/sign-up-validation.js"></script>
</body>
</html>
