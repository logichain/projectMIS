<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>


<html:form action="projectManage.do" onsubmit="return checkFormValidate();">
	<html:errors />
	
	<input type="hidden" name="method" value="confirmTaskImplementRecord">				
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
		<caption><bean:message bundle="projectmanage" key="implement.title"/></caption>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td width="20%" align="right">
				<bean:message bundle="projectmanage" key="schedulestage.name" />：
			</td>
			<td width="15%">
				<bean:write name="projectSearchForm" property="projectInfo.currentScheduleStage.pssName"/>			
			</td>
			<td width="20%" align="right">
				<bean:message bundle="projectmanage" key="scheduletask.name" />：
			</td>
			<td  width="15%">
				<bean:write name="projectSearchForm" property="projectInfo.currentScheduleStage.currentScheduleTask.pstName"/>			
			</td>				
		
			<td  width="20%" align="right">
				<bean:message bundle="projectmanage" key="scheduleplan.responsibility" />：
			</td>
			<td>
				<bean:write name="projectSearchForm"  property="projectInfo.currentScheduleStage.currentScheduleTask.responsiblePerson.person.personName"/>				
				
			</td>
		</tr>
		<tr>			
			<td align="right"><bean:message bundle="projectmanage" key="scheduleplan.pretask" />：</td>
			<td>
				<logic:notEmpty name="projectSearchForm" property="projectInfo.currentScheduleStage.currentScheduleTask.preTask">
					<bean:write name="projectSearchForm" property="projectInfo.currentScheduleStage.currentScheduleTask.preTask.pstName"/>
				</logic:notEmpty>
			</td>
			
			<td align="right">
				<bean:message bundle="projectmanage" key="project.begindate" />： 
			</td>
			<td >						
				<bean:write name="projectSearchForm" property="projectInfo.currentScheduleStage.currentScheduleTask.pstBeginDate"/>
			</td>				
			<td align="right">
				<bean:message bundle="projectmanage" key="project.enddate" />： 
			</td>
			<td >						
				<bean:write name="projectSearchForm" property="projectInfo.currentScheduleStage.currentScheduleTask.pstEndDate"/>
			</td>
		</tr>
		<tr><td colspan="6" align="center">---------------------------------------------------------------------------------------------------------------------------------</td></tr>

		<tr>
			
			<td align="right">
				<bean:message bundle="projectmanage" key="implement.begindate" />： 
			</td>
			<td >						
				
				<html:text property="projectInfo.currentScheduleStage.currentScheduleTask.currentImplementRecord.tirBeginDate" readonly="true" size="10" maxlength="32" onclick="SelectDate(this)"/>
			</td>				
			<td align="right">
				<bean:message bundle="projectmanage" key="implement.enddate" />： 
			</td>
			<td >		
				<html:text property="projectInfo.currentScheduleStage.currentScheduleTask.currentImplementRecord.tirEndDate" readonly="true" size="10" maxlength="32" onclick="SelectDate(this)"/>				
				
			</td>
			<td align="right">
				<bean:message bundle="projectmanage" key="implement.percent" />： 
			</td>
			<td >						
				<html:select property="projectInfo.currentScheduleStage.currentScheduleTask.currentImplementRecord.tirImplementPercent" style="width:60">	
					<html:option value="0">0%</html:option>
					<html:option value="5">5%</html:option>
					<html:option value="10">10%</html:option>
					<html:option value="15">15%</html:option>
					<html:option value="20">20%</html:option>
					<html:option value="25">25%</html:option>
					<html:option value="30">30%</html:option>
					<html:option value="35">35%</html:option>
					<html:option value="40">40%</html:option>
					<html:option value="45">45%</html:option>
					<html:option value="50">50%</html:option>
					<html:option value="55">55%</html:option>
					<html:option value="60">60%</html:option>
					<html:option value="65">65%</html:option>
					<html:option value="70">70%</html:option>
					<html:option value="75">75%</html:option>
					<html:option value="80">80%</html:option>
					<html:option value="85">85%</html:option>
					<html:option value="90">90%</html:option>
					<html:option value="95">95%</html:option>
					<html:option value="100">100%</html:option>
				</html:select>
			</td>
		</tr>

		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="implement.description" />：
			</td>
			<td colspan="5">
				<html:textarea property="projectInfo.currentScheduleStage.currentScheduleTask.currentImplementRecord.tirDescription" rows="3" cols="50"/>			
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

 window.onunload=function(){  
    chgFormOnsubmit('return true;');
	chgAction(document.all.method,'finishImplementRecord');
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
	var bd = document.getElementById("projectInfo.currentScheduleStage.currentScheduleTask.currentImplementRecord.tirBeginDate").value;	
	if(bd == "")
	{
		alert("开始日期不能为空");
		return false;
	} 
	var ed = document.getElementById("projectInfo.currentScheduleStage.currentScheduleTask.currentImplementRecord.tirEndDate").value;	
	if(ed == "")
	{
		alert("结束日期不能为空");
		return false;
	} 
	var percent = document.getElementById("projectInfo.currentScheduleStage.currentScheduleTask.currentImplementRecord.tirImplementPercent").value;	
	if(percent == "")
	{
		alert("完成比例不能为空");
		return false;
	} 
	
	 	
	return true;
}


</script>

