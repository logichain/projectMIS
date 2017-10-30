<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>
<%@ page import="org.king.security.domain.Account" %>
<div class="scroll"> 
<logic:equal name="checkTaskInfo" property="ctTaskType" value="1">
<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
	<caption>合同信息</caption>
	<tr>
		<td width="15%">&nbsp;</td><td width="45%"></td><td width="10%">&nbsp;</td><td></td>
	</tr>
	<tr>
		<td align="right">
			<bean:message bundle="projectmanage" key="contract.title"/>：
		</td>
		<td>
			<bean:write name="checkTaskInfo" property="contract.pcTitle"/>
		</td>		
		
		<td align="right">
			<bean:message bundle="projectmanage" key="contract.type"/>：
		</td>
		<td>
			<logic:equal name="checkTaskInfo" property="contract.pcType" value="1">订出合同</logic:equal>
			<logic:equal name="checkTaskInfo" property="contract.pcType" value="2">订进合同</logic:equal>			
		</td>		
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>			
		<td align="right">
			<bean:message bundle="projectmanage" key="project.customer" />：
		</td>
		<td colspan="3">					
			<bean:write name="checkTaskInfo" property="contract.customer.CName"/>				
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td align="right">
			<bean:message bundle="projectmanage" key="contract.remark" />：
		</td>
		<td colspan="3">			
			<bean:write name="checkTaskInfo" property="contract.pcRemark"/>	
		</td>				
	</tr>		
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td align="right">
			<bean:message bundle="projectmanage" key="project.attachment" />：
		</td>
		<td colspan="3">
			<table width="100%"><tr><td width="60%">
				
				<logic:iterate id="am" name="checkTaskInfo" property="contract.attachmentList" indexId="i">
					<logic:equal name="am" property="paInputInterface" value="3">							
						<a href="projectManage.do?method=downloadAttachment&id=<bean:write name='am' property='paId'/>" title="<bean:write name="am" property="paLocalUrl"/>">
							<bean:write name="am" property="paName"/>
						</a>									
					</logic:equal>		
				</logic:iterate>
		
			</td><td>	
			
			</td></tr>
			</table>
		</td>	
	</tr>
	<tr><td>&nbsp;</td></tr>
</table>
</logic:equal>

<logic:equal name="checkTaskInfo" property="ctTaskType" value="2">
<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
	<caption>立项备案信息</caption>
	<tr>
		<td width="10%"></td>
		<td width="40%"></td>				
		<td width="10%"></td>
		<td></td>
	</tr>
	<tr>
		<td align="right">
			<bean:message bundle="approvalrecord" key="project_name" />：
		</td>
		<td>
			<bean:write name="checkTaskInfo" property="projectApprovalRecord.parProjectName"/>
		</td>

		<td align="right">
			<bean:message bundle="approvalrecord" key="project_owner" />：
		</td>
		<td>			
			<bean:write name="checkTaskInfo" property="projectApprovalRecord.parProjectOwner"/>							
		</td>
		
	</tr>
	<tr>
		<td align="right">
			<bean:message bundle="approvalrecord" key="design_company" />：
		</td>
		<td>
			<bean:write name="checkTaskInfo" property="projectApprovalRecord.parDesignCompany"/>
		</td>

		<td align="right">
			<bean:message bundle="approvalrecord" key="tender_company" />：
		</td>
		<td>
			<bean:write name="checkTaskInfo" property="projectApprovalRecord.parTenderCompany"/>				
		</td>
		
	</tr>
	<tr>
		<td align="right">
			<bean:message bundle="approvalrecord" key="tender_price" />：
		</td>
		<td>
			<bean:write name="checkTaskInfo" property="projectApprovalRecord.parTenderPrice"/>万元
		</td>

		<td align="right">
			<bean:message bundle="approvalrecord" key="tender_time" />： 
		</td>
		<td >						
			<bean:write name="checkTaskInfo" property="projectApprovalRecord.parTenderDate"/>
		</td>									
	</tr>
	<tr>
		<td align="right">
			<bean:message bundle="approvalrecord" key="operating_expense" />：
		</td>
		<td>
			<bean:write name="checkTaskInfo" property="projectApprovalRecord.parOperatingExpense"/>万元
		</td>

		<td align="right">
			<bean:message bundle="approvalrecord" key="business_type" />：
		</td>
		<td>										
			<bean:write name="checkTaskInfo" property="projectApprovalRecord.parBusinessType"/>
		</td>
		
	</tr>
	
	<tr>				
		<td align="right">
			<bean:message bundle="approvalrecord" key="business_relation" />：
		</td>
		<td>
			<bean:write name="checkTaskInfo" property="projectApprovalRecord.parBusinessRelation"/>
		</td>		
		<td align="right">
			<bean:message bundle="projectmanage" key="project.manager" />：
		</td>
		<td>
			<logic:notEmpty name="checkTaskInfo" property="projectApprovalRecord.manager">
				<bean:write name="checkTaskInfo" property="projectApprovalRecord.manager.person.personName"/>
			</logic:notEmpty>							
		</td>			
	</tr>
	<tr><td>&nbsp;</td></tr>	

	<tr>
		<td align="left">
			<bean:message bundle="approvalrecord" key="project_remark" />：
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3">
			<bean:write name="checkTaskInfo" property="projectApprovalRecord.parProjectRemark"/>
		</td>					
	</tr>
	<tr>
		<td align="left" colspan="2">
			<bean:message bundle="approvalrecord" key="risk_control" />：
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3">
			<bean:write name="checkTaskInfo" property="projectApprovalRecord.parRiskControl"/>
		</td>					
	</tr>
	<tr>
		<td align="left" colspan="2">
			<bean:message bundle="approvalrecord" key="actionability" />：
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3">
			<bean:write name="checkTaskInfo" property="projectApprovalRecord.parActionability"/>
		</td>					
	</tr>							
	
