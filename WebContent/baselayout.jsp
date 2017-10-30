<%@ include file="WEB-INF/pages/common/include.jsp"%>
<html:html locale="true">
<head>


<!-- ++++++++++ Style Sheet ++++++++++ -->
<META http-equiv="Content-Style-Type" content="text/css">
<%@ include file="WEB-INF/pages/common/style.jsp"%>
<html:base/>

<link rel="bookmark" href="favicon.ico" type="image/x-icon" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<title><bean:message key="baseDef.title"/></title>

<script type="text/javascript" src="<c:url value='/pages/scripts/selectbox.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/pages/scripts/global.js'/>" charset="gbk"></script>
<script type="text/javascript" src="<c:url value='/pages/scripts/calendar.js'/>" charset="gbk"></script>
</head>

<body topmargin="0" leftmargin="0" rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0" onkeydown="KeyDown()"  bgcolor="#c0d2ec">
    <!---------------------- include header start --------------------------->
    <tiles:insert attribute="header"/>
    <!---------------------- include header end ----------------------------->
    
    <!---------------------- include body start ----------------------------->
    <tiles:insert attribute="body"/>
    <!---------------------- include body end ------------------------------->
    
    <!---------------------- include footer start --------------------------->
    <tiles:insert attribute="footer"/>
    <!---------------------- include footer end ----------------------------->
</body>
</html:html>
