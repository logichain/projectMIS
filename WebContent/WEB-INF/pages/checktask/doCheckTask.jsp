<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>
<%@ page import="org.king.security.domain.Account" %>
<style>
<!--
.scroll {   
        width: 100%;                                    
        height:360px;                                   
        color: ;                                        
        font-family: ;                                  
        padding-left: 0px;                              
        padding-right: 0px;                             
        padding-top: 0px;                               
        padding-bottom: 0px;                          
        overflow-x: auto;                            
        overflow-y: auto;       
                   
        scrollbar-face-color: #D4D4D4;                  
        scrollbar-hightlight-color: #ffffff;                
        scrollbar-shadow-color: #919192;               
        scrollbar-3dlight-color: #ffffff;            
        scrollbar-arrow-color: #919192;                
        scrollbar-track-color: #ffffff;                
        scrollbar-darkshadow-color: #ffffff;              
    }
-->
</style>



<html:form action="projectManage.do" onsubmit="return checkFormValidate();">
	<html:errors />
	
	<input type="hidden" name="method" value="confirmCheckTask">	
	<input type="hidden" name="src" value="<%=request.getParameter("src") %>">	
	<html:hidden styleId="result" property="checkTaskInfo.ctCheckResult"/>		

<div class="scroll">
<logic:equal name="projectSearchForm" property="checkTaskInfo.ctTaskType" value="1">
<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
	<caption>合同信息</caption>
	<tr>
		<td width="20%">&nbsp;</td><td width="30%">&nbsp;</td><td width="20%">&nbsp;</td><td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right">
			<bean:message bundle="projectmanage" key="contract.title"/>：
		</td>
		<td colspan="3">
			<bean:write name="projectSearchForm" property="checkTaskInfo.contract.pcTitle"/>
		</td>			
	</tr>

	<tr>			
		<td align="right">
			<bean:message bundle="projectmanage" key="project.customer" />：
		</td>
		<td colspan="3">					
			<bean:write name="projectSearchForm" property="checkTaskInfo.contract.customer.CName"/>				
		</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td align="right">
			<bean:message bundle="projectmanage" key="contract.type"/>：
		</td>
		<td>
			<logic:equal name="projectSearchForm" property="checkTaskInfo.contract.pcType" value="1">订出合同</logic:equal>
			<logic:equal name="projectSearchForm" property="checkTaskInfo.contract.pcType" value="2">订进合同</logic:equal>				
		</td>			
	</tr>
	<tr>
		<td align="right"><bean:message bundle="projectmanage" key="contract.price"/>：</td>
		<td><bean:write name="projectSearchForm" property="checkTaskInfo.contract.pcContractPrice"/></td>
		<td align="right">
			<logic:equal name="projectSearchForm" property="checkTaskInfo.contract.pcType" value="1">与订进合同差异：</logic:equal>
			<logic:equal name="projectSearchForm" property="checkTaskInfo.contract.pcType" value="2">与投标书差异：</logic:equal>
		</td>
		<td><bean:write name="projectSearchForm" property="checkTaskInfo.contract.pcPriceDiff"/></td>
	</tr>
	<tr>
		<td align="right"><bean:message bundle="projectmanage" key="contract.payment_type"/>：</td>
		<td><bean:write name="projectSearchForm" property="checkTaskInfo.contract.pcPayType"/></td>
	</tr>
	<tr>
		<td align="right"><bean:message bundle="projectmanage" key="contract.quality_amount_year"/>：</td>
		<td><bean:write name="projectSearchForm" property="checkTaskInfo.contract.pcQualityAmountYear"/></td>
		<td align="right"><bean:message bundle="projectmanage" key="contract.quality_amount_percent"/>：</td>
		<td><bean:write name="projectSearchForm" property="checkTaskInfo.contract.pcQualityAmountPercent"/></td>
	</tr>

	<tr>
		<td align="right">
			<bean:message bundle="projectmanage" key="contract.remark" />：
		</td>
		<td colspan="3">			
			<bean:write name="projectSearchForm" property="checkTaskInfo.contract.pcRemark"/>	
		</td>				
	</tr>		
	<tr>
		<td align="right">
			<bean:message bundle="projectmanage" key="project.attachment" />：
		</td>
		<td colspan="3">				
			<logic:iterate id="am" name="projectSearchForm" property="checkTaskInfo.contract.attachmentList" indexId="i">
				<logic:equal name="am" property="paInputInterface" value="3">							
					<a href="projectManage.do?method=downloadAttachment&id=<bean:write name='am' property='paId'/>" title="<bean:write name="am" property="paLocalUrl"/>">
						<bean:write name="am" property="paName"/>
					</a>									
				</logic:equal>		
			</logic:iterate>					
		</td>	
	</tr>		
