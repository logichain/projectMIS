<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>
<center>
	<bean:define id="title">
		<bean:message bundle="notice" key="title.search" />
	</bean:define>

	<gui:window title="<%=title%>" prototype="boWindow">			
		<html:form action="noticeManage.do">
			<table class="win" CELLPADDING="2" CELLSPACING="0" width="100%">	
				<tr>
					<td width="9%">&nbsp;</td><td width="16%">&nbsp;</td>
					<td width="9%">&nbsp;</td><td width="15%">&nbsp;</td>
					<td width="9%">&nbsp;</td><td width="15%">&nbsp;</td>
					<td width="9%">&nbsp;</td><td>&nbsp;</td>
				</tr>
				<tr height="30">
									
					<td align="right">
						<bean:message bundle="notice" key="notice.theme" />：
					</td>
					<td>
						<html:text property="searchInfo.inTheme" size="18" maxlength="18" />
					</td>
					<td align="right">
						<bean:message bundle="notice" key="release.dept" />：
					</td>
					<td>
						<html:select property="searchInfo.inDept" style="width:128">	
							<html:option value=""></html:option>
							<html:options collection="departmentList" property="DId" labelProperty="DName"/>
						</html:select>
					</td>
	
					<td align="right">
						<bean:message bundle="notice" key="notice.type" />：
					</td>
					<td>
						<html:select property="searchInfo.inType" style="width:128">	
							<html:option value=""></html:option>
							<html:options collection="noticeTypeList" property="ntId" labelProperty="ntName"/>
						</html:select>
					</td>
					
					<td align="right">
						<bean:message bundle="notice" key="release.date" />：
					</td>
					<td>
						<html:text property="searchInfo.inReleaseDate" readonly="true" size="18" maxlength="32" onclick="SelectDate(this);"/>
					</td>
				</tr>				
				<tr align="right">
					<td align="right">
						<bean:message key="order_item"/>:
					</td>
					<td align="left">											
						<html:select property="searchInfo.orderColumn" style="width:120" onchange="noticeForm.submit();">	
							<html:option value=""></html:option>		
							<html:option value="inTheme"><bean:message bundle="notice" key="notice.theme" /></html:option>
							<html:option value="inDept"><bean:message bundle="notice" key="release.dept" /></html:option>
							<html:option value="inType"><bean:message bundle="notice" key="notice.type" /></html:option>
							<html:option value="inReleaseDate"><bean:message bundle="notice" key="release.date" /></html:option>
						</html:select>
						<html:checkbox property="searchInfo.ascFlag">↑</html:checkbox>
						<html:hidden property="searchInfo.ascFlag" value="false"/>	
					</td>	
					<td align="right"><bean:message key="page_itemcount"/>:</td>
					<td align="left">
						<html:select property="searchInfo.pageItemCount" style="width:120px" onchange="noticeForm.submit();">	
							<html:option value="20">20</html:option>
							<html:option value="30">30</html:option>
							<html:option value="40">40</html:option>
							<html:option value="50">50</html:option>
							<html:option value="60">60</html:option>																	
						</html:select>
					</td>
					<td COLSPAN="2" ALIGN="RIGHT">
						<html:submit styleClass="addbutton" onclick="chgAction(document.all.method,'createNotice');">
							&nbsp;
						</html:submit>
					</td>
					<td colspan="2">
						<html:submit styleClass="searchbutton" onclick="initPageNo();">
							&nbsp;
						</html:submit>
						<html:submit styleClass="resetbutton" onclick="chgAction(document.all.method,'resetSearchNotice');">
							&nbsp;
						</html:submit>
					</td>
				</tr>
			</table>

			<input type="hidden" name="method" value="searchNotice">	
			<input type="hidden" name="id" value="">	
			<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">			
				<thead>
					<tr>
						<td width="4%" align="center">
							<bean:message bundle="projectmanage" key="serial" />
						</td>
						<td align="center">
							<bean:message bundle="notice" key="notice.theme" />
						</td>
						<td width="10%" align="center">
							<bean:message bundle="notice" key="release.date" />
						</td>
						<td width="10%" align="center">
							<bean:message bundle="notice" key="notice.type" />
						</td>
						<td width="15%" align="center">
							<bean:message bundle="projectmanage" key="project.attachment" />
						</td>		
						<td width="4%" align="center">
							查阅
						</td>		
						<td width="4%" align="center">														
							<bean:message key="button.edit" />							
						</td>						
						<logic:equal name="accountPerson" property="id" value="0">	
							<td width="4%" align="center">						
								<bean:message key="button.candel" />
							</td>							
						</logic:equal>
						
						<td width="4%" align="center">
							<bean:message key="button.delete" />
						</td>						
					</tr>
				</thead>
					<logic:present name="noticeList" scope="request">
					<!--page offset start -->
					<bean:define id="pageCount" name="noticeForm" property="searchInfo.pageItemCount"></bean:define>
					<%int itemNo = ((Integer) request.getAttribute("noticeCount")).intValue();%>
					<pg:pager url="./noticeManage.do" items="<%=itemNo%>" index="center" maxPageItems="<%=Integer.valueOf(pageCount.toString())%>" maxIndexPages="10" isOffset="<%= true %>" export="offset,currentPageNumber=pageNumber" scope="request">
						<%-- keep track of preference --%>						
						<pg:param name="method" value="searchNotice"/>

						<%-- save pager offset during form changes --%>
						<input type="hidden" name="pager.offset" value="<%= offset %>">
						<logic:iterate name="noticeList" id="notice" indexId="i">
							<pg:item>
							  <tr>
								<% int a = i%2;request.setAttribute("a",a);%>
								<logic:equal name="a" value="0"><tr class="even"></logic:equal>
								<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
									<td align="center">										
										<%=i+1 %>
									</td>
									
									<td>
										<bean:write name="notice" property="inTheme" />
									</td>									
									<td>
										<bean:write name="notice" property="inReleaseDate"/>
									</td>
									<td>
										<logic:notEmpty name="notice" property="inType">
											<bean:write name="notice" property="noticeType.ntName" />
										</logic:notEmpty>
									</td>
									<td>									
										<logic:iterate id="am" name="notice" property="attachmentList" indexId="j">
											<a href="noticeManage.do?method=downloadAttachment&id=<bean:write name='am' property='naId'/>" title="<bean:write name="am" property="naLocalUrl"/>">
												<bean:write name="am" property="naName"/>；
											</a>												
										</logic:iterate>			
									</td>
									<td align="center">									
										<a href="javascript:openDialog('noticeManage.do?method=displayNotice&id=<bean:write name="notice" property="inId"/>',700,400)"><img border="0" src="pages\images\icon\16x16\display.gif"></a>
									</td>
									<td align="center">									
										<logic:equal name="notice" property="inCreateUser" value='<%=(String)request.getSession().getAttribute("account") %>'>
											<a href='noticeManage.do?method=modifyNotice&id=<bean:write name="notice" property="inId"/>'><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
										</logic:equal>
									</td>
									
									<logic:equal name="accountPerson" property="id" value="0">
										<td align="center">	
											<logic:equal name="notice" property="inFlag" value="-1">
												<a href='noticeManage.do?method=cancelDeletedNotice&id=<bean:write name="notice" property="inId"/>'><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
											</logic:equal>
										</td>
									</logic:equal>			
										
									<td align="center">
										<logic:equal name="notice" property="inCreateUser" value='<%=(String)request.getSession().getAttribute("account") %>'>
											<logic:notEqual name="notice" property="inFlag" value="-1">
												<a href='javascript:if(confirm("确认要删除这条信息吗?")) {chgAction(document.all.id,"<bean:write name="notice" property="inId"/>");chgAction(document.all.method,"deleteNotice");noticeForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
											</logic:notEqual>
										</logic:equal>
										<logic:notEqual name="notice" property="inCreateUser" value='<%=(String)request.getSession().getAttribute("account") %>'>
											<logic:equal name="accountPerson" property="id" value="0">
												<logic:notEqual name="notice" property="inFlag" value="-1">
													<a href='javascript:if(confirm("确认要删除这条信息吗?")) {chgAction(document.all.id,"<bean:write name="notice" property="inId"/>");chgAction(document.all.method,"deleteNotice");noticeForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
												</logic:notEqual>
											</logic:equal>
										</logic:notEqual>
									</td>
								</tr>
							</pg:item>
						</logic:iterate>
			
					<jsp:include page="../common/page.jsp" flush="true" />
				</pg:pager>
				<!-- page offset end -->
				</logic:present>
				<logic:notPresent name="noticeList" scope="request">
					<tr>
						<td COLSPAN="7">
							<bean:message key="noRecord" />
						</td>
					</tr>
				</logic:notPresent>	
			</table>
						
			<table class="win" cellSpacing="0" cellPadding="0" width="100%" border="0">
				<tr><td>&nbsp;</td></tr>	
				<tr>
					
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
 
 function openDialog(loadpos,WWidth,WHeight)//Lock   Size 
{   
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);   
	var WTop = Math.ceil((window.screen.height - WHeight) / 2); 
	var features = 'width=' + WWidth + 'px,' +	'height=' + WHeight + 'px,' + 'left=' + WLeft + 'px,' + 'top=' + WTop + 'px'; 
		
	WinOP = window.open(loadpos,"_blank",features); 
	WinOP.focus();	   
}

</script>
