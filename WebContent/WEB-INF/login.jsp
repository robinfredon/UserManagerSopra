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
	<c:import url="/WEB-INF/user/formLog.jsp" />
 	<br />
 	<c:if test="${ !errorStatus }">
 	<c:import url="/WEB-INF/user/userLog.jsp" />
 	</c:if>
</body>
</html>