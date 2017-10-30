<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<c:import url="/WEB-INF/pages/projectmanage/tabinfo.jsp">
	<c:param name="selectTab" value="accountManage"></c:param>
</c:import>


<iframe name="printframe" style="height:0px;width:0px;"></iframe>

<script language="JavaScript">

var pageId = "<%=request.getAttribute("tabpageId")%>";
if(pageId != 'null')
{
	displayChange(pageId);
	for(var i=1;i<=4;i++)
	{
		var radio = document.getElementById("radio"+i);
		if(radio.value == pageId)
		{
			radio.checked = true;
		}
	}
	
}

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

function displayChange(divId)
{
	var divList = ["saleContract","purchaseContract","feeTax","incomeExpense"];
	for(var i=0;i<divList.length;i++)
	{
		if(divList[i] == divId)
		{	
			document.getElementById(divList[i]).style.display = "block";			
		}
		else
		{
			document.getElementById(divList[i]).style.display = "none";
		}
	}	
}

function openDialog(loadpos,WWidth,WHeight)//Lock   Size 
{   	
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);   
	var WTop = Math.ceil((window.screen.height - WHeight) / 2); 
	var features = 'width=' + WWidth + 'px,' +	'height=' + WHeight + 'px,' + 'left=' + WLeft + 'px,' + 'top=' + WTop + 'px'; 
		
	WinOP = window.open(loadpos,"_blank",features); 
	WinOP.focus();   
}


function excelExport(divId){ 
	var tempStr = document.getElementById(divId).innerHTML;
   	var doc = document.frames["printframe"].document;
   	doc.write(tempStr);
  	
   	doc.close();
   	
   	doc.execCommand('saveas',true,'exportData.xls');
 }

</script>

