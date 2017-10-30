<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<body>
	<html:form action="tenderProjectManage.do" onsubmit="return checkFormValidate();">
		<html:errors />
		
		<input type="hidden" name="method" value="confirmDetailDevice">
		<input type="hidden" name="radioId" value="">
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="90%" border="0">
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
					<bean:message bundle="budgetmanage" key="budget.count" />：
				</td>
				<td>
					<html:text property="detailDeviceInfo.dldEquipmentCount" size="20" maxlength="32"/>
				</td>
				<td align="right">
					<bean:message bundle="budgetmanage" key="budget.deviceUnit" />：
				</td>
				<td>
					<html:select property="detailDeviceInfo.dldEquipmentUnit" style="width:120">							
						<html:option value=""></html:option>
						<html:options collection="unitList" property="duId" labelProperty="duName"/>
					</html:select>
				</td>
				
			</tr>
			<tr><td>&nbsp;</td></tr>
		
		<tr><td></td><td></td>		
				<td align="center">
					<html:submit styleClass="confirmbutton">
						&nbsp;
					</html:submit>
				</td>						
		</tr>
	</table>
	</html:form>
</body>

<script language="JavaScript">

function checkFormValidate()
{	 	
	var devname = document.getElementById("detailDeviceInfo.dldEquipmentName").value;	
	if(devname == "")
	{
		alert("设备名称不能为空");
		return false;
	} 
	
	var devcount = document.getElementById("detailDeviceInfo.dldEquipmentCount").value;	
	if(devcount == "")
	{
		alert("设备数量不能为空");
		return false;
	} 
	 	
	return true;
}

</script>

