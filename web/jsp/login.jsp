<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
  <fmt:bundle basename="local">
  <title><fmt:message key="LogIn"/></title>
</head>
<body>
<h1><fmt:message key="LogIn"/> </h1>
<form action="/login" method="post">
  <input placeholder="<fmt:message key="login"/>" name="loginField"><br>
  <input type="password" autocomplete="off" placeholder="<fmt:message key="password" />" name="passField"><br>
  <input type="submit" value="<fmt:message key="LogIn"/>">
  <input type="button" value="<fmt:message key="Home"/> " onclick="history.back()">
</form>
<a href="/ru_RU">ru</a> | <a href="/en_US">en</a>
</fmt:bundle>
</body>
</html>