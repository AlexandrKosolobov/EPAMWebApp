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
    Username: ${requestScope.get("username")}
    <br/>
    Status: ${requestScope.get("user_role")}
    <br/>
    First name: ${requestScope.get("first_name")}
    <br/>
    Second name: ${requestScope.get("second_name")}
    <br/>
    Surname: ${requestScope.get("sur_name")}
    <br/>
    Email: ${requestScope.get("user_email")}
    <br/>
    Phone: ${requestScope.get("user_phone")}
    <br/>
    Description: ${requestScope.get("user_desc")}
    <br/>
    <input type='submit' value='Booking'>
</form>

</body>
</html>
