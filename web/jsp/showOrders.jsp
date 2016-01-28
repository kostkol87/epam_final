<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="zz" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
    <fmt:bundle basename="local">
    <title><fmt:message key="yOrders"/></title>
</head>
<body>
<h1><fmt:message key="yOrders"/></h1>
<zz:orders userId="${pageContext.session.getAttribute('user').getId()}" locale="${pageContext.session.getAttribute('locale')}"/>
<input type="button" value="<fmt:message key="Home"/> " onclick="window.location = '/jsp/workspace.jsp'"><br>
<a href="/locale?loc=ru_RU&target=${pageContext.request.servletPath}">ru</a> | <a href="/locale?loc=en_US&target=${pageContext.request.servletPath}">en</a>
</fmt:bundle>
</body>
</html>
