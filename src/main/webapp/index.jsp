<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html lang="en">
    <head>
        <title>BarberShopManager</title>
    </head>
    <body>
    <form action="controller" method="post">
        <label>
            <button>Log in</button>
        </label>
        <input type="text" name="command" value="LOGIN" hidden>
    </form>
    <form action="signup.jsp">
        <label>
            <button>Sign up</button>
        </label>
    </form>
    <c:if test="${requestScope.error != null}">
        <label>${requestScope.error}</label>
    </c:if>
    </body>
</html>