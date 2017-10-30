<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>
<center>
	<bean:define id="title">
		<bean:message bundle="customer" key="customer.title" />
	</bean:define>

	<gui:window title="<%=title%>" prototype="boWindow" color="100%">			
		<html:form action="tenderProjectManage.do">
			<input type="hidden" name="method" value="confirmSupplier">	
			<input type="hidden" name="opType" value="<%=request.getParameter("opType") %>">	
			<table class="win" CELLPADDING="2" CELLSPACING="0" width="100%">					
				<tr align="right">
					<td>						
						<bean:message bundle="customer" key="customer.name" />：
						<html:text property="customerInfo.CName" size="10" maxlength="10" />
						<bean:message bundle="customer" key="customer.tradetype" />：
						<html:select property="customerInfo.CTradeType" style="width:120">	
							<html:option value=""></html:option>
							<html:options collection="tradeTypeList" property="ttId" labelProperty="ttName"/>
						</html:select>
						<html:submit styleClass="searchbutton" onclick="chgAction(document.all.method,'searchSupplier');initPageNo();">
							&nbsp;
						</html:submit>
						<html:submit styleClass="resetbutton" onclick="chgAction(document.all.method,'resetSearchSupplier');">
							&nbsp;
						</html:submit>
					</td>
				</tr>
			</table>
		
					
			<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">						
				
				<thead>
					<tr>
						<td width="10%" align="center">
						</td>
						<td width="25%" align="center">
							<bean:message bundle="customer" key="customer.name" />
						</td>
						<td width="25%" align="center">
							<bean:message bundle="customer" key="customer.address" />
						</td>
						<td width="8%" align="center">
							<bean:message bundle="customer" key="customer.phone" />
						</td>

						<td width="5%" align="center">
							<bean:message bundle="customer" key="customer.contactperson" />
						</td>
						<td width="8%" align="center">
							<bean:message bundle="customer" key="customer.cellphone" />
						</td>
						<td align="center">
							<bean:message bundle="customer" key="customer.mail" />
						</td>

					</tr>
				</thead>
					<logic:present name="customerList" scope="request">
					<!--page offset start -->
					<%int itemNo = ((Integer) request.getAttribute("customerCount")).intValue();%>
					<pg:pager url="./tenderProjectManage.do" items="<%=itemNo%>" index="center" maxPageItems="10" maxIndexPages="10" isOffset="<%= true %>" export="offset,currentPageNumber=pageNumber" scope="request">
						<%-- keep track of preference --%>						
						<pg:param name="method"/>
						<pg:param name="opType"/>
						
						<%-- save pager offset during form changes --%>
						<input type="hidden" name="pager.offset" value="<%= offset %>">
						<logic:iterate name="customerList" id="customer" indexId="i">
							<pg:item>
							  <tr>
								<% int a = i%2;request.setAttribute("a",a);%>
								<logic:equal name="a" value="0"><tr class="even"></logic:equal>
								<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
									<td>
										<input type="radio" name="id" value="<bean:write  name="customer" property="CId" />">										
									</td>
									<td>
										<bean:write name="customer" property="CName" />
									</td>									
									<td>
										<bean:write name="customer" property="CAddress"/>
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
									<td>
										<bean:write name="customer" property="CMail" />
									</td>

								</tr>
							</pg:item>
						</logic:iterate>
			
					<jsp:include page="../common/page.jsp" flush="true" />
				</pg:pager>
				<!-- page offset end -->
				</logic:present>
				<logic:notPresent name="customerList" scope="request">
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
					<td COLSPAN="2" ALIGN="RIGHT">						
						<html:submit styleClass="confirmbutton" onclick="return validSelectedIdForEdit();">
							&nbsp;
						</html:submit>
					</td>
				</tr>
			</table>
		</html:form>
		

	</gui:window>
</center>

<script language="JavaScript">

 function validSelectedIdForEdit(){
     var flag = 0;
     var count = 0;
     var a = document.all("id");
     if(a==null){
        alert("没有客户可以选择");
        return false;
     }   
     if(a.length==null){
        if(!a.checked) {
          alert("请选择客户!");
          return false;
        }    
     }else {
        for  (var  i=0;  i<a.length;  i++){  
	         if(a[i].checked==true){
	            flag=flag+1;
	         }
        }  
	     if(flag==0){
	         alert("请选择客户!");
	         return false;
	     }    
	     if(flag>1){
	         alert("请选择一个客户!");
	         return false;
	     }
     
     }
     
     return true;                      
 }
 
  
 function chgAction(obj,str){
	obj.value=str;
 }
 
  
 function initPageNo()
 {
 	document.getElementById("pager.offset").value='0'; 	
 }

</script>
