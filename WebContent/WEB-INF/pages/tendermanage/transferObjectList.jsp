<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>
<center>
	<bean:define id="title">
		<bean:message bundle="security" key="account.title" />
	</bean:define>

	<gui:window title="<%=title%>" prototype="boWindow" color="100%">			
		<html:form action="tenderProjectManage.do">
			<input type="hidden" name="method" value="transferTenderProject">
			<table class="sort-table" cellSpacing="1" cellPadding="1" width="100%" border="1">						
				
					<thead>
						<tr>
							<td width="6%" align="center">
								<bean:message bundle="projectmanage" key="serial" />
							</td>
							<td width="10%" align="center">
								<bean:message bundle="security" key="account.name" />
							</td>
							<td width="10%" align="center">
								<bean:message bundle="security" key="person.code" />
							</td>
							<td width="20%" align="center">
								<bean:message bundle="security" key="person.name" />
							</td>
							<td align="center">
								<bean:message bundle="security" key="person.dept" />/
								<bean:message bundle="security" key="person.post" />
							</td>
							<td width="20%" align="center">
								<bean:message bundle="security" key="person.email" />
							</td>
						</tr>
					</thead>
				<logic:present name="accountList" scope="request">					
					<logic:iterate name="accountList" id="account" indexId="i">					
					  <tr>
						<% int a = i%2;request.setAttribute("a",a);%>
						<logic:equal name="a" value="0"><tr class="even"></logic:equal>
						<logic:equal name="a" value="1"><tr class="odd"></logic:equal>
							<td align="center">										
								<input type="radio" name="id" value="<bean:write  name="account" property="id" />">
								<%=i+1 %>
							</td>
							<td>										
								<bean:write name="account" property="name" />
							</td>
							<td>
								<bean:write name="account" property="person.personCode" />
							</td>
							<td>
								<bean:write name="account" property="person.personName" />
							</td>
							<td>
								<logic:iterate id="up" name="account" property="usrPostList">
									<logic:notEmpty name="up" property="deptObject">
										<bean:write name="up" property="deptObject.DName"/>
									</logic:notEmpty>/
									<logic:notEmpty name="up" property="postObject">
										<bean:write name="up" property="postObject.PName"/>
									</logic:notEmpty>;
								</logic:iterate>
							</td>
							<td>
								<bean:write name="account" property="person.email" />
							</td>
						</tr>							
					</logic:iterate>				
				</logic:present>
				<logic:notPresent name="accountList" scope="request">
					<tr>
						<td COLSPAN="3">
							<bean:message bundle="security" key="account.noRecord" />
						</td>
					</tr>
				</logic:notPresent>		
			</table>
			
			<table class="win" cellSpacing="0" cellPadding="0" width="100%" border="0">
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td COLSPAN="2" ALIGN="RIGHT">
						<html:submit styleClass="confirmbutton" onclick="chgAction(document.all.method,'confirmTransferTenderProject');return validSelectedIdForEdit();">
							&nbsp;
						</html:submit>						
					</td>
				</tr>
			</table>
		</html:form>	

	</gui:window>
</center>

<script language="JavaScript">

 function validSelectedIdForEdit(){
     var flag = 0;
     var count = 0;
     var a = document.all("id");
     if(a==null){
        alert("没有用户可以编辑");
        return false;
     }   
     if(a.length==null){
        if(!a.checked) {
          alert("请选择用户!");
          return false;
        }    
     }else {
        for  (var  i=0;  i<a.length;  i++){  
	         if(a[i].checked==true){
	            flag=flag+1;
	         }
        }  
	     if(flag==0){
	         alert("请选择用户!");
	         return false;
	     }    
	     if(flag>1){
	         alert("请选择一个用户!");
	         return false;
	     }
     
     }
     
     return true;
    
     
         
              
 }
 
   
 function chgAction(obj,str){
	obj.value=str;
 }

</script>
