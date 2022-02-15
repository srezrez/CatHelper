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
<form id = "cat-add-form">
    <h1>Добавить котика</h1>
    <label class="label-for-form">Имя:</label>
    <input id="name" type="text" minlength="2" maxlength="25" placeholder="Имя"/>
    <span class="error" aria-live="polite"></span>
    <label class="label-for-form">Дата рождения:</label>
    <input id="birth-date" type="date" name="birth-date" min="2018-01-01" max="2018-12-31" value="2018-07-22">
    <label class="label-for-form">Порода:</label>
    <select name="breed" class="breed-class">
        <option value="value1">Порода 1</option>
        <option value="value2" selected>Порода 2</option>
        <option value="value3">Порода 3</option>
    </select>

    <label class="label-for-form">Пол:</label>
    <div class="gender-class">
        <input  type="radio" id="contactChoice1"
                name="contact" value="email">
        <label for="contactChoice1">мужской</label>

        <input type="radio" id="contactChoice2"
               name="contact" value="phone">
        <label for="contactChoice2">женский</label>

    </div>
    <label class="label-for-form">Описание:</label>
    <textarea name="story" rows="5" cols="70" placeholder="Описание"></textarea>

    <label class="label-for-form">Фото:</label>
    <input name="myFile" type="file">

    <div id="buttons">
        <input type="submit" value="Добавить"/>
    </div>
</form>
</body>
</html>
