<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<script type="text/javascript" src="<c:url value='/pages/scripts/calendar.js'/>" charset="gbk"></script>

<bean:define id="title">
	<bean:message bundle="attachment" key="input.title" />
</bean:define>


<center>
<gui:window title='<%=title%>' prototype="boWindow">
<html:form action="documentManage.do" enctype="multipart/form-data" onsubmit="return checkFormValidate();">
	<html:errors />
	
	<input type="hidden" name="method" value="saveEditDocument">	
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
			<td width="10%" align="right">
				<bean:message bundle="attachment" key="code" />：
			</td>
			<td width="20%">						
				<html:text property="documentInfo.paCode" size="18" maxlength="45" readonly="true" style="background-color:LightGray;"/>
			</td>
			<td width="10%" align="right">
				<bean:message bundle="attachment" key="category" />：
			</td>
			<td width="20%">						
				<html:select styleId="paAttachmentCategory" property="documentInfo.paAttachmentCategory" style="width:120">	
					<html:options collection="attachmentCategoryList" property="acId" labelProperty="acName"/>
				</html:select>
			</td>
			
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="attachment" key="filename" />：
			</td>
			<td colspan="3">						
				<html:text styleId="paName" property="documentInfo.paName" size="80" maxlength="100"/>
			</td>			
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="attachment" key="submitdepartment" />：
			</td>
			<td>						
				<html:select property="documentInfo.paSubmitDepartment" style="width:120">						
					<html:options collection="departmentList" property="DId" labelProperty="DName"/>
				</html:select>
			</td>
			<td align="right">
				<bean:message bundle="attachment" key="submitdate" />：
			</td>
			<td >
				<html:text styleId="paSubmitDate" property="documentInfo.paSubmitDate" readonly="true" size="18" maxlength="32" onclick="SelectDate(this)"/>
			</td>
			
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td align="right">
				<bean:message bundle="attachment" key="selectfile" />：
			</td>
			<td colspan="3">
				<div style="width:84%;height:20 px;">
					<bean:write name="documentForm" property="documentInfo.paLocalUrl"/>
				</div>
			</td>
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="attachment" key="updatefile" />：
			</td>
			<td colspan="3">
				<html:file property="documentInfo.attachmentFile" size="80"  onchange="document.getElementById('documentInfo.paLocalUrl').value=this.value">					
				</html:file>	
				<html:hidden property="documentInfo.paLocalUrl"/>				
			</td>				
		</tr>
		
		<tr>
			<td align="right">
				<bean:message bundle="attachment" key="remarks" />：
			</td>
			<td colspan="3">
				<html:textarea property="documentInfo.paDescription" cols="70" rows="3"></html:textarea>				
			</td>				
		</tr>
		
		<tr><td>&nbsp;</td></tr>
					
		
	</table>
	
</html:form>
</gui:window>
</center>

<script language="JavaScript">


function checkFormValidate()
{	 	
	var category = document.getElementById("paAttachmentCategory").value;	
	if(category == "")
	{
		alert("文件类别不能为空");
		return false;
	} 
		
	var name = document.getElementById("paName").value;	
	if(name == "")
	{
		alert("文件名称 不能为空");
		return false;
	} 
	
	var date = document.getElementById("paSubmitDate").value;	
	if(date == "")
	{
		alert("提交时间 不能为空");
		return false;
	}
	 	
	return true;
}


</script>

