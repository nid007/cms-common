<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<h1 class="left"><fmt:message key="module.page.title" /> </h1>
		<a href="#" class="button blue right">button</a>
		<a href="#" class="button blue right">button</a>
	</div><!--/page-header-->
	
	
	<table id="table_bug_report" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th><fmt:message key="module.id" /></th>
					<th><fmt:message key="module.parent" /></th>
					<th><fmt:message key="module.title" /></th>				
					<th></th>
				</tr>
			</thead>
									
			<tbody>
			<c:forEach items="${list}" var="item">		
				<tr class="oddrow">					
					<td>${item.module.moduleId}</td>
					<td>${item.module.parentId}</td>
					<td>${item.module.title}</td>
					<td></td>
				</tr>
		  <c:if test="${item.subList !=null }">
		   
		  	 <c:forEach items="${item.subList}" var ="si">
				<tr>					
					<td>${si.moduleId}</td>
					<td>${si.parentId}</td>
					<td>${si.title}</td>
					<td></td>
				</tr>
			 </c:forEach>
		  </c:if>
    </c:forEach>	
				
			</tbody>
		</table>
</div><!--/#page-content-->
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript">		
$(document).ready(function() {
	$('a.dropdown-toggle').click(function (e) {
        var dropDown = $(this).next();
        $('.dropdown').not(dropDown).slideUp('fast');
        dropDown.slideToggle('fast');
        e.preventDefault();
    })
})
</script>
</body>
</html>