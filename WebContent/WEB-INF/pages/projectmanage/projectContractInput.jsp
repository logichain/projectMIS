<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>


<html:form action="projectManage.do" onsubmit="return checkFormValidate();">
	<html:errors />
	
	<input type="hidden" name="method" value="confirmProjectContract">				
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
		<tr>
			<td width="15%">&nbsp;</td>
			<td></td>
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="contract.title"/>：
			</td>
			<td colspan="2">
				<html:text styleId="contractTitle" property="projectInfo.projectContract.pcTitle" size="65" maxlength="100"/>
			</td>
			<td>
				<logic:notEmpty name="projectSearchForm" property="projectInfo.projectContract.pcId">
					<logic:equal name="accountPerson" property="hasCreatePower" value="true">													
						<logic:equal name="projectSearchForm" property="projectInfo.projectContract.pcStatus" value="0">
							<a href='projectManage.do?method=checkProjectContractBegin'><img border="0" src="pages\style\default\mis\sendcheck.jpg"></a>
						</logic:equal>
						<logic:equal name="projectSearchForm" property="projectInfo.projectContract.pcStatus" value="2">
							<a href='projectManage.do?method=checkProjectContractBegin'><img border="0" src="pages\style\default\mis\sendcheck.jpg"></a>
						</logic:equal>				
					</logic:equal>
				</logic:notEmpty>
			</td>				
		</tr>

		<tr>			
			<td align="right">
				<bean:message bundle="projectmanage" key="project.customer" />：
			</td>
			<td colspan="3">
				<html:text styleId="customerName" property="projectInfo.projectContract.customer.CName" size="65" maxlength="100" readonly="true" style="background-color:LightGray;"/>	
				 <html:button property="" onclick="openDialog('projectManage.do?method=searchCustomer&opType=projectContract',800,500);">...</html:button>				
			</td>
		</tr>

		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="contract.type"/>：
			</td>
			<td colspan="3">
				<html:select styleId="contractType" property="projectInfo.projectContract.pcType" style="width:125" onchange="contractTypeChange();">	
					<html:option value="1">订出合同</html:option>
					<html:option value="2">订进合同</html:option>
				</html:select>	
			</td>			
		</tr>
		<tr>
			<td align="right"><bean:message bundle="projectmanage" key="contract.price"/>：</td>
			<td><html:text styleId="contractPrice" property="projectInfo.projectContract.pcContractPrice" size="20" maxlength="20"/>万元</td>
			<td align="right" id="diffCol"></td>
			<td><html:text styleId="priceDiff" property="projectInfo.projectContract.pcPriceDiff" size="20" maxlength="20"/></td>
		</tr>
		<tr>
			<td align="right"><bean:message bundle="projectmanage" key="contract.payment_type"/>：</td>
			<td><html:text styleId="payType" property="projectInfo.projectContract.pcPayType" size="20" maxlength="20"/></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td align="right"><bean:message bundle="projectmanage" key="contract.quality_amount_year"/>：</td>
			<td><html:text styleId="qualityAmountYear" property="projectInfo.projectContract.pcQualityAmountYear" size="20" maxlength="20"/></td>
			<td align="right"><bean:message bundle="projectmanage" key="contract.quality_amount_percent"/>：</td>
			<td><html:text styleId="qualityAmountPercent" property="projectInfo.projectContract.pcQualityAmountPercent" size="20" maxlength="20"/></td>
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="contract.remark" />：
			</td>
			<td colspan="3">			
				<html:textarea styleId="contractRemark" property="projectInfo.projectContract.pcRemark" cols="80" rows="4" />
			</td>				
		</tr>		
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="project.attachment" />：
			</td>
			<td colspan="3">
				<table width="100%"><tr><td width="60%">
				
					<logic:iterate id="am" name="projectSearchForm" property="projectInfo.projectContract.attachmentList" indexId="i">
					<logic:equal name="am" property="paInputInterface" value="3">
					<logic:notEqual name='am' property='paFlag' value="-1">
						<logic:empty name='am' property='paId'>
							<span title="<bean:write name="am" property="paLocalUrl"/>">
								<bean:write name="am" property="paName"/>
							</span>
							<a href="projectManage.do?method=deleteAttachmentByIndex&index=<%=i %>"><img border="0" src="pages\images\icon\16x16\delete.gif"></a>；
						</logic:empty>
						<logic:notEmpty name='am' property='paId'>
							<a href="projectManage.do?method=downloadAttachment&id=<bean:write name='am' property='paId'/>" title="<bean:write name="am" property="paLocalUrl"/>">
								<bean:write name="am" property="paName"/>
							</a>
							<a href="projectManage.do?method=deleteAttachment&id=<bean:write name='am' property='paId'/>"><img border="0" src="pages\images\icon\16x16\delete.gif"></a>；
						</logic:notEmpty>
					</logic:notEqual>	
					</logic:equal>		
				</logic:iterate>
			
			</td>
			<td>	
				<html:button property="" onclick="openDialog('projectManage.do?method=addAttachment&if=3',500,180);" styleClass="selectFileButton">&nbsp;</html:button>
				</td></tr>
				</table>
			</td>	
		</tr>
		<tr><td>&nbsp;</td></tr>
	</table>
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="90%" border="0">
		<tr><td>&nbsp;</td></tr>	
		<tr><td>&nbsp;</td>
			<td width="20%" align="center">
				<html:submit styleClass="confirmbutton">&nbsp;</html:submit>						
			</td>						
		</tr>
		<tr><td>&nbsp;</td></tr>
	</table>		
</html:form>


<script language="JavaScript">
contractTypeChange();
	
 function contractTypeChange()
 {
 	var contractType = document.getElementById("contractType").value;
 	if(contractType == 1)
 	{
 		document.getElementById("diffCol").innerText = "与订进合同差异：";
 	}
 	else
 	{
 		document.getElementById("diffCol").innerText = "与投标书差异：";
 	}
 	
 } 
	
 function chgAction(obj,str){
	obj.value=str;
 }
 
 
 function chgFormOnsubmit(str){  	
	projectSearchForm.onsubmit="function onsubmit(){" + str + "}";	
 }
 

function checkFormValidate()
{	 	
	var pssName = document.getElementById("contractTitle").value;	
	if(pssName == "")
	{
		alert("合同标题  不能为空");
		return false;
	} 
	var personName = document.getElementById("customerName").value;	
	if(personName == "")
	{
		alert("客户名称  不能为空");
		return false;
	} 
		 	
	var contractPrice = document.getElementById("contractPrice").value;	
	if(contractPrice == "")
	{
		alert("合同价格  不能为空");
		return false;
	} 	 	
		 	
	return true;
}

function submitForm()
{
	chgFormOnsubmit('return true;');
	chgAction(document.all.method,'refreshProjectContractInput');
	projectSearchForm.submit();
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

