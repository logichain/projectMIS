<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<bean:define id="loginTitle">
	<bean:message bundle="security" key="login.title" />
</bean:define>
<body>
	
				
<table width="100%" height="100%" border="0" style="background-image:url('pages/style/default/mis/login.jpg');background-repeat:no-repeat;background-position:center center;">
	<tr>
		<td align="center" valign="middle">
			<table border="0" cellspacing="0" cellpadding="0">
				<html:form action="loginAction?method=login" target="_top" focus="name" onsubmit="return validateLoginForm(this);">											
					<tr><td height="80">&nbsp;</td></tr>
					<tr>
						<td height="30" align="right" valign="middle">用户名：</td>
						<td width="167" height="30">
							<html:text property="name" styleId="userName" size="20" maxlength="18" onchange="filterUsername();" style="width:120;background: url(none); FONT-SIZE: 12px;  border: 1 solid #000000" onkeydown="if(event.keyCode==13) {event.keyCode=9}" />
						</td>
					</tr>
					<tr>
						<td height="30" align="right" valign="middle">密&nbsp;&nbsp;&nbsp;&nbsp;码：	</td>
						<td height="30">
							<html:password property="password" maxlength="255" redisplay="false" style="width:120;background: url(none); FONT-SIZE: 12px;  border: 1 solid #000000" onkeydown="if(event.keyCode==13) {event.keyCode=9}" />

						</td>
					</tr>
					<tr>
						<td height="30" align="right" valign="middle">验证码：</td>
						<td height="30">
							<table width="120" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td align="left" valign="middle">
										<input name="chkCode" style="width:60;background: url(none); FONT-SIZE: 12px;  border: 1 solid #000000" maxlength=50 onkeydown="if(event.keyCode==13) {if(validateLoginForm(loginForm)) {loginForm.submit();}}">
									</td>
									<td align="right" valign="middle">
										<html:img page="/CheckCodeImage" style="border: 1 dotted black" width="52" height="20" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
					</html:form>
					<tr><td height="20"></td></tr>
					<tr>	
						<td align="right"><img src="pages\style\default\mis\exit.jpg" border="0" usemap="#Map1"></td>					
						<td align="right"><img src="pages\style\default\mis\loginbutton.jpg" border="0" usemap="#Map2"></td>				
					</tr>
					<tr><td colspan="2"><html:errors/></td></tr>
				</table>
			
		</td>
	</tr>
	

</table>			
	<map name="Map1">
		<area shape="rect" coords="0,0,80,30" href="javascript:window.close();">
	</map>
	<map name="Map2">
		<area shape="rect" coords="0,0,80,30" href="javascript:document.loginForm.submit();">
	</map>

</body>
<html:javascript formName="loginForm" dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript" src="<html:rewrite forward='staticjavascript'/>"></script>

<script type="text/javascript">
<!--
function filterUsername() {

	var s = document.getElementById("userName").value;
	
    var pattern = "[`~!@#$^&*()=|{}':;',[].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]";

    for (var i = 0; i < pattern.length; i++) {		
        s = s.replace(pattern[i], '');
    }	
    
   document.getElementById("userName").value = s;   
}
//-->
</script>