</table>

</logic:equal>

<logic:equal name="checkTaskInfo" property="ctTaskType" value="3">
<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
	<caption>投标信息</caption>
	<tr>
		<td width="10%"></td>
		<td width="40%"></td>				
		<td width="10%"></td>
		<td></td>
	</tr>
	<tr>
		<td align="right">
			<bean:message bundle="approvalrecord" key="project_owner" />：
		</td>
		<td>										
			<bean:write name="checkTaskInfo" property="projectApprovalRecord.parProjectOwner"/>		
		</td>
		<td align="right">
			<bean:message bundle="checktask" key="task.dept" />：
		</td>
		<td>
			<bean:write name="checkTaskInfo" property="projectApprovalRecord.commitDept.DName"/>			
		</td>
	</tr>
	<tr>
		<td align="right">
			<bean:message bundle="approvalrecord" key="tender_member" />：
		</td>
		<td>
			<bean:write name="checkTaskInfo" property="projectApprovalRecord.parTenderMember"/>
		</td>

		<td align="right">
			<bean:message bundle="approvalrecord" key="tender_company" />：
		</td>
		<td>										
			<bean:write name="checkTaskInfo" property="projectApprovalRecord.parTenderCompany"/>					
		</td>
		
	</tr>

	<tr><td>&nbsp;</td></tr>	
	<tr>
		<td colspan="3" align="left">
			<b>业主简介与项目概况</b>（包括业主资信、项目规模等）：
		</td>	
	</tr>
	<tr><td></td>	
		<td colspan="3">
			<bean:write name="checkTaskInfo" property="projectApprovalRecord.parTenderRemark"/>
		</td>					
	</tr>
	<tr><td>&nbsp;</td></tr>	
	<tr>
		<td align="right">
			<bean:message bundle="projectmanage" key="project.attachment" />：
		</td>
		<td colspan="2">			
			<logic:iterate id="am" name="checkTaskInfo" property="projectApprovalRecord.attachmentList" indexId="i">					
				<a href="projectManage.do?method=downloadAttachment&id=<bean:write name='am' property='paId'/>" title="<bean:write name="am" property="paLocalUrl"/>">
					<bean:write name="am" property="paName"/>						
				</a>；			
			</logic:iterate>		
		</td>			
		<td>
		
		</td>	
	</tr>	
		
</table>
</logic:equal>
<logic:equal name="checkTaskInfo" property="ctTaskType" value="4">

