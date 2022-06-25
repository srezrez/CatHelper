<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="loc" var="loc"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="resources/css/styles.css" type="text/css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp" />
<div id="body-div">
	<form action="MyController" method="get">
	<input type="hidden" name="command" value="FILTER_CAT">
	<div>
		<h4><fmt:message bundle="${loc}" key="search.message" /></h4>
		<h5><fmt:message bundle="${loc}" key="search.breed" /></h5>
		<c:forEach items="${breedList}" var="breed">
			<input type="checkbox" class="custom-checkbox" value="${breed.idPk}" name="breed">
			<label>${breed.title}</label>
			<br/>
		</c:forEach>
		<h5><fmt:message bundle="${loc}" key="search.gender" /></h5>
		<div class="gender-class">
			<input  type="checkbox"
					name="gender" value="1">
			<label><fmt:message bundle="${loc}" key="lbl.male" /></label>

			<input type="checkbox"
				   name="gender" value="2">
			<label><fmt:message bundle="${loc}" key="lbl.female" /></label>
		</div>
		<div id="buttons">
			<input type="submit" id="table-btn" value=<fmt:message bundle="${loc}" key="btn.submit" />/>
		</div>
	</div>
	</form>
	<table class ="table">
		<tr>
			<th width="30%"><fmt:message bundle="${loc}" key="tbl.photo" /></th>
			<th width="25%"><fmt:message bundle="${loc}" key="tbl.name" /></th>
			<th width="20%"><fmt:message bundle="${loc}" key="tbl.age" /></th>
			<th width="25%"><fmt:message bundle="${loc}" key="tbl.breed" /></th>
			<th width="10%"><fmt:message bundle="${loc}" key="tbl.gender" /></th>
			<th width="30%"></th>
		</tr>
		<c:forEach items="${catList}" var="cat">
		<tr>
			<td><img src='${cat.photoPath}' style="width:70px;height:90px;"/> </td>
			<td>${cat.name}</td>
			<td>${cat.age}</td>
			<td>${cat.breed}</td>
			<td>${cat.gender.title}</td>
            <td><form action="MyController" method="get">
                <input type="hidden" name="command" value="GO_TO_CAT_PAGE">
                <input type="hidden" name="id-cat" value=${cat.idPk}>
                <input class="table-btn" type="submit" value=<fmt:message bundle="${loc}" key="btn.more" />>
            </form></td>
		</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>