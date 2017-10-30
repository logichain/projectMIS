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


<gui:tabbedPanel selectedTab='contractcheck' prototype="boTabbedPanel" width="16">
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
		<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">					
			<thead>
				<tr>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="serial" />
					</td>						
					<td width="15%" align="center">
						<bean:message bundle="projectmanage" key="contract.title" />
					</td>											
					<td  width="5%" align="center">
						<bean:message bundle="projectmanage" key="contract.type" />
					</td>						
					<td align="center">
						<bean:message bundle="projectmanage" key="project.customer" />
					</td>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="task.checkstatus" />
					</td>					
					<td width="10%" align="center">
						<bean:message bundle="projectmanage" key="task.sendtime" />
					</td>
					<td width="10%" align="center">
						<bean:message bundle="projectmanage" key="task.oktime" />
					</td>
					<td width="15%" align="center">
						<bean:message bundle="projectmanage" key="project.attachment" />
					</td>
					
				</tr>
			</thead>
			<tbody>
				<%int i=0;%>
				<logic:iterate id="pc" name="tenderProjectForm" property="tenderInfo.tenderContractList">
				<logic:notEqual name="pc" property="pcStatus" value="-1">					
				 	<% int a = i%2;request.setAttribute("a",a);i++;%>
					<logic:equal name="a" value="0"><tr class="even"></logic:equal>
					<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
						<td align="center"><%=i%></td>
						<td><bean:write name="pc" property="pcTitle"/></td>
						<td>
							<logic:equal name="pc" property="pcType" value="1">分包合同</logic:equal>
							<logic:equal name="pc" property="pcType" value="2">采购合同</logic:equal>
							<logic:equal name="pc" property="pcType" value="3">其他类型</logic:equal>
						</td>
						<td><bean:write name="pc" property="customer.CName"/></td>
						<td>							
							<logic:equal name="pc" property="pcStatus" value="0">
								初始
							</logic:equal>
							<logic:equal name="pc" property="pcStatus" value="1">
								评审中
							</logic:equal>
							<logic:equal name="pc" property="pcStatus" value="2">
								驳回
							</logic:equal>
							<logic:equal name="pc" property="pcStatus" value="3">
								通过
							</logic:equal>
						</td>
						<td><bean:write name="pc" property="pcCheckBegin"/></td>
						<td><bean:write name="pc" property="pcCheckEnd"/></td>
						<td>
							<logic:iterate id="am" name="pc" property="attachmentList">
								<logic:equal name="am" property="paInputInterface" value="3">
								<logic:notEqual name='am' property='paFlag' value="-1">									
									<a href="tenderProjectManage.do?method=downloadAttachment&id=<bean:write name='am' property='paId'/>" title="<bean:write name="am" property="paLocalUrl"/>">
										<bean:write name="am" property="paName"/>
									</a>									
								</logic:notEqual>	
								</logic:equal>					
							</logic:iterate>						
						</td>
													
					</tr>
				</logic:notEqual>
				</logic:iterate>
			</tbody>
		</table>
	</gui:tab>
	<gui:tab prototype="boTab" name="tenderDocument" title="<%=tenderDocumentTitle%>" followUp="tenderProjectManage.do?method=displayTenderDocument">
	</gui:tab>
</gui:tabbedPanel>


</gui:window>

