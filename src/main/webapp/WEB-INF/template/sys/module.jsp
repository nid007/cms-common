<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/cms.tld" prefix="cms" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/style.css" />
<link rel="stylesheet" href="../css/jquery-ui.css" />

<title><fmt:message key="module.page.title" /></title>
</head>
<body>

<div id="page-content" >
	<div class="page-header position-relative">
		<h1 class="left"><fmt:message key="module.page.title" /> </h1>
		<a href="module_edit" class="button blue right"><fmt:message key="module.add.title" /></a>
	</div><!--/page-header-->
	
	
	<table id="table_bug_report" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th><fmt:message key="module.id" /></th>
					<th><fmt:message key="module.parent" /></th>
					<th><fmt:message key="module.title" /></th>				
					<th><fmt:message key="module.enable" /></th>				
					<th></th>
				</tr>
			</thead>
									
			<tbody>
			<c:forEach items="${list}" var="item">		
				<tr class="oddrow">					
					<td>${item.module.moduleId}</td>
					<td>${item.module.parentId}</td>
					<td>${item.module.title}</td>
					<td>${cms:enable(item.module.enabled)}</td>
					<td><a href="module_edit?id=${item.module.id}" ><fmt:message key="edit" /></a> | 
					<a class="delete" href="${item.module.id}" ><fmt:message key="delete" /></a></td>
				</tr>
		  <c:if test="${item.subList !=null }">
		   
		  	 <c:forEach items="${item.subList}" var ="si">
				<tr>					
					<td>${si.moduleId}</td>
					<td>${si.parentId}</td>
					<td>${si.title}</td>
					<td>${cms:enable(si.enabled)}</td>
					<td><a href="module_edit?id=${si.id}" ><fmt:message key="edit" /></a> | 
					<a class="delete" href="${si.id}" ><fmt:message key="delete" /></a></td>
				</tr>
			 </c:forEach>
		  </c:if>
    </c:forEach>	
				
			</tbody>
		</table>
</div><!--/#page-content-->
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script>
<script type="text/javascript">		
$(document).ready(function() {
	$('a.delete').click(function (e) {
		var cid = $(this).attr("href");
		$( "#dialog-confirm" ).dialog({
		      resizable: false,
		      height:156,
		      modal: true,
		      buttons: {
		        "确定": function() {
		        	location.href="module_del?id=" + cid;
		          $( this ).dialog( "close" );
		        },
		        "取消": function() {
		          $( this ).dialog( "close" );
		        }
		      }
		  });
		 e.preventDefault();
		 $(".ui-dialog-buttonset:first button:last").focus();
    })
})
</script>
<div id="dialog-confirm" style="display:none" title="删除确认?">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>确定要删除么?</p>
</div>
</body>
</html>