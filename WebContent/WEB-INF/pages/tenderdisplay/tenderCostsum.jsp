<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<bean:define id="title">
	<bean:message bundle="projectmanage" key="project.edit" />
</bean:define>
<bean:define id="basetitle">
	<bean:message bundle="projectmanage" key="tabtitle.base" />
</bean:define>
<bean:define id="teammembertitle">
	<bean:message bundle="projectmanage" key="tabtitle.teammember" />
</bean:define>
<bean:define id="scheduleplantitle">
	<bean:message bundle="projectmanage" key="tabtitle.scheduleplan" />
</bean:define>
<bean:define id="scheduleupdatetitle">
	<bean:message bundle="projectmanage" key="tabtitle.scheduleupdate" />
</bean:define>
<bean:define id="budgettitle">
	<bean:message bundle="projectmanage" key="tabtitle.budget" />
</bean:define>
<bean:define id="logtitle">
	<bean:message bundle="projectmanage" key="tabtitle.log" />
</bean:define>
<bean:define id="documenttitle">
	<bean:message bundle="projectmanage" key="tabtitle.document" />
</bean:define>
<bean:define id="contracttitle">
	<bean:message bundle="projectmanage" key="tabtitle.contractcheck" />
</bean:define>
<bean:define id="accountManageTitle">
	<bean:message bundle="projectmanage" key="tabtitle.accountmanage" />
</bean:define>
<bean:define id="costsumTitle">
	<bean:message bundle="tendermanage" key="tender.costsum" />
</bean:define>


<div class="scroll">
<gui:window title='<%=title%>' prototype="boWindow">
<jsp:include page="../projectmanage/projectInfo.jsp"></jsp:include>


<gui:tabbedPanel selectedTab='costsum' prototype="boTabbedPanel" width="16">
	<gui:tab prototype="boTab" name="base" title="<%=basetitle%>" followUp="projectManage.do?method=displayProjectBase">	
</gui:tab>
<gui:tab prototype="boTab" name="accountManage" title="<%=accountManageTitle%>" followUp="projectManage.do?method=displayProjectAccount">	
</gui:tab>
<gui:tab prototype="boTab" name="document" title="<%=documenttitle%>" followUp="projectManage.do?method=displayProjectDocument">	
</gui:tab>
<gui:tab prototype="boTab" name="teammember" title="<%=teammembertitle%>" followUp="projectManage.do?method=displayProjectTeamMember">	
</gui:tab>
<gui:tab prototype="boTab" name="scheduleplan" title="<%=scheduleplantitle%>" followUp="projectManage.do?method=displayProjectSchedulePlan">	
</gui:tab>
<gui:tab prototype="boTab" name="scheduleupdate" title="<%=scheduleupdatetitle%>" followUp="projectManage.do?method=displayProjectSchedule">	
</gui:tab>
<gui:tab prototype="boTab" name="contractcheck" title="<%=contracttitle%>" followUp="projectManage.do?method=displayProjectContractManage">	
</gui:tab>
<gui:tab prototype="boTab" name="log" title="<%=logtitle%>" followUp="projectManage.do?method=displayProjectRecord">	
</gui:tab>
	<gui:tab prototype="boTab" name="costsum" title="<%=costsumTitle%>" followUp="projectManage.do?method=displayTenderCostsum">
	
		<%@ page import="org.king.security.domain.Account" %>		
		<table width="100%"><tr>
		<td align="left">
			<bean:message bundle="projectmanage" key="task.checkresult"/>：
			<logic:equal name="projectSearchForm" property="projectInfo.applyBudget.pbCheckResult" value="1">
				评审中
			</logic:equal>
			<logic:equal name="projectSearchForm" property="projectInfo.applyBudget.pbCheckResult" value="2">
				驳回
			</logic:equal>
			<logic:equal name="projectSearchForm" property="projectInfo.applyBudget.pbCheckResult" value="3">
				通过
			</logic:equal>	
		</td>
		<td align="right">			