<table class="sort-table" cellStacing="1" celltadding="1" width="100%" border="0">					
	<thead>
		<tr>
			<td width="5%" align="center">
				<bean:message bundle="projectmanage" key="serial" />
			</td>						
			<td width="10%" align="center">
				<bean:message bundle="attachment" key="code" />
			</td>											
			<td  width="20%" align="center">
				<bean:message bundle="attachment" key="category" />
			</td>						
			<td width="50%" align="center">
				<bean:message bundle="attachment" key="filename" />
			</td>
			<td width="5%" align="center">
				<bean:message bundle="attachment" key="download" />
			</td>
			
		</tr>
	</thead>
	<tbody>
		<logic:iterate id="ta" name="checkTaskInfo" property="project.tenderAttachmentList" indexId="i">
		<logic:notEqual name="ta" property="taFlag" value="-1">
		  
			<% int a = i%2;request.setAttribute("a",a);%>
			<logic:equal name="a" value="0"><tr class="even"></logic:equal>
			<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
				<td align="center"><%=i+1 %></td>
				<td><bean:write name="ta" property="taCode"/></td>
				<td>
					<logic:notEmpty name="ta" property="category">
						<bean:write name="ta" property="category.acName"/>
					</logic:notEmpty>	
				</td>
				<td><bean:write name="ta" property="taName"/></td>				
				<td align="center"><a href='tenderProjectManage.do?method=downloadTenderAttachment&id=<bean:write name="ta" property="taId"/>'><img border="0" src="pages\images\icon\16x16\make-index.gif"></a></td>
						
			</tr>
		</logic:notEqual>
		</logic:iterate>
	</tbody>
</table>

</logic:equal>
<logic:equal name="checkTaskInfo" property="ctTaskType" value="5">
	<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">
		<thead>
			<tr>
				<td align="center">
					<bean:message bundle="projectmanage" key="project.name" />
				</td>
				<td width="20%" align="center">
					<bean:message bundle="tendermanage" key="tender.clientName" />
				</td>						
				<td width="10%" align="center">
					<bean:message bundle="projectmanage" key="project.begindate" />
				</td>
				<td width="10%" align="center">
					<bean:message bundle="projectmanage" key="project.enddate" />
				</td>	
				<td width="10%" align="center">
					<bean:message bundle="projectmanage" key="project.manager" />
				</td>						
				<td width="8%" align="center">
					<bean:message bundle="projectmanage" key="project.status" />
				</td>
				
			</tr>
		</thead>
		<tbody>
			<tr>			
				<td align="center"><bean:write name="checkTaskInfo" property="project.tpName"/></td>											
				<td align="center"><bean:write name="checkTaskInfo" property="project.customer.CName"/></td>			
				<td align="center"><bean:write name="checkTaskInfo" property="project.tpBeginDate"/></td>
				<td align="center"><bean:write name="checkTaskInfo" property="project.tpEndDate"/></td>
				<td align="center">
					<logic:notEmpty name="checkTaskInfo" property="project.manager">
						<bean:write name="checkTaskInfo" property="project.manager.person.personName"/>
					</logic:notEmpty>
				</td>
				<td align="center"><bean:write name="checkTaskInfo" property="project.status.psName"/></td>
			</tr>
		</tbody>
	</table>		
	<table class="sort-table" WIDTH="100%" border="0">
		<thead>
			<tr>
				<td width="5%"></td><td width="5%"></td><td width="30%"></td><td width="15%"></td><td></td>
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
			<logic:equal name="checkTaskInfo" property='<%= "projectBudget.e" + i + ".itemModel.bimTenderFlag" %>' value="1">
			<tr>
				<logic:equal name="checkTaskInfo" property='<%= "projectBudget.e" + i + ".itemModel.bimGatherFlag" %>' value="1">
					<%a++; %>
					<td align="center"><%=a%></td>	
					<td colspan="2" align="center"><bean:write name="checkTaskInfo" property='<%= "projectBudget.e" + i + ".itemModel.bimName" %>'/></td>
				</logic:equal>
				<logic:equal name="checkTaskInfo" property='<%= "projectBudget.e" + i + ".itemModel.bimGatherFlag" %>' value="0">
					<td>&nbsp;</td>
					<td align="center"><bean:write name="checkTaskInfo" property='<%= "projectBudget.e" + i + ".itemModel.bimCode" %>'/></td>
					<td align="center"><bean:write name="checkTaskInfo" property='<%= "projectBudget.e" + i + ".itemModel.bimName" %>'/></td>
				</logic:equal>				
				<td align="center"><bean:write name="checkTaskInfo" property='<%= "projectBudget.e" + i + "AmountStr" %>'/></td>						
				<td align="left"><bean:write name="checkTaskInfo" property='<%= "projectBudget.e" + i + ".biRemark" %>'/></td>					
		        
			</tr>
			</logic:equal>
			<%} %>
			
		</tbody>
	</table>
</logic:equal>

