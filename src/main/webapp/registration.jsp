<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE>
<html lang="en">
<head>
    <title>Title</title>
</head>
<body>

<form action='controller'>
    <label>
        <input type='text' name='username' placeholder='username'>
    </label>
    <br/>
    <label>
        <input type='text' name='password' placeholder='password'>
    </label>
    <br/>
    <label>
        <input type='text' name='first_name' placeholder='First Name'>
    </label>
    <br/>
    <label>
        <input type='text' name='second_name' placeholder='Second Name'>
    </label>
    <br/>
    <label>
        <input type='text' name='sur_name' placeholder='Surname'>
    </label>
    <br/>
    <label>
        <input type='checkbox' name='is_barber'>I am Barber
    </label>
    <br/>
    <label>
        <input type='file' name='certificate' placeholder='Certificate' hidden>
    </label>
    <br/>
    <input type='submit' name='register' value='Register'>
    <br/>
</form>

</body>
</html>
