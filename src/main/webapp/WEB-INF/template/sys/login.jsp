<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
 	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <title><fmt:message key="login.title" /> </title>
     <link rel="stylesheet" href="../css/style.css" />
<style type="text/css">    
#login{
	margin:10% auto;
	width:300px;
	text-align:center;		
}
#logintable td{
	border:0;
}
#logintable{
	
	border: 1px solid #ddd;
}
</style>
<script language="javascript">
function location_top(){
    if(top.location!=self.location){
        top.location=self.location;
        return false;		
    }
    return true;
}
location_top(); // 调用
</script>
</head>
<body>
 <form action="../j_spring_security_check" method="post">	
    <div id="login">
    
    <table id="logintable" class="table table-bordered">
	<thead>
		<tr>
			<th colspan="2"><fmt:message key="login.title" /></th>
		</tr>
	</thead>
						
	<tbody>
		<tr>					
			<td><fmt:message key="login.username" />: </td>
			<td><input type="text" name="j_username"/></td>					
		</tr>
	 		<tr>
			<td><fmt:message key="login.password" />: </td>
			<td><input type="password" name="j_password"/></td>					
		</tr>
	 	<tr>					
			<td></td>
			<td><input type="submit" value="<fmt:message key='login.button' />"/></td>					
		</tr>
	</tbody>
	</table>
	
	</div>
	</form>
</body>
</html>