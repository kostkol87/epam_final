<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
  <fmt:bundle basename="local">
    <title>change order</title>
</head>
<body>
<h1>Change order:</h1>

<b>attention, options you have choised will be accepted for all passangers!</b>
<form action="/payment">
  <table border="1">

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
<input type="submit" value="<fmt:message key="Submit"/>"/><br>
<input type="button" value="<fmt:message key="Home"/> " onclick="window.location = '/jsp/workspace.jsp'"><br>
<a href="/locale/ru_RU">ru</a> | <a href="/locale/en_US">en</a>
</fmt:bundle>
</body>
</html>
