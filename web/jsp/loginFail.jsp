<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
    <fmt:bundle basename="local">
    <title>Login Fail</title>
</head>
<body>
<h2>Something has gone wrong</h2>
<input type="button" value="<fmt:message key="Home"/> " onclick="window.location = '/'">
</fmt:bundle>
</body>
</html>
