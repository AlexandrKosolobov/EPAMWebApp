<%--
  Created by IntelliJ IDEA.
  User: sasha
  Date: 12/28/2021
  Time: 6:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE>
<html lang="en">
<head>
    <title>Personal Area</title>
</head>
<body>

<form action="controller">
    username: ${requestScope.get("username")}
    <br/>
    First name: ${requestScope.get("first_name")}
    <br/>
    Second name: ${requestScope.get("second_name")}
    <br/>
    Surname: ${requestScope.get("sur_name")}
    <br/>
    Status: ${requestScope.get("is_barber") != null && requestScope.get("is_barber").equals("true") ? "Barber" : "Client"}
    <br/>
    <input type='submit' value='Booking'>
</form>

</body>
</html>
