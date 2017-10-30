<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>


<html:form action="projectManage.do" onsubmit="return checkFormValidate();">
	<html:errors />
	
	<input type="hidden" name="method" value="confirmScheduleStage">				
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="90%" border="0">
		<caption><bean:message bundle="projectmanage" key="schedulestage.caption" /></caption>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td width="15%" align="right">
				<bean:message bundle="projectmanage" key="schedulestage.name" />：
			</td>
			<td  width="30%">
				<html:text styleId="scheduleStageName" property="projectInfo.currentScheduleStage.pssName" size="12" maxlength="45"/>			
			</td>				
		
			<td  width="15%" align="right">
				<bean:message bundle="projectmanage" key="scheduleplan.responsibility" />：
			</td>
			<td>
				<html:text styleId="responsiblePerson" property="projectInfo.currentScheduleStage.responsiblePerson.person.personName" size="12" maxlength="100" readonly="true" style="background-color:LightGray;"/>					
				<html:button property="" onclick="openDialog('projectManage.do?method=searchAccount&opType=stageRes',800,420);">...</html:button>
			</td>
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="project.begindate" />： 
			</td>
			<td >						
				<html:text styleId="beginDate" property="projectInfo.currentScheduleStage.pssBeginDate" readonly="true" size="12" maxlength="32" onclick="SelectDate(this)"/>
			</td>				
			<td align="right">
				<bean:message bundle="projectmanage" key="project.enddate" />： 
			</td>
			<td >						
				<html:text styleId="endDate" property="projectInfo.currentScheduleStage.pssEndDate" readonly="true" size="12" maxlength="32" onclick="SelectDate(this)"/>
			</td>
		</tr>

		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="scheduleplan.description" />：
			</td>
			<td colspan="3">
				<html:textarea property="projectInfo.currentScheduleStage.pssDescription" cols="50" rows="3"/>			
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
	chgAction(document.all.method,'finishScheduleStage');
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
	var pssName = document.getElementById("scheduleStageName").value;	
	if(pssName == "")
	{
		alert("阶段名称不能为空");
		return false;
	} 
	var personName = document.getElementById("responsiblePerson").value;	
	if(personName == "")
	{
		alert("责任人不能为空");
		return false;
	} 
	var pssbd = document.getElementById("beginDate").value;	
	if(pssbd == "")
	{
		alert("阶段开始日期不能为空");
		return false;
	} 
	var pssed = document.getElementById("endDate").value;	
	if(pssed == "")
	{
		alert("阶段结束日期不能为空");
		return false;
	} 
	 	
	return true;
}

function submitForm()
{
	chgFormOnsubmit('return true;');
	chgAction(document.all.method,'refreshScheduleStage');
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

