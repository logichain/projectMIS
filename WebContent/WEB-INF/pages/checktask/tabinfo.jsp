<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<gui:window title='��������' prototype="boWindow">
	<gui:tabbedPanel selectedTab='<%=request.getParameter("selectTab")%>' prototype="boTabbedPanel" width="70" >
		<gui:tab prototype="boTab" name="currentTask" title="��ǰ����" followUp="projectManage.do?method=resetSearchCheckTask&status=1">	
			<%if("currentTask".equals(request.getParameter("selectTab"))) {%>
			<c:import url="/WEB-INF/pages/checktask/currentTaskTab.jsp">
			</c:import>
			<%} %>
		</gui:tab>
		<gui:tab prototype="boTab" name="futureTask" title="δ������" followUp="projectManage.do?method=resetSearchCheckTask&status=0">	
			<%if("futureTask".equals(request.getParameter("selectTab"))) {%>
			<c:import url="/WEB-INF/pages/checktask/futureTaskTab.jsp">
			</c:import>
			<%} %>
		</gui:tab>
		<gui:tab prototype="boTab" name="checkedTask" title="���������" followUp="projectManage.do?method=resetSearchCheckTask&status=2">	
			<%if("checkedTask".equals(request.getParameter("selectTab"))) {%>
			<c:import url="/WEB-INF/pages/checktask/checkedTaskTab.jsp">
			</c:import>
			<%} %>
		</gui:tab>		
	</gui:tabbedPanel>		
</gui:window>
