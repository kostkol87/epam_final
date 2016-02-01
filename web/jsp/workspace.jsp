<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="zz" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="../statics/css/style.css" />
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="http://www.modernizr.com/downloads/modernizr-latest.js"></script>
    <script type="text/javascript" src="../statics/js/placeholder.js"></script>

    <script type="text/javascript">
        function accept(id) {
            if (confirm("Are you sure want to delete this direction id=" + id + "?") == true) {
                window.location = '/removeDirection?id=' + id;
            } else {
                return;
            }
        }
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
    <fmt:bundle basename="local">
    <title><fmt:message key="Workspace"/> </title>
</head>
<body>

    <div class="top-menu">
        <a href="/logout"><fmt:message key="LogOut"/></a> <a href="/locale?loc=ru_RU&target=${pageContext.request.servletPath}">ru</a>  <a href="/locale?loc=en_US&target=${pageContext.request.servletPath}">en</a>
        <h2 id="status"><fmt:message key="Hello"/>, ${pageContext.session.getAttribute('user').getName()} ${pageContext.session.getAttribute('user').getSurname()} </h2>
        <input type="button" value="<fmt:message key="Home"/> " onclick="window.location = '/login'"></a>
        <span class="right">
        </span>
        <div class="clr"></div>
    </div>
    <br>

    <div id="add-dir">
        <button id="small-btn" onclick="window.location='/jsp/showOrders.jsp'"><fmt:message key="MyOrders"/> </button><br>
        <h3 id="text"><fmt:message key="DList"/> </h3><br>
        <c:set var="role" scope="session" value="${pageContext.session.getAttribute('user').getRole()}"/>
        <c:if test="${role == 3}">
            <form action="/addDirection">
                <table border="2">
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
    </div>
    <br>
    <div>
        <zz:directions admin="${pageContext.session.getAttribute('user').getRole()}" page="${pageContext.session.getAttribute('page')}" locale="${pageContext.session.getAttribute('locale')}"/><br>
    </div>

   <div id="paginator">
       <a href="/page?fwd=l&target=${pageContext.request.servletPath}"> << </a> ...${pageContext.session.getAttribute('page')}... <a href="/page?fwd=r&target=${pageContext.request.servletPath}"> >> </a><br>
   </div>

</fmt:bundle>
</body>
</html>
