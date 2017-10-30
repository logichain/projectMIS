<%@ include file="../common/include.jsp"%>
<%@page pageEncoding="GBK"%>

<bean:define id="title">
	<bean:message bundle="notice" key="title.create" />
</bean:define>


<center>
	<gui:window title="<%=title%>" prototype="boWindow">
		<html:form action="noticeManage.do" onsubmit="return validateNoticeForm(this);">
			<html:errors />

			<input type="hidden" name="method" value="saveNotice">
			<input type="hidden" name="optype" value="<%=request.getParameter("optype") %>">
			<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
				<tr>
					<td width="9%">&nbsp;</td><td width="16%">&nbsp;</td>
					<td width="9%">&nbsp;</td><td width="15%">&nbsp;</td>
					<td width="9%">&nbsp;</td><td width="15%">&nbsp;</td>
					<td width="9%">&nbsp;</td><td>&nbsp;</td>
				</tr>
				
				<tr height="30">
					<td align="right">
						<bean:message bundle="notice" key="notice.theme" />£º
					</td>
					<td colspan="7">
						<html:text property="noticeInfo.inTheme" size="100" maxlength="200" />*
					</td>
				</tr>
				<tr height="30">
					<td align="right">
						<bean:message bundle="notice" key="release.dept" />£º
					</td>
					<td>
						<html:select property="noticeInfo.inDept" style="width:120">								
							<html:options collection="departmentList" property="DId" labelProperty="DName"/>
						</html:select>
					</td>
	
					<td align="right">
						<bean:message bundle="notice" key="notice.type" />£º
					</td>
					<td>
						<html:select property="noticeInfo.inType" style="width:120">							
							<html:options collection="noticeTypeList" property="ntId" labelProperty="ntName"/>
						</html:select>*
					</td>

									
					<td align="right">
						<bean:message bundle="notice" key="release.date" />£º
					</td>
					<td>
						<html:text property="noticeInfo.inReleaseDate" readonly="true" size="16" maxlength="32" onclick="SelectDate(this);"/>*
					</td>
				</tr>			
				
				<tr>
					<td align="right" valign="top">
						<bean:message bundle="customer" key="customer.remarks" />£º
					</td>
					<td colspan="7">
						<html:textarea property="noticeInfo.inRemark" cols="109" rows="10" />
					</td>
					
				</tr>
				<tr><td>&nbsp;</td></tr>
			
				<tr>
					<td align="right">
						<bean:message bundle="projectmanage" key="project.attachment" />£º
					</td>
					<td align="left" colspan="5">
						
						<logic:iterate id="am" name="noticeForm" property="noticeInfo.attachmentList" indexId="i">
							
							<span title="<bean:write name="am" property="naLocalUrl"/>">
								<bean:write name="am" property="naName"/>
								<a href="noticeManage.do?method=deleteAttachmentByIndex&index=<%=i %>"><img border="0" src="pages\images\icon\16x16\delete.gif"></a>£»
							</span>				
							
						</logic:iterate>
					
					</td>			
					<td align="left">
						<html:button property="" onclick="openDialog('noticeManage.do?method=addAttachment',500,180);" styleClass="selectFileButton">&nbsp;</html:button>
					</td>	
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
				</tr>						
				<tr>
					<td colspan="8" align="right">
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


<html:javascript formName="noticeForm" dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript" src="<html:rewrite forward='staticjavascript'/>"></script>

<script language="JavaScript">

function chgAction(obj,str){
	obj.value=str;
 }
  
function chgFormOnsubmit(str){  	 	
	noticeForm.onsubmit="function onsubmit(){" + str + "}";	
 }
 
function submitForm()
{
	chgFormOnsubmit('return true;');
	chgAction(document.all.method,'refreshInput');
	noticeForm.submit();
}


function openDialog(loadpos,WWidth,WHeight)//Lock   Size 
{   
	submitForm();
	
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);   
	var WTop = Math.ceil((window.screen.height - WHeight) / 2); 
	var features = 'width=' + WWidth + 'px,' +	'height=' + WHeight + 'px,' + 'left=' + WLeft + 'px,' + 'top=' + WTop + 'px'; 
		
	WinOP = window.open(loadpos,"_blank",features); 
	WinOP.focus();	   
}

</script>
