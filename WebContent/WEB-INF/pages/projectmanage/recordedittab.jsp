<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<html:form action="projectManage.do" onsubmit="return checkFormValidate()&&validateProjectSearchForm(this);">
	<html:errors />
	
	<input type="hidden" name="method" value="saveProject">
	<input type="hidden" name="id" value="">		
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
		<tr><td>&nbsp;</td></tr>	
		
	</table>					
	<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">								
		<thead>
			<tr>
				<td width="5%" align="center">
					<bean:message bundle="projectmanage" key="serial" />
				</td>					
				<td width="10%" align="center">
					<bean:message bundle="projectmanage" key="project.createuser" />
				</td>
				<td width="10%" align="center">
					<bean:message bundle="projectmanage" key="scheduletask.task" />
				</td>
				<td width="10%" align="center">
					<bean:message bundle="projectmanage" key="implement.begindate" />
				</td>
				<td width="10%" align="center">
					<bean:message bundle="projectmanage" key="implement.enddate" />
				</td>
				<td width="5%" align="center">
					<bean:message bundle="projectmanage" key="implement.finish" />
				</td>
				<td align="center">
					<bean:message bundle="projectmanage" key="implement.description" />
				</td>
			</tr>
		</thead>
		<tbody>
		<%int index =1; %>
		<logic:iterate name="projectSearchForm" property="projectInfo.scheduleStageList" id="ss" indexId="i">
		<logic:notEqual name="ss" property="pssFlag" value="-1">
		<logic:iterate name="ss" property="scheduleTaskList" id="st" indexId="j" >
		<logic:notEqual name="st" property="pstFlag" value="-1">
			<logic:iterate id="r" name="st" property="implementRecordList" indexId="m">
			<logic:notEqual name="r" property="tirFlag" value="-1">
			   <tr>				
				<% int a = j%2;request.setAttribute("a",a);%>
				<logic:equal name="a" value="0"><tr class="even"></logic:equal>
				<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
					<td align="center">	<%=index++%></td>
					<td><bean:write name="r" property="createUser.person.personName"/></td>
					<td><bean:write name="st" property="pstName"/></td>
					<td><bean:write name="r" property="tirBeginDate"/></td>
					<td><bean:write name="r" property="tirEndDate"/></td>
					<td><bean:write name="r" property="tirImplementPercent"/>%</td>
					<td><bean:write name="r" property="tirDescription"/></td>						
					
				</tr>
			</logic:notEqual>
			</logic:iterate>
		</logic:notEqual>
		</logic:iterate>
		</logic:notEqual>
		</logic:iterate>
		</tbody>
	</table>			
</html:form>

<html:javascript formName="projectSearchForm" dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript" src="<html:rewrite forward='staticjavascript'/>"></script>