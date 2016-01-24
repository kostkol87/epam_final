<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="zz" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
    <fmt:bundle basename="local">
    <title>Yout orders</title>
</head>
<body>
<h1>Your orders:</h1>
<zz:orders userId="${pageContext.session.getAttribute('user').getId()}"/>
<input type="button" value="<fmt:message key="Home"/> " onclick="window.location = '/jsp/workspace.jsp'"><br>
<a href="/locale/ru_RU">ru</a> | <a href="/locale/en_US">en</a>
</fmt:bundle>
</body>
</html>
