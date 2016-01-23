<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>--%>
<html>
<head>
    <title>new order</title>
</head>
<body>

<h2>Customize you order:</h2>

<form action="/orderConfirm">
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
                Passangers count:
            </td>
            <td>
                <input type="text" name="passengersCount" placeholder="Passangers count">
            </td>
        </tr>

        <tr>
            <td>
                Need baggage:
            </td>
            <td>
                <input type="checkbox" name="baggage" placeholder="Passangers count">
            </td>
        </tr>

        <tr>
            <td>
                Need priority queue:
            </td>
            <td>
                <input type="checkbox" name="priotityQueue" placeholder="Passangers count">
            </td>
        </tr>
    </table>
    confirm and go to the payment: <br>
    <input type="submit" value="Confirm">
</form>
</body>
</html>
