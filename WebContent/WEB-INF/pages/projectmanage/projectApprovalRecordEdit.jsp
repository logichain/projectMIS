<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>


<bean:define id="title">
	<bean:message bundle="approvalrecord" key="title" />
</bean:define>

<gui:window title='<%=title%>' prototype="boWindow">
	<html:form action="projectManage.do" onsubmit="return validateEditForm();">
		<html:errors />
		
		<input type="hidden" name="method" value="saveProjectApprovalRecord">	
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr><td>&nbsp;</td></tr>	
			<tr><td>&nbsp;</td>						
				<td align="right" width="10%">
					<logic:notEmpty name="projectSearchForm" property="projectApprovalRecordInfo.parId">
						<logic:equal name="projectSearchForm" property="projectApprovalRecordInfo.parStatus" value="99">
							<a href='projectManage.do?method=checkProjectApprovalRecordBegin'><img border="0" src="pages\style\default\mis\sendcheck.jpg"></a>	
						</logic:equal>		
						<logic:equal name="projectSearchForm" property="projectApprovalRecordInfo.parStatus" value="2">
							<a href='projectManage.do?method=checkProjectApprovalRecordBegin'><img border="0" src="pages\style\default\mis\sendcheck.jpg"></a>	
						</logic:equal>	
					</logic:notEmpty>
				</td>				
			</tr>
			<tr><td>&nbsp;</td></tr>	
		</table>	
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr>
				<td width="7%"></td>
				<td width="40%"></td>				
				<td width="8%"></td>
				<td></td>
			</tr>
			<tr>
				<td align="right">
					<bean:message bundle="approvalrecord" key="project_name" />：
				</td>
				<td>
					<html:text styleId="projectName" property="projectApprovalRecordInfo.parProjectName" size="65" maxlength="100"/>*
				</td>
				<td align="right">
					<bean:message bundle="projectmanage" key="project.dept" />：
				</td>
				<td>										
					<html:select property="projectApprovalRecordInfo.parDept" style="width:125">							
						<html:options collection="deptList" property="DId" labelProperty="DName"/>
					</html:select>*					
				</td>
				
			</tr>
			<tr>
				<td align="right">
					<bean:message bundle="approvalrecord" key="project_owner" />：
				</td>
				<td>										
					<html:text styleId="projectOwner" property="projectApprovalRecordInfo.parProjectOwner" size="65" maxlength="100"/>*						
				</td>
				
				<td align="right">
					<bean:message bundle="projectmanage" key="project.implement_dept" />：
				</td>
				<td>										
					<html:select property="projectApprovalRecordInfo.parImplementDept" style="width:125">							
						<html:option value="2">环保工程部</html:option>
						<html:option value="5">新能源工程部</html:option>
					</html:select>*							
				</td>
			</tr>
			<tr>
				<td align="right">
					<bean:message bundle="approvalrecord" key="design_company" />：
				</td>
				<td>
					<html:text property="projectApprovalRecordInfo.parDesignCompany" size="65" maxlength="100"/>
				</td>
				
				<td align="right">
					<bean:message bundle="approvalrecord" key="market_manager" />：
				</td>
				<td>
					<html:text styleId="marketmanager" property="projectApprovalRecordInfo.marketManager.person.personName" size="18" maxlength="100" readonly="true" style="background-color:LightGray;"/>*		
					<html:button property="" onclick="openDialog('projectManage.do?method=searchAccount&opType=parMarketManagerEdit',800,420);">...</html:button>			
				</td>	
			</tr>
			<tr>
				<td align="right">
					<bean:message bundle="approvalrecord" key="tender_company" />：
				</td>
				<td>										
					<html:text property="projectApprovalRecordInfo.parTenderCompany" size="65" maxlength="100"/>					
				</td>
				
			</tr>
			<tr>
				<td align="right">
					<bean:message bundle="approvalrecord" key="tender_price" />：
				</td>
				<td>
					<html:text styleId="tenderPrice" property="projectApprovalRecordInfo.parTenderPrice" size="18" maxlength="18"/>万元*
				</td>

				<td align="right">
					<bean:message bundle="approvalrecord" key="tender_time" />： 
				</td>
				<td >						
					<html:text styleId="tenderDate" property="projectApprovalRecordInfo.parTenderDate" readonly="true" size="18" maxlength="18" onclick="SelectDate(this);"/>
				</td>									
			</tr>
			<tr>
				<td align="right">
					<bean:message bundle="approvalrecord" key="operating_expense" />：
				</td>
				<td>
					<html:text styleId="operatingExpense" property="projectApprovalRecordInfo.parOperatingExpense" size="18" maxlength="18"/>万元*
				</td>

				<td align="right">
					<bean:message bundle="approvalrecord" key="business_type" />：
				</td>
				<td>										
					<html:text property="projectApprovalRecordInfo.parBusinessType" size="18" maxlength="18"/>				
				</td>
				
			</tr>
			
			<tr>				
				<td align="right">
					<bean:message bundle="approvalrecord" key="business_relation" />：
				</td>
				<td>
					<html:text property="projectApprovalRecordInfo.parBusinessRelation" size="18" maxlength="18"/>
				</td>		
				<td align="right">
					<bean:message bundle="projectmanage" key="project.manager" />：
				</td>
				<td>
					<html:text styleId="manager" property="projectApprovalRecordInfo.manager.person.personName" size="18" maxlength="100" readonly="true" style="background-color:LightGray;"/>*		
					<html:button property="" onclick="openDialog('projectManage.do?method=searchAccount&opType=parManagerEdit',800,420);">...</html:button>			
				</td>		
			</tr>
			<tr><td>&nbsp;</td></tr>	

			<tr>
				<td align="left">
					<bean:message bundle="approvalrecord" key="project_remark" />：
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<html:textarea property="projectApprovalRecordInfo.parProjectRemark" cols="110" rows="5"/>
				</td>					
			</tr>
			<tr>
				<td align="left" colspan="2">
					<bean:message bundle="approvalrecord" key="risk_control" />：
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<html:textarea property="projectApprovalRecordInfo.parRiskControl" cols="110" rows="5"/>
				</td>					
			</tr>
			<tr>
				<td align="left" colspan="2">
					<bean:message bundle="approvalrecord" key="actionability" />：
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<html:textarea property="projectApprovalRecordInfo.parActionability" cols="110" rows="5"/>
				</td>					
			</tr>							
			
		</table>
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">			
			<tr><td>&nbsp;</td>						
				<td align="center" width="10%">			
					<logic:notEqual name="projectSearchForm" property="projectApprovalRecordInfo.parStatus" value="3">		
						<html:submit styleClass="savebutton">&nbsp;</html:submit>	
					</logic:notEqual>					
				</td>				
			</tr>
		</table>		
	</html:form>
		
