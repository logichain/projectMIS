<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<c:import url="/WEB-INF/pages/tendermanage/tabinfo.jsp">
	<c:param name="selectTab" value="teamMember"></c:param>
</c:import>

<script language="JavaScript">

 function chgAction(obj,str){
	obj.value=str;
 }
 
 
  function chgFormOnsubmit(str){  	 	
	tenderProjectForm.onsubmit="function onsubmit(){" + str + "}";	
 }
 
function checkFormValidate()
{	
	var pname = document.getElementById("personName").value;	
	if(pname == "")
	{
		alert("姓名 不能为空");
		return false;
	} 
	
	var roleId = document.getElementById("projectRole").value;	
	if(roleId == "" ||　roleId == "0")
	{
		alert("项目角色 不能为空");
		return false;
	}
		
	return true;
}

function submitForm()
{
	chgFormOnsubmit('return true;');
	chgAction(document.all.method,'refreshProjectTeamEdit');
	tenderProjectForm.submit();
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

