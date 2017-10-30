<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<html:form action="projectManage.do" onsubmit="return checkFormValidate()&&validateProjectSearchForm(this);">
	<html:errors />
	
	<input type="hidden" name="method" value="saveProjectBase">		
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
		<tr><td>&nbsp;</td></tr>	
		<tr><td width="70%">&nbsp;</td>						
			<td align="center" width="12%">
				<logic:equal name="accountPerson" property="hasCreatePower" value="true">	
					<html:submit styleClass="savebutton">
						&nbsp;
					</html:submit>
				</logic:equal>						
			</td>				
		</tr>
	</table>					
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
		<tr>
			<td width="10%"></td>
			<td width="15%"></td>				
			<td width="10%"></td>
			<td width="16%"></td>
			<td width="10%"></td>
			<td width="16%"></td>
			<td width="12%"></td>
			<td></td>
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="project.name"/>£º
			</td>
			<td colspan="3">
				<html:text property="projectInfo.tpName" size="65" maxlength="100" readonly="true" style="background-color:LightGray;"/>
			</td>
		</tr>
		<tr>	
			<td align="right">
				<bean:message bundle="projectmanage" key="project.status" />£º
			</td>
			<td>
				<html:select property="projectInfo.tpStatus" style="width:125">					
					<html:options collection="statusList" property="psId" labelProperty="psName"/>
				</html:select>						
			</td>	
			
			<td align="right">
				<bean:message bundle="projectmanage" key="project.dept" />£º
			</td>
			<td>										
				<html:select property="projectInfo.tpDept" style="width:125">						
					<html:options collection="deptList" property="DId" labelProperty="DName"/>
				</html:select>*							
			</td>
			<td align="right">
				<bean:message bundle="projectmanage" key="project.manager" />£º
			</td>
			<td>
				<html:text property="projectInfo.manager.person.personName" size="18" maxlength="100" readonly="true" style="background-color:LightGray;"/>		
				<html:button property="" onclick="openDialog('projectManage.do?method=searchAccount&opType=projectBaseManager',800,400);">...</html:button>			
			</td>
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="project.customer" />£º
			</td>
			<td colspan="3">
				<html:text property="projectInfo.customer.CName" size="65" maxlength="100" readonly="true" style="background-color:LightGray;"/>	
				<!-- <html:button property="" onclick="openDialog('projectManage.do?method=searchCustomer',800,600);">...</html:button>-->				
			</td>				
			<td align="right">
				<bean:message bundle="approvalrecord" key="market_manager" />£º
			</td>
			<td>						
				<html:text property="projectInfo.marketManager.person.personName" size="18" maxlength="45" readonly="true" style="background-color:LightGray;"/>
				<html:button property="" onclick="openDialog('projectManage.do?method=searchAccount&opType=projectBaseMarketManager',800,420);">...</html:button>
			</td>
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="project.begindate" />£º 
			</td>
			<td >						
				<html:text property="projectInfo.tpBeginDate" readonly="true" size="18" maxlength="32" onclick="SelectDate(this)" onchange="submitForm();"/>
			</td>				
			<td align="right">
				<bean:message bundle="projectmanage" key="project.enddate" />£º 
			</td>
			<td >						
				<html:text property="projectInfo.tpEndDate" readonly="true" size="18" maxlength="32" onclick="SelectDate(this)" onchange="submitForm();"/>
			</td>

			<td align="right">
				<bean:message bundle="projectmanage" key="project.workperiod" />£º
			</td>
			<td>
				<html:text property="projectInfo.tpWorkdayCount" size="18" maxlength="100" readonly="true" style="background-color:LightGray;"/><bean:message bundle="projectmanage" key="project.workperiodunit" />					
			</td>			
		</tr>
		<tr>		
			<td align="right">
				<bean:message bundle="tendermanage" key="tender.projectAddress" />£º
			</td>
			<td colspan="3">
				<html:text property="projectInfo.tpAddress" size="65" maxlength="100"/>*
			</td>	
			<td align="right">
				<bean:message bundle="projectmanage" key="project.contractamount" />£º
			</td>
			<td>
				<html:text property="projectInfo.tpContractAmount" size="18" maxlength="100"/><bean:message bundle="projectmanage" key="project.contractamountunit" />					
			</td>	
		</tr>
		
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="project.description" />£º
			</td>
			<td colspan="7">
				<html:textarea property="projectInfo.tpDescription" cols="110" rows="3"/>
			</td>			
				
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
			<td align="right"><bean:message bundle="projectmanage" key="project.createuser" />£º</td>
			<td><bean:write name="projectSearchForm" property="projectInfo.createUser.person.personName"/></td>
			<td align="right"><bean:message bundle="projectmanage" key="project.createtime" />£º</td>
			<td><bean:write name="projectSearchForm" property="projectInfo.tpCreateTime"/></td>
		</tr>				
	</table>				
</html:form>

<html:javascript formName="projectSearchForm" dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript" src="<html:rewrite forward='staticjavascript'/>"></script>
