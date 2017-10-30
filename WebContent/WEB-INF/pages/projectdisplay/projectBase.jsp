<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<bean:define id="title">
	<bean:message bundle="projectmanage" key="project.edit" />
</bean:define>
<bean:define id="basetitle">
	<bean:message bundle="projectmanage" key="tabtitle.base" />
</bean:define>
<bean:define id="teammembertitle">
	<bean:message bundle="projectmanage" key="tabtitle.teammember" />
</bean:define>
<bean:define id="scheduleplantitle">
	<bean:message bundle="projectmanage" key="tabtitle.scheduleplan" />
</bean:define>
<bean:define id="scheduleupdatetitle">
	<bean:message bundle="projectmanage" key="tabtitle.scheduleupdate" />
</bean:define>
<bean:define id="budgettitle">
	<bean:message bundle="projectmanage" key="tabtitle.budget" />
</bean:define>
<bean:define id="logtitle">
	<bean:message bundle="projectmanage" key="tabtitle.log" />
</bean:define>
<bean:define id="documenttitle">
	<bean:message bundle="projectmanage" key="tabtitle.document" />
</bean:define>
<bean:define id="contracttitle">
	<bean:message bundle="projectmanage" key="tabtitle.contractcheck" />
</bean:define>
<bean:define id="accountManageTitle">
	<bean:message bundle="projectmanage" key="tabtitle.accountmanage" />
</bean:define>
<bean:define id="costsumTitle">
	<bean:message bundle="tendermanage" key="tender.costsum" />
</bean:define>


<gui:window title='<%=title%>' prototype="boWindow">
<jsp:include page="../projectmanage/projectInfo.jsp"></jsp:include>
<gui:tabbedPanel selectedTab="base" prototype="boTabbedPanel" width="16" >
	<gui:tab prototype="boTab" name="base" title="<%=basetitle%>" followUp="projectManage.do?method=displayProjectBase">	
		<html:form action="projectManage.do">
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr>
				<td width="10%">&nbsp;</td>
				<td width="15%"></td>				
				<td width="10%"></td>
				<td width="15%"></td>
				<td width="10%"></td>
				<td width="16%"></td>
				<td width="12%"></td>
				<td></td>
			</tr>
			<tr>
				<td align="right">
					<bean:message bundle="projectmanage" key="project.name" />£º
				</td>
				<td colspan="7">
					<bean:write name="projectSearchForm" property="projectInfo.tpName"/>
				</td>
				
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="right">
					<bean:message bundle="projectmanage" key="project.status" />£º
				</td>
				<td>								
					<bean:write name="projectSearchForm" property="projectInfo.status.psName"/>							
				</td>
				<td align="right">
					<bean:message bundle="projectmanage" key="project.dept" />£º
				</td>
				<td>								
					<bean:write name="projectSearchForm" property="projectInfo.department.DName"/>				
				</td>
				<td align="right">
					<bean:message bundle="projectmanage" key="project.manager" />£º
				</td>
				<td>
					<logic:notEmpty name="projectSearchForm" property="projectInfo.manager">
						<bean:write name="projectSearchForm" property="projectInfo.manager.person.personName"/>
					</logic:notEmpty>					
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="right">
					<bean:message bundle="projectmanage" key="project.customer" />£º
				</td>
				<td colspan="7">
					<bean:write name="projectSearchForm" property="projectInfo.customer.CName"/>				
				</td>					
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="right">
					<bean:message bundle="projectmanage" key="project.begindate" />£º 
				</td>
				<td >						
					<bean:write name="projectSearchForm" property="projectInfo.tpBeginDate"/>
				</td>				
				<td align="right">
					<bean:message bundle="projectmanage" key="project.enddate" />£º 
				</td>
				<td >						
					<bean:write name="projectSearchForm" property="projectInfo.tpEndDate"/>
				</td>

				<td align="right">
					<bean:message bundle="projectmanage" key="project.workperiod" />£º
				</td>
				<td>
					<bean:write name="projectSearchForm" property="projectInfo.tpWorkdayCount"/>					
				</td>
								
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="right">
					<bean:message bundle="projectmanage" key="project.description" />£º
				</td>
				<td colspan="7">
					<bean:write name="projectSearchForm" property="projectInfo.tpDescription"/>
				</td>			
					
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="right">
					<bean:message bundle="projectmanage" key="project.attachment" />£º
				</td>
				<td colspan="4">
					
					<logic:iterate id="am" name="projectSearchForm" property="projectInfo.attachmentList" indexId="i">
						<a href="projectManage.do?method=downloadAttachment&id=<bean:write name='am' property='paId'/>" title="<bean:write name="am" property="paLocalUrl"/>">
							<bean:write name="am" property="paName"/>£»
						</a>												
					</logic:iterate>
					
				</td>			
				
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
				<td align="right"><bean:message bundle="projectmanage" key="project.createuser" />£º</td>
				<td><bean:write name="projectSearchForm" property="projectInfo.createUser.person.personName"/></td>
				<td align="right"><bean:message bundle="projectmanage" key="project.createtime" />£º</td>
				<td><bean:write name="projectSearchForm" property="projectInfo.tpCreateTime"/></td>
			</tr>				
		</table>				
	</html:form>
</gui:tab>
<gui:tab prototype="boTab" name="accountManage" title="<%=accountManageTitle%>" followUp="projectManage.do?method=displayProjectAccount">	
</gui:tab>
<gui:tab prototype="boTab" name="document" title="<%=documenttitle%>" followUp="projectManage.do?method=displayProjectDocument">	
</gui:tab>
<gui:tab prototype="boTab" name="teammember" title="<%=teammembertitle%>" followUp="projectManage.do?method=displayProjectTeamMember">	
</gui:tab>
<gui:tab prototype="boTab" name="scheduleplan" title="<%=scheduleplantitle%>" followUp="projectManage.do?method=displayProjectSchedulePlan">	
</gui:tab>
<gui:tab prototype="boTab" name="scheduleupdate" title="<%=scheduleupdatetitle%>" followUp="projectManage.do?method=displayProjectSchedule">	
</gui:tab>
<gui:tab prototype="boTab" name="contractcheck" title="<%=contracttitle%>" followUp="projectManage.do?method=displayProjectContractManage">	
</gui:tab>
<gui:tab prototype="boTab" name="log" title="<%=logtitle%>" followUp="projectManage.do?method=displayProjectRecord">	
</gui:tab>
<gui:tab prototype="boTab" name="costsum" title="<%=costsumTitle%>" followUp="projectManage.do?method=displayTenderCostsum">
		
</gui:tab>

</gui:tabbedPanel>		
</gui:window>
</center>


