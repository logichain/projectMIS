<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<html:form action="projectManage.do" onsubmit="return checkFormValidate();">
	<html:errors />
	<input type="hidden" name="method" value="editProjectMonthSettlement">
	<table width="100%">
	<tr><td>决算月份：<html:text property="projectInfo.currentMonth" size="5" maxlength="5" readonly="true" onclick="SelectDateFormat(this,'yyyy-MM')"/> 
			<button onclick="projectSearchForm.submit();">提取</button>
		</td></tr>
	</table>
	<table class="sort-table" WIDTH="100%" border="0">
		<thead>
			<tr>
				<td width="5%"></td><td width="5%"></td><td width="20%"></td><td width="10%"></td><td width="10%"></td><td width="10%"></td><td width="10%"></td><td></td><td width="5%"></td>
			</tr>
			<tr>
				<td align="center">
					<bean:message bundle="budgetmanage" key="budget.indexNo" />
				</td>
				<td colspan="2" align="center">
					<bean:message bundle="budgetmanage" key="budget.budgetItem" />
				</td>
				<td align="center">
					<bean:message bundle="budgetmanage" key="budget.allamount" />
				</td>
				<td align="center">
					<bean:message bundle="budgetmanage" key="budget.monthamount" />
				</td>
				<td align="center">
					<bean:message bundle="budgetmanage" key="budget.income" />
				</td>
				<td align="center">
					<bean:message bundle="budgetmanage" key="budget.monthshortfall" />
				</td>
				<td align="center">
					<bean:message bundle="budgetmanage" key="budget.remark" />
				</td>
				<td align="center">
					<bean:message key="button.edit" />
				</td>
			</tr>
		</thead>
		<tbody>
			<%for(int i=7;i<=47;i++) {%>
			<tr>
				<logic:equal name="projectSearchForm" property='<%= "projectInfo.currentMonthSettlement.e" + i + ".itemModel.bimGatherFlag" %>' value="1">
					<td align="center"><bean:write name="projectSearchForm" property='<%= "projectInfo.currentMonthSettlement.e" + i + ".itemModel.bimCode" %>'/></td>					
					<td colspan="2" align="center"><bean:write name="projectSearchForm" property='<%= "projectInfo.currentMonthSettlement.e" + i + ".itemModel.bimName" %>'/></td>
				</logic:equal>
				<logic:equal name="projectSearchForm" property='<%= "projectInfo.currentMonthSettlement.e" + i + ".itemModel.bimGatherFlag" %>' value="0">
					<td>&nbsp;</td>
					<td align="center"><bean:write name="projectSearchForm" property='<%= "projectInfo.currentMonthSettlement.e" + i + ".itemModel.bimCode" %>'/></td>
					<td align="center"><bean:write name="projectSearchForm" property='<%= "projectInfo.currentMonthSettlement.e" + i + ".itemModel.bimName" %>'/></td>
				</logic:equal>				
				<td align="center"><bean:write name="projectSearchForm" property='<%= "projectInfo.applyBudget.e" + i + "AmountStr" %>'/></td>
				<td align="center"><bean:write name="projectSearchForm" property='<%= "projectInfo.currentMonthBudget.e" + i + "AmountStr" %>'/></td>
				<td align="center"><bean:write name="projectSearchForm" property='<%= "projectInfo.currentMonthSettlement.e" + i + "AmountStr" %>'/></td>
				<bean:define id="shortfall" name="projectSearchForm" property='<%= "projectInfo.shortfallAmount.e" + i + "AmountStr" %>'></bean:define>						
				<td align="center">
					<logic:match name="shortfall"  value="-"  location="start">
						<font color="red"><bean:write name="shortfall"/></font>
					</logic:match>
					<logic:notMatch name="shortfall"  value="-"  location="start">
						<bean:write name="shortfall"/>
					</logic:notMatch>
				</td>
				<td align="left"><bean:write name="projectSearchForm" property='<%= "projectInfo.currentMonthSettlement.e" + i + ".biRemark" %>'/></td>					
		        <td align="center">
					<logic:equal name="accountPerson" property="hasCreatePower" value="true">
				        <logic:equal name="projectSearchForm" property='<%= "projectInfo.currentMonthSettlement.e" + i + ".itemModel.bimEditable" %>' value="1">
				        	<a href="javascript:openDialog('projectManage.do?method=editDetailTenderBugetItem&opType=monthSettlement&id=<bean:write name="projectSearchForm" property='<%= "projectInfo.currentMonthSettlement.e" + i + ".biId" %>'/>', 500,160);void(0);"><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
				        </logic:equal>
					</logic:equal>
		        </td>
			</tr>
			<%} %>
		</tbody>
	</table>
	

</html:form>