<%@page pageEncoding="GBK"%>
<%@ include file="../common/include.jsp"%>

<html:form action="tenderProjectManage.do" onsubmit="return checkFormValidate();">
		<html:errors />
		
		<input type="hidden" name="method" value="saveTenderResult">
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr><td>&nbsp;</td></tr>	
			<tr><td width="70%">&nbsp;</td>						
				<td align="center" width="12%">
					<html:submit styleClass="savebutton" onclick="chgAction(document.all.method,'saveTenderResult');" style="cursor: hand;">
						&nbsp;
					</html:submit>
				</td>
			</tr>
		</table>	
		<table class="win" CELLPADDING="0" CELLSPACING="0" WIDTH="100%" border="0">
			<tr>
				<td width="10%" align="right">
					<bean:message bundle="tendermanage" key="tender.tenderOpenDate" />£º
				</td>
				<td width="15%">
					<html:text property="tenderResultInfo.tprTenderOpenDate" readonly="true" size="18" maxlength="32" onclick="SelectDate(this)"/>*
				</td>
				<td width="8%" align="right">
					<bean:message bundle="tendermanage" key="tender.tenderHighestPrice" />£º
				</td>
				<td width="15%">						
					<html:text property="tenderResultInfo.tprTenderHighestPriceStr" size="18" maxlength="20"/>
				</td>
				
				<td width="8%" align="right">
					<bean:message bundle="tendermanage" key="tender.tenderLowestPrice" />£º
				</td>
				<td width="16%">
					<html:text property="tenderResultInfo.tprTenderLowestPriceStr" size="18" maxlength="20"/>
				</td>
				<td width="8%"></td>
				<td></td>
			</tr>

			<tr>
				<td align="right">
					<bean:message bundle="tendermanage" key="tender.tenderBasePrice" />£º
				</td>
				<td>
					<html:text property="tenderResultInfo.tprTenderBasePriceStr" size="18" maxlength="20"/>
				</td>	
				
				<td align="right">
					<bean:message bundle="tendermanage" key="tender.tenderPrice" />£º 
				</td>
				<td>						
					<html:text property="tenderResultInfo.tprTenderPriceStr" size="18" maxlength="20"/>
				</td>	

				<td align="right">
					<bean:message bundle="tendermanage" key="tender.tenderResult" />£º 
				</td>
				<td>						
					<html:select property="tenderResultInfo.tprTenderResult" style="width:120">	
						<html:option value=""></html:option>
						<html:options collection="tenderResultList" property="tsId" labelProperty="tsName"/>
					</html:select>*
				</td>	
			</tr>
			
			<tr>
				<td align="right">
					<bean:message bundle="tendermanage" key="tender.reviewQuotesScore" />£º
				</td>
				<td>
					<html:text property="tenderResultInfo.tprReviewQuotesScore" size="18" maxlength="7"/>
				</td>
				<td align="right">
					<bean:message bundle="tendermanage" key="tender.creditScore" />£º
				</td>
				<td>
					<html:text property="tenderResultInfo.tprCreditScore" size="18" maxlength="7"/>
				</td>
				<td align="right">
					<bean:message bundle="tendermanage" key="tender.businessScore" />£º
				</td>
				<td>
					<html:text property="tenderResultInfo.tprBusinessScore" size="18" maxlength="7"/>
				</td>
				<td align="right">
					<bean:message bundle="tendermanage" key="tender.techniqueScore" />£º
				</td>
				<td>
					<html:text property="tenderResultInfo.tprTechniqueScore" size="18" maxlength="7"/>
				</td>
			</tr>
					
			
			<tr>
				<td align="right">
					<bean:message bundle="tendermanage" key="tender.tenderSummary" />£º
				</td>
				<td colspan="7">
					<html:textarea property="tenderResultInfo.tprTenderSummary" rows="5" cols="100"/>
				</td>
			</tr>

			<logic:iterate name="tenderProjectForm" property="tenderResultInfo.competitorTenderResultList" id="detail" indexId="i">								
				<tr>
					
					<td align="right">
						<bean:message bundle="tendermanage" key="tender.competitor" /><%=i+1 %>£º
					</td>
					<td colspan="3">
						<html:text name="tenderProjectForm" property='<%= "tenderResultInfo.competitorTenderResultList[" + i + "].competitor.CName" %>' size="60" readonly="true" style="background-color:LightGray;"/>
						<html:button property="" onclick='<%= "openDialog(\'tenderProjectManage.do?method=searchCompetitor&index=" + i + "\',800,420);" %>'>...</html:button>
					</td>
					<td align="right">
						<bean:message bundle="tendermanage" key="tender.competitorQuotes" />£º
					</td>
					<td>
						<html:text name="tenderProjectForm" property='<%= "tenderResultInfo.competitorTenderResultList[" + i + "].ctrTenderPriceStr" %>' size="18" maxlength="20"/>
					</td>		

					<td align="right">
						<bean:message bundle="tendermanage" key="tender.tenderResult" />£º 
					</td>
					<td>						
						<html:select  name="tenderProjectForm" property='<%= "tenderResultInfo.competitorTenderResultList[" + i + "].ctrTenderResult" %>' style="width:120">	
							<html:option value=""></html:option>
							<html:options collection="tenderResultList" property="tsId" labelProperty="tsName"/>
						</html:select>
					</td>			
				</tr>		
			</logic:iterate>
			
			<tr><td>&nbsp;</td></tr>						
			<tr>
				<td align="right">
					<bean:message bundle="tendermanage" key="tender.attachment" />£º
				</td>
				<td colspan="5">					
				
					<logic:iterate id="am" name="tenderProjectForm" property="tenderInfo.attachmentList" indexId="i">						
						<logic:equal name="am" property="paInputInterface" value="1">
							<span title="<bean:write name="am" property="paLocalUrl"/>">
								<bean:write name="am" property="paName"/>
								<a href="tenderProjectManage.do?method=deleteAttachment&i=<%=i%>&type=tenderResult"><img border="0" src="pages\images\icon\16x16\delete.gif"></a>£»
							</span>			
						</logic:equal>	
					</logic:iterate>
				
				</td>			
				<td>
					<html:button property="" onclick="openDialog('tenderProjectManage.do?method=addAttachment&type=tenderResult',500,160);" styleClass="selectFileButton">&nbsp;</html:button>
				</td>	
			</tr>	
			<tr><td>&nbsp;</td></tr>
		</table>
				
	</html:form>