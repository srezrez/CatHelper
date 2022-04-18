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
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp" />
<form id = "cat-add-form" action="MyController" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="add-cat">
    <h1><fmt:message bundle="${loc}" key="msg.addcat" /></h1>
    <label class="label-for-form"><fmt:message bundle="${loc}" key="lbl.name" /></label>
    <input name="name" id="name" type="text" minlength="2" maxlength="25" placeholder="Имя"/>
    <span class="error" aria-live="polite"></span>
    <label class="label-for-form"><fmt:message bundle="${loc}" key="lbl.birthDate" /></label>
    <input id="birth-date" type="date" name="birth-date" min="2018-01-01" max="2018-12-31" value="2018-07-22">
    <label class="label-for-form"><fmt:message bundle="${loc}" key="lbl.breed" /></label>
    <select name="breed" class="breed-class">
        <c:forEach items="${breedList}" var="breed">
            <option value=${breed.idPk}>${breed.title}</option>
        </c:forEach>
    </select>

    <label class="label-for-form"><fmt:message bundle="${loc}" key="lbl.gender" /></label>
    <div class="gender-class">
        <input  type="radio" id="contactChoice1" name="gender" value="1" checked>
        <label for="contactChoice1"><fmt:message bundle="${loc}" key="lbl.male" /></label>

        <input type="radio" id="contactChoice2" name="gender" value="2">
        <label for="contactChoice2"><fmt:message bundle="${loc}" key="lbl.female" /></label>

    </div>
    <label class="label-for-form"><fmt:message bundle="${loc}" key="lbl.description" /></label>
    <textarea name="description" rows="5" cols="70" placeholder=<fmt:message bundle="${loc}" key="lbl.description" />></textarea>

    <label class="label-for-form"><fmt:message bundle="${loc}" key="lbl.photo" /></label>
    <input name="catPhoto" type="file">

    <div id="buttons">
        <input type="submit" value=<fmt:message bundle="${loc}" key="btn.addcat" />/>
    </div>
</form>
</body>
</html>