</table>
</logic:equal>
<logic:equal name="projectSearchForm" property="checkTaskInfo.ctTaskType" value="2">
<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
	<caption>立项备案信息</caption>
	<tr>
		<td width="10%"></td>
		<td width="40%"></td>				
		<td width="10%"></td>
		<td></td>
	</tr>
	<tr>
		<td align="center">
			<bean:message bundle="approvalrecord" key="project_name" />：
		</td>
		<td>
			<bean:write name="projectSearchForm" property="checkTaskInfo.projectApprovalRecord.parProjectName"/>
		</td>

		<td align="center">
			<bean:message bundle="approvalrecord" key="project_owner" />：
		</td>
		<td>			
			<bean:write name="projectSearchForm" property="checkTaskInfo.projectApprovalRecord.parProjectOwner"/>							
		</td>
		
	</tr>
	<tr>
		<td align="center">
			<bean:message bundle="approvalrecord" key="design_company" />：
		</td>
		<td>
			<bean:write name="projectSearchForm" property="checkTaskInfo.projectApprovalRecord.parDesignCompany"/>
		</td>

		<td align="center">
			<bean:message bundle="approvalrecord" key="tender_company" />：
		</td>
		<td>
			<bean:write name="projectSearchForm" property="checkTaskInfo.projectApprovalRecord.parTenderCompany"/>				
		</td>
		
	</tr>
	<tr>
		<td align="center">
			<bean:message bundle="approvalrecord" key="tender_price" />：
		</td>
		<td>
			<bean:write name="projectSearchForm" property="checkTaskInfo.projectApprovalRecord.parTenderPrice"/>万元
		</td>

		<td align="center">
			<bean:message bundle="approvalrecord" key="tender_time" />： 
		</td>
		<td >						
			<bean:write name="projectSearchForm" property="checkTaskInfo.projectApprovalRecord.parTenderDate"/>
		</td>									
	</tr>
	<tr>
		<td align="center">
			<bean:message bundle="approvalrecord" key="operating_expense" />：
		</td>
		<td>
			<bean:write name="projectSearchForm" property="checkTaskInfo.projectApprovalRecord.parOperatingExpense"/>万元
		</td>

		<td align="center">
			<bean:message bundle="approvalrecord" key="business_type" />：
		</td>
		<td>										
			<bean:write name="projectSearchForm" property="checkTaskInfo.projectApprovalRecord.parBusinessType"/>
		</td>
		
	</tr>
	
	<tr>				
		<td align="center">
			<bean:message bundle="approvalrecord" key="business_relation" />：
		</td>
		<td colspan="3">
			<bean:write name="projectSearchForm" property="checkTaskInfo.projectApprovalRecord.parBusinessRelation"/>
		</td>				
	</tr>
	<tr><td>&nbsp;</td></tr>	

	<tr>
		<td align="left">
			<bean:message bundle="approvalrecord" key="project_remark" />：
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3">
			<bean:write name="projectSearchForm" property="checkTaskInfo.projectApprovalRecord.parProjectRemark"/>
		</td>					
	</tr>
	<tr>
		<td align="left" colspan="2">
			<bean:message bundle="approvalrecord" key="risk_control" />：
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3">
			<bean:write name="projectSearchForm" property="checkTaskInfo.projectApprovalRecord.parRiskControl"/>
		</td>					
	</tr>
	<tr>
		<td align="left" colspan="2">
			<bean:message bundle="approvalrecord" key="actionability" />：
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td colspan="3">
			<bean:write name="projectSearchForm" property="checkTaskInfo.projectApprovalRecord.parActionability"/>
		</td>					
	</tr>							
	
</table>

</logic:equal>


