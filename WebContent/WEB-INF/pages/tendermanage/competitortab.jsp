<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<html:form action="tenderProjectManage.do" onsubmit="return checkFormValidate();">
	<html:errors />
	
	<input type="hidden" name="method" value="searchTenderResult">
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
		<tr><td width="10%"></td><td width="70%">&nbsp;</td></tr>	
		<tr>
			<td align="right"><bean:message bundle="tendermanage" key="tender.competitor" />£º</td>				
			<td>
				<html:text property='competitorInfo.CName' size="60" readonly="true" style="background-color:LightGray;"/>
				<html:button property="" onclick="openDialog('tenderProjectManage.do?method=searchCompetitor&index=result',800,420);">...</html:button>
			</td>										
			<td align="center">
				<!--html:submit styleClass="searchbutton" style="cursor: hand;"-->
					&nbsp;
				<!-- /html:submit-->
			</td>
		</tr>
	</table>	

</html:form>
<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
	<tr>
		<td width="9%">&nbsp;</td><td width="15%">&nbsp;</td>
		<td width="9%">&nbsp;</td><td width="15%">&nbsp;</td>
		<td width="9%">&nbsp;</td><td width="15%">&nbsp;</td>
		<td width="9%">&nbsp;</td><td>&nbsp;</td>
	</tr>
	<tr>
		<td align="right">
			
		</td>
		<td>
			
		</td>
		<td align="right">
			<bean:message bundle="customer" key="customer.shortname" />£º
		</td>
		<td>
			<bean:write name="tenderProjectForm" property="competitorInfo.CShortName"/>
		</td>
		<td align="right">
			<bean:message bundle="customer" key="customer.type" />£º
		</td>
		<td>
			<logic:notEmpty name="tenderProjectForm" property="competitorInfo.customerType">
				<bean:write name="tenderProjectForm" property="competitorInfo.customerType.ctName"/>
			</logic:notEmpty>
		</td>
		<td align="right">
			<bean:message bundle="customer" key="customer.tradetype" />£º
		</td>
		<td>
			<logic:notEmpty name="tenderProjectForm" property="competitorInfo.tradeType">
				<bean:write name="tenderProjectForm" property="competitorInfo.tradeType.ttName"/>	
			</logic:notEmpty>						
		</td>
	</tr>
	
	<tr>
		<td align="right">
			<bean:message bundle="customer" key="customer.name" />£º
		</td>
		<td colspan="3">
			<bean:write name="tenderProjectForm" property="competitorInfo.CName" />
		</td>
		<td align="right">
			<bean:message bundle="customer" key="customer.address" />£º
		</td>
		<td colspan="3">
			<bean:write name="tenderProjectForm" property="competitorInfo.CAddress" />
		</td>				
		
	</tr>
	
	<tr>
		<td align="right">
			<bean:message bundle="customer" key="customer.phone" />£º
		</td>
		<td>
			<bean:write name="tenderProjectForm" property="competitorInfo.CPhone" />
		</td>
		<td align="right">
			<bean:message bundle="customer" key="customer.cellphone" />£º
		</td>
		<td>
			<bean:write name="tenderProjectForm" property="competitorInfo.CCellphone" />
		</td>
		<td align="right">
			<bean:message bundle="customer" key="customer.fax" />£º
		</td>
		<td>
			<bean:write name="tenderProjectForm" property="competitorInfo.CFax" />
		</td>
		
		<td align="right">
			<bean:message bundle="customer" key="customer.postcode" />£º
		</td>
		<td >
			<bean:write name="tenderProjectForm" property="competitorInfo.CPostcode" />
		</td>	
	</tr>
	<tr>
		<td align="right">
			<bean:message bundle="customer" key="customer.saleman" />£º
		</td>
		<td>
			<logic:notEmpty name="tenderProjectForm" property="competitorInfo.saleman">
				<bean:write name="tenderProjectForm" property="competitorInfo.saleman.person.personName" />		
			</logic:notEmpty>										
		</td>					
		<td align="right">
			<bean:message bundle="customer" key="customer.contactperson" />£º
		</td>
		<td>
			<bean:write name="tenderProjectForm" property="competitorInfo.CContactPerson"/>
		</td>	
		<td align="right">
			<bean:message bundle="customer" key="customer.mail" />£º
		</td>
		<td colspan="3">
			<bean:write name="tenderProjectForm" property="competitorInfo.CMail"/>
		</td>
		
	</tr>
	<tr>
			
		<td align="right">
			<bean:message bundle="customer" key="customer.nature" />£º
		</td>
		<td>
			<logic:notEmpty name="tenderProjectForm" property="competitorInfo.customerNature">
				<bean:write name="tenderProjectForm" property="competitorInfo.customerNature.cnName" />
			</logic:notEmpty>						
		</td>
		<td align="right">
			<bean:message bundle="customer" key="customer.scale" />£º
		</td>
		<td>
			<bean:write name="tenderProjectForm" property="competitorInfo.CScale" />
		</td>
	
		<td align="right">
			<bean:message bundle="customer" key="customer.organizationcode" />£º
		</td>
		<td>
			<bean:write name="tenderProjectForm" property="competitorInfo.COrganizationCode" />
		</td>
		
	</tr>
	
	<tr>
		<td align="right">
			<bean:message bundle="customer" key="customer.remarks" />£º
		</td>
		<td colspan="7">
			<bean:write name="tenderProjectForm" property="competitorInfo.CRemarks"/>
		</td>
		
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td align="right" valign="top"><bean:message bundle="tendermanage" key="tender.tenderhistory" />£º</td>
		<td colspan="7">
			<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="0">		
				<thead>
					<tr>
						<td width="5%" align="center">
							<bean:message bundle="projectmanage" key="serial" />
						</td>
						<td width="20%" align="center">
							<bean:message bundle="projectmanage" key="project.name" />
						</td>
						<td width="8%" align="center">
							<bean:message bundle="tendermanage" key="tender.tenderOpenDate" />
						</td>
						<td width="10%" align="center">
							<bean:message bundle="tendermanage" key="tender.other" /><bean:message bundle="tendermanage" key="tender.competitorQuotes" />
						</td>
						<td width="10%" align="center">
							<bean:message bundle="tendermanage" key="tender.other" /><bean:message bundle="tendermanage" key="tender.tenderResult" />
						</td>
						<td width="10%" align="center">
							<bean:message bundle="tendermanage" key="tender.self" /><bean:message bundle="tendermanage" key="tender.competitorQuotes" />
						</td>
						<td width="10%" align="center">
							<bean:message bundle="tendermanage" key="tender.self" /><bean:message bundle="tendermanage" key="tender.tenderResult" />
						</td>
						<td align="center">
							<bean:message bundle="tendermanage" key="tender.tenderSummary" />
						</td>
					</tr>
				</thead>
				<logic:notEmpty name="competitorTenderResultList">
				<tbody>
					<logic:iterate name="competitorTenderResultList" id="result" indexId="i">
					<tr>
						<td><%=i+1 %></td>
						<td><bean:write name="result" property="referTenderProject.tpName"/></td>
						<td><bean:write name="result" property="referSelfTenderResult.tprTenderOpenDate"/></td>
						<td><bean:write name="result" property="ctrTenderPriceStr"/></td>
						<td>
							<logic:notEmpty name="result" property="ctrTenderResult">	
								<bean:write name="result" property="tenderResult.tsName"/>
							</logic:notEmpty>
						</td>
						<td><bean:write name="result" property="referSelfTenderResult.tprTenderPriceStr"/></td>
						<td>
							<logic:notEmpty name="result" property="referSelfTenderResult.tprTenderResult">
							<bean:write name="result" property="referSelfTenderResult.tenderResult.tsName"/>
							</logic:notEmpty>
						</td>
						<td><bean:write name="result" property="referSelfTenderResult.tprTenderSummary"/></td>
					</tr>
					</logic:iterate>
				</tbody>
				</logic:notEmpty>
			</table>
		</td>
	</tr>						
					
</table>
		