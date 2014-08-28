<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head> 
    <title>Getting Started: Serving Web Content</title> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    Hello, ${name}!
    
    <c:forEach items="${atts}" var="item">
    ${item}<br />
    </c:forEach>
    
    <% out.print("xxx"); %>
</body>
</html>
