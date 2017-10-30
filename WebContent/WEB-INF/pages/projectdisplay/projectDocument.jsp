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
<gui:tabbedPanel selectedTab="document" prototype="boTabbedPanel" width="16" >
<gui:tab prototype="boTab" name="base" title="<%=basetitle%>" followUp="projectManage.do?method=displayProjectBase">	
</gui:tab>
<gui:tab prototype="boTab" name="accountManage" title="<%=accountManageTitle%>" followUp="projectManage.do?method=displayProjectAccount">	
</gui:tab>
<gui:tab prototype="boTab" name="document" title="<%=documenttitle%>" followUp="projectManage.do?method=displayProjectDocument">	
	<html:form action="projectManage.do">
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
					<logic:iterate id="pa" name="projectSearchForm" property="projectInfo.attachmentList" indexId="i">
					<logic:notEqual name="pa" property="paFlag" value="-1">
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
							
							<td align="center"><a href='projectManage.do?method=downloadAttachment&id=<bean:write name="pa" property="paId"/>'><img border="0" src="pages\images\icon\16x16\make-index.gif"></a></td>
								
						</tr>
					</logic:notEqual>
					</logic:iterate>
				</tbody>
			</table>				
	</html:form>
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


