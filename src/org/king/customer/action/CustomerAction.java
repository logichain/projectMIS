package org.king.customer.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.DynaValidatorForm;
import org.king.common.service.CommonService;
import org.king.customer.bean.Customer;
import org.king.customer.bean.CustomerAttachment;
import org.king.customer.service.CustomerService;
import org.king.framework.web.action.BaseAction;
import org.king.projectmanage.bean.ProjectAttachment;
import org.king.security.domain.Account;
import org.king.utils.FileUtil;

public class CustomerAction extends BaseAction {
	private CustomerService customerService;
	private CommonService commonService;
	
	/**
	 * 下载附件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward downloadAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		String id = request.getParameter("id");
		CustomerAttachment pa = customerService.getCustomerAttachmentById(Integer.parseInt(id));
		
		String fileName = pa.getCaUrl();	

		try {
			response.setContentType(FileUtil.getContentType(fileName));
//			response.setHeader("Content-Disposition", "attachment;filename=" + fileName.substring(fileName.lastIndexOf("\\") + 1));	
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(pa.getCaName().getBytes("GB2312"), "ISO_8859_1") + fileName.substring(fileName.lastIndexOf(".")));
			
			InputStream file = new FileInputStream(fileName);
			byte[] bit = new byte[1024];
			int len = file.read(bit);
			OutputStream out = response.getOutputStream();

			while (len != -1) {
				out.write(bit, 0, len);
				len = file.read(bit);
			}
			out.close();
			file.close();

		} catch (Exception e) {
			e.printStackTrace();

			ActionMessages errors = new ActionMessages();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.download"));
			saveErrors(request, errors);

			return mapping.findForward("fail");
		}

		return null;
	}
	/**
	 * 删除附件：项目输入，base编辑用
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteAttachmentByIndex(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;		
		
		Customer customerInfo = (Customer) dform.get("customerInfo");
		String index = request.getParameter("index");

		customerInfo.getAttachmentList().remove(Integer.parseInt(index));
		
		return mapping.findForward("input");
		
	}
	/**
	 * 删除附件：项目输入，base编辑用
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		Customer customerInfo = (Customer) dform.get("customerInfo");
		String id = request.getParameter("id");

		for (CustomerAttachment p : customerInfo.getAttachmentList()) {
			if (p.getCaId().equals(Integer.valueOf(id))) {
				p.setCaFlag(CommonService.DELETE_FLAG);
				p.setModified(true);

				break;
			}
		}

		return mapping.findForward("input");
	}
	
	/**
	 * 增加附件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		Account user = (Account) request.getSession().getAttribute("accountPerson");

		Customer customerInfo = (Customer) dform.get("customerInfo");
		
		CustomerAttachment ca = new CustomerAttachment();
		ca.setCaFlag(CommonService.NORMAL_FLAG);
		ca.setCaCreateTime(new Date());
		
		ca.setCaCreateUser(user.getId());		
		customerInfo.setCurrentAttachment(ca);

		return mapping.findForward("uploadAttachment");
	}
	
	/**
	 * 提交附件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward confirmAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		Customer customerInfo = (Customer) dform.get("customerInfo");
		CustomerAttachment ca = customerInfo.getCurrentAttachment();
	
		customerInfo.setCurrentAttachment(null);
		
		customerInfo.getAttachmentList().add(ca);
		
		return mapping.findForward("refreshInput");
	
	}
	
	public ActionForward createCustomer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{				
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		Customer c = customerService.createCustomer();	
		c.setSaleman(new Account());
		c.setCFlag(Customer.CUSTOMER_FLAG_ALTERNATIVE);
		dform.set("customerInfo",c);
		
		this.prepareMetaData(request);
						
		return  mapping.findForward("input");		
	}
	
	public ActionForward checkCustomer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
				
		String gId = (String) request.getParameter("id");
		Customer c = customerService.getCustomerById(Integer.valueOf(gId));
		c.setCFlag(Customer.CUSTOMER_FLAG_QUALIFIED);
		customerService.saveCustomer(c);
										
		return this.searchCustomer(mapping, dform, request, response);			
	}
	
	public ActionForward alternativeCustomer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		String gId = (String) request.getParameter("id");
		Customer c = customerService.getCustomerById(Integer.valueOf(gId));
		c.setCFlag(Customer.CUSTOMER_FLAG_ALTERNATIVE);
		customerService.saveCustomer(c);
								
		return this.searchQualifiedCustomer(mapping, dform, request, response);		
	}
	
			
	public ActionForward searchCustomer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String page = request.getParameter("pager.offset");
		
		Customer searchInfo = (Customer) dform.get("searchInfo");
		
		Object[] args = {searchInfo,user,page};
		
		ArrayList<Customer> gList = customerService.searchCustomer(args);
		Integer count = customerService.searchCustomerCount(args);
		if(count > 0)		
		{
			request.setAttribute("customerList",gList);			
			request.setAttribute("customerCount",count);
		}
		
		this.prepareMetaData(request);
		
		return mapping.findForward("list");		
	}
	
	public ActionForward searchQualifiedCustomer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String page = request.getParameter("pager.offset");
		
		Customer searchInfo = (Customer) dform.get("searchInfo");
		
		Object[] args = {searchInfo,user,page};
		
		ArrayList<Customer> gList = customerService.searchCustomer(args);
		Integer count = customerService.searchCustomerCount(args);
		if(count > 0)		
		{
			request.setAttribute("customerList",gList);			
			request.setAttribute("customerCount",count);
		}
		
		this.prepareMetaData(request);
		
		return mapping.findForward("qualifiedList");		
	}
	
	public ActionForward resetSearchCustomer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		Customer searchInfo = new Customer();
		dform.set("searchInfo",searchInfo);
		
		return this.searchCustomer(mapping, dform, request, response);
	}
	
	public ActionForward resetSearchQualifiedCustomer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		Customer searchInfo = new Customer();
		searchInfo.setCFlag(Customer.CUSTOMER_FLAG_QUALIFIED);
		dform.set("searchInfo",searchInfo);
		
		return this.searchQualifiedCustomer(mapping, dform, request, response);
	}
		
		
	public ActionForward saveCustomer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String uploadPath = request.getSession().getServletContext().getInitParameter("uploadFilePath");
		if (!uploadPath.endsWith("\\")) {
			uploadPath = uploadPath + "\\";
		}
		
		Customer c = (Customer) dform.get("customerInfo");
		
		String userId = (String) request.getSession().getAttribute("account");
		if(c.getCId() == null || c.getCId() == 0)		
		{
			c.setCCreateUser(userId);
		}
		
		try{
			customerService.saveCustomer(c,uploadPath);	
			
			c.setCustomerType(commonService.getCustomerTypeById(c.getCType()));
			if(c.getCTradeType() != null)
			{
				c.setTradeType(commonService.getTradeTypeById(c.getCTradeType()));
			}			
		}		
		catch(Exception e)
		{
			ActionMessages errors = new ActionMessages();			
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.opererror"));
			saveErrors(request, errors);
			
			this.prepareMetaData(request);
			
			e.printStackTrace();
			
			return mapping.findForward("input");
		}
		
		if("create".equals(request.getParameter("optype")))
		{						
			return this.createCustomer(mapping, dform, request, response);
		}
		
		return this.searchCustomer(mapping, dform, request, response);		
	}
	
	private void prepareMetaData(HttpServletRequest request)
	{
		request.setAttribute("typeList", commonService.getCustomerTypeList());
		request.setAttribute("tradeTypeList", commonService.getTradeTypeList());
		request.setAttribute("natureList", commonService.getCustomerNatureList());
	}
		
	
	public ActionForward modifyCustomer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		String gId = (String) request.getParameter("id");
		Customer g = customerService.getCustomerById(Integer.valueOf(gId));
		dform.set("customerInfo",g);
		
		if(g.getCSaleman() == null)
		{
			g.setSaleman(new Account());
		}
		
		this.prepareMetaData(request);
						
		return mapping.findForward("input");
			
	}
	
	public ActionForward displayCustomerInfo(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		String gId = (String) request.getParameter("id");
		Customer g = customerService.getCustomerById(Integer.valueOf(gId));
		
		request.setAttribute("customerInfo",g);
								
		return mapping.findForward("display");
			
	}
	public ActionForward searchAccount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String page = request.getParameter("pager.offset");
		Account searchInfo = (Account) dform.get("accountInfo");
		
		Object[] args = { searchInfo, page};
		
		ArrayList<Account> gList = commonService.searchAccount(args);
		Integer count = commonService.searchAccountCount(args);
		if(count > 0)		
		{
			request.setAttribute("accountList",gList);			
			request.setAttribute("accountCount",count);
		}
		
		request.setAttribute("departmentList", commonService.getDepartmentList());
		request.setAttribute("postList", commonService.getPostList());
	
		return  mapping.findForward("searchAccount");
	}
	
	public ActionForward resetSearchAccount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
		Account searchInfo = new Account();

		dform.set("accountInfo", searchInfo);
	
		return this.searchAccount(mapping, dform, request, response);
	}
	public ActionForward confirmAccount(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		Customer c = (Customer) dform.get("customerInfo");
		String gId = request.getParameter("id");
		
		Account a = commonService.getAccountById(gId);
		
		c.setCSaleman(a.getId());
		c.setSaleman(a);
		
		this.prepareMetaData(request);
										
		return mapping.findForward("refreshInput");
	}
	public ActionForward refreshInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		
		this.prepareMetaData(request);
		
		return  mapping.findForward("input");
	}
	
	public ActionForward deleteCustomer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{				
		String id = request.getParameter("id");
				
		Customer c = customerService.getCustomerById(Integer.valueOf(id));
		customerService.deleteCustomer(c);
							
		return this.searchCustomer(mapping, form, request, response);			
	}
	
	
	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}


	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}


	public CommonService getCommonService() {
		return commonService;
	}
}
