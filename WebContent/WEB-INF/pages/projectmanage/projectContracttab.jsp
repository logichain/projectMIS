<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<html:form action="projectManage.do" onsubmit="return checkFormValidate();">
	<html:errors />
	
	<input type="hidden" name="method" value="saveProjectContract">
	<input type="hidden" name="id">		
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
		<tr><td>&nbsp;</td></tr>	
		<tr>
			<td ></td>						
			<td width="15%" align="center">	
				<logic:equal name="accountPerson" property="hasCreatePower" value="true">				
					<html:button property="" styleClass="createbutton" onclick="openDialog('projectManage.do?method=createProjectContract',800,300);" style="cursor: hand;">
						&nbsp;
					</html:button>				
					<html:submit styleClass="savebutton">
						&nbsp;
					</html:submit>			
				</logic:equal>
			</td>				
		</tr>
	</table>					
					
	<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">					
			<thead>
				<tr>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="serial" />
					</td>						
					<td align="center">
						<bean:message bundle="projectmanage" key="contract.title" />
					</td>											
					<td  width="10%" align="center">
						<bean:message bundle="projectmanage" key="contract.type" />
					</td>						
					<td align="center">
						<bean:message bundle="projectmanage" key="project.customer" />
					</td>
					<td width="10%" align="center">
						<bean:message bundle="projectmanage" key="task.checkstatus" />
					</td>
					
					<td width="5%" align="center">
						<bean:message bundle="checktask" key="task.read" />
					</td>
					<td width="5%" align="center">
						<bean:message key="button.edit" />
					</td>
					<td width="5%" align="center">
						<bean:message key="button.delete" />
					</td>
				</tr>
			</thead>
			<tbody>
				<%int i=0;%>
				<logic:iterate id="pc" name="projectSearchForm" property="projectInfo.projectContractList">
				<logic:notEqual name="pc" property="pcStatus" value="-1">					
				 	<% int a = i%2;request.setAttribute("a",a);i++;%>
					<logic:equal name="a" value="0"><tr class="even"></logic:equal>
					<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
						<td align="center"><%=i%></td>
						<td><bean:write name="pc" property="pcTitle"/></td>
						<td align="center">
							<logic:equal name="pc" property="pcType" value="1">订出合同</logic:equal>
							<logic:equal name="pc" property="pcType" value="2">订进合同</logic:equal>
						</td>
						<td><bean:write name="pc" property="customer.CName"/></td>
						<td>
							<logic:equal name="pc" property="pcStatus" value="0">
								初始
							</logic:equal>
							<logic:equal name="pc" property="pcStatus" value="1">
								评审中
							</logic:equal>
							<logic:equal name="pc" property="pcStatus" value="2">
								驳回
							</logic:equal>
							<logic:equal name="pc" property="pcStatus" value="3">
								通过
							</logic:equal>
						</td>
											
						
						<td align="center">
							<logic:notEmpty name="pc" property="pcId">
								<a href='javascript:openDialog("projectManage.do?method=readProjectContract&id=<bean:write name="pc" property="pcId"/>",800,400);'><img border="0" src="pages\images\icon\16x16\display.gif"></a>
							</logic:notEmpty>
						</td>					
								
													
						<td align="center">
							<logic:equal name="accountPerson" property="hasCreatePower" value="true">
							<logic:notEmpty name="pc" property="pcId">											
								<logic:equal name="pc" property="pcStatus" value="0">
									<a href='javascript:openDialog("projectManage.do?method=editProjectContract&id=<bean:write name="pc" property="pcId"/>",800,300);'><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
								</logic:equal>
								<logic:equal name="pc" property="pcStatus" value="2">
									<a href='javascript:openDialog("projectManage.do?method=editProjectContract&id=<bean:write name="pc" property="pcId"/>",800,300);'><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
								</logic:equal>
							</logic:notEmpty>
							</logic:equal>
						</td>
						<td align="center">
							<logic:equal name="accountPerson" property="hasCreatePower" value="true">
							<logic:notEmpty name="pc" property="pcId">											
								<logic:equal name="pc" property="pcStatus" value="0">	
										<a href='javascript:if(confirm("确认要删除这条信息吗?")) {chgAction(document.all.id,"<bean:write name="pc" property="pcId"/>");chgAction(document.all.method,"deleteProjectContract");projectSearchForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
								</logic:equal>
							</logic:notEmpty>
							</logic:equal>
						</td>						
					</tr>
				</logic:notEqual>
				</logic:iterate>
			</tbody>
		</table>
</html:form>


