<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>


<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">
	<thead>
		<tr>
			<td width="20%" align="center">
				<bean:message bundle="projectmanage" key="project.name" />
			</td>
			<td width="6%" align="center">
				<bean:message bundle="projectmanage" key="project.important" />
			</td>
			<td width="8%" align="center">
				<bean:message bundle="projectmanage" key="project.type" />
			</td>
			<td width="10%" align="center">
				<bean:message bundle="budgetmanage" key="budget.tenderDate" />
			</td>
			<td width="10%" align="center">
				<bean:message bundle="projectmanage" key="project.status" />
			</td>
			<td width="10%" align="center">
				<bean:message bundle="projectmanage" key="project.code" />
			</td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td width="20%" align="center">
				<bean:write name="tenderInputForm" property="tenderInfo.tpName"/>
			</td>
			<td width="6%" align="center">
				<logic:notEmpty name="tenderInputForm" property="tenderInfo.importantDegree">
					<bean:write name="tenderInputForm" property="tenderInfo.importantDegree.pidName"/>
				</logic:notEmpty>
			</td>
			<td width="8%" align="center">
				<logic:notEmpty name="tenderInputForm" property="tenderInfo.type">
					<logic:present name="tenderInfo.type">
						<bean:write name="tenderInputForm" property="tenderInfo.type.ptName"/>
					</logic:present>
				</logic:notEmpty>
			</td>
			<td width="10%" align="center">
				<bean:write name="tenderInputForm" property="tenderInfo.tpTenderDate"/>
			</td>
			<td width="10%" align="center">
				<logic:notEmpty name="tenderInputForm" property="tenderInfo.status">
					<logic:present name="tenderInfo.status">
						<bean:write name="tenderInputForm" property="tenderInfo.status.psName"/>
					</logic:present>
				</logic:notEmpty>
			</td>
			<td width="10%" align="center">
				<bean:write name="tenderInputForm" property="tenderInfo.tpCode"/>
			</td>
		</tr>
	</tbody>
</table>
<br>