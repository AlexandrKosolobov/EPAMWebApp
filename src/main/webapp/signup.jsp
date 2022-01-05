<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE>
<html lang="en">
<head>
    <title>Title</title>
</head>
<body>

<form action='controller' method="post">
    <label>
        <input type='text' name='username' placeholder='Username' required>
    </label>
    <br/>
    <label>
        <input type='text' name='password' placeholder='Password' required>
    </label>
    <br/>
    <label>
        <input type='checkbox' name='user_role'>I am Barber
    </label>
    <br/>
    <label>
        <input type='file' name='certificate_file' placeholder='Certificate'>
    </label>
    <br/>
    <input type='submit' name='register' value='Register'>
    <br/>
    <label>
        <input type="hidden" name="command" value="ADD_USER">
    </label>
</form>

</body>
</html>
