<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>


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
			<td align="center"><bean:write name="tenderProjectForm" property="tenderInfo.tpName"/></td>											
			<td align="center"><bean:write name="tenderProjectForm" property="tenderInfo.customer.CName"/></td>			
			<td align="center"><bean:write name="tenderProjectForm" property="tenderInfo.tpBeginDate"/></td>
			<td align="center"><bean:write name="tenderProjectForm" property="tenderInfo.tpEndDate"/></td>
			<td align="center">
				<logic:notEmpty name="tenderProjectForm" property="tenderInfo.manager">
					<bean:write name="tenderProjectForm" property="tenderInfo.manager.person.personName"/>
				</logic:notEmpty>
			</td>
			<td align="center"><bean:write name="tenderProjectForm" property="tenderInfo.status.psName"/></td>
		</tr>
	</tbody>
</table>
<br>