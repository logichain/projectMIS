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


<gui:tabbedPanel selectedTab='baseInfo' prototype="boTabbedPanel" width="16">
	<gui:tab prototype="boTab" name="baseInfo" title="<%=baseInfoTitle%>" followUp="tenderProjectManage.do?method=displayTenderBase">
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr>
				<td width="10%">&nbsp;</td>
				<td width="15%"></td>				
				<td width="10%"></td>
				<td width="16%"></td>
				<td width="10%"></td>
				<td width="16%"></td>
				<td width="12%"></td>
				<td></td>
			</tr>
			<tr>
				<td align="right">
					<bean:message bundle="tendermanage" key="tender.projectName" />£º
				</td>
				<td colspan="3">
					<bean:write name="tenderProjectForm" property="tenderInfo.tpName"/>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="right">
					<bean:message bundle="projectmanage" key="project.status" />£º
				</td>
				<td>					
					<bean:write name="tenderProjectForm" property="tenderInfo.status.psName"/>													
				</td>
				<td align="right">
					<bean:message bundle="projectmanage" key="project.dept" />£º
				</td>
				<td>					
					<bean:write name="tenderProjectForm" property="tenderInfo.department.DName"/>				
				</td>
				<td align="right">
					<bean:message bundle="tendermanage" key="tender.manager" />£º
				</td>
				<td>					
					<logic:notEmpty name="tenderProjectForm" property="tenderInfo.manager">
						<bean:write name="tenderProjectForm" property="tenderInfo.manager.person.personName"/>	
					</logic:notEmpty>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>			
			<tr>
				<td align="right">
					<bean:message bundle="tendermanage" key="tender.clientName" />£º
				</td>
				<td colspan="3">			
					<bean:write name="tenderProjectForm" property="tenderInfo.customer.CName"/>	
				</td>			
				<td align="right">
					<bean:message bundle="approvalrecord" key="market_manager" />£º
				</td>
				<td>					
					<logic:notEmpty name="tenderProjectForm" property="tenderInfo.marketManager">
						<bean:write name="tenderProjectForm" property="tenderInfo.marketManager.person.personName"/>	
					</logic:notEmpty>
				</td>	
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="right">
					<bean:message bundle="tendermanage" key="tender.projectAddress" />£º
				</td>
				<td colspan="3">
					<bean:write name="tenderProjectForm" property="tenderInfo.tpAddress"/>	
				</td>
				
				<td align="right">
					<bean:message bundle="projectmanage" key="project.contractamount" />£º
				</td>
				<td>
					<bean:write name="tenderProjectForm" property="tenderInfo.tpContractAmount"/>					
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>	
			<tr>			
				<td align="right">
					<bean:message bundle="projectmanage" key="project.begindate" />£º 
				</td>
				<td >		
					<bean:write name="tenderProjectForm" property="tenderInfo.tpBeginDate"/>							
				</td>				
				<td align="right">
					<bean:message bundle="projectmanage" key="project.enddate" />£º 
				</td>
				<td >						
					<bean:write name="tenderProjectForm" property="tenderInfo.tpEndDate"/>
				</td>
	
				<td align="right">
					<bean:message bundle="projectmanage" key="project.workperiod" />£º
				</td>
				<td>
					<bean:write name="tenderProjectForm" property="tenderInfo.tpWorkdayCount"/>					
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="right">
					<bean:message bundle="tendermanage" key="tender.projectDescription" />£º
				</td>
				<td colspan="7">
					<bean:write name="tenderProjectForm" property="tenderInfo.tpDescription"/>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="right">
					<bean:message bundle="tendermanage" key="tender.attachment" />£º
				</td>
				<td colspan="5">					
				
					<logic:iterate id="am" name="tenderProjectForm" property="tenderInfo.attachmentList" indexId="i">
						<logic:equal name="am" property="paInputInterface" value="2">
						<span title="<bean:write name="am" property="paLocalUrl"/>">
							<a href="projectManage.do?method=downloadAttachment&id=<bean:write name='am' property='paId'/>" title="<bean:write name="am" property="paLocalUrl"/>">
								<bean:write name="am" property="paName"/>
							</a>
						</span>				
						</logic:equal>
					</logic:iterate>
			
				</td>	
			</tr>
			<tr><td>&nbsp;</td></tr>
			
		</table>
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
	</gui:tab>
	
</gui:tabbedPanel>


</gui:window>

