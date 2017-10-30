<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>


<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
	<tr><td width="20%"></td><td width="30%"></td><td width="15%"></td><td></td></tr>
	<tr>
		<td align="right">
			<bean:message bundle="security" key="account.name" />£º
		</td>

		<td>
			<bean:write name="userAccount" property="name"/>
		</td>
	
		<td align="right">
			<bean:message bundle="security" key="person.code" />£º
		</td>

		<td>
			<bean:write name="userAccount" property="person.personCode"/>
		</td>
		
	</tr>

	<tr>		
		<td align="right">
			<bean:message bundle="security" key="person.name" />£º
		</td>

		<td>
			<bean:write name="userAccount" property="person.personName"/>
		</td>
	
		<td align="right">
			<bean:message bundle="security" key="person.sex" />£º
		</td>

		<td>			
			<logic:equal name="userAccount" property="person.sex" value="1"><bean:message bundle="security" key="person.male" /></logic:equal>
			<logic:equal name="userAccount" property="person.sex" value="2"><bean:message bundle="security" key="person.female" /></logic:equal>			
		</td>
	</tr>

	
	<tr>
		<td align="right">
			<bean:message bundle="security" key="person.birthday" />£º
		</td>

		<td>
			<bean:write name="userAccount" property="person.birthday"/>
		</td>
		<td align="right">
			<bean:message bundle="security" key="person.entrydate" />£º
		</td>

		<td>
			<bean:write name="userAccount" property="person.entryDate"/>
		</td>
		
	</tr>
	
	<tr>
		<td align="right">
			<bean:message bundle="security" key="person.email" />£º
		</td>

		<td colspan="3">
			<bean:write name="userAccount" property="person.email"/>
		</td>
	</tr>
	<tr>
		<td align="right">
			<bean:message bundle="security" key="person.dept" />/
			<bean:message bundle="security" key="person.post"/>£º
		</td>
		<td colspan="3">
			<logic:iterate id="up" name="userAccount" property="usrPostList">
				<logic:notEmpty name="up" property="deptObject">
					<bean:write name="up" property="deptObject.DName"/>
				</logic:notEmpty>/
				<logic:notEmpty name="up" property="postObject">
					<bean:write name="up" property="postObject.PName"/>
				</logic:notEmpty>;
			</logic:iterate>
		</td>					
	</tr>
	<tr>
		<td align="right">
			<bean:message bundle="security" key="person.polity" />£º
		</td>

		<td>
			<logic:notEmpty name="userAccount" property="person.politicalStatusObject">
				<bean:write name="userAccount" property="person.politicalStatusObject.psName"/>
			</logic:notEmpty>
		</td>
		<td align="right">
			<bean:message bundle="security" key="person.status" />£º
		</td>

		<td>
			<logic:notEmpty name="userAccount" property="person.personStatusObject">
				<bean:write name="userAccount" property="person.personStatusObject.psName"/>
			</logic:notEmpty>
		</td>
	</tr>
	<tr>
		<td align="right">
			<bean:message bundle="security" key="person.phone" />£º
		</td>

		<td>
			<bean:write name="userAccount" property="person.phone"/>
		</td>
		<td align="right">
			<bean:message bundle="security" key="person.mobile" />£º
		</td>

		<td>
			<bean:write name="userAccount" property="person.mobile"/>
		</td>
	</tr>
	<tr>					
		<td align="right">
			<bean:message bundle="security" key="person.education" />£º
		</td>

		<td>
			<logic:notEmpty name="userAccount" property="person.educationObject">
				<bean:write name="userAccount" property="person.educationObject.ebName"/>
			</logic:notEmpty>
		</td>
		<td></td><td></td>
	</tr>
	<tr>
		<td align="right">
			<bean:message bundle="security" key="person.address" />£º
		</td>

		<td colspan="3">
			<bean:write name="userAccount" property="person.address"/>
		</td>
	</tr>
	
</table>
			