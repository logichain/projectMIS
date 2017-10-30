<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<div class="scroll">
<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
	<caption>立项信息</caption>
	<tr>
		<td width="10%"></td>
		<td width="50%"></td>				
		<td width="12%"></td>
		<td></td>
	</tr>
	<tr>
		<td align="right">
			<bean:message bundle="approvalrecord" key="project_name" />：
		</td>
		<td>
			<bean:write name="projectApprovalRecordInfo" property="parProjectName"/>
		</td>
		
		<td align="right">
			<bean:message bundle="projectmanage" key="project.dept" />：
		</td>
		<td>
			<bean:write name="projectApprovalRecordInfo" property="department.DName"/>
		</td>
	</tr>
	<tr>
		<td align="right">
			<bean:message bundle="approvalrecord" key="project_owner" />：
		</td>
		<td>										
			<bean:write name="projectApprovalRecordInfo" property="parProjectOwner"/>		
		</td>
		<td align="right">
			<bean:message bundle="projectmanage" key="project.implement_dept" />：
		</td>
		<td>
			<logic:notEmpty name="projectApprovalRecordInfo" property="implementDepartment">
				<bean:write name="projectApprovalRecordInfo" property="implementDepartment.DName"/>
			</logic:notEmpty>
		</td>
	</tr>
	<tr>
		<td align="right">
			<bean:message bundle="approvalrecord" key="design_company" />：
		</td>
		<td>
			<bean:write name="projectApprovalRecordInfo" property="parDesignCompany"/>
		</td>
		<td align="right">
			<bean:message bundle="approvalrecord" key="market_manager" />：
		</td>
		<td>
			<logic:notEmpty name="projectApprovalRecordInfo" property="marketManager">
				<bean:write name="projectApprovalRecordInfo" property="marketManager.person.personName"/>
			</logic:notEmpty>
		</td>
	</tr>
	<tr>
		<td align="right">
			<bean:message bundle="approvalrecord" key="tender_company" />：
		</td>
		<td>										
			<bean:write name="projectApprovalRecordInfo" property="parTenderCompany"/>					
		</td>
		
	</tr>
	<tr>
		<td align="right">
			<bean:message bundle="approvalrecord" key="tender_price" />：
		</td>
		<td>
			<bean:write name="projectApprovalRecordInfo" property="parTenderPrice"/>万元
		</td>

		<td align="right">
			<bean:message bundle="approvalrecord" key="tender_time" />： 
		</td>
		<td >						
			<bean:write name="projectApprovalRecordInfo" property="parTenderDate"/>
		</td>									
	</tr>
	<tr>
		<td align="right">
			<bean:message bundle="approvalrecord" key="operating_expense" />：
		</td>
		<td>
			<bean:write name="projectApprovalRecordInfo" property="parOperatingExpense"/>万元
		</td>

		<td align="right">
			<bean:message bundle="approvalrecord" key="business_type" />：
		</td>
		<td>										
			<bean:write name="projectApprovalRecordInfo" property="parBusinessType"/>				
		</td>
		
	</tr>
	
	<tr>				
		<td align="right">
			<bean:message bundle="approvalrecord" key="business_relation" />：
		</td>
		<td>
			<bean:write name="projectApprovalRecordInfo" property="parBusinessRelation"/>
		</td>
		<td align="right">
			<bean:message bundle="projectmanage" key="project.manager" />：
		</td>
		<td>
			<bean:write name="projectApprovalRecordInfo" property="manager.person.personName"/>							
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
			<bean:write name="projectApprovalRecordInfo" property="parProjectRemark"/>
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
			<bean:write name="projectApprovalRecordInfo" property="parRiskControl"/>
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
			<bean:write name="projectApprovalRecordInfo" property="parActionability"/>
		</td>					
	</tr>		
	
</table>

<logic:greaterEqual name="projectApprovalRecordInfo" property="parStatus" value="4">
<logic:lessThan name="projectApprovalRecordInfo" property="parStatus" value="99">
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
		<caption>投标信息</caption>
		<tr>
			<td width="10%"></td>
			<td width="40%"></td>				
			<td width="10%"></td>
			<td></td>
		</tr>
		<tr>
			<td align="center">
				<bean:message bundle="approvalrecord" key="project_owner" />：
			</td>
			<td>										
				<bean:write name="projectApprovalRecordInfo" property="parProjectOwner"/>		
			</td>
			<td align="center">
				<bean:message bundle="checktask" key="task.dept" />：
			</td>
			<td>
				<bean:write name="projectApprovalRecordInfo" property="commitDept.DName"/>			
			</td>
		</tr>
		<tr>
			<td align="center">
				<bean:message bundle="approvalrecord" key="tender_member" />：
			</td>
			<td>
				<bean:write name="projectApprovalRecordInfo" property="parTenderMember"/>
			</td>

			<td align="center">
				<bean:message bundle="approvalrecord" key="tender_company" />：
			</td>
			<td>										
				<bean:write name="projectApprovalRecordInfo" property="parTenderCompany"/>					
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
				<bean:write name="projectApprovalRecordInfo" property="parTenderRemark"/>
			</td>					
		</tr>
		<tr><td>&nbsp;</td></tr>	
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="project.attachment" />：
			</td>
			<td colspan="2">
				
				<logic:iterate id="am" name="projectApprovalRecordInfo" property="attachmentList" indexId="i">					
					<a href="projectManage.do?method=downloadAttachment&id=<bean:write name='am' property='paId'/>" title="<bean:write name="am" property="paLocalUrl"/>">
						<bean:write name="am" property="paName"/>						
					</a>；			
				</logic:iterate>
			
			</td>			
			<td>
			
			</td>	
		</tr>	
					
	</table>
</logic:lessThan>
</logic:greaterEqual>	

<table class="normal-table" cellSpacing="1" cellPadding="1" width="100%" border="0">	
	<caption>评审记录</caption>	
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
			<td width="16%" align="center">
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

