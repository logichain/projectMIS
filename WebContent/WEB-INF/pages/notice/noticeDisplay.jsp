<%@ include file="../common/include.jsp"%>
<%@page pageEncoding="GBK"%>


<div class="scroll">		
<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
	<tr>
		<td width="10%">&nbsp;</td><td width="20%">&nbsp;</td>
		<td width="10%">&nbsp;</td><td width="20%">&nbsp;</td>
		<td width="10%">&nbsp;</td><td>&nbsp;</td>
	</tr>
	
	<tr height="30">
		<td align="right">
			<bean:message bundle="notice" key="notice.theme" />£º
		</td>
		<td colspan="7">
			<bean:write name="noticeInfo" property="inTheme"/>
		</td>
	</tr>
	<tr height="30">
		<td align="right">
			<bean:message bundle="notice" key="release.dept" />£º
		</td>
		<td>
			<bean:write name="noticeInfo" property="department.DName"/>						
		</td>

		<td align="right">
			<bean:message bundle="notice" key="notice.type" />£º
		</td>
		<td>
			<bean:write name="noticeInfo" property="noticeType.ntName"/>	
		</td>

						
		<td align="right">
			<bean:message bundle="notice" key="release.date" />£º
		</td>
		<td>
			<bean:write name="noticeInfo" property="inReleaseDate"/>
		</td>
	</tr>			
	
	<tr>
		<td align="right" valign="top">
			<bean:message bundle="customer" key="customer.remarks" />£º
		</td>
		<td colspan="7">
			<bean:write name="noticeInfo" property="inRemark" />
		</td>
		
	</tr>
	<tr><td>&nbsp;</td></tr>

	<tr>
		<td align="right">
			<bean:message bundle="projectmanage" key="project.attachment" />£º
		</td>
		<td align="left" colspan="5">						
			<logic:iterate id="am" name="noticeInfo" property="attachmentList" indexId="i">								
				<a href="noticeManage.do?method=downloadAttachment&id=<bean:write name='am' property='naId'/>" title="<bean:write name="am" property="naLocalUrl"/>">
					<bean:write name="am" property="naName"/>£»
				</a>															
			</logic:iterate>						
		</td>	
	</tr>
					
</table>	
</div>		
			