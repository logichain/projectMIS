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
<gui:tabbedPanel selectedTab="log" prototype="boTabbedPanel" width="16" >
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
</gui:tab>
<gui:tab prototype="boTab" name="contractcheck" title="<%=contracttitle%>" followUp="projectManage.do?method=displayProjectContractManage">	
</gui:tab>
<gui:tab prototype="boTab" name="log" title="<%=logtitle%>" followUp="projectManage.do?method=displayProjectRecord">	
	<html:form action="projectManage.do" onsubmit="return checkFormValidate()&&validateProjectSearchForm(this);">
				
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr><td>&nbsp;</td></tr>	
			
		</table>					
		<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">								
			<thead>
				<tr>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="serial" />
					</td>					
					<td width="10%" align="center">
						<bean:message bundle="projectmanage" key="scheduletask.stage" />
					</td>
					<td width="10%" align="center">
						<bean:message bundle="projectmanage" key="scheduletask.task" />
					</td>
					<td width="10%" align="center">
						<bean:message bundle="projectmanage" key="implement.begindate" />
					</td>
					<td width="10%" align="center">
						<bean:message bundle="projectmanage" key="implement.enddate" />
					</td>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="implement.finish" />
					</td>
					<td align="center">
						<bean:message bundle="projectmanage" key="implement.description" />
					</td>
				</tr>
			</thead>
			<tbody>
			<%int index =1; %>
			<logic:iterate name="projectSearchForm" property="projectInfo.scheduleStageList" id="ss" indexId="i">
			<logic:notEqual name="ss" property="pssFlag" value="-1">
			<logic:iterate name="ss" property="scheduleTaskList" id="st" indexId="j" >
			<logic:notEqual name="st" property="pstFlag" value="-1">
				<logic:iterate id="r" name="st" property="implementRecordList" indexId="m">
				<logic:notEqual name="r" property="tirFlag" value="-1">					
					<% int a = j%2;request.setAttribute("a",a);%>
					<logic:equal name="a" value="0"><tr class="even"></logic:equal>
					<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
						<td align="center">	<%=index++%></td>
						<td><bean:write name="ss" property="pssName"/></td>
						<td><bean:write name="st" property="pstName"/></td>
						<td><bean:write name="r" property="tirBeginDate"/></td>
						<td><bean:write name="r" property="tirEndDate"/></td>
						<td><bean:write name="r" property="tirImplementPercentStr"/></td>
						<td><bean:write name="r" property="tirDescription"/></td>						
						
					</tr>
				</logic:notEqual>
				</logic:iterate>
			</logic:notEqual>
			</logic:iterate>
			</logic:notEqual>
			</logic:iterate>
			</tbody>
		</table>			
	</html:form>
</gui:tab>
<gui:tab prototype="boTab" name="costsum" title="<%=costsumTitle%>" followUp="projectManage.do?method=displayTenderCostsum">
		
</gui:tab>	

</gui:tabbedPanel>		
</gui:window>
