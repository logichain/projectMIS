<%@page pageEncoding="GBK"%>
<%@ include file="WEB-INF/pages/common/include.jsp"%>

<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ page import="org.king.framework.exception.BusinessException" %>
 <%@ include file="/pages/common/meta.jsp" %>


<html>
<head>
    <title>����ҳ��</title>   
</head>

<body bgcolor="#c0d2ec">

    <%
        exception = (BusinessException) request.getAttribute("exception");
    %>

    <h3>ҵ�����:</h3>

    <p><%=exception.getMessage()%></p>
    <button onclick="history.back();">����</button>

</body>
</html>