<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
    <head>
        <title> </title>
    </head>
    <body>
       
        <form action="../j_spring_security_check" method="post">
            <div><label><fmt:message key="login.username" />: <input type="text" name="j_username"/> </label></div>
            <div><label> <fmt:message key="login.password" />: <input type="password" name="j_password"/> </label></div>
            <div><input type="submit" value="<fmt:message key='login.button' />"/></div>
        </form>
    </body>
</html>