<logic:equal name="projectSearchForm" property="checkTaskInfo.ctTaskType" value="3">
<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
	<caption>投标信息</caption>
	<tr>
		<td width="10%"></td>
		<td width="40%"></td>				
		<td width="10%"></td>
		<td></td>
	</tr>
	<tr>
		<td align="center">
			<bean:message bundle="approvalrecord" key="project_owner" />：
		</td>
		<td>										
			<bean:write name="projectSearchForm" property="checkTaskInfo.projectApprovalRecord.parProjectOwner"/>		
		</td>
		<td align="center">
			<bean:message bundle="checktask" key="task.dept" />：
		</td>
		<td>
			<bean:write name="projectSearchForm" property="checkTaskInfo.projectApprovalRecord.commitDept.DName"/>			
		</td>
	</tr>
	<tr>
		<td align="center">
			<bean:message bundle="approvalrecord" key="tender_member" />：
		</td>
		<td>
			<bean:write name="projectSearchForm" property="checkTaskInfo.projectApprovalRecord.parTenderMember"/>
		</td>

		<td align="center">
			<bean:message bundle="approvalrecord" key="tender_company" />：
		</td>
		<td>										
			<bean:write name="projectSearchForm" property="checkTaskInfo.projectApprovalRecord.parTenderCompany"/>					
		</td>
		
	</tr>

	<tr><td>&nbsp;</td></tr>	
	<tr>
		<td colspan="3" align="left">
			<b>业主简介与项目概况</b>（包括业主资信、项目规模等）：
		</td>	
	</tr>
	<tr><td></td>	
		<td colspan="3">
			<bean:write name="projectSearchForm" property="checkTaskInfo.projectApprovalRecord.parTenderRemark"/>
		</td>					
	</tr>
	<tr><td>&nbsp;</td></tr>	
	<tr>
		<td align="left">
			<bean:message bundle="projectmanage" key="project.attachment" />：
		</td>
		<td colspan="2">		
			<logic:iterate id="am" name="projectSearchForm" property="checkTaskInfo.projectApprovalRecord.attachmentList" indexId="i">					
				<a href="projectManage.do?method=downloadAttachment&id=<bean:write name='am' property='paId'/>" title="<bean:write name="am" property="paLocalUrl"/>">
					<bean:write name="am" property="paName"/>						
				</a>；			
			</logic:iterate>	
		</td>			
		<td>
		
		</td>	
	</tr>	
		
</table>
</logic:equal>

<logic:equal name="projectSearchForm" property="checkTaskInfo.ctTaskType" value="4">

<table class="sort-table" cellStacing="1" celltadding="1" width="100%" border="0">					
	<thead>
		<tr>
			<td width="5%" align="center">
				<bean:message bundle="projectmanage" key="serial" />
			</td>						
			<td width="10%" align="center">
				<bean:message bundle="attachment" key="code" />
			</td>											
			<td  width="20%" align="center">
				<bean:message bundle="attachment" key="category" />
			</td>						
			<td width="50%" align="center">
				<bean:message bundle="attachment" key="filename" />
			</td>
			<td width="5%" align="center">
				<bean:message bundle="attachment" key="download" />
			</td>
			
		</tr>
	</thead>
	<tbody>
		<logic:iterate id="ta" name="projectSearchForm" property="checkTaskInfo.project.tenderAttachmentList" indexId="i">
		<logic:notEqual name="ta" property="taFlag" value="-1">
		  
			<% int a = i%2;request.setAttribute("a",a);%>
			<logic:equal name="a" value="0"><tr class="even"></logic:equal>
			<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
				<td align="center"><%=i+1 %></td>
				<td><bean:write name="ta" property="taCode"/></td>
				<td>
					<logic:notEmpty name="ta" property="category">
						<bean:write name="ta" property="category.acName"/>
					</logic:notEmpty>	
				</td>
				<td><bean:write name="ta" property="taName"/></td>				
				<td align="center"><a href='tenderProjectManage.do?method=downloadTenderAttachment&id=<bean:write name="ta" property="taId"/>'><img border="0" src="pages\images\icon\16x16\make-index.gif"></a></td>
						
			</tr>
		</logic:notEqual>
		</logic:iterate>
	</tbody>
</table>

</logic:equal>

