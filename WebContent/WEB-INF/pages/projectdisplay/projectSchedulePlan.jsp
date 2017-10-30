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
<gui:tabbedPanel selectedTab="scheduleplan" prototype="boTabbedPanel" width="16" >
<gui:tab prototype="boTab" name="base" title="<%=basetitle%>" followUp="projectManage.do?method=displayProjectBase">	
</gui:tab>
<gui:tab prototype="boTab" name="accountManage" title="<%=accountManageTitle%>" followUp="projectManage.do?method=displayProjectAccount">	
</gui:tab>
<gui:tab prototype="boTab" name="document" title="<%=documenttitle%>" followUp="projectManage.do?method=displayProjectDocument">	
</gui:tab>
<gui:tab prototype="boTab" name="teammember" title="<%=teammembertitle%>" followUp="projectManage.do?method=displayProjectTeamMember">	
</gui:tab>
<gui:tab prototype="boTab" name="scheduleplan" title="<%=scheduleplantitle%>" followUp="projectManage.do?method=displayProjectSchedulePlan">	
	<html:form action="projectManage.do" >
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr><td>&nbsp;</td></tr>	
			<tr><td width="80%"><bean:message bundle="projectmanage" key="tabtitle.txtplantitle"/></td>						
					
			</tr>
		</table>	
		<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">								
			<thead>
				<tr>
					<td width="4%" align="center">
						<bean:message bundle="projectmanage" key="serial" />
					</td>					
					<td width="20%" align="center">
						<bean:message bundle="projectmanage" key="scheduleplan.stagetask" />
					</td>
					<td width="4%" align="center">
						<bean:message bundle="projectmanage" key="project.workperiod" />
					</td>
					<td width="8%" align="center">
						<bean:message bundle="projectmanage" key="project.begindate" />
					</td>
					<td width="8%" align="center">
						<bean:message bundle="projectmanage" key="project.enddate" />
					</td>
					<td width="15%" align="center">
						<bean:message bundle="projectmanage" key="scheduleplan.pretask" />
					</td>
					<td width="20%" align="center">
						<bean:message bundle="projectmanage" key="scheduleplan.description" />
					</td>

					<td align="center">
						<bean:message bundle="projectmanage" key="scheduleplan.responsibility" />
					</td>
					
				</tr>
			</thead>
			<tbody>					
				<logic:iterate name="projectSearchForm" property="projectInfo.scheduleStageList" id="ss" indexId="i">
					<logic:notEqual name="ss" property="pssFlag" value="-1">

					<tr class="odd">
						<td align="center">		
							<bean:size id="ts" name="ss" property="scheduleTaskList"/>
							<logic:greaterThan name="ts" value="0">					
								<logic:equal name="ss" property="taskExpendStatus" value="+">
									<a href='projectManage.do?method=collectScheduleStageForFinish&id=<bean:write name="ss" property="pssId"/>'><img border="0" src="pages\style\default\images\-.GIF"></a>
								</logic:equal>
								<logic:equal name="ss" property="taskExpendStatus" value="-">
									<a href='projectManage.do?method=expendScheduleStageForFinish&id=<bean:write name="ss" property="pssId"/>'><img border="0" src="pages\style\default\images\+.gif"></a>
								</logic:equal>
							</logic:greaterThan>
							<%=i+1 %>
						</td>
						<td><bean:write name="ss" property="pssName"/></td>									
						<td align="center"><bean:write name="ss" property="pssWorkPeriod"/></td>						
						<td align="center"><bean:write name="ss" property="pssBeginDate"/></td>
						<td align="center"><bean:write name="ss" property="pssEndDate"/></td>
						<td></td>
						<td><bean:write name="ss" property="pssDescription"/></td>
						<td align="center">
							<logic:notEmpty name="ss" property="responsiblePerson">
								<bean:write name="ss" property="responsiblePerson.person.personName"/>
							</logic:notEmpty>
						</td>
																
					</tr>
					<logic:equal name="ss" property="taskExpendStatus" value="+">
						<logic:iterate name="ss" property="scheduleTaskList" id="st" indexId="j" >	
						<logic:notEqual name="st" property="pstFlag" value="-1">				
					
							<tr class="even">
								<td align="right">	<%=j+1 %>)</td>							
								<td><bean:write name="st" property="pstName"/></td>									
								<td align="center"><bean:write name="st" property="pstWorkPeriod"/></td>						
								<td align="center"><bean:write name="st" property="pstBeginDate"/></td>
								<td align="center"><bean:write name="st" property="pstEndDate"/></td>
								<td>
									<logic:notEmpty name="st" property="preTask">
										<bean:write name="st" property="preTask.pstName"/>
									</logic:notEmpty>
								</td>
								<td><bean:write name="st" property="pstDescription"/></td>
								<td align="center">
									<logic:notEmpty name="st" property="responsiblePerson">
										<bean:write name="st" property="responsiblePerson.person.personName"/>
									</logic:notEmpty>
								</td>	
																					
							</tr>
						</logic:notEqual>
						</logic:iterate>
					</logic:equal>
					</logic:notEqual>		
				</logic:iterate>			
			</tbody>
		</table>	
		
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr><td>&nbsp;</td></tr>	
			<tr><td width="80%"><bean:message bundle="projectmanage" key="tabtitle.chartplantitle"/></td></tr>
		</table>		

		<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">								
			<thead>
				<tr>
					<td width="4%" align="center">
						<bean:message bundle="projectmanage" key="serial" />
					</td>					
					<td width="20%" align="center">
						<bean:message bundle="projectmanage" key="scheduleplan.stagetask" />
					</td>
					<td width="4%" align="center">
						<bean:message bundle="projectmanage" key="project.workperiod" />						
					</td>
					
					<logic:iterate name="tableHeader" id="h" indexId="i">
						<logic:equal name="i" value="0">
							<td width="6%" align="center">
								<logic:notEmpty name="projectSearchForm" property="projectInfo.scheduleStageList">
									<a href="projectManage.do?method=displayScheduleGoBackForFinish"><img border="0" src="pages\style\default\images\firstPage.gif"></a>
									
								</logic:notEmpty>
								<bean:write name="h"/>
							</td>
						</logic:equal>
						<logic:equal name="i" value="11">
							<td width="6%" align="center">
								<bean:write name="h"/>
								<logic:notEmpty name="projectSearchForm" property="projectInfo.scheduleStageList">
									<a href="projectManage.do?method=displayScheduleGoOnForFinish"><img border="0" src="pages\style\default\images\lastPage.gif"></a>
									
								</logic:notEmpty>
							</td>
						</logic:equal>
						<logic:notEqual name="i" value="0">
							<logic:notEqual name="i" value="11">						
								<td width="6%" align="center"><bean:write name="h"/></td>
							</logic:notEqual>							
						</logic:notEqual>						
					</logic:iterate>				
				</tr>
			</thead>
			<tbody>					
				<logic:iterate name="projectSearchForm" property="projectInfo.scheduleStageList" id="ss" indexId="i">
				<logic:notEqual name="ss" property="pssFlag" value="-1">
				
					<tr class="odd">
						<td align="center">		
							<bean:size id="ts" name="ss" property="scheduleTaskList"/>
							<logic:greaterThan name="ts" value="0">							
							<logic:equal name="ss" property="taskExpendStatus" value="+">
								<a href='projectManage.do?method=collectScheduleStageForFinish&id=<bean:write name="ss" property="pssId"/>'><img border="0" src="pages\style\default\images\-.GIF"></a>
							</logic:equal>
							<logic:equal name="ss" property="taskExpendStatus" value="-">
								<a href='projectManage.do?method=expendScheduleStageForFinish&id=<bean:write name="ss" property="pssId"/>'><img border="0" src="pages\style\default\images\+.gif"></a>
							</logic:equal>
							</logic:greaterThan>
							<%=i+1 %>
						</td>
						<td><bean:write name="ss" property="pssName"/></td>									
						<td align="center"><bean:write name="ss" property="pssWorkPeriod"/></td>	
										
						<td colspan="12">
							<div style="width=100%;overflow:hidden;height:6px;">
								<span style="width:<bean:write name="ss" property="beginDatePercentStr"/>">													
								</span>
								<span title="<bean:write name="ss" property="pssBeginDate"/>~<bean:write name="ss" property="pssEndDate"/>" style="width:<bean:write name="ss" property="endDatePercentStr"/>;background-color:#0000FF">													
								</span>
							</div>	
							<div style="width=100%;overflow:hidden;height:1px;"></div>
							<div style="width=100%;overflow:hidden;height:6px;">
								
							</div>						
						</td>											
					</tr>
					<logic:equal name="ss" property="taskExpendStatus" value="+">
						<logic:iterate name="ss" property="scheduleTaskList" id="st" indexId="j" >	
						<logic:notEqual name="st" property="pstFlag" value="-1">				
					
							<tr class="even">
								<td align="right">	<%=j+1 %>)</td>
								<td><bean:write name="st" property="pstName"/></td>									
								<td align="center"><bean:write name="st" property="pstWorkPeriod"/></td>
								
								<td colspan="12">
									<div style="width=100%;overflow:hidden;height:6px;">
										<span style="width:<bean:write name="st" property="beginDatePercentStr"/>">													
										</span>
										<span title="<bean:write name="st" property="pstBeginDate"/>~<bean:write name="st" property="pstEndDate"/>" style="width:<bean:write name="st" property="endDatePercentStr"/>;background-color:#0000FF">													
										</span>
									</div>
									<div style="width=100%;overflow:hidden;height:1px;"></div>
									<div style="width=100%;overflow:hidden;height:6px;">
										<logic:iterate id="r" name="st" property="implementRecordList">
										<logic:notEqual name="r" property="tirFlag" value="-1">
											<span style="width:<bean:write name="r" property="beginDatePercentStr"/>">													
											</span>
											<span title="<bean:write name="r" property="tirBeginDate"/>-<bean:write name="r" property="tirEndDate"/>:<bean:write name="r" property="tirDescription"/>" style="width:<bean:write name="r" property="endDatePercentStr"/>;background-color:#FF0000">													
											</span>
										</logic:notEqual>
										</logic:iterate>
									</div>
								</td>													
							</tr>
						</logic:notEqual>
						</logic:iterate>	
					</logic:equal>	
				</logic:notEqual>
				</logic:iterate>			
			</tbody>
		</table>		
				
	</html:form>
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

