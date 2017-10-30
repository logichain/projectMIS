<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<html:form action="tenderProjectManage.do" enctype="multipart/form-data" onsubmit="return checkFormValidate();">
	<html:errors />
	
	<input type="hidden" name="method" value="confirmTenderAttachment">	
	<input type="hidden" name="if" value="<%=request.getParameter("if") %>">			
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="90%" border="0">
		<caption><bean:message bundle="attachment" key="caption" /></caption>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td width="10%" align="right">
				<bean:message bundle="attachment" key="selectfile" />：
			</td>
			<td id="filetd">
				<html:file styleId="attachmentFile" property="tenderInfo.currentTenderAttachment.attachmentFile" size="50"  onchange="document.getElementById('tenderInfo.currentTenderAttachment.paLocalUrl').value=this.value"></html:file>	
				<html:hidden property="tenderInfo.currentTenderAttachment.taLocalUrl"/>				
			</td>				
		</tr>
		
		<tr>
			<td width="10%" align="right">
				<bean:message bundle="attachment" key="code" />：
			</td>
			<td width="15%">						
				<html:text property="tenderInfo.currentTenderAttachment.taCode" size="12" maxlength="45" readonly="true" style="background-color:LightGray;"/>
			</td>
		</tr>
		<tr>
			<td width="10%" align="right">
				<bean:message bundle="attachment" key="filename" />：
			</td>
			<td width="15%">						
				<html:text styleId="paName" property="tenderInfo.currentTenderAttachment.taName" size="45" maxlength="45"/>
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
 
 window.onunload=function(){  
    chgFormOnsubmit('return true;');
	chgAction(document.all.method,'finishAttachment');
	
	//删除html:file标签，防止产生异常
	var list=document.getElementById("filetd");
 	list.removeChild(list.childNodes[0]);
 	
	projectSearchForm.submit();  		
}

function checkFormValidate()
{	 	
	var file = document.getElementById("attachmentFile").value;	
	if(file == "")
	{
		alert("选择文件不能为空");
		return false;
	} 
			
	var name = document.getElementById("paName").value;	
	if(name == "")
	{
		alert("文件名称 不能为空");
		return false;
	} 
	 	
	return true;
}


</script>

