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
		</tr>
	</thead>
	<tbody>			
		<logic:iterate name="checkedTaskList" id="task" indexId="i">			
			  <tr>
				<% int a = i%2;request.setAttribute("a",a);%>
				<logic:equal name="a" value="0"><tr class="even"></logic:equal>
				<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
					<td align="center">										
						<%=i+1 %>
					</td>
					<td>
					<logic:notEmpty name="task" property="contract">
						<bean:write name="task" property="contract.pcTitle"/>
					</logic:notEmpty>
					<logic:notEmpty name="task" property="projectApprovalRecord">
						<bean:write name="task" property="projectApprovalRecord.parProjectName"/>
					</logic:notEmpty>
				</td>	
				<td align="center">
					<logic:notEmpty name="task" property="contract">
						<logic:equal name="task" property="contract.pcStatus" value="1">评审中</logic:equal>
						<logic:equal name="task" property="contract.pcStatus" value="2">驳回</logic:equal>
						<logic:equal name="task" property="contract.pcStatus" value="3">通过</logic:equal>
					</logic:notEmpty>
					<logic:notEmpty name="task" property="projectApprovalRecord">
						<logic:equal name="task" property="projectApprovalRecord.parStatus" value="1">评审中</logic:equal>
						<logic:equal name="task" property="projectApprovalRecord.parStatus" value="2">驳回</logic:equal>
						<logic:equal name="task" property="projectApprovalRecord.parStatus" value="3">通过</logic:equal>
					</logic:notEmpty>
				</td>
				
				<td align="center">
					<logic:equal name="task" property="ctTaskType" value="1">合同评审</logic:equal>
					<logic:equal name="task" property="ctTaskType" value="2">立项备案评审</logic:equal>
				</td>
				<td align="center"><bean:write name="task" property="checkableUserList"/></td>
				<td align="center"><bean:write name="task" property="receiveDepartment.DName"/></td>				
				<td align="center"><bean:write name="task" property="sendUser.person.personName"/></td>		
				<td align="center"><bean:write name="task" property="ctSendTime"/></td>
				<td align="center">
					<a href='javascript:openDialog("projectManage.do?method=readCheckTask&id=<bean:write name="task" property="ctId"/>",800,400);'><img border="0" src="pages\images\icon\16x16\display.gif"></a>
				</td>										
				</tr>
		</logic:iterate>			
	</tbody>
</table>		
	


<script language="JavaScript">

 function chgAction(obj,str){
	obj.value=str;	
 }
 
 
 function initPageNo()
 {
 	document.getElementById("pager.offset").value='0';
 }
 
  function openDialog(loadpos,WWidth,WHeight)//Lock   Size 
{   	
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);   
	var WTop = Math.ceil((window.screen.height - WHeight) / 2); 
	var features = 'width=' + WWidth + 'px,' +	'height=' + WHeight + 'px,' + 'left=' + WLeft + 'px,' + 'top=' + WTop + 'px'; 
		
	WinOP = window.open(loadpos,"_blank",features); 
	WinOP.focus();   
}
</script>
