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

<title><fmt:message key="role.page.title" /></title>
</head>
<body>

<div id="page-content" >
	<div class="page-header position-relative">
		<h1 class="left"><fmt:message key="role.page.title" /> </h1>
		<a href="role_edit" class="button blue right"><fmt:message key="role.add.title" /></a>
	</div><!--/page-header-->
	
	
	<table id="table_bug_report" class="table table-striped table-bordered table-hover table-odd">
			<thead>
				<tr>
					<th>ID</th>
					<th><fmt:message key="role.title" /></th>
					<th></th>
				</tr>
			</thead>
									
			<tbody>
			<c:forEach items="${list}" var="item">		
				<tr>					
					<td>${item.id}</td>
					<td>${item.title}</td>
					<td><a href="role_edit?id=${item.id}" ><fmt:message key="edit" /></a> | 
					<a class="delete" href="${item.id}" ><fmt:message key="delete" /></a></td>
				</tr>
    </c:forEach>	
				
			</tbody>
		</table>
		<div class="page">${page}</div>
</div><!--/#page-content-->
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script>
<script type="text/javascript">		

$(document).ready(function() {
	$('a.delete').click(function (e) {//处理删除
		var cid = $(this).attr("href");
		$( "#dialog-confirm" ).html("确定要删除么?");
		
		$( "#dialog-confirm" ).dialog({
		      resizable: false,
		      height:140,
		      modal: true,
		      title: "删除确认？",
		      buttons: {
		        "确定": function() {
		        	location.href="role_del?id=" + cid;
		          $( this ).dialog( "close" );
		        },
		        "取消": function() {
		          $( this ).dialog( "close" );
		        }
		      }
		  });
		 e.preventDefault();
		 $(".ui-dialog-buttonset:first button:last").focus();
    });
	
})
</script>
<div id="dialog-confirm" style="display:none"></div>
</body>
</html>