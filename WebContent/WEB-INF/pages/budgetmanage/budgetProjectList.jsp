<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>
<%@ page import="org.king.common.tenderstatus.TenderStatus"%>

<center>
	<bean:define id="title">
		<bean:message bundle="budgetmanage" key="budget.manageTitle" />
	</bean:define>

	<gui:window title="<%=title%>" prototype="boWindow" color="100%">			
		<html:form action="tenderProjectManage.do">
			<html:errors />
			<input type="hidden" name="method" value="searchTenderProjectByStatus">
			<input type="hidden" name="id" value="">
			<table class="win" CELLPADDING="2" CELLSPACING="0" width="100%" border="0">
				<tr>
					<%String statusId = (String)request.getAttribute("statusId"); %>
					<logic:equal name="statusId" value="">
						<td align="left" width="8%">
							<input type="radio" name="ra" value=""
								onclick="confirmRadioSelected()" checked>全部
						</td>
					</logic:equal>
					<logic:notEqual name="statusId" value="">
						<td align="left" width="8%">
							<input type="radio" name="ra" value=""
								onclick="confirmRadioSelected()">全部
						</td>
					</logic:notEqual>
							
					<logic:present name="tenderStatusList" scope="request">
					<logic:iterate name="tenderStatusList" id="status" indexId="i">
						<%
						    Integer tsId = ((TenderStatus)status).getTsId();
							if(statusId != null && statusId.equals(tsId.toString())){
						%>
							<td align="left" width="8%">
								<input type="radio"	name="ra" value="<bean:write name="status" property="tsId"/>" 
									onclick="confirmRadioSelected()" checked>
								<bean:write  name="status" property="tsName" />
							</td>
						
						<%
							} else {
						%>
							<td align="left" width="8%">
								<input type="radio"	name="ra" value="<bean:write name="status" property="tsId"/>" 
									onclick="confirmRadioSelected()">
								<bean:write  name="status" property="tsName" />
							</td>
						<%
						}
						%>
					</logic:iterate>
					</logic:present>
					<td width="50%" align="center"></td>
				</tr>
			</table>

						
			<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">						
				
				<thead>
					<tr>
						<td width="4%" align="center">
							<bean:message bundle="projectmanage" key="serial" />
						</td>
						<td width="20%" align="center">
							<bean:message bundle="projectmanage" key="project.name" />
						</td>
						<td width="8%" align="center">
							<bean:message bundle="projectmanage" key="project.type" />
						</td>
						<td width="6%" align="center">
							<bean:message bundle="projectmanage" key="project.important" />
						</td>
						<td width="10%" align="center">
							<bean:message bundle="projectmanage" key="project.begindate" />
						</td>
						<td width="10%" align="center">
							<bean:message bundle="projectmanage" key="project.enddate" />
						</td>
						<td width="6%" align="center">
							<bean:message bundle="projectmanage" key="project.schedule" />
						</td>
						<td width="10%" align="center">
							<bean:message bundle="projectmanage" key="project.status" />
						</td>
						<td width="10%" align="center">
							<bean:message bundle="projectmanage" key="project.manager" />
						</td>
						<td width="6%" align="center">
							<bean:message bundle="projectmanage" key="project.realschedule" />
						</td>
						<td width="4%" align="center">
							<bean:message key="button.edit" />
						</td>
						<td align="center">
							<bean:message key="button.delete" />
						</td>
					</tr>
				</thead>
				<tbody>
					<logic:present name="projectList" scope="request">
					<!--page offset start -->
					<%int itemNo = ((Integer) request.getAttribute("projectCount")).intValue();%>
					<pg:pager url="./tenderProjectManage.do" items="<%=itemNo%>" index="center" maxPageItems="10" maxIndexPages="10" isOffset="<%= true %>" export="offset,currentPageNumber=pageNumber" scope="request">
						<%-- keep track of preference --%>						
						<pg:param name="method" value="searchBudgetProjectByStatus"/>

						<%-- save pager offset during form changes --%>
						<input type="hidden" name="pager.offset" value="<%= offset %>"/>
						<logic:iterate name="projectList" id="tp" indexId="i">
							<pg:item>
							  <tr>
								<% int a = i%2;request.setAttribute("a",a);%>
								<logic:equal name="a" value="0"><tr class="even"></logic:equal>
								<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
									<td align="center">										
										<%=i+1 %>
									</td>
									<td><bean:write name="tp" property="tpName"/></td>									
									<td align="center">
										<logic:notEmpty name="tp" property="type">
											<logic:present name="type">
												<bean:write name="tp" property="type.ptName"/>
											</logic:present>
										</logic:notEmpty>
									</td>
									
									<td align="center">
										<logic:notEmpty name="tp" property="importantDegree">
											<bean:write name="tp" property="importantDegree.pidName"/>
										</logic:notEmpty>
									</td>
									<td align="center"><bean:write name="tp" property="tpBeginDate"/></td>
									<td align="center"><bean:write name="tp" property="tpEndDate"/></td>
									<td align="center"><bean:write name="tp" property="schedulePlanPercent"/>%</td>
									<td align="center"><bean:write name="tp" property="status.psName"/></td>
									<td align="center">
										<logic:notEmpty name="tp" property="manager">
											<bean:write name="tp" property="manager.person.personName"/>
										</logic:notEmpty>
									</td>
										
									<td align="center"></td>
									<td align="center"><a href='tenderProjectManage.do?method=editTenderBugetItem&id=<bean:write name="tp" property="tpId"/>'><img border="0" src="pages\images\icon\16x16\modify.gif"></a></td>	
									<td align="center"><a href='javascript:if(confirm("确认要删除这条信息吗?")) {chgAction(document.all.id,"<bean:write name="tp" property="tpId"/>");chgAction(document.all.method,"deleteTenderProject");tenderProjectForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a></td>					
								</tr>
							</pg:item>
						</logic:iterate>
			
					<jsp:include page="../common/page.jsp" flush="true" />
					</pg:pager>
					<!-- page offset end -->
					</logic:present>
					<logic:notPresent name="projectList" scope="request">
						<tr>
							<td COLSPAN="9">
								<bean:message key="noRecord" />
							</td>
						</tr>
					</logic:notPresent>	
				</tbody>
			</table>
					
		</html:form>
		
	</gui:window>
</center>


<script language="JavaScript">

 function chgAction(obj,str){
	obj.value=str;	
 }
 
 
 function initPageNo()
 {
 	document.getElementById("pager.offset").value='0';
 }
 
 function confirmRadioSelected(){
 	var selectedIndex = -1;
    var radios = document.forms[0].ra;
    var i = 0;

    for (i=0; i<radios.length; i++)
    {
        if (radios[i].checked)
        {
            selectedIndex = i;
            break;
        }
    }
    
    if (selectedIndex < 0)
    {
        return;
    }
	chgAction(document.all.method,'searchBudgetProjectByStatus');
	chgAction(document.all.id, radios[selectedIndex].value);
	tenderProjectForm.submit();
 }
 
  function chgAction(obj,str){
	obj.value=str;
 }
 
 
  function chgFormOnsubmit(str){  	 	
	tenderProjectForm.onsubmit="function onsubmit(){" + str + "}";	
 }
 

</script>
