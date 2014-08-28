<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/style.css" />
<title><fmt:message key="project.title" /></title>
<style type="text/css">    

.but {   
	position: absolute;     
	top: 40%; 
	width:10px;
	height:30px;
	
	margin-left:0;
	margin-top:0;
}    

.left:after{
	display: inline-block;
	content: "";
	position: absolute!important;
	right: 0;
	top: 4px;
	border: 8px solid transparent;
	border-width: 24px 10px;
	border-right-color: #0b6cbc;
}
.right:after{
	display: inline-block;
	content: "";
	position: absolute!important;
	left: 0;
	top: 4px;
	border: 8px solid transparent;
	border-width: 24px 10px;
	border-left-color: #0b6cbc;
}
</style>   
</head>
<body marginwidth="0" marginheight="0" topmargin="0" leftmargin="0">
<a id="switchBar" href="#" title="隐藏左侧菜单">
<span id="divBar" class="but left" ></span>
</a>
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript">		
$(document).ready(function() {
	var btn = $("#divBar");
	var link = $("#switchBar");
	var maincols =  parent.document.getElementById('mainframeset').cols;
	link.click(function(e){
		if(btn.attr("class")=="but right"){
			parent.document.getElementById('mainframeset').cols=maincols;
			btn.attr("class","but left");
			link.attr("title","隐藏左侧菜单");
			
		}else{
			maincols = parent.document.getElementById('mainframeset').cols;;
			parent.document.getElementById('mainframeset').cols="0,10,*";
			btn.attr("class","but right");
			link.attr("title","展开左侧菜单");
		}	
		e.preventDefault();
	});	

});
</script>
</body>
</html>