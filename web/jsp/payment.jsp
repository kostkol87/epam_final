<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
    <fmt:bundle basename="local">
    <title><fmt:message key="Pay"/></title>
</head>
<body>

<h2><fmt:message key="Pay"/> :</h2>
<fmt:message key="Summa"/> ${pageContext.session.getAttribute('summa')}. <br>
<button onclick="window.location = '/payOk'">PAY</button>
<button onclick="window.location = 'jsp/showOrders.jsp'">SAVE</button>
<input type="button" value="<fmt:message key="Home"/> " onclick="window.location = '/jsp/workspace.jsp'"><br>
<a href="/locale?loc=ru_RU&target=${pageContext.request.servletPath}">ru</a> | <a href="/locale?loc=en_US&target=${pageContext.request.servletPath}">en</a>
</fmt:bundle>
</body>
</html>
