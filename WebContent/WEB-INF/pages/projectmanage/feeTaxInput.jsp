<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>


<html:form action="projectManage.do" onsubmit="return checkFormValidate();">
	<html:errors />
	
	<input type="hidden" name="method" value="confirmAccountFeeTax">				
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
		
		<tr><td width="10%">&nbsp;</td><td  width="15%">&nbsp;</td><td width="10%">&nbsp;</td><td  width="15%">&nbsp;</td>
			<td width="10%">&nbsp;</td><td  width="15%">&nbsp;</td><td width="10%">&nbsp;</td><td >&nbsp;</td></tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.code" />：
			</td>
			<td>
				<html:text styleId="code" property="projectInfo.accountFeeTax.aftCode" size="12" maxlength="45"/>			
			</td>							
		</tr>
		
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="feetax.saleAmount" />： 
			</td>
			<td >						
				<html:text styleId="" property="projectInfo.accountFeeTax.aftSaleFee" size="12" maxlength="32"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>
			
			<td align="right">
				<bean:message bundle="projectmanage" key="feetax.financeAmount" />： 
			</td>
			<td >						
				<html:text styleId="" property="projectInfo.accountFeeTax.aftFinanceFee" size="12" maxlength="32"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>
			<td align="right">
				<bean:message bundle="projectmanage" key="feetax.implementAmount" />： 
			</td>
			<td >						
				<html:text styleId="" property="projectInfo.accountFeeTax.aftImplementFee" size="12" maxlength="32"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>			
			<td align="right">
				<bean:message bundle="projectmanage" key="feetax.salaryAmount" />： 
			</td>
			<td >						
				<html:text styleId="" property="projectInfo.accountFeeTax.aftSalaryFee" size="12" maxlength="32"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>			
		</tr>		
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="feetax.saleTax" />： 
			</td>
			<td >						
				<html:text styleId="" property="projectInfo.accountFeeTax.aftSaleTax" size="12" maxlength="32"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>
			<td align="right">
				<bean:message bundle="projectmanage" key="feetax.purchaseTax" />： 
			</td>
			<td >						
				<html:text styleId="" property="projectInfo.accountFeeTax.aftPurchaseTax" size="12" maxlength="32"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>
			<td align="right">
				<bean:message bundle="projectmanage" key="feetax.payableTax" />： 
			</td>
			<td >						
				<html:text styleId="" property="projectInfo.accountFeeTax.aftPayableTax" size="12" maxlength="32" readonly="true" style="background-color:LightGray;"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>
		</tr>
		
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="feetax.businessTax" />： 
			</td>
			<td >						
				<html:text styleId="" property="projectInfo.accountFeeTax.aftBusinessTax" size="12" maxlength="32"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>
			<td align="right">
				<bean:message bundle="projectmanage" key="feetax.additionalTax" />： 
			</td>
			<td >						
				<html:text styleId="" property="projectInfo.accountFeeTax.aftAdditionalTax" size="12" maxlength="32"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="feetax.remark" />：
			</td>
			<td colspan="7">
				<html:textarea property="projectInfo.accountFeeTax.aftRemarks" cols="80" rows="5"/>			
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
		 	
	return true;
}

</script>

