<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>


<center>
	<bean:define id="title">
		<bean:message bundle="tendermanage" key="tender.tenderManage" />
	</bean:define>

	<gui:window title="<%=title%>" prototype="boWindow" color="100%">
		<html:form action="tenderManage.do">
			<html:errors />
			<input type="hidden" name="method" value="searchTenderProject">
			<input type="hidden" name="id" value="">
			<table class="win" CELLPADDING="2" CELLSPACING="0" width="100%" border="0">
				<tr><td width="10%"></td><td width="20%"></td><td width="10%"></td><td width="20%"></td><td width="10%"></td><td></td></tr>
				<tr>
					<td align="right">
						<bean:message bundle="projectmanage" key="project.name" />：
					</td>
					<td>
						<html:text property="tenderInfo.tpName" size="18" maxlength="100"/>
					</td>			
					
					<td align="right">
						<bean:message bundle="projectmanage" key="project.status" />：
					</td>
					<td>
						<html:select property="tenderInfo.tpStatus" style="width:120">	
							<html:option value=""></html:option>
							<html:options collection="statusList" property="psId" labelProperty="psName"/>
						</html:select>				
					</td>
	
					<td align="right">
						<bean:message bundle="projectmanage" key="project.manager" />：
					</td>
					<td>
						<html:text property="tenderInfo.manager.person.personName" size="18" maxlength="100"/>								
					</td>
					
					<td ALIGN="center">	
						<logic:equal name="accountPerson" property="hasCreatePower" value="true">					
							<html:submit styleClass="createbutton" onclick="chgAction(document.all.method,'loadTenderProjectAdd');">
								&nbsp;
							</html:submit>
						</logic:equal>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<bean:message bundle="notice" key="release.dept" />：
					</td>
					<td>
						<html:select property="tenderInfo.tpDept" style="width:120">	
							<html:option value=""></html:option>
							<html:options collection="departmentList" property="DId" labelProperty="DName"/>
						</html:select>
					</td>	
					<td align="right">
						<bean:message key="order_item"/>:
					</td>
					<td align="left">						
						<html:select property="tenderInfo.orderColumn" style="width:120" onchange="tenderInputForm.submit();">	
							<html:option value=""></html:option>
							<html:option value="tpName"><bean:message bundle="projectmanage" key="project.name" /></html:option>
							<html:option value="tpStatus"><bean:message bundle="projectmanage" key="project.status" /></html:option>
							<html:option value="manager.person.personName"><bean:message bundle="projectmanage" key="project.manager" /></html:option>
							<html:option value="tpBeginDate"><bean:message bundle="projectmanage" key="project.begindate" /></html:option>
							<html:option value="tpEndDate"><bean:message bundle="projectmanage" key="project.enddate" /></html:option>							
						</html:select>
						<html:checkbox property="tenderInfo.ascFlag">↑</html:checkbox>
						<html:hidden property="tenderInfo.ascFlag" value="false"/>
					</td>
					<td align="right"><bean:message key="page_itemcount"/>:</td>
					<td align="left">
						<html:select property="tenderInfo.pageItemCount" style="width:120px" onchange="tenderInputForm.submit();">	
							<html:option value="20">20</html:option>
							<html:option value="30">30</html:option>
							<html:option value="40">40</html:option>
							<html:option value="50">50</html:option>
							<html:option value="60">60</html:option>																	
						</html:select>
					</td>	
					
					
					
					<td align="right">
						<html:submit styleClass="searchbutton" onclick="initPageNo();">
							&nbsp;
						</html:submit>
						<html:submit styleClass="resetbutton" onclick="chgAction(document.all.method,'resetSearchTenderProject');">
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
							<bean:message bundle="projectmanage" key="project.name" />
						</td>
