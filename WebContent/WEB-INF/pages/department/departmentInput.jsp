<%@ include file="../common/include.jsp"%>
<%@page pageEncoding="GBK"%>

<bean:define id="title">
	<bean:message bundle="department" key="department.title" />
</bean:define>


<center>
	<gui:window title="<%=title%>" prototype="boWindow">
		<html:form action="departmentManage.do" onsubmit="return validateDepartmentForm(this);">
			<html:errors />

			<input type="hidden" name="method" value="saveDepartment">
			<html:hidden property="departmentInfo.DId" />
			<html:hidden property="departmentInfo.DFlag" />
			<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
				<tr>
					<td width="15%">&nbsp;</td><td width="25%">&nbsp;</td>
					
					<td width="15%">&nbsp;</td><td>&nbsp;</td>
				</tr>
				
				<tr>
					<td align="right">
						<bean:message bundle="department" key="department.name" />£º
					</td>
					<td>
						<html:text property="departmentInfo.DName" size="18" maxlength="10" />*
					</td>					
					
				</tr>
				
				<tr>
					<td align="right">
						<bean:message bundle="department" key="department.phone" />£º
					</td>
					<td>
						<html:text property="departmentInfo.DPhone" size="18" maxlength="10" />
					</td>	
					<td align="right">
						<bean:message bundle="department" key="department.fax" />£º
					</td>
					<td>
						<html:text property="departmentInfo.DFax" size="18" maxlength="10" />
					</td>	
				</tr>
				<tr>
					<td align="right">
						<bean:message bundle="department" key="department.description" />£º
					</td>
					<td colspan="7">
						<html:textarea property="departmentInfo.DDescription" cols="80" rows="3" />
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

<!--						<html:cancel styleClass="button">-->
<!--							<bean:message key="button.cancel" />-->
<!--						</html:cancel>-->
					</td>
				</tr>
				
				
			</table>
		</html:form>
			
	</gui:window>
</center>


<html:javascript formName="departmentForm" dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript" src="<html:rewrite forward='staticjavascript'/>"></script>

<script language="JavaScript">

function chgAction(obj,str){
	obj.value=str;
 }
  
function chgFormOnsubmit(str){  	 	
	departmentForm.onsubmit="function onsubmit(){" + str + "}";	
 }
 
function submitForm()
{
	chgFormOnsubmit('return true;');
	chgAction(document.all.method,'refreshInput');
	departmentForm.submit();
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
