<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header">
    <a href="#default" class="logo">Cat Helper</a>
    <div class="header-right">
        <a href="MyController?command=GO_TO_PROFILE_PAGE">RU/ENG</a>
        <a class="active" href="MyController?command=GO_TO_PROFILE_PAGE">Name Surname</a>
        <a href="#contact">Contact</a>
        <a href="#about">About</a>
        <a href="#about">Log out</a>
    </div>
</div>
<ul >
    <li><a id="all-cats" href="MyController?command=GO_TO_MAIN_PAGE">ALL cats</a></li>
    <li><a id="added-cats" href="MyController?command=GO_TO_ADDED_CAT_LIST_PAGE">Added cats</a></li>
    <li><a id="requests" href="MyController?command=GO_TO_REQUESTS_PAGE">My requests</a></li>
    <li><a id="users" href="MyController?command=GO_TO_USERS_PAGE">Users</a></li>
</ul>
