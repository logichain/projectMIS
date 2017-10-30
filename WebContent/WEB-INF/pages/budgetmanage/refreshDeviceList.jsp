<script language="JavaScript">
 	var url = window.opener.location.href;	
	var index = url.indexOf("=");
	window.opener.location = url.substring(0,index) + "=editDeviceList";
	
	window.close();	
</script>

