<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<html:form action="tenderProjectManage.do" onsubmit="return checkFormValidate()&&validateTenderProjectForm(this);" enctype="multipart/form-data">
	<html:errors />
	
	<input type="hidden" name="method" value="saveBaseTenderProject">	
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
		<tr><td>&nbsp;</td></tr>	
		<tr><td width="70%">&nbsp;</td>						
			<td align="center" width="12%">
				<logic:equal name="accountPerson" property="hasCreatePower" value="true">
				<html:submit styleClass="savebutton" onclick="chgAction(document.all.method,'saveBaseTenderProject');" style="cursor: hand;">
					&nbsp;
				</html:submit>						
				</logic:equal>
			</td>				
		</tr>
	</table>	
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
		<tr>
			<td width="10%"></td>
			<td width="15%"></td>				
			<td width="10%"></td>
			<td width="16%"></td>
			<td width="10%"></td>
			<td width="16%"></td>
			<td width="12%"></td>
			<td></td>
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="tendermanage" key="tender.projectName" />：
			</td>
			<td colspan="3">
				<html:text property="tenderInfo.tpName" size="61" maxlength="45"/>*
			</td>
		</tr>
		<tr>	
			<td align="right">
				<bean:message bundle="projectmanage" key="project.status" />：
			</td>
			<td>										
				<html:select property="tenderInfo.tpStatus" style="width:125">				
					<html:options collection="statusList" property="psId" labelProperty="psName"/>
				</html:select>								
			</td>
			
			<td align="right">
				<bean:message bundle="projectmanage" key="project.dept" />：
			</td>
			<td>										
				<html:select property="tenderInfo.tpDept" style="width:125">						
					<html:options collection="deptList" property="DId" labelProperty="DName"/>
				</html:select>
			</td>
			<td align="right">
				<bean:message bundle="tendermanage" key="tender.manager" />：
			</td>
			<td>						
				<html:text property="tenderInfo.manager.person.personName" size="18" maxlength="45" readonly="true" style="background-color:LightGray;"/>
				<logic:equal name="accountPerson" property="hasCreatePower" value="true">
					<html:button property="" onclick="openDialog('tenderProjectManage.do?method=searchAccount&type=tenderEditManager',800,420);">...</html:button>
				</logic:equal>
			</td>
		</tr>

		
		<tr>
			<td align="right">
				<bean:message bundle="tendermanage" key="tender.clientName" />：
			</td>
			<td colspan="3">						
				<html:text property="tenderInfo.customer.CName" size="61" maxlength="100" readonly="true" style="background-color:LightGray;"/>*
			</td>		
			<td align="right">
				<bean:message bundle="approvalrecord" key="market_manager" />：
			</td>
			<td>						
				<html:text property="tenderInfo.marketManager.person.personName" size="18" maxlength="45" readonly="true" style="background-color:LightGray;"/>
				<html:button property="" onclick="openDialog('tenderProjectManage.do?method=searchAccount&type=tenderEditMarketManager',800,420);">...</html:button>
			</td>
		</tr>
		
		<tr>
			<td align="right">
				<bean:message bundle="tendermanage" key="tender.projectAddress" />：
			</td>
			<td colspan="3">
				<html:text property="tenderInfo.tpAddress" size="61" maxlength="100"/>
			</td>
			<td align="right">
				<bean:message bundle="projectmanage" key="project.contractamount" />：
			</td>
			<td>
				<html:text property="tenderInfo.tpContractAmount" size="18" maxlength="100"/><bean:message bundle="projectmanage" key="project.contractamountunit" />					
			</td>
		</tr>
			
		<tr>			
			<td align="right">
				<bean:message bundle="projectmanage" key="project.begindate" />： 
			</td>
			<td >						
				<html:text property="tenderInfo.tpBeginDate" readonly="true" size="18" maxlength="32" onclick="SelectDate(this)" onchange="submitForm();"/>
			</td>				
			<td align="right">
				<bean:message bundle="projectmanage" key="project.enddate" />： 
			</td>
			<td >						
				<html:text property="tenderInfo.tpEndDate" readonly="true" size="18" maxlength="32" onclick="SelectDate(this)" onchange="submitForm();"/>
			</td>

			<td align="right">
				<bean:message bundle="projectmanage" key="project.workperiod" />：
			</td>
			<td>
				<html:text property="tenderInfo.tpWorkdayCount" size="18" maxlength="100" readonly="true" style="background-color:LightGray;"/><bean:message bundle="projectmanage" key="project.workperiodunit" />					
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td align="right">
				<bean:message bundle="tendermanage" key="tender.projectDescription" />：
			</td>
			<td colspan="7">
				<html:textarea property="tenderInfo.tpDescription" rows="5" cols="100"/>
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td align="right">
				<bean:message bundle="tendermanage" key="tender.attachment" />：
			</td>
			<td colspan="5">					
				
				<logic:iterate id="am" name="tenderProjectForm" property="tenderInfo.attachmentList" indexId="i">
					<logic:equal name="am" property="paInputInterface" value="2">
					<span title="<bean:write name="am" property="paLocalUrl"/>">
						<bean:write name="am" property="paName"/>
						<logic:equal name="accountPerson" property="hasCreatePower" value="true">
							<a href="tenderProjectManage.do?method=deleteAttachment&i=<%=i%>&type=baseInput"><img border="0" src="pages\images\icon\16x16\delete.gif"></a>；
						</logic:equal>
					</span>				
					</logic:equal>
				</logic:iterate>
				
			</td>			
			<td>
				<logic:equal name="accountPerson" property="hasCreatePower" value="true">
					<html:button property="" onclick="openDialog('tenderProjectManage.do?method=addAttachment&type=baseInput',500,160);" styleClass="selectFileButton">&nbsp;</html:button>
				</logic:equal>
			</td>	
		</tr>
		<tr><td>&nbsp;</td></tr>
		
	</table>
			
</html:form>

<html:javascript formName="tenderProjectForm" dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript" src="<html:rewrite forward='staticjavascript'/>"></script>

<script language="JavaScript">

 function chgAction(obj,str){
	obj.value=str;
 }
 
 
  function chgFormOnsubmit(str){  	 	
	tenderProjectForm.onsubmit="function onsubmit(){" + str + "}";	
 }
 
	
function checkFormValidate()
{	 	
	var pomId = document.getElementById("tenderInfo.tpCode").value;	
	if(pomId == "" || pomId == "0")
	{
		alert("项目编号 不能为空");
		return false;
	} 
	 	
	return true;
}

function submitForm()
{
	chgFormOnsubmit('return true;');
	chgAction(document.all.method,'refreshTenderBase');
	tenderProjectForm.submit();
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