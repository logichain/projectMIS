<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<body>
	<html:form action="projectManage.do" onsubmit="return checkFormValidate();">
		<html:errors />
		
		<input type="hidden" name="method" value="saveBudgetItem">
		<input type="hidden" name="opType" value="<%=request.getParameter("opType") %>">				
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="90%" border="0">
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td width="10%" align="right">
					<bean:message bundle="budgetmanage" key="budget.budgetItem" />£º
				</td>
				<td >
					<html:text property="budgetItemInfo.itemModel.bimName" size="40" disabled="true"/>
				</td>
			</tr>
			
			<tr>
				<td width="10%" align="right">
					<bean:message bundle="budgetmanage" key="budget.money" />£º
				</td>
				<td width="15%">
					<html:text property="budgetItemInfo.biAmount" size="12" maxlength="45"/>
				</td>
			</tr>
			<tr>
				<td width="10%" align="right">
					<bean:message bundle="budgetmanage" key="budget.remark" />£º
				</td>
				<td width="15%">
					<html:textarea property="budgetItemInfo.biRemark" cols="45" rows="3"/>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
						
			
		</table>
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
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

<script language="JavaScript">

 function chgAction(obj,str){
	obj.value=str;
 }
 
 
 function chgFormOnsubmit(str){  	
	projectInputForm.onsubmit="function onsubmit(){" + str + "}";	
 }
 

function checkFormValidate()
{	 	
		 	
	return true;
}


</script>

</body>
