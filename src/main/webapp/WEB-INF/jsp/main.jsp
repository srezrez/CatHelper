<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="resources/css/style.css" type="text/css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="body-div">
	<table class ="table">
		<tr>
			<th width="40%">Photo</th>
			<th width="25%">Name</th>
			<th width="20%">Age</th>
			<th width="25%">Breed</th>
			<th width="30%"></th>
		</tr>
		<c:forEach items="${catList}" var="cat">
		<tr>
			<td><img src='${cat.photoPath}' style="width:70px;height:90px;"/> </td>
			<td>${cat.name}</td>
			<td>${cat.age}</td>
			<td>${cat.breed}</td>
            <td><form action="MyController" method="get">
                <input type="hidden" name="command" value="GO_TO_CAT_PAGE">
                <input type="hidden" name="id-cat" value=${cat.idPk}>
                <input class="table-btn" type="submit" value="More...">
            </form></td>
		</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>