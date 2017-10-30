<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<c:import url="/WEB-INF/pages/tendermanage/tabinfo.jsp">
	<c:param name="selectTab" value="deviceList"></c:param>
</c:import>


<script language="JavaScript">
	
 function chgAction(obj,str){
	obj.value=str;
 }
	
function openDialog(loadpos,WWidth,WHeight)//Lock   Size 
{   
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);   
	var WTop = Math.ceil((window.screen.height - WHeight) / 2); 
	var features = 'width=' + WWidth + 'px,' +	'height=' + WHeight + 'px,' + 'left=' + WLeft + 'px,' + 'top=' + WTop + 'px'; 

	WinOP = window.open(loadpos,"_blank",features); 
	WinOP.focus();   
}
</script>