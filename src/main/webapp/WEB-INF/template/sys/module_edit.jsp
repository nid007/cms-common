<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/style.css" />
<title><fmt:message key="module.page.title" /></title>
</head>
<body>

<div id="page-content" >
	<div class="page-header position-relative">
		<h1 class="left"><fmt:message key="module.add.title" /> </h1>
	</div><!--/page-header-->
	
	<div class="row-fluid">
<!-- PAGE CONTENT BEGINS HERE -->
	<form:form commandName="module">
	 <form:hidden path="id" /> 
	 
	<table class="formtable">
	<tr>
		<td><fmt:message key="module.parent" /></td>
		<td><form:input path="parentId"  /></td>
	</tr>
	<tr>
		<td><fmt:message key="module.id" /></td>
		<td><form:input path="moduleId"  /></td>
	</tr>
	<tr>
		<td><fmt:message key="module.title" /></td>
		<td><form:input path="title"  /></td>
	</tr>
	<tr>
		<td><fmt:message key="module.link" /></td>
		<td><form:input path="link"  /></td>
	</tr>
	<tr>
		<td><fmt:message key="authority" /></td>
		<td><form:select path="authority" items="${roles}" itemValue="title" itemLabel="title">
				
			</form:select>
		</td>
	</tr>
	<tr>
		<td><fmt:message key="module.enable" /></td>
		<td><form:checkbox path="enabled" value="1"  /><fmt:message key="yes" /></td>
	</tr>
	<tr>
		<td><fmt:message key="note" /></td>
		<td><form:input path="note"  /></td>
	</tr>
	
	<tr>
		<td></td>
		<td><input type="submit" class="s_btn" value="提交"></td>
	</tr>
	</table>
	<input type="hidden" name="enabled"  value="0" />
	</form:form>
	</div>
</div><!--/#page-content-->

</body>
</html>