<logic:equal name="checkTaskInfo" property="ctTaskType" value="6">
	<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">
		<thead>
			<tr>
				<td align="center">
					<bean:message bundle="projectmanage" key="project.name" />
				</td>
				<td width="20%" align="center">
					<bean:message bundle="tendermanage" key="tender.clientName" />
				</td>						
				<td width="10%" align="center">
					<bean:message bundle="projectmanage" key="project.begindate" />
				</td>
				<td width="10%" align="center">
					<bean:message bundle="projectmanage" key="project.enddate" />
				</td>	
				<td width="10%" align="center">
					<bean:message bundle="projectmanage" key="project.manager" />
				</td>						
				<td width="8%" align="center">
					<bean:message bundle="projectmanage" key="project.status" />
				</td>
				
			</tr>
		</thead>
		<tbody>
			<tr>			
				<td align="center"><bean:write name="checkTaskInfo" property="project.tpName"/></td>											
				<td align="center"><bean:write name="checkTaskInfo" property="project.customer.CName"/></td>			
				<td align="center"><bean:write name="checkTaskInfo" property="project.tpBeginDate"/></td>
				<td align="center"><bean:write name="checkTaskInfo" property="project.tpEndDate"/></td>
				<td align="center">
					<logic:notEmpty name="checkTaskInfo" property="project.manager">
						<bean:write name="checkTaskInfo" property="project.manager.person.personName"/>
					</logic:notEmpty>
				</td>
				<td align="center"><bean:write name="checkTaskInfo" property="project.status.psName"/></td>
			</tr>
		</tbody>
	</table>		
	<table class="sort-table" WIDTH="100%" border="1">
		<thead>
			<tr>
				<td width="5%"></td><td width="5%"></td><td width="30%"></td><td width="15%"></td><td></td>
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
			<logic:equal name="checkTaskInfo" property='<%= "projectBudget.e" + i + ".itemModel.bimApplyFlag" %>' value="1">
			<tr>
				<logic:equal name="checkTaskInfo" property='<%= "projectBudget.e" + i + ".itemModel.bimGatherFlag" %>' value="1">
					<%a++; %>
					<td align="center"><%=a%></td>	
					<td colspan="2" align="center"><bean:write name="checkTaskInfo" property='<%= "projectBudget.e" + i + ".itemModel.bimName" %>'/></td>
				</logic:equal>
				<logic:equal name="checkTaskInfo" property='<%= "projectBudget.e" + i + ".itemModel.bimGatherFlag" %>' value="0">
					<td>&nbsp;</td>
					<td align="center"><bean:write name="checkTaskInfo" property='<%= "projectBudget.e" + i + ".itemModel.bimCode" %>'/></td>
					<td align="center"><bean:write name="checkTaskInfo" property='<%= "projectBudget.e" + i + ".itemModel.bimName" %>'/></td>
				</logic:equal>				
				<td align="center"><bean:write name="checkTaskInfo" property='<%= "projectBudget.e" + i + "AmountStr" %>'/></td>						
				<td align="left"><bean:write name="checkTaskInfo" property='<%= "projectBudget.e" + i + ".biRemark" %>'/></td>					
		        
			</tr>
			</logic:equal>
			<%} %>
			
		</tbody>
	</table>
</logic:equal>
<table class="normal-table" cellSpacing="1" cellPadding="1" width="100%" border="0">	
	<caption>评审记录</caption>		
	<thead>
		<tr>
			<td width="5%" align="center">
				<bean:message bundle="projectmanage" key="serial" />
			</td>
			<td width="8%" align="center">
				<bean:message bundle="checktask" key="task.senduser" />
			</td>			
			<td width="10%" align="center">
				<bean:message bundle="checktask" key="task.sendtime" />
			</td>
			<td width="10%" align="center">
				<bean:message bundle="checktask" key="task.receiveDept" />
			</td>			
			<td width="8%" align="center">
				<bean:message bundle="checktask" key="task.receivePost" />
			</td>
			<td width="10%" align="center">
				<bean:message bundle="checktask" key="task.checkableUser" />
			</td>
			<td width="8%" align="center">
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
		 
			<% int a = ((org.king.common.checktask.CheckTask)task).getCtCheckStep()%2;request.setAttribute("a",a);%>
			<logic:equal name="a" value="0"><tr class="blue"></logic:equal>
			<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
				<td align="center">										
					<%=i+1 %>
					<logic:equal name="task" property="ctCheckStep" value="1">↓</logic:equal>
					<logic:notEqual name="task" property="ctCheckStep" value="1">&nbsp;&nbsp;</logic:notEqual>
				</td>
				<td align="center"><bean:write name="task" property="sendUser.person.personName"/></td>
				<td align="center"><bean:write name="task" property="ctSendTime"/></td>
				<td align="center"><bean:write name="task" property="receiveDepartment.DName"/></td>
								
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
					<logic:equal name="task" property="ctStatus" value="-1">--</logic:equal>
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
