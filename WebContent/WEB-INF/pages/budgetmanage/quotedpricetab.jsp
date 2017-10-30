<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.king.tender.bean.DeviceList1st"%>
<%@ page import="org.king.tender.bean.DeviceList2nd"%>
<%@ page import="org.king.tender.bean.DeviceListDetail"%>
	
<%
String context = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath();
%>

	
  <script type="text/javascript" src="<%=context%>/resources/treetable/jquery.js"></script>
  <script type="text/javascript" src="<%=context%>/resources/treetable/jquery.ui.js"></script>

  <!-- BEGIN Plugin Code -->
  
  <link href="<%=context%>/resources/treetable/jquery.treeTable.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="<%=context%>/resources/treetable/jquery.treeTable.js"></script>
  	  
<script type="text/javascript">
  $(document).ready(function() {
    $(".treeSelectTable").treeTable(
    {
      initialState: "expanded"
    });
    
    // Drag & Drop Example Code
    $(".treeSelectTable .node, .treeSelectTable .parentNode").draggable({
      helper: "clone",
      opacity: .75,
      refreshPositions: true, // Performance?
      revert: "invalid",
      revertDuration: 300,
      scroll: true
    });
    
    $(".treeSelectTable .parentNode").each(function() {
      $($(this).parents("tr")[0]).droppable({
        accept: ".node, .parentNode",
        drop: function(e, ui) { 
          $($(ui.draggable).parents("tr")[0]).appendBranchTo(this);
        },
        hoverClass: "accept",
        over: function(e, ui) {
          if(this.id != $(ui.draggable.parents("tr")[0]).id && !$(this).is(".expanded")) {
            $(this).expand();
          }
        }
      });
    });
    
    // Make visible that a row is clicked
    $("table.treeSelectTable tbody tr").mousedown(function() {
      $("tr.selected").removeClass("selected"); // Deselect currently selected rows
      $(this).addClass("selected");
    });
    
    // Make sure row is selected when span is clicked
    $("table.treeSelectTable tbody tr span").mousedown(function() {
      $($(this).parents("tr")[0]).trigger("mousedown");
    });
  });
</script>
	
	<html:form action="tenderProjectManage.do" onsubmit="return checkFormValidate()&&validatetenderProjectForm(this);;" enctype="multipart/form-data">
	<html:errors />
	<input type="hidden" name="method" value="editDeviceListFirst">

	<table class="treeSelectTable" id="dnd-example" width="100%">
	  <thead>
	    <tr>
	      	<th align="center" width="25%"><bean:message bundle="budgetmanage" key="budget.name" /></th>
	      	<th align="center" width="10%"><bean:message bundle="budgetmanage" key="budget.standard" /></th>
	      	<th align="center" width="5%"><bean:message bundle="budgetmanage" key="budget.count" /></th>
	    	<th align="center" width="5%"><bean:message bundle="budgetmanage" key="budget.unit" /></th>
	    	<th align="center" width="7%"><bean:message bundle="budgetmanage" key="budget.beginDate" /></th>
	    	<th align="center" width="7%"><bean:message bundle="budgetmanage" key="budget.endDate" /></th>
	    	<th align="center" width="16%"><bean:message bundle="budgetmanage" key="budget.claim" /></th>
	    	<th align="center" width="10%"><bean:message bundle="budgetmanage" key="budget.responsiblePerson" /></th>
	    	<th align="center" width="5%"><bean:message key="button.edit" /></th>
	    	<th align="center" width="5%"><bean:message bundle="budgetmanage" key="budget.firstDevicePrice" /></th>
	    	<th align="center"><bean:message bundle="budgetmanage" key="budget.finish" /></th>
	    </tr>
	  </thead>
	  <tbody>
	    <logic:notEmpty name="deviceList">
	    	<%int firstDeviceCount = ((Integer) request.getAttribute("firstdeviceCount")).intValue();
			  int secondDeviceCount = ((Integer) request.getAttribute("secondDeviceCount")).intValue();
			  int itemNo = 0;
	    	%>
	    	<logic:iterate name="deviceList" id="deviceList1st" indexId="i">
	    		<% String node = "node-" + i; %>
	    		<tr id="<%=node%>">
			    	<td class="droplist"><span class="parentNode"><bean:write name="deviceList1st" property="dlfFirstEquipmentName"/></span></td>
			    	<td>&nbsp;</td>
			      	<td>&nbsp;</td>
			      	<td>&nbsp;</td>
			      	<td>&nbsp;</td>
			      	<td>&nbsp;</td>
			      	<td>&nbsp;</td>
			      	<td>&nbsp;</td>
			      	<td>&nbsp;</td>
			      	<td>&nbsp;</td>
			      	<td>&nbsp;</td>
			    </tr>
	    		<%
	    			List<DeviceList2nd> device2ndList = ((DeviceList1st)deviceList1st).getDevicList2ndList();
					List<DeviceListDetail> deviceDetailList = ((DeviceList1st)deviceList1st).getDeviceListDetailList();
					String secondNode = "";
					String secondNodeClass = "";
					firstDeviceCount += device2ndList.size();
					if(device2ndList.size() > 0){
						for(int indexi = 0; indexi < device2ndList.size(); indexi++){
						secondNode = "node-" + firstDeviceCount + indexi;
						secondNodeClass = "child-of-node-" + i;
						DeviceList2nd secondDevice = (DeviceList2nd)device2ndList.get(indexi);
	    		%>
	    			<tr id="<%=secondNode%>" class="<%=secondNodeClass%>">
				        <td class="droplist"><span class="parentNode"><%=secondDevice.getDlsSecondEquipmentName()%></span></td>
				        <td>&nbsp;</td>
				      	<td>&nbsp;</td>
				      	<td>&nbsp;</td>
				      	<td>&nbsp;</td>
				      	<td>&nbsp;</td>
				      	<td>&nbsp;</td>
				      	<td>&nbsp;</td>
				      	<td>&nbsp;</td>
				      	<td>&nbsp;</td>
				      	<td>&nbsp;</td>
				    </tr>
	    		<%
	    				List<DeviceListDetail> secDetailList = secondDevice.getDeviceListDetailList();
	    				secondDeviceCount +=secDetailList.size();
	    				if(secDetailList.size() > 0){
	    					String thirdNode = "";
							String thirdNodeClass = "";
	    					for(int indexj = 0; indexj < secDetailList.size(); indexj++){
	    						DeviceListDetail secDetail = secDetailList.get(indexj);
	    						thirdNode = "node-" + secondDeviceCount + indexj;
								thirdNodeClass = "child-of-node-" + firstDeviceCount + indexi;
								%>
									<tr id="<%=thirdNode%>" class="<%=thirdNodeClass%>">
								        <td class="droplist"><span class="node"><%=secDetail.getDldEquipmentName()%></span></td>
								        <td><%=secDetail.getDldEquipmentStandard()%></td>
								      	<td align="center"><%=secDetail.getDldEquipmentCount()%></td>
								      	<td align="center"><%=secDetail.getDeviceUnit()==null?"":secDetail.getDeviceUnit().getDuName()%></td>
								      	<td align="center"><%=secDetail.getDldBeginDate()==null?"":secDetail.getDldBeginDate()%></td>
								      	<td align="center"><%=secDetail.getDldEndDate()==null?"":secDetail.getDldEndDate()%></td>
								    	<td><%=secDetail.getDldComment()==null?"":secDetail.getDldComment()%></td>
								    	<td><%=secDetail.getDldResponsiblePerson()==null?"":secDetail.getResponsiblePerson().getPerson().getPersonName()%></td>
								    	<%--if(secDetail.getSupplierCount() == 0){--%>
								      	<td align="center">
											<logic:equal name="accountPerson" property="hasCreatePower" value="true">
												<a href="javascript:openDialog('tenderProjectManage.do?method=editBaseQuotedPriceInfo&onType=second&id=<%=secDetail.getDldId()%>', 580,250);void(0);"><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
											</logic:equal>
										</td>
				      					<td align="center">
											<logic:equal name="accountPerson" property="hasCreatePower" value="true">
												<a href="javascript:openDialog('tenderProjectManage.do?method=detailDeviceReportPrice&onType=second&id=<%=secDetail.getDldId()%>', 500,250);void(0);"><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
											</logic:equal>
										</td>
				      					<%--} else {--%>
<!--				      					<td></td><td></td>-->
				      					<%--}--%>
								      	<td align="center"><%=secDetail.getQuotedPriceFlag()==1?"V":"X"%></td>
								    </tr>
								<%
	    					}
	    				}
	    			}} if(deviceDetailList.size() > 0){
	    				String lastNode = "";
						String lastNodeClass = "";
						firstDeviceCount += deviceDetailList.size();
						for(int indexk = 0; indexk < deviceDetailList.size(); indexk++){
	    					DeviceListDetail lastDetail = deviceDetailList.get(indexk);
	    					lastNode = "node-" + firstDeviceCount + indexk;
							lastNodeClass = "child-of-node-" + i;
	    		%>
	    					<tr id="<%=lastNode%>" class="<%=lastNodeClass%>">
						        <td class="droplist"><span class="node"><%=lastDetail.getDldEquipmentName()%></span></td>
						        <td><%=lastDetail.getDldEquipmentStandard()%></td>
						      	<td align="center"><%=lastDetail.getDldEquipmentCount()%></td>
						      	<td align="center"><%=lastDetail.getDeviceUnit()==null?"":lastDetail.getDeviceUnit().getDuName()%></td>
								<td align="center"><%=lastDetail.getDldBeginDate()==null?"":lastDetail.getDldBeginDate()%></td>
						      	<td align="center"><%=lastDetail.getDldEndDate()==null?"":lastDetail.getDldEndDate()%></td>
						    	<td><%=lastDetail.getDldComment()==null?"":lastDetail.getDldComment()%></td>
						    	<td><%=lastDetail.getDldResponsiblePerson()==null?"":lastDetail.getResponsiblePerson().getPerson().getPersonName()%></td>
						      	<%--if(lastDetail.getSupplierCount() == 0){--%>
						      	<td align="center">
									<logic:equal name="accountPerson" property="hasCreatePower" value="true">
										<a href="javascript:openDialog('tenderProjectManage.do?method=editBaseQuotedPriceInfo&onType=second&id=<%=lastDetail.getDldId()%>', 580,250);void(0);"><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
									</logic:equal>
								</td>
		      					<td align="center">
									<logic:equal name="accountPerson" property="hasCreatePower" value="true">
										<a href="javascript:openDialog('tenderProjectManage.do?method=detailDeviceReportPrice&onType=second&id=<%=lastDetail.getDldId()%>', 500,250);void(0);"><img border="0" src="pages\images\icon\16x16\modify.gif"></a>
									</logic:equal>
								</td>
						      	<%--} else {--%>
<!--				      			<td></td><td></td>-->
				      			<%--}--%>
						      	<td align="center"><%=lastDetail.getQuotedPriceFlag()==1?"V":"X"%></td>
						    </tr>
	    		<%
	    			}}
	    		%>
	    	</logic:iterate>
	    </logic:notEmpty>
	    <logic:empty name="deviceList">
			<tr><td colspan="11"><bean:message key="noRecord" /></td></tr>
		</logic:empty>
	  </tbody> 	
	</table>
  </html:form>

<html:javascript formName="tenderProjectForm" dynamicJavascript="true" staticJavascript="false" />
<script type="text/javascript" src="<html:rewrite forward='staticjavascript'/>"></script>

