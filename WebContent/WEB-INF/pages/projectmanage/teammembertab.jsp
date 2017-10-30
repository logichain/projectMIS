<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<html:form action="projectManage.do" onsubmit="return checkFormValidate();">
	<html:errors />
	<input type="hidden" name="id" value="">
	<input type="hidden" name="method" value="saveTeamMember">	

	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">			
		<tr><td><bean:message bundle="projectmanage" key="member.logtitle" /></td></tr>
	</table>
			
	<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">							
		<thead>
			<tr>
				<td width="4%" align="center">
					<bean:message bundle="projectmanage" key="serial" />
				</td>
				<td width="10%" align="center">
					<bean:message bundle="projectmanage" key="scheduleplan.changetime" />
				</td>					
				<td width="25%" align="center">
					<bean:message bundle="projectmanage" key="scheduleplan.changepoint" />
				</td>
				<td width="25%" align="center">
					<bean:message bundle="projectmanage" key="scheduleplan.changereason" />
				</td>
				<td width="8%" align="center">
					<bean:message bundle="projectmanage" key="scheduleplan.changeperson" />
				</td>
				<td align="center">
					<bean:message bundle="projectmanage" key="scheduleplan.checkperson" />
				</td>
			</tr>
		</thead>
		<tbody>					
						
		</tbody>
	</table>

	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">			
		<tr><td>&nbsp;</td></tr>
		
		<tr>
			<td><bean:message bundle="security" key="person.name" />：</td>
			<td>
				<html:text styleId="personName" property="projectInfo.currentProjectTeam.account.person.personName" size="18" maxlength="100" readonly="true" style="background-color:LightGray;"/>*
				<html:button property="" onclick="openDialog('projectManage.do?method=searchAccount&opType=teamMember',800,600);">...</html:button>
			</td>
			<td><bean:message bundle="projectmanage" key="member.projectrole" />：</td>
			<td>
				<html:select styleId="projectRole" property="projectInfo.currentProjectTeam.ptProjectRole" style="width:120">	
					<html:options collection="projectRoleList" property="prId" labelProperty="prName"/>
				</html:select>*				
			</td>
			<td><bean:message bundle="projectmanage" key="feetax.remark" />：</td>
			<td>
				<html:text property="projectInfo.currentProjectTeam.ptRemark" size="30" maxlength="100"/>*
			</td>	
			<td align="center">
				<logic:equal name="accountPerson" property="hasCreatePower" value="true">	
					<html:submit styleClass="addbutton" onclick="chgAction(document.all.method,'addTeamMember');">
						&nbsp;
					</html:submit>				
					<html:submit styleClass="savebutton" onclick="chgFormOnsubmit('return true;');">
						&nbsp;
					</html:submit>				
				</logic:equal>	
			</td>									
		</tr>
		<tr><td>&nbsp;</td></tr>
	</table>					
	<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">					
		<thead>
			<tr>
				<td width="4%" align="center">
					<bean:message bundle="projectmanage" key="serial" />
				</td>
				<td width="12%" align="center">
					<bean:message bundle="projectmanage" key="member.projectrole" />
				</td>
				<td width="15%" align="center">
					<bean:message bundle="security" key="person.name" />
				</td>
				
				<td width="20%" align="center">
					<bean:message bundle="security" key="person.dept" />/
					<bean:message bundle="security" key="person.post" />
				</td>
				<td width="10%" align="center"><bean:message bundle="projectmanage" key="member.projectrole" /></td>
				
				<td align="center">
					<bean:message bundle="projectmanage" key="feetax.remark" />
				</td>
				<td width="5%" align="center">
					详细
				</td>
				<td width="5%" align="center">
					<bean:message key="button.delete" />
				</td>
			</tr>
		</thead>
		<tbody>				
			<%int index = 1;%>	
			<logic:iterate name="projectSearchForm" property="projectInfo.teamMemberList" id="tm" indexId="i">	
			<logic:notEmpty name="tm" property="ptProjectRole">	
			<logic:notEqual name="tm" property="ptFlag" value="-1">
			  <tr>				
				<% int a = i%2;request.setAttribute("a",a);%>
				<logic:equal name="a" value="0"><tr class="even"></logic:equal>
				<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
					<td align="center">										
						<%=index %>
						<%index++; %>
					</td>
					<td align="center"><bean:write name="tm" property="projectRole.prName"/></td>
					<td><bean:write name="tm" property="account.person.personName"/></td>									
					
					<td align="center">						
						<logic:iterate id="up" name="tm" property="account.usrPostList">
							<logic:notEmpty name="up" property="deptObject">
								<bean:write name="up" property="deptObject.DName"/>
							</logic:notEmpty>/
							<logic:notEmpty name="up" property="postObject">
								<bean:write name="up" property="postObject.PName"/>
							</logic:notEmpty>;
						</logic:iterate>
					</td>
					<td><bean:write name="tm" property="projectRole.prName"/></td>
					
					<td align="center"><bean:write name="tm" property="ptRemark"/></td>
					<td align="center"><a href="javascript:openDialogNoSubmit('projectManage.do?method=displayUserAccountInfo&id=<bean:write name="tm" property='ptAccount'/>', 600,300);void(0);"><img border="0" src="pages\images\icon\16x16\display.gif"/></a></td>
					<td align="center">
						<logic:equal name="accountPerson" property="hasCreatePower" value="true">
							<logic:notEmpty name="tm" property="ptId">
								<a href='javascript:if(confirm("确认要删除这条信息吗?")) {chgAction(document.all.id,"<bean:write name="tm" property="ptId"/>");chgAction(document.all.method,"deleteProjectTeamMember");projectSearchForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
							</logic:notEmpty>
							<logic:empty name="tm" property="ptId">
								<a href='projectManage.do?method=deleteProjectTeamMemberByIndex&index=<%=i %>'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
							</logic:empty>
						</logic:equal>
					</td>					
				</tr>				
			</logic:notEqual>
			</logic:notEmpty>
			</logic:iterate>			
		</tbody>
	</table>				
</html:form>

