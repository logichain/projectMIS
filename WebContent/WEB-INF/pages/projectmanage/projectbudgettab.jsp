<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>
<%@ page import="org.king.security.domain.Account" %>

<html:form action="projectManage.do" onsubmit="return checkFormValidate();">
	<html:errors />
	<table width="100%"><tr>
		<td align="left">
			<bean:message bundle="projectmanage" key="task.checkresult"/>��
			<logic:equal name="projectSearchForm" property="projectInfo.applyBudget.pbStatus" value="1">
				<logic:equal name="projectSearchForm" property="projectInfo.applyBudget.pbCheckResult" value="1">
					ͨ��
				</logic:equal>
				<logic:equal name="projectSearchForm" property="projectInfo.applyBudget.pbCheckResult" value="2">
					����
				</logic:equal>
			</logic:equal>
			<logic:equal name="projectSearchForm" property="projectInfo.applyBudget.pbStatus" value="2">
				������
			</logic:equal>
		</td>
		<td align="right">			
			<logic:notEqual name="projectSearchForm" property="projectInfo.tpCreateUser" value='<%=(String)request.getSession().getAttribute("account") %>'>				
				<html:button property="" styleClass="checklogbutton" onclick="openDialog('projectManage.do?method=showCheckTaskList&type=2',800,360);" style="cursor: hand;">
					&nbsp;
				</html:button>
			</logic:notEqual>
		</td>
	</tr></table>
	
	<table class="sort-table" WIDTH="100%" border="0">
		<thead>
			<tr>
				<td width="5%"></td><td width="5%"></td><td width="20%"></td><td width="15%"></td><td width="40%"></td>
				<td>
				
				</td>
			</tr>
			<tr>
				<td align="center">
					<bean:message bundle="budgetmanage" key="budget.indexNo" />
				</td>
				<td colspan="2" align="center">
					<bean:message bundle="budgetmanage" key="budget.budgetItem" />
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
			<%
			Account account = (Account)request.getSession().getAttribute("accountPerson");
			int groupno = 1;
			for(int i=7;i<=47;i++) {
			
			if( !account.isAdmin() && (i>=16 && i<=23 || i> 37))
			{
				continue;
			}
			%>
			<tr>
				<logic:equal name="projectSearchForm" property='<%= "projectInfo.applyBudget.e" + i + ".itemModel.bimGatherFlag" %>' value="1">
					<td align="center">
							<%=groupno %>
							<% groupno++;%>
					</td>					
					<td colspan="2" align="center"><bean:write name="projectSearchForm" property='<%= "projectInfo.applyBudget.e" + i + ".itemModel.bimName" %>'/></td>
				</logic:equal>
				<logic:equal name="projectSearchForm" property='<%= "projectInfo.applyBudget.e" + i + ".itemModel.bimGatherFlag" %>' value="0">
					<td>&nbsp;</td>
					<td align="center"><bean:write name="projectSearchForm" property='<%= "projectInfo.applyBudget.e" + i + ".itemModel.bimCode" %>'/></td>
					<td align="center"><bean:write name="projectSearchForm" property='<%= "projectInfo.applyBudget.e" + i + ".itemModel.bimName" %>'/></td>
				</logic:equal>				
				<td align="center"><bean:write name="projectSearchForm" property='<%= "projectInfo.applyBudget.e" + i + "AmountStr" %>'/></td>						
				<td align="left"><bean:write name="projectSearchForm" property='<%= "projectInfo.applyBudget.e" + i + ".biRemark" %>'/></td>					
		        <td align="center">
					<logic:equal name="accountPerson" property="hasCreatePower" value="true">
		        	<logic:notEqual name="projectSearchForm" property="projectInfo.applyBudget.pbStatus" value="1">
				        <logic:equal name="projectSearchForm" property='<%= "projectInfo.applyBudget.e" + i + ".itemModel.bimEditable" %>' value="1">
				        	<a href="javascript:openDialog('projectManage.do?method=editDetailTenderBugetItem&opType=projectBudget&id=<bean:write name="projectSearchForm" property='<%= "projectInfo.applyBudget.e" + i + ".biId" %>'/>', 500,160);void(0);"><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
				        </logic:equal>
			        </logic:notEqual>
					</logic:equal>
		        </td>
			</tr>
			<%} %>
		</tbody>
	</table>
	
</html:form>