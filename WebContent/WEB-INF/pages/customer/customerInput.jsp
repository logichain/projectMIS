<%@ include file="../common/include.jsp"%>
<%@page pageEncoding="GBK"%>

<bean:define id="title">
	<bean:message bundle="customer" key="customer.title" />
</bean:define>


<center>
	<gui:window title="<%=title%>" prototype="boWindow">
		<html:form action="customerManage.do" onsubmit="return validateCustomerForm(this);">
			<html:errors />

			<input type="hidden" name="method" value="saveCustomer">
			<input type="hidden" name="optype" value="<%=request.getParameter("optype") %>">
			<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
				<tr>
					<td width="9%">&nbsp;</td><td width="15%">&nbsp;</td>
					<td width="9%">&nbsp;</td><td width="15%">&nbsp;</td>
					<td width="9%">&nbsp;</td><td width="15%">&nbsp;</td>
					<td width="9%">&nbsp;</td><td>&nbsp;</td>
				</tr>
				<tr>
				
					<td align="right">
						<bean:message bundle="customer" key="customer.shortname" />£º
					</td>
					<td colspan="7">
						<html:text property="customerInfo.CShortName" size="62" maxlength="45" />
					</td>
				</tr>
				<tr>	
					<td align="right">
						<bean:message bundle="customer" key="customer.type" />£º
					</td>
					<td>
						<html:select property="customerInfo.CType" style="width:120">								
							<html:options collection="typeList" property="ctId" labelProperty="ctName"/>
						</html:select>*
					</td>
					<td align="right">
						<bean:message bundle="customer" key="customer.tradetype" />£º
					</td>
					<td>
						<html:select property="customerInfo.CTradeType" style="width:120">								
							<html:options collection="tradeTypeList" property="ttId" labelProperty="ttName"/>
						</html:select>
					</td>
				</tr>
				
				<tr>
					<td align="right">
						<bean:message bundle="customer" key="customer.name" />£º
					</td>
					<td colspan="3">
						<html:text property="customerInfo.CName" size="62" maxlength="100" />*
					</td>
				</tr>
				<tr>	
					<td align="right">
						<bean:message bundle="customer" key="customer.address" />£º
					</td>
					<td colspan="3">
						<html:text property="customerInfo.CAddress" size="62" maxlength="200" />*
					</td>				
					
				</tr>
				
				<tr>
					<td align="right">
						<bean:message bundle="customer" key="customer.phone" />£º
					</td>
					<td>
						<html:text property="customerInfo.CPhone" size="18" maxlength="12" />*
					</td>
					<td align="right">
						<bean:message bundle="customer" key="customer.cellphone" />£º
					</td>
					<td>
						<html:text property="customerInfo.CCellphone" size="18" maxlength="12" />
					</td>
					<td align="right">
						<bean:message bundle="customer" key="customer.fax" />£º
					</td>
					<td>
						<html:text property="customerInfo.CFax" size="18" maxlength="12" />
					</td>
					
				</tr>
				<tr>
					<td align="right">
						<bean:message bundle="customer" key="customer.saleman" />£º
					</td>
					<td>
						<html:text property="customerInfo.saleman.person.personName" size="18" maxlength="100" readonly="true" style="background-color:LightGray;"/>		
						<html:button property="" onclick="openDialog('customerManage.do?method=searchAccount',800,420);">...</html:button>										
					</td>					
					<td align="right">
						<bean:message bundle="customer" key="customer.contactperson" />£º
					</td>
					<td>
						<html:text property="customerInfo.CContactPerson" size="18" maxlength="10" />
					</td>
					
					<td align="right">
						<bean:message bundle="customer" key="customer.postcode" />£º
					</td>
					<td >
						<html:text property="customerInfo.CPostcode" size="18" maxlength="10" />
					</td>	
				</tr>
				<tr>			
					<td align="right">
						<bean:message bundle="customer" key="customer.mail" />£º
					</td>
					<td colspan="3">
						<html:text property="customerInfo.CMail" size="62" maxlength="45" />
					</td>
					
				</tr>
				<tr>
						
					<td align="right">
						<bean:message bundle="customer" key="customer.nature" />£º
					</td>
					<td>
						<html:select property="customerInfo.CNature" style="width:120">	
							<html:option value=""></html:option>
							<html:options collection="natureList" property="cnId" labelProperty="cnName"/>
						</html:select>
					</td>
					<td align="right">
						<bean:message bundle="customer" key="customer.scale" />£º
					</td>
					<td>
						<html:text property="customerInfo.CScale" size="18" maxlength="10" />
					</td>
				
					<td align="right">
						<bean:message bundle="customer" key="customer.organizationcode" />£º
					</td>
					<td>
						<html:text property="customerInfo.COrganizationCode" size="18" maxlength="10" />
					</td>
					
				</tr>
				
				<tr>
					<td align="right">
						<bean:message bundle="customer" key="customer.remarks" />£º
					</td>
					<td colspan="7">
						<html:textarea property="customerInfo.CRemarks" cols="109" rows="3" />
					</td>
					
				</tr>
				
				<tr>
					<td align="right">&nbsp;</td>
				</tr>				
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="project.attachment" />£º
			</td>
			<td colspan="3">
				<table width="100%"><tr><td width="60%">
				
					<logic:iterate id="am" name="customerForm" property="customerInfo.attachmentList" indexId="i">
					
					<logic:notEqual name='am' property='caFlag' value="-1">
						<logic:empty name='am' property='caId'>
							<span title="<bean:write name="am" property="caLocalUrl"/>">
								<bean:write name="am" property="caName"/>
							</span>
							<a href="customerManage.do?method=deleteAttachmentByIndex&index=<%=i %>"><img border="0" src="pages\images\icon\16x16\delete.gif"></a>£»
						</logic:empty>
						<logic:notEmpty name='am' property='caId'>
							<a href="customerManage.do?method=downloadAttachment&id=<bean:write name='am' property='caId'/>">
								<bean:write name="am" property="caName"/>
							</a>
							<a href="cusotmerManage.do?method=deleteAttachment&id=<bean:write name='am' property='caId'/>"><img border="0" src="pages\images\icon\16x16\delete.gif"></a>£»
						</logic:notEmpty>
					</logic:notEqual>	
						
				</logic:iterate>
			
			</td>
			<td>	
				<html:button property="" onclick="openDialog('customerManage.do?method=addAttachment',500,180);" styleClass="selectFileButton">&nbsp;</html:button>
				</td></tr>
				</table>
			</td>	
		</tr>		
				<tr>
					<td colspan="8" align="right">
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


<html:javascript formName="customerForm" dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript" src="<html:rewrite forward='staticjavascript'/>"></script>

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
