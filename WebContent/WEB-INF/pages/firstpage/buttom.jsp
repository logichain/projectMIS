<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>
<%String style = (session != null && session.getAttribute("STYLE") != null) ? (String) session
					.getAttribute("STYLE")
					: "default";
			String context = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath();
			String stylePath = context + "/pages/style/" + style;

			%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="<%=stylePath%>/style.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>flash</title>
<style type="text/css">
<!--
body {
    text-align:left;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
</head>
<body  bgcolor="#FFFFFF">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr> 
    <td height="25" align="center" valign="middle" background="<%=stylePath%>/images/bottom.jpg"><font color="#FFFFFF">MDS</font></td>
  </tr>
</table>
</body>
</html>
