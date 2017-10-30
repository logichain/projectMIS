<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

							
<table class="sort-table" width="100%" border="0">		
	<thead>
		<tr>
			<td width="4%" align="center">
				<bean:message bundle="projectmanage" key="serial" />
			</td>
			<td align="center">
				<bean:message bundle="checktask" key="task.name" />
			</td>			
			<td width="5%" align="center">
				<bean:message bundle="checktask" key="task.status" />
			</td>
			<td width="10%" align="center">
				<bean:message bundle="checktask" key="task.type" />
			</td>
			<td width="15%" align="center">
				<bean:message bundle="checktask" key="task.checkableUser" />
			</td>
			<td width="10%" align="center">
				<bean:message bundle="checktask" key="task.dept" />
			</td>
			<td width="5%" align="center">
				<bean:message bundle="checktask" key="task.senduser" />
			</td>
			<td width="12%" align="center">
				<bean:message bundle="checktask" key="task.sendtime" />
			</td>			
			<td width="4%" align="center">
				<bean:message bundle="checktask" key="task.read" />
			</td>
			<logic:notEqual name="accountPerson" property="id" value="0">
				<td width="4%" align="center">
					<bean:message  bundle="checktask" key="task.do" />
				</td>
			</logic:notEqual>
		</tr>
	</thead>
	<tbody>		
<logic:notEmpty name="currentTaskList">
		<logic:iterate name="currentTaskList" id="task" indexId="i">		
		  <tr>
			<% int a = i%2;request.setAttribute("a",a);%>
			<logic:equal name="a" value="0"><tr class="even"></logic:equal>
			<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
				<td align="center">										
					<%=i+1 %>
				</td>
				<td>
					<logic:equal name="task" property="ctTaskType" value="1">
						<bean:write name="task" property="contract.pcTitle"/>
					</logic:equal>
					<logic:notEmpty name="task" property="projectApprovalRecord">
						<bean:write name="task" property="projectApprovalRecord.parProjectName"/>
					</logic:notEmpty>
					<logic:equal name="task" property="ctTaskType" value="5">
						<bean:write name="task" property="project.tpName"/>
					</logic:equal>
					<logic:equal name="task" property="ctTaskType" value="6">
						<bean:write name="task" property="project.tpName"/>
					</logic:equal>
					<logic:equal name="task" property="ctTaskType" value="4">
						<bean:write name="task" property="project.tpName"/>
					</logic:equal>
				</td>									
				
				<td align="center">
					<logic:equal name="task" property="ctTaskType" value="1">
						<logic:equal name="task" property="contract.pcStatus" value="1">评审中</logic:equal>
						<logic:equal name="task" property="contract.pcStatus" value="2">驳回</logic:equal>
						<logic:equal name="task" property="contract.pcStatus" value="3">通过</logic:equal>
					</logic:equal>
					<logic:notEmpty name="task" property="projectApprovalRecord">
						<logic:equal name="task" property="projectApprovalRecord.parStatus" value="1">评审中</logic:equal>
						<logic:equal name="task" property="projectApprovalRecord.parStatus" value="2">驳回</logic:equal>
						<logic:equal name="task" property="projectApprovalRecord.parStatus" value="3">通过</logic:equal>
						<logic:equal name="task" property="projectApprovalRecord.parStatus" value="4">评审中</logic:equal>
						<logic:equal name="task" property="projectApprovalRecord.parStatus" value="5">驳回</logic:equal>
						<logic:equal name="task" property="projectApprovalRecord.parStatus" value="6">通过</logic:equal>
					</logic:notEmpty>
					<logic:equal name="task" property="ctTaskType" value="5">
						<logic:equal name="task" property="project.tenderBudget.pbCheckResult" value="1">评审中</logic:equal>
						<logic:equal name="task" property="project.tenderBudget.pbCheckResult" value="2">驳回</logic:equal>
						<logic:equal name="task" property="project.tenderBudget.pbCheckResult" value="3">通过</logic:equal>
					</logic:equal>
					<logic:equal name="task" property="ctTaskType" value="6">
						<logic:equal name="task" property="project.applyBudget.pbCheckResult" value="1">评审中</logic:equal>
						<logic:equal name="task" property="project.applyBudget.pbCheckResult" value="2">驳回</logic:equal>
						<logic:equal name="task" property="project.applyBudget.pbCheckResult" value="3">通过</logic:equal>
					</logic:equal>
					<logic:equal name="task" property="ctTaskType" value="4">
						<logic:equal name="task" property="project.tpTenderdocCheckStatus" value="1">评审中</logic:equal>
						<logic:equal name="task" property="project.tpTenderdocCheckStatus" value="2">驳回</logic:equal>
						<logic:equal name="task" property="project.tpTenderdocCheckStatus" value="3">通过</logic:equal>
					</logic:equal>
				</td>
				
				<td align="center">
					<logic:equal name="task" property="ctTaskType" value="1">合同评审</logic:equal>
					<logic:equal name="task" property="ctTaskType" value="2">立项备案评审</logic:equal>
					<logic:equal name="task" property="ctTaskType" value="3">投标评审</logic:equal>
					<logic:equal name="task" property="ctTaskType" value="4">标书评审</logic:equal>
					<logic:equal name="task" property="ctTaskType" value="5">预算标前评审</logic:equal>
					<logic:equal name="task" property="ctTaskType" value="6">预算执行评审</logic:equal>
				</td>
				<td align="center"><bean:write name="task" property="checkableUserList"/></td>
				<td align="center"><bean:write name="task" property="receiveDepartment.DName"/></td>
				<td align="center"><bean:write name="task" property="sendUser.person.personName"/></td>		
				<td align="center"><bean:write name="task" property="ctSendTime"/></td>
				<td align="center">
					<a href='javascript:openDialog("projectManage.do?method=readCheckTask&id=<bean:write name="task" property="ctId"/>",800,500);'><img border="0" src="pages\images\icon\16x16\display.gif"></a>
				</td>				
				<logic:notEqual name="accountPerson" property="id" value="0">
					<td align="center">
<%-- 						<logic:notEqual name="accountPerson" property="hasCreatePower" value="true"> --%>
							<a href='javascript:openDialog("projectManage.do?method=doCheckTask&src=about&id=<bean:write name="task" property="ctId"/>",800,400);'><img border="0" src="pages\images\icon\16x16\authority.gif"></a>
<%-- 						</logic:notEqual> --%>
					</td>										
				</logic:notEqual>
			</tr>				
		</logic:iterate>	
</logic:notEmpty>	
	</tbody>
</table>					

	


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
