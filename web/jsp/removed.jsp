<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>

    <head>
            ${pageContext.session.getAttribute("user") == null? pageContext.forward("/jsp/loginFail.jsp"): pass}
        <jsp:include page="parts/basic.jsp"/>
                <fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
                <fmt:bundle basename="local">
        <title><fmt:message key="DirRemoved"/></title>
    </head>
    <body>
    <div class="top-menu">
        <a href="/locale?loc=ru_RU&target=${pageContext.request.servletPath}">ru</a> <a
            href="/locale?loc=en_US&target=${pageContext.request.servletPath}">en</a>

        <h2 id="status"><fmt:message key="DirRemoved"/></h2><b/>
        <input type="button" value="<fmt:message key="Home"/> " onclick="window.location = '/login'"></a>
        <span class="right">
        </span>

    </div>

    <form id="slick-login">
        <h1 id="error"><fmt:message key="DirRemovedOk"/></h1>
    </form>

    </body>
</fmt:bundle>
</html>