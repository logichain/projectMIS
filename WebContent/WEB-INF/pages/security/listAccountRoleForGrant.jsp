<%@ page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<center>
<bean:define id="title">
	<bean:message bundle="security" key="security.selectRole" />
</bean:define>
<gui:window title="<%=title%>" prototype="boWindow" color="100%">
		
	<html:form action="accountSecurityManager.do?method=grantAccountRole" onsubmit="return onFormSubmit(this);">
		<html:errors />
		
	<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">		
		
		<thead>
			<tr>
				<td width="10%" align="center">
					<bean:message bundle="security" key="account.name" />
				</td>
				<td width="10%" align="center">
					<bean:message bundle="security" key="person.code" />
				</td>
				<td width="15%" align="center">
					<bean:message bundle="security" key="person.name" />
				</td>
				<td width="10%" align="center">
					<bean:message bundle="security" key="person.birthday" />
				</td>
				<td align="center">
					<bean:message bundle="security" key="person.dept" />/
					<bean:message bundle="security" key="person.post" />
				</td>
				<td width="20%" align="center">
					<bean:message bundle="security" key="person.email" />
				</td>					
			</tr>
		</thead>

		<tr>
			<td>										
				<bean:write name="currentAccount" property="name" />
			</td>
			<td>
				<bean:write name="currentAccount" property="person.personCode" />
			</td>
			<td>
				<bean:write name="currentAccount" property="person.personName" />
			</td>
			<td>
				<bean:write name="currentAccount" property="person.birthday" />
			</td>
			<td>				
				<logic:iterate id="up" name="currentAccount" property="usrPostList">
					<logic:notEmpty name="up" property="deptObject">
						<bean:write name="up" property="deptObject.DName"/>
					</logic:notEmpty>/
					<logic:notEmpty name="up" property="postObject">
						<bean:write name="up" property="postObject.PName"/>
					</logic:notEmpty>;
				</logic:iterate>
			</td>
			<td>
				<bean:write name="currentAccount" property="person.email" />
			</td>				
		</tr>
	</table>
		
	<table class="win" cellSpacing="1" cellPadding="1" width="100%" border="0">
		<tr>			
			<th width="30%" align="center">
				
			</th>
			<th width="10%">
				&nbsp;
			</th>
			<th  width="30%" align="center">
			</th>
			<th></th>
		</tr>
		<tr><td colspan="4">
		<c:set var="leftList" value="${availableRole}" scope="request" />
		<c:set var="rightList" value="${currentRole}" scope="request" />
		<c:import url="/WEB-INF/pages/security/pickList.jsp">
			<c:param name="leftLabel" value="可选角色" />
			<c:param name="rightLabel" value="当前角色" />
			<c:param name="listCount" value="6" />
			<c:param name="leftId" value="availableRole" />
			<c:param name="rightId" value="currentRole" />
			<c:param name="listSize" value="12" />
			<c:param name="listWidth" value="200px" />
		</c:import>
		</td></tr>
		<tr>
			<td colspan="3"></td>
			<td align="right">
				<html:submit styleClass="button">
					<bean:message key="button.save" />
				</html:submit>
				<html:cancel styleClass="button">
					<bean:message key="button.cancel" />
				</html:cancel>
			</td>
		</tr>
	</table>
		<input type="hidden" name="id" value='<c:out value="${currentAccount.id}"/>'>
	</html:form>

</gui:window>

</center>
<script type="text/javascript">
function onFormSubmit(theForm) {
	selectAll('currentRole');
    return true;
}
</script>
