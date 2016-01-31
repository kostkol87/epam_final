<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <title><fmt:message key="WelcomeStr"/></title>

</head>

<body>
<div class="top-menu">
    <a href="/locale?loc=ru_RU&target=${pageContext.request.servletPath}">ru</a> <a
        href="/locale?loc=en_US&target=${pageContext.request.servletPath}">en</a>

    <b></b>

    <h2 id="status"><fmt:message key="WelcomeStr"/></h2><b/>
        <span class="right">
        </span>

</div>

<div id="main-div">

    <h2>
        <fmt:message key="MakeChoice"/><br>

        <button onclick="window.location = '/login'"><fmt:message key='LogIn'/></button>
        <button onclick="window.location = '/jsp/register.jsp'"><fmt:message key="Registration"/></button>
    </h2>

</fmt:bundle>
</body>
</html>
