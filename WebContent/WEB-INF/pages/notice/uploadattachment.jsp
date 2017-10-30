<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<html:form action="noticeManage.do" enctype="multipart/form-data" onsubmit="return checkFormValidate();">
	<html:errors />
	
	<input type="hidden" name="method" value="confirmAttachment">	
	
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="90%" border="0">
		<caption><bean:message bundle="attachment" key="caption" /></caption>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td width="10%" align="right">
				<bean:message bundle="attachment" key="selectfile" />：
			</td>
			<td>
				<html:file styleId="file" property="noticeInfo.currentAttachment.attachmentFile" size="50"  onchange="document.getElementById('noticeInfo.currentAttachment.naLocalUrl').value=this.value"></html:file>	
				<html:hidden property="noticeInfo.currentAttachment.naLocalUrl"/>				
			</td>				
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="attachment" key="category" />：
			</td>
			<td >						
				<html:select styleId="category" property="noticeInfo.currentAttachment.naAttachmentCategory" style="width:120">	
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
				<html:text styleId="code" property="noticeInfo.currentAttachment.naCode" size="12" maxlength="45"/>
			</td>
		</tr>
		<tr>
			<td width="10%" align="right">
				<bean:message bundle="attachment" key="filename" />：
			</td>
			<td width="15%">						
				<html:text styleId="name" property="noticeInfo.currentAttachment.naName" size="45" maxlength="45"/>
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
					
		
	</table>
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
		<tr><td>&nbsp;</td></tr>	
		<tr><td>&nbsp;</td>
			<td width="20%" align="center">
				<html:submit styleClass="confirmbutton">
					&nbsp;
				</html:submit>						
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
	projectSearchForm.onsubmit="function onsubmit(){" + str + "}";	
 }
 

function checkFormValidate()
{	 	
	var file = document.getElementById("file").value;	
	if(file == "")
	{
		alert("选择文件不能为空");
		return false;
	} 
	
	var category = document.getElementById("category").value;	
	if(category == "")
	{
		alert("文件类别不能为空");
		return false;
	} 
	
	var code = document.getElementById("code").value;	
	if(code == "")
	{
		alert("文件编号不能为空");
		return false;
	} 
	
	var name = document.getElementById("name").value;	
	if(name == "")
	{
		alert("文件名称 不能为空");
		return false;
	} 
	 	
	return true;
}


</script>

