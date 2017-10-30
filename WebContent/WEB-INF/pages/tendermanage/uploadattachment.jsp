<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<body>
	<html:form action="tenderManage.do" enctype="multipart/form-data" onsubmit="return checkFormValidate();">
		<html:errors />
		
		<input type="hidden" name="method" value="confirmAttachment">
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="90%" border="0">
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td width="10%" align="right">
					<bean:message bundle="attachment" key="selectfile" />：
				</td>
				<td >
					<html:file property="tenderInfo.currentAttachment.attachmentFile" size="50"  onchange="document.getElementById('tenderInfo.currentAttachment.paUrl').value=this.value"></html:file>	
					<html:hidden property="tenderInfo.currentAttachment.paUrl"/>				
				</td>				
			</tr>
			<tr>
				<td align="right">
					<bean:message bundle="attachment" key="category" />：
				</td>
				<td >						
					<html:select property="tenderInfo.currentAttachment.paAttachmentCategory" style="width:120" disabled="true">	
						<html:option value=""></html:option>
						<html:options collection="attachmentCategoryList" property="acId" labelProperty="acName"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<td width="10%" align="right">
					<bean:message bundle="attachment" key="code" />：
				</td>
				<td width="15%">						
					<html:text property="tenderInfo.currentAttachment.paCode" size="12" maxlength="45" readonly="true" style="background-color:LightGray;"/>
				</td>
			</tr>
			<tr>
				<td width="10%" align="right">
					<bean:message bundle="attachment" key="filename" />：
				</td>
				<td width="15%">						
					<html:text property="tenderInfo.currentAttachment.paName" size="45" maxlength="45"/>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
						
			
		</table>
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr><td>&nbsp;</td></tr>	
			<tr><td>&nbsp;</td>
				<td width="20%" align="center">
					<html:button styleClass="confirmbutton" property="" onclick="tenderInputForm.submit();">
						&nbsp;
					</html:button>						
				</td>
							
			</tr>
			<tr><td>&nbsp;</td></tr>
		</table>		
	</html:form>


<script language="JavaScript">

 function chgAction(obj,str){
	obj.value=str;
 }
 
 
 function chgFormOnsubmit(str){  	
	projectInputForm.onsubmit="function onsubmit(){" + str + "}";	
 }
 

function checkFormValidate()
{	 	
	var pomId = document.getElementById("tenderInfo.tpId").value;	
	if(pomId == "" || pomId == "0")
	{
		alert("客户 不能为空");
		return false;
	} 
	 	
	return true;
}


</script>

</body>
