<%@page pageEncoding="UTF-8"%>
<%@ include file = "common/include.jsp"%>
<head>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META name="DownloadOptions" content="noopen">
<link rel="bookmark" href="favicon.ico" type="image/x-icon" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<title><bean:message key="baseDef.title"/></title>
</head>

<frameset framespacing="0" border="0" frameborder="yes" rows="70,*">
	<frame name="banner" scrolling="no" noresize src="header.do" marginwidth="0" marginheight="0">
	<frameset cols="210,*">
		<frame name="menu" src="getMenu.do?method=getUserMenu" marginwidth="0" marginheight="0" scrolling="auto">
		<frame name="main" src="about.do" noResize marginwidth="0" marginheight="0" scrolling="auto">
	</frameset>
<!--	<frame name="footer" src="footer.do" noResize scrolling=no marginwidth="0" marginheight="0">-->
	<noframes>
	<body>

	<p>This page use fram,but your brower can't suport fram!</p>

	</body>
	</noframes>
</frameset>