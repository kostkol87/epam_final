<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    ${pageContext.session.getAttribute("user") == null? pageContext.forward("/jsp/loginFail.jsp"): pass}
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="../statics/css/style.css"/>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="http://www.modernizr.com/downloads/modernizr-latest.js"></script>
    <script type="text/javascript" src="../statics/js/placeholder.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
    <fmt:bundle basename="local">
    <title><fmt:message key="newOrder"/></title>
</head>
<body>
<div class="top-menu">
    <a href="/logout"><fmt:message key="LogOut"/></a> <a href="/locale?loc=ru_RU&target=${pageContext.request.servletPath}">ru</a>  <a href="/locale?loc=en_US&target=${pageContext.request.servletPath}">en</a>

    <h2 id="status"><fmt:message key="Customize"/></h2><b/>
    <input type="button" value="<fmt:message key="Home"/> " onclick="window.location = '/jsp/workspace.jsp'"></a>
        <span class="right">
        </span>
</div>
<div>
    <h3 id="text"><fmt:message key="attention"/> </h3>

    <form action="/payment" method="post">
        <table border="1">
            <tr>
                <td>
                    <fmt:message key="Departure"/>
                </td>
                <td>
                        ${pageContext.session.getAttribute("newOrder").getDeparture()}
                </td>
            </tr>

            <tr>
                <td>
                    <fmt:message key="Destination"/>:
                </td>
                <td>
                        ${pageContext.session.getAttribute("newOrder").getDestination()}
                </td>
            </tr>


            <tr>
                <td>
                    <fmt:message key="DepTime"/>:
                </td>
                <td>
                        ${pageContext.session.getAttribute("newOrder").getDepTime()}
                </td>
            </tr>

            <tr>
                <td>
                    <fmt:message key="DestTime"/>:
                </td>
                <td>
                        ${pageContext.session.getAttribute("newOrder").getDestTime()}
                </td>
            </tr>


            <tr>
                <td>
                    <fmt:message key="Price"/>:
                </td>
                <td>
                        ${pageContext.session.getAttribute("newOrder").getBasicPrice()}
                </td>
            </tr>


            <tr>
                <td>
                    <fmt:message key="PassCount"/>:
                </td>
                <td>
                    <input type="text" name="passengersCount" required placeholder="Passangers count">
                </td>
            </tr>

            <tr>
                <td>
                    <fmt:message key="NeedBaggage"/>
                </td>
                <td>
                    <input type="checkbox" name="baggage">
                </td>
            </tr>

            <tr>
                <td>
                    <fmt:message key="NeedPriority"/>(+30€):
                </td>
                <td>
                    <input type="checkbox" name="priotityQueue">
                </td>
            </tr>
        </table>
        <br>

        <h3 id="text"> <fmt:message key="confirtToPay"/> : </h3><br>
        <input id="small-btn" type="submit" value="<fmt:message key='Confirm'/> ">
    </form>
</div>
</fmt:bundle>
</body>
</html>
