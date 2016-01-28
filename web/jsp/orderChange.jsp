<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
  <fmt:bundle basename="local">
    <title><fmt:message key="changeOrder"/> </title>
</head>
<body>
<h1><fmt:message key="changeOrder"/>:</h1>

<b><fmt:message key="attention"/> </b>
<form action="/changeComplete">
  <table border="1">

    <tr>
      <td>
        <fmt:message key='PassCount'/>:
      </td>
      <td>
        <input type="text" name="<fmt:message key='PassCount'/>" required placeholder="<fmt:message key='PassCount'/>">
      </td>
    </tr>

    <tr>
      <td>
        <fmt:message key='NeedBaggage'/>(+45€):
      </td>
      <td>
        <input type="checkbox" name="<fmt:message key='Baggage'/>">
      </td>
    </tr>

    <tr>
      <td>
        <fmt:message key='NeedPriority'/>(+30€):
      </td>
      <td>
        <input type="checkbox" name="<fmt:message key='Priority'/>">
      </td>
    </tr>
  </table>
  <br>
<input type="submit" value="<fmt:message key="Submit"/>"/><br>
<input type="button" value="<fmt:message key="Home"/> " onclick="window.location = '/jsp/workspace.jsp'"><br>
  <a href="/locale?loc=ru_RU&target=${pageContext.request.servletPath}">ru</a> | <a href="/locale?loc=en_US&target=${pageContext.request.servletPath}">en</a>
</fmt:bundle>
</body>
</html>
