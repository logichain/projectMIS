<%@ page errorPage="/error.jsp"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-nested" prefix="nested" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/sql" prefix="sql" %>
<%@ taglib uri="/tags/pow2webgui" prefix="gui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ec" uri="http://www.extremecomponents.org" %>
<%@ taglib prefix="jodd" uri="http://www.king.org/jodd_form" %>
<%@ page import="com.pow2.util.Util" %>
<%@ page import="java.util.*,java.text.*" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String bss = "'00','01','02','03','04',05";
String sss = "'10','11','12','13','14','15'";
String bks = "'20','21','22'";
String zks = "'23','24'";
java.util.Calendar ca1 = java.util.Calendar.getInstance();
java.text.SimpleDateFormat t1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
java.text.SimpleDateFormat t3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
java.text.SimpleDateFormat t2 = new java.text.SimpleDateFormat("yyyy");
String v_nd = t2.format(ca1.getTime());
int dqnd = Integer.parseInt(v_nd);
String nowdate = t1.format(ca1.getTime());
String time = t3.format(ca1.getTime());

%>