<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="zz" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="../statics/css/style.css"/>
    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="http://www.modernizr.com/downloads/modernizr-latest.js"></script>
    <script type="text/javascript" src="../statics/js/placeholder.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
    <fmt:bundle basename="local">
    <title><fmt:message key="yOrders"/></title>
</head>
<body>
<div class="top-menu">
    <a href="/logout"><fmt:message key="LogOut"/></a> <a href="/locale?loc=ru_RU&target=${pageContext.request.servletPath}">ru</a>  <a href="/locale?loc=en_US&target=${pageContext.request.servletPath}">en</a>
    <h2 id="status"><fmt:message key="yOrders"/></h2><b/>
    <input type="button" value="<fmt:message key="Home"/> " onclick="window.location = '/jsp/workspace.jsp'"></a>
    <b></b>
        <span class="right">
        </span>
</div>
<div>
    <zz:orders userId="${pageContext.session.getAttribute('user').getId()}"
                locale="${pageContext.session.getAttribute('locale')}"/>
</div>
</fmt:bundle>
</body>
</html>
