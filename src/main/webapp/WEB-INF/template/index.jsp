<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><fmt:message key="project.title" /></title>
</head>
<frameset rows="45,*,22" frameborder="no" border="0" framespacing="0">
  <frame src="sys/top" name="topFrame" scrolling="No" noresize="noresize" id="topFrame"/>
  <frameset id="mainframeset" rows="*" cols="172,10,*">
    <frame src="sys/left"  name="leftFrame" scrolling="auto" noresize="noresize" id="leftFrame"/>
    <frame src="sys/leftbar" id="leftbar" scrolling="no" noresize="noresize" name="switchLeftFrame" />
    <frame src="sys/welcome" name="mainFrame" scrolling="auto" noresize="noresize" id="mainFrame">
  </frameset>
  <frame src="sys/footer" name="footFrame" scrolling="No" noresize="noresize" id="bottomFrame"/>
</frameset>
<noframes><body>
</body></noframes>
</html>