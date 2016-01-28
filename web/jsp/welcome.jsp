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
    <b><a href="/login"><fmt:message key="LogIn"/> </a> | <a href="/jsp/register.jsp"><fmt:message key="Registration"/> </a></b>
</h2>
<a href="/locale?loc=ru_RU&target=${pageContext.request.servletPath}">ru</a> | <a href="/locale?loc=en_US&target=${pageContext.request.servletPath}">en</a>
</fmt:bundle>
</body>
</html>
