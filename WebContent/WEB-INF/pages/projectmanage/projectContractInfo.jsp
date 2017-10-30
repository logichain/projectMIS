<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<div class="scroll">	
<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
	<tr>
		<td width="15%">&nbsp;</td><td width="40%"></td><td width="15%">&nbsp;</td><td></td>
	</tr>
	<tr>
		<td align="right">
			<bean:message bundle="projectmanage" key="contract.title"/>：
		</td>
		<td>
			<bean:write name="projectSearchForm" property="projectInfo.projectContract.pcTitle"/>
		</td>		
		<td align="right">
			<bean:message bundle="projectmanage" key="contract.type"/>：
		</td>
		<td>
			<logic:equal name="projectSearchForm" property="projectInfo.projectContract.pcType" value="1">订出合同</logic:equal>
			<logic:equal name="projectSearchForm" property="projectInfo.projectContract.pcType" value="2">订进合同</logic:equal>	
		</td>	
	</tr>
	<tr>			
		<td align="right">
			<bean:message bundle="projectmanage" key="project.customer" />：
		</td>
		<td colspan="3">
			<bean:write name="projectSearchForm" property="projectInfo.projectContract.customer.CName"/>			 				
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td align="right"><bean:message bundle="projectmanage" key="contract.price"/>：</td>
		<td><bean:write name="projectSearchForm" property="projectInfo.projectContract.pcContractPrice"/></td>
		<td align="right">
			<logic:equal name="projectSearchForm" property="projectInfo.projectContract.pcType" value="1">与订进合同差异：</logic:equal>
			<logic:equal name="projectSearchForm" property="projectInfo.projectContract.pcType" value="2">与投标书差异：</logic:equal>
		</td>
		<td><bean:write name="projectSearchForm" property="projectInfo.projectContract.pcPriceDiff"/></td>
	</tr>
	<tr>
		<td align="right"><bean:message bundle="projectmanage" key="contract.payment_type"/>：</td>
		<td><bean:write name="projectSearchForm" property="projectInfo.projectContract.pcPayType"/></td>		
	</tr>
	<tr>
		<td align="right"><bean:message bundle="projectmanage" key="contract.quality_amount_year"/>：</td>
		<td><bean:write name="projectSearchForm" property="projectInfo.projectContract.pcQualityAmountYear"/></td>
		<td align="right"><bean:message bundle="projectmanage" key="contract.quality_amount_percent"/>：</td>
		<td><bean:write name="projectSearchForm" property="projectInfo.projectContract.pcQualityAmountPercent"/></td>
	</tr>
	<tr>				
		<td align="right">
			<bean:message bundle="projectmanage" key="task.checkstatus" />：
		</td>
		<td>
			<logic:equal name="projectSearchForm" property="projectInfo.projectContract.pcStatus" value="0">初始</logic:equal>
			<logic:equal name="projectSearchForm" property="projectInfo.projectContract.pcStatus" value="1">评审中</logic:equal>
			<logic:equal name="projectSearchForm" property="projectInfo.projectContract.pcStatus" value="2">驳回</logic:equal>
			<logic:equal name="projectSearchForm" property="projectInfo.projectContract.pcStatus" value="3">通过</logic:equal>	
		</td>
		
	</tr>
	<tr><td>&nbsp;</td></tr>	

	<tr>
		<td align="right">
			<bean:message bundle="projectmanage" key="contract.remark" />：
		</td>
		<td>			
			<bean:write name="projectSearchForm" property="projectInfo.projectContract.pcRemark"/>
		</td>				
	</tr>		
	<tr>
		<td align="right">
			<bean:message bundle="projectmanage" key="project.attachment" />：
		</td>
		<td>			
			<logic:iterate id="am" name="projectSearchForm" property="projectInfo.projectContract.attachmentList" indexId="i">
				<logic:equal name="am" property="paInputInterface" value="3">
					<a href="projectManage.do?method=downloadAttachment&id=<bean:write name='am' property='paId'/>" title="<bean:write name="am" property="paLocalUrl"/>">
						<bean:write name="am" property="paName"/>
					</a>					
				</logic:equal>		
			</logic:iterate>				
		</td>	
	</tr>
	<tr><td>&nbsp;</td></tr>
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
