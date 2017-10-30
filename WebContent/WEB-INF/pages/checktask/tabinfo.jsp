<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<gui:window title='工作任务' prototype="boWindow">
	<gui:tabbedPanel selectedTab='<%=request.getParameter("selectTab")%>' prototype="boTabbedPanel" width="70" >
		<gui:tab prototype="boTab" name="currentTask" title="当前任务" followUp="projectManage.do?method=resetSearchCheckTask&status=1">	
			<%if("currentTask".equals(request.getParameter("selectTab"))) {%>
			<c:import url="/WEB-INF/pages/checktask/currentTaskTab.jsp">
			</c:import>
			<%} %>
		</gui:tab>
		<gui:tab prototype="boTab" name="futureTask" title="未来任务" followUp="projectManage.do?method=resetSearchCheckTask&status=0">	
			<%if("futureTask".equals(request.getParameter("selectTab"))) {%>
			<c:import url="/WEB-INF/pages/checktask/futureTaskTab.jsp">
			</c:import>
			<%} %>
		</gui:tab>
		<gui:tab prototype="boTab" name="checkedTask" title="已完成任务" followUp="projectManage.do?method=resetSearchCheckTask&status=2">	
			<%if("checkedTask".equals(request.getParameter("selectTab"))) {%>
			<c:import url="/WEB-INF/pages/checktask/checkedTaskTab.jsp">
			</c:import>
			<%} %>
		</gui:tab>		
	</gui:tabbedPanel>		
</gui:window>
