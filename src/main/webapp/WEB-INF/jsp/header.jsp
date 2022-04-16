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
        <a class="active" href="MyController?command=GO_TO_PROFILE_PAGE">Name Surname</a>
        <a href="#contact">Contact</a>
        <a href="#about">About</a>
        <a href="#about">Log out</a>
    </div>
</div>
<ul >
    <li><a id="all-cats" href="MyController?command=GO_TO_MAIN_PAGE">ALL cats</a></li>
    <li><a id="added-cats" href="MyController?command=GO_TO_ADDED_CAT_LIST_PAGE">Added cats</a></li>
    <li><a id="requests" href="MyController?command=FILTER_REQUESTS&status=1">My requests</a></li>
    <li><a id="users" href="MyController?command=GO_TO_USERS_PAGE">Users</a></li>
</ul>
<script src="resources/js/menu-script.js"></script>
</body>
</html>
