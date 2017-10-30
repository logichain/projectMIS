<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<table class="normal-table" cellSpacing="1" cellPadding="1" width="100%" border="1">	
	<caption>评审记录</caption>			
	<tbody>		
		<tr>
			<td align="right" width="15%">
				<bean:message bundle="checktask" key="task.senduser" />:
			</td>			
			<td align="left"><bean:write name="task" property="sendUser.person.personName"/></td>
		</tr>
		<tr>	
			<td align="right">
				<bean:message bundle="checktask" key="task.sendtime" />:
			</td>
			<td align="left"><bean:write name="task" property="ctSendTime"/></td>
		</tr>	
		<tr>	
			<td align="right">
				<bean:message bundle="checktask" key="task.receiveDept" />:
			</td>
			<td align="left"><bean:write name="task" property="receiveDepartment.DName"/></td>
		</tr>	
		<tr>			
			<td align="right">
				<bean:message bundle="checktask" key="task.receivePost" />:
			</td>
			<td>
				<logic:notEmpty name="task" property="receivePost">
					<bean:write name="task" property="receivePost.PName"/>
				</logic:notEmpty>
				<logic:empty name="task" property="receivePost">部门经理	</logic:empty>
			</td>
		</tr>	
		<tr>			
			<td align="right">
				<bean:message bundle="checktask" key="task.checkableUser" />:
			</td>
			<td><bean:write name="task" property="checkableUserList"/></td>
		</tr>	
		<tr>			
			<td align="right">
				<bean:message bundle="checktask" key="task.checkUser" />:
			</td>
			<td>
				<logic:notEmpty name="task" property="checkUser">
					<bean:write name="task" property="checkUser.person.personName"/>
				</logic:notEmpty>
			</td>
		</tr>	
		<tr>			
			<td align="right">
				<bean:message bundle="checktask" key="task.checkTime" />:
			</td>
			<td><bean:write name="task" property="ctCheckTime"/></td>
		</tr>	
		<tr>			
			<td align="right">
				<bean:message bundle="checktask" key="task.checkResult" />:
			</td>
			<td>
				<logic:equal name="task" property="ctCheckResult" value="1">通过</logic:equal>
				<logic:equal name="task" property="ctCheckResult" value="2">驳回</logic:equal>
				<logic:equal name="task" property="ctStatus" value="-1">--</logic:equal>
			</td>
		</tr>	
		<tr>			
			<td align="right">
				<bean:message bundle="checktask" key="task.remark" />:
			</td>
			<td><bean:write name="task" property="ctRemark"/></td>
		</tr>		
	</tbody>
</table>

