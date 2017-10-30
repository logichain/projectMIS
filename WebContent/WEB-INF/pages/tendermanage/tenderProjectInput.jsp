<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<bean:define id="title">
	<bean:message bundle="tendermanage" key="tender.input" />
</bean:define>


<center>
<gui:window title='<%=title%>' prototype="boWindow">
	<html:form action="tenderManage.do" onsubmit="return validateTenderInputForm(this);">
		<html:errors />
		
		<input type="hidden" name="method" value="saveProject">	
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
				<tr><td>&nbsp;</td></tr>	
				<tr><td width="70%">&nbsp;</td>						
					<td align="center" width="12%">
						<html:submit styleClass="savebutton" onclick="chgAction(document.all.method,'saveTenderProject');" style="cursor: hand;">
							&nbsp;
						</html:submit>						
					</td>				
				</tr>
			</table>	
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr>
				<td width="10%"></td>
				<td width="15%"></td>				
				<td width="9%"></td>
				<td width="15%"></td>
				<td width="10%"></td>
				<td width="16%"></td>
				<td width="8%"></td>
				<td></td>
			</tr>
			<tr>
				<td align="right">
					<bean:message bundle="tendermanage" key="tender.projectName" />£º
				</td>
				<td colspan="3">
					<html:text property="tenderInfo.tpName" size="65" maxlength="100"/>*
				</td>
			</tr>
			<tr>	
				<td align="right">
					<bean:message bundle="projectmanage" key="project.status" />£º
				</td>
				<td>										
					<html:select property="tenderInfo.tpStatus" style="width:125">							
						<html:options collection="statusList" property="psId" labelProperty="psName"/>
					</html:select>								
				</td>
				<td align="right">
					<bean:message bundle="projectmanage" key="project.dept" />£º
				</td>
				<td>										
					<html:select property="tenderInfo.tpDept" style="width:125">							
						<html:options collection="deptList" property="DId" labelProperty="DName"/>
					</html:select>								
				</td>
			</tr>
			
			<tr>
				<td align="right">
					<bean:message bundle="tendermanage" key="tender.clientName" />£º 
				</td>
				<td colspan="3">						
					<html:text property="tenderInfo.customer.CName" size="65" maxlength="100" readonly="true" style="background-color:LightGray;"/>*
					<html:button property="" onclick="openDialog('tenderManage.do?method=searchCustomer',800,420);">...</html:button>
				</td>		
						
			</tr>
			<tr>	
				<td align="right">
					<bean:message bundle="tendermanage" key="tender.manager" />£º
				</td>
				<td>						
					<html:text property="tenderInfo.manager.person.personName" size="18" maxlength="45" readonly="true" style="background-color:LightGray;"/>*
					<html:button property="" onclick="openDialog('tenderManage.do?method=searchAccount&type=tenderInputManager',800,420);">...</html:button>
				</td>
				<td align="right">
					<bean:message bundle="approvalrecord" key="market_manager" />£º
				</td>
				<td>						
					<html:text property="tenderInfo.marketManager.person.personName" size="18" maxlength="45" readonly="true" style="background-color:LightGray;"/>*
					<html:button property="" onclick="openDialog('tenderManage.do?method=searchAccount&type=tenderInputMarketManager',800,420);">...</html:button>
				</td>
			</tr>
			
			<tr>
				<td align="right">
					<bean:message bundle="tendermanage" key="tender.projectAddress" />£º
				</td>
				<td colspan="3">
					<html:text property="tenderInfo.tpAddress" size="65" maxlength="100"/>*
				</td>
				<td align="right">
					<bean:message bundle="projectmanage" key="project.contractamount" />£º
				</td>
				<td>
					<html:text property="tenderInfo.tpContractAmount" size="18" maxlength="100"/><bean:message bundle="projectmanage" key="project.contractamountunit" />					
				</td>
			</tr>
			<tr>			
				<td align="right">
					<bean:message bundle="projectmanage" key="project.begindate" />£º 
				</td>
				<td >						
					<html:text property="tenderInfo.tpBeginDate" readonly="true" size="18" maxlength="32" onclick="SelectDate(this)" onchange="submitForm();"/>*
				</td>				
				<td align="right">
					<bean:message bundle="projectmanage" key="project.enddate" />£º 
				</td>
				<td >						
					<html:text property="tenderInfo.tpEndDate" readonly="true" size="18" maxlength="32" onclick="SelectDate(this)" onchange="submitForm();"/>*
				</td>
	
				<td align="right">
					<bean:message bundle="projectmanage" key="project.workperiod" />£º
				</td>
				<td>
					<html:text property="tenderInfo.tpWorkdayCount" size="18" maxlength="100" readonly="true" style="background-color:LightGray;"/><bean:message bundle="projectmanage" key="project.workperiodunit" />					
				</td>
			</tr>	
			<tr>
				<td align="right">
					<bean:message bundle="tendermanage" key="tender.projectDescription" />£º
				</td>
				<td colspan="7">
					<html:textarea property="tenderInfo.tpDescription" rows="5" cols="100"/>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td align="right">
					<bean:message bundle="tendermanage" key="tender.attachment" />£º
				</td>
				<td colspan="5">					
					
					<logic:iterate id="am" name="tenderInputForm" property="tenderInfo.attachmentList" indexId="i">
						<logic:equal name="am" property="paInputInterface" value="2">
						<span title="<bean:write name="am" property="paLocalUrl"/>">
							<bean:write name="am" property="paName"/>
							<a href="tenderManage.do?method=deleteAttachmentByIndex&i=<%=i%>"><img border="0" src="pages\images\icon\16x16\delete.gif"></a>£»
						</span>				
						</logic:equal>
					</logic:iterate>
					
				</td>			
				<td>
					<html:button property="" onclick="openDialog('tenderManage.do?method=addAttachment',500,160);" styleClass="selectFileButton">&nbsp;</html:button>
				</td>	
			</tr>
			<tr><td>&nbsp;</td></tr>			
			
						
			
		</table>
				
	</html:form>
		
</gui:window>
</center>

<html:javascript formName="tenderInputForm" dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript" src="<html:rewrite forward='staticjavascript'/>"></script>

<script language="JavaScript">

 function chgAction(obj,str){
	obj.value=str;
 }
 
 
  function chgFormOnsubmit(str){  	 	
	tenderInputForm.onsubmit="function onsubmit(){" + str + "}";	
 }
 
function submitForm()
{
	chgFormOnsubmit('return true;');
	chgAction(document.all.method,'refreshTenderProject');
	tenderInputForm.submit();
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

