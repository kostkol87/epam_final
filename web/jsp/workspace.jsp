<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="zz" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <script>
        function accept(id) {
            var x;
            if (confirm("Are you sure want to delete this direction id=" + id + "?") == true) {
                x = "You pressed OK!";
                window.location = '/removeDirection?id='+id;
            } else {
                return;
            }
        }
    </script>

    <script>
        document.getElementById('datetimeLocalToday').value = new Date().toJSON().slice(0, 19);
    </script>
    <script>
        document.getElementById('datetimeLocalToday').value = new Date().toJSON().slice(0, 19);
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>

    <title>Workspace</title>
</head>
<body>
<fmt:bundle basename="local">
    <h2>
        Hello, ${pageContext.session.getAttribute('user').getName()} ${pageContext.session.getAttribute('user').getSurname()}</h2>

    <a href="/jsp/showOrders.jsp">My orders</a><br>
    Full list of available flights:
    <h3>Directions list:</h3>
    <c:set var="role" scope="session" value="${pageContext.session.getAttribute('user').getRole()}"/>
    <c:if test="${role == 3}">
        <form action="/addDirection" method="post">
            <table border="1">
                <tr>
                    <td><b> It looks like you're admin!</b></td>
                    <br>
                <tr>
                <tr>
                    <td> Departure</td>
                    <td> Departure Time</td>
                    <td> Destination</td>
                    <td> Destination Time</td>
                    <td> Basic Price (€)</td>
                    <td> Date multiplier</td>
                    <td> Fill multiplier</td>
                    <td> Capacity</td>
                <tr>

                <tr>
                    <td><input type="text" name="fieldDeparture" required placeholder="City departure"></td>
                    <td><input type="datetime-local" name="depTime" required id="datetimeLocalToday1"></td>
                    <td><input type="text" name="fieldDestination" required placeholder="City destination"></td>
                    <td><input type="datetime-local" name="destTime" required id="datetimeLocalToday2"></td>
                    <td><input type="text" name="basicPrice" required placeholder="basic price (€)"></td>
                    <td><input type="text"  name="dateMult" required placeholder="date multiplier"></td>
                    <td><input type="text"  name="fillMult" required placeholder="fill multiplier"></td>
                    <td><input type="text" name="capacity" required placeholder="capacity"></td>
                    <td><input type="submit" value="add"></td>
                <tr>
            </table>
        </form>
        <br>
    </c:if>


<zz:directions admin="${pageContext.session.getAttribute('user').getRole()}" page="${pageContext.session.getAttribute('page')}"/><br>

    <a href="/page?fwd=l&target=jsp/workspace.jsp"> << </a> ...${pageContext.session.getAttribute('page')}... <a href="/page?fwd=r&target=jsp/workspace.jsp"> >> </a><br>
    <a href="/logout"><fmt:message key="LogOut"/></a><br>
    <a href="/locale/ru_RU">ru</a> | <a href="/locale/en_US">en</a>
</fmt:bundle>
</body>
</html>
