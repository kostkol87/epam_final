<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>

    <head>
        <jsp:include page="parts/basic.jsp"/>
        <fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
        <fmt:bundle basename="local">
        <title><fmt:message key="WelcomeStr"/></title>

    </head>

    <body>
    <div class="top-menu">
        <a href="/locale?loc=ru_RU&target=${pageContext.request.servletPath}">ru</a> <a
            href="/locale?loc=en_US&target=${pageContext.request.servletPath}">en</a>

        <b></b>

        <h2 id="status"><fmt:message key="WelcomeStr"/></h2><b/>
        <span class="right">
        </span>

    </div>

    <div id="main-div">

        <h2>
            <fmt:message key="MakeChoice"/><br>

            <button onclick="window.location = '/login'"><fmt:message key='LogIn'/></button>
            <button onclick="window.location = '/jsp/register.jsp'"><fmt:message key="Registration"/></button>
        </h2>


    </body>
</fmt:bundle>
</html>