<logic:equal name="projectSearchForm" property="checkTaskInfo.ctTaskType" value="5">
	<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">
		<thead>
			<tr>
				<td align="center">
					<bean:message bundle="projectmanage" key="project.name" />
				</td>
				<td width="20%" align="center">
					<bean:message bundle="tendermanage" key="tender.clientName" />
				</td>						
				<td width="10%" align="center">
					<bean:message bundle="projectmanage" key="project.begindate" />
				</td>
				<td width="10%" align="center">
					<bean:message bundle="projectmanage" key="project.enddate" />
				</td>	
				<td width="10%" align="center">
					<bean:message bundle="projectmanage" key="project.manager" />
				</td>						
				<td width="8%" align="center">
					<bean:message bundle="projectmanage" key="project.status" />
				</td>
				
			</tr>
		</thead>
		<tbody>
			<tr>			
				<td align="center"><bean:write name="projectSearchForm" property="checkTaskInfo.project.tpName"/></td>											
				<td align="center"><bean:write name="projectSearchForm" property="checkTaskInfo.project.customer.CName"/></td>			
				<td align="center"><bean:write name="projectSearchForm" property="checkTaskInfo.project.tpBeginDate"/></td>
				<td align="center"><bean:write name="projectSearchForm" property="checkTaskInfo.project.tpEndDate"/></td>
				<td align="center">
					<logic:notEmpty name="projectSearchForm" property="checkTaskInfo.project.manager">
						<bean:write name="projectSearchForm" property="checkTaskInfo.project.manager.person.personName"/>
					</logic:notEmpty>
				</td>
				<td align="center"><bean:write name="projectSearchForm" property="checkTaskInfo.project.status.psName"/></td>
			</tr>
		</tbody>
	</table>	
	<table class="sort-table" WIDTH="100%" border="0">
		<thead>
			<tr>
				<td width="5%"></td><td width="5%"></td><td width="30%"></td><td width="15%"></td><td></td>
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
			</tr>
		</thead>
		<tbody>
			<%int a = 0;for(int i=7;i<=45;i++){%>				
			<logic:equal name="projectSearchForm" property='<%= "checkTaskInfo.projectBudget.e" + i + ".itemModel.bimTenderFlag" %>' value="1">
			<tr>
				<logic:equal name="projectSearchForm" property='<%= "checkTaskInfo.projectBudget.e" + i + ".itemModel.bimGatherFlag" %>' value="1">
					<%a++; %>
					<td align="center"><%=a%></td>	
					<td colspan="2" align="center"><bean:write name="projectSearchForm" property='<%= "checkTaskInfo.projectBudget.e" + i + ".itemModel.bimName" %>'/></td>
				</logic:equal>
				<logic:equal name="projectSearchForm" property='<%= "checkTaskInfo.projectBudget.e" + i + ".itemModel.bimGatherFlag" %>' value="0">
					<td>&nbsp;</td>
					<td align="center"><bean:write name="projectSearchForm" property='<%= "checkTaskInfo.projectBudget.e" + i + ".itemModel.bimCode" %>'/></td>
					<td align="center"><bean:write name="projectSearchForm" property='<%= "checkTaskInfo.projectBudget.e" + i + ".itemModel.bimName" %>'/></td>
				</logic:equal>				
				<td align="center"><bean:write name="projectSearchForm" property='<%= "checkTaskInfo.projectBudget.e" + i + "AmountStr" %>'/></td>						
				<td align="left"><bean:write name="projectSearchForm" property='<%= "checkTaskInfo.projectBudget.e" + i + ".biRemark" %>'/></td>					
		        
			</tr>
			</logic:equal>
			<%} %>
			
		</tbody>
	</table>
</logic:equal>

<logic:equal name="projectSearchForm" property="checkTaskInfo.ctTaskType" value="6">
	<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">
		<thead>
			<tr>
				<td align="center">
					<bean:message bundle="projectmanage" key="project.name" />
				</td>
				<td width="20%" align="center">
					<bean:message bundle="tendermanage" key="tender.clientName" />
				</td>						
				<td width="10%" align="center">
					<bean:message bundle="projectmanage" key="project.begindate" />
				</td>
				<td width="10%" align="center">
					<bean:message bundle="projectmanage" key="project.enddate" />
				</td>	
				<td width="10%" align="center">
					<bean:message bundle="projectmanage" key="project.manager" />
				</td>						
				<td width="8%" align="center">
					<bean:message bundle="projectmanage" key="project.status" />
				</td>
				
			</tr>
		</thead>
		<tbody>
			<tr>			
				<td align="center"><bean:write name="projectSearchForm" property="checkTaskInfo.project.tpName"/></td>											
				<td align="center"><bean:write name="projectSearchForm" property="checkTaskInfo.project.customer.CName"/></td>			
				<td align="center"><bean:write name="projectSearchForm" property="checkTaskInfo.project.tpBeginDate"/></td>
				<td align="center"><bean:write name="projectSearchForm" property="checkTaskInfo.project.tpEndDate"/></td>
				<td align="center">
					<logic:notEmpty name="projectSearchForm" property="checkTaskInfo.project.manager">
						<bean:write name="projectSearchForm" property="checkTaskInfo.project.manager.person.personName"/>
					</logic:notEmpty>
				</td>
				<td align="center"><bean:write name="projectSearchForm" property="checkTaskInfo.project.status.psName"/></td>
			</tr>
		</tbody>
	</table>	
	<table class="sort-table" WIDTH="100%" border="1">
		<thead>
			<tr>
				<td width="5%"></td><td width="5%"></td><td width="30%"></td><td width="15%"></td><td></td>
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
			</tr>
		</thead>
		<tbody>
			<%int a = 0;for(int i=7;i<=45;i++){%>				
			<logic:equal name="projectSearchForm" property='<%= "checkTaskInfo.projectBudget.e" + i + ".itemModel.bimApplyFlag" %>' value="1">
			<tr>
				<logic:equal name="projectSearchForm" property='<%= "checkTaskInfo.projectBudget.e" + i + ".itemModel.bimGatherFlag" %>' value="1">
						<%a++; %>
						<td align="center"><%=a%></td>	
						<td colspan="2" align="center"><bean:write name="projectSearchForm" property='<%= "checkTaskInfo.projectBudget.e" + i + ".itemModel.bimName" %>'/></td>
				</logic:equal>
				<logic:equal name="projectSearchForm" property='<%= "checkTaskInfo.projectBudget.e" + i + ".itemModel.bimGatherFlag" %>' value="0">
					<td>&nbsp;</td>
					<td align="center"><bean:write name="projectSearchForm" property='<%= "checkTaskInfo.projectBudget.e" + i + ".itemModel.bimCode" %>'/></td>
					<td align="center"><bean:write name="projectSearchForm" property='<%= "checkTaskInfo.projectBudget.e" + i + ".itemModel.bimName" %>'/></td>
				</logic:equal>				
				<td align="center"><bean:write name="projectSearchForm" property='<%= "checkTaskInfo.projectBudget.e" + i + "AmountStr" %>'/></td>						
				<td align="left"><bean:write name="projectSearchForm" property='<%= "checkTaskInfo.projectBudget.e" + i + ".biRemark" %>'/></td>					
		        
			</tr>
			</logic:equal>
			<%} %>
			
		</tbody>
	</table>
</logic:equal>

<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">	
	<caption>评审内容</caption>	
	<tr>
		<td width="15%" align="right">
			<bean:message bundle="projectmanage" key="task.checkcontent" />：
		</td>
		<td>						
			<html:textarea styleId="remark" property="checkTaskInfo.ctRemark" cols="80" rows="8"/>
		</td>	
	</tr>
	
	<tr><td>&nbsp;</td>
		<td align="center">
			<html:submit onclick="okSubmit();"  styleClass="checkOKbutton">
				&nbsp;
			</html:submit>
			<html:submit onclick="noSubmit();"  styleClass="checkNObutton">
				&nbsp;
			</html:submit>
		</td>											
	</tr>
	<tr><td>&nbsp;</td></tr>
</table>	
</div>	
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
	if(document.getElementById("result").value == 2)
	{
		if(document.getElementById("remark").value == "")
		{
			alert("评审意见 不能空");			
			return false;
		}
	}
	 	
	return true;
}

 function okSubmit(){
	document.getElementById("result").value = 1;
	
 }
  function noSubmit(){
	document.getElementById("result").value = 2;
		
 }

</script>