<%-- 			<html:button styleClass="checklogbutton" property="" onclick="openDialog('projectManage.do?method=showBudgetChecktaskList&type=6',800,360);"> --%>
<!-- 				&nbsp; -->
<%-- 			</html:button> --%>
		</td>
	</tr></table>
	
		<table class="sort-table" WIDTH="100%" border="1">
			<thead>
				<tr>
					<td width="5%"></td><td width="5%"></td><td width="30%"></td><td width="20%"></td><td></td>
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
					
				</tr>
			</thead>
			<tbody>
				<%int a = 0;for(int i=7;i<=45;i++){%>				
				<logic:equal name="projectSearchForm" property='<%= "projectInfo.applyBudget.e" + i + ".itemModel.bimApplyFlag" %>' value="1">
				
				<tr>
					<logic:equal name="projectSearchForm" property='<%= "projectInfo.applyBudget.e" + i + ".itemModel.bimGatherFlag" %>' value="1">
						<%a++; %>
						<td align="center"><%=a%></td>	
						<td colspan="2" align="center"><bean:write name="projectSearchForm" property='<%= "projectInfo.applyBudget.e" + i + ".itemModel.bimName" %>'/></td>
					</logic:equal>
					<logic:equal name="projectSearchForm" property='<%= "projectInfo.applyBudget.e" + i + ".itemModel.bimGatherFlag" %>' value="0">
						<td>&nbsp;</td>
						<td align="center"><bean:write name="projectSearchForm" property='<%= "projectInfo.applyBudget.e" + i + ".itemModel.bimCode" %>'/></td>
						<td align="center"><bean:write name="projectSearchForm" property='<%= "projectInfo.applyBudget.e" + i + ".itemModel.bimName" %>'/></td>
					</logic:equal>				
					<td align="center"><bean:write name="projectSearchForm" property='<%= "projectInfo.applyBudget.e" + i + "AmountStr" %>'/></td>						
					<td align="left"><bean:write name="projectSearchForm" property='<%= "projectInfo.applyBudget.e" + i + ".biRemark" %>'/></td>					
			        
				</tr>
				</logic:equal>
				<%} %>
				
			</tbody>
		</table>
		<table class="normal-table" cellSpacing="1" cellPadding="1" width="100%" border="0">		
		<thead>
			<tr>
				<td width="5%" align="center">
					<bean:message bundle="projectmanage" key="serial" />
				</td>
				<td width="10%" align="center">
					<bean:message bundle="checktask" key="task.receiveDept" />
				</td>			
				<td width="10%" align="center">
					<bean:message bundle="checktask" key="task.receivePost" />
				</td>
				<td width="16%"  align="center">
					<bean:message bundle="checktask" key="task.checkableUser" />
				</td>
				<td width="10%" align="center">
					<bean:message bundle="checktask" key="task.checkUser" />
				</td>
				<td width="10%" align="center">
					<bean:message bundle="checktask" key="task.checkTime" />
				</td>
				<td width="8%" align="center">
					<bean:message bundle="checktask" key="task.checkResult" />
				</td>
				<td align="center">
					<bean:message bundle="checktask" key="task.remark" />
				</td>						
			</tr>
		</thead>
		<tbody>		
			<logic:iterate name="taskList" id="task" indexId="i">				  
				<% int b = ((org.king.common.checktask.CheckTask)task).getCtCheckStep()%2;request.setAttribute("b",b);%>
				<logic:equal name="b" value="0"><tr class="blue"></logic:equal>
				<logic:equal name="b" value="1"><tr class="odd"></logic:equal>
					<td align="center">										
						<%=i+1 %>
						<logic:equal name="task" property="ctCheckStep" value="1">↓</logic:equal>
						<logic:notEqual name="task" property="ctCheckStep" value="1">&nbsp;&nbsp;</logic:notEqual>
					</td>
					<td align="center">
						<bean:write name="task" property="receiveDepartment.DName"/>						
					</td>
									
					<td align="center">
						<logic:notEmpty name="task" property="receivePost">
							<bean:write name="task" property="receivePost.PName"/>
						</logic:notEmpty>
						<logic:empty name="task" property="receivePost">部门经理	</logic:empty>
					</td>
					<td align="center"><bean:write name="task" property="checkableUserList"/></td>
					<td align="center">
						<logic:notEmpty name="task" property="checkUser">
							<bean:write name="task" property="checkUser.person.personName"/>
						</logic:notEmpty>
					</td>
					<td align="center"><bean:write name="task" property="ctCheckTime"/></td>
					<td align="center">
						<logic:equal name="task" property="ctCheckResult" value="1">通过</logic:equal>
						<logic:equal name="task" property="ctCheckResult" value="2">驳回</logic:equal>
					</td>	
					<td>
						<logic:empty name="task" property="ctRemark">--</logic:empty>
						<logic:notEmpty name="task" property="ctRemark"><bean:write name="task" property="ctRemark"/></logic:notEmpty>
					</td>	
					<!--td align="center">									
						<a href="javascript:openDialog('projectManage.do?method=displayCheckTaskDetail&id=<bean:write name="task" property="ctId"/>',700,400)"><img border="0" src="pages\images\icon\16x16\display.gif"></a>
					</td-->															
				</tr>			
			</logic:iterate>		 
		</tbody>
	</table>
	</gui:tab>	

</gui:tabbedPanel>


</gui:window>
</div>

<script language="JavaScript">
 
 function openDialog(loadpos,WWidth,WHeight)//Lock   Size 
{   
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);   
	var WTop = Math.ceil((window.screen.height - WHeight) / 2); 
	var features = 'width=' + WWidth + 'px,' +	'height=' + WHeight + 'px,' + 'left=' + WLeft + 'px,' + 'top=' + WTop + 'px'; 
		
	WinOP = window.open(loadpos,"_blank",features); 
	WinOP.focus();	   
}

</script>
