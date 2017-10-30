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
<gui:tabbedPanel selectedTab="teammember" prototype="boTabbedPanel" width="16" >
<gui:tab prototype="boTab" name="base" title="<%=basetitle%>" followUp="projectManage.do?method=displayProjectBase">	
</gui:tab>
<gui:tab prototype="boTab" name="accountManage" title="<%=accountManageTitle%>" followUp="projectManage.do?method=displayProjectAccount">	
</gui:tab>
<gui:tab prototype="boTab" name="document" title="<%=documenttitle%>" followUp="projectManage.do?method=displayProjectDocument">	
</gui:tab>
<gui:tab prototype="boTab" name="teammember" title="<%=teammembertitle%>" followUp="projectManage.do?method=displayProjectTeamMember">	
	<html:form action="projectManage.do">
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr><td>&nbsp;</td></tr>	
			
		</table>							
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
					<td width="12%" align="center">
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
				<logic:iterate name="projectSearchForm" property="projectInfo.teamMemberList" id="tm" indexId="i">	
				<logic:notEmpty name="tm" property="ptProjectRole">	
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
						<td align="center"><bean:write name="tm" property="projectRole.prName"/></td>
						
						<td align="center"><bean:write name="tm" property="ptRemark"/></td>
						<td align="center"><a href="javascript:openDialogNoSubmit('projectManage.do?method=displayUserAccountInfo&id=<bean:write name="tm" property='ptAccount'/>', 600,300);void(0);"><img border="0" src="pages\images\icon\16x16\display.gif"/></a></td>
									
					</tr>
					</logic:notEqual>
					</logic:notEmpty>
				</logic:iterate>			
			</tbody>
		</table>				
	</html:form>
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
