<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>


<table width="100%" height="70" border="0" cellpadding="0" cellspacing="0" style="background-image:url('pages/style/default/mis/logintop.gif');background-repeat:no-repeat;background-size:cover;">
  	<tr><td width="85%" height="40">&nbsp;</td><td align="center"><a href="about.do" target="main"><img height="28" width="40" src="pages/style/default/mis/home.jpg"></a><a href="logoutAction.do?method=logout" target="_top"><img src="pages/style/default/mis/logout.jpg"></a></td></tr>
	
	<tr><td colspan="2" style="color:white;font-size:80%;" align="right"><bean:write name="selfJobInfo"/></td></tr>
</table>


