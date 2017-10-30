<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<script type="text/javascript" src="<c:url value='/pages/scripts/calendar.js'/>" charset="gbk"></script>

<center>
	<bean:define id="title">
		<bean:message bundle="attachment" key="list.title" />
	</bean:define>

	<gui:window title="<%=title%>" prototype="boWindow">			
		<html:form action="documentManage.do">

		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr>
				<td width="9%">&nbsp;</td><td width="16%">&nbsp;</td>
				<td width="9%">&nbsp;</td><td width="15%">&nbsp;</td>
				<td width="9%">&nbsp;</td><td width="15%">&nbsp;</td>
				<td width="9%">&nbsp;</td><td>&nbsp;</td>
			</tr>
			<tr height="30">
				<td align="right">
					<bean:message bundle="attachment" key="code" />：
				</td>
				<td>						
					<html:text property="searchInfo.paCode" size="18" maxlength="45"/>
				</td>
				<td align="right">
					<bean:message bundle="attachment" key="category" />：
				</td>
				<td>						
					<html:select property="searchInfo.paAttachmentCategory" style="width:120">	
						<html:option value=""></html:option>
						<html:options collection="attachmentCategoryList" property="acId" labelProperty="acName"/>
					</html:select>
				</td>								
			
				<td align="right">
					<bean:message bundle="attachment" key="filename" />：
				</td>
				<td>						
					<html:text property="searchInfo.paName" size="18" maxlength="100"/>
				</td>
	
				
				<td align="right">
					<bean:message bundle="attachment" key="submitdate" />：
				</td>
				<td >
					<html:text property="searchInfo.paSubmitDate" readonly="true" size="18" maxlength="32" onclick="SelectDate(this)"/>
				</td>
				
			</tr>	
						
			<tr align="right">
				<td align="right">
					<bean:message bundle="attachment" key="submitdepartment" />：
				</td>
				<td align="left">						
					<html:select property="searchInfo.paSubmitDepartment" style="width:120">	
						<html:option value=""></html:option>
						<html:options collection="departmentList" property="DId" labelProperty="DName"/>
					</html:select>
				</td>
				<td align="right">
					<bean:message key="order_item"/>:
				</td>
				<td align="left">						
					<html:select property="searchInfo.orderColumn" style="width:120" onchange="documentForm.submit();">	
						<html:option value=""></html:option>
						<html:option value="paCode"><bean:message bundle="attachment" key="code" /></html:option>
						<html:option value="paAttachmentCategory"><bean:message bundle="attachment" key="category" /></html:option>
						<html:option value="paName"><bean:message bundle="attachment" key="filename" /></html:option>
						<html:option value="paSubmitDate"><bean:message bundle="attachment" key="submitdate" /></html:option>
						<html:option value="paSubmitDepartment"><bean:message bundle="attachment" key="submitdepartment" /></html:option>
					</html:select>
					<html:checkbox property="searchInfo.ascFlag">↑</html:checkbox>
					<html:hidden property="searchInfo.ascFlag" value="false"/>
				</td>
				<td align="right">
					<bean:message key="page_itemcount"/>:
				</td>
				<td align="left">
					<html:select property="searchInfo.pageItemCount" style="width:120px" onchange="documentForm.submit();">	
						<html:option value="20">20</html:option>
						<html:option value="30">30</html:option>
						<html:option value="40">40</html:option>
						<html:option value="50">50</html:option>
						<html:option value="60">60</html:option>																	
					</html:select>
				</td>
				<td ALIGN="RIGHT">
					<html:submit styleClass="addbutton" onclick="chgAction(document.all.method,'createDocument');">
						&nbsp;
					</html:submit>
				</td>
				
				<td colspan="2">					
					<html:submit styleClass="searchbutton" onclick="initPageNo();">
						&nbsp;
					</html:submit>
					<html:submit styleClass="resetbutton" onclick="chgAction(document.all.method,'resetSearchDocument');">
						&nbsp;
					</html:submit>
				</td>
			</tr>					
			
		</table>
			
			<input type="hidden" name="method" value="searchDocument">	
			<input type="hidden" name="id" value="">		
			<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">			
				<thead>
					<tr>
						<td width="5%" align="center">
							<bean:message bundle="projectmanage" key="serial" />
						</td>
						<td width="10%" align="center">
							<bean:message bundle="attachment" key="code" />
						</td>
						<td align="center">
							<bean:message bundle="attachment" key="filename" />
						</td>
						<td width="10%" align="center">
							<bean:message bundle="attachment" key="category" />
						</td>
						<td width="10%" align="center">
							<bean:message bundle="attachment" key="submitdepartment" />
						</td>						
						<td width="10%" align="center">
							<bean:message bundle="attachment" key="submitdate" />
						</td>
						<td width="5%" align="center">
							<bean:message bundle="attachment" key="download" />
						</td>
						<td width="5%" align="center">
							<bean:message key="button.edit" />
						</td>
						<td width="5%" align="center">
							<bean:message key="button.delete" />
						</td>
					</tr>
				</thead>
					<logic:present name="documentList" scope="request">
					<!--page offset start -->
					<bean:define id="pageCount" name="documentForm" property="searchInfo.pageItemCount"></bean:define>
					<bean:define id="itemNo" name="documentCount"></bean:define>
					<pg:pager url="./documentManage.do" items="<%=Integer.valueOf(itemNo.toString())%>" index="center" maxPageItems="<%=Integer.valueOf(pageCount.toString())%>" maxIndexPages="10" isOffset="<%= true %>" export="offset,currentPageNumber=pageNumber" scope="request">
						<%-- keep track of preference --%>						
						<pg:param name="method" value="searchDocument"/>

						<%-- save pager offset during form changes --%>
						<input type="hidden" name="pager.offset" value="<%= offset %>">
						<logic:iterate name="documentList" id="d" indexId="i">
							<pg:item>
							  <tr>
								<% int a = i%2;request.setAttribute("a",a);%>
								<logic:equal name="a" value="0"><tr class="even"></logic:equal>
								<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
									<td align="center"><%=i+1 %></td>
									<td>
										<bean:write name="d" property="paCode" />
									</td>
									<td>
										<bean:write name="d" property="paName" />
									</td>									
									<td>
										<bean:write name="d" property="category.acName"/>
									</td>
									<td>
										<logic:notEmpty name="d" property="submitDepartment">
											<bean:write name="d" property="submitDepartment.DName" />
										</logic:notEmpty>
									</td>									
									<td>
										<bean:write name="d" property="paSubmitDate" />
									</td>			

									<td align="center"><a href='documentManage.do?method=downloadAttachment&id=<bean:write name="d" property="paId"/>'><img border="0" src="pages\images\icon\16x16\make-index.gif"></a></td>
									
									<td align="center">
										<logic:equal name="d" property="paCreateUser" value='<%=(String)request.getSession().getAttribute("account") %>'>
											<a href='documentManage.do?method=modifyDocument&id=<bean:write name="d" property="paId"/>'><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
										</logic:equal>
									</td>
									<td align="center">
										<logic:equal name="d" property="paCreateUser" value='<%=(String)request.getSession().getAttribute("account") %>'>
											<a href='javascript:if(confirm("确认要删除这条信息吗?")) {chgAction(document.all.id,"<bean:write name="d" property="paId"/>");chgAction(document.all.method,"deleteDocument");documentForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
										</logic:equal>
									</td>
								</tr>
							</pg:item>
						</logic:iterate>
			
					<jsp:include page="../common/page.jsp" flush="true" />
				</pg:pager>
				<!-- page offset end -->
				</logic:present>
				<logic:notPresent name="documentList" scope="request">
					<tr>
						<td COLSPAN="8">
							<bean:message key="noRecord" />
						</td>
					</tr>
				</logic:notPresent>	
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
