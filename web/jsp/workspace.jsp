<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="zz" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>

    <title>Workspace</title>
</head>
<body>
<fmt:bundle basename="local">
<h2>
    Hello, ${pageContext.session.getAttribute('user').getName()} ${pageContext.session.getAttribute('user').getSurname()}</h2>
    <a href="/jsp/showOrders.jsp">My orders</a>
<br>
Full list of available flights:

<br>
<h3>Directions list:</h3><br>
<zz:directions page="${pageContext.session.getAttribute('page')}"/><br>
<a href="/page/l"> << </a> ...${pageContext.session.getAttribute('page')}... <a href="/page/r"> >> </a><br>
<a href="/logout"><fmt:message key="LogOut"/></a><br><br>
<a href="/locale/ru_RU">ru</a> | <a href="/locale/en_US">en</a>
</fmt:bundle>
</body>
</html>
