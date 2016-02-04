<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>

    <head>
            ${pageContext.session.getAttribute("user") == null? pageContext.forward("/jsp/loginFail.jsp"): pass}
        <jsp:include page="parts/basic.jsp"/>
                <fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
                <fmt:bundle basename="local">
        <title><fmt:message key="changeOrder"/></title>
    </head>
    <body>

    <div class="top-menu">
        <a href="/logout"><fmt:message key="LogOut"/></a> <a
            href="/locale?loc=ru_RU&target=${pageContext.request.servletPath}">ru</a> <a
            href="/locale?loc=en_US&target=${pageContext.request.servletPath}">en</a>

        <h2 id="status"><fmt:message key="changeOrder"/></h2><b/>
        <input type="button" value="<fmt:message key="Home"/> " onclick="window.location = '/jsp/workspace.jsp'"></a>
        <span class="right">
        </span>
    </div>

    <div>
        <h1 id="text"><fmt:message key="changeOrder"/>:</h1>

        <h3 id="text"><fmt:message key="attention"/></h3>

        <form action="/changeComplete">
            <table border="1">

                <tr>
                    <td>
                        <fmt:message key='PassCount'/>:
                    </td>
                    <td>
                        <input type="text" name="passengersCount" required placeholder="<fmt:message key='PassCount'/>">
                    </td>
                </tr>

                <tr>
                    <td>
                        <fmt:message key='NeedBaggage'/>(+45€):
                    </td>
                    <td>
                        <input type="checkbox" name="baggage">
                    </td>
                </tr>

                <tr>
                    <td>
                        <fmt:message key='NeedPriority'/>(+30€):
                    </td>
                    <td>
                        <input type="checkbox" name="priotityQueue">
                    </td>
                </tr>
            </table>
            <br>
            <input id="small-btn" type="submit" value="<fmt:message key="Submit"/>"/>

        </form>
    </div>
    </body>
</fmt:bundle>
</html>
