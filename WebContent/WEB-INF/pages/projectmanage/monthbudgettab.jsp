<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<html:form action="projectManage.do" onsubmit="return checkFormValidate();">
	<html:errors />
	<input type="hidden" name="method" value="editProjectMonthBudget">	
	<table width="100%">
		
		<tr>
		<td align="left">
			<bean:message bundle="projectmanage" key="task.checkresult"/>：
			<logic:equal name="projectSearchForm" property="projectInfo.currentMonthBudget.pbStatus" value="1">
				<logic:equal name="projectSearchForm" property="projectInfo.currentMonthBudget.pbCheckResult" value="1">
					通过
				</logic:equal>
				<logic:equal name="projectSearchForm" property="projectInfo.currentMonthBudget.pbCheckResult" value="2">
					驳回
				</logic:equal>
			</logic:equal>
			<logic:equal name="projectSearchForm" property="projectInfo.currentMonthBudget.pbStatus" value="2">
				评审中
			</logic:equal>
		</td>
		<td align="right">			
			<logic:notEqual name="projectSearchForm" property="projectInfo.tpCreateUser" value='<%=(String)request.getSession().getAttribute("account") %>'>								
				<html:button property="" styleClass="checklogbutton" onclick="openDialog('projectManage.do?method=showCheckTaskList&type=3',800,360);" style="cursor: hand;">
					&nbsp;
				</html:button>
			</logic:notEqual>
		</td>
	</tr>
	<tr><td>预算月份：<html:text property="projectInfo.currentMonth" size="5" maxlength="5" readonly="true" onclick="SelectDateFormat(this,'yyyy-MM')"/> 
			<button onclick="projectSearchForm.submit();">提取</button>
		</td></tr>
	</table>
	<table class="sort-table" WIDTH="100%" border="0">
		<thead>
			<tr>
				<td width="5%"></td><td width="5%"></td><td width="20%"></td><td width="10%"></td><td width="10%"></td><td width="10%"></td><td></td><td width="5%"></td>
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
					<bean:message bundle="budgetmanage" key="budget.settlementamount" />
				</td>
				<td align="center">
					<bean:message bundle="budgetmanage" key="budget.income" />
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
				<logic:equal name="projectSearchForm" property='<%= "projectInfo.currentMonthBudget.e" + i + ".itemModel.bimGatherFlag" %>' value="1">
					<td align="center"><bean:write name="projectSearchForm" property='<%= "projectInfo.currentMonthBudget.e" + i + ".itemModel.bimCode" %>'/></td>					
					<td colspan="2" align="center"><bean:write name="projectSearchForm" property='<%= "projectInfo.currentMonthBudget.e" + i + ".itemModel.bimName" %>'/></td>
				</logic:equal>
				<logic:equal name="projectSearchForm" property='<%= "projectInfo.currentMonthBudget.e" + i + ".itemModel.bimGatherFlag" %>' value="0">
					<td>&nbsp;</td>
					<td align="center"><bean:write name="projectSearchForm" property='<%= "projectInfo.currentMonthBudget.e" + i + ".itemModel.bimCode" %>'/></td>
					<td align="center"><bean:write name="projectSearchForm" property='<%= "projectInfo.currentMonthBudget.e" + i + ".itemModel.bimName" %>'/></td>
				</logic:equal>
				<td align="center"><bean:write name="projectSearchForm" property='<%= "projectInfo.applyBudget.e" + i + "AmountStr" %>'/></td>	
				<td align="center"><bean:write name="projectSearchForm" property='<%= "projectInfo.settlementAmount.e" + i + "AmountStr" %>'/></td>			
				<td align="center"><bean:write name="projectSearchForm" property='<%= "projectInfo.currentMonthBudget.e" + i + "AmountStr" %>'/></td>						
				<td align="left"><bean:write name="projectSearchForm" property='<%= "projectInfo.currentMonthBudget.e" + i + ".biRemark" %>'/></td>					
		        <td align="center">
					<logic:equal name="accountPerson" property="hasCreatePower" value="true">
		        	<logic:notEqual name="projectSearchForm" property="projectInfo.currentMonthBudget.pbStatus" value="1">
				        <logic:equal name="projectSearchForm" property='<%= "projectInfo.currentMonthBudget.e" + i + ".itemModel.bimEditable" %>' value="1">
				        	<a href="javascript:openDialog('projectManage.do?method=editDetailTenderBugetItem&opType=monthBudget&id=<bean:write name="projectSearchForm" property='<%= "projectInfo.currentMonthBudget.e" + i + ".biId" %>'/>', 500,160);void(0);"><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
				        </logic:equal>
			        </logic:notEqual>
					</logic:equal>
		        </td>
			</tr>
			<%} %>
		</tbody>
	</table>
	
</html:form>

