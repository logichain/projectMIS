<script language="JavaScript">
 	var url = window.opener.location.href;		
	var index = url.indexOf("method=");
	if(index == -1)
	{
		window.opener.location = url + "?method=refreshInput";
	}
	else
	{
		window.opener.location = url.substring(0,index) + "method=refreshInput";		
	}		
	
	window.close();	
</script>

