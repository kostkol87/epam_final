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
    <title><fmt:message key="DirRemoved"/></title>
</head>
<body>
<div class="top-menu">
    <a href="/locale?loc=ru_RU&target=${pageContext.request.servletPath}">ru</a> <a
        href="/locale?loc=en_US&target=${pageContext.request.servletPath}">en</a>

    <h2 id="status"><fmt:message key="DirRemoved"/></h2><b/>
    <input type="button" value="<fmt:message key="Home"/> " onclick="window.location = '/login'"></a>
        <span class="right">
        </span>

</div>


<form id="slick-login">
    <h1 id="error"><fmt:message key="DirRemovedOk"/></h1>
</form>


</fmt:bundle>
</body>
</html>