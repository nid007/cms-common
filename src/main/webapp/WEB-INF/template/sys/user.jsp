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

<title><fmt:message key="user.page.title" /></title>
</head>
<body>

<div id="page-content" >
	<div class="page-header position-relative">
		<h1 class="left"><fmt:message key="user.page.title" /> </h1>
		<a href="user_add" class="button blue right"><fmt:message key="user.add.title" /></a>
	</div><!--/page-header-->
	<div id="search-bar">
		<form method="get">
			用户名：<input type="text" name="username" value="<c:out value='${username}' />"/>
			每页显示<input style="width:20px" type="text" name="pagesize" size="1" value="<c:out value='${pagesize}' />"/>
			<input type="submit" value="搜索" />
		</form>
	</div>
	
	<table id="table_bug_report" class="table table-striped table-bordered table-hover table-odd">
			<thead>
				<tr>
					<th><fmt:message key="login.username" /></th>
					<th><fmt:message key="user.enable" /></th>
							
					<th></th>
				</tr>
			</thead>
									
			<tbody>
			<c:forEach items="${result.result}" var="item">		
				<tr>					
					<td>${item.username}</td>
					<td><a class="enable" href="${item.username}" >${cms:enableStr(item.enabled)}</a></td>
					<td>
					<a class="reset_pass" href="${item.username}" ><fmt:message key="reset_pass" /></a> |
					<a href="user_authority?username=${item.username}" ><fmt:message key="authority" /></a> |
					<a href="user_detail?username=${item.username}" ><fmt:message key="detail" /></a> | 
					<a class="delete" href="${item.username}" ><fmt:message key="delete" /></a></td>
				</tr>
    </c:forEach>	
				
			</tbody>
		</table>
		<div class="page">${page}</div>
</div><!--/#page-content-->
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script>
<script type="text/javascript">		
function update(ele,username){
	var load = document.createElement("span");
	load.innerHTML = "loading...";
	ele.parent().append(load);
	ele.css("display","none");
	$.getJSON( "user_mod_enable?username=" + username, function( data ) {
		  ele.html(data.enableStr);
		}).done(function(){
			ele.next().remove();
			ele.css("display","block");
		}).fail(function(){
			load.innerHTML = "请求失败";
		});
}
function reset(ele,username){
	
	$.getJSON( "admin_pass_reset?username=" + username, function( data ) {
		   $( "#dialog-confirm" ).html("密码重置为：" +data.newpass);
		
			$( "#dialog-confirm" ).dialog({
		      resizable: false,
		      height:140,
		      modal: true,
		      title: "密码重置成功",
		      buttons: {
		        "确定": function() {
		          $( this ).dialog( "close" );
		        }
		      }
			});
		}).done(function(){
			
		}).fail(function(){
			alert("请求失败");
		});
}
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
		        	location.href="user_del?username=" + cid;
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
	$('a.reset_pass').click(function (e) {//密码重置
		var username = $(this).attr("href");
		var a =  $(this);
		$( "#dialog-confirm" ).html("确定要重置么?");
		
		$( "#dialog-confirm" ).dialog({
		      resizable: false,
		      height:140,
		      modal: true,
		      title: "确认修改？",
		      buttons: {
		        "确定": function() {
		        	reset(a,username);
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
	$('a.enable').click(function (e) {//处理激活
		var username = $(this).attr("href");
		var a =  $(this);
		$( "#dialog-confirm" ).html("确定要修改么?");
		
		$( "#dialog-confirm" ).dialog({
		      resizable: false,
		      height:140,
		      modal: true,
		      title: "确认修改？",
		      buttons: {
		        "确定": function() {
		        	update(a,username);
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