<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">					
	<thead>
		<tr>					
			<td width="30%" align="center">
				<bean:message bundle="projectmanage" key="project.name" />
			</td>			
			<td width="8%" align="center">
				<bean:message bundle="projectmanage" key="project.begindate" />
			</td>
			<td width="8%" align="center">
				<bean:message bundle="projectmanage" key="project.enddate" />
			</td>
			<td width="8%" align="center">
				<bean:message bundle="projectmanage" key="project.workperiod" />
			</td>
			<td width="8%" align="center">
				<bean:message bundle="projectmanage" key="project.status" />
			</td>
			<td width="8%" align="center">
				<bean:message bundle="projectmanage" key="project.manager" />
			</td>			
		</tr>
	</thead>
	<tbody>
		<tr>
							
			<td><bean:write name="projectSearchForm" property="projectInfo.tpName"/></td>									
			
			<td align="center"><bean:write name="projectSearchForm" property="projectInfo.tpBeginDate"/></td>
			<td align="center"><bean:write name="projectSearchForm" property="projectInfo.tpEndDate"/></td>
			<td align="center"><bean:write name="projectSearchForm" property="projectInfo.tpWorkdayCount"/></td>
			<td align="center"><bean:write name="projectSearchForm" property="projectInfo.status.psName"/></td>
			<td align="center">
				<logic:notEmpty name="projectSearchForm" property="projectInfo.manager">
					<bean:write name="projectSearchForm" property="projectInfo.manager.person.personName"/>
				</logic:notEmpty>
			</td>				
		</tr>
	</tbody>	
</table>

<table width="100%" border="0" class="win">
<tr>
	<td width="8%"><bean:message bundle="projectmanage" key="project.timeschedule" />£º</td>
	<td width="50%">
		<div style="width:<bean:write name="projectSearchForm" property="projectInfo.schedulePlanPercentStr"/>;background-color:#00EEEE;text-align:center">
			<logic:greaterThan name="projectSearchForm" property="projectInfo.schedulePlanPercent" value="10">
				<bean:write name="projectSearchForm" property="projectInfo.schedulePlanPercentStr"/>
			</logic:greaterThan>
		</div>
	</td>
	<td width="10%"><bean:message bundle="projectmanage" key="project.planworkdaycount" />£º<bean:write name="projectSearchForm" property="projectInfo.tpWorkdayCount"/></td>
	<td width="10%"><bean:message bundle="projectmanage" key="project.doworkdaycount" />£º<bean:write name="projectSearchForm" property="projectInfo.doDayCount"/></td>
	<td width="10%"><bean:message bundle="projectmanage" key="project.remainworkdaycount" />£º<bean:write name="projectSearchForm" property="projectInfo.remainDayCount"/></td>
	<td><bean:message bundle="projectmanage" key="porject.beyondworkdaycount" />£º<bean:write name="projectSearchForm" property="projectInfo.beyondDayCount"/></td>
</tr>
<tr>
	<td><bean:message bundle="projectmanage" key="project.doschedule" />£º</td>
	<td>
		<div style="width:<bean:write name="projectSearchForm" property="projectInfo.schedulePercentStr"/>;background-color:#EE00EE;text-align:center">
			<logic:greaterThan name="projectSearchForm" property="projectInfo.schedulePercent" value="10">
				<bean:write name="projectSearchForm" property="projectInfo.schedulePercentStr"/>
			</logic:greaterThan>
		</div>
	</td>
	<td><bean:message bundle="projectmanage" key="project.dopercent" />£º<bean:write name="projectSearchForm" property="projectInfo.schedulePercentStr"/></td>
	<td><bean:message bundle="projectmanage" key="project.remainpercent" />£º<bean:write name="projectSearchForm" property="projectInfo.remainPercentStr"/></td>	
</tr>
</table>
