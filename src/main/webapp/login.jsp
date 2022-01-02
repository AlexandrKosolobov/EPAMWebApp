<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE>
<html lang="en">
<head>
    <title>BarberShop Manager</title>
</head>
<body>
<form method="post" action="controller">
    <label>
        <input type="text" name="username" placeholder="username" required>
    </label>
    <br/>
    <label>
        <input type="password" name="password" placeholder="password" required>
    </label>
    <br/>
    <input type="submit" value="Log in">
    <label>
        <input type="checkbox" name="remember">Remember me
    </label>
    <label>
        <input type="hidden" name="command" value="CHECK_USER">
    </label>
</form>
<form method="post" action="controller">
    <label>
        <input type="hidden" name="command" value="SIGNUP_USER">
        ${cookie.get("username")}
    </label>
    <input type="submit" value="Sign up">
</form>
<br/>
<label>
    ${requestScope.get("error") != null? "Incorrect password" : ""}
</label>
</body>
</html>