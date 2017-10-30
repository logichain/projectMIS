<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>
<center>
	<bean:define id="title">
		<bean:message bundle="customer" key="customer.title" />
	</bean:define>

	<gui:window title="<%=title%>" prototype="boWindow">			
		<html:form action="customerManage.do">
			<table class="win" CELLPADDING="2" CELLSPACING="0" width="100%">					
				<tr align="right">
					<td align="right" width="10%">						
						<bean:message bundle="customer" key="customer.name" />£º
					</td>
					<td align="left" width="20%">
						<html:text property="searchInfo.CName" size="18" maxlength="45" />
					</td>	
					<td align="right" width="10%">
						<bean:message bundle="customer" key="customer.type" />£º
					</td>
					<td align="left" width="20%">
						<html:select property="searchInfo.CType" style="width:120">	
							<html:option value=""></html:option>
							<html:options collection="typeList" property="ctId" labelProperty="ctName"/>
						</html:select>
					</td>	
					<td align="right" width="10%">
						<bean:message bundle="customer" key="customer.tradetype" />£º
					</td>
					<td align="left">
						<html:select property="searchInfo.CTradeType" style="width:120">	
							<html:option value=""></html:option>
							<html:options collection="tradeTypeList" property="ttId" labelProperty="ttName"/>
						</html:select>
					</td>	
				</tr>
				<tr>		
					<td align="right">
						<bean:message key="order_item"/>:
					</td>		
					<td align="left">								
						<html:select property="searchInfo.orderColumn" style="width:120" onchange="customerForm.submit();">	
							<html:option value=""></html:option>		
							<html:option value="CName"><bean:message bundle="customer" key="customer.name" /></html:option>
							<html:option value="CType"><bean:message bundle="customer" key="customer.type" /></html:option>
							<html:option value="CTradeType"><bean:message bundle="customer" key="customer.tradetype" /></html:option>
						</html:select>
						<html:checkbox property="searchInfo.ascFlag">¡ü</html:checkbox>
						<html:hidden property="searchInfo.ascFlag" value="false"/>	
					</td>
				
					<td align="right"><bean:message key="page_itemcount"/>:</td>
					<td align="left">
						<html:select property="searchInfo.pageItemCount" style="width:120px" onchange="customerForm.submit();">	
							<html:option value="20">20</html:option>
							<html:option value="30">30</html:option>
							<html:option value="40">40</html:option>
							<html:option value="50">50</html:option>
							<html:option value="60">60</html:option>																	
						</html:select>
					</td>																
					
					<td align="right" colspan="2">	
						<html:submit styleClass="searchbutton" onclick="initPageNo();">
							&nbsp;
						</html:submit>
						<html:submit styleClass="resetbutton" onclick="chgAction(document.all.method,'resetSearchQualifiedCustomer');">
							&nbsp;
						</html:submit>
					</td>
				</tr>
			</table>

			<input type="hidden" name="method" value="searchQualifiedCustomer">	
			<input type="hidden" name="id" value="">	
			<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">			
				<thead>
					<tr>
						<td width="4%" align="center">
							<bean:message bundle="projectmanage" key="serial" />
						</td>
						
						<td align="center">
							<bean:message bundle="customer" key="customer.name" />
						</td>
						<td width="25%" align="center">
							<bean:message bundle="customer" key="customer.address" />
						</td>
						<td width="6%" align="center">
							<bean:message bundle="customer" key="customer.type" />
						</td>
						<td width="6%" align="center">
							<bean:message bundle="customer" key="customer.tradetype" />
						</td>
						<td width="8%" align="center">
							<bean:message bundle="customer" key="customer.phone" />
						</td>

						<td width="8%" align="center">
							<bean:message bundle="customer" key="customer.contactperson" />
						</td>
						<td width="9%" align="center">
							<bean:message bundle="customer" key="customer.cellphone" />
						</td>
						<td width="4%" align="center">
							<bean:message bundle="checktask" key="task.read" />
						</td>
						<logic:equal name="accountPerson" property="projectManageSecretary" value="true">
							<td width="6%" align="center">
								½µÎª±¸Ñ¡
							</td>
						</logic:equal>
					</tr>
				</thead>
					<logic:present name="customerList" scope="request">
					<!--page offset start -->
					<bean:define id="pageCount" name="customerForm" property="searchInfo.pageItemCount"></bean:define>
					<%int itemNo = ((Integer) request.getAttribute("customerCount")).intValue();%>
					<pg:pager url="./customerManage.do" items="<%=itemNo%>" index="center" maxPageItems="<%=Integer.valueOf(pageCount.toString())%>" maxIndexPages="10" isOffset="<%= true %>" export="offset,currentPageNumber=pageNumber" scope="request">
						<%-- keep track of preference --%>						
						<pg:param name="method" value="searchQualifiedCustomer"/>

						<%-- save pager offset during form changes --%>
						<input type="hidden" name="pager.offset" value="<%= offset %>">
						<logic:iterate name="customerList" id="customer" indexId="i">
							<pg:item>
							  <tr>
								<% int a = i%2;request.setAttribute("a",a);%>
								<logic:equal name="a" value="0"><tr class="even"></logic:equal>
								<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
									<td align="center">										
										<%=i+1 %>
									</td>
									
									<td>
										<bean:write name="customer" property="CName" />
									</td>									
									<td>
										<bean:write name="customer" property="CAddress"/>
									</td>
									<td>
										<bean:write name="customer" property="customerType.ctName" />
									</td>
									<td>
										<logic:notEmpty name="customer" property="CTradeType">
											<bean:write name="customer" property="tradeType.ttName" />
										</logic:notEmpty>
									</td>
									<td>
										<bean:write name="customer" property="CPhone" />
									</td>
									<td>
										<bean:write name="customer" property="CContactPerson" />
									</td>
									<td>
										<bean:write name="customer" property="CCellphone" />
									</td>
									<td align="center">
										<a href='javascript:openDialog("customerManage.do?method=displayCustomerInfo&id=<bean:write name="customer" property="CId"/>",800,300);'><img border="0" src="pages\images\icon\16x16\display.gif"></a>
									</td>
									<logic:equal name="accountPerson" property="projectManageSecretary" value="true">										
										<td align="center">
											<logic:equal name="customer" property="CFlag" value="1">
												<a href='customerManage.do?method=alternativeCustomer&id=<bean:write name="customer" property="CId"/>'><img border="0" src="pages\images\icon\16x16\return.gif"></a>
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
				<logic:notPresent name="customerList" scope="request">
					<tr>
						<td COLSPAN="10">
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
  function openDialog(loadpos,WWidth,WHeight)//Lock   Size 
  {   	
  	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);   
  	var WTop = Math.ceil((window.screen.height - WHeight) / 2); 
  	var features = 'width=' + WWidth + 'px,' +	'height=' + WHeight + 'px,' + 'left=' + WLeft + 'px,' + 'top=' + WTop + 'px'; 
  		
  	WinOP = window.open(loadpos,"_blank",features); 
  	WinOP.focus();   
  }
</script>
