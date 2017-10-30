<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>
<head>
<link rel="stylesheet" href="/resources/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="/resources/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="<c:url value='/resources/kindeditor/kindeditor.js'/>"></script>
<script charset="utf-8" src="<c:url value='/resources/kindeditor/lang/zh_CN.js'/>"></script>
<script charset="utf-8" src="<c:url value='/resources/kindeditor/plugins/code/prettify.js'/>"></script>
<script>
	KindEditor.ready(function(K) {
		var editor1 = K.create('textarea[name="projectInfo.accountPurchaseContract.apcPaymentMethod"]', {
			cssPath : "<c:url value='/resources/kindeditor/plugins/code/prettify.css'/>",
			uploadJson : "<c:url value='/resources/kindeditor/jsp/upload_json.jsp'/>",
			fileManagerJson : "<c:url value='/resources/kindeditor/jsp/file_manager_json.jsp'/>",
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['documentItemForm'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['documentItemForm'].submit();
				});
			}
		});
		prettyPrint();
	});
</script>
</head>

<html:form action="projectManage.do" onsubmit="return checkFormValidate();">
	<html:errors />
	
	<input type="hidden" name="method" value="confirmAccountPurchaseContract">				
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
		
		<tr><td width="11%">&nbsp;</td><td  width="15%">&nbsp;</td><td width="10%">&nbsp;</td><td  width="15%">&nbsp;</td>
			<td width="10%">&nbsp;</td><td  width="15%">&nbsp;</td><td width="10%">&nbsp;</td><td >&nbsp;</td></tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.code" />：
			</td>
			<td>
				<html:text styleId="code" property="projectInfo.accountPurchaseContract.apcCode" size="12" maxlength="45"/>			
			</td>				
		
			<td align="right">
				<bean:message bundle="projectmanage" key="purchasecontract.customer" />：
			</td>
			<td colspan="5">
				<html:text styleId="customerName" property="projectInfo.accountPurchaseContract.apcCustomerName" size="60" maxlength="100"/>					
				<html:button property="" onclick="openDialog('projectManage.do?method=searchCustomer&opType=purchaseContract',800,420);">...</html:button>
			</td>
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.date" />： 
			</td>
			<td >						
				<html:text styleId="signdate" property="projectInfo.accountPurchaseContract.apcSignDate" readonly="true" size="12" maxlength="32" onclick="SelectDate(this)"/>
			</td>
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.object" />： 
			</td>
			<td colspan="5">						
				<html:text styleId="object" property="projectInfo.accountPurchaseContract.apcObject"  size="60" maxlength="100"/>
			</td>			
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.amount" />： 
			</td>
			<td >						
				<html:text styleId="" property="projectInfo.accountPurchaseContract.apcAmount" size="12" maxlength="32" readonly="true" style="background-color:LightGray;"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>
			<td align="right">其中：</td>
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.deviceAmount" />： 
			</td>
			<td >						
				<html:text styleId="" property="projectInfo.accountPurchaseContract.apcDeviceAmount" size="12" maxlength="32"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.installAmount" />： 
			</td>
			<td >						
				<html:text styleId="" property="projectInfo.accountPurchaseContract.apcInstallAmount" size="12" maxlength="32"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>			
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.serviceAmount" />： 
			</td>
			<td >						
				<html:text styleId="" property="projectInfo.accountPurchaseContract.apcServiceAmount" size="12" maxlength="32"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.addAmount" />： 
			</td>
			<td >						
				<html:text styleId="" property="projectInfo.accountPurchaseContract.apcAddAmount" size="12" maxlength="32"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>
		</tr>		
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="purchasecontract.invoiceAmount" />： 
			</td>
			<td >						
				<html:text styleId="" property="projectInfo.accountPurchaseContract.apcInvoiceAmount" size="12" maxlength="32"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>
			<td align="right">
				<bean:message bundle="projectmanage" key="purchasecontract.paymentAmount" />： 
			</td>
			<td >						
				<html:text styleId="" property="projectInfo.accountPurchaseContract.apcPaymentAmount" size="12" maxlength="32"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.period" />： 
			</td>
			<td >						
				<html:text styleId="" property="projectInfo.accountPurchaseContract.apcPeriod" size="12" maxlength="32"/>
			</td>
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.paymentMethod" />：
			</td>
			<td colspan="7">
				<bean:define id="dc" name="projectSearchForm" property="projectInfo.accountPurchaseContract"></bean:define>
				<textarea name="projectInfo.accountPurchaseContract.apcPaymentMethod" cols="80" rows="6" style="width:680px;height:200px;visibility:hidden;"><%=htmlspecialchars(((org.king.accountmanage.bean.AccountPurchaseContract)dc).getApcPaymentMethod())%></textarea>		
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
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>

<script language="JavaScript">
	
 function chgAction(obj,str){
	obj.value=str;
 }
 
 
 function chgFormOnsubmit(str){  	
	projectSearchForm.onsubmit="function onsubmit(){" + str + "}";	
 }
 

function checkFormValidate()
{	 	
	var pssName = document.getElementById("code").value;	
	if(pssName == "")
	{
		alert("合同编号  不能为空");
		return false;
	} 
	var personName = document.getElementById("customerName").value;	
	if(personName == "")
	{
		alert("业主方  不能为空");
		return false;
	} 
	var pssbd = document.getElementById("object").value;	
	if(pssbd == "")
	{
		alert("合同标的  不能为空");
		return false;
	} 
	var pssed = document.getElementById("signdate").value;	
	if(pssed == "")
	{
		alert("签订日期  不能为空");
		return false;
	} 
	 	
	return true;
}

function submitForm()
{
	chgFormOnsubmit('return true;');
	chgAction(document.all.method,'refreshPurchaseContractInput');
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

