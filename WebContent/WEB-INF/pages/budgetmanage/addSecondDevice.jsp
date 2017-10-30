<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<body>
	<html:form action="tenderProjectManage.do" onsubmit="return checkFormValidate();">
		<html:errors />
		
		<input type="hidden" name="method" value="confirmSecondDevice">
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="90%" border="0">
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td>
				<td width="35%" align="right">
					<bean:message bundle="budgetmanage" key="budget.secondDeviceName" />：
				</td>
				<td >
					<html:text property="secondDeviceInfo.dlsSecondEquipmentName" size="20" maxlength="64"/>
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

function checkFormValidate()
{	 	
	var pomId = document.getElementById("secondDeviceInfo.dlsSecondEquipmentName").value;	
	if(pomId == "")
	{
		alert("名称 不能为空");
		return false;
	} 
	 	
	return true;
}


</script>

</body>
