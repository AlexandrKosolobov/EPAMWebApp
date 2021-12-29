<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE>
<html lang="en">
<head>
    <title>BarberShop Manager</title>
</head>
<body>
<form action="controller">
    <label>
        <input type="text" name="username" placeholder="username">
    </label>
    <br/>
    <label>
        <input type="password" name="password" placeholder="password">
    </label>
    <br/>
    <input type="submit" value="Log in">
    <label>
        <input type="checkbox" name="remember">Remember me
    </label>
    <label>
        <input type="text" name="command" value="CHECK_USER" hidden>
    </label>
</form>
<form action="registration.jsp">
    <label>
        <input type="text" name="command" value="REG_USER" hidden>
    </label>
    <input type="submit" value="Registration">
</form>
<br/>
<label>
    ${param.get("checked") != null? "Incorrect password" : ""}
</label>
</body>
</html>