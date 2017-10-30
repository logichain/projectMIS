<%@page pageEncoding="GBK"%>
<%@ include file = "./common/include.jsp"%>
<%@ include file="/WEB-INF/pages/common/style.jsp"%>
<body  bgcolor="#c0d2ec">

<table class="sort-table" width="100%" border="0">
<tr><td>当前工作任务</td></tr>
<tr><td>
<div class="scroll" id="div1">
<iframe width="99%" height="98%" src="projectManage.do?method=aboutSearchCheckTask&status=1"></iframe>
</div>
</td></tr>

<tr><td>未来工作任务</td></tr>
<tr><td>
<div class="scroll" id="div2">
<iframe width="99%" height="98%" src="projectManage.do?method=aboutSearchCheckTask&status=0"></iframe>
</div>
</td></tr>

<tr><td>公告显示</td></tr>
<tr><td>
<div class="scroll" id="div3">
<iframe width="99%" height="98%" src="noticeManage.do?method=aboutSearchNotice"></iframe>
</div>
</td></tr>

</table>
</body>
<script lang="javascript">
    <!--
    var clientheight = document.body.clientHeight / 3;

    document.getElementById('div1').style.height = (clientheight +5) + 'px';
    document.getElementById('div2').style.height = (clientheight +5) + 'px'; 
    document.getElementById('div3').style.height = (clientheight -80) + 'px';   
    // -->
</script>
