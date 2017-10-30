<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>
<center>
	<bean:define id="title">
		<bean:message bundle="security" key="account.title" />
	</bean:define>

	<gui:window title="<%=title%>" prototype="boWindow" color="100%">			
		<html:form action="accountManager.do">
			<table class="win" CELLPADDING="2" CELLSPACING="0" width="100%">					
				<tr align="right">
					<td width="10%" align="right">
						<bean:message bundle="security" key="account.name" />：
					</td>
					<td width="15%" align="left">
						<html:text property="accountInfo.name"></html:text>
					</td>
					<td width="10%" align="right">
						<bean:message bundle="security" key="person.name" />：
					</td>
					<td width="15%" align="left">
						<html:text property="accountInfo.person.personName"></html:text>
					</td>
					<td width="10%" align="right">
						<bean:message bundle="security" key="person.dept" />：
					</td>
					<td width="15%" align="left">
						<html:select property="accountInfo.person.dept" style="width:120">	
							<html:option value=""></html:option>
							<html:options collection="departmentList" property="DId" labelProperty="DName"/>
						</html:select>
					</td>
					<td width="10%" align="right">
						<bean:message bundle="security" key="person.post"/>：
					</td>
					<td align="left">
						<html:select property="accountInfo.person.post" style="width:120">	
							<html:option value=""></html:option>
							<html:options collection="postList" property="PId" labelProperty="PName"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<bean:message key="page_itemcount"/>：				
					</td>
					<td align="left">			
						<html:select property="accountInfo.pageItemCount" style="width:120px" onchange="accountForm.submit();">	
							<html:option value="20">20</html:option>
							<html:option value="30">30</html:option>
							<html:option value="40">40</html:option>
							<html:option value="50">50</html:option>
							<html:option value="60">60</html:option>																	
						</html:select>	
					</td>
					<td colspan="6" align="right">							
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
			<input type="hidden" name="opType" value="add">
			<input type="hidden" name="id" value="">
			<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">						
				<logic:present name="accounts" scope="request">
					<thead>
						<tr>
							<td width="4%" align="center">
								<bean:message bundle="projectmanage" key="serial" />
							</td>
							<td width="10%" align="center">
								<bean:message bundle="security" key="account.name" />
							</td>
							<td width="10%" align="center">
								<bean:message bundle="security" key="person.code" />
							</td>
							<td width="10%" align="center">
								<bean:message bundle="security" key="person.name" />
							</td>
							<td align="center">
								<bean:message bundle="security" key="person.dept" />/
								<bean:message bundle="security" key="person.post" />
							</td>
							<td width="15%" align="center">
								<bean:message bundle="security" key="person.email" />
							</td>
							<td width="4%" align="center">
								<bean:message bundle="security" key="account.enabled" />
							</td>
							<td width="4%" align="center">
								<bean:message key="button.edit" />
							</td>							
							<td width="4%" align="center">
								<bean:message key="button.delete" />
							</td>
							<td width="4%" align="center">							
								<bean:message key="button.candel" />							
							</td>			
						</tr>
					</thead>

					<!--page offset start -->
					<bean:define id="pageCount" name="accountForm" property="accountInfo.pageItemCount"></bean:define>
					<%int itemNo = ((Integer) request.getAttribute("accountCount")).intValue();%>
					<pg:pager url="./accountManager.do" items="<%=itemNo%>" index="center" maxPageItems="<%=Integer.valueOf(pageCount.toString())%>" maxIndexPages="10" isOffset="<%= true %>" export="offset,currentPageNumber=pageNumber" scope="request">
						<%-- keep track of preference --%>								
						<pg:param name="method" value="searchAccount"/>

						<%-- save pager offset during form changes --%>
						<input type="hidden" id="pageOffset" name="pager.offset" value="<%= offset %>">
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
										<logic:equal name="account" property="flag" value="0">
											<logic:equal name="account" property="enabled" value="1">
												<input type="checkbox" checked onclick='chgAction(document.all.id,"<bean:write  name="account" property="id" />");chgAction(document.all.method,"enableAccount");accountForm.submit();'>
											</logic:equal>	
											<logic:equal name="account" property="enabled" value="0">
												<input type="checkbox" onclick='chgAction(document.all.id,"<bean:write  name="account" property="id" />");chgAction(document.all.method,"enableAccount");accountForm.submit();'>
											</logic:equal>
										</logic:equal>
									</td>
									<td align="center">
										<logic:equal name="account" property="flag" value="0">
											<a href="accountManager.do?method=loadAccount4Edit&id=<bean:write  name="account" property="id" />"> <img border="0" src="pages\images\icon\16x16\modify.gif"></a>
										</logic:equal>	
									</td>	
								
									<td align="center">
										<logic:equal name="account" property="flag" value="0">	
											<a href='javascript:if(confirm("确认要删除这条信息吗?")) {chgAction(document.all.id,"<bean:write  name="account" property="id" />");chgAction(document.all.method,"removeAccount");accountForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
										</logic:equal>
									</td>
									<td align="center">				
										<logic:equal name="account" property="flag" value="-1">						
											<a href='accountManager.do?method=cancelDeletedAccount&id=<bean:write name="account" property="id"/>'><img border="0" src="pages\images\icon\16x16\return.gif"></a>
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
			
			<table class="win" cellSpacing="0" cellPadding="0" width="100%" border="0">
				<tr>
					<td COLSPAN="2" ALIGN="RIGHT">
						<html:submit styleClass="addbutton" onclick="chgAction(document.all.method,'loadAccount4Add');chgAction(document.all.opType,'add');">
							&nbsp;
						</html:submit>						
					</td>
				</tr>
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
 	document.getElementById("pageOffset").value='0';
 }
 function chgAction(obj,str){
	obj.value=str;
 }

</script>
