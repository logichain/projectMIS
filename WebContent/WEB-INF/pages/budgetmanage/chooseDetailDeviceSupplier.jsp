<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<body>
	<html:form action="tenderProjectManage.do" onsubmit="return checkFormValidate();">
		<html:errors />
		
		<input type="hidden" name="method" value="confirmChooseDetailDeviceSupplier">
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
				<td align="center" width="30%"><bean:message bundle="budgetmanage" key="budget.supplierName" /></td>
				<td align="center" width="30%"><bean:message bundle="budgetmanage" key="budget.firstDevicePrice" /></td>
				<td align="center"><bean:message bundle="budgetmanage" key="budget.selected" /></td>
			</tr></thead>
			<tr >
				<td align="right">
					<bean:message bundle="budgetmanage" key="budget.firstSupplierFactory" />：
				</td>
				<td>
					<logic:notEmpty name="tenderProjectForm" property="detailDeviceInfo.firstSelectedSupplier">
					<bean:write name="tenderProjectForm" property="detailDeviceInfo.firstSelectedSupplier.CName"/>
					</logic:notEmpty>
				</td>				
				<td>
					<bean:write name="tenderProjectForm" property="detailDeviceInfo.dldFirstSelectedSupplierPriceStr"/>
				</td>				
				<td align="center">
					<logic:notEmpty name="tenderProjectForm" property="detailDeviceInfo.firstSelectedSupplier">
						<logic:equal name="statusId" value="1">
							<input type="radio" name="facRadio" value="1"  checked/>
						</logic:equal>
						<logic:notEqual name="statusId" value="1">
							<input type="radio" name="facRadio" value="1" />
						</logic:notEqual>
					</logic:notEmpty>
				</td>
			</tr>
			<tr >
				<td align="right">
					<bean:message bundle="budgetmanage" key="budget.secondSupplierFactory" />：
				</td>
				<td>
					<logic:notEmpty name="tenderProjectForm" property="detailDeviceInfo.secondSelectedSupplier">
					<bean:write name="tenderProjectForm" property="detailDeviceInfo.secondSelectedSupplier.CName"/>
					</logic:notEmpty>
				</td>
				<td>
					<bean:write name="tenderProjectForm" property="detailDeviceInfo.dldSecondSelectedSupplierPriceStr"/>
				</td>
				<td align="center">
					<logic:notEmpty name="tenderProjectForm" property="detailDeviceInfo.secondSelectedSupplier">
						<logic:equal name="statusId" value="2">
							<input type="radio" name="facRadio" value="2"  checked/>
						</logic:equal>
						<logic:notEqual name="statusId" value="2">
							<input type="radio" name="facRadio" value="2" />
						</logic:notEqual>
					</logic:notEmpty>
				</td>
			</tr>
			<tr  >
				<td align="right">
					<bean:message bundle="budgetmanage" key="budget.thirdSupplierFactory" />：
				</td>
				<td>
					<logic:notEmpty name="tenderProjectForm" property="detailDeviceInfo.thirdSelectedSupplier">
					<bean:write name="tenderProjectForm" property="detailDeviceInfo.thirdSelectedSupplier.CName"/>
					</logic:notEmpty>
				</td>
				<td>
					<bean:write name="tenderProjectForm" property="detailDeviceInfo.dldThirdSelectedSupplierPriceStr"/>
				</td>
				<td align="center">
					<logic:notEmpty name="tenderProjectForm" property="detailDeviceInfo.thirdSelectedSupplier">
						<logic:equal name="statusId" value="3">
							<input type="radio" name="facRadio" value="3"  checked/>
						</logic:equal>
						<logic:notEqual name="statusId" value="3">
							<input type="radio" name="facRadio" value="3" />
						</logic:notEqual>
					</logic:notEmpty>
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

