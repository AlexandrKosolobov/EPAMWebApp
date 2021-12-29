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
    username: ${param.get("username")}
    <br/>
    password: ${param.get("password")}
    <br/>
    First name: ${param.get("first_name")}
    <br/>
    Second name: ${param.get("second_name")}
    <br/>
    Surname: ${param.get("sur_name")}
    <br/>
    Status: ${param.get("is_barber") != null ? "Barber" : "Client"}
    <br/>
    <input type='submit' value='Booking'>
</form>

</body>
</html>
