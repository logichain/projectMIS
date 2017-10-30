<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>


<html:form action="projectManage.do" onsubmit="return checkFormValidate();">
	<html:errors />
	
	<input type="hidden" name="method" value="confirmScheduleTask">				
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="90%" border="0">
		<caption><bean:message bundle="projectmanage" key="scheduletask.caption" /></caption>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td width="15%" align="right">
				<bean:message bundle="projectmanage" key="scheduletask.name" />：
			</td>
			<td  width="30%">
				<html:text property="projectInfo.currentScheduleStage.currentScheduleTask.pstName" size="12" maxlength="45"/>			
			</td>				
		
			<td  width="15%" align="right">
				<bean:message bundle="projectmanage" key="scheduleplan.responsibility" />：
			</td>
			<td>
				<html:text property="projectInfo.currentScheduleStage.currentScheduleTask.responsiblePerson.person.personName" size="12" maxlength="100" readonly="true" style="background-color:LightGray;"/>					
				<html:button property="" onclick="openDialog('projectManage.do?method=searchAccount&opType=taskRes',800,420);">...</html:button>
			</td>
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="schedulestage.name" />：
			</td>
			<td>
				<bean:write name="projectSearchForm" property="projectInfo.currentScheduleStage.pssName"/>			
			</td>
			<td><bean:message bundle="projectmanage" key="scheduleplan.pretask" />：</td>
			<td>
				<html:select property="projectInfo.currentScheduleStage.currentScheduleTask.pstPreTask" style="width:110">	
					<html:option value=""></html:option>
					<html:options collection="taskList" property="pstId" labelProperty="pstName"/>
				</html:select>
			</td>
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="project.begindate" />： 
			</td>
			<td >						
				<html:text property="projectInfo.currentScheduleStage.currentScheduleTask.pstBeginDate" readonly="true" size="12" maxlength="32" onclick="SelectDate(this)"/>
			</td>				
			<td align="right">
				<bean:message bundle="projectmanage" key="project.enddate" />： 
			</td>
			<td >						
				<html:text property="projectInfo.currentScheduleStage.currentScheduleTask.pstEndDate" readonly="true" size="12" maxlength="32" onclick="SelectDate(this)"/>
			</td>
		</tr>

		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="scheduleplan.description" />：
			</td>
			<td colspan="3">
				<html:textarea property="projectInfo.currentScheduleStage.currentScheduleTask.pstDescription" cols="50" rows="3"/>			
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
					
		
	</table>
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="90%" border="0">
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

window.onunload=function(){  
    chgFormOnsubmit('return true;');
	chgAction(document.all.method,'finishScheduleTask');
	projectSearchForm.submit();  		
}

 function chgAction(obj,str){
	obj.value=str;
 }
 
 
 function chgFormOnsubmit(str){  	
	projectSearchForm.onsubmit="function onsubmit(){" + str + "}";	
 }
 

function checkFormValidate()
{	 	
	var pssName = document.getElementById("projectInfo.currentScheduleStage.currentScheduleTask.pstName").value;	
	if(pssName == "")
	{
		alert("任务名称不能为空");
		return false;
	} 
	var personName = document.getElementById("projectInfo.currentScheduleStage.currentScheduleTask.responsiblePerson.person.personName").value;	
	if(personName == "")
	{
		alert("责任人不能为空");
		return false;
	} 
	var pssbd = document.getElementById("projectInfo.currentScheduleStage.currentScheduleTask.pstBeginDate").value;	
	if(pssbd == "")
	{
		alert("任务开始日期不能为空");
		return false;
	} 
	var pssed = document.getElementById("projectInfo.currentScheduleStage.currentScheduleTask.pstEndDate").value;	
	if(pssed == "")
	{
		alert("任务结束日期不能为空");
		return false;
	}  
	 	
	return true;
}

function submitForm()
{
	chgFormOnsubmit('return true;');
	chgAction(document.all.method,'refreshScheduleTask');
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

