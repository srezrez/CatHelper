<%--
  Created by IntelliJ IDEA.
  User: srezrez
  Date: 07.12.2021
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="header">
    <a href="#default" class="logo">Cat Helper</a>
    <div class="header-right">
        <a class="active" href="#home">Name Surname</a>
        <a href="#contact">Contact</a>
        <a href="#about">About</a>
        <a href="#about">Log out</a>
    </div>
</div>
<ul>
    <li><a class="active" href="MyController?command=GO_TO_MAIN_PAGE">ALL cats</a></li>
    <li><a href="MyController?command=GO_TO_ADDED_CAT_LIST_PAGE">Added cats</a></li>
    <li><a href="MyController?command=GO_TO_REQUESTS_PAGE">My requests</a></li>
</ul>

</body>
</html>
