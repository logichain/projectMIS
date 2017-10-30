<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>


<bean:define id="title">
	<bean:message bundle="security" key="account.title" />
</bean:define>


<center>
	<gui:window title="<%=title%>" prototype="boWindow" color="100%">
		<html:form action="accountManager.do" focus="account.name" onsubmit="return validateAccountForm(this);">
			<html:errors />
			
			<logic:equal name="accountForm" property="account.id" value="">
				<html:hidden property="method" value="addAccount"/>
			</logic:equal>
			<logic:notEqual name="accountForm" property="account.id" value="">
				<html:hidden property="method" value="editAccount"/>
			</logic:notEqual>
						
			<html:hidden property="account.id" />
			<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
				<tr><td width="10%">&nbsp;</td><td width="10%">&nbsp;</td><td width="15%">&nbsp;</td><td width="10%">&nbsp;</td><td width="15%">&nbsp;</td><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td>
					<td align="right">
						<bean:message bundle="security" key="account.name" />£º
					</td>

					<td>
						<html:text property="account.name" size="17" maxlength="32" />*
					</td>
				
					<td align="right">
						<bean:message bundle="security" key="person.code" />£º
					</td>

					<td>
						<html:text property="account.person.personCode" size="17" maxlength="32" />
					</td>
					
				</tr>

				<tr><td>&nbsp;</td>
					<td align="right">
						<bean:message bundle="security" key="account.password" />£º
					</td>

					<td>
						<html:password property="account.password" size="18" maxlength="32" redisplay="true" onchange="passwordChanged(this)" style="width:120"/>*
					</td>
					<td align="right">
						<bean:message bundle="security" key="person.name" />£º
					</td>

					<td>
						<html:text property="account.person.personName" size="17" maxlength="32" />*
					</td>
				</tr>

				<tr><td>&nbsp;</td>
					<td align="right">
						<bean:message bundle="security" key="account.repassword" />£º
					</td>

					<td>
						<html:password property="account.repassword" size="18" maxlength="32" redisplay="true" style="width:120"/>*
					</td>

					<td align="right">
						<bean:message bundle="security" key="person.sex" />£º
					</td>

					<td>
						<html:radio property="account.person.sex" value="1" />
						<bean:message bundle="security" key="person.male" />
						<html:radio property="account.person.sex" value="2" />
						<bean:message bundle="security" key="person.female" />
					</td>
				</tr>

				
				<tr><td>&nbsp;</td>
					<td align="right">
						<bean:message bundle="security" key="person.birthday" />£º
					</td>

					<td>
						<html:text property="account.person.birthday" readonly="true" size="17" maxlength="32" onclick="SelectDate(this)"/>
					</td>
					<td align="right">
						<bean:message bundle="security" key="person.entrydate" />£º
					</td>

					<td>
						<html:text property="account.person.entryDate" readonly="true" size="17" maxlength="32" onclick="SelectDate(this)"/>
					</td>
					
				</tr>
				
				<tr><td>&nbsp;</td>
					<td align="right">
						<bean:message bundle="security" key="person.email" />£º
					</td>

					<td colspan="3">
						<html:text property="account.person.email" size="60" maxlength="100" />
					</td>
				</tr>
				<tr><td>&nbsp;</td>
					<td align="right">
						<bean:message bundle="security" key="person.dept" />£º
					</td>

					<td>
						<html:select property="account.person.dept" style="width:120">	
							<html:option value=""></html:option>
							<html:options collection="departmentList" property="DId" labelProperty="DName"/>
						</html:select>*
					</td>
					<td align="right">
						<bean:message bundle="security" key="person.post"/>£º
					</td>
					<td>
						<html:select property="account.person.post" style="width:120">	
							<html:option value=""></html:option>
							<html:options collection="postList" property="PId" labelProperty="PName"/>
						</html:select>*
					</td>					
				</tr>
				<tr><td>&nbsp;</td>
					<td align="right">
						<bean:message bundle="security" key="person.polity" />£º
					</td>

					<td>
						<html:select property="account.person.politicalStatus" style="width:120">	
							<html:option value=""></html:option>
							<html:options collection="politicalStatusList" property="psId" labelProperty="psName"/>
						</html:select>
					</td>
					<td align="right">
						<bean:message bundle="security" key="person.status" />£º
					</td>

					<td>
						<html:select property="account.person.status" style="width:120">	
							<html:option value=""></html:option>
							<html:options collection="personStatusList" property="psId" labelProperty="psName"/>
						</html:select>
					</td>
				</tr>
				<tr><td>&nbsp;</td>
					<td align="right">
						<bean:message bundle="security" key="person.phone" />£º
					</td>

					<td>
						<html:text property="account.person.phone" size="17" maxlength="32" />
					</td>
					<td align="right">
						<bean:message bundle="security" key="person.mobile" />£º
					</td>

					<td>
						<html:text property="account.person.mobile" size="17" maxlength="32" />
					</td>
				</tr>
				<tr><td>&nbsp;</td>					
					<td align="right">
						<bean:message bundle="security" key="person.education" />£º
					</td>

					<td>
						<html:select property="account.person.education" style="width:120">	
							<html:option value=""></html:option>
							<html:options collection="educationList" property="ebId" labelProperty="ebName"/>
						</html:select>
					</td>
				</tr>
				<tr><td>&nbsp;</td>
					<td align="right">
						<bean:message bundle="security" key="person.address" />£º
					</td>

					<td colspan="3">
						<html:text property="account.person.address" size="60" maxlength="100" />
					</td>
				</tr>
				<tr>
					<td colspan="5" align="right">
						<html:submit styleClass="savebutton">
							&nbsp;
						</html:submit>

						<html:reset styleClass="resetbutton">
							&nbsp;
						</html:reset>
					</td>
				</tr>
			</table>
			</html:form>
			
	</gui:window>
</center>

<html:javascript formName="accountForm" dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript" src="<html:rewrite forward='staticjavascript'/>"></script>

<script type="text/javascript">

 function passwordChanged(passwordField) {
     var origPassword = "<c:out value="${accountForm.map.account.password}"/>";
     if (passwordField.value != origPassword) {
         createFormElement("input", "hidden",
                           "encryptPass", "encryptPass",
                           "true", passwordField.form);
     }
 }
</script>

