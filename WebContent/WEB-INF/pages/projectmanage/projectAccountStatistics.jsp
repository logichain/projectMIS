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

<center>

	<gui:window title="台帐统计" prototype="boWindow" color="100%">			
		<html:form action="projectManage.do">
			<html:errors />
			<input type="hidden" name="method" value="searchAccountStatistics">
			<input type="hidden" name="id" value="">
			<table class="win" CELLPADDING="2" CELLSPACING="0" width="100%" border="0">					
				<tr>
					<td width="10%" align="right">
						<bean:message bundle="projectmanage" key="project.name" />：
					</td>
					<td width="15%" >
						<html:text property="projectInfo.tpName" size="18" maxlength="45"/>						
					</td>
					
						
					<td width="10%" align="right">
						<bean:message bundle="projectmanage" key="project.manager" />：
					</td>
					<td width="15%">
						<html:text property="projectInfo.manager.person.personName" size="18" maxlength="100"/>		
									
					</td>
					<td width="10%" align="right">
						<bean:message bundle="projectmanage" key="project.status" />：
					</td>
					<td width="15%">
						<html:select property="projectInfo.tpStatus" style="width:120">	
							<html:option value=""></html:option>
							<html:options collection="statusList" property="psId" labelProperty="psName"/>
						</html:select>				
					</td>
					<td width="10%" align="right">
						<bean:message bundle="notice" key="release.dept" />：
					</td>
					<td>
						<html:select property="projectInfo.tpDept" style="width:120">	
							<html:option value=""></html:option>
							<html:options collection="departmentList" property="DId" labelProperty="DName"/>
						</html:select>
					</td>
				</tr>
				<tr>
									
					<td colspan="8" align="right">
						<html:submit styleClass="searchbutton" onclick="initPageNo();">
							&nbsp;
						</html:submit>
						<html:submit styleClass="resetbutton" onclick="chgAction(document.all.method,'resetSearchAccountStatistics');">
							&nbsp;
						</html:submit>
					</td>
				</tr>
			</table>

			<div class="radio-toolbar">   		
			    <input type="radio" id="radio1" name="radios" onchange="displayChange(this.value);" value="saleContract" checked>
			    <label for="radio1"><bean:message bundle="projectmanage" key="account.salecontract" /></label>	
			    <input type="radio" id="radio2" name="radios" onchange="displayChange(this.value);" value="purchaseContract">
			    <label for="radio2"><bean:message bundle="projectmanage" key="account.purchasecontract" /></label>	
			    <input type="radio" id="radio3" name="radios" onchange="displayChange(this.value);" value="feeTax">
			    <label for="radio3"><bean:message bundle="projectmanage" key="account.feetax" /></label> 
				<input type="radio" id="radio4" name="radios" onchange="displayChange(this.value);" value="incomeExpense">
			    <label for="radio4">总<bean:message bundle="projectmanage" key="account.incomeexpense" /></label>
			</div>	
		
			<div id="saleContract">
				<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
					<tr><td>&nbsp;</td></tr>	
					<tr><td width="70%" align="right">
							<html:button styleClass="exportbutton" property="" onclick="excelExport('saleContractExport');">
								&nbsp;
							</html:button>					
					</tr>
				</table>
				<div id="saleContractExport" class="scroll">
				<table class="sort-table" cellSpacing="1" cellPadding="1" width="150%" border="0">					
					<thead>
						<tr>
							<td width="4%" align="center">
								<bean:message bundle="projectmanage" key="serial" />
							</td>
							<td width="4%" align="center">
								详细
							</td>						
							<td align="center">
								<bean:message bundle="projectmanage" key="project.name" />
							</td>	
							<td width="6%" align="center">			
								<bean:message bundle="projectmanage" key="salecontract.code" />
							</td>																
							<td  align="center">
								<bean:message bundle="projectmanage" key="salecontract.customer" />
							</td>						
							<td width="6%" align="center">
								<bean:message bundle="projectmanage" key="salecontract.object" />
							</td>									
							<td width="6%" align="center">
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
							<td width="6%" align="center">
								<bean:message bundle="projectmanage" key="salecontract.date" />
							</td>					
							<td width="5%" align="center">
								<bean:message bundle="projectmanage" key="salecontract.period" />
							</td>
							<td width="4%" align="center">
								<bean:message bundle="projectmanage" key="salecontract.paymentMethod" />
							</td>							
						</tr>
					</thead>
					<tbody>
						<%int i=0;%>
						<logic:iterate id="tp" name="projectList">			
						<logic:iterate id="asc" name="tp" property="accountSaleContractList">	
						 	<% int a = i%2;request.setAttribute("a",a);i++;%>
							<logic:equal name="a" value="0"><tr class="even"></logic:equal>
							<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
								
								<td align="center"><%=i%></td>
								<td align="center">					
									<a href="javascript:openDialog('projectManage.do?method=displayTenderProject&id=<bean:write name="tp" property="tpId"/>',1200,480)"><img border="0" src="pages\images\icon\16x16\display.gif"></a>			
								</td>
								<td><bean:write name="tp" property="tpName"/></td>
								<td><bean:write name="asc" property="ascCode"/></td>
								<td><bean:write name="asc" property="ascCustomerName"/></td>
								<td><bean:write name="asc" property="ascObject"/></td>
								<td align="right"><bean:write name="asc" property="ascAmount"/></td>
								<td align="right"><bean:write name="asc" property="ascDeviceAmount"/></td>
								<td align="right"><bean:write name="asc" property="ascInstallAmount"/></td>
								<td align="right"><bean:write name="asc" property="ascServiceAmount"/></td>
								<td align="right"><bean:write name="asc" property="ascAddAmount"/></td>
								<td align="right"><bean:write name="asc" property="ascInvoiceAmount"/></td>
								<td align="right"><bean:write name="asc" property="ascGatheringAmount"/></td>
								<td align="center"><bean:write name="asc" property="ascSignDate"/></td>						
								<td><bean:write name="asc" property="ascPeriod"/></td>		
								<td align="center"><img border="0" src="pages\images\icon\16x16\payment.gif" title='<bean:write name="asc" property="ascPaymentMethod"/>'></td>
													
							</tr>
						</logic:iterate>
						</logic:iterate>
						<logic:notEmpty name="totalAccountSaleContract">
							<tr>
								<td align="center">合计</td>
								<td></td><td></td><td></td><td></td><td></td>
								<td align="right"><bean:write name="totalAccountSaleContract" property="ascAmount"/></td>
								<td align="right"><bean:write name="totalAccountSaleContract" property="ascDeviceAmount"/></td>
								<td align="right"><bean:write name="totalAccountSaleContract" property="ascInstallAmount"/></td>
								<td align="right"><bean:write name="totalAccountSaleContract" property="ascServiceAmount"/></td>
								<td align="right"><bean:write name="totalAccountSaleContract" property="ascAddAmount"/></td>
								<td align="right"><bean:write name="totalAccountSaleContract" property="ascInvoiceAmount"/></td>
								<td align="right"><bean:write name="totalAccountSaleContract" property="ascGatheringAmount"/></td>
								<td></td><td></td><td></td>
							</tr>
						</logic:notEmpty>
					</tbody>
				</table>	
				</div>	
			</div>
			<div id="purchaseContract" style="display:none;">
				<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
					<tr><td>&nbsp;</td></tr>	
					<tr><td width="70%" align="right">
							<html:button styleClass="exportbutton" property="" onclick="excelExport('purchaseContractExport');">
								&nbsp;
							</html:button>					
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
							<td width="4%" align="center">
								详细
							</td>					
							<td align="center">
								<bean:message bundle="projectmanage" key="project.name" />
							</td>	
							<td width="6%" align="center">			
								<bean:message bundle="projectmanage" key="salecontract.code" />
							</td>								
							<td  align="center">
								<bean:message bundle="projectmanage" key="salecontract.customer" />
							</td>						
							<td width="6%" align="center">
								<bean:message bundle="projectmanage" key="salecontract.object" />
							</td>			
							<td width="6%" align="center">
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
							<td width="6%" align="center">
								<bean:message bundle="projectmanage" key="salecontract.date" />
							</td>					
							<td width="5%" align="center">
								<bean:message bundle="projectmanage" key="salecontract.period" />
							</td>
							<td width="5%" align="center">
								<bean:message bundle="projectmanage" key="salecontract.paymentMethod" />
							</td>							
						</tr>
					</thead>
					<tbody>
						<%int j=0;%>
						<logic:iterate id="tp" name="projectList">	
						<logic:iterate id="apc" name="tp" property="accountPurchaseContractList">		
						 	<% int a = j%2;request.setAttribute("a",a);j++;%>
							<logic:equal name="a" value="0"><tr class="even"></logic:equal>
							<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
								<td align="center"><%=j%></td>
								<td align="center">					
									<a href="javascript:openDialog('projectManage.do?method=displayTenderProject&id=<bean:write name="tp" property="tpId"/>',1200,480)"><img border="0" src="pages\images\icon\16x16\display.gif"></a>			
								</td>
								<td><bean:write name="tp" property="tpName"/></td>
								<td><bean:write name="apc" property="apcCode"/></td>
								<td><bean:write name="apc" property="apcCustomerName"/></td>
								<td><bean:write name="apc" property="apcObject"/></td>
								<td align="right"><bean:write name="apc" property="apcAmount"/></td>
								<td align="right"><bean:write name="apc" property="apcDeviceAmount"/></td>
								<td align="right"><bean:write name="apc" property="apcInstallAmount"/></td>
								<td align="right"><bean:write name="apc" property="apcServiceAmount"/></td>
								<td align="right"><bean:write name="apc" property="apcAddAmount"/></td>
								<td align="right"><bean:write name="apc" property="apcInvoiceAmount"/></td>
								<td align="right"><bean:write name="apc" property="apcPaymentAmount"/></td>
								<td align="center"><bean:write name="apc" property="apcSignDate"/></td>						
								<td><bean:write name="apc" property="apcPeriod"/></td>		
								<td align="center"><img border="0" src="pages\images\icon\16x16\payment.gif" title='<bean:write name="apc" property="apcPaymentMethod"/>'></td>					
															
							</tr>
						</logic:iterate>	
						</logic:iterate>
						<logic:notEmpty name="totalAccountPurchaseContract">
							<tr>
								<td align="center">合计</td>
								<td></td><td></td><td></td><td></td><td></td>
								<td align="right"><bean:write name="totalAccountPurchaseContract" property="apcAmount"/></td>
								<td align="right"><bean:write name="totalAccountPurchaseContract" property="apcDeviceAmount"/></td>
								<td align="right"><bean:write name="totalAccountPurchaseContract" property="apcInstallAmount"/></td>
								<td align="right"><bean:write name="totalAccountPurchaseContract" property="apcServiceAmount"/></td>
								<td align="right"><bean:write name="totalAccountPurchaseContract" property="apcAddAmount"/></td>
								<td align="right"><bean:write name="totalAccountPurchaseContract" property="apcInvoiceAmount"/></td>
								<td align="right"><bean:write name="totalAccountPurchaseContract" property="apcPaymentAmount"/></td>
								<td></td><td></td><td></td>
							</tr>
						</logic:notEmpty>
					</tbody>
				</table>	
				</div>
			</div>
			<div id="feeTax" style="display:none;">
				<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
					<tr><td>&nbsp;</td></tr>	
					<tr><td width="70%" align="right">
							<html:button styleClass="exportbutton" property="" onclick="excelExport('feeTaxExport');">
								&nbsp;
							</html:button>					
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
							<td rowspan="2" width="4%" align="center">
								详细
							</td>					
							<td rowspan="2" align="center">
								<bean:message bundle="projectmanage" key="project.name" />
							</td>											
							<td  rowspan="2" width="6%" align="center">
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
						<logic:iterate id="tp" name="projectList">
						<logic:iterate id="aft" name="tp" property="accountFeeTaxList">					
						 	<% int a = m%2;request.setAttribute("a",a);m++;%>
							<logic:equal name="a" value="0"><tr class="even"></logic:equal>
							<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
								<td align="center"><%=m%></td>
								<td align="center">					
									<a href="javascript:openDialog('projectManage.do?method=displayTenderProject&id=<bean:write name="tp" property="tpId"/>',1200,480)"><img border="0" src="pages\images\icon\16x16\display.gif"></a>			
								</td>
								<td><bean:write name="tp" property="tpName"/></td>
								<td><bean:write name="aft" property="aftCode"/></td>
								<td align="right"><bean:write name="aft" property="aftSaleFee"/></td>
								<td align="right"><bean:write name="aft" property="aftImplementFee"/></td>
								<td align="right"><bean:write name="aft" property="aftSalaryFee"/></td>
								<td align="right"><bean:write name="aft" property="aftFinanceFee"/></td>
								<td align="right"><bean:write name="aft" property="aftSaleTax"/></td>
								<td align="right"><bean:write name="aft" property="aftPurchaseTax"/></td>
								<td align="right"><bean:write name="aft" property="aftPayableTax"/></td>
								<td align="right"><bean:write name="aft" property="aftBusinessTax"/></td>
								<td align="right"><bean:write name="aft" property="aftAdditionalTax"/></td>
								<td><bean:write name="aft" property="aftRemarks"/></td>								
							</tr>
						</logic:iterate>
						</logic:iterate>
						<logic:notEmpty name="totalAccountFeeTax">
							<tr>
								<td align="center">合计</td>
								<td></td><td></td><td></td>
								<td align="right"><bean:write name="totalAccountFeeTax" property="aftSaleFee"/></td>
								<td align="right"><bean:write name="totalAccountFeeTax" property="aftImplementFee"/></td>
								<td align="right"><bean:write name="totalAccountFeeTax" property="aftSalaryFee"/></td>
								<td align="right"><bean:write name="totalAccountFeeTax" property="aftFinanceFee"/></td>
								<td align="right"><bean:write name="totalAccountFeeTax" property="aftSaleTax"/></td>
								<td align="right"><bean:write name="totalAccountFeeTax" property="aftPurchaseTax"/></td>
								<td align="right"><bean:write name="totalAccountFeeTax" property="aftPayableTax"/></td>
								<td align="right"><bean:write name="totalAccountFeeTax" property="aftBusinessTax"/></td>
								<td align="right"><bean:write name="totalAccountFeeTax" property="aftAdditionalTax"/></td>
								<td></td>
							</tr>
						</logic:notEmpty>
					</tbody>
				</table>	
				</div>
			</div>
			<div id="incomeExpense" style="display:none;">
				<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
					<tr><td>&nbsp;</td></tr>	
					<tr><td width="70%" align="right">										
						</td>						
						<td align="center" width="12%">
							<html:button styleClass="exportbutton" property="" onclick="excelExport('incomeExpenseExport');">
								&nbsp;
							</html:button>						
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
							<td align="right"><bean:write name="totalAccountSaleContract" property="ascAmount"/></td>
							<td align="right"><bean:write name="totalAccountPurchaseContract" property="apcAmount"/></td>
							<td align="right"><bean:write name="totalAccountSaleContract" property="ascInvoiceAmount"/></td>
							<td align="right"><bean:write name="totalAccountPurchaseContract" property="apcInvoiceAmount"/></td>	
							<td align="right"><bean:write name="totalAccountSaleContract" property="ascGatheringAmount"/></td>
							<td align="right"><bean:write name="totalAccountPurchaseContract" property="apcPaymentAmount"/></td>
		
							<td align="right"><bean:write name="totalAccountFeeTax" property="aftSaleFee"/></td>	
							<td align="right"><bean:write name="totalAccountFeeTax" property="manageFee"/></td>
							<td align="right"><bean:write name="totalAccountFeeTax" property="aftFinanceFee"/></td>
							<td align="right"><bean:write name="totalAccountFeeTax" property="aftSaleTax"/></td>
							<td align="right"><bean:write name="totalAccountFeeTax" property="aftPurchaseTax"/></td>
							<td align="right"></td>
							<td align="right"><bean:write name="totalAccountFeeTax" property="aftBusinessTax"/></td>
							<td align="right"><bean:write name="totalAccountFeeTax" property="aftAdditionalTax"/></td>	
							<td align="right"></td>		
						</tr>				
					</tbody>
				</table>	
				</div>
			</div>		
					
		</html:form>
		
	</gui:window>
</center>
<iframe name="printframe" style="height:0px;width:0px;"></iframe>

<script language="JavaScript">

 function chgAction(obj,str){
	obj.value=str;	
 }
 
 function initPageNo()
 {
 	document.getElementById("pager.offset").value='0';
 }
 
 function displayChange(divId)
{
	var divList = ["saleContract","purchaseContract","feeTax","incomeExpense"];
	for(var i=0;i<divList.length;i++)
	{
		if(divList[i] == divId)
		{	
			document.getElementById(divList[i]).style.display = "block";			
		}
		else
		{
			document.getElementById(divList[i]).style.display = "none";
		}
	}	
}
function excelExport(divId){
	var tempStr = document.getElementById(divId).innerHTML;
   	var doc = document.frames["printframe"].document;
   	doc.write(tempStr);    
   	doc.close();
   	
   	doc.execCommand('saveas',true,'exportData.xls');
 } 
 
 function openDialog(loadpos,WWidth,WHeight)//Lock   Size 
{   	
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);   
	var WTop = Math.ceil((window.screen.height - WHeight) / 2); 
	var features = 'width=' + WWidth + 'px,' +	'height=' + WHeight + 'px,' + 'left=' + WLeft + 'px,' + 'top=' + WTop + 'px'; 
		
	WinOP = window.open(loadpos,"_blank",features); 
	WinOP.focus();	   
}
 
</script>
