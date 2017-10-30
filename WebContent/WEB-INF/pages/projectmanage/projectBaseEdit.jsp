<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<c:import url="/WEB-INF/pages/projectmanage/tabinfo.jsp">
	<c:param name="selectTab" value="base"></c:param>
</c:import>


<script language="JavaScript">

 function chgAction(obj,str){
	obj.value=str;
 }
 
 
  function chgFormOnsubmit(str){  	 	
	projectSearchForm.onsubmit="function onsubmit(){" + str + "}";	
 }


function checkFormValidate()
{	
	 	
	return true;
}

function submitForm()
{
	chgFormOnsubmit('return true;');
	chgAction(document.all.method,'refreshProjectBase');
	projectSearchForm.submit();
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

