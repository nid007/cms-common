<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/cms.tld" prefix="cms" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/style.css" />
<link rel="stylesheet" href="../css/jquery-ui.css" />

<title><fmt:message key="group.authority.title" /></title>
</head>
<body>

<div id="page-content" >
	<div class="page-header position-relative">
		<h1 class="left"><fmt:message key="group.authority.title" /> - ${title } </h1>		
	</div>
	
	<div class="box-content">
	<form method="post">
	 <c:forEach items="${list}" var="item">		
	 
		<input <c:if test="${item.selected}">checked="checked"</c:if> name="roles" type="checkbox" value="${item.title}" />${item.title} &nbsp;&nbsp;
    </c:forEach>	
	</div>
	<div>
		<input type="submit" class="s_btn" value="提交">
	</div>
	</form>
	
</div><!--/#page-content-->


</body>
</html>