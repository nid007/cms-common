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
		  <c:if test="${item.subList ==null }">
		    <a href="index.html" >
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
				<li><a href="elements.html"><i class="icon-double-angle-right"></i> ${si.title }</a></li>
			 </c:forEach>
		  	 </ul>
		  </c:if>
		</li>
    </c:forEach>

		<li>
		  <a href="typography.html">
			<i class="icon-text-width"></i>
			<span>Typography</span>
		  </a>
		</li>



		

		<li>

		  <a href="#" class="dropdown-toggle" >

			<i class="icon-desktop"></i>

			<span>UI Elements</span>

			<b class="arrow icon-angle-down"></b>

		  </a>
		  <ul class="submenu">
			<li><a href="elements.html"><i class="icon-double-angle-right"></i> Elements</a></li>
			<li><a href="buttons.html"><i class="icon-double-angle-right"></i> Buttons & Icons</a></li>
		  </ul>

		</li>



		

		<li>

		  <a href="tables.html">

			<i class="icon-list"></i>

			<span>Tables</span>

			

		  </a>

		</li>



		

		<li>

		  <a href="#" class="dropdown-toggle" >

			<i class="icon-edit"></i>

			<span>Forms</span>

			<b class="arrow icon-angle-down"></b>

		  </a>
		  <ul class="submenu">
			<li><a href="form-elements.html"><i class="icon-double-angle-right"></i> Form Elements</a></li>
			<li><a href="form-wizard.html"><i class="icon-double-angle-right"></i> Wizard & Validation</a></li>
		  </ul>

		</li>

		<li>

		  <a href="widgets.html">

			<i class="icon-list-alt"></i>

			<span>Widgets</span>

			

		  </a>

		</li>

		<li>

		  <a href="#" class="dropdown-toggle" >

			<i class="icon-file"></i>

			<span>Other Pages</span>

			<b class="arrow icon-angle-down"></b>

		  </a>
		  <ul class="submenu">
			<li><a href="pricing.html"><i class="icon-double-angle-right"></i> Pricing Tables</a></li>
			<li><a href="invoice.html"><i class="icon-double-angle-right"></i> Invoice</a></li>
			<li><a href="login.html"><i class="icon-double-angle-right"></i> Login & Register</a></li>
			<li><a href="error-404.html"><i class="icon-double-angle-right"></i> Error 404</a></li>
			<li><a href="error-500.html"><i class="icon-double-angle-right"></i> Error 500</a></li>
			<li><a href="blank.html"><i class="icon-double-angle-right"></i> Blank Page</a></li>
		  </ul>
		</li>
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