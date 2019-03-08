<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type="text/css" rel="stylesheet" href="style.css" />
<title>UserManager4</title>
</head>
<body>
	<c:import url="/WEB-INF/menu/menu.jsp" />
	<fieldset>
		<table>
			<tr>
				<th>Email :</th>
				<th>Nom :</th>
				<th>Action</th>
			</tr>
			<c:forEach items = "${ users }" var = "user" varStatus = "status">
	         	<tr
	         	<c:choose>
	         	<c:when test="${status.index%2 == 0}">  class="pair" </c:when>
	         	<c:when test="${status.index%2 == 1}"> class="impair" </c:when>
	         	</c:choose>
	         	>
	         		<td> <c:out value = "${user.value.getEmail()}"/></td>
	         		<td> <c:out value = "${user.value.getName()}"/>  </td>
	         		<td><a href="del-user?mail=<c:out value = "${user.value.getEmail()}"/>" >
	         		<img src="<c:url value="/images/supprimer.png"/>" height="30" width="30" alt="Supprimer" /></a>
	         		</td>
	         	</tr>
	      	</c:forEach>
		</table>
	</fieldset>
</body>
</html>