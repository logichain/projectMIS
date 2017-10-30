<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<body>
	<html:form action="tenderProjectManage.do" onsubmit="return checkFormValidate();">
		<html:errors />
		
		<input type="hidden" name="method" value="confirmBaseDetailDevice">
		<input type="hidden" name="radioId" value="">
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="90%" border="0">
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
		<tr><td colspan="4">
		<table class="sort-table" cellSpacing="1" cellPadding="1" width="90%" border="0">
			<thead><tr>
				<td align="center" width="30%">&nbsp;</td>
				<td align="center" width="36%"><bean:message bundle="budgetmanage" key="budget.supplierName" /></td>
				<td align="center"><bean:message bundle="budgetmanage" key="budget.firstDevicePrice" /></td>
			</tr></thead>
			<tr >
				<td align="right">
					<bean:message bundle="budgetmanage" key="budget.firstSupplierFactory" />：
				</td>
				<td>
					<html:text property="detailDeviceInfo.firstSelectedSupplier.CName" size="18" maxlength="128" readonly="true" style="background-color:LightGray;"/>
					<html:button property="" onclick="openDialog('tenderProjectManage.do?method=searchSupplier&opType=first',800,420);">...</html:button>
				</td>				
				<td>
					<html:text property="detailDeviceInfo.dldFirstSelectedSupplierPriceStr" size="18" maxlength="20"/>
				</td>	
			</tr>
			<tr >
				<td align="right">
					<bean:message bundle="budgetmanage" key="budget.secondSupplierFactory" />：
				</td>
				<td>
					<html:text property="detailDeviceInfo.secondSelectedSupplier.CName" size="18" maxlength="128" readonly="true" style="background-color:LightGray;"/>
					<html:button property="" onclick="openDialog('tenderProjectManage.do?method=searchSupplier&opType=second',800,420);">...</html:button>
				</td>
				<td>
					<html:text property="detailDeviceInfo.dldSecondSelectedSupplierPriceStr" size="18" maxlength="20"/>
				</td>
			</tr>
			<tr  >
				<td align="right">
					<bean:message bundle="budgetmanage" key="budget.thirdSupplierFactory" />：
				</td>
				<td>
					<html:text property="detailDeviceInfo.thirdSelectedSupplier.CName" size="18" maxlength="128" readonly="true" style="background-color:LightGray;"/>
					<html:button property="" onclick="openDialog('tenderProjectManage.do?method=searchSupplier&opType=third',800,420);">...</html:button>
				</td>
				<td>
					<html:text property="detailDeviceInfo.dldThirdSelectedSupplierPriceStr" size="18" maxlength="20"/>
				</td>
			</tr>
			
		</table>
		</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td></td><td></td>
		
				<td align="center">
					<html:button styleClass="confirmbutton" property="" onclick="tenderProjectForm.submit();">
						&nbsp;
					</html:button>
				</td>						
		</tr>
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
	tenderProjectForm.onsubmit="function onsubmit(){" + str + "}";	
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

 
 function submitForm()
{
	chgFormOnsubmit('return true;');
	chgAction(document.all.method,'refreshDetailDeviceReportPrice');
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

