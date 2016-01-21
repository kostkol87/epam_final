<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
<fmt:bundle basename="local">
    <title>Workspace</title>
</head>
<body>
<h2>Hello, ${pageContext.session.getAttribute('user').getName()} ${pageContext.session.getAttribute('user').getSurname()}</h2><br>
this is the main screen!
<br>
<c:forEach items="${rows}" var="row">
    <c:out value="${row.name}" /><br>
</c:forEach><br>

<a href="/logout"><fmt:message key="LogOut"/></a>
<a href="/locale/ru_RU">ru</a> | <a href="/locale/en_US">en</a>
</fmt:bundle>
</body>
</html>
