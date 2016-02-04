<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>

    <head>
        <jsp:include page="parts/basic.jsp"/>
        <fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
        <fmt:bundle basename="local">
        <title><fmt:message key="Fail"/></title>
    </head>
    <body>
    <div class="top-menu">
        <a href="/locale?loc=ru_RU&target=${pageContext.request.servletPath}">ru</a> <a
            href="/locale?loc=en_US&target=${pageContext.request.servletPath}">en</a>

        <h2 id="status"><fmt:message key="regFail"/></h2><b/>
        <input type="button" value="<fmt:message key="Home"/> " onclick="window.location = '/'"></a>
        <span class="right">
        </span>
    </div>
    <h2 id="error"><fmt:message key="regFail"/></h2>

    </body>
</fmt:bundle>
</html>
