<%@ include file="../common/include.jsp"%>
<%@page pageEncoding="GBK"%>

<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
	<caption>客户资料信息</caption>
	<tr>
		<td width="9%">&nbsp;</td><td width="15%">&nbsp;</td>
		<td width="9%">&nbsp;</td><td width="15%">&nbsp;</td>
		<td width="9%">&nbsp;</td><td width="15%">&nbsp;</td>
		<td width="9%">&nbsp;</td><td>&nbsp;</td>
	</tr>
	<tr>
	
		<td align="right">
			<bean:message bundle="customer" key="customer.shortname" />：
		</td>
		<td colspan="7">
			<bean:write name="customerInfo" property="CShortName"/>
		</td>
	</tr>
	<tr>	
		<td align="right">
			<bean:message bundle="customer" key="customer.type" />：
		</td>
		<td>
			<logic:notEmpty name="customerInfo" property="customerType">
				<bean:write name="customerInfo" property="customerType.ctName"/>
			</logic:notEmpty>
		</td>
		<td align="right">
			<bean:message bundle="customer" key="customer.tradetype" />：
		</td>
		<td>
			<logic:notEmpty name="customerInfo" property="tradeType">
				<bean:write name="customerInfo" property="tradeType.ttName"/>
			</logic:notEmpty>
		</td>
	</tr>
	
	<tr>
		<td align="right">
			<bean:message bundle="customer" key="customer.name" />：
		</td>
		<td colspan="7">
			<bean:write name="customerInfo" property="CName"/>
		</td>
	</tr>
	<tr>	
		<td align="right">
			<bean:message bundle="customer" key="customer.address" />：
		</td>
		<td colspan="7">
			<bean:write name="customerInfo" property="CAddress"/>
		</td>				
		
	</tr>
	
	<tr>
		<td align="right">
			<bean:message bundle="customer" key="customer.phone" />：
		</td>
		<td>
			<bean:write name="customerInfo" property="CPhone"/>
		</td>
		<td align="right">
			<bean:message bundle="customer" key="customer.cellphone" />：
		</td>
		<td>
			<bean:write name="customerInfo" property="CCellphone"/>
		</td>
		<td align="right">
			<bean:message bundle="customer" key="customer.fax" />：
		</td>
		<td>
			<bean:write name="customerInfo" property="CFax"/>
		</td>
		
		<td align="right">
			<bean:message bundle="customer" key="customer.postcode" />：
		</td>
		<td >
			<bean:write name="customerInfo" property="CPostcode"/>
		</td>	
	</tr>
	<tr>
		<td align="right">
			<bean:message bundle="customer" key="customer.saleman" />：
		</td>
		<td>
			<logic:notEmpty name="customerInfo" property="saleman">
				<bean:write name="customerInfo" property="saleman.person.personName"/>	
			</logic:notEmpty>									
		</td>					
		<td align="right">
			<bean:message bundle="customer" key="customer.contactperson" />：
		</td>
		<td>
			<bean:write name="customerInfo" property="CContactPerson"/>
		</td>	
		<td align="right">
			<bean:message bundle="customer" key="customer.mail" />：
		</td>
		<td colspan="3">
			<bean:write name="customerInfo" property="CMail"/>
		</td>
		
	</tr>
	<tr>
			
		<td align="right">
			<bean:message bundle="customer" key="customer.nature" />：
		</td>
		<td>
			<logic:notEmpty name="customerInfo" property="customerNature">
				<bean:write name="customerInfo" property="customerNature.cnName"/>
			</logic:notEmpty>
		</td>
		<td align="right">
			<bean:message bundle="customer" key="customer.scale" />：
		</td>
		<td>
			<bean:write name="customerInfo" property="CScale"/>
		</td>
	
		<td align="right">
			<bean:message bundle="customer" key="customer.organizationcode" />：
		</td>
		<td>
			<bean:write name="customerInfo" property="COrganizationCode"/>
		</td>
		
	</tr>
	
	<tr>
		<td align="right">
			<bean:message bundle="customer" key="customer.remarks" />：
		</td>
		<td colspan="7">
			<bean:write name="customerInfo" property="CRemarks"/>
		</td>
		
	</tr>
	
	<tr>
		<td align="right">
			<bean:message bundle="projectmanage" key="project.attachment" />：
		</td>
		<td colspan="4">
			
			<logic:iterate id="am" name="customerInfo" property="attachmentList" indexId="i">
				<a href="customerManage.do?method=downloadAttachment&id=<bean:write name='am' property='caId'/>" title="<bean:write name="am" property="caLocalUrl"/>">
					<bean:write name="am" property="caName"/>；
				</a>												
			</logic:iterate>
			
		</td>			
		
	</tr>
				
</table>				

