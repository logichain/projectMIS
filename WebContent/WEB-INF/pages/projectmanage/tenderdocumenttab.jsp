<%@ include file="../common/include.jsp"%>

<html:form action="tenderProjectManage.do" onsubmit="return checkFormValidate();">
	<html:errors />

	<input type="hidden" name="id" value="">
	<input type="hidden" name="method" value="saveTenderDocument">		
	<table class="win" CELLtaDDING="0" CELLStaCING="0" WIDTH="100%" border="0">
		<tr><td>&nbsp;</td>
			<td align="center" width="15%">
				<logic:empty name="tenderProjectForm" property="tenderInfo.tpTenderdocCheckStatus">
					<a href='tenderProjectManage.do?method=checkTenderDocumentBegin'><img border="0" src="pages\style\default\mis\sendcheck.jpg"></a>
				</logic:empty>
				<logic:equal name="tenderProjectForm" property="tenderInfo.tpTenderdocCheckStatus" value="0">
					<a href='tenderProjectManage.do?method=checkTenderDocumentBegin'><img border="0" src="pages\style\default\mis\sendcheck.jpg"></a>
				</logic:equal>
				<logic:equal name="tenderProjectForm" property="tenderInfo.tpTenderdocCheckStatus" value="2">
					<a href='tenderProjectManage.do?method=checkTenderDocumentBegin'><img border="0" src="pages\style\default\mis\sendcheck.jpg"></a>
				</logic:equal>
			</td>
		</tr>	
		<tr><td align="left">
				<bean:message bundle="projectmanage" key="task.checkresult"/>:	
				<logic:empty name="tenderProjectForm" property="tenderInfo.tpTenderdocCheckStatus">
					<bean:message key="check_status_init"/>
				</logic:empty>	
				<logic:equal name="tenderProjectForm" property="tenderInfo.tpTenderdocCheckStatus" value="0">
					<bean:message key="check_status_init"/>
				</logic:equal>	
				<logic:equal name="tenderProjectForm" property="tenderInfo.tpTenderdocCheckStatus" value="1">
					<bean:message key="check_status_checking"/>
				</logic:equal>
				<logic:equal name="tenderProjectForm" property="tenderInfo.tpTenderdocCheckStatus" value="2">
					<bean:message key="check_status_reject"/>
				</logic:equal>
				<logic:equal name="tenderProjectForm" property="tenderInfo.tpTenderdocCheckStatus" value="3">
					<bean:message key="check_status_checked"/>
				</logic:equal>	
			</td>						
			<td align="center">							
			<logic:empty name="tenderProjectForm" property="tenderInfo.tpTenderdocCheckStatus">
				<logic:equal name="accountPerson" property="hasCreatePower" value="true">	
					<html:button styleClass="addbutton" property="" onclick="openDialog('tenderProjectManage.do?method=addTenderAttachment',500,180);">
						&nbsp;
					</html:button>	
					<html:submit styleClass="savebutton">
						&nbsp;
					</html:submit>
				</logic:equal>
			</logic:empty>
			<logic:equal name="tenderProjectForm" property="tenderInfo.tpTenderdocCheckStatus" value="0">
				<logic:equal name="accountPerson" property="hasCreatePower" value="true">	
					<html:button styleClass="addbutton" property="" onclick="openDialog('tenderProjectManage.do?method=addTenderAttachment',500,180);">
						&nbsp;
					</html:button>	
					<html:submit styleClass="savebutton">
						&nbsp;
					</html:submit>
				</logic:equal>	
			</logic:equal>
			<logic:equal name="tenderProjectForm" property="tenderInfo.tpTenderdocCheckStatus" value="2">
				<logic:equal name="accountPerson" property="hasCreatePower" value="true">	
					<html:button styleClass="addbutton" property="" onclick="openDialog('tenderProjectManage.do?method=addTenderAttachment',500,180);">
						&nbsp;
					</html:button>	
					<html:submit styleClass="savebutton">
						&nbsp;
					</html:submit>
				</logic:equal>	
			</logic:equal>					
			</td>				
		</tr>
	</table>					
	<table class="sort-table" cellStacing="1" celltadding="1" width="100%" border="0">					
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
				<logic:iterate id="ta" name="tenderProjectForm" property="tenderInfo.tenderAttachmentList" indexId="i">
				<logic:notEqual name="ta" property="taFlag" value="-1">
				  
					<% int a = i%2;request.setAttribute("a",a);%>
					<logic:equal name="a" value="0"><tr class="even"></logic:equal>
					<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
						<td align="center"><%=i+1 %></td>
						<td><bean:write name="ta" property="taCode"/></td>
						<td>
							<logic:notEmpty name="ta" property="category">
								<bean:write name="ta" property="category.acName"/>
							</logic:notEmpty>	
						</td>
						<td><bean:write name="ta" property="taName"/></td>
						<logic:empty name="ta" property="taId">
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td align="center"><a href='tenderProjectManage.do?method=deleteTenderAttachmentByIndex&index=<%=i %>'><img border="0" src="pages\images\icon\16x16\delete.gif"></a></td>
						</logic:empty>
						<logic:notEmpty name="ta" property="taId">
							<td align="center"><a href='tenderProjectManage.do?method=downloadTenderAttachment&id=<bean:write name="ta" property="taId"/>'><img border="0" src="pages\images\icon\16x16\make-index.gif"></a></td>
							<td align="center">
							<logic:empty name="tenderProjectForm" property="tenderInfo.tpTenderdocCheckStatus">
								<logic:equal name="accountPerson" property="hasCreatePower" value="true">
									<a href='javascript:openDialog("tenderProjectManage.do?method=editTenderAttachment&id=<bean:write name="ta" property="taId"/>",500,200);'><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
								</logic:equal>
							</logic:empty>
							<logic:equal name="tenderProjectForm" property="tenderInfo.tpTenderdocCheckStatus" value="0">
								<logic:equal name="accountPerson" property="hasCreatePower" value="true">
									<a href='javascript:openDialog("tenderProjectManage.do?method=editTenderAttachment&id=<bean:write name="ta" property="taId"/>",500,200);'><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
								</logic:equal>
							</logic:equal>
							<logic:equal name="tenderProjectForm" property="tenderInfo.tpTenderdocCheckStatus" value="2">
								<logic:equal name="accountPerson" property="hasCreatePower" value="true">
									<a href='javascript:openDialog("tenderProjectManage.do?method=editTenderAttachment&id=<bean:write name="ta" property="taId"/>",500,200);'><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
								</logic:equal>
							</logic:equal>
							</td>
							<td align="center">
							<logic:empty name="tenderProjectForm" property="tenderInfo.tpTenderdocCheckStatus">
								<logic:equal name="accountPerson" property="hasCreatePower" value="true">
									<a href='javascript:if(confirm("<bean:message key="delete_confirm" />")) {chgAction(document.all.id,"<bean:write name="ta" property="taId"/>");chgAction(document.all.method,"deleteTenderAttachment");tenderProjectForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
								</logic:equal>
							</logic:empty>
							<logic:equal name="tenderProjectForm" property="tenderInfo.tpTenderdocCheckStatus" value="0">
								<logic:equal name="accountPerson" property="hasCreatePower" value="true">
									<a href='javascript:if(confirm("<bean:message key="delete_confirm" />")) {chgAction(document.all.id,"<bean:write name="ta" property="taId"/>");chgAction(document.all.method,"deleteTenderAttachment");tenderProjectForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
								</logic:equal>
							</logic:equal>
							<logic:equal name="tenderProjectForm" property="tenderInfo.tpTenderdocCheckStatus" value="2">
								<logic:equal name="accountPerson" property="hasCreatePower" value="true">
									<a href='javascript:if(confirm("<bean:message key="delete_confirm" />")) {chgAction(document.all.id,"<bean:write name="ta" property="taId"/>");chgAction(document.all.method,"deleteTenderAttachment");tenderProjectForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
								</logic:equal>
							</logic:equal>
							</td>
						</logic:notEmpty>
					</tr>
				</logic:notEqual>
				</logic:iterate>
			</tbody>
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
				<% int b = ((org.king.common.checktask.CheckTask)task).getCtCheckStep()%2;request.setAttribute("b",b);%>
				<logic:equal name="b" value="0"><tr class="blue"></logic:equal>
				<logic:equal name="b" value="1"><tr class="odd"></logic:equal>
					<td align="center">										
						<%=i+1 %>
						<logic:equal name="task" property="ctCheckStep" value="1"><bean:message key="down_arrow"/></logic:equal>
						<logic:notEqual name="task" property="ctCheckStep" value="1">&nbsp;&nbsp;</logic:notEqual>
					</td>
					<td align="center">					
						<bean:write name="task" property="receiveDepartment.DName"/>					
					</td>
									
					<td align="center">
						<logic:notEmpty name="task" property="receivePost">
							<bean:write name="task" property="receivePost.PName"/>
						</logic:notEmpty>
						<logic:empty name="task" property="receivePost"><bean:message bundle="tendermanage" key="dept_manager"/></logic:empty>
					</td>
					<td align="center"><bean:write name="task" property="checkableUserList"/></td>
					<td align="center">
						<logic:notEmpty name="task" property="checkUser">
							<bean:write name="task" property="checkUser.person.personName"/>
						</logic:notEmpty>
					</td>
					<td align="center"><bean:write name="task" property="ctCheckTime"/></td>
					<td align="center">
						<logic:equal name="task" property="ctCheckResult" value="1"><bean:message key="check_status_checked"/></logic:equal>
						<logic:equal name="task" property="ctCheckResult" value="2"><bean:message key="check_status_reject"/></logic:equal>
					</td>	
					<td>
						<logic:empty name="task" property="ctRemark">--</logic:empty>
						<logic:notEmpty name="task" property="ctRemark"><bean:write name="task" property="ctRemark"/></logic:notEmpty>
					</td>	
					<!--td align="center">									
						<a href="javascript:openDialog('tenderProjectManage.do?method=displayCheckTaskDetail&id=<bean:write name="task" property="ctId"/>',700,400)"><img border="0" src="pages\images\icon\16x16\display.gif"></a>
					</td-->															
				</tr>			
			</logic:iterate>		 
		</tbody>
	</table>			
</html:form>