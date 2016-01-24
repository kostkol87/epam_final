<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>new order</title>
</head>
<body>

<h2>Customize you order:</h2>
<b>attention, options you have choised will be accepted for all passangers!</b>
<form action="/payment">
    <table border="1">
        <tr>
            <td>
                Departure:
            </td>
            <td>
                ${pageContext.session.getAttribute("newOrder").getDeparture()}
            </td>
        </tr>

        <tr>
            <td>
                Destination:
            </td>
            <td>
                ${pageContext.session.getAttribute("newOrder").getDestination()}
            </td>
        </tr>


        <tr>
            <td>
                Departure time and date:
            </td>
            <td>
                ${pageContext.session.getAttribute("newOrder").getDepTime()}
            </td>
        </tr>

        <tr>
            <td>
                Destination time and date:
            </td>
            <td>
                ${pageContext.session.getAttribute("newOrder").getDestTime()}
            </td>
        </tr>


        <tr>
            <td>
                Basic price (€):
            </td>
            <td>
                ${pageContext.session.getAttribute("newOrder").getBasicPrice()}
            </td>
        </tr>


        <tr>
            <td>
                Passangers count:
            </td>
            <%--doesn't work =(--%>
            ${pageContext.getAttribute("capacityFail") ? "not enough tikets" : " "}
            <td>
                <input type="text" name="passengersCount" required placeholder="Passangers count">
            </td>
        </tr>

        <tr>
            <td>
                Need baggage(+45€):
            </td>
            <td>
                <input type="checkbox" name="baggage">
            </td>
        </tr>

        <tr>
            <td>
                Need priority queue(+30€):
            </td>
            <td>
                <input type="checkbox" name="priotityQueue">
            </td>
        </tr>
    </table>
    <br>

    confirm and go to the payment: <br>
    <input type="submit" value="Confirm">
    ">
</form>
</body>
</html>
