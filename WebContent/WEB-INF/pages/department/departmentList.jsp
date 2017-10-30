<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>
<center>
	<bean:define id="title">
		<bean:message bundle="department" key="department.title" />
	</bean:define>

	<gui:window title="<%=title%>" prototype="boWindow">			
		<html:form action="departmentManage.do">
			<table class="win" CELLPADDING="2" CELLSPACING="0" width="100%">					
				<tr align="right">
					<td>
						<bean:message bundle="department" key="department.name" />：
						<html:text property="name" size="18" maxlength="45" />		
						
						<html:submit styleClass="searchbutton" onclick="initPageNo();">
							&nbsp;
						</html:submit>
						<html:submit styleClass="resetbutton" onclick="chgAction(document.all.method,'resetSearchDepartment');">
							&nbsp;
						</html:submit>
					</td>
				</tr>
			</table>

			<input type="hidden" name="method" value="searchDepartment">	
			<input type="hidden" name="id" value="">	
			<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">			
				<thead>
					<tr>
						<td width="5%" align="center">
							<bean:message bundle="projectmanage" key="serial" />
						</td>						
						<td width="20%" align="center">
							<bean:message bundle="department" key="department.name" />
						</td>
						<td width="10%" align="center">
							<bean:message bundle="department" key="department.phone" />
						</td>
						<td width="10%" align="center">
							<bean:message bundle="department" key="department.fax" />
						</td>
						<td align="center">
							<bean:message bundle="department" key="department.description" />
						</td>					
						<td width="5%" align="center">
							<bean:message key="button.edit" />
						</td>
<!--						<td align="center">	<bean:message key="button.delete" /></td>-->
					</tr>
				</thead>
					<logic:present name="departmentList" scope="request">
					<!--page offset start -->
					<%int itemNo = ((Integer) request.getAttribute("departmentCount")).intValue();%>
					<pg:pager url="./departmentManage.do" items="<%=itemNo%>" index="center" maxPageItems="10" maxIndexPages="10" isOffset="<%= true %>" export="offset,currentPageNumber=pageNumber" scope="request">
						<%-- keep track of preference --%>						
						<pg:param name="method" value="searchDepartment"/>

						<%-- save pager offset during form changes --%>
						<input type="hidden" name="pager.offset" value="<%= offset %>">
						<logic:iterate name="departmentList" id="department" indexId="i">
							<pg:item>
							  <tr>
								<% int a = i%2;request.setAttribute("a",a);%>
								<logic:equal name="a" value="0"><tr class="even"></logic:equal>
								<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
									<td align="center">										
										<%=i+1 %>
									</td>
									<td>
										<bean:write name="department" property="DName" />
									</td>
													
									<td>
										<bean:write name="department" property="DPhone"/>
									</td>
									<td>
										<bean:write name="department" property="DFax" />
									</td>																		
									<td>
										<bean:write name="department" property="DDescription" />
									</td>
									<td align="center"><a href='departmentManage.do?method=modifyDepartment&id=<bean:write name="department" property="DId"/>'><img border="0" src="pages\images\icon\16x16\modify.gif"></a></td>	
<!--									<td align="center"><a href='javascript:if(confirm("确认要删除这条信息吗?")) {chgAction(document.all.id,"<bean:write name="department" property="DId"/>");chgAction(document.all.method,"deleteDepartment");departmentForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a></td>-->
								</tr>
							</pg:item>
						</logic:iterate>
			
					<jsp:include page="../common/page.jsp" flush="true" />
				</pg:pager>
				<!-- page offset end -->
				</logic:present>
				<logic:notPresent name="departmentList" scope="request">
					<tr>
						<td COLSPAN="9">
							<bean:message key="noRecord" />
						</td>
					</tr>
				</logic:notPresent>	
			</table>
						
			<table class="win" cellSpacing="0" cellPadding="0" width="100%" border="0">
				<tr><td>&nbsp;</td></tr>	
				<tr>
					<td COLSPAN="2" ALIGN="RIGHT">
						<html:submit styleClass="addbutton" onclick="chgAction(document.all.method,'createDepartment');">
							&nbsp;
						</html:submit>
					</td>
				</tr>
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
