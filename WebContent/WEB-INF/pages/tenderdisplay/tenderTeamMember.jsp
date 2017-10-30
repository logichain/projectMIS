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


<gui:tabbedPanel selectedTab='teamMember' prototype="boTabbedPanel" width="16">
	<gui:tab prototype="boTab" name="baseInfo" title="<%=baseInfoTitle%>" followUp="tenderProjectManage.do?method=displayTenderBase">
		
	</gui:tab>
	<gui:tab prototype="boTab" name="tenderFile" title="<%=tenderFileTitle%>" followUp="tenderProjectManage.do?method=displayTenderFile">
		
	</gui:tab>

	<gui:tab prototype="boTab" name="teamMember" title="<%=teammembertitle%>" followUp="tenderProjectManage.do?method=displayTenderTeamMember">
		<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">					
			<thead>
				<tr>
					<td width="4%" align="center">
						<bean:message bundle="projectmanage" key="serial" />
					</td>
					
					<td width="10%" align="center">
						<bean:message bundle="security" key="person.name" />
					</td>
					
					<td width="30%" align="center">
						<bean:message bundle="security" key="person.dept" />/
						<bean:message bundle="security" key="person.post" />
					</td>

					<td width="8%" align="center">
						<bean:message bundle="projectmanage" key="member.projectrole" />
					</td>
					<td align="center">
						<bean:message bundle="projectmanage" key="feetax.remark" />
					</td>
					<td width="5%" align="center">
						ÏêÏ¸
					</td>
				</tr>
			</thead>
			<tbody>					
				<%int index = 1;%>
				<logic:iterate name="tenderProjectForm" property="tenderInfo.teamMemberList" id="tm" indexId="i">
				<logic:notEmpty name="tm" property="ptTenderRole">	
				<logic:notEqual name="tm" property="ptFlag" value="-1">
				  <tr>				
					<% int a = i%2;request.setAttribute("a",a);%>
					<logic:equal name="a" value="0"><tr class="even"></logic:equal>
					<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
						<td align="center">										
							<%=index %>
							<%index++; %>
						</td>
						<td><bean:write name="tm" property="account.person.personName"/></td>									
						
						<td align="center">
							<logic:iterate id="up" name="tm" property="account.usrPostList">
								<logic:notEmpty name="up" property="deptObject">
									<bean:write name="up" property="deptObject.DName"/>
								</logic:notEmpty>/
								<logic:notEmpty name="up" property="postObject">
									<bean:write name="up" property="postObject.PName"/>
								</logic:notEmpty>;
							</logic:iterate>
						</td>
						<td align="center"><bean:write name="tm" property="tenderRole.trName"/></td>
						<td align="center"><bean:write name="tm" property="ptRemark"/></td>
						<td align="center"><a href="javascript:openDialogNoSubmit('projectManage.do?method=displayUserAccountInfo&id=<bean:write name="tm" property='ptAccount'/>', 600,300);void(0);"><img border="0" src="pages\images\icon\16x16\display.gif"/></a></td>
										
					</tr>
				</logic:notEqual>
				</logic:notEmpty>
				</logic:iterate>			
			</tbody>
		</table>	
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


<script language="JavaScript">
function openDialogNoSubmit(loadpos,WWidth,WHeight)//Lock   Size 
{   	
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);   
	var WTop = Math.ceil((window.screen.height - WHeight) / 2); 
	var features = 'width=' + WWidth + 'px,' +	'height=' + WHeight + 'px,' + 'left=' + WLeft + 'px,' + 'top=' + WTop + 'px'; 
		
	WinOP = window.open(loadpos,"_blank",features); 
	WinOP.focus();   
}

</script>