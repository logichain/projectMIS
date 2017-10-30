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
<gui:tabbedPanel selectedTab="scheduleupdate" prototype="boTabbedPanel" width="16" >
<gui:tab prototype="boTab" name="base" title="<%=basetitle%>" followUp="projectManage.do?method=displayProjectBase">	
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
	<html:form action="projectManage.do">
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr><td>&nbsp;</td></tr>	
			
		</table>	
			
		<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">								
			<thead>
				<tr>
					<td width="4%" align="center">
						<bean:message bundle="projectmanage" key="serial" />
					</td>					
					<td width="18%" align="center">
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
					<td width="4%" align="center">
						<bean:message bundle="projectmanage" key="schedule.percent" />
					</td>
					
					<td width="8%" align="center">
						<bean:message bundle="projectmanage" key="schedule.updatedate" />
					</td>
					<td width=20%" align="center">
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
					<% int a = i%2;request.setAttribute("a",a);%>
					<tr class="odd">
						<td align="center">
							<%=i+1 %>							
						</td>
						<td><bean:write name="ss" property="pssName"/></td>									
						<td align="center"><bean:write name="ss" property="pssWorkPeriod"/></td>						
						<td align="center"><bean:write name="ss" property="pssBeginDate"/></td>
						<td align="center"><bean:write name="ss" property="pssEndDate"/></td>
						<td></td>
						<td></td>
						<td></td>
						<td><bean:write name="ss" property="pssDescription"/></td>
						<td align="center">
							<logic:notEmpty name="ss" property="responsiblePerson">
								<bean:write name="ss" property="responsiblePerson.person.personName"/>
							</logic:notEmpty>
						</td>						
																		
					</tr>
					<logic:iterate name="ss" property="scheduleTaskList" id="st" indexId="j" >
					<logic:notEqual name="st" property="pstFlag" value="-1">					
						<% int b = j%2;request.setAttribute("b",b);%>
						<tr class="even">
							<td align="right">	
								<bean:size id="rs" name="st" property="implementRecordList"/>
								<logic:greaterThan name="rs" value="0">
								<logic:equal name="st" property="recordExpendStatus" value="+">
									<a href='projectManage.do?method=collectScheduleTaskForFinish&id=<bean:write name="st" property="pstId"/>'><img border="0" src="pages\style\default\images\-.GIF"></a>
								</logic:equal>
								<logic:equal name="st" property="recordExpendStatus" value="-">
									<a href='projectManage.do?method=expendScheduleTaskForFinish&id=<bean:write name="st" property="pstId"/>'><img border="0" src="pages\style\default\images\+.gif"></a>
								</logic:equal>
								</logic:greaterThan>
								<%=j+1 %>)
							</td>							
							<td><bean:write name="st" property="pstName"/></td>									
							<td align="center"><bean:write name="st" property="pstWorkPeriod"/></td>						
							<td align="center"><bean:write name="st" property="pstBeginDate"/></td>
							<td align="center"><bean:write name="st" property="pstEndDate"/></td>
							<td>
								<logic:notEmpty name="st" property="preTask">
									<bean:write name="st" property="preTask.pstName"/>
								</logic:notEmpty>
							</td>
							<td><bean:write name="st" property="pstFinishPercentryStr"/></td>
							
							<td align="center">
								<logic:notEmpty name="st" property="pstFinishChangeTime">
									<bean:define id="fd" name="st" property="pstFinishChangeTime"></bean:define>
									<fmt:formatDate value="${fd}" type="date" dateStyle="default"/>	
								</logic:notEmpty>
							</td>
							<td><bean:write name="st" property="pstDescription"/></td>
							<td align="center">
								<logic:notEmpty name="st" property="responsiblePerson">
									<bean:write name="st" property="responsiblePerson.person.personName"/>
								</logic:notEmpty>
							</td>								
																											
						</tr>
						
						<logic:equal name="st" property="recordExpendStatus" value="+">
						<tr>
							<td></td>
							<td colspan="9">
								<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">								
									<thead>
										<tr>
											<td width="5%" align="center">
												<bean:message bundle="projectmanage" key="serial" />
											</td>					
											<td width="10%" align="center">
												<bean:message bundle="projectmanage" key="implement.begindate" />
											</td>
											<td width="10%" align="center">
												<bean:message bundle="projectmanage" key="implement.enddate" />
											</td>
											<td width="6%" align="center">
												<bean:message bundle="projectmanage" key="implement.finish" />
											</td>
											<td  align="center">
												<bean:message bundle="projectmanage" key="implement.description" />
											</td>
											
										</tr>
									</thead>
									<tbody>
										<logic:iterate id="r" name="st" property="implementRecordList" indexId="m">
										<logic:notEqual name="r" property="tirFlag" value="-1">
											<tr>
												<td align="center">	<%=m+1 %></td>
												<td><bean:write name="r" property="tirBeginDate"/></td>
												<td><bean:write name="r" property="tirEndDate"/></td>
												<td><bean:write name="r" property="tirImplementPercentStr"/></td>
												<td><bean:write name="r" property="tirDescription"/></td>
												
											</tr>
										</logic:notEqual>
										</logic:iterate>
									</tbody>
								</table>

							</td>
						</tr>
						</logic:equal>	
					</logic:notEqual>		
					</logic:iterate>
				</logic:notEqual>
				</logic:iterate>			
			</tbody>
		</table>
					
	
	</html:form>
</gui:tab>
<gui:tab prototype="boTab" name="contractcheck" title="<%=contracttitle%>" followUp="projectManage.do?method=displayProjectContractManage">	
</gui:tab>
<gui:tab prototype="boTab" name="log" title="<%=logtitle%>" followUp="projectManage.do?method=displayProjectRecord">	
</gui:tab>
<gui:tab prototype="boTab" name="costsum" title="<%=costsumTitle%>" followUp="projectManage.do?method=displayTenderCostsum">
		
</gui:tab>	

</gui:tabbedPanel>		
</gui:window>
