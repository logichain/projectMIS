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

<div class="scroll">
<gui:window title='<%=title%>' prototype="boWindow">
<jsp:include page="/WEB-INF/pages/tendermanage/tenderProjectInfo.jsp"></jsp:include>


<gui:tabbedPanel selectedTab='tenderBudget' prototype="boTabbedPanel" width="16">
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
		<table width="100%"><tr>
			<td align="left">
				<bean:message bundle="projectmanage" key="task.checkresult"/>：
				<logic:equal name="tenderProjectForm" property="tenderInfo.tenderBudget.pbCheckResult" value="1">
					评审中
				</logic:equal>
				<logic:equal name="tenderProjectForm" property="tenderInfo.tenderBudget.pbCheckResult" value="2">
					驳回
				</logic:equal>
				<logic:equal name="tenderProjectForm" property="tenderInfo.tenderBudget.pbCheckResult" value="3">
					通过
				</logic:equal>	
			</td>
			<td align="center" width="30%">
				付款方式：						
				<html:checkbox disabled="true" name="tenderProjectForm" property="tenderInfo.tenderBudget.pbOnAccount" value="1">垫付</html:checkbox>									
			</td>		
			<td align="right">			
<%-- 				<html:button property="" styleClass="checklogbutton" onclick="openDialog('tenderProjectManage.do?method=showBudgetChecktaskList&type=5',800,360);" style="cursor: hand;"> --%>
<!-- 					&nbsp; -->
<%-- 				</html:button>	 --%>
			</td>
		</tr></table>
		<table class="sort-table" WIDTH="100%" border="0">
			<thead>
				<tr>
					<td width="5%"></td><td width="5%"></td><td width="30%"></td><td width="15%"></td><td></td>
				</tr>
				<tr>
					<td align="center">
						<bean:message bundle="budgetmanage" key="budget.indexNo" />
					</td>
					<td colspan="2" align="center">
						<bean:message bundle="budgetmanage" key="budget.budgetItem" />
					</td>
					<td align="center">
						<bean:message bundle="budgetmanage" key="budget.income" />
					</td>
					<td align="center">
						<bean:message bundle="budgetmanage" key="budget.remark" />
					</td>					
				</tr>
			</thead>
			<tbody>
				<%int a = 0;for(int i=7;i<=45;i++){%>
				<logic:equal name="tenderProjectForm" property='<%= "tenderInfo.tenderBudget.e" + i + ".itemModel.bimTenderFlag" %>' value="1">
				<tr>
					<logic:equal name="tenderProjectForm" property='<%= "tenderInfo.tenderBudget.e" + i + ".itemModel.bimGatherFlag" %>' value="1">
						<%a++; %>
						<td align="center"><%=a%></td>							
						<td colspan="2" align="center"><bean:write name="tenderProjectForm" property='<%= "tenderInfo.tenderBudget.e" + i + ".itemModel.bimName" %>'/></td>
					</logic:equal>
					<logic:equal name="tenderProjectForm" property='<%= "tenderInfo.tenderBudget.e" + i + ".itemModel.bimGatherFlag" %>' value="0">
						<td>&nbsp;</td>
						<td align="center"><bean:write name="tenderProjectForm" property='<%= "tenderInfo.tenderBudget.e" + i + ".itemModel.bimCode" %>'/></td>
						<td align="center"><bean:write name="tenderProjectForm" property='<%= "tenderInfo.tenderBudget.e" + i + ".itemModel.bimName" %>'/></td>
					</logic:equal>				
					<td align="center"><bean:write name="tenderProjectForm" property='<%= "tenderInfo.tenderBudget.e" + i + "AmountStr" %>'/></td>						
					<td align="left"><bean:write name="tenderProjectForm" property='<%= "tenderInfo.tenderBudget.e" + i + ".biRemark" %>'/></td>			
			       
				</tr>
				</logic:equal>
				<%} %>
				
			</tbody>
		</table>
		<table class="normal-table" cellSpacing="1" cellPadding="1" width="100%" border="0">		
		<thead>
			<tr>
				<td width="5%" align="center">
					<bean:message bundle="projectmanage" key="serial" />
				</td>
				<td width="10%" align="center">
					<bean:message bundle="checktask" key="task.receiveDept" />
				</td>			
				<td width="10%" align="center">
					<bean:message bundle="checktask" key="task.receivePost" />
				</td>
				<td width="16%"  align="center">
					<bean:message bundle="checktask" key="task.checkableUser" />
				</td>
				<td width="10%" align="center">
					<bean:message bundle="checktask" key="task.checkUser" />
				</td>
				<td width="10%" align="center">
					<bean:message bundle="checktask" key="task.checkTime" />
				</td>
				<td width="8%" align="center">
					<bean:message bundle="checktask" key="task.checkResult" />
				</td>
				<td align="center">
					<bean:message bundle="checktask" key="task.remark" />
				</td>						
			</tr>
		</thead>
		<tbody>		
			<logic:iterate name="taskList" id="task" indexId="i">				  
				<% int b = ((org.king.common.checktask.CheckTask)task).getCtCheckStep()%2;request.setAttribute("b",b);%>
				<logic:equal name="b" value="0"><tr class="blue"></logic:equal>
				<logic:equal name="b" value="1"><tr class="odd"></logic:equal>
					<td align="center">										
						<%=i+1 %>
						<logic:equal name="task" property="ctCheckStep" value="1">↓</logic:equal>
						<logic:notEqual name="task" property="ctCheckStep" value="1">&nbsp;&nbsp;</logic:notEqual>
					</td>
					<td align="center">
						<bean:write name="task" property="receiveDepartment.DName"/>						
					</td>
									
					<td align="center">
						<logic:notEmpty name="task" property="receivePost">
							<bean:write name="task" property="receivePost.PName"/>
						</logic:notEmpty>
						<logic:empty name="task" property="receivePost">部门经理	</logic:empty>
					</td>
					<td align="center"><bean:write name="task" property="checkableUserList"/></td>
					<td align="center">
						<logic:notEmpty name="task" property="checkUser">
							<bean:write name="task" property="checkUser.person.personName"/>
						</logic:notEmpty>
					</td>
					<td align="center"><bean:write name="task" property="ctCheckTime"/></td>
					<td align="center">
						<logic:equal name="task" property="ctCheckResult" value="1">通过</logic:equal>
						<logic:equal name="task" property="ctCheckResult" value="2">驳回</logic:equal>
					</td>	
					<td>
						<logic:empty name="task" property="ctRemark">--</logic:empty>
						<logic:notEmpty name="task" property="ctRemark"><bean:write name="task" property="ctRemark"/></logic:notEmpty>
					</td>	
					<!--td align="center">									
						<a href="javascript:openDialog('projectManage.do?method=displayCheckTaskDetail&id=<bean:write name="task" property="ctId"/>',700,400)"><img border="0" src="pages\images\icon\16x16\display.gif"></a>
					</td-->															
				</tr>			
			</logic:iterate>		 
		</tbody>
	</table>
	</gui:tab>
	<gui:tab prototype="boTab" name="competitor" title="<%=competitorTitle%>" followUp="tenderProjectManage.do?method=displayCompetitor">
		
	</gui:tab>
	
	<gui:tab prototype="boTab" name="contractcheck" title="<%=tenderContractTitle%>" followUp="tenderProjectManage.do?method=displayTenderContractcheck">
		
	</gui:tab>
	<gui:tab prototype="boTab" name="tenderDocument" title="<%=tenderDocumentTitle%>" followUp="tenderProjectManage.do?method=displayTenderDocument">
	</gui:tab>
</gui:tabbedPanel>


</gui:window>
</div>

<script language="JavaScript">
 
 function openDialog(loadpos,WWidth,WHeight)//Lock   Size 
{   
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);   
	var WTop = Math.ceil((window.screen.height - WHeight) / 2); 
	var features = 'width=' + WWidth + 'px,' +	'height=' + WHeight + 'px,' + 'left=' + WLeft + 'px,' + 'top=' + WTop + 'px'; 
		
	WinOP = window.open(loadpos,"_blank",features); 
	WinOP.focus();	   
}

</script>