</gui:window>

<script language="JavaScript">

 function chgAction(obj,str){
	obj.value=str;
 }
 
 
 function chgFormOnsubmit(str){  	 	
	projectSearchForm.onsubmit="function onsubmit(){" + str + "}";	
 }	
  

function validateEditForm()
{   
	var projectName = document.getElementById("projectName").value;	
	if(projectName == "")
	{
		alert("项目名称  不能为空");
		return false;
	} 
	var projectOwner = document.getElementById("projectOwner").value;	
	if(projectOwner == "")
	{
		alert("业主单位  不能为空");
		return false;
	}	 	
	
	var tenderPrice = document.getElementById("tenderPrice").value;	
	if(tenderPrice == "")
	{
		alert("招标价格  不能为空");
		return false;
	}

	var operatingExpense = document.getElementById("operatingExpense").value;	
	if(operatingExpense == "")
	{
		alert("业务费用  不能为空");
		return false;
	}
	
	var manager = document.getElementById("manager").value;	
	if(manager == "")
	{
		alert("项目经理  不能为空");
		return false;
	}
	
	var manager = document.getElementById("marketmanager").value;	
	if(manager == "")
	{
		alert("项目市场经理  不能为空");
		return false;
	}
	
	return true;	   
}

	
function submitForm()
{
	chgFormOnsubmit('return true;');
	chgAction(document.all.method,'refreshProjectApprovalRecordEdit');
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

