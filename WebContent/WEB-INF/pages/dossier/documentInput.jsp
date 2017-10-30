<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<script type="text/javascript" src="<c:url value='/pages/scripts/calendar.js'/>" charset="gbk"></script>

<bean:define id="title">
	<bean:message bundle="attachment" key="input.title" />
</bean:define>


<center>
<gui:window title='<%=title%>' prototype="boWindow">
<html:form action="documentManage.do" enctype="multipart/form-data" onsubmit="return validateDocumentForm(this);">
	<html:errors />
	
	<input type="hidden" name="method" value="saveDocument">	
	<input type="hidden" name="optype" value="<%=request.getParameter("optype") %>">
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
		<tr><td>&nbsp;</td></tr>	
		<tr><td width="70%">&nbsp;</td>						
			<td align="center" width="12%">
				<html:submit styleClass="savebutton">
					&nbsp;
				</html:submit>						
			</td>				
		</tr>
	</table>				
	<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
		<tr>
			<td width="10%" align="right">
				<bean:message bundle="attachment" key="code" />£º
			</td>
			<td width="20%">						
				<html:text property="documentInfo.paCode" size="18" maxlength="45" readonly="true" style="background-color:LightGray;"/>
			</td>
			<td width="10%" align="right">
				<bean:message bundle="attachment" key="category" />£º
			</td>
			<td width="20%">						
				<html:select property="documentInfo.paAttachmentCategory" style="width:120">	
					<html:options collection="attachmentCategoryList" property="acId" labelProperty="acName"/>
				</html:select>
			</td>
			
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="attachment" key="filename" />£º
			</td>
			<td colspan="3">						
				<html:text property="documentInfo.paName" size="78" maxlength="100"/>
			</td>
			
		</tr>
		<tr>
			<td align="right">
				<bean:message bundle="attachment" key="submitdepartment" />£º
			</td>
			<td>						
				<html:select property="documentInfo.paSubmitDepartment" style="width:120">						
					<html:options collection="departmentList" property="DId" labelProperty="DName"/>
				</html:select>
			</td>
			<td align="right">
				<bean:message bundle="attachment" key="submitdate" />£º
			</td>
			<td >
				<html:text property="documentInfo.paSubmitDate" readonly="true" size="18" maxlength="32" onclick="SelectDate(this)"/>
			</td>
			
		</tr>

		<tr>
			<td align="right">
				<bean:message bundle="attachment" key="selectfile" />£º
			</td>
			<td colspan="3">
				<html:file property="documentInfo.attachmentFile" size="78"  onchange="document.getElementById('documentInfo.paLocalUrl').value=this.value"></html:file>	
				<html:hidden property="documentInfo.paLocalUrl"/>				
			</td>				
		</tr>
		
		<tr>
			<td align="right">
				<bean:message bundle="attachment" key="remarks" />£º
			</td>
			<td colspan="5">
				<html:textarea property="documentInfo.paDescription" cols="100" rows="3"></html:textarea>				
			</td>				
		</tr>
		
		<tr><td>&nbsp;</td></tr>
					
		
	</table>
	
</html:form>
</gui:window>
</center>

<html:javascript formName="documentForm" dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript" src="<html:rewrite forward='staticjavascript'/>"></script>

