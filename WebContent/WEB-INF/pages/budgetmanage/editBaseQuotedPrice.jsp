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
					<bean:write name="tenderProjectForm" property="detailDeviceInfo.dldEquipmentName"/>
				</td>
				<td width="20%" align="right">
					<bean:message bundle="budgetmanage" key="budget.deviceStandard" />：
				</td>
				<td>
					<bean:write name="tenderProjectForm" property="detailDeviceInfo.dldEquipmentStandard"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					<bean:message bundle="budgetmanage" key="budget.deviceUnit" />：
				</td>
				<td>
					<bean:write name="tenderProjectForm" property="detailDeviceInfo.dldEquipmentUnit"/>
				</td>
				<td align="right">
					<bean:message bundle="budgetmanage" key="budget.count" />：
				</td>
				<td>
					<bean:write name="tenderProjectForm" property="detailDeviceInfo.dldEquipmentCount"/>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr >
				<td align="right">
					<bean:message bundle="budgetmanage" key="budget.beginDate" />：
				</td>
				<td>
					<html:text property="detailDeviceInfo.dldBeginDate" readonly="true" size="18" maxlength="32" onclick="SelectDate(this)"/>
				</td>
				<td align="right">
					<bean:message bundle="budgetmanage" key="budget.responsiblePerson" />：
				</td>
				<td>
					<html:text property="detailDeviceInfo.responsiblePerson.person.personName" size="18" maxlength="45" readonly="true" style="background-color:LightGray;"/>
					<html:button property="" onclick="openDialog('tenderProjectManage.do?method=searchAccount&type=quoteprice',800,420);">...</html:button>
				</td>
			</tr>
			<tr >
				<td align="right">
					<bean:message bundle="budgetmanage" key="budget.endDate" />：
				</td>
				<td>
					<html:text property="detailDeviceInfo.dldEndDate" readonly="true" size="18" maxlength="32" onclick="SelectDate(this)"/>
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
					<html:textarea property="detailDeviceInfo.dldComment" rows="4" cols="48"/>
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
</body>

<script language="JavaScript">

function checkFormValidate()
{	 	
	var bd = document.getElementById("detailDeviceInfo.dldBeginDate").value;	
	if(bd == "")
	{
		alert("开始日期不能为空");
		return false;
	} 
	
	var ed = document.getElementById("detailDeviceInfo.dldEndDate").value;	
	if(ed == "")
	{
		alert("结束日期不能为空");
		return false;
	} 
	
	if(ed <= bd)
	{
		alert("结束日期不能早于开始日期!");
		return false;
	}	
	
	var rp = document.getElementById("detailDeviceInfo.responsiblePerson.person.personName").value;	
	if(rp == "")
	{
		alert("责任人不能为空");
		return false;
	} 
	 	
	return true;
}


function chgAction(obj,str){
	obj.value=str;
 }
 
 
 function chgFormOnsubmit(str){  	
	tenderProjectForm.onsubmit="function onsubmit(){" + str + "}";	
 }
 

function submitForm()
{
	chgFormOnsubmit('return true;');
	chgAction(document.all.method,'refreshEditBaseQuotedPrice');
	tenderProjectForm.submit();
}

function openDialog(loadpos,WWidth,WHeight)//Lock   Size 
{   	
	submitForm();

	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);   
	var WTop = Math.ceil((window.screen.height - WHeight) / 2); 
	var features = 'width=' + WWidth + 'px,' +	'height=' + WHeight + 'px,' + 'left=' + WLeft + 'px,' + 'top=' + WTop + 'px'; 
		
	WinOP = window.open(loadpos,"_blank",features); 
	WinOP.focus();   
}

</script>

