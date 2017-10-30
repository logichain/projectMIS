<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<bean:define id="title">
	<bean:message bundle="tendermanage" key="tender.title" />
</bean:define>

<bean:define id="baseInfoTitle">
	<bean:message bundle="tendermanage" key="tender.baseInfo" />
</bean:define>
<bean:define id="tenderFileTitle">
	<bean:message bundle="tendermanage" key="tender.tenderFile" />
</bean:define>

<bean:define id="competitorTitle">
	<bean:message bundle="tendermanage" key="tender.competitor" />
</bean:define>

<bean:define id="tenderBudgetTitle">
	<bean:message bundle="budgetmanage" key="budget.tenderBudget" />
</bean:define>
<bean:define id="deviceListTitle">
	<bean:message bundle="budgetmanage" key="budget.deviceList" />
</bean:define>
<bean:define id="deviceAskPriceTitle">
	<bean:message bundle="budgetmanage" key="budget.deviceAskPrice" />
</bean:define>
<bean:define id="confirmSupplierTitle">
	<bean:message bundle="budgetmanage" key="budget.confirmsupplier" />
</bean:define>
<bean:define id="teammembertitle">
	<bean:message bundle="projectmanage" key="tabtitle.teammember" />
</bean:define>

<bean:define id="tenderContractTitle">
	<bean:message bundle="tendermanage" key="tender.tenderContract" />
</bean:define>
<bean:define id="tenderDocumentTitle">
	<bean:message bundle="projectmanage" key="tabtitle.tenderDocumentTitle" />
</bean:define>

<gui:window title='<%=title%>' prototype="boWindow">

<jsp:include page="/WEB-INF/pages/tendermanage/tenderProjectInfo.jsp"></jsp:include>


<gui:tabbedPanel selectedTab='tenderDocument' prototype="boTabbedPanel" width="16">
	<gui:tab prototype="boTab" name="baseInfo" title="<%=baseInfoTitle%>" followUp="tenderProjectManage.do?method=displayTenderBase">
		
	</gui:tab>
	<gui:tab prototype="boTab" name="tenderFile" title="<%=tenderFileTitle%>" followUp="tenderProjectManage.do?method=displayTenderFile">
		
	</gui:tab>

	<gui:tab prototype="boTab" name="teamMember" title="<%=teammembertitle%>" followUp="tenderProjectManage.do?method=displayTenderTeamMember">
		
	</gui:tab>
	<gui:tab prototype="boTab" name="deviceAskPrice" title="<%=deviceAskPriceTitle%>" followUp="tenderProjectManage.do?method=displayDeviceQuotedPrice">
		
	</gui:tab>
	<gui:tab prototype="boTab" name="confirmsupplier" title="<%=confirmSupplierTitle%>" followUp="tenderProjectManage.do?method=displayConfirmSupplier">
			
	</gui:tab>
	<gui:tab prototype="boTab" name="deviceList" title="<%=deviceListTitle%>" followUp="tenderProjectManage.do?method=displayDeviceList">	
		
	</gui:tab>
	<gui:tab prototype="boTab" name="tenderBudget" title="<%=tenderBudgetTitle%>" followUp="tenderProjectManage.do?method=displayTenderBugetItem">
		
	</gui:tab>
	<gui:tab prototype="boTab" name="competitor" title="<%=competitorTitle%>" followUp="tenderProjectManage.do?method=displayCompetitor">
		
	</gui:tab>
	
	<gui:tab prototype="boTab" name="contractcheck" title="<%=tenderContractTitle%>" followUp="tenderProjectManage.do?method=displayTenderContractcheck">
		
	</gui:tab>

<gui:tab prototype="boTab" name="tenderDocument" title="<%=tenderDocumentTitle%>" followUp="tenderProjectManage.do?method=displayTenderDocument">	
	<html:form action="tenderProjectManage.do">
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr><td>	
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
						<td width="60%" align="center">
							<bean:message bundle="attachment" key="filename" />
						</td>
						<td align="center">
							<bean:message bundle="attachment" key="download" />
						</td>
						
					</tr>
				</thead>
				<tbody>
					<logic:iterate id="pa" name="tenderProjectForm" property="tenderInfo.tenderAttachmentList" indexId="i">
					<logic:notEqual name="pa" property="taFlag" value="-1">
						<% int a = i%2;request.setAttribute("a",a);%>
						<logic:equal name="a" value="0"><tr class="even"></logic:equal>
						<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
							<td align="center"><%=i+1 %></td>
							<td><bean:write name="pa" property="taCode"/></td>
							<td>
								<logic:notEmpty name="pa" property="category">
									<bean:write name="pa" property="category.acName"/>
								</logic:notEmpty>	
							</td>
							<td><bean:write name="pa" property="taName"/></td>
							
							<td align="center"><a href='tenderProjectManage.do?method=downloadTenderAttachment&id=<bean:write name="pa" property="taId"/>'><img border="0" src="pages\images\icon\16x16\make-index.gif"></a></td>
								
						</tr>
					</logic:notEqual>
					</logic:iterate>
				</tbody>
			</table>				
	</html:form>
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
						<logic:equal name="task" property="ctCheckResult" value="2"><bean:message key="check_status_reject"/></logic:equal>
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
</gui:tab>
</gui:tabbedPanel>		
</gui:window>


