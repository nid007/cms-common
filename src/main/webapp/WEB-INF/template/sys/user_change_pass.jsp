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
<link rel="stylesheet" href="../css/jquery-ui.css" />
<title><fmt:message key="user.page.title" /></title>
<script type="text/javascript" src="../js/jquery.min.js">
<script type="text/javascript" src="../js/jquery-ui.js"></script>

</head>
<body>

<div id="page-content" >
	<div class="page-header position-relative">
		<h1 class="left"><fmt:message key="user.add.title" /> </h1>
	</div><!--/page-header-->
	
	<div class="row-fluid">
<!-- PAGE CONTENT BEGINS HERE -->
	<form method="post">
	 
	 
	<table class="formtable">
	<tr>
		<td><fmt:message key="oldpass" /></td>
		<td><input type="password" id="oldpass" name="oldpass" class="required"  /></td>
	</tr>
	<tr>
		<td><fmt:message key="newpass" /></td>
		<td><input type="password" id="newpass" name="newpass"  class="required" /></td>
	</tr>	
	<tr>
		<td><fmt:message key="newpass2" /></td>
		<td><input type="password" id="newpass2" name="newpass2"  class="required" /></td>
	</tr>

	
	<tr>
		<td></td>
		<td><input type="submit" id="send" class="s_btn" value="提交">${message}</td>
	</tr>
	</table>
	
	<form>
	</div>
</div><!--/#page-content-->
<div id="dialog-confirm" style="display:none"></div>
<script type="text/javascript">
//<![CDATA[
$(function(){
        /*
        *思路大概是先为每一个required添加必填的标记，用each()方法来实现。
        *在each()方法中先是创建一个元素。然后通过append()方法将创建的元素加入到父元素后面。
        *这里面的this用的很精髓，每一次的this都对应着相应的input元素，然后获取相应的父元素。
        *然后为input元素添加失去焦点事件。然后进行用户名、邮件的验证。
        *这里用了一个判断is()，如果是用户名，做相应的处理，如果是邮件做相应的验证。
        *在jQuery框架中，也可以适当的穿插一写原汁原味的javascript代码。比如验证用户名中就有this.value，和this.value.length。对内容进行判断。
        *然后进行的是邮件的验证，貌似用到了正则表达式。
        *然后为input元素添加keyup事件与focus事件。就是在keyup时也要做一下验证，调用blur事件就行了。用triggerHandler()触发器，触发相应的事件。
        *最后提交表单时做统一验证
        *做好整体与细节的处理
        */
        //如果是必填的，则加红星标识.
        $("form :input.required").each(function(){
            var $required = $("<strong class='high'> *</strong>"); //创建元素
            $(this).parent().append($required); //然后将它追加到文档中
        });
         //文本框失去焦点后
        $('form :input').blur(function(){
             var $parent = $(this).parent();
             $parent.find(".formtips").remove();
             //验证用户名
             if( $(this).is('#oldpass') ){
                    if( this.value=="" ){
                        var errorMsg = '请输入旧密码.';
                        $parent.append('<span class="formtips onError">'+errorMsg+'</span>');
                    }
             }
			  if( $(this).is('#newpass') ){
                    if( this.value=="" ){
                        var errorMsg = '请输入新密码.';
                        $parent.append('<span class="formtips onError">'+errorMsg+'</span>');
                    }
             }
              if( $(this).is('#newpass2') ){
                    if( this.value!=$("#newpass").val() ){
                        var errorMsg = '两次输入的新密码不一致.';
                        $parent.append('<span class="formtips onError">'+errorMsg+'</span>');
                    }
             }
        }).keyup(function(){
           $(this).triggerHandler("blur");
        }).focus(function(){
             $(this).triggerHandler("blur");
        });//end blur

        
        //提交，最终验证。
         $('#send').click(function(){
                $("form :input.required").trigger('blur');
                var numError = $('form .onError').length;
                if(numError){
                    return false;
                } 
               
         });
})
//]]>
</script>

</body>
</html>