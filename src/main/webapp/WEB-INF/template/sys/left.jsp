<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/style.css" />
<title><fmt:message key="project.title" /></title>
</head>
<body>
<div id="sidebar">
	
	<ul class="nav nav-list">
	<c:forEach items="${list}" var="item">		
		<li>
		  <c:if test="${item.subList ==null}">
		    <a href="${item.module.link}"  target="mainFrame">
			  <i class="icon-dashboard"></i>
			  <span>  ${item.module.title}</span>
		    </a>
		  </c:if>
		  <c:if test="${item.subList !=null }">
		    <a href="index.html" class="dropdown-toggle">
			  <i class="icon-dashboard"></i>
			  <span>  ${item.module.title}</span>
		    </a>
		  	 <ul class="submenu">
		  	 <c:forEach items="${item.subList}" var ="si">
				<li><a href="${si.link}" target="mainFrame"><i class="icon-double-angle-right"></i> ${si.title }</a></li>
			 </c:forEach>
		  	 </ul>
		  </c:if>
		</li>
    </c:forEach>		
	</ul><!--/.nav-list-->

	<div id="sidebar-collapse"><i class="icon-double-angle-left"></i></div>
</div><!--/#sidebar-->
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