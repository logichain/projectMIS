<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>
<style>
<!--
.radio-toolbar input[type="radio"] {
    display:none; 
}

.radio-toolbar label {
	border-width:1px;
	border-top-style:solid;
	border-right-style:solid;
	border-left-style:solid;
    display:inline-block;
    background-color:#c0d2ec;
    padding:4px 11px;
    font-family:Arial;
    font-size:12px;
}

.radio-toolbar input[type="radio"]:checked + label { 
    background-color:blue;
}
-->
</style>


<html:form action="projectManage.do" onsubmit="return checkFormValidate();">
	<html:errors />

	<input type="hidden" name="id" value="">
	<input type="hidden" name="method" value="saveAccountSaleContract">	

	<div class="radio-toolbar">   		
	    <input type="radio" id="radio1" name="radios" onchange="displayChange(this.value);" value="saleContract" checked>
	    <label for="radio1"><bean:message bundle="projectmanage" key="account.salecontract" /></label>	
	    <input type="radio" id="radio2" name="radios" onchange="displayChange(this.value);" value="purchaseContract">
	    <label for="radio2"><bean:message bundle="projectmanage" key="account.purchasecontract" /></label>	
	    <input type="radio" id="radio3" name="radios" onchange="displayChange(this.value);" value="feeTax">
	    <label for="radio3"><bean:message bundle="projectmanage" key="account.feetax" /></label> 
		<input type="radio" id="radio4" name="radios" onchange="displayChange(this.value);" value="incomeExpense">
	    <label for="radio4"><bean:message bundle="projectmanage" key="account.incomeexpense" /></label>
	</div>	

	<div id="saleContract">
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr><td>&nbsp;</td></tr>	
			<tr><td align="left">
					<html:button styleClass="exportbutton" property="" onclick="excelExport('saleContractExport');">
						&nbsp;
					</html:button>				
				</td>						
				<td align="center" width="12%">
					<logic:equal name="accountPerson" property="hasCreatePower" value="true">
						<html:button styleClass="addbutton" property="" onclick="openDialog('projectManage.do?method=createAccountSaleContract',800,450);">
							&nbsp;
						</html:button>
	
						<html:submit styleClass="savebutton" onclick="chgAction(document.all.method,'saveAccountSaleContract');">
							&nbsp;
						</html:submit>
					</logic:equal>											
				</td>
								
			</tr>
		</table>
		<div id="saleContractExport" class="scroll">
		<table class="sort-table" cellSpacing="1" cellPadding="1" width="150%" border="0">					
			<thead>
				<tr>
					<td width="4%" align="center">
						<bean:message bundle="projectmanage" key="serial" />
					</td>						
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="salecontract.code" />
					</td>											
					<td  align="center">
						<bean:message bundle="projectmanage" key="salecontract.customer" />
					</td>						
					<td align="center">
						<bean:message bundle="projectmanage" key="salecontract.object" />
					</td>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="salecontract.amount" />
					</td>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="salecontract.deviceAmount" />
					</td>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="salecontract.installAmount" />
					</td>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="salecontract.serviceAmount" />
					</td>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="salecontract.addAmount" />
					</td>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="salecontract.invoiceAmount" />
					</td>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="salecontract.gatheringAmount" />
					</td>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="salecontract.date" />
					</td>					
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="salecontract.period" />
					</td>
					<td width="4%" align="center">
						<bean:message bundle="projectmanage" key="salecontract.paymentMethod" />
					</td>
					<td width="4%" align="center">
						<bean:message key="button.edit" />
					</td>
					<td width="4%" align="center">
						<bean:message key="button.delete" />
					</td>
				</tr>
			</thead>
			<tbody>
				<%int i=0;%>
				<logic:iterate id="sc" name="projectSearchForm" property="projectInfo.accountSaleContractList">
				<logic:notEqual name="sc" property="ascStatus" value="-1">					
				 	<% int a = i%2;request.setAttribute("a",a);i++;%>
					<logic:equal name="a" value="0"><tr class="even"></logic:equal>
					<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
						<td align="center"><%=i%></td>
						<td align="center"><bean:write name="sc" property="ascCode"/></td>
						<td><bean:write name="sc" property="ascCustomerName"/></td>
						<td><bean:write name="sc" property="ascObject"/></td>
						<td align="right"><bean:write name="sc" property="ascAmount"/></td>
						<td align="right"><bean:write name="sc" property="ascDeviceAmount"/></td>
						<td align="right"><bean:write name="sc" property="ascInstallAmount"/></td>
						<td align="right"><bean:write name="sc" property="ascServiceAmount"/></td>
						<td align="right"><bean:write name="sc" property="ascAddAmount"/></td>
						<td align="right"><bean:write name="sc" property="ascInvoiceAmount"/></td>
						<td align="right"><bean:write name="sc" property="ascGatheringAmount"/></td>
						<td align="center"><bean:write name="sc" property="ascSignDate"/></td>						
						<td><bean:write name="sc" property="ascPeriod"/></td>		
						<td align="center"><a href='javascript:openDialog("projectManage.do?method=displayAccountSaleContractPayment&id=<bean:write name="sc" property="ascId"/>",200,150);'><img border="0" src="pages\images\icon\16x16\payment.gif"></a></td>					
						<logic:notEmpty name="sc" property="ascId">							
							<td align="center"><a href='javascript:openDialog("projectManage.do?method=editAccountSaleContract&id=<bean:write name="sc" property="ascId"/>",800,450);'><img border="0" src="pages\images\icon\16x16\modify.gif"></a></td>
							<td align="center"><a href='javascript:if(confirm("确认要删除这条信息吗?")) {chgAction(document.all.id,"<bean:write name="sc" property="ascId"/>");chgAction(document.all.method,"deleteAccountSaleContract");projectSearchForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a></td>
						</logic:notEmpty>
						<logic:empty name="sc" property="ascId">							
							<td align="center"></td>
							<td align="center"></td>
						</logic:empty>
						
					</tr>
				</logic:notEqual>
				</logic:iterate>
				<logic:notEmpty name="projectSearchForm" property="projectInfo.accountSaleContract">
					<tr>
						<td align="center">合计</td>
						<td></td><td></td><td></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountSaleContract.ascAmount"/></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountSaleContract.ascDeviceAmount"/></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountSaleContract.ascInstallAmount"/></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountSaleContract.ascServiceAmount"/></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountSaleContract.ascAddAmount"/></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountSaleContract.ascInvoiceAmount"/></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountSaleContract.ascGatheringAmount"/></td>
						<td></td><td></td><td></td><td></td><td></td>
					</tr>
				</logic:notEmpty>
			</tbody>
		</table>	
		</div>	
	</div>
	<div id="purchaseContract" style="display:none;">
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr><td>&nbsp;</td></tr>	
			<tr><td align="left">
					<html:button styleClass="exportbutton" property="" onclick="excelExport('purchaseContractExport');">
						&nbsp;
					</html:button>					
				</td>						
				<td align="center" width="12%">
					<logic:equal name="accountPerson" property="hasCreatePower" value="true">	
						<html:button styleClass="addbutton" property="" onclick="openDialog('projectManage.do?method=createAccountPurchaseContract',800,450);">
							&nbsp;
						</html:button>
						<html:submit styleClass="savebutton" onclick="chgAction(document.all.method,'saveAccountPurchaseContract');">
							&nbsp;
						</html:submit>
					</logic:equal>						
				</td>				
			</tr>
		</table>
		<div id="purchaseContractExport" class="scroll">
		<table class="sort-table" cellSpacing="1" cellPadding="1" width="150%" border="0">					
			<thead>
				<tr>
					<td width="4%" align="center">
						<bean:message bundle="projectmanage" key="serial" />
					</td>						
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="salecontract.code" />
					</td>											
					<td  align="center">
						<bean:message bundle="projectmanage" key="salecontract.customer" />
					</td>						
					<td align="center">
						<bean:message bundle="projectmanage" key="salecontract.object" />
					</td>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="salecontract.amount" />
					</td>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="salecontract.deviceAmount" />
					</td>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="salecontract.installAmount" />
					</td>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="salecontract.serviceAmount" />
					</td>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="salecontract.addAmount" />
					</td>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="purchasecontract.invoiceAmount" />
					</td>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="purchasecontract.paymentAmount" />
					</td>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="salecontract.date" />
					</td>					
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="salecontract.period" />
					</td>
					<td width="4%" align="center">
						<bean:message bundle="projectmanage" key="salecontract.paymentMethod" />
					</td>
					<td width="4%" align="center">
						<bean:message key="button.edit" />
					</td>
					<td width="4%" align="center">
						<bean:message key="button.delete" />
					</td>
				</tr>
			</thead>
			<tbody>
				<%int j=0;%>
				<logic:iterate id="pc" name="projectSearchForm" property="projectInfo.accountPurchaseContractList">
				<logic:notEqual name="pc" property="apcStatus" value="-1">					
				 	<% int a = j%2;request.setAttribute("a",a);j++;%>
					<logic:equal name="a" value="0"><tr class="even"></logic:equal>
					<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
						<td align="center"><%=j%></td>
						<td align="center"><bean:write name="pc" property="apcCode"/></td>
						<td><bean:write name="pc" property="apcCustomerName"/></td>
						<td><bean:write name="pc" property="apcObject"/></td>
						<td align="right"><bean:write name="pc" property="apcAmount"/></td>
						<td align="right"><bean:write name="pc" property="apcDeviceAmount"/></td>
						<td align="right"><bean:write name="pc" property="apcInstallAmount"/></td>
						<td align="right"><bean:write name="pc" property="apcServiceAmount"/></td>
						<td align="right"><bean:write name="pc" property="apcAddAmount"/></td>
						<td align="right"><bean:write name="pc" property="apcInvoiceAmount"/></td>
						<td align="right"><bean:write name="pc" property="apcPaymentAmount"/></td>
						<td align="center"><bean:write name="pc" property="apcSignDate"/></td>						
						<td><bean:write name="pc" property="apcPeriod"/></td>		
						<td align="center"><a href='javascript:openDialog("projectManage.do?method=displayAccountPurchaseContractPayment&id=<bean:write name="pc" property="apcId"/>",200,150);'><img border="0" src="pages\images\icon\16x16\payment.gif"></a></td>					
						<logic:notEmpty name="pc" property="apcId">							
							<td align="center"><a href='javascript:openDialog("projectManage.do?method=editAccountPurchaseContract&id=<bean:write name="pc" property="apcId"/>",800,450);'><img border="0" src="pages\images\icon\16x16\modify.gif"></a></td>
							<td align="center"><a href='javascript:if(confirm("确认要删除这条信息吗?")) {chgAction(document.all.id,"<bean:write name="pc" property="apcId"/>");chgAction(document.all.method,"deleteAccountPurchaseContract");projectSearchForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a></td>
						</logic:notEmpty>
						<logic:empty name="pc" property="apcId">							
							<td align="center"></td>
							<td align="center"></td>
						</logic:empty>
						
					</tr>
				</logic:notEqual>
				</logic:iterate>
				<logic:notEmpty name="projectSearchForm" property="projectInfo.accountPurchaseContract">
					<tr>
						<td align="center">合计</td>
						<td></td><td></td><td></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountPurchaseContract.apcAmount"/></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountPurchaseContract.apcDeviceAmount"/></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountPurchaseContract.apcInstallAmount"/></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountPurchaseContract.apcServiceAmount"/></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountPurchaseContract.apcAddAmount"/></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountPurchaseContract.apcInvoiceAmount"/></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountPurchaseContract.apcPaymentAmount"/></td>
						<td></td><td></td><td></td><td></td><td></td>
					</tr>
				</logic:notEmpty>
			</tbody>
		</table>	
		</div>
	</div>
	<div id="feeTax" style="display:none;">
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr><td>&nbsp;</td></tr>	
			<tr><td align="left">
					<html:button styleClass="exportbutton" property="" onclick="excelExport('feeTaxExport');">
						&nbsp;
					</html:button>					
				</td>						
				<td align="center" width="12%">
					<logic:equal name="accountPerson" property="hasCreatePower" value="true">	
						<html:button styleClass="addbutton" property="" onclick="openDialog('projectManage.do?method=createAccountFeeTax',800,300);">
							&nbsp;
						</html:button>
						<html:submit styleClass="savebutton" onclick="chgAction(document.all.method,'saveAccountFeeTax');">
							&nbsp;
						</html:submit>
					</logic:equal>						
				</td>				
			</tr>
		</table>
		<div id="feeTaxExport" class="scroll">
		<table class="sort-table" cellSpacing="1" cellPadding="1" width="150%" border="0">					
			<thead>
				<tr>
					<td rowspan="2" width="5%" align="center">
						<bean:message bundle="projectmanage" key="serial" />
					</td>						
					<td rowspan="2" width="8%" align="center">
						<bean:message bundle="projectmanage" key="salecontract.code" />
					</td>											
					<td rowspan="2" width="6%" align="center">
						<bean:message bundle="projectmanage" key="feetax.saleAmount" />
					</td>						
					<td colspan="2" width="12%"  align="center">
						<bean:message bundle="projectmanage" key="feetax.manageAmount" />
					</td>
					<td rowspan="2" width="6%" align="center">
						<bean:message bundle="projectmanage" key="feetax.financeAmount" />
					</td>
					<td colspan="5" width="30%" align="center">
						<bean:message bundle="projectmanage" key="feetax.tax" />
					</td>					
					<td rowspan="2" align="center">
						<bean:message bundle="projectmanage" key="feetax.remark" />
					</td>
					<td rowspan="2" width="3%" align="center">
						<bean:message key="button.edit" />
					</td>
					<td rowspan="2" width="3%" align="center">
						<bean:message key="button.delete" />
					</td>
				</tr>
				<tr>
					<td align="center">
						<bean:message bundle="projectmanage" key="feetax.implementAmount" />
					</td>
					<td align="center">
						<bean:message bundle="projectmanage" key="feetax.salaryAmount" />
					</td>

					<td align="center">
						<bean:message bundle="projectmanage" key="feetax.saleTax" />
					</td>
					<td align="center">
						<bean:message bundle="projectmanage" key="feetax.purchaseTax" />
					</td>
					<td align="center">
						<bean:message bundle="projectmanage" key="feetax.payableTax" />
					</td>
					<td align="center">
						<bean:message bundle="projectmanage" key="feetax.businessTax" />
					</td>
					<td align="center">
						<bean:message bundle="projectmanage" key="feetax.additionalTax" />
					</td>
				</tr>
			</thead>
			<tbody>
				<%int m=0;%>
				<logic:iterate id="ft" name="projectSearchForm" property="projectInfo.accountFeeTaxList">
				<logic:notEqual name="ft" property="aftStatus" value="-1">					
				 	<% int a = m%2;request.setAttribute("a",a);m++;%>
					<logic:equal name="a" value="0"><tr class="even"></logic:equal>
					<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
						<td align="center"><%=m%></td>
						<td align="center"><bean:write name="ft" property="aftCode"/></td>
						<td align="right"><bean:write name="ft" property="aftSaleFee"/></td>
						<td align="right"><bean:write name="ft" property="aftImplementFee"/></td>
						<td align="right"><bean:write name="ft" property="aftSalaryFee"/></td>
						<td align="right"><bean:write name="ft" property="aftFinanceFee"/></td>
						<td align="right"><bean:write name="ft" property="aftSaleTax"/></td>
						<td align="right"><bean:write name="ft" property="aftPurchaseTax"/></td>
						<td align="right"><bean:write name="ft" property="aftPayableTax"/></td>
						<td align="right"><bean:write name="ft" property="aftBusinessTax"/></td>
						<td align="right"><bean:write name="ft" property="aftAdditionalTax"/></td>
						<td><bean:write name="ft" property="aftRemarks"/></td>						
						<logic:notEmpty name="ft" property="aftId">							
							<td align="center"><a href='javascript:openDialog("projectManage.do?method=editAccountFeeTax&id=<bean:write name="ft" property="aftId"/>",800,300);'><img border="0" src="pages\images\icon\16x16\modify.gif"></a></td>
							<td align="center"><a href='javascript:if(confirm("确认要删除这条信息吗?")) {chgAction(document.all.id,"<bean:write name="ft" property="aftId"/>");chgAction(document.all.method,"deleteAccountFeeTax");projectSearchForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a></td>
						</logic:notEmpty>
						<logic:empty name="ft" property="aftId">							
							<td align="center"></td>
							<td align="center"></td>
						</logic:empty>
						
					</tr>
				</logic:notEqual>
				</logic:iterate>
				<logic:notEmpty name="projectSearchForm" property="projectInfo.accountFeeTax">
					<tr>
						<td align="center">合计</td>
						<td></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountFeeTax.aftSaleFee"/></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountFeeTax.aftImplementFee"/></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountFeeTax.aftSalaryFee"/></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountFeeTax.aftFinanceFee"/></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountFeeTax.aftSaleTax"/></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountFeeTax.aftPurchaseTax"/></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountFeeTax.aftPayableTax"/></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountFeeTax.aftBusinessTax"/></td>
						<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountFeeTax.aftAdditionalTax"/></td>
						<td></td><td></td><td></td>
					</tr>
				</logic:notEmpty>
			</tbody>
		</table>	
		</div>
	</div>
	<div id="incomeExpense" style="display:none;">
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr><td>&nbsp;</td></tr>	
			<tr><td align="left">
					<html:button styleClass="exportbutton" property="" onclick="excelExport('incomeExpenseExport');">
						&nbsp;
					</html:button>										
				</td>						
				<td align="center" width="12%">
											
				</td>				
			</tr>
		</table>
		
		<div id="incomeExpenseExport" class="scroll">
		<table class="sort-table" cellSpacing="1" cellPadding="1" width="150%" border="0">					
			<thead>
				<tr>
					<td rowspan="2" width="5%" align="center">
						<bean:message bundle="projectmanage" key="serial" />
					</td>						
					<td rowspan="2" align="center">
						<bean:message bundle="projectmanage" key="salecontract.code" />
					</td>											
					<td colspan="2" width="12%" align="center">
						合同金额
					</td>						
					<td colspan="2" width="12%"  align="center">
						<bean:message bundle="projectmanage" key="salecontract.invoiceAmount" />
					</td>
					<td rowspan="2" width="6%" align="center">
						销售收款
					</td>
					<td rowspan="2" width="6%" align="center">
						采购付款
					</td>
					<td colspan="3" width="18%" align="center">
						三项费用
					</td>
					<td colspan="3" width="18%" align="center">
						增值税
					</td>	
					<td colspan="2" width="12%" align="center">
						<bean:message bundle="projectmanage" key="feetax.businessTax" />
					</td>				
					<td rowspan="2" width="6%" align="center">
						项目现金流
					</td>					
				</tr>
				<tr>
					<td align="center">
						销售
					</td>
					<td align="center">
						采购
					</td>
					<td align="center">
						销售
					</td>
					<td align="center">
						采购
					</td>

					<td align="center">
						<bean:message bundle="projectmanage" key="feetax.saleAmount" />
					</td>
					<td align="center">
						管理费用
					</td>
					<td align="center">
						<bean:message bundle="projectmanage" key="feetax.financeAmount" />
					</td>

					<td align="center">
						<bean:message bundle="projectmanage" key="feetax.saleTax" />
					</td>
					<td align="center">
						<bean:message bundle="projectmanage" key="feetax.purchaseTax" />
					</td>
					<td align="center">
						<bean:message bundle="projectmanage" key="feetax.payableTax" />及<bean:message bundle="projectmanage" key="feetax.additionalTax" />
					</td>
					<td align="center">
						<bean:message bundle="projectmanage" key="feetax.businessTax" />
					</td>
					<td align="center">
						<bean:message bundle="projectmanage" key="feetax.additionalTax" />
					</td>
				</tr>
			</thead>
			<tbody>				
				<tr class="even">
					<td align="center">1</td>
					<td align="center"></td>
					<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountSaleContract.ascAmount"/></td>
					<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountPurchaseContract.apcAmount"/></td>
					<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountSaleContract.ascInvoiceAmount"/></td>
					<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountPurchaseContract.apcInvoiceAmount"/></td>	
					<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountSaleContract.ascGatheringAmount"/></td>
					<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountPurchaseContract.apcPaymentAmount"/></td>

					<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountFeeTax.aftSaleFee"/></td>	
					<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountFeeTax.manageFee"/></td>
					<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountFeeTax.aftFinanceFee"/></td>
					<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountFeeTax.aftSaleTax"/></td>
					<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountFeeTax.aftPurchaseTax"/></td>
					<td align="right"></td>
					<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountFeeTax.aftBusinessTax"/></td>
					<td align="right"><bean:write name="projectSearchForm" property="projectInfo.accountFeeTax.aftAdditionalTax"/></td>	
					<td align="right"></td>		
				</tr>				
			</tbody>
		</table>	
		</div>
	</div>				
</html:form>

