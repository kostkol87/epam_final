<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="zz" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/utils.js"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>

    <title><fmt:message key="Workspace"/> </title>
</head>
<body>
<fmt:bundle basename="local">
    <h2>
        <fmt:message key="Hello"/> , ${pageContext.session.getAttribute('user').getName()} ${pageContext.session.getAttribute('user').getSurname()}</h2>

    <a href="/jsp/showOrders.jsp"><fmt:message key="MyOrders"/> </a><br>
    <h3><fmt:message key="DList"/> </h3>
    <c:set var="role" scope="session" value="${pageContext.session.getAttribute('user').getRole()}"/>
    <c:if test="${role == 3}">
        <form action="/addDirection" method="post">
            <table border="1">
                <tr>
                    <td><b> <fmt:message key="youAdmin"/> </b></td>
                <tr>
                <tr>
                    <td> <fmt:message key="Departure"/> </td>
                    <td> <fmt:message key="DepTime"/> </td>
                    <td> <fmt:message key="Destination"/> </td>
                    <td> <fmt:message key="DestTime"/> </td>
                    <td> <fmt:message key="Price"/> </td>
                    <td> <fmt:message key="DMult"/> </td>
                    <td> <fmt:message key="FMult"/> </td>
                    <td> <fmt:message key="Capacity"/> </td>
                <tr>

                <tr>
                    <td><input type="text" name="fieldDeparture" required placeholder="<fmt:message key="Departure"/>"></td>
                    <td><input type="datetime-local" name="depTime" required></td>
                    <td><input type="text" name="fieldDestination" required placeholder="<fmt:message key="Destination"/>"></td>
                    <td><input type="datetime-local" name="destTime" required></td>
                    <td><input type="text" name="basicPrice" required placeholder="<fmt:message key="Price"/>"></td>
                    <td><input type="text"  name="dateMult" required placeholder="<fmt:message key="DMult"/>"></td>
                    <td><input type="text"  name="fillMult" required placeholder="<fmt:message key="FMult"/>"></td>
                    <td><input type="text" name="capacity" required placeholder="<fmt:message key="Capacity"/>"></td>
                    <td><input type="submit" value="<fmt:message key="Add"/>"></td>
                <tr>
            </table>
        </form>
        <br>
    </c:if>


<zz:directions admin="${pageContext.session.getAttribute('user').getRole()}" page="${pageContext.session.getAttribute('page')}" locale="${pageContext.session.getAttribute('locale')}"/><br>

    <a href="/page?fwd=l&target=${pageContext.request.servletPath}"> << </a> ...${pageContext.session.getAttribute('page')}... <a href="/page?fwd=r&target=${pageContext.request.servletPath}"> >> </a><br>
    <a href="/logout"><fmt:message key="LogOut"/></a><br><br>
    <a href="/locale?loc=ru_RU&target=${pageContext.request.servletPath}">ru</a> | <a href="/locale?loc=en_US&target=${pageContext.request.servletPath}">en</a>

</fmt:bundle>
</body>
</html>
