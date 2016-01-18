<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <fmt:setLocale value="ru_RU"/>

  <fmt:bundle basename="local">
  <title><fmt:message key="LogIn"/></title>
</head>
<body>
<%--<img src="${pageContext.request.contextPath}/images/rf.gif" alt="">--%>
<%--<img src="${pageContext.request.contextPath}/images/uk.gif" alt="">--%>



<p><fmt:message key="Locale"/></p>
  1. <fmt:message key="login"/><br/>
  2. <fmt:message key="password"/><br/>
</fmt:bundle>
</body>
</html>