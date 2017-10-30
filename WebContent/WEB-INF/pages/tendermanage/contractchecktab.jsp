<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<html:form action="tenderProjectManage.do" onsubmit="return checkFormValidate();">
	<html:errors />
	
	<input type="hidden" name="method" value="saveProjectContract">	
	<input type="hidden" name="id">		
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
		<tr><td>&nbsp;</td></tr>	
		<tr>
		<td align="left">
			<bean:message bundle="projectmanage" key="task.checkresult"/>：
			<logic:equal name="tenderProjectForm" property="tenderInfo.tenderContract.pcStatus" value="1">
				已评审
			</logic:equal>
			<logic:equal name="tenderProjectForm" property="tenderInfo.tenderContract.pcStatus" value="2">
				评审中
			</logic:equal>
		</td>							
						
			<td align="center" width="20%">		
				<logic:equal name="accountPerson" property="hasCreatePower" value="true">		
					<html:button styleClass="createbutton" property="" onclick="createContract();">
						&nbsp;
					</html:button>
					<logic:notEqual name="tenderProjectForm" property="tenderInfo.tenderContract.pcStatus" value="1">
						<html:submit styleClass="savebutton">
							&nbsp;
						</html:submit>			
					</logic:notEqual>
				</logic:equal>			
			</td>				
		</tr>
	</table>					
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
		<tr>
			<td width="10%"></td>
			<td></td>
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="contract.title"/>：
			</td>
			<td colspan="3">
				<html:text styleId="contractTitle" property="tenderInfo.tenderContract.pcTitle" size="65" maxlength="100"/>
			</td>			
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="contract.type"/>：
			</td>
			<td colspan="3">
				<html:select property="tenderInfo.tenderContract.pcType" style="width:125">	
					<html:option value="1">分包合同</html:option>
					<html:option value="2">采购合同</html:option>
					<html:option value="3">其他类型</html:option>
				</html:select>	
			</td>			
		</tr>
		<tr>			
			<td align="right">
				<bean:message bundle="projectmanage" key="project.customer" />：
			</td>
			<td colspan="3">
				<html:text styleId="customerName" property="tenderInfo.tenderContract.customer.CName" size="65" maxlength="100" readonly="true" style="background-color:LightGray;"/>	
				 <html:button property="" onclick="openDialog('tenderProjectManage.do?method=searchCustomer&type=contractInput',800,600);">...</html:button>				
			</td>
		</tr>

		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="contract.remark" />：
			</td>
			<td>			
				<html:textarea property="tenderInfo.tenderContract.pcRemark" cols="100" rows="8" />
			</td>				
		</tr>		
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="project.attachment" />：
			</td>
			<td>
				<table width="100%"><tr><td width="60%">
		
					<logic:iterate id="am" name="tenderProjectForm" property="tenderInfo.tenderContract.attachmentList" indexId="i">
					<logic:equal name="am" property="paInputInterface" value="3">
					<logic:notEqual name='am' property='paFlag' value="-1">
						<logic:empty name='am' property='paId'>
							<span title="<bean:write name="am" property="paLocalUrl"/>">
								<bean:write name="am" property="paName"/>
							</span>
							<logic:equal name="accountPerson" property="hasCreatePower" value="true">
								<a href="tenderProjectManage.do?method=deleteAttachmentByIndex&index=<%=i %>"><img border="0" src="pages\images\icon\16x16\delete.gif"></a>；
							</logic:equal>
						</logic:empty>
						<logic:notEmpty name='am' property='paId'>
							<a href="tenderProjectManage.do?method=downloadAttachment&id=<bean:write name='am' property='paId'/>" title="<bean:write name="am" property="paLocalUrl"/>">
								<bean:write name="am" property="paName"/>
							</a>
							<logic:equal name="accountPerson" property="hasCreatePower" value="true">
								<a href="tenderProjectManage.do?method=deleteAttachment&id=<bean:write name='am' property='paId'/>"><img border="0" src="pages\images\icon\16x16\delete.gif"></a>；
							</logic:equal>
						</logic:notEmpty>
					</logic:notEqual>	
					</logic:equal>		
				</logic:iterate>

			</td><td>	
				<logic:equal name="accountPerson" property="hasCreatePower" value="true">
					<html:button property="" onclick="openDialog('tenderProjectManage.do?method=addAttachment&type=contractInput',500,180);" styleClass="selectFileButton">&nbsp;</html:button>
				</logic:equal>
				</td></tr>
				</table>
			</td>	
		</tr>
		<tr><td>&nbsp;</td></tr>
	</table>				
	<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">					
			<thead>
				<tr>
					<td width="5%" align="center">
						<bean:message bundle="projectmanage" key="serial" />
					</td>						
					<td width="15%" align="center">
						<bean:message bundle="projectmanage" key="contract.title" />
					</td>											
					<td  width="5%" align="center">
						<bean:message bundle="projectmanage" key="contract.type" />
					</td>						
					<td align="center">
						<bean:message bundle="projectmanage" key="project.customer" />
					</td>
					<td align="center">
						<bean:message bundle="projectmanage" key="contract.remark" />
					</td>
					<td width="15%" align="center">
						<bean:message bundle="projectmanage" key="project.attachment" />
					</td>
					<td width="5%" align="center">
						<bean:message key="button.edit" />
					</td>
					<td width="5%" align="center">
						<bean:message key="button.delete" />
					</td>
				</tr>
			</thead>
			<tbody>
				<%int i=0;%>
				<logic:iterate id="pc" name="tenderProjectForm" property="tenderInfo.tenderContractList">
				<logic:notEqual name="pc" property="pcStatus" value="-1">					
				 	<% int a = i%2;request.setAttribute("a",a);i++;%>
					<logic:equal name="a" value="0"><tr class="even"></logic:equal>
					<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
						<td align="center"><%=i%></td>
						<td><bean:write name="pc" property="pcTitle"/></td>
						<td>
							<logic:equal name="pc" property="pcType" value="1">分包合同</logic:equal>
							<logic:equal name="pc" property="pcType" value="2">采购合同</logic:equal>
							<logic:equal name="pc" property="pcType" value="3">其他类型</logic:equal>
						</td>
						<td><bean:write name="pc" property="customer.CName"/></td>
						<td><bean:write name="pc" property="pcRemark"/></td>
						<td>
							<logic:iterate id="am" name="pc" property="attachmentList">
								<logic:equal name="am" property="paInputInterface" value="3">
								<logic:notEqual name='am' property='paFlag' value="-1">									
									<a href="tenderProjectManage.do?method=downloadAttachment&id=<bean:write name='am' property='paId'/>" title="<bean:write name="am" property="paLocalUrl"/>">
										<bean:write name="am" property="paName"/>
									</a>									
								</logic:notEqual>	
								</logic:equal>					
							</logic:iterate>						
						</td>
													
						<logic:equal name="pc" property="pcStatus" value="0">							
							<td align="center">
								<logic:equal name="accountPerson" property="hasCreatePower" value="true">
									<a href='tenderProjectManage.do?method=editProjectContractCheck&cid=<bean:write name="pc" property="pcId"/>&id=<bean:write name="pc" property="pcProject"/>'><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
								</logic:equal>
							</td>
							<td align="center">
								<logic:equal name="accountPerson" property="hasCreatePower" value="true">
									<a href='javascript:if(confirm("确认要删除这条信息吗?")) {chgAction(document.all.id,"<bean:write name="pc" property="pcId"/>");deleteContract();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
								</logic:equal>
							</td>
						</logic:equal>
						<logic:notEqual name="pc" property="pcStatus" value="0">							
							<td align="center"></td>
							<td align="center"></td>
						</logic:notEqual>
						
					</tr>
				</logic:notEqual>
				</logic:iterate>
			</tbody>
		</table>
</html:form>



<script language="JavaScript">

 function chgAction(obj,str){
	obj.value=str;
 }
 
 
  function chgFormOnsubmit(str){  	 	
	tenderProjectForm.onsubmit="function onsubmit(){" + str + "}";	
 }
 
 
function checkFormValidate()
{	
	var cname = document.getElementById("customerName").value;	
	if(cname == "")
	{
		alert("客户 不能为空");
		return false;
	}
	
	var pname = document.getElementById("contractTitle").value;	
	if(pname == "")
	{
		alert("合同标题 不能为空");
		return false;
	} 
		 	
	return true;
}

function createContract()
{
	chgAction(document.all.method,'createProjectContract');
	tenderProjectForm.submit();
}
function deleteContract()
{
	chgAction(document.all.method,'deleteProjectContract');
	tenderProjectForm.submit();
}

function submitForm()
{
	chgFormOnsubmit('return true;');
	chgAction(document.all.method,'refreshProjectContract');
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
