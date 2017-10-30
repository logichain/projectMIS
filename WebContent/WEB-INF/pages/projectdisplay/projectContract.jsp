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
<gui:tabbedPanel selectedTab="contractcheck" prototype="boTabbedPanel" width="16" >
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
<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">					
	<thead>
		<tr>
			<td width="5%" align="center">
				<bean:message bundle="projectmanage" key="serial" />
			</td>						
			<td align="center">
				<bean:message bundle="projectmanage" key="contract.title" />
			</td>											
			<td  width="10%" align="center">
				<bean:message bundle="projectmanage" key="contract.type" />
			</td>						
			<td align="center">
				<bean:message bundle="projectmanage" key="project.customer" />
			</td>
			<td width="10%" align="center">
				<bean:message bundle="projectmanage" key="task.checkstatus" />
			</td>
			
			<td width="5%" align="center">
				<bean:message bundle="checktask" key="task.read" />
			</td>			
		</tr>
	</thead>
	<tbody>
		<%int i=0;%>
		<logic:iterate id="pc" name="projectSearchForm" property="projectInfo.projectContractList">
		<logic:notEqual name="pc" property="pcStatus" value="-1">					
		 	<% int a = i%2;request.setAttribute("a",a);i++;%>
			<logic:equal name="a" value="0"><tr class="even"></logic:equal>
			<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
				<td align="center"><%=i%></td>
				<td><bean:write name="pc" property="pcTitle"/></td>
				<td align="center">
					<logic:equal name="pc" property="pcType" value="1">订出合同</logic:equal>
					<logic:equal name="pc" property="pcType" value="2">订进合同</logic:equal>
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
									
				
				<td align="center">
					<logic:notEmpty name="pc" property="pcId">
						<a href='javascript:openDialog("projectManage.do?method=readProjectContract&id=<bean:write name="pc" property="pcId"/>",800,400);'><img border="0" src="pages\images\icon\16x16\display.gif"></a>
					</logic:notEmpty>
				</td>									
			</tr>
		</logic:notEqual>
		</logic:iterate>
	</tbody>
</table>
</gui:tab>
<gui:tab prototype="boTab" name="log" title="<%=logtitle%>" followUp="projectManage.do?method=displayProjectRecord">	
</gui:tab>
<gui:tab prototype="boTab" name="costsum" title="<%=costsumTitle%>" followUp="projectManage.do?method=displayTenderCostsum">
		
</gui:tab>	

</gui:tabbedPanel>		
</gui:window>

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

