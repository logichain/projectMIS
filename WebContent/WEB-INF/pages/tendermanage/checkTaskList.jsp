<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<div class="scroll">	

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
