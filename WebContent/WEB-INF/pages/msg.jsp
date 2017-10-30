<%@ include file="./common/include.jsp"%>

<bean:define id="title">
	<bean:message bundle="security" key="msg.title"/>
</bean:define>

<body  bgcolor="#c0d2ec">

<gui:window title="<%=title%>" prototype="boWindow" color="80%">
	<html:errors/>
</gui:window>

</body>
