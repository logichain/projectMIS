<%@ include file="../common/include.jsp"%>
<center>
	<bean:define id="title">
		<bean:message bundle="security" key="security.title" />
	</bean:define>

	<gui:window title="<%=title%>" prototype="boWindow" color="100%">	
	<html:form action="accountSecurityManager.do">	
		<table class="win" CELLPADDING="2" CELLSPACING="0" width="100%">			
			<tr align="right">
				<td>
					<bean:message bundle="security" key="account.name" />
					<html:text property="para"></html:text>
					<bean:message bundle="security" key="person.name" />
					<html:text property="paraPersonName"></html:text>
					<bean:message bundle="security" key="person.dept" />
					<html:select property="department" style="width:120">	
						<html:option value=""></html:option>
						<html:options collection="departmentList" property="DId" labelProperty="DName"/>
					</html:select>
					<html:submit styleClass="searchbutton" onclick="initPageNo();">
						&nbsp;
					</html:submit>
					<html:submit styleClass="resetbutton" onclick="initPageNo();chgAction(document.all.method,'resetSearchAccount');">
						&nbsp;
					</html:submit>
				</td>
			</tr>
		</table>
	<input type="hidden" name="method" value="searchAccount">
	<input type="hidden" name="id" value="">
	<table class="sort-table" cellSpacing="0" cellPadding="0" width="100%" border="0">		
		<logic:present name="accounts" scope="request">
			<thead>
				<tr>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="serial" />
					</td>
					<td width="10%" align="center">
						<bean:message bundle="security" key="account.name" />
					</td>
					<td width="10%" align="center">
						<bean:message bundle="security" key="person.code" />
					</td>
					<td width="15%" align="center">
						<bean:message bundle="security" key="person.name" />
					</td>
					<td width="10%" align="center">
						<bean:message bundle="security" key="security.currentRole" />
					</td>
					<td align="center">
						<bean:message bundle="security" key="person.dept" />/
						<bean:message bundle="security" key="person.post" />
					</td>
					<td width="20%" align="center">
						<bean:message bundle="security" key="person.email" />
					</td>
					<td width="5%" align="center">
						<bean:message bundle="security" key="account.enabled" />
					</td>
				</tr>
			</thead>

			<!--page offset start -->
			<%int itemNo = ((Integer) request.getAttribute("accountCount")).intValue();%>
			<pg:pager url="./accountSecurityManager.do" items="<%=itemNo%>" index="center" maxPageItems="10" maxIndexPages="10" isOffset="<%= true %>" export="offset,currentPageNumber=pageNumber" scope="request">
				<%-- keep track of preference --%>								
				<pg:param name="method" value="searchAccount"/>

				<%-- save pager offset during form changes --%>
				<input type="hidden" name="pager.offset" value="<%= offset %>">
				<logic:iterate name="accounts" id="account" indexId="i">
					<pg:item>
					  <tr>
						<% int a = i%2;request.setAttribute("a",a);%>
						<logic:equal name="a" value="0"><tr class="even"></logic:equal>
						<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
							<td align="center"><%=i+1 %></td>
							<td>										
								<bean:write name="account" property="name" />
							</td>
							<td>
								<bean:write name="account" property="person.personCode" />
							</td>
							<td>
								<bean:write name="account" property="person.personName" />
							</td>
							<td>
								<logic:iterate id="role" name="account" property="roles">
									<bean:write name="role" property="name" />&nbsp;
								</logic:iterate>
							</td>
							<td>								
								<logic:iterate id="up" name="account" property="usrPostList">
									<logic:notEmpty name="up" property="deptObject">
										<bean:write name="up" property="deptObject.DName"/>
									</logic:notEmpty>/
									<logic:notEmpty name="up" property="postObject">
										<bean:write name="up" property="postObject.PName"/>
									</logic:notEmpty>;
								</logic:iterate>
							</td>
							<td>
								<bean:write name="account" property="person.email" />
							</td>
							<td align="center">
								<logic:equal name="account" property="enabled" value="1">
									<input type="checkbox" checked onclick='chgAction(document.all.id,"<bean:write  name="account" property="id" />");chgAction(document.all.method,"enableAccount");accountForm.submit();'>
								</logic:equal>	
								<logic:equal name="account" property="enabled" value="0">
									<input type="checkbox" onclick='chgAction(document.all.id,"<bean:write  name="account" property="id" />");chgAction(document.all.method,"enableAccount");accountForm.submit();'>
								</logic:equal>												
							</td>

						</tr>
					</pg:item>
				</logic:iterate>
	
		<jsp:include page="../common/page.jsp" flush="true" />
	</pg:pager>
	<!-- page offset end -->
	</logic:present>
	</table>
</html:form>	
	<logic:notPresent name="accounts" scope="request">
		<tr>
			<td COLSPAN="3">
				<bean:message bundle="security" key="account.noRecord" />
			</td>
		</tr>
	</logic:notPresent>
			

	</gui:window>
</center>

<script language="JavaScript">
  function initPageNo()
 {
 	document.getElementById("pager.offset").value='0';
 }
 function chgAction(obj,str){
	obj.value=str;
 }

</script>
