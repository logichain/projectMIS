<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

		
<html:form action="projectManage.do">
	<html:errors />
	<input type="hidden" name="method" value="searchCheckTask">
	<input type="hidden" name="status" value="2">
	<table class="win" CELLPADDING="2" CELLSPACING="0" width="100%" border="0">	
		<tr><td width="10%"></td><td width="20%"></td><td width="10%"></td><td width="20%"></td><td width="10%"></td><td></td></tr>			
		<tr>
			<td align="right">
				<bean:message bundle="checktask" key="task.type" />：
			</td>
			<td >
				<html:select property="checkTaskInfo.ctTaskType" style="width:120">		
					<html:option value=""></html:option>
					<html:option value="1">合同评审</html:option>	
					<html:option value="2">立项备案评审</html:option>
					<html:option value="3">投标评审</html:option>	
					<html:option value="4">标书评审</html:option>
					<html:option value="5">预算标前评审</html:option>
					<html:option value="6">预算执行评审</html:option>				
				</html:select>						
			</td>
			
			<td align="right">
				<bean:message bundle="checktask" key="task.sendtime" />：
			</td>			
			<td colspan="2">						
				<html:text property="checkTaskInfo.sendTimeBegin" readonly="true" size="18" maxlength="32" onclick="SelectDate(this)"/>
				~						
				<html:text property="checkTaskInfo.sendTimeEnd" readonly="true" size="18" maxlength="32" onclick="SelectDate(this)"/>
			</td>
		</tr>	
		<tr>										
			<td align="right">
				<bean:message bundle="checktask" key="task.dept" />：
			</td>
			<td>
				<html:select property="checkTaskInfo.ctReceiveDept" style="width:120">	
					<html:option value=""></html:option>
					<html:options collection="deptList" property="DId" labelProperty="DName"/>
				</html:select>				
			</td>
			<td align="right">
				<bean:message bundle="checktask" key="task.checkTime" />：
			</td>			
			<td colspan="2">						
				<html:text property="checkTaskInfo.checkTimeBegin" readonly="true" size="18" maxlength="32" onclick="SelectDate(this)"/>
				~						
				<html:text property="checkTaskInfo.checkTimeEnd" readonly="true" size="18" maxlength="32" onclick="SelectDate(this)"/>
			</td>
				
			<td align="left">
				<bean:message bundle="checktask" key="task.senduser" />：
			
				<html:text property="checkTaskInfo.sendUser.person.personName" size="18" maxlength="100"/>								
			</td>
		</tr>
		<tr>
			<td align="right">
				<bean:message key="order_item"/>:
			</td>
			<td align="left">						
				<html:select property="checkTaskInfo.orderColumn" style="width:120" onchange="projectSearchForm.submit();">	
					<html:option value=""></html:option>
					<html:option value="ctTaskType"><bean:message bundle="checktask" key="task.type" /></html:option>
					<html:option value="sendUser.person.personName"><bean:message bundle="checktask" key="task.senduser" /></html:option>
					<html:option value="ctSendTime"><bean:message bundle="checktask" key="task.sendtime" /></html:option>
					<html:option value="ctCheckTime"><bean:message bundle="checktask" key="task.checkTime" /></html:option>
				</html:select>
				<html:checkbox property="checkTaskInfo.ascFlag">↑</html:checkbox>
				<html:hidden property="checkTaskInfo.ascFlag" value="false"/>
			</td>
			<td align="right"><bean:message key="page_itemcount"/>:</td>
			<td align="left">
				<html:select property="checkTaskInfo.pageItemCount" style="width:120px" onchange="projectSearchForm.submit();">	
					<html:option value="20">20</html:option>
					<html:option value="30">30</html:option>
					<html:option value="40">40</html:option>
					<html:option value="50">50</html:option>
					<html:option value="60">60</html:option>																	
				</html:select>
			</td>		
			<td colspan="2" align="right">
				<html:submit styleClass="searchbutton" onclick="initPageNo();">
					&nbsp;
				</html:submit>
				<html:submit styleClass="resetbutton" onclick="initPageNo();chgAction(document.all.method,'resetSearchCheckTask');">
					&nbsp;
				</html:submit>
			</td>
		</tr>
	</table>
							
	<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">		
		<thead>
			<tr>
				<td width="4%" align="center">
					<bean:message bundle="projectmanage" key="serial" />
				</td>
				<td align="center">
					<bean:message bundle="checktask" key="task.name" />
				</td>			
				<td width="5%" align="center">
					<bean:message bundle="checktask" key="task.status" />
				</td>
				<td width="10%" align="center">
					<bean:message bundle="checktask" key="task.type" />
				</td>
				<td width="15%" align="center">
					<bean:message bundle="checktask" key="task.checkableUser" />
				</td>
				<td width="10%" align="center">
					<bean:message bundle="checktask" key="task.dept" />
				</td>
				<td width="5%" align="center">
					<bean:message bundle="checktask" key="task.senduser" />
				</td>
				<td width="12%" align="center">
					<bean:message bundle="checktask" key="task.sendtime" />
				</td>	
				<td width="5%" align="center">
					<bean:message bundle="checktask" key="task.checkUser" />
				</td>
				<td width="12%" align="center">
					<bean:message bundle="checktask" key="task.checkTime" />
				</td>			
				<td width="5%" align="center">
					<bean:message bundle="checktask" key="task.read" />
				</td>
			</tr>
		</thead>
		<tbody>
			<!--page offset start -->
			<bean:define id="pageCount" name="projectSearchForm" property="checkTaskInfo.pageItemCount"></bean:define>
			<bean:define id="itemNo" name="taskCount"></bean:define>
			<pg:pager url="./projectManage.do" items="<%=Integer.valueOf(itemNo.toString())%>" index="center" maxPageItems="<%=Integer.valueOf(pageCount.toString())%>" maxIndexPages="10" isOffset="<%= true %>" export="offset,currentPageNumber=pageNumber" scope="request">
				<%-- keep track of preference --%>						
				<pg:param name="method" value="searchCheckTask"/>
				<pg:param name="status"/>

				<%-- save pager offset during form changes --%>
				<input type="hidden" id="pageOffset" name="pager.offset" value="<%= offset %>"/>
				<logic:iterate name="taskList" id="task" indexId="i">
					<pg:item>
					  <tr>
						<% int a = i%2;request.setAttribute("a",a);%>
						<logic:equal name="a" value="0"><tr class="even"></logic:equal>
						<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
							<td align="center">										
								<%=i+1 %>
							</td>
							<td>
								<logic:equal name="task" property="ctTaskType" value="1">
									<bean:write name="task" property="contract.pcTitle"/>
								</logic:equal>
								<logic:notEmpty name="task" property="projectApprovalRecord">
									<bean:write name="task" property="projectApprovalRecord.parProjectName"/>
								</logic:notEmpty>
								
								<logic:equal name="task" property="ctTaskType" value="5">
									<bean:write name="task" property="project.tpName"/>
								</logic:equal>
								<logic:equal name="task" property="ctTaskType" value="6">
									<bean:write name="task" property="project.tpName"/>
								</logic:equal>
								<logic:equal name="task" property="ctTaskType" value="4">
									<bean:write name="task" property="project.tpName"/>
								</logic:equal>
							</td>									
							
							<td align="center">
								<logic:equal name="task" property="ctTaskType" value="1">
									<logic:equal name="task" property="contract.pcStatus" value="1">评审中</logic:equal>
									<logic:equal name="task" property="contract.pcStatus" value="2">驳回</logic:equal>
									<logic:equal name="task" property="contract.pcStatus" value="3">通过</logic:equal>
								</logic:equal>
								<logic:notEmpty name="task" property="projectApprovalRecord">
									<logic:equal name="task" property="projectApprovalRecord.parStatus" value="1">评审中</logic:equal>
									<logic:equal name="task" property="projectApprovalRecord.parStatus" value="2">驳回</logic:equal>
									<logic:equal name="task" property="projectApprovalRecord.parStatus" value="3">通过</logic:equal>
									<logic:equal name="task" property="projectApprovalRecord.parStatus" value="4">评审中</logic:equal>
									<logic:equal name="task" property="projectApprovalRecord.parStatus" value="5">驳回</logic:equal>
									<logic:equal name="task" property="projectApprovalRecord.parStatus" value="6">通过</logic:equal>
								</logic:notEmpty>
								<logic:equal name="task" property="ctTaskType" value="5">
									<logic:equal name="task" property="project.tenderBudget.pbCheckResult" value="1">评审中</logic:equal>
									<logic:equal name="task" property="project.tenderBudget.pbCheckResult" value="2">驳回</logic:equal>
									<logic:equal name="task" property="project.tenderBudget.pbCheckResult" value="3">通过</logic:equal>
								</logic:equal>
								<logic:equal name="task" property="ctTaskType" value="6">
									<logic:equal name="task" property="project.applyBudget.pbCheckResult" value="1">评审中</logic:equal>
									<logic:equal name="task" property="project.applyBudget.pbCheckResult" value="2">驳回</logic:equal>
									<logic:equal name="task" property="project.applyBudget.pbCheckResult" value="3">通过</logic:equal>
								</logic:equal>
								<logic:equal name="task" property="ctTaskType" value="4">
								<logic:equal name="task" property="project.tpTenderdocCheckStatus" value="1">评审中</logic:equal>
								<logic:equal name="task" property="project.tpTenderdocCheckStatus" value="2">驳回</logic:equal>
								<logic:equal name="task" property="project.tpTenderdocCheckStatus" value="3">通过</logic:equal>
							</logic:equal>
							</td>
							
							<td align="center">
								<logic:equal name="task" property="ctTaskType" value="1">合同评审</logic:equal>
								<logic:equal name="task" property="ctTaskType" value="2">立项备案评审</logic:equal>
								<logic:equal name="task" property="ctTaskType" value="3">投标评审</logic:equal>
								<logic:equal name="task" property="ctTaskType" value="4">标书评审</logic:equal>
								<logic:equal name="task" property="ctTaskType" value="5">预算标前评审</logic:equal>
								<logic:equal name="task" property="ctTaskType" value="6">预算执行评审</logic:equal>	
							</td>
							<td align="center"><bean:write name="task" property="checkableUserList"/></td>
							<td align="center"><bean:write name="task" property="receiveDepartment.DName"/></td>
							<td align="center"><bean:write name="task" property="sendUser.person.personName"/></td>		
							<td align="center"><bean:write name="task" property="ctSendTime"/></td>
							<td align="center"><bean:write name="task" property="checkUser.person.personName"/></td>
							<td align="center"><bean:write name="task" property="ctCheckTime"/></td>
							<td align="center">
								<a href='javascript:openDialog("projectManage.do?method=readCheckTask&id=<bean:write name="task" property="ctId"/>",800,500);'><img border="0" src="pages\images\icon\16x16\display.gif"></a>
							</td>										
						</tr>
					</pg:item>
				</logic:iterate>
	
			<jsp:include page="../common/page.jsp" flush="true" />
			</pg:pager>
			<logic:equal name="itemNo" value="0">
				<tr>
					<td COLSPAN="9">
						<bean:message key="noRecord" />
					</td>
				</tr>
			</logic:equal>	
		</tbody>
	</table>					
</html:form>
	


<script language="JavaScript">

 function chgAction(obj,str){
	obj.value=str;	
 }
 
 
 function initPageNo()
 {
 	document.getElementById("pageOffset").value='0';
 }
 
  function openDialog(loadpos,WWidth,WHeight)//Lock   Size 
{   	
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);   
	var WTop = Math.ceil((window.screen.height - WHeight) / 2); 
	var features = 'width=' + WWidth + 'px,' +	'height=' + WHeight + 'px,' + 'left=' + WLeft + 'px,' + 'top=' + WTop + 'px'; 
		
	WinOP = window.open(loadpos,"_blank",features); 
	WinOP.focus();   
}
</script>
