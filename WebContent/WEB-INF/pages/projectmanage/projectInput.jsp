<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>


<bean:define id="title">
	<bean:message bundle="projectmanage" key="project.input" />
</bean:define>


<center>
<gui:window title='<%=title%>' prototype="boWindow">
	<html:form action="projectManage.do" onsubmit="return validateProjectSearchForm(this);">
		<html:errors />
		
		<input type="hidden" name="method" value="saveTenderProject">	
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
				<tr><td>&nbsp;</td></tr>	
				<tr><td width="70%">&nbsp;</td>						
					<td align="center" width="12%">
						<html:submit styleClass="savebutton">
							&nbsp;
						</html:submit>						
					</td>				
				</tr>
			</table>	
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr>
				<td width="10%"></td>
				<td width="15%"></td>				
				<td width="10%"></td>
				<td width="15%"></td>
				<td width="10%"></td>
				<td width="16%"></td>
				<td width="12%"></td>
				<td></td>
			</tr>
			<tr>
				<td align="right">
					<bean:message bundle="projectmanage" key="project.name" />£º
				</td>
				<td colspan="3">
					<html:text property="projectInfo.tpName" size="65" maxlength="100"/>*
				</td>
			</tr>
			<tr>	
				<td align="right">
					<bean:message bundle="projectmanage" key="project.status" />£º
				</td>
				<td>										
					<html:select property="projectInfo.tpStatus" style="width:125">							
						<html:options collection="statusList" property="psId" labelProperty="psName"/>
					</html:select>								
				</td>
				<td align="right">
					<bean:message bundle="projectmanage" key="project.dept" />£º
				</td>
				<td>										
					<html:select property="projectInfo.tpDept" style="width:125">							
						<html:options collection="deptList" property="DId" labelProperty="DName"/>
					</html:select>*								
				</td>
				<td align="right">
					<bean:message bundle="projectmanage" key="project.manager" />£º
				</td>
				<td>
					<html:text property="projectInfo.manager.person.personName" size="18" maxlength="100" readonly="true" style="background-color:LightGray;"/>*		
					<html:button property="" onclick="openDialog('projectManage.do?method=searchAccount&opType=projectInputManager',800,420);">...</html:button>			
				</td>
			</tr>

			<tr>
				<td align="right">
					<bean:message bundle="projectmanage" key="project.customer" />£º
				</td>
				<td colspan="3">
					<html:text property="projectInfo.customer.CName" size="65" maxlength="100" readonly="true" style="background-color:LightGray;"/>*	
					 <html:button property="" onclick="openDialog('projectManage.do?method=searchCustomer',800,420);">...</html:button>				
				</td>	
				<td align="right">
					<bean:message bundle="approvalrecord" key="market_manager" />£º
				</td>
				<td>						
					<html:text property="projectInfo.marketManager.person.personName" size="18" maxlength="45" readonly="true" style="background-color:LightGray;"/>*
					<html:button property="" onclick="openDialog('projectManage.do?method=searchAccount&opType=projectInputMarketManager',800,420);">...</html:button>
				</td>
			</tr>
			<tr>
				<td align="right">
					<bean:message bundle="projectmanage" key="project.begindate" />£º 
				</td>
				<td >						
					<html:text property="projectInfo.tpBeginDate" readonly="true" size="18" maxlength="32" onclick="SelectDate(this);" onchange="submitForm();"/>*
				</td>				
				<td align="right">
					<bean:message bundle="projectmanage" key="project.enddate" />£º 
				</td>
				<td>						
					<html:text property="projectInfo.tpEndDate" readonly="true" size="18" maxlength="32" onclick="SelectDate(this);" onchange="submitForm();"/>*
				</td>

				<td align="right">
					<bean:message bundle="projectmanage" key="project.workperiod" />£º
				</td>
				<td>
					<html:text property="projectInfo.tpWorkdayCount" size="18" maxlength="100" readonly="true" style="background-color:LightGray;"/><bean:message bundle="projectmanage" key="project.workperiodunit" />					
				</td>				
			</tr>
			
			<tr>
				
				<td align="right">
					<bean:message bundle="tendermanage" key="tender.projectAddress" />£º
				</td>
				<td colspan="3">
					<html:text property="projectInfo.tpAddress" size="65" maxlength="100"/>*
				</td>
				<td align="right">
					<bean:message bundle="projectmanage" key="project.contractamount" />£º
				</td>
				<td>
					<html:text property="projectInfo.tpContractAmount" size="18" maxlength="100"/><bean:message bundle="projectmanage" key="project.contractamountunit" />					
				</td>

				
				
			</tr>
				

			<tr>
				<td align="right">
					<bean:message bundle="projectmanage" key="project.description" />£º
				</td>
				<td colspan="7">
					<html:textarea property="projectInfo.tpDescription" cols="110" rows="3"/>
				</td>			
					
			</tr>
			<tr><td>&nbsp;</td></tr>
								
			
		</table>
				
	</html:form>
		
</gui:window>
</center>

<html:javascript formName="projectSearchForm" dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript" src="<html:rewrite forward='staticjavascript'/>"></script>

<script language="JavaScript">

 function chgAction(obj,str){
	obj.value=str;
 }
 
 
  function chgFormOnsubmit(str){  	 	
	projectSearchForm.onsubmit="function onsubmit(){" + str + "}";	
 }
 
	
function submitForm()
{
	chgFormOnsubmit('return true;');
	chgAction(document.all.method,'refreshProjectInput');
	projectSearchForm.submit();
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

