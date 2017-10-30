<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<html:form action="projectManage.do" onsubmit="return checkFormValidate();">
	<html:errors />

	<input type="hidden" name="id" value="">
	<input type="hidden" name="method" value="saveSchedule">		
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
		<tr><td>&nbsp;</td></tr>	
		<tr>
			<td >&nbsp;</td>						
			<td width="10%"align="center">
				<logic:equal name="accountPerson" property="hasCreatePower" value="true">	
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
				<td width="18%" align="center">
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
				<td width="4%" align="center">
					<bean:message bundle="projectmanage" key="schedule.percent" />
				</td>
				
				<td width="8%" align="center">
					<bean:message bundle="projectmanage" key="schedule.updatedate" />
				</td>
				<td width=20%" align="center">
					<bean:message bundle="projectmanage" key="scheduleplan.description" />
				</td>

				<td width="7%" align="center">
					<bean:message bundle="projectmanage" key="scheduleplan.responsibility" />
				</td>
				<td align="center">
					<bean:message  bundle="projectmanage" key="schedule.update"/>
				</td>
			</tr>
		</thead>
		<tbody>					
			<logic:iterate name="projectSearchForm" property="projectInfo.scheduleStageList" id="ss" indexId="i">
			<logic:notEqual name="ss" property="pssFlag" value="-1">
				<% int a = i%2;request.setAttribute("a",a);%>
				<tr class="odd">
					<td align="center">
						<%=i+1 %>							
					</td>
					<td><bean:write name="ss" property="pssName"/></td>									
					<td align="center"><bean:write name="ss" property="pssWorkPeriod"/></td>						
					<td align="center"><bean:write name="ss" property="pssBeginDate"/></td>
					<td align="center"><bean:write name="ss" property="pssEndDate"/></td>
					<td></td>
					<td></td>
					<td></td>
					<td><bean:write name="ss" property="pssDescription"/></td>
					<td align="center">
						<logic:notEmpty name="ss" property="responsiblePerson">
							<bean:write name="ss" property="responsiblePerson.person.personName"/>
						</logic:notEmpty>
					</td>						
					<td align="center"></td>
																	
				</tr>
				<logic:iterate name="ss" property="scheduleTaskList" id="st" indexId="j" >
				<logic:notEqual name="st" property="pstFlag" value="-1">					
					<% int b = j%2;request.setAttribute("b",b);%>
					<tr class="even">
						<td align="right">	
							<bean:size id="rs" name="st" property="implementRecordList"/>
							<logic:greaterThan name="rs" value="0">
							<logic:equal name="st" property="recordExpendStatus" value="+">
								<a href='projectManage.do?method=collectScheduleTask&id=<bean:write name="st" property="pstId"/>'><img border="0" src="pages\style\default\images\-.GIF"></a>
							</logic:equal>
							<logic:equal name="st" property="recordExpendStatus" value="-">
								<a href='projectManage.do?method=expendScheduleTask&id=<bean:write name="st" property="pstId"/>'><img border="0" src="pages\style\default\images\+.gif"></a>
							</logic:equal>
							</logic:greaterThan>
							<%=j+1 %>)
						</td>							
						<td><bean:write name="st" property="pstName"/></td>									
						<td align="center"><bean:write name="st" property="pstWorkPeriod"/></td>						
						<td align="center"><bean:write name="st" property="pstBeginDate"/></td>
						<td align="center"><bean:write name="st" property="pstEndDate"/></td>
						<td>
							<logic:notEmpty name="st" property="preTask">
								<bean:write name="st" property="preTask.pstName"/>
							</logic:notEmpty>
						</td>
						<td><bean:write name="st" property="pstFinishPercentry"/>
							<logic:notEmpty name="st" property="pstFinishPercentry">%</logic:notEmpty>
						</td>
						
						<td align="center">
							<logic:notEmpty name="st" property="pstFinishChangeTime">
								<bean:define id="fd" name="st" property="pstFinishChangeTime"></bean:define>
								<fmt:formatDate value="${fd}" type="date" dateStyle="default"/>
							</logic:notEmpty>
						</td>
						<td><bean:write name="st" property="pstDescription"/></td>
						<td align="center">
							<logic:notEmpty name="st" property="responsiblePerson">
								<bean:write name="st" property="responsiblePerson.person.personName"/>
							</logic:notEmpty>
						</td>								
						<td align="center">
							<logic:equal name="accountPerson" property="hasCreatePower" value="true">
								<logic:notEmpty name="st" property="pstId">
									<a href='javascript:openDialog("projectManage.do?method=addTaskImplementRecord&id=<bean:write name="st" property="pstId"/>",600,210);'><img border="0" src="pages\images\icon\16x16\new.gif"></a>
								</logic:notEmpty>
							</logic:equal>
						</td>	
																				
					</tr>
					<logic:greaterThan name="rs" value="0">
					<logic:equal name="st" property="recordExpendStatus" value="+">
					<tr>
						<td></td>
						<td colspan="10">
							<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">								
								<thead>
									<tr>
										<td width="5%" align="center">
											<bean:message bundle="projectmanage" key="serial" />
										</td>					
										<td width="10%" align="center">
											<bean:message bundle="projectmanage" key="implement.begindate" />
										</td>
										<td width="10%" align="center">
											<bean:message bundle="projectmanage" key="implement.enddate" />
										</td>
										<td width="6%" align="center">
											<bean:message bundle="projectmanage" key="implement.finish" />
										</td>
										<td  width="60%" align="center">
											<bean:message bundle="projectmanage" key="implement.description" />
										</td>
										<td width="5%" align="center">
											<bean:message key="button.edit" />
										</td>
										<td align="center">
											<bean:message key="button.delete" />
										</td>
									</tr>
								</thead>
								<tbody>
									<logic:iterate id="r" name="st" property="implementRecordList" indexId="m">
									<logic:notEqual name="r" property="tirFlag" value="-1">
										<tr>
											<td align="center">	<%=m+1 %></td>
											<td><bean:write name="r" property="tirBeginDate"/></td>
											<td><bean:write name="r" property="tirEndDate"/></td>
											<td><bean:write name="r" property="tirImplementPercent"/>%</td>
											<td><bean:write name="r" property="tirDescription"/></td>
											<logic:empty name="r" property="tirId">
												<td align="center">&nbsp;</td>
												<td align="center">
													<logic:equal name="accountPerson" property="hasCreatePower" value="true">
														<a href='projectManage.do?method=deleteTaskImplementRecordByIndex&stageIndex=<%=i %>&taskIndex=<%=j %>&index=<%=m %>'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
													</logic:equal>
												</td>
											</logic:empty>		
											<logic:notEmpty name="r" property="tirId">
												<td align="center">
													<logic:equal name="accountPerson" property="hasCreatePower" value="true">
														<a href='javascript:openDialog("projectManage.do?method=editTaskImplementRecord&id=<bean:write name="r" property="tirId"/>",600,210);'><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
													</logic:equal>
												</td>
												<td align="center">
													<logic:equal name="accountPerson" property="hasCreatePower" value="true">
														<a href='javascript:if(confirm("确认要删除这条信息吗?")) {chgAction(document.all.id,"<bean:write name="r" property="tirId"/>");chgAction(document.all.method,"deleteTaskImplementRecord");projectSearchForm.submit();}'><img border="0" src="pages\images\icon\16x16\delete.gif"></a>
													</logic:equal>
												</td>
											</logic:notEmpty>	
										</tr>
									</logic:notEqual>
									</logic:iterate>
								</tbody>
							</table>

						</td>
					</tr>
					</logic:equal>	
					</logic:greaterThan>
				</logic:notEqual>		
				</logic:iterate>
			</logic:notEqual>
			</logic:iterate>			
		</tbody>
	</table>
				

</html:form>