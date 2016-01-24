<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="zz" %>
<html>
<head>
    <title></title>
</head>
<body>
  <h1>Your orders:</h1>
<zz:orders userId="${pageContext.session.getAttribute('user').getId()}"/>
</body>
</html>
