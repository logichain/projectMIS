<%@ include file="../common/include.jsp"%>
<%@page pageEncoding="GBK"%>

<bean:define id="title">
	<bean:message bundle="customer" key="customer.title" />
</bean:define>


<center>
	<gui:window title="<%=title%>" prototype="boWindow">
		<html:form action="tenderManage.do" onsubmit="return checkFormValidate();">
			<html:errors />

			<input type="hidden" name="method" value="saveCustomer">
			<input type="hidden" name="optype" value="<%=request.getParameter("optype") %>">
			<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
				<tr>
					<td width="12%">&nbsp;</td><td width="20%">&nbsp;</td>
					<td width="12%">&nbsp;</td><td width="20%">&nbsp;</td>
					<td width="12%">&nbsp;</td><td>&nbsp;</td>
				</tr>
				<tr>					
					<td align="right">
						<bean:message bundle="customer" key="customer.shortname" />：
					</td>
					<td>
						<html:text property="customerInfo.CShortName" size="18" maxlength="45" />
					</td>
					<td align="right">
						<bean:message bundle="customer" key="customer.type" />：
					</td>
					<td>
						<html:select property="customerInfo.CType" style="width:120">	
							<html:option value=""></html:option>
							<html:options collection="typeList" property="ctId" labelProperty="ctName"/>
						</html:select>*
					</td>
					
				</tr>
								
				<tr>
					<td align="right">
						<bean:message bundle="customer" key="customer.name" />：
					</td>
					<td colspan="3">
						<html:text property="customerInfo.CName" size="62" maxlength="100" />*
					</td>
					<td align="right">
						<bean:message bundle="customer" key="customer.tradetype" />：
					</td>
					<td>
						<html:select property="customerInfo.CTradeType" style="width:120">	
							<html:option value=""></html:option>
							<html:options collection="tradeTypeList" property="ttId" labelProperty="ttName"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<bean:message bundle="customer" key="customer.address" />：
					</td>
					<td colspan="3">
						<html:text property="customerInfo.CAddress" size="62" maxlength="200" />*
					</td>				
					<td align="right">
						<bean:message bundle="customer" key="customer.postcode" />：
					</td>
					<td >
						<html:text property="customerInfo.CPostcode" size="18" maxlength="10" />
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<bean:message bundle="customer" key="customer.phone" />：
					</td>
					<td>
						<html:text property="customerInfo.CPhone" size="18" maxlength="12" />*
					</td>
					<td align="right">
						<bean:message bundle="customer" key="customer.cellphone" />：
					</td>
					<td>
						<html:text property="customerInfo.CCellphone" size="18" maxlength="12" />
					</td>
					<td align="right">
						<bean:message bundle="customer" key="customer.fax" />：
					</td>
					<td>
						<html:text property="customerInfo.CFax" size="18" maxlength="12" />
					</td>
						
				</tr>
				<tr>
					<td align="right">
						<bean:message bundle="customer" key="customer.saleman" />：
					</td>
					<td>
						<html:text property="customerInfo.saleman.person.personName" size="18" maxlength="100" readonly="true" style="background-color:LightGray;"/>		
						<html:button property="" onclick="openDialog('tenderManage.do?method=searchAccount',800,420);">...</html:button>										
					</td>					
					<td align="right">
						<bean:message bundle="customer" key="customer.contactperson" />：
					</td>
					<td>
						<html:text property="customerInfo.CContactPerson" size="18" maxlength="10" />
					</td>
					
				</tr>
				<tr>	
					<td align="right">
						<bean:message bundle="customer" key="customer.mail" />：
					</td>
					<td colspan="3">
						<html:text property="customerInfo.CMail" size="62" maxlength="45" />
					</td>
					
				</tr>
				<tr>
						
					<td align="right">
						<bean:message bundle="customer" key="customer.nature" />：
					</td>
					<td>
						<html:select property="customerInfo.CNature" style="width:120">	
							<html:option value=""></html:option>
							<html:options collection="natureList" property="cnId" labelProperty="cnName"/>
						</html:select>
					</td>
					<td align="right">
						<bean:message bundle="customer" key="customer.scale" />：
					</td>
					<td>
						<html:text property="customerInfo.CScale" size="18" maxlength="10" />
					</td>
				
					<td align="right">
						<bean:message bundle="customer" key="customer.organizationcode" />：
					</td>
					<td>
						<html:text property="customerInfo.COrganizationCode" size="18" maxlength="10" />
					</td>
					
				</tr>
				
				<tr>
					<td align="right">
						<bean:message bundle="customer" key="customer.remarks" />：
					</td>
					<td colspan="5">
						<html:textarea property="customerInfo.CRemarks" cols="70" rows="3" />
					</td>
					
				</tr>
				
				<tr>
					<td align="right">&nbsp;</td>
				</tr>						
				<tr>
					<td colspan="6" align="right">
						<html:submit styleClass="savebutton">
							&nbsp;
						</html:submit>

						<html:reset styleClass="resetbutton">
							&nbsp;
						</html:reset>
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
  
function chgFormOnsubmit(str){  	 	
	customerForm.onsubmit="function onsubmit(){" + str + "}";	
 }
 
function submitForm()
{
	chgFormOnsubmit('return true;');
	chgAction(document.all.method,'refreshInput');
	customerForm.submit();
}

function checkFormValidate()
{	 		
	var type = document.getElementById("customerInfo.CType").value;	
	if(type == "")
	{
		alert("客户类型不能为空");
		return false;
	}
	
	var name = document.getElementById("customerInfo.CName").value;	
	if(name == "")
	{
		alert("名称不能为空");
		return false;
	}
	
	var addr = document.getElementById("customerInfo.CAddress").value;	
	if(addr == "")
	{
		alert("地址不能为空");
		return false;
	}
	
	var phone = document.getElementById("customerInfo.CPhone").value;	
	if(phone == "")
	{
		alert("电话不能为空");
		return false;
	}
	 	
	return true;
}


function openDialog(loadpos,WWidth,WHeight)//Lock   Size 
{   
	submitForm();
	
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);   
	var WTop = Math.ceil((window.screen.height - WHeight) / 2); 
	var features = 'width=' + WWidth + 'px,' +	'height=' + WHeight + 'px,' + 'left=' + WLeft + 'px,' + 'top=' + WTop + 'px'; 
		
	WinOP = window.open(loadpos,"_blank",features); 
	WinOP.focus();	   
}

</script>
