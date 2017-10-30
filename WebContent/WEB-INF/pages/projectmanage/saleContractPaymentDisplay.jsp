<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<table>
	<tr>
		<td align="left">
			<bean:write name="projectSearchForm" property="projectInfo.accountSaleContract.ascPaymentMethod" filter="false"/>
		</td>
	</tr>
</table>