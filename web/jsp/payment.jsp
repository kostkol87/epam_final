<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>

    <head>
            ${pageContext.session.getAttribute("user") == null? pageContext.forward("/jsp/loginFail.jsp"): pass}
        <jsp:include page="parts/basic.jsp"/>
                <fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
                <fmt:bundle basename="local">
        <title><fmt:message key="Pay"/></title>
    </head>
    <body>
    <div class="top-menu">
        <a href="/logout"><fmt:message key="LogOut"/></a> <a
            href="/locale?loc=ru_RU&target=${pageContext.request.servletPath}">ru</a> <a
            href="/locale?loc=en_US&target=${pageContext.request.servletPath}">en</a>

        <h2 id="status"><fmt:message key="Pay"/></h2><b/>
        <input type="button" value="<fmt:message key="Home"/> " onclick="window.location = '/jsp/workspace.jsp'"></a>
        <span class="right">
        </span>
    </div>
    <div>
        <h3 id="text"><fmt:message key="Summa"/> ${pageContext.session.getAttribute('summa')}.</h3> <br>
        <button id="small-btn" onclick="window.location = '/payOk'">PAY</button>
        <button id="small-btn" onclick="window.location = 'jsp/showOrders.jsp'">SAVE</button>
    </div>

    </body>
</fmt:bundle>
</html>
