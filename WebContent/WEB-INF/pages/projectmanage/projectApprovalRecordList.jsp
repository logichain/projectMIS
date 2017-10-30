<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>


<center>
	<bean:define id="title">
		<bean:message bundle="approvalrecord" key="title" />
	</bean:define>

	<gui:window title="<%=title%>" prototype="boWindow" color="100%">			
		<html:form action="projectManage.do">
			<html:errors />
			<input type="hidden" name="method" value="searchProjectApprovalRecord">
			<input type="hidden" name="id" value="">
			<table class="win" CELLPADDING="2" CELLSPACING="0" width="100%" border="0">	
				<tr><td width="10%"></td><td width="20%"></td><td width="10%"></td><td width="20%"></td><td width="10%"></td><td></td></tr>				
				<tr>
					<td align="right">
						<bean:message bundle="approvalrecord" key="project_name" />：
					</td>
					<td >
						<html:text property="projectApprovalRecordInfo.parProjectName" size="18" maxlength="45"/>						
					</td>
										
					<td align="right">
						<bean:message bundle="approvalrecord" key="project_owner" />：
					</td>
					<td >
						<html:text property="projectApprovalRecordInfo.parProjectOwner" size="18" maxlength="45"/>						
					</td>
					<td align="right">
						<bean:message bundle="approvalrecord" key="design_company" />：
					</td>
					<td >
						<html:text property="projectApprovalRecordInfo.parDesignCompany" size="18" maxlength="45"/>						
					</td>
				</tr>
				<tr>
					<td align="right">
						<bean:message bundle="approvalrecord" key="tender_company" />：
					</td>
					<td >
						<html:text property="projectApprovalRecordInfo.parTenderCompany" size="18" maxlength="45"/>						
					</td>
					<td align="right">
						<bean:message bundle="approvalrecord" key="status" />：
					</td>
					<td >
						<html:select property="projectApprovalRecordInfo.parStatus" style="width:125">	
							<html:option value=""></html:option>
							<html:option value="99">初始状态</html:option>
							<html:option value="1">立项评审中</html:option>
							<html:option value="2">立项评审驳回</html:option>
							<html:option value="3">立项评审通过</html:option>
							<html:option value="4">投标评审中</html:option>
							<html:option value="5">投标评审驳回</html:option>
							<html:option value="6">投标评审通过</html:option>
						</html:select>	
						
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<bean:message key="order_item"/>:
					</td>
					<td align="left">						
						<html:select property="projectApprovalRecordInfo.orderColumn" style="width:120" onchange="projectSearchForm.submit();">	
							<html:option value=""></html:option>
							<html:option value="parProjectName"><bean:message bundle="approvalrecord" key="project_name" /></html:option>
							<html:option value="parProjectOwner"><bean:message bundle="approvalrecord" key="project_owner" /></html:option>
							<html:option value="parDesignCompany"><bean:message bundle="approvalrecord" key="design_company" /></html:option>
							<html:option value="parTenderCompany"><bean:message bundle="approvalrecord" key="tender_company" /></html:option>
							<html:option value="parStatus"><bean:message bundle="approvalrecord" key="status" /></html:option>	
							<html:option value="parTenderDate"><bean:message bundle="approvalrecord" key="tender_time" /></html:option>						
						</html:select>
						<html:checkbox property="projectApprovalRecordInfo.ascFlag">↑</html:checkbox>
						<html:hidden property="projectApprovalRecordInfo.ascFlag" value="false"/>
					</td>
				
					<td align="right"><bean:message key="page_itemcount"/>:</td>
					<td align="left">
						<html:select property="projectApprovalRecordInfo.pageItemCount" style="width:120px" onchange="projectSearchForm.submit();">	
							<html:option value="20">20</html:option>
							<html:option value="30">30</html:option>
							<html:option value="40">40</html:option>
							<html:option value="50">50</html:option>
							<html:option value="60">60</html:option>																	
						</html:select>
					</td>
					<td></td>		
					<td align="center">
						<logic:equal name="accountPerson" property="hasCreatePower" value="true">
							<html:submit styleClass="createbutton" onclick="chgAction(document.all.method,'createProjectApprovalRecord');">
								&nbsp;
							</html:submit>
						</logic:equal>
					</td>
					<td align="right">
						<html:submit styleClass="searchbutton" onclick="initPageNo();">
							&nbsp;
						</html:submit>
						<html:submit styleClass="resetbutton" onclick="chgAction(document.all.method,'resetSearchProjectApprovalRecord');">
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
						<td align="center">
							<bean:message bundle="approvalrecord" key="project_name" />
						</td>						
						<td align="center">
							<bean:message bundle="approvalrecord" key="project_owner" />
						</td>
						<td align="center">
							<bean:message bundle="approvalrecord" key="design_company" />
						</td>
						<td align="center">
							<bean:message bundle="approvalrecord" key="tender_company" />
						</td>
						<td width="10%" align="center">
							<bean:message bundle="projectmanage" key="project.dept" />
						</td>
						<td width="8%" align="center">
							<bean:message bundle="approvalrecord" key="status" />
						</td>
						<td width="8%" align="center">
							<bean:message bundle="approvalrecord" key="tender_time" />
						</td>		
						<td width="4%" align="center">							
							查阅
						</td>				
						
						<logic:equal name="accountPerson" property="hasCreatePower" value="true">
							<td width="4%" align="center">
								<bean:message key="button.edit" />
							</td>
							<td width="4%" align="center">
								<bean:message key="button.delete" />
							</td>
						</logic:equal>
						
						<logic:equal name="accountPerson" property="id" value="0">
							<td width="4%" align="center">
								<bean:message key="button.candel" />
							</td>
						</logic:equal>
					</tr>
				</thead>
				<tbody>
					<!--page offset start -->
					<bean:define id="pageCount" name="projectSearchForm" property="projectApprovalRecordInfo.pageItemCount"></bean:define>
					<bean:define id="itemNo" name="projectApprovalRecordCount"></bean:define>
					<pg:pager url="./projectManage.do" items="<%=Integer.valueOf(itemNo.toString())%>" index="center" maxPageItems="<%=Integer.valueOf(pageCount.toString())%>" maxIndexPages="10" isOffset="<%= true %>" export="offset,currentPageNumber=pageNumber" scope="request">
						<%-- keep track of preference --%>						
						<pg:param name="method" value="searchProjectApprovalRecord"/>

						<%-- save pager offset during form changes --%>
						<input type="hidden" name="pager.offset" value="<%= offset %>"/>
						<logic:iterate name="projectApprovalRecordList" id="tp" indexId="i">
							<pg:item>
							  <tr>
								<% int a = i%2;request.setAttribute("a",a);%>
								<logic:equal name="a" value="0"><tr class="even"></logic:equal>
								<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
									<td align="center">										
										<%=i+1 %>
									</td>
									<td><bean:write name="tp" property="parProjectName"/></td>									
									
									<td><bean:write name="tp" property="parProjectOwner"/></td>
									<td><bean:write name="tp" property="parDesignCompany"/></td>
									<td><bean:write name="tp" property="parTenderCompany"/></td>
									<td><bean:write name="tp" property="department.DName"/></td>
									<td align="center">
										<logic:equal name="tp" property="parStatus" value="99">初始状态</logic:equal>
										<logic:equal name="tp" property="parStatus" value="1">立项评审中</logic:equal>
										<logic:equal name="tp" property="parStatus" value="2">立项评审驳回</logic:equal>
										<logic:equal name="tp" property="parStatus" value="3">立项评审通过</logic:equal>
										<logic:equal name="tp" property="parStatus" value="4">投标评审中</logic:equal>
										<logic:equal name="tp" property="parStatus" value="5">投标评审驳回</logic:equal>
										<logic:equal name="tp" property="parStatus" value="6">投标评审通过</logic:equal>
									</td>
									<td align="center"><bean:write name="tp" property="parTenderDate"/></td>
													
									<td align="center">										
										<a href="javascript:openDialog('projectManage.do?method=displayProjectApprovalRecord&id=<bean:write name="tp" property="parId"/>',800,480)"><img border="0" src="pages\images\icon\16x16\display.gif"></a>
									</td>						
																		
									<logic:equal name="accountPerson" property="hasCreatePower" value="true">
										<td align="center">	
											<logic:equal name="tp" property="parStatus" value="99">
												<a href='projectManage.do?method=editProjectApprovalRecord&id=<bean:write name="tp" property="parId"/>'><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
											</logic:equal>
											<logic:equal name="tp" property="parStatus" value="2">
												<a href='projectManage.do?method=editProjectApprovalRecord&id=<bean:write name="tp" property="parId"/>'><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
											</logic:equal>
											<logic:equal name="tp" property="parStatus" value="5">
												<a href='projectManage.do?method=editProjectApprovalRecord&id=<bean:write name="tp" property="parId"/>'><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
											</logic:equal>											
										</td>
										
										<td align="center">
											<logic:equal name="tp" property="parStatus" value="99">																				
												<a href='javascript:if(confirm("确认要删除这条信息吗?")) {chgAction(document.all.id,"<bean:write name="tp" property="parId"/>");chgAction(document.all.method,"deleteProjectApprovalRecord");projectSearchForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
											</logic:equal>	
											<logic:equal name="tp" property="parStatus" value="2">																				
												<a href='javascript:if(confirm("确认要删除这条信息吗?")) {chgAction(document.all.id,"<bean:write name="tp" property="parId"/>");chgAction(document.all.method,"deleteProjectApprovalRecord");projectSearchForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
											</logic:equal>	
											<logic:equal name="tp" property="parStatus" value="5">																				
												<a href='javascript:if(confirm("确认要删除这条信息吗?")) {chgAction(document.all.id,"<bean:write name="tp" property="parId"/>");chgAction(document.all.method,"deleteProjectApprovalRecord");projectSearchForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
											</logic:equal>	
										</td>
										
									</logic:equal>	
									<logic:equal name="accountPerson" property="id" value="0">
										<td align="center">	
											<logic:equal name="tp" property="parStatus" value="-1">
												<a href='projectManage.do?method=cancelDeletedProjectApprovalRecord&id=<bean:write name="tp" property="parId"/>'><img border="0" src="pages\images\icon\16x16\return.gif"></a>
											</logic:equal>										
										</td>										
									</logic:equal>
										
								</tr>
							</pg:item>
						</logic:iterate>
			
					<jsp:include page="../common/page.jsp" flush="true" />
					</pg:pager>
					<logic:equal name="itemNo" value="0">
						<tr>
							<td COLSPAN="8">
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
 
 
function openDialog(loadpos,WWidth,WHeight)//Lock   Size 
{   	
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);   
	var WTop = Math.ceil((window.screen.height - WHeight) / 2); 
	var features = 'width=' + WWidth + 'px,' +	'height=' + WHeight + 'px,' + 'left=' + WLeft + 'px,' + 'top=' + WTop + 'px'; 
		
	WinOP = window.open(loadpos,"_blank",features); 
	WinOP.focus();	   
}
 
</script>
