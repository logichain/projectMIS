<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>
<script type="text/javascript" src="<c:url value='/pages/scripts/calendar.js'/>" charset="gbk"></script>
	
<body>
	<html:form action="tenderProjectManage.do" onsubmit="return checkFormValidate();">
		<html:errors />
		
		<input type="hidden" name="method" value="confirmBaseDetailDevice">
		<input type="hidden" name="radioId" value="">
		<table class="" CELLPADDING="0" CELLSPACING="0" WIDTH="90%" border="0">
			<%String statusId = (String)request.getAttribute("statusId"); %>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td width="20%" align="right">
					<bean:message bundle="budgetmanage" key="budget.deviceName" />：
				</td>
				<td width="25%">
					<html:text property="detailDeviceInfo.dldEquipmentName" size="20" maxlength="64"/>
				</td>
				<td width="20%" align="right">
					<bean:message bundle="budgetmanage" key="budget.deviceStandard" />：
				</td>
				<td>
					<html:text property="detailDeviceInfo.dldEquipmentStandard" size="20" maxlength="64"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					<bean:message bundle="budgetmanage" key="budget.deviceUnit" />：
				</td>
				<td>
					<html:text property="detailDeviceInfo.dldEquipmentUnit" size="20" maxlength="32"/>
				</td>
				<td align="right">
					<bean:message bundle="budgetmanage" key="budget.count" />：
				</td>
				<td>
					<html:text property="detailDeviceInfo.dldEquipmentCount" size="20" maxlength="32"/>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr >
				<td align="right">
					<bean:message bundle="budgetmanage" key="budget.beginDate" />：
				</td>
				<td>
					<html:text property="detailDeviceInfo.dldBeginDate" readonly="true" size="15" maxlength="32" onclick="SelectDate(this)"/>
				</td>
				<td align="right">
					<bean:message bundle="budgetmanage" key="budget.responsiblePerson" />：
				</td>
				<td>
					<html:text property="detailDeviceInfo.dldResponsiblePerson" size="20" maxlength="16"/>
				</td>
			</tr>
			<tr >
				<td align="right">
					<bean:message bundle="budgetmanage" key="budget.endDate" />：
				</td>
				<td>
					<html:text property="detailDeviceInfo.dldEndDate" readonly="true" size="15" maxlength="32" onclick="SelectDate(this)"/>
				</td>
				<td align="right">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr >
				<td align="right">
					<bean:message bundle="budgetmanage" key="budget.claim" />：
				</td>
				<td colspan="3">
					<html:textarea property="detailDeviceInfo.dldComment" rows="5" cols="40"/>
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
					<html:button styleClass="confirmbutton" property="" onclick="tenderProjectForm.submit();">
						&nbsp;
					</html:button>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
		</table>		
	</html:form>
</body>
<html:javascript formName="tenderProjectForm" dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript" src="<html:rewrite forward='staticjavascript'/>"></script>
<script language="JavaScript">

 function chgAction(obj,str){
	obj.value=str;
 }
 
 
 function chgFormOnsubmit(str){  	
	projectInputForm.onsubmit="function onsubmit(){" + str + "}";	
 }
 

function checkFormValidate()
{	 	
	var pomId = document.getElementById("secondDeviceInfo.dlsSecondEquipmentName").value;	
	if(pomId.trim() == "")
	{
		alert("客户 不能为空");
		return false;
	} 
	 	
	return true;
}


</script>

