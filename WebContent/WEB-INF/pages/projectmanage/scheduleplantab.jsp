<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<html:form action="projectManage.do" onsubmit="return checkFormValidate();">
	<html:errors />

	<input type="hidden" name="id" value="">
	<input type="hidden" name="method" value="saveSchedulePlan">

	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">			
		<tr><td><bean:message bundle="projectmanage" key="scheduleplan.logtitle" /></td></tr>
	</table>
			
	<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">							
		<thead>
			<tr>
				<td width="4%" align="center">
					<bean:message bundle="projectmanage" key="serial" />
				</td>
				<td width="10%" align="center">
					<bean:message bundle="projectmanage" key="scheduleplan.changetime" />
				</td>
				<td width="20%" align="center">
					<bean:message bundle="projectmanage" key="scheduleplan.stagetask" />
				</td>
				<td width="25%" align="center">
					<bean:message bundle="projectmanage" key="scheduleplan.changepoint" />
				</td>
				<td width="25%" align="center">
					<bean:message bundle="projectmanage" key="scheduleplan.changereason" />
				</td>
				<td width="8%" align="center">
					<bean:message bundle="projectmanage" key="scheduleplan.changeperson" />
				</td>
				<td align="center">
					<bean:message bundle="projectmanage" key="scheduleplan.checkperson" />
				</td>
			</tr>
		</thead>
		<tbody>					
						
		</tbody>
	</table>	

	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
		<tr><td>&nbsp;</td></tr>	
		<tr><td ><bean:message bundle="projectmanage" key="tabtitle.txtplantitle"/></td>						
			<td width="15%" align="center">
				<logic:equal name="accountPerson" property="hasCreatePower" value="true">	
					<html:button styleClass="addbutton" property="" onclick="openDialog('projectManage.do?method=addScheduleStage',600,200);">
						&nbsp;
					</html:button>
			
					<html:submit styleClass="savebutton">
						&nbsp;
					</html:submit>	
				</logic:equal>					
			</td>					
		</tr>
	</table>	
	

	<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">								
		<thead>
			<tr>
				<td width="4%" align="center">
					<bean:message bundle="projectmanage" key="serial" />
				</td>					
				<td width="20%" align="center">
					<bean:message bundle="projectmanage" key="scheduleplan.stagetask" />
				</td>
				<td width="4%" align="center">
					<bean:message bundle="projectmanage" key="project.workperiod" />
				</td>
				<td width="8%" align="center">
					<bean:message bundle="projectmanage" key="project.begindate" />
				</td>
				<td width="8%" align="center">
					<bean:message bundle="projectmanage" key="project.enddate" />
				</td>
				<td width="15%" align="center">
					<bean:message bundle="projectmanage" key="scheduleplan.pretask" />
				</td>
				<td width="20%" align="center">
					<bean:message bundle="projectmanage" key="scheduleplan.description" />
				</td>

				<td width="10%" align="center">
					<bean:message bundle="projectmanage" key="scheduleplan.responsibility" />
				</td>
				<td width="4%" align="center">
					<bean:message key="button.edit" />
				</td>
				<td width="4%" align="center">
					<bean:message bundle="projectmanage" key="schedulestage.addchild"/>
				</td>
				<td align="center">
					<bean:message key="button.delete" />
				</td>
			</tr>
		</thead>
		<tbody>					
			<logic:iterate name="projectSearchForm" property="projectInfo.scheduleStageList" id="ss" indexId="i">
				<logic:notEqual name="ss" property="pssFlag" value="-1">

				<tr class="odd">
					<td align="center">		
						<bean:size id="ts" name="ss" property="scheduleTaskList"/>
						<logic:greaterThan name="ts" value="0">					
							<logic:equal name="ss" property="taskExpendStatus" value="+">
								<a href='projectManage.do?method=collectScheduleStage&id=<bean:write name="ss" property="pssId"/>'><img border="0" src="pages\style\default\images\-.GIF"></a>
							</logic:equal>
							<logic:equal name="ss" property="taskExpendStatus" value="-">
								<a href='projectManage.do?method=expendScheduleStage&id=<bean:write name="ss" property="pssId"/>'><img border="0" src="pages\style\default\images\+.gif"></a>
							</logic:equal>
						</logic:greaterThan>
						<%=i+1 %>
					</td>
					<td><bean:write name="ss" property="pssName"/></td>									
					<td align="center"><bean:write name="ss" property="pssWorkPeriod"/></td>						
					<td align="center"><bean:write name="ss" property="pssBeginDate"/></td>
					<td align="center"><bean:write name="ss" property="pssEndDate"/></td>
					<td></td>
					<td><bean:write name="ss" property="pssDescription"/></td>
					<td align="center">
						<logic:notEmpty name="ss" property="responsiblePerson">
							<bean:write name="ss" property="responsiblePerson.person.personName"/>
						</logic:notEmpty>
					</td>
					<logic:empty name="ss" property="pssId">
						<td align="center">&nbsp;</td>
						<td align="center">&nbsp;</td>
						<td align="center">
							<logic:equal name="accountPerson" property="hasCreatePower" value="true">
								<a href='projectManage.do?method=deleteScheduleStageByIndex&index=<%=i%>'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
							</logic:equal>	
						</td>
					</logic:empty>		
					<logic:notEmpty name="ss" property="pssId">
						<td align="center">
							<logic:equal name="accountPerson" property="hasCreatePower" value="true">
								<a href='javascript:openDialog("projectManage.do?method=editScheduleStage&id=<bean:write name="ss" property="pssId"/>",600,200);'><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
							</logic:equal>
						</td>
						<td align="center">
							<logic:equal name="accountPerson" property="hasCreatePower" value="true">
								<a href='javascript:openDialog("projectManage.do?method=addScheduleTask&id=<bean:write name="ss" property="pssId"/>",600,200);'><img border="0" src="pages\images\icon\16x16\new.gif"></a>
							</logic:equal>
						</td>	
						<td align="center">
							<logic:equal name="accountPerson" property="hasCreatePower" value="true">
								<a href='javascript:if(confirm("确认要删除这条信息吗?")) {chgAction(document.all.id,"<bean:write name="ss" property="pssId"/>");chgAction(document.all.method,"deleteScheduleStage");projectSearchForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
							</logic:equal>
						</td>
					</logic:notEmpty>											
				</tr>
				<logic:equal name="ss" property="taskExpendStatus" value="+">
					<logic:iterate name="ss" property="scheduleTaskList" id="st" indexId="j" >	
					<logic:notEqual name="st" property="pstFlag" value="-1">				
				
						<tr class="even">
							<td align="right">	<%=j+1 %>)</td>							
							<td><bean:write name="st" property="pstName"/></td>									
							<td align="center"><bean:write name="st" property="pstWorkPeriod"/></td>						
							<td align="center"><bean:write name="st" property="pstBeginDate"/></td>
							<td align="center"><bean:write name="st" property="pstEndDate"/></td>
							<td>
								<logic:notEmpty name="st" property="preTask">
									<bean:write name="st" property="preTask.pstName"/>
								</logic:notEmpty>
							</td>
							<td><bean:write name="st" property="pstDescription"/></td>
							<td align="center">
								<logic:notEmpty name="st" property="responsiblePerson">
									<bean:write name="st" property="responsiblePerson.person.personName"/>
								</logic:notEmpty>
							</td>	
							<logic:empty name="st" property="pstId">
								<td align="center">&nbsp;</td>
								<td align="center">&nbsp;</td>
								<td align="center">
									<logic:equal name="accountPerson" property="hasCreatePower" value="true">
										<a href='projectManage.do?method=deleteScheduleTaskByIndex&stageIndex=<%=i%>&index=<%=j%>'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
									</logic:equal>
								</td>
							</logic:empty>		
							<logic:notEmpty name="st" property="pstId">
								<td align="center">
									<logic:equal name="accountPerson" property="hasCreatePower" value="true">
										<a href='javascript:openDialog("projectManage.do?method=editScheduleTask&id=<bean:write name="st" property="pstId"/>",600,200);'><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
									</logic:equal>
								</td>	
								<td>&nbsp;</td>	
								<td align="center">
									<logic:equal name="accountPerson" property="hasCreatePower" value="true">
										<a href='javascript:if(confirm("确认要删除这条信息吗?")) {chgAction(document.all.id,"<bean:write name="st" property="pstId"/>");chgAction(document.all.method,"deleteScheduleTask");projectSearchForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
									</logic:equal>
								</td>
							</logic:notEmpty>														
						</tr>
					</logic:notEqual>
					</logic:iterate>
				</logic:equal>
				</logic:notEqual>		
			</logic:iterate>			
		</tbody>
	</table>	
	
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
		<tr><td>&nbsp;</td></tr>	
		<tr><td width="80%"><bean:message bundle="projectmanage" key="tabtitle.chartplantitle"/></td></tr>
	</table>		

	<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">								
		<thead>
			<tr>
				<td width="4%" align="center">
					<bean:message bundle="projectmanage" key="serial" />
				</td>					
				<td width="20%" align="center">
					<bean:message bundle="projectmanage" key="scheduleplan.stagetask" />
				</td>
				<td width="4%" align="center">
					<bean:message bundle="projectmanage" key="project.workperiod" />						
				</td>
				
				<logic:iterate name="tableHeader" id="h" indexId="i">
					<logic:equal name="i" value="0">
						<td width="6%" align="center">
							<logic:notEmpty name="projectSearchForm" property="projectInfo.scheduleStageList">
								<a href="projectManage.do?method=displayScheduleGoBack"><img border="0" src="pages\style\default\images\firstPage.gif"></a>								
							</logic:notEmpty>
							<bean:write name="h"/>
						</td>
					</logic:equal>
					<logic:equal name="i" value="11">
						<td width="6%" align="center">
							<bean:write name="h"/>
							<logic:notEmpty name="projectSearchForm" property="projectInfo.scheduleStageList">
								<a href="projectManage.do?method=displayScheduleGoOn"><img border="0" src="pages\style\default\images\lastPage.gif"></a>								
							</logic:notEmpty>
						</td>
					</logic:equal>
					<logic:notEqual name="i" value="0">
						<logic:notEqual name="i" value="11">						
							<td width="6%" align="center"><bean:write name="h"/></td>
						</logic:notEqual>							
					</logic:notEqual>						
				</logic:iterate>				
			</tr>
		</thead>
		<tbody>					
			<logic:iterate name="projectSearchForm" property="projectInfo.scheduleStageList" id="ss" indexId="i">
			<logic:notEqual name="ss" property="pssFlag" value="-1">
			
				<tr class="odd">
					<td align="center">		
						<bean:size id="ts" name="ss" property="scheduleTaskList"/>
						<logic:greaterThan name="ts" value="0">							
						<logic:equal name="ss" property="taskExpendStatus" value="+">
							<a href='projectManage.do?method=collectScheduleStage&id=<bean:write name="ss" property="pssId"/>'><img border="0" src="pages\style\default\images\-.GIF"></a>
						</logic:equal>
						<logic:equal name="ss" property="taskExpendStatus" value="-">
							<a href='projectManage.do?method=expendScheduleStage&id=<bean:write name="ss" property="pssId"/>'><img border="0" src="pages\style\default\images\+.gif"></a>
						</logic:equal>
						</logic:greaterThan>
						<%=i+1 %>
					</td>
					<td><bean:write name="ss" property="pssName"/></td>									
					<td align="center"><bean:write name="ss" property="pssWorkPeriod"/></td>	
									
					<td colspan="12">													
						<img src="pages\style\default\images\black.gif" style="height:5px;width:<bean:write name="ss" property="beginDatePercent"/>%"/><img src="pages\style\default\images\top2bg.gif" style="height:10px;width:<bean:write name="ss" property="endDatePercent"/>%;" title="<bean:write name="ss" property="pssBeginDate"/>~<bean:write name="ss" property="pssEndDate"/>"/>											
					</td>											
				</tr>
				<logic:equal name="ss" property="taskExpendStatus" value="+">
					<logic:iterate name="ss" property="scheduleTaskList" id="st" indexId="j" >	
					<logic:notEqual name="st" property="pstFlag" value="-1">				
				
						<tr class="even">
							<td align="right">	<%=j+1 %>)</td>
							<td><bean:write name="st" property="pstName"/></td>									
							<td align="center"><bean:write name="st" property="pstWorkPeriod"/></td>
							
							<td colspan="12">
								<img src="pages\style\default\images\black.gif" style="height:5px;width:<bean:write name="st" property="beginDatePercent"/>%"/><img src="pages\style\default\images\top2bg.gif" style="height:5px;width:<bean:write name="st" property="endDatePercent"/>%;" title="<bean:write name="st" property="pstBeginDate"/>~<bean:write name="st" property="pstEndDate"/>"/>
								<br>
								<logic:iterate id="r" name="st" property="implementRecordList"><logic:notEqual name="r" property="tirFlag" value="-1"><img src="pages\style\default\images\black.gif" style="height:5px;width:<bean:write name="r" property="beginDatePercent"/>%"/><img src="pages\style\default\images\red.gif" style="height:5px;width:<bean:write name="r" property="endDatePercent"/>%;" title="<bean:write name="r" property="tirBeginDate"/>-<bean:write name="r" property="tirEndDate"/>:<bean:write name="r" property="tirDescription"/>"/></logic:notEqual></logic:iterate>								
							</td>													
						</tr>
					</logic:notEqual>
					</logic:iterate>	
				</logic:equal>	
			</logic:notEqual>
			</logic:iterate>			
		</tbody>
	</table>		
			
</html:form>