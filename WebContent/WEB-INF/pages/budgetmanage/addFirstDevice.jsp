<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<body>
	<html:form action="tenderProjectManage.do" onsubmit="return checkFormValidate();">
		<html:errors />
		
		<input type="hidden" name="method" value="confirmFirstDevice">
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="90%" border="0">
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td>
				<td width="35%" align="right">
					<bean:message bundle="budgetmanage" key="budget.firstDeviceName" />：
				</td>
				<td >
					<html:text property="firstDeviceInfo.dlfFirstEquipmentName" size="20" maxlength="64"/>				
				</td>				
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			
		</table>
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td>
				<td width="20%" align="center">
					<html:submit styleClass="confirmbutton">
						&nbsp;
					</html:submit>						
				</td>
								
			</tr>
			<tr><td>&nbsp;</td></tr>
		</table>		
	</html:form>

<script language="JavaScript">

 function chgAction(obj,str){
	obj.value=str;
 }
 
 
 function chgFormOnsubmit(str){  	
	projectInputForm.onsubmit="function onsubmit(){" + str + "}";	
 }
 

function checkFormValidate()
{	 	
	var pomId = document.getElementById("firstDeviceInfo.dlfFirstEquipmentName").value;	
	if(pomId == "")
	{
		alert("名称不能为空");
		return false;
	} 
	 	
	return true;
}


</script>

</body>
