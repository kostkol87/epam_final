<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <jsp:include page="parts/basic.jsp"/>
    <fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
    <fmt:bundle basename="local">
    <title><fmt:message key="LogIn"/></title>
</head>
<body>

<div class="top-menu">
    <a href="/locale?loc=ru_RU&target=${pageContext.request.servletPath}">ru</a> <a
        href="/locale?loc=en_US&target=${pageContext.request.servletPath}">en</a>

    <h2 id="status"><fmt:message key="LogIn"/></h2>
    <input type="button" value="<fmt:message key="Home"/> " onclick="window.location = '/'"></a>
        <span class="right">
        </span>

    <div class="clr"></div>
</div>

<form id="slick-login" action="/logged" method="post">
    <label for="username"><fmt:message key="login"/></label><input type="text" name="loginField" class="placeholder"
                                                                   required placeholder="admin@example.com">
    <label for="password"><fmt:message key="password"/></label><input type="password" name="passField"
                                                                      class="placeholder" required
                                                                      placeholder="<fmt:message key='password' />">
    <input type="submit" value="<fmt:message key='LogIn'/> ">

</form>

</body>
</fmt:bundle>
</html>