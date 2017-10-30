<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<html:form action="tenderProjectManage.do" onsubmit="return checkFormValidate()&&validateTenderProjectForm(this);">
	<input type="hidden" name="method" value="saveTenderBudget">
		<html:errors />
	<table width="100%"><tr>
		<td align="left" width="30%">
			<bean:message bundle="projectmanage" key="task.checkresult"/>：			
			<logic:equal name="tenderProjectForm" property="tenderInfo.tenderBudget.pbCheckResult" value="1">
				评审中
			</logic:equal>
			<logic:equal name="tenderProjectForm" property="tenderInfo.tenderBudget.pbCheckResult" value="2">
				驳回
			</logic:equal>
			<logic:equal name="tenderProjectForm" property="tenderInfo.tenderBudget.pbCheckResult" value="3">
				通过
			</logic:equal>	
			
		</td>
		<td align="center" width="30%">
			付款方式：
			<!-- 选择支付方式 -->
			<logic:equal name="accountPerson" property="hasCreatePower" value="true">				
				<logic:equal name="tenderProjectForm" property="tenderInfo.tenderBudget.pbStatus" value="0">
					<logic:equal name="tenderProjectForm" property="tenderInfo.tenderBudget.pbCheckResult" value="0">
						<html:checkbox name="tenderProjectForm" property="tenderInfo.tenderBudget.pbOnAccount" value="1" onchange="tenderProjectForm.submit();">垫付</html:checkbox>
						<html:hidden name="tenderProjectForm" property="tenderInfo.tenderBudget.pbOnAccount" value="-1"/>
					</logic:equal>
					<logic:equal name="tenderProjectForm" property="tenderInfo.tenderBudget.pbCheckResult" value="2">
						<html:checkbox name="tenderProjectForm" property="tenderInfo.tenderBudget.pbOnAccount" value="1" onchange="tenderProjectForm.submit();">垫付</html:checkbox>
						<html:hidden name="tenderProjectForm" property="tenderInfo.tenderBudget.pbOnAccount" value="-1"/>
					</logic:equal>
				</logic:equal>
			</logic:equal>					
			<!--显示支付方式 -->
			<logic:notEqual name="accountPerson" property="hasCreatePower" value="true">
				<html:checkbox disabled="true" name="tenderProjectForm" property="tenderInfo.tenderBudget.pbOnAccount" value="1">垫付</html:checkbox>
			</logic:notEqual>	
			<logic:equal name="accountPerson" property="hasCreatePower" value="true">				
				<logic:notEqual name="tenderProjectForm" property="tenderInfo.tenderBudget.pbStatus" value="0">
					<html:checkbox disabled="true" name="tenderProjectForm" property="tenderInfo.tenderBudget.pbOnAccount" value="1">垫付</html:checkbox>
				</logic:notEqual>
			</logic:equal>
			<logic:equal name="accountPerson" property="hasCreatePower" value="true">				
				<logic:equal name="tenderProjectForm" property="tenderInfo.tenderBudget.pbStatus" value="0">
					<logic:equal name="tenderProjectForm" property="tenderInfo.tenderBudget.pbCheckResult" value="1">
						<html:checkbox disabled="true" name="tenderProjectForm" property="tenderInfo.tenderBudget.pbOnAccount" value="1">垫付</html:checkbox>
					</logic:equal>
					<logic:equal name="tenderProjectForm" property="tenderInfo.tenderBudget.pbCheckResult" value="3">
						<html:checkbox disabled="true" name="tenderProjectForm" property="tenderInfo.tenderBudget.pbOnAccount" value="1">垫付</html:checkbox>
					</logic:equal>
				</logic:equal>
			</logic:equal>	
		</td>		
		<td align="right">					
<%-- 			<html:button property="" styleClass="checklogbutton" onclick="openDialog('tenderProjectManage.do?method=showBudgetChecktaskList&type=5',800,360);" style="cursor: hand;"> --%>
<!-- 				&nbsp; -->
<%-- 			</html:button>		 --%>
				
			<logic:equal name="accountPerson" property="hasCreatePower" value="true">				
				<logic:equal name="tenderProjectForm" property="tenderInfo.tenderBudget.pbStatus" value="0">
					<logic:equal name="tenderProjectForm" property="tenderInfo.tenderBudget.pbCheckResult" value="0">
						<a href='tenderProjectManage.do?method=checkTenderBudgetBegin'><img border="0" src="pages\style\default\mis\sendcheck.jpg"></a>
					</logic:equal>
					<logic:equal name="tenderProjectForm" property="tenderInfo.tenderBudget.pbCheckResult" value="2">
						<a href='tenderProjectManage.do?method=checkTenderBudgetBegin'><img border="0" src="pages\style\default\mis\sendcheck.jpg"></a>
					</logic:equal>
				</logic:equal>
			</logic:equal>			
		</td>
	</tr></table>
		<table class="sort-table" WIDTH="100%" border="0">
			<thead>
				<tr>
					<td width="5%"></td><td width="5%"></td><td width="30%"></td><td width="15%"></td><td width="40%"></td><td></td>
				</tr>
				<tr>
					<td align="center">
						<bean:message bundle="budgetmanage" key="budget.indexNo" />
					</td>
					<td colspan="2" align="center">
						<bean:message bundle="budgetmanage" key="budget.budgetItem" />
					</td>
					<td align="center">
						<bean:message bundle="budgetmanage" key="budget.income" />
					</td>
					<td align="center">
						<bean:message bundle="budgetmanage" key="budget.remark" />
					</td>
					<td align="center">
						<bean:message key="button.edit" />
					</td>
				</tr>
			</thead>
			<tbody>
				<%int a = 0;for(int i=7;i<=45;i++){%>				
				<logic:equal name="tenderProjectForm" property='<%= "tenderInfo.tenderBudget.e" + i + ".itemModel.bimTenderFlag" %>' value="1">
				<tr>
					<logic:equal name="tenderProjectForm" property='<%= "tenderInfo.tenderBudget.e" + i + ".itemModel.bimGatherFlag" %>' value="1">
						<%a++; %>
						<td align="center"><%=a%></td>					
						<td colspan="2" align="center"><bean:write name="tenderProjectForm" property='<%= "tenderInfo.tenderBudget.e" + i + ".itemModel.bimName" %>'/></td>
					</logic:equal>
					<logic:equal name="tenderProjectForm" property='<%= "tenderInfo.tenderBudget.e" + i + ".itemModel.bimGatherFlag" %>' value="0">
						<td>&nbsp;</td>
						<td align="center"><bean:write name="tenderProjectForm" property='<%= "tenderInfo.tenderBudget.e" + i + ".itemModel.bimCode" %>'/></td>
						<td align="center"><bean:write name="tenderProjectForm" property='<%= "tenderInfo.tenderBudget.e" + i + ".itemModel.bimName" %>'/></td>
					</logic:equal>				
					<td align="center"><bean:write name="tenderProjectForm" property='<%= "tenderInfo.tenderBudget.e" + i + "AmountStr" %>'/></td>						
					<td align="left"><bean:write name="tenderProjectForm" property='<%= "tenderInfo.tenderBudget.e" + i + ".biRemark" %>'/></td>					
			        <td align="center">
						<logic:equal name="accountPerson" property="hasCreatePower" value="true">
			        	<logic:equal name="tenderProjectForm" property="tenderInfo.tenderBudget.pbStatus" value="0">
				        	<logic:equal name="tenderProjectForm" property="tenderInfo.tenderBudget.pbCheckResult" value="0">
						        <logic:equal name="tenderProjectForm" property='<%= "tenderInfo.tenderBudget.e" + i + ".itemModel.bimTenderEditable" %>' value="1">
						        	<a href="javascript:openDialog('tenderProjectManage.do?method=editDetailTenderBugetItem&id=<bean:write name="tenderProjectForm" property='<%= "tenderInfo.tenderBudget.e" + i + ".biId" %>'/>', 500,160);void(0);"><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
						        </logic:equal>
					        </logic:equal>
				        	<logic:equal name="tenderProjectForm" property="tenderInfo.tenderBudget.pbCheckResult" value="2">
						        <logic:equal name="tenderProjectForm" property='<%= "tenderInfo.tenderBudget.e" + i + ".itemModel.bimTenderEditable" %>' value="1">
						        	<a href="javascript:openDialog('tenderProjectManage.do?method=editDetailTenderBugetItem&id=<bean:write name="tenderProjectForm" property='<%= "tenderInfo.tenderBudget.e" + i + ".biId" %>'/>', 500,160);void(0);"><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
						        </logic:equal>
					        </logic:equal>
				        </logic:equal>
						</logic:equal>
			        </td>
				</tr>
				</logic:equal>
				<%}%>
				
			</tbody>
		</table>
		
	<table class="normal-table" cellSpacing="1" cellPadding="1" width="100%" border="0">		
		<thead>
			<tr>
				<td width="5%" align="center">
					<bean:message bundle="projectmanage" key="serial" />
				</td>
				<td width="10%" align="center">
					<bean:message bundle="checktask" key="task.receiveDept" />
				</td>			
				<td width="10%" align="center">
					<bean:message bundle="checktask" key="task.receivePost" />
				</td>
				<td width="16%"  align="center">
					<bean:message bundle="checktask" key="task.checkableUser" />
				</td>
				<td width="10%" align="center">
					<bean:message bundle="checktask" key="task.checkUser" />
				</td>
				<td width="10%" align="center">
					<bean:message bundle="checktask" key="task.checkTime" />
				</td>
				<td width="8%" align="center">
					<bean:message bundle="checktask" key="task.checkResult" />
				</td>
				<td align="center">
					<bean:message bundle="checktask" key="task.remark" />
				</td>						
			</tr>
		</thead>
		<tbody>		
			<logic:iterate name="taskList" id="task" indexId="i">				  
				<% int b = ((org.king.common.checktask.CheckTask)task).getCtCheckStep()%2;request.setAttribute("b",b);%>
				<logic:equal name="b" value="0"><tr class="blue"></logic:equal>
				<logic:equal name="b" value="1"><tr class="odd"></logic:equal>
					<td align="center">										
						<%=i+1 %>
						<logic:equal name="task" property="ctCheckStep" value="1">↓</logic:equal>
						<logic:notEqual name="task" property="ctCheckStep" value="1">&nbsp;&nbsp;</logic:notEqual>
					</td>
					<td align="center">
						<bean:write name="task" property="receiveDepartment.DName"/>						
					</td>
									
					<td align="center">
						<logic:notEmpty name="task" property="receivePost">
							<bean:write name="task" property="receivePost.PName"/>
						</logic:notEmpty>
						<logic:empty name="task" property="receivePost">部门经理	</logic:empty>
					</td>
					<td align="center"><bean:write name="task" property="checkableUserList"/></td>
					<td align="center">
						<logic:notEmpty name="task" property="checkUser">
							<bean:write name="task" property="checkUser.person.personName"/>
						</logic:notEmpty>
					</td>
					<td align="center"><bean:write name="task" property="ctCheckTime"/></td>
					<td align="center">
						<logic:equal name="task" property="ctCheckResult" value="1">通过</logic:equal>
						<logic:equal name="task" property="ctCheckResult" value="2">驳回</logic:equal>
					</td>	
					<td>
						<logic:empty name="task" property="ctRemark">--</logic:empty>
						<logic:notEmpty name="task" property="ctRemark"><bean:write name="task" property="ctRemark"/></logic:notEmpty>
					</td>	
					<!--td align="center">									
						<a href="javascript:openDialog('projectManage.do?method=displayCheckTaskDetail&id=<bean:write name="task" property="ctId"/>',700,400)"><img border="0" src="pages\images\icon\16x16\display.gif"></a>
					</td-->															
				</tr>			
			</logic:iterate>		 
		</tbody>
	</table>

	</html:form>
	
<html:javascript formName="tenderProjectForm" dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript" src="<html:rewrite forward='staticjavascript'/>"></script>