<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="../statics/css/style.css" />
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="http://www.modernizr.com/downloads/modernizr-latest.js"></script>
	<script type="text/javascript" src="../statics/js/placeholder.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<fmt:setLocale value="${pageContext.session.getAttribute('locale')}"/>
	<fmt:bundle basename="local">
	<title><fmt:message key="LogIn"/></title>
</head>
<body>

<div class="top-menu">
	<a href="/locale?loc=ru_RU&target=${pageContext.request.servletPath}">ru</a> <a href="/locale?loc=en_US&target=${pageContext.request.servletPath}">en</a>
	<h2 id="status"><fmt:message key="Registration"/> </h2>
	<input type="button" value="<fmt:message key="Home"/> " onclick="window.location = '/'"></a>
        <span class="right">
        </span>
	<div class="clr"></div>
</div>

	<form id="slick-login" action="/register" method="post">
		<label for="username"><fmt:message key="login"/></label>	<input type="text" class="placeholder" placeholder="<fmt:message key="login"/>" required name="loginField"><br>
		<label for="password"><fmt:message key="login"/></label>	<input type="password" autocomplete="off" class="placeholder" placeholder="<fmt:message key="password"/>" required name="passField"><br>
		<label for="surname"><fmt:message key="login"/></label>		<input type="text" class="placeholder" placeholder="<fmt:message key="sName"/>" required name="sNameField"><br>
		<label for="name"><fmt:message key="login"/></label>		<input type="text" class="placeholder" placeholder="<fmt:message key="Name"/>" required name="nameField"><br>
		<label for="patronomic"><fmt:message key="login"/></label>	<input type="text" class="placeholder" placeholder="<fmt:message key="Patronomic"/>" required name="patronomicField"><br>

		<input type="submit" value="<fmt:message key='Registration'/> ">
	</form>
</fmt:bundle>
</body>
</html>