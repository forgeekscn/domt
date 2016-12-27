<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<title>陕西杰信商务综合管理平台</title>
</head>
<frameset rows="125,*" name="topFrameset" border="0">
	<frame name="top_frame" scrolling="no"  target="middleFrameSet" src="title.action">	
	<frameset cols="202,*" height="100%" name="middle" frameborder="no" border="0" framespacing="0">
		<frame name="leftFrame" class="leftFrame" target="main" scrolling="no" src="left.action" />
		<frame name="main" class="rightFrame" src="main.action" />
	</frameset>
</frameset>

<noframes>
<body>
    <p>此网页使用了框架，但您的浏览器不支持框架。</p>
</body>
</noframes>

</html>