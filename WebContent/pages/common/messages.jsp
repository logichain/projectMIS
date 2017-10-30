<%-- Error Messages --%>
<logic:messagesPresent>
    <div class="error" id="errorMessages">
        <html:messages id="error">
            
            <font color=red><c:out value="${error}" escapeXml="false"/><br/></font>
        </html:messages>
    </div>
</logic:messagesPresent>

<%-- Success Messages --%>
<logic:messagesPresent message="true">
    <div class="message" id="successMessages">
        <html:messages id="message" message="true">
            
          <font color=red>   <c:out value="${message}" escapeXml="false"/><br/></font>
        </html:messages>
    </div>
</logic:messagesPresent>
