<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>


<c:import url="/WEB-INF/pages/tendermanage/tabinfo.jsp">
	<c:param name="selectTab" value="competitor"></c:param>
</c:import>

<script language="JavaScript">

 function chgAction(obj,str){
	obj.value=str;
 }
 
  function checkFormValidate()
{	
	var pname = document.getElementById("competitorInfo.CName").value;	
	if(pname == "")
	{
		alert("竞争对手 不能为空");
		return false;
	} 
	 	
	return true;
}
 
  function initPageNo()
 {
 	document.getElementById("pager.offset").value='0';
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
