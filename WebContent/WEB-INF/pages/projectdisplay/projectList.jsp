<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<center>
	<bean:define id="title">
		<bean:message bundle="projectmanage" key="project.manage" />
	</bean:define>

	<gui:window title="<%=title%>" prototype="boWindow" color="100%">			
		<html:form action="projectManage.do">
			<html:errors />
			<input type="hidden" name="method" value="searchTenderProjectForFinish">
			<input type="hidden" name="id" value="">
			<table class="win" CELLPADDING="2" CELLSPACING="0" width="100%" border="0">					
				<tr>
					<td width="10%" align="right">
						<bean:message bundle="projectmanage" key="project.name" />£º
					</td>
					<td width="15%" >
						<html:text property="projectInfo.tpName" size="18" maxlength="45"/>						
					</td>
					
					<td width="10%" align="right">
						<bean:message bundle="projectmanage" key="project.code" />£º
					</td>
					<td width="15%">						
						<html:text property="projectInfo.tpCode" size="18" maxlength="45"/>
					</td>
					<td width="10%" align="right">
						<bean:message bundle="projectmanage" key="project.contractcode" />£º
					</td>
					<td width="15%">						
						<html:text property="projectInfo.tpContractCode" size="18" maxlength="45"/>
					</td>
				</tr>
	
				<tr>
					<td align="right">
						<bean:message bundle="projectmanage" key="project.type" />£º
					</td>
					<td>						
						<html:select property="projectInfo.tpType" style="width:120">	
							<html:option value=""></html:option>
							<html:options collection="typeList" property="ptId" labelProperty="ptName"/>
						</html:select>
					</td>	
					<td align="right">
						<bean:message bundle="projectmanage" key="project.important" />£º
					</td>
					<td>						
						<html:select property="projectInfo.tpImportantDegree" style="width:120">	
							<html:option value=""></html:option>
							<html:options collection="importantDegreeList" property="pidId" labelProperty="pidName"/>
						</html:select>
					</td>
	
					<td align="right">
						<bean:message bundle="projectmanage" key="project.manager" />£º
					</td>
					<td>
						<html:text property="projectInfo.manager.person.personName" size="18" maxlength="100"/>		
									
					</td>
	
				</tr>
				<tr>
					<td width="10%" align="right">
						<bean:message bundle="projectmanage" key="project.begindate" />£º 
					</td>
					<td width="15%" >						
						<html:text property="projectInfo.tpBeginDate" readonly="true" size="18" maxlength="32" onclick="SelectDate(this)"/>
					</td>				
					<td width="10%" align="right">
						<bean:message bundle="projectmanage" key="project.enddate" />£º 
					</td>
					<td width="15%" >						
						<html:text property="projectInfo.tpEndDate" readonly="true" size="18" maxlength="32" onclick="SelectDate(this)"/>
					</td>
							
					<td align="right">
						<bean:message bundle="projectmanage" key="project.status" />£º
					</td>
					<td>
						<html:select property="projectInfo.tpStatus" style="width:120">	
							<html:option value=""></html:option>
							<html:options collection="statusList" property="psId" labelProperty="psName"/>
						</html:select>				
					</td>
				</tr>
				<tr>
					<td colspan="6" align="right">
						<html:submit styleClass="searchbutton" onclick="initPageNo();">
							&nbsp;
						</html:submit>
						<html:submit styleClass="resetbutton" onclick="chgAction(document.all.method,'resetSearchTenderProjectForFinish');">
							&nbsp;
						</html:submit>
					</td>
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
						<td width="6%" align="center">
							<bean:message key="button.view" />
						</td>
						<td align="center">
							<bean:message key="closewindow" />
						</td>
					</tr>
				</thead>
				<tbody>					
					<!--page offset start -->
					<bean:define id="itemNo" name="projectSearchForm" property="projectCount"></bean:define>
					<pg:pager url="./projectManage.do" items="<%=Integer.valueOf(itemNo.toString())%>" index="center" maxPageItems="10" maxIndexPages="10" isOffset="<%= true %>" export="offset,currentPageNumber=pageNumber" scope="request">
						<%-- keep track of preference --%>						
						<pg:param name="method" value="searchTenderProjectForFinish"/>

						<%-- save pager offset during form changes --%>
						<input type="hidden" name="pager.offset" value="<%= offset %>"/>
						<logic:iterate name="projectSearchForm" property="projectList" id="tp" indexId="i">
							<pg:item>
								<% int a = i%2;request.setAttribute("a",a);%>
								<logic:equal name="a" value="0"><tr class="even"></logic:equal>
								<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
									<td align="center">										
										<%=i+1 %>
									</td>
									<td><bean:write name="tp" property="tpName"/></td>									
									<td align="center">
										<logic:notEmpty name="tp" property="type">
											<bean:write name="tp" property="type.ptName"/>
										</logic:notEmpty>
									</td>
									
									<td align="center">
										<logic:notEmpty name="tp" property="importantDegree">
											<bean:write name="tp" property="importantDegree.pidName"/>
										</logic:notEmpty>
									</td>
									<td align="center"><bean:write name="tp" property="tpBeginDate"/></td>
									<td align="center"><bean:write name="tp" property="tpEndDate"/></td>
									<td align="center"><bean:write name="tp" property="schedulePlanPercentStr"/></td>
									<td align="center"><bean:write name="tp" property="status.psName"/></td>
									<td align="center">
										<logic:notEmpty name="tp" property="manager">
											<bean:write name="tp" property="manager.person.personName"/>
										</logic:notEmpty>
									</td>
										
									<td align="center"></td>
									<td align="center"><a href='projectManage.do?method=displayTenderProject&id=<bean:write name="tp" property="tpId"/>'><img border="0" src="pages\images\icon\16x16\display.gif"></a></td>
									<td align="center">
									<logic:equal name="tp" property="tpStatus" value="4">
										<a href='projectManage.do?method=closeTenderProject&id=<bean:write name="tp" property="tpId"/>'><img border="0" src="pages\style\default\images\recycle.gif"></a>
									</logic:equal>
									</td>														
								</tr>
							</pg:item>
						</logic:iterate>
			
					<jsp:include page="../common/page.jsp" flush="true" />
					</pg:pager>
					<!-- page offset end -->
					<logic:equal name="itemNo" value="0">
						<tr>
							<td COLSPAN="9">
								<bean:message key="noRecord" />
							</td>
						</tr>
					</logic:equal>
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
 
 
</script>
