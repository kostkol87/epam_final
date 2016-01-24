<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
    <fmt:bundle basename="local">
    <title>payment</title>
</head>
<body>

<h2>Payment:</h2>
summa is ${pageContext.session.getAttribute('summa')}. <br>
<button value="Save!" onclick="window.location = '/payOk'">PAY</button>
<button value="Save!" onclick="window.location = 'jsp/showOrders.jsp'">SAVE</button>
<input type="button" value="<fmt:message key="Home"/> " onclick="window.location = '/jsp/workspace.jsp'"><br>
<a href="/locale/ru_RU">ru</a> | <a href="/locale/en_US">en</a>
</fmt:bundle>
</body>
</html>
