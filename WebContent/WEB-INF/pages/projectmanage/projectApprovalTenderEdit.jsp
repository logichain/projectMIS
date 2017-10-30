<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>


<bean:define id="title">
	<bean:message bundle="approvalrecord" key="checktitle" />
</bean:define>

<gui:window title='<%=title%>' prototype="boWindow">
	<html:form action="projectManage.do" onsubmit="return validateEditForm();">
		<html:errors />
		
		<input type="hidden" name="method" value="saveProjectApprovalTender">
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr><td>&nbsp;</td></tr>	
			<tr><td>&nbsp;</td>						
				<td align="center" width="10%">
					<logic:notEmpty name="projectSearchForm" property="projectApprovalRecordInfo.parCommitDept">
						<logic:equal name="projectSearchForm" property="projectApprovalRecordInfo.parStatus" value="5">
							<a href='projectManage.do?method=checkProjectApprovalTenderBegin'><img border="0" src="pages\style\default\mis\sendcheck.jpg"></a>	
						</logic:equal>		
						<logic:equal name="projectSearchForm" property="projectApprovalRecordInfo.parStatus" value="3">
							<a href='projectManage.do?method=checkProjectApprovalTenderBegin'><img border="0" src="pages\style\default\mis\sendcheck.jpg"></a>	
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
				<td width="7%"></td>
				<td></td>
			</tr>
			<tr>
				<td align="center">
					<bean:message bundle="approvalrecord" key="project_name" />：
				</td>
				<td>
					<bean:write name="projectSearchForm" property="projectApprovalRecordInfo.parProjectName"/>
				</td>

				<td align="center">
					<bean:message bundle="approvalrecord" key="project_owner" />：
				</td>
				<td>										
					<html:text styleId="projectOwner" property="projectApprovalRecordInfo.parProjectOwner" size="65" maxlength="100"/>*						
				</td>
				
			</tr>
			<tr>
				<td align="center">
					<bean:message bundle="approvalrecord" key="tender_member" />：
				</td>
				<td>
					<html:text property="projectApprovalRecordInfo.parTenderMember" size="65" maxlength="100"/>
				</td>

				<td align="center">
					<bean:message bundle="approvalrecord" key="tender_company" />：
				</td>
				<td>										
					<html:text property="projectApprovalRecordInfo.parTenderCompany" size="65" maxlength="100"/>					
				</td>
				
			</tr>
			<tr>
				<td align="center">
					<bean:message bundle="checktask" key="task.dept" />：
				</td>
				<td>
					<html:select styleId="commitDept" property="projectApprovalRecordInfo.parCommitDept" style="width:120">							
						<html:options collection="deptList" property="DId" labelProperty="DName"/>
					</html:select>*				
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>	
			<tr>
				<td colspan="3" align="left">
					<b>业主简介与项目概况</b>（包括业主资信、项目规模等）：
				</td>	
			</tr>
			<tr><td></td>		
				<td colspan="3">
					<html:textarea property="projectApprovalRecordInfo.parTenderRemark" cols="110" rows="8"/>
				</td>					
			</tr>	
			<tr><td>&nbsp;</td></tr>	
			<tr>
				<td align="right">
					<bean:message bundle="projectmanage" key="project.attachment" />：
				</td>
				<td colspan="2">
					
					<logic:iterate id="am" name="projectSearchForm" property="projectApprovalRecordInfo.attachmentList" indexId="i">
						
						<span title="<bean:write name="am" property="paLocalUrl"/>">
							<bean:write name="am" property="paName"/>
							<a href="projectManage.do?method=deleteAttachmentByIndex&index=<%=i %>&if=4"><img border="0" src="pages\images\icon\16x16\delete.gif"></a>；
						</span>				
						
					</logic:iterate>
				
				</td>			
				<td>
					<html:button property="" onclick="openDialog('projectManage.do?method=addAttachment&if=4',500,180);" styleClass="selectFileButton">&nbsp;</html:button>
				</td>	
			</tr>	
			
				
		</table>

		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">			
			<tr><td>&nbsp;</td>						
				<td align="center" width="10%">								
					<html:submit styleClass="savebutton">&nbsp;</html:submit>											
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
	var commitDept = document.getElementById("commitDept").value;		
	if(commitDept == 0)
	{
		alert("所属部门  不能为空");
		return false;
	} 
		
	return true;	   
}

function submitForm()
{
	chgFormOnsubmit('return true;');
	chgAction(document.all.method,'refreshProjectTenderEdit');
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