<!-- 						<td width="20%" align="center"> -->
<%-- 							<bean:message bundle="tendermanage" key="tender.clientName" /> --%>
<!-- 						</td>						 -->
						<td width="10%" align="center">
							<bean:message bundle="projectmanage" key="project.begindate" />
						</td>
						<td width="10%" align="center">
							<bean:message bundle="projectmanage" key="project.enddate" />
						</td>	
						<td width="10%" align="center">
							<bean:message bundle="projectmanage" key="project.dept" />
						</td>
						<td width="10%" align="center">
							<bean:message bundle="projectmanage" key="project.manager" />
						</td>						
						<td width="8%" align="center">
							<bean:message bundle="projectmanage" key="project.status" />
						</td>
						
						<td width="5%" align="center">
							查阅
						</td>
						<logic:equal name="accountPerson" property="hasCreatePower" value="true">
							<td width="5%" align="center">
								<bean:message key="button.edit" />
							</td>						
							<td  width="5%" align="center">
								<bean:message key="button.delete" />
							</td> 
						
							<td width="5%" align="center">
								转移
							</td>
						</logic:equal>
						<logic:equal name="accountPerson" property="id" value="0">
							<td width="5%" align="center">
								<bean:message key="button.candel" />
							</td>					
						</logic:equal>
					</tr>
				</thead>
				<tbody>
					<logic:present name="projectList" scope="request">
					<!--page offset start -->
					<bean:define id="pageCount" name="tenderInputForm" property="tenderInfo.pageItemCount"></bean:define>
					<%int itemNo = ((Integer) request.getAttribute("projectCount")).intValue();%>
					<pg:pager url="./tenderManage.do" items="<%=itemNo%>" index="center" maxPageItems="<%=Integer.valueOf(pageCount.toString())%>" maxIndexPages="10" isOffset="<%= true %>" export="offset,currentPageNumber=pageNumber" scope="request">
						<%-- keep track of preference --%>						
						<pg:param name="method" value="searchTenderProject"/>

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
<%-- 									<td align="center"><bean:write name="tp" property="customer.CName"/></td> --%>
									
									<td align="center"><bean:write name="tp" property="tpBeginDate"/></td>
									<td align="center"><bean:write name="tp" property="tpEndDate"/></td>
									<td><bean:write name="tp" property="department.DName"/></td>
									<td align="center">
										<logic:notEmpty name="tp" property="manager">
											<bean:write name="tp" property="manager.person.personName"/>
										</logic:notEmpty>
									</td>
									<td align="center"><bean:write name="tp" property="status.psName"/></td>										
									<td align="center">
										<a href="javascript:openDialog('tenderProjectManage.do?method=displayBaseTenderProject&id=<bean:write name="tp" property="tpId"/>',1200,480)"><img border="0" src="pages\images\icon\16x16\display.gif"></a>										
									</td>
									<logic:equal name="accountPerson" property="hasCreatePower" value="true">
										<td align="center">
											<logic:equal name="tp" property="tpStatus" value="2">
												<a href='tenderProjectManage.do?method=editBaseTenderProject&id=<bean:write name="tp" property="tpId"/>'><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
											</logic:equal>
											<logic:equal name="tp" property="tpStatus" value="5">
												<a href='tenderProjectManage.do?method=editBaseTenderProject&id=<bean:write name="tp" property="tpId"/>'><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
											</logic:equal>
										</td>										
										<td align="center">											
											<logic:equal name="tp" property="tpStatus" value="2">
												<a href='javascript:if(confirm("确认要删除这条信息吗?")) {chgAction(document.all.id,"<bean:write name="tp" property="tpId"/>");chgAction(document.all.method,"deleteTenderProject");tenderInputForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
											</logic:equal>
											<logic:equal name="tp" property="tpStatus" value="5">
												<a href='javascript:if(confirm("确认要删除这条信息吗?")) {chgAction(document.all.id,"<bean:write name="tp" property="tpId"/>");chgAction(document.all.method,"deleteTenderProject");tenderInputForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
											</logic:equal>											
										</td>
										<td align="center">
											<logic:equal name="tp" property="tpStatus" value="2">
												<a href="javascript:openDialog('tenderProjectManage.do?method=transferTenderProject&id=<bean:write name="tp" property="tpId"/>',800,480)"><img border="0" src="pages\images\icon\16x16\manage.gif"></a>
											</logic:equal>
										</td>
									</logic:equal>			
									<logic:equal name="accountPerson" property="id" value="0">
										<td align="center">
											<logic:equal name="tp" property="tpStatus" value="-1">
												<a href='tenderProjectManage.do?method=cancelDeletedTenderProject&id=<bean:write name="tp" property="tpId"/>'><img border="0" src="pages\images\icon\16x16\return.gif"></a>
											</logic:equal>
										</td>		
									</logic:equal>												
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
 
 
 function openDialog(loadpos,WWidth,WHeight)//Lock   Size 
{   	
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);   
	var WTop = Math.ceil((window.screen.height - WHeight) / 2); 
	var features = 'width=' + WWidth + 'px,' +	'height=' + WHeight + 'px,' + 'left=' + WLeft + 'px,' + 'top=' + WTop + 'px'; 
		
	WinOP = window.open(loadpos,"_blank",features); 
	WinOP.focus();	   
}
 
</script>
