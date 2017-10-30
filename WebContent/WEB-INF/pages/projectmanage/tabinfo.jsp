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
<bean:define id="logtitle">
	<bean:message bundle="projectmanage" key="tabtitle.log" />
</bean:define>
<bean:define id="documenttitle">
	<bean:message bundle="projectmanage" key="tabtitle.document" />
</bean:define>
<bean:define id="contracttitle">
	<bean:message bundle="projectmanage" key="tabtitle.contractcheck" />
</bean:define>
<bean:define id="contractlisttitle">
	<bean:message bundle="projectmanage" key="tabtitle.contractlist" />
</bean:define>
<bean:define id="accountManageTitle">
	<bean:message bundle="projectmanage" key="tabtitle.accountmanage" />
</bean:define>
<bean:define id="costsumTitle">
	<bean:message bundle="tendermanage" key="tender.costsum" />
</bean:define>


<gui:window title='<%=title%>' prototype="boWindow">
<jsp:include page="projectInfo.jsp"></jsp:include>

<gui:tabbedPanel selectedTab='<%=request.getParameter("selectTab")%>' prototype="boTabbedPanel" width="25" >
	<gui:tab prototype="boTab" name="base" title="<%=basetitle%>" followUp="projectManage.do?method=editProjectBase">	
		<%if("base".equals(request.getParameter("selectTab"))) {%>
		<c:import url="/WEB-INF/pages/projectmanage/projectbasetab.jsp">
		</c:import>
		<%} %>
	</gui:tab>
	<gui:tab prototype="boTab" name="accountManage" title="<%=accountManageTitle%>" followUp="projectManage.do?method=editProjectAccount">	
		<%if("accountManage".equals(request.getParameter("selectTab"))) {%>
		<c:import url="/WEB-INF/pages/projectmanage/accountManageTab.jsp">
		</c:import>
		<%} %>
	</gui:tab>
	<gui:tab prototype="boTab" name="document" title="<%=documenttitle%>" followUp="projectManage.do?method=editProjectDocument">	
		<%if("document".equals(request.getParameter("selectTab"))) {%>
		<c:import url="/WEB-INF/pages/projectmanage/projectdocumenttab.jsp">
		</c:import>
		<%} %>
	</gui:tab>
	<gui:tab prototype="boTab" name="teammember" title="<%=teammembertitle%>" followUp="projectManage.do?method=editProjectTeamMember">	
		<%if("teammember".equals(request.getParameter("selectTab"))) {%>
		<c:import url="/WEB-INF/pages/projectmanage/teammembertab.jsp">
		</c:import>
		<%} %>
	</gui:tab>
	
	<gui:tab prototype="boTab" name="scheduleplan" title="<%=scheduleplantitle%>" followUp="projectManage.do?method=editProjectSchedulePlan">	
		<%if("scheduleplan".equals(request.getParameter("selectTab"))) {%>
		<c:import url="/WEB-INF/pages/projectmanage/scheduleplantab.jsp">
		</c:import>
		<%} %>
	</gui:tab>
	<gui:tab prototype="boTab" name="scheduleupdate" title="<%=scheduleupdatetitle%>" followUp="projectManage.do?method=editProjectSchedule">	
		<%if("scheduleupdate".equals(request.getParameter("selectTab"))) {%>
		<c:import url="/WEB-INF/pages/projectmanage/scheduleedittab.jsp">
		</c:import>
		<%} %>
	</gui:tab>			
	<gui:tab prototype="boTab" name="contractcheck" title="<%=contracttitle%>" followUp="projectManage.do?method=editProjectContractManage">	
		<%if("contractcheck".equals(request.getParameter("selectTab"))) {%>
		<c:import url="/WEB-INF/pages/projectmanage/projectContracttab.jsp">
		</c:import>
		<%} %>	
	</gui:tab>
	
	<gui:tab prototype="boTab" name="log" title="<%=logtitle%>" followUp="projectManage.do?method=editProjectRecord">	
		<%if("log".equals(request.getParameter("selectTab"))) {%>
		<c:import url="/WEB-INF/pages/projectmanage/recordedittab.jsp">
		</c:import>
		<%} %>
	</gui:tab>
	
	<gui:tab prototype="boTab" name="costsum" title="<%=costsumTitle%>" followUp="projectManage.do?method=editApplyTenderBudget">
		<%if("costsum".equals(request.getParameter("selectTab"))) {%>
		<c:import url="/WEB-INF/pages/budgetmanage/applybudgettab.jsp">
		</c:import>
		<%} %>
	</gui:tab>		

</gui:tabbedPanel>		


</gui:window>
