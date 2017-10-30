<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>


<table class="sort-table" width="100%" border="0">			
	<thead>
		<tr>
			<td width="4%" align="center">
				<bean:message bundle="projectmanage" key="serial" />
			</td>			
			<td align="center">
				<bean:message bundle="notice" key="notice.theme" />
			</td>
			<td width="10%" align="center">
				<bean:message bundle="notice" key="release.date" />
			</td>
			<td width="10%" align="center">
				<bean:message bundle="notice" key="notice.type" />
			</td>
			<td width="15%" align="center">
				<bean:message bundle="projectmanage" key="project.attachment" />
			</td>		
			<td width="4%" align="center">
				²éÔÄ
			</td>						
		</tr>
	</thead>
<logic:notEmpty name="noticeList">
	<logic:iterate name="noticeList" id="notice" indexId="i">				
		  <tr>
			<% int a = i%2;request.setAttribute("a",a);%>
			<logic:equal name="a" value="0"><tr class="even"></logic:equal>
			<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
				<td align="center">										
					<%=i+1 %>
				</td>
				<td>
					<bean:write name="notice" property="inTheme" />
				</td>									
				<td>
					<bean:write name="notice" property="inReleaseDate"/>
				</td>
				<td>
					<logic:notEmpty name="notice" property="inType">
						<bean:write name="notice" property="noticeType.ntName" />
					</logic:notEmpty>
				</td>
				<td>									
					<logic:iterate id="am" name="notice" property="attachmentList" indexId="j">
						<a href="noticeManage.do?method=downloadAttachment&id=<bean:write name='am' property='naId'/>" title="<bean:write name="am" property="naLocalUrl"/>">
							<bean:write name="am" property="naName"/>£»
						</a>												
					</logic:iterate>			
				</td>
				<td align="center">				
					<a href="javascript:openDialog('noticeManage.do?method=displayNotice&id=<bean:write name="notice" property="inId"/>',700,200)"><img border="0" src="pages\images\icon\16x16\display.gif"></a>
				</td>				
			</tr>				
	</logic:iterate>
</logic:notEmpty>
</table>

<script language="JavaScript">
function openDialog(loadpos,WWidth,WHeight)//Lock   Size 
{   	
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);   
	var WTop = Math.ceil((window.screen.height - WHeight) / 2); 
	var features = 'width=' + WWidth + 'px,' +	'height=' + WHeight + 'px,' + 'left=' + WLeft + 'px,' + 'top=' + WTop + 'px'; 
		
	WinOP = window.open(loadpos,"_blank",features); 
	WinOP.focus();   
}
 
</script>
