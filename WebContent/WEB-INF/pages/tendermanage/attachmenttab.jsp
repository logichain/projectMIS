<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<html:form action="tenderProjectManage.do" onsubmit="return checkFormValidate();">
		<html:errors />

		<input type="hidden" name="id" value="">
		<input type="hidden" name="method" value="saveProjectDocument">		
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr><td>&nbsp;</td></tr>	
			<tr><td align="right">
					
				</td>						
				<td align="center" width="12%">
					<logic:equal name="accountPerson" property="hasCreatePower" value="true">
						<html:button styleClass="addbutton" property="" onclick="openDialog('tenderProjectManage.do?method=addAttachment&type=attachmentInput',500,180);">
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
					<td width="5%" align="center">
						<bean:message key="button.edit" />
					</td>
					<td align="center">
						<bean:message key="button.delete" />
					</td>
				</tr>
			</thead>
			<tbody>
				<logic:iterate id="pa" name="tenderProjectForm" property="tenderInfo.attachmentList" indexId="i">
				<logic:notEqual name="pa" property="paFlag" value="-1">
				  <tr>
					<% int a = i%2;request.setAttribute("a",a);%>
					<logic:equal name="a" value="0"><tr class="even"></logic:equal>
					<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
						<td align="center"><%=i+1 %></td>
						<td><bean:write name="pa" property="paCode"/></td>
						<td>
							<logic:notEmpty name="pa" property="category">
								<bean:write name="pa" property="category.acName"/>
							</logic:notEmpty>	
						</td>
						<td><bean:write name="pa" property="paName"/></td>
						<logic:empty name="pa" property="paId">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td align="center">
								<logic:equal name="accountPerson" property="hasCreatePower" value="true">
									<a href='tenderProjectManage.do?method=deleteProjectAttachmentByIndex&index=<%=i %>'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
								</logic:equal>
							</td>
						</logic:empty>
						<logic:notEmpty name="pa" property="paId">
							<td align="center"><a href='tenderProjectManage.do?method=downloadAttachment&id=<bean:write name="pa" property="paId"/>'><img border="0" src="pages\images\icon\16x16\make-index.gif"></a></td>
							<td align="center">
								<logic:equal name="accountPerson" property="hasCreatePower" value="true">
									<a href='javascript:openDialog("tenderProjectManage.do?method=modifyAttachment&type=attachmentInput&id=<bean:write name="pa" property="paId"/>",500,200);'><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
								</logic:equal>
							</td>
							<td align="center">
								<logic:equal name="accountPerson" property="hasCreatePower" value="true">
									<a href='javascript:if(confirm("确认要删除这条信息吗?")) {chgAction(document.all.id,"<bean:write name="pa" property="paId"/>");chgAction(document.all.method,"deleteProjectAttachment");tenderInputForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
								</logic:equal>
							</td>
						</logic:notEmpty>
					</tr>
				</logic:notEqual>
				</logic:iterate>
			</tbody>
		</table>				
	</html:form>