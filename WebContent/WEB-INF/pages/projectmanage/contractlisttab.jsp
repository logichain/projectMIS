<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<html:form action="projectManage.do" onsubmit="return checkFormValidate();">
	<html:errors />

	<input type="hidden" name="id" value="">
	<input type="hidden" name="method" value="saveProjectDocument">		
			
	<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">					
			<thead>
				<tr>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="serial" />
					</td>						
					<td width="15%" align="center">
						<bean:message bundle="projectmanage" key="contract.title" />
					</td>											
					<td  width="5%" align="center">
						<bean:message bundle="projectmanage" key="contract.type" />
					</td>						
					<td align="center">
						<bean:message bundle="projectmanage" key="project.customer" />
					</td>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="task.checkresult" />
					</td>
					<td width="10%" align="center">
						<bean:message bundle="projectmanage" key="task.sendtime" />
					</td>
					<td width="10%" align="center">
						<bean:message bundle="projectmanage" key="task.oktime" />
					</td>
					<td width="15%" align="center">
						<bean:message bundle="projectmanage" key="project.attachment" />
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
						<td>
							<logic:equal name="pc" property="pcType" value="1">分包合同</logic:equal>
							<logic:equal name="pc" property="pcType" value="2">采购合同</logic:equal>
							<logic:equal name="pc" property="pcType" value="3">其他类型</logic:equal>
						</td>
						<td><bean:write name="pc" property="customer.CName"/></td>
						<td>
							<logic:equal name="pc" property="pcStatus" value="1">
								<bean:message bundle="projectmanage" key="button.ok"/>
							</logic:equal>
							<logic:equal name="pc" property="pcStatus" value="2">
								评审中
							</logic:equal>
						</td>
						<td><bean:write name="pc" property="pcCheckBegin"/></td>
						<td><bean:write name="pc" property="pcCheckOk"/></td>
						<td>
							<logic:iterate id="am" name="pc" property="attachmentList">
								<logic:equal name="am" property="paInputInterface" value="3">
								<logic:notEqual name='am' property='paFlag' value="-1">									
									<a href="projectManage.do?method=downloadAttachment&id=<bean:write name='am' property='paId'/>" title="<bean:write name="am" property="paLocalUrl"/>">
										<bean:write name="am" property="paName"/>
									</a>									
								</logic:notEqual>	
								</logic:equal>					
							</logic:iterate>						
						</td>
													
						<logic:equal name="pc" property="pcStatus" value="0">							
							<td align="center"><a href='projectManage.do?method=editProjectContractCheck&cid=<bean:write name="pc" property="pcId"/>&id=<bean:write name="pc" property="pcProject"/>'><img border="0" src="pages\images\icon\16x16\modify.gif"></a></td>
							<td align="center"><a href='javascript:if(confirm("确认要删除这条信息吗?")) {chgAction(document.all.id,"<bean:write name="pc" property="pcId"/>");chgAction(document.all.method,"deleteProjectContract");projectSearchForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a></td>
						</logic:equal>
						<logic:notEqual name="pc" property="pcStatus" value="0">							
							<td align="center"></td>
							<td align="center"></td>
						</logic:notEqual>
						
					</tr>
				</logic:notEqual>
				</logic:iterate>
			</tbody>
		</table>				
</html:form>

<script language="JavaScript">

 function chgAction(obj,str){
	obj.value=str;
 }
 
</script>