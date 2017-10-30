<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>
<table>
	<tr>
		<td align="left">
			<bean:write name="projectSearchForm" property="projectInfo.accountPurchaseContract.apcPaymentMethod" filter="false"/>
		</td>
	</tr>
</table>