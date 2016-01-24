<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>payment</title>
</head>
<body>

<form action="/payOk">
  <h2>Payment:</h2>
  summa is ${pageContext.session.getAttribute('summa')}. <br>
  <input type="submit" value="Pay!">
</form>
<button value="Save!" onclick="window.location = '/saveOrder'"></button>

</body>
</html>
