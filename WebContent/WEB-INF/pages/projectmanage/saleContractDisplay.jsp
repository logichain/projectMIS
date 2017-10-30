<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>


		
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
		
		<tr><td width="10%">&nbsp;</td><td  width="15%">&nbsp;</td><td width="10%">&nbsp;</td><td  width="15%">&nbsp;</td>
			<td width="10%">&nbsp;</td><td  width="15%">&nbsp;</td><td width="10%">&nbsp;</td><td >&nbsp;</td></tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.code" />£∫
			</td>
			<td>
				<bean:write name="asc"  property="ascCode"/>						
			</td>				
		
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.customer" />£∫
			</td>
			<td colspan="5">
				<bean:write name="asc"  property="ascCustomerName"/>
			</td>
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.date" />£∫ 
			</td>
			<td >						
				<bean:write name="asc"  property="ascSignDate"/>				
			</td>
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.object" />£∫ 
			</td>
			<td colspan="5">	
				<bean:write name="asc"  property="ascObject"/>					
			</td>			
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.amount" />£∫ 
			</td>
			<td >					
				<bean:write name="asc"  property="ascAmount"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>
			<td align="right">∆‰÷–£∫</td>
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.deviceAmount" />£∫ 
			</td>
			<td >					
				<bean:write name="asc"  property="ascDeviceAmount"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.installAmount" />£∫ 
			</td>
			<td >			
				<bean:write name="asc"  property="ascInstallAmount"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>			
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.serviceAmount" />£∫ 
			</td>
			<td >						
				<bean:write name="asc"  property="ascServiceAmount"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.addAmount" />£∫ 
			</td>
			<td >		
				<bean:write name="asc"  property="ascAddAmount"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>
		</tr>		
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.invoiceAmount" />£∫ 
			</td>
			<td >	
				<bean:write name="asc"  property="ascInvoiceAmount"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.gatheringAmount" />£∫ 
			</td>
			<td >	
				<bean:write name="asc"  property="ascGatheringAmount"/><bean:message bundle="projectmanage" key="project.contractamountunit" />
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.period" />£∫ 
			</td>
			<td >		
				<bean:write name="asc"  property="ascPeriod"/>				
			</td>
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="projectmanage" key="salecontract.paymentMethod" />£∫
			</td>
			<td colspan="7">
				<bean:write name="asc"  property="ascPaymentMethod"/>		
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
					
		
	</table>		


