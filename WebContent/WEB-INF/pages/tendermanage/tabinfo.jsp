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


<gui:tabbedPanel selectedTab='<%=request.getParameter("selectTab")%>' prototype="boTabbedPanel" width="16">
	<gui:tab prototype="boTab" name="baseInfo" title="<%=baseInfoTitle%>" followUp="tenderProjectManage.do?method=editTenderBase">
		<%if("baseInfo".equals(request.getParameter("selectTab"))) {%>
		<c:import url="/WEB-INF/pages/tendermanage/baseinfotab.jsp">
		</c:import>
		<%} %>
	</gui:tab>
	<gui:tab prototype="boTab" name="tenderFile" title="<%=tenderFileTitle%>" followUp="tenderProjectManage.do?method=searchAttachmentList">
		<%if("tenderFile".equals(request.getParameter("selectTab"))) {%>
		<c:import url="/WEB-INF/pages/tendermanage/attachmenttab.jsp">
		</c:import>
		<%} %>
	</gui:tab>
	<gui:tab prototype="boTab" name="teamMember" title="<%=teammembertitle%>" followUp="tenderProjectManage.do?method=editProjectTeamMember">
		<%if("teamMember".equals(request.getParameter("selectTab"))) {%>
		<c:import url="/WEB-INF/pages/tendermanage/teammembertab.jsp">
		</c:import>
		<%} %>
	</gui:tab>
	<gui:tab prototype="boTab" name="deviceAskPrice" title="<%=deviceAskPriceTitle%>" followUp="tenderProjectManage.do?method=editDeviceQuotedPrice">
		<%if("deviceAskPrice".equals(request.getParameter("selectTab"))) {%>
		<c:import url="/WEB-INF/pages/budgetmanage/quotedpricetab.jsp">
		</c:import>
		<%} %>	
	</gui:tab>
	<gui:tab prototype="boTab" name="confirmsupplier" title="<%=confirmSupplierTitle%>" followUp="tenderProjectManage.do?method=editConfirmSupplier">
		<%if("confirmsupplier".equals(request.getParameter("selectTab"))) {%>
		<c:import url="/WEB-INF/pages/budgetmanage/confirmsuppliertab.jsp">
		</c:import>
		<%} %>		
	</gui:tab>
	<gui:tab prototype="boTab" name="deviceList" title="<%=deviceListTitle%>" followUp="tenderProjectManage.do?method=editDeviceList">	
		<%if("deviceList".equals(request.getParameter("selectTab"))) {%>
		<c:import url="/WEB-INF/pages/budgetmanage/devicetab.jsp">
		</c:import>
		<%} %>
	</gui:tab>
	<gui:tab prototype="boTab" name="tenderBudget" title="<%=tenderBudgetTitle%>" followUp="tenderProjectManage.do?method=editTenderBugetItem">
		<%if("tenderBudget".equals(request.getParameter("selectTab"))) {%>
		<c:import url="/WEB-INF/pages/budgetmanage/budgetitemtab.jsp">
		</c:import>
		<%} %>
	</gui:tab>
	<gui:tab prototype="boTab" name="competitor" title="<%=competitorTitle%>" followUp="tenderProjectManage.do?method=editCompetitor">
		<%if("competitor".equals(request.getParameter("selectTab"))) {%>
		<c:import url="/WEB-INF/pages/tendermanage/competitortab.jsp">
		</c:import>
		<%} %>
	</gui:tab>
	
	<gui:tab prototype="boTab" name="contractcheck" title="<%=tenderContractTitle%>" followUp="tenderProjectManage.do?method=editProjectContractCheck">
		<%if("contractcheck".equals(request.getParameter("selectTab"))) {%>
		<c:import url="/WEB-INF/pages/tendermanage/contractchecktab.jsp">
		</c:import>
		<%} %>
	</gui:tab>
	
	<gui:tab prototype="boTab" name="tenderDocument" title="<%=tenderDocumentTitle%>" followUp="tenderProjectManage.do?method=editTenderDocument">
		<%if("tenderDocument".equals(request.getParameter("selectTab"))) {%>
		<c:import url="/WEB-INF/pages/projectmanage/tenderdocumenttab.jsp">
		</c:import>
		<%} %>
	</gui:tab>
	
</gui:tabbedPanel>


</gui:window>

