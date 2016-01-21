<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>

    <fmt:bundle basename="local">
    <title><fmt:message key="WelcomeStr"/> </title>

</head>

<body>

<h1><fmt:message key="WelcomeStr"/> </h1><br>
<h2>
    <fmt:message key="MakeChoice"/><br>
    <b><a href="/login"><fmt:message key="LogIn"/> </a> | <a href="/register"><fmt:message key="Registration"/> </a></b>
</h2>
<a href="/locale/ru_RU">ru</a> | <a href="/locale/en_US">en</a>
</fmt:bundle>
</body>
</html>
