package org.king.tender.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.DynaValidatorForm;
import org.king.common.attachmentcategory.AttachmentCategory;
import org.king.common.checktask.CheckTask;
import org.king.common.post.Post;
import org.king.common.projectstatus.ProjectStatus;
import org.king.common.service.CommonService;
import org.king.common.tenderrole.TenderRole;
import org.king.customer.bean.Customer;
import org.king.customer.service.CustomerService;
import org.king.framework.web.action.BaseAction;
import org.king.projectmanage.bean.ProjectAttachment;
import org.king.projectmanage.bean.ProjectContract;
import org.king.projectmanage.bean.ProjectTeam;
import org.king.projectmanage.bean.TenderAttachment;
import org.king.projectmanage.bean.TenderProject;
import org.king.projectmanage.service.ProjectManageService;
import org.king.security.domain.Account;
import org.king.security.domain.UsrPost;
import org.king.tender.bean.BudgetItem;
import org.king.tender.bean.DeviceList1st;
import org.king.tender.bean.DeviceList2nd;
import org.king.tender.bean.DeviceListDetail;
import org.king.tender.bean.ProjectBudget;
import org.king.tender.service.TenderManageService;
import org.king.utils.DateUtil;
import org.king.utils.FileUtil;
import org.king.utils.MailTool;

public class TenderManageAction extends BaseAction {
	private TenderManageService tenderManageService;
	private ProjectManageService projectManageService;
	private CommonService commonService;
	private CustomerService customerService;
	
	/**
	 * 删除附件：项目输入，base编辑用
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteTenderAttachmentByIndex(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		String index = request.getParameter("index");

		tp.getTenderAttachmentList().remove(Integer.parseInt(index));

		return this.editTenderDocument(mapping, dform, request, response);
	}
	
	public ActionForward checkTenderDocumentBegin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");	
		Account user = (Account) request.getSession().getAttribute("accountPerson");
				
		//发送
		ArrayList<CheckTask> taskList = projectManageService.getTenderDocumentCheckStep(tp, user, tp.getTpDept());
				
		if(taskList.size() > 0)//无意义？
		{			
			projectManageService.saveCheckTaskList(taskList);
			
			tp.setTpTenderdocCheckStatus(ProjectStatus.TENDERDOC_CHECK_STATUS_CHECKING);				
			tenderManageService.updateTenderProject(tp);
		}		
		
		//发送评审任务的mail
		String mailList = "";
		for(CheckTask ct:taskList)
		{
			if(ct.getCtStatus().equals(CheckTask.TASK_STATUS_CURRENT_CHECK))
			{
				mailList = mailList + MailTool.MAIL_ADDRESS_SEPARATER + projectManageService.getChecktaskMail(ct);
			}
		}		
		projectManageService.sendCheckTaskMail(mailList, taskList.get(0));
		
		return this.editTenderDocument(mapping, dform, request, response);
	}
	

	/**
	 * 保存项目文档信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward saveTenderDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		String uploadPath = request.getSession().getServletContext().getInitParameter("uploadFilePath");
		if (!uploadPath.endsWith("\\")) {
			uploadPath = uploadPath + "\\";
		}

		TenderProject tp = (TenderProject) dform.get("tenderInfo");

		projectManageService.saveTenderDocument(tp, uploadPath);
		
		return this.editTenderDocument(mapping, dform, request, response);
	}

	/**
	 * 刷新项目文档页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refreshTenderDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		return this.editTenderDocument(mapping, dform, request, response);
	}
	
	/**

	
	/**
	 * 编辑项目文档
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editTenderDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");
				
		List<CheckTask> taskList = tenderManageService.getAllStepCheckTask(tp,String.valueOf(CheckTask.TASK_TYPE_TENDER_DOCUMENT_CHECK));
		projectManageService.retrieveTaskCheckableUserList(taskList);
		request.setAttribute("taskList", taskList);
		
		return mapping.findForward("tenderDocumentInput");
	}


	
	public ActionForward editTenderAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");

		String id = request.getParameter("id");

		for (TenderAttachment pa : tp.getTenderAttachmentList()) {
			if (pa.getTaId().equals(Integer.valueOf(id))) {
				tp.setCurrentTenderAttachment(pa);
				break;
			}
		}

		return mapping.findForward("editTenderAttachment");
	}

	
	public ActionForward downloadTenderAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		String id = request.getParameter("id");
		TenderAttachment pa = projectManageService.getTenderAttachmentById(Integer.parseInt(id));
		
		String fileName = pa.getTaUrl();	

		try {
			response.setContentType(FileUtil.getContentType(fileName));
//			response.setHeader("Content-Disposition", "attachment;filename=" + fileName.substring(fileName.lastIndexOf("\\") + 1));	
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(pa.getTaName().getBytes("GB2312"), "ISO_8859_1") + fileName.substring(fileName.lastIndexOf(".")));
			
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

	
	public ActionForward displayTenderDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		
		List<CheckTask> taskList = tenderManageService.getAllStepCheckTask(tp,String.valueOf(CheckTask.TASK_TYPE_TENDER_DOCUMENT_CHECK));
		projectManageService.retrieveTaskCheckableUserList(taskList);
		request.setAttribute("taskList", taskList);

		return mapping.findForward("tenderDocumentDisplay");
	}

	public ActionForward deleteTenderAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		String id = request.getParameter("id");

		for (TenderAttachment p : tp.getTenderAttachmentList()) {
			if (p.getTaId().equals(Integer.valueOf(id))) {
				p.setTaFlag(CommonService.DELETE_FLAG);
				p.setModified(true);

				break;
			}
		}

		return this.editTenderDocument(mapping, dform, request, response);
	}

	/**
	 * 提交附件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward confirmTenderAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		TenderAttachment pa = tp.getCurrentTenderAttachment();
		if (pa.getTaId() == null) {
			pa.setCategory(commonService.getAttachmentCategoryById(pa.getTaAttachmentCategory()));
			tp.getTenderAttachmentList().add(pa);
		} else {
			pa.setModified(true);
		}
						
		return mapping.findForward("refreshTenderDocument");	
	}
	
	/**

	/**
	 * 增加附件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addTenderAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		Account user = (Account) request.getSession().getAttribute("accountPerson");

		TenderProject tp = (TenderProject) dform.get("tenderInfo");

		TenderAttachment ta = new TenderAttachment();
		ta.setTaAttachmentCategory(AttachmentCategory.TENDER_DOC_ATTACHMENT);
		
		ta.setTaFlag(CommonService.NORMAL_FLAG);
		ta.setTaCreateTime(new Date());
		ta.setTaCreateUser(user.getId());
		ta.setCreateUser(user);
		ta.setTaTenderProject(tp.getTpId());
		tp.setCurrentTenderAttachment(ta);

		return mapping.findForward("uploadTenderAttachment");
	}

	public ActionForward showBudgetChecktaskList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		String type = request.getParameter("type");
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");	
		
		List<CheckTask> taskList = tenderManageService.getAllStepCheckTask(tp,type);
		projectManageService.retrieveTaskCheckableUserList(taskList);
		request.setAttribute("taskList", taskList);
			
		return mapping.findForward("checkTaskList");
	}
	

	public ActionForward checkTenderBudgetBegin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");	
		ProjectBudget tenderBudget = tp.getTenderBudget();
		Account user = (Account) request.getSession().getAttribute("accountPerson");
				
		//发送
		ArrayList<CheckTask> taskList = projectManageService.getTenderBudgetCheckStep(tp.getTenderBudget(), user, tp.getTpDept());
				
		if(taskList.size() > 0)//无意义？
		{			
			projectManageService.saveCheckTaskList(taskList);
			
			tenderBudget.setPbCheckResult(ProjectBudget.BUDGET_CHECK_STATUS_CHECKING);				
			tenderManageService.updateProjectBudget(tenderBudget);			
		}		
				
		//发送评审任务的mail
		String mailList = "";
		for(CheckTask ct:taskList)
		{
			if(ct.getCtStatus().equals(CheckTask.TASK_STATUS_CURRENT_CHECK))
			{
				mailList = mailList + MailTool.MAIL_ADDRESS_SEPARATER + projectManageService.getChecktaskMail(ct);
			}
		}		
		projectManageService.sendCheckTaskMail(mailList, taskList.get(0));
				
		return this.editTenderBugetItem(mapping, dform, request, response);
	}
	
	
	
	
	
	public ActionForward displayBaseTenderProject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String id = request.getParameter("id");
				
		TenderProject tp = tenderManageService.getTenderProjectById(Integer.valueOf(id));
							
		dform.set("tenderInfo", tp);
		
		String userId = (String) request.getSession().getAttribute("account");
		if(!userId.equals("0"))
		{
			for(ProjectTeam pt:tp.getTeamMemberList())
			{
				if(pt.getPtAccount().equals(userId))
				{
					dform.set("teamMemberInfo", pt);
				}
			}			
		}
		
		this.prepareMetaData(request);
		
		return mapping.findForward("baseDisplay");
	}
	
	public ActionForward displayTenderBase(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");

		this.prepareMetaData(request);

		return mapping.findForward("baseDisplay");
	}
	public ActionForward displayCompetitor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");

		this.prepareMetaData(request);

		return mapping.findForward("competitorDisplay");
	}
	public ActionForward displayConfirmSupplier(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");

		this.prepareMetaData(request);

		return mapping.findForward("confirmsupplierDisplay");
	}
	public ActionForward displayTenderContractcheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");

		this.prepareMetaData(request);

		return mapping.findForward("contractcheckDisplay");
	}
	
	public ActionForward displayDeviceQuotedPrice(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");

		this.prepareMetaData(request);

		return mapping.findForward("deviceAskPriceDisplay");
	}
	public ActionForward displayDeviceList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");

		this.prepareMetaData(request);

		return mapping.findForward("deviceListDisplay");
	}
	public ActionForward displayTenderTeamMember(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");

		this.prepareMetaData(request);

		return mapping.findForward("teamMemberDisplay");
	}
	public ActionForward displayTenderBugetItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		
		if(tp.getTenderBudget() == null)
		{
			ProjectBudget pbs = tenderManageService.modifyCreateBudgetItems();
			pbs.setPbType(ProjectBudget.BUDGET_TYPE_TENDER);
			pbs.setPbProject(tp.getTpId());
			tp.setTenderBudget(pbs);
		}
		
		List<CheckTask> taskList = tenderManageService.getAllStepCheckTask(tp,String.valueOf(CheckTask.TASK_TYPE_TENDER_BUDGET_CHECK));
		projectManageService.retrieveTaskCheckableUserList(taskList);
		request.setAttribute("taskList", taskList);

		this.prepareMetaData(request);

		return mapping.findForward("budgetDisplay");
	}
	public ActionForward displayTenderFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");

		this.prepareMetaData(request);

		return mapping.findForward("tenderFileDisplay");
	}
	
	public ActionForward deleteProjectContract(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String id = request.getParameter("id");
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		ProjectContract deletePc = null;
		for(ProjectContract pc:tp.getTenderContractList())
		{
			if(pc.getPcId().equals(Integer.valueOf(id)))
			{
				deletePc = pc;
				break;
			}
		}
		
		if(deletePc != null)
		{
			deletePc.setPcStatus(CommonService.DELETE_FLAG);
			projectManageService.updateProjectContract(deletePc);
			tp.getTenderContractList().remove(deletePc);
		}
		
		return  mapping.findForward("contractCheck");
	}
	
	public ActionForward saveProjectContract(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String uploadPath = request.getSession().getServletContext().getInitParameter("uploadFilePath");
		if(!uploadPath.endsWith("\\"))
		{
			uploadPath = uploadPath + "\\";
		}
		
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
	
		projectManageService.saveTenderContract(tp,uploadPath);
		
		ProjectContract pc = new ProjectContract();
		pc.setPcStatus(ProjectContract.Contract_Status_INIT);
		pc.setPcProject(tp.getTpId());
		pc.setTenderProject(tp);
		pc.setCustomer(new Customer());
		tp.setTenderContract(pc);		
		
							
		return  mapping.findForward("contractCheck");
	}
	
	public ActionForward createProjectContract(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
	
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
								
		ProjectContract pc = new ProjectContract();
		pc.setPcCategory(ProjectContract.Contract_CATEGORY_TENDER);
		pc.setPcStatus(ProjectContract.Contract_Status_INIT);
		pc.setPcProject(tp.getTpId());
		pc.setTenderProject(tp);
		pc.setCustomer(new Customer());
		tp.setTenderContract(pc);
					
		return  mapping.findForward("contractCheck");
	}
	

	
	public ActionForward refreshProjectContract(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		
				
		return  mapping.findForward("contractCheck");
	}
	public ActionForward editProjectContractCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		
		String id = request.getParameter("id");
		String cid = request.getParameter("cid");
		if(id != null && !id.isEmpty() && cid != null && !cid.isEmpty())
		{			
			tp = projectManageService.getProjectById(Integer.parseInt(id));
			dform.set("tenderInfo", tp);
			for(ProjectContract pc:tp.getTenderContractList())
			{
				if(pc.getPcId().equals(Integer.parseInt(cid)))
				{
					tp.setTenderContract(pc);
					break;
				}
			}
		}
		
		if(tp.getTenderContract() == null)
		{
			ProjectContract pc = new ProjectContract();
			pc.setPcStatus(ProjectContract.Contract_Status_INIT);
			pc.setPcCategory(ProjectContract.Contract_CATEGORY_TENDER);
			pc.setPcProject(tp.getTpId());
			pc.setTenderProject(tp);
			pc.setCustomer(new Customer());
			tp.setTenderContract(pc);
		}
					
		return  mapping.findForward("contractCheck");
	}

	public ActionForward loadTenderProjectAdd(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = tenderManageService.createTenderProject();

		dform.set("tenderInfo", tp);		
		this.prepareMetaData(request);

		return mapping.findForward("input");
	}

	
	public ActionForward editProjectTeamMember(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String userId = (String) request.getSession().getAttribute("account");
		
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		ProjectTeam pt = projectManageService.createProjectTeam();
		pt.setPtCreateUser(userId);
		pt.setPtTenderProject(tp.getTpId());
		
		tp.setCurrentProjectTeam(pt);
		
		this.prepareTeamMetadata(request);
			
		return  mapping.findForward("teamMemberInput");
	}
	
	public ActionForward addTeamMember(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String userId = (String) request.getSession().getAttribute("account");
		
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
								
		ProjectTeam pt = tp.getCurrentProjectTeam();
						
		TenderRole tr = commonService.getTenderRoleById(pt.getPtTenderRole());
		pt.setTenderRole(tr);
		pt.setJobResponsibility(tr.getJobResponsibility());
		pt.setPtJobResponsibility(tr.getTrJobResponsibility());
		pt.setPtTenderProject(tp.getTpId());
		pt.setPtCreateUser(userId);
		pt.setPtTeamType(ProjectTeam.TENDER_TEAM_MEMEBER_TYPE);
		
		tp.getTeamMemberList().add(pt);
		
		tp.setCurrentProjectTeam(projectManageService.createProjectTeam());
				
		this.prepareTeamMetadata(request);
	
		return  mapping.findForward("teamMemberInput");
	}
	
	public ActionForward saveTeamMember(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
												
		projectManageService.saveProjectTeam(tp);
		
		this.prepareTeamMetadata(request);
	
		return  mapping.findForward("teamMemberInput");
	}
	
	
	public ActionForward refreshEditBaseQuotedPrice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
				
		return  mapping.findForward("editBaseQuotedPrice");
	}
	
	public ActionForward refreshDetailDeviceReportPrice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
				
		return  mapping.findForward("detailDeviceReportPrice");
	}
	
	public ActionForward deleteTenderProject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		String id = request.getParameter("id");
		
		TenderProject tp = tenderManageService.getTenderProjectById(Integer.valueOf(id));
		
		if(!user.getId().equals("0") && !user.getCreatePowerDepartmentList().contains(tp.getDepartment()))
		{			
			ActionMessages errors = new ActionMessages();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.nopower"));
			this.saveErrors(request, errors);		
		}
		else
		{
			tenderManageService.deleteTenderProject(tp);
		}				
					
		return  this.searchTenderProject(mapping, form, request, response);
	}
	
	public ActionForward cancelDeletedTenderProject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		String id = request.getParameter("id");
		
		TenderProject tp = tenderManageService.getTenderProjectById(Integer.valueOf(id));
		tp.setTpStatus(ProjectStatus.PROJECT_STATUS_TENDER);
		tenderManageService.updateTenderProject(tp);
			
		return  this.searchTenderProject(mapping, form, request, response);
	}
	
	public ActionForward saveTenderProject(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		String uploadPath = request.getSession().getServletContext().getInitParameter("uploadFilePath")	+ "\\";
				
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
	
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		if (tp.getTpCreateUser() == null || tp.getTpCreateUser().equals("")) {
			tp.setTpCreateUser(user.getId());
		}

		if (tp.getTpId() == null) {
			for (ProjectAttachment pa : tp.getAttachmentList()) {
				pa.setPaCreateUser(user.getId());
				pa.setCreateUser(user);
			}
		}
		
		tenderManageService.saveTenderProject(tp, uploadPath);
		tp.setDepartment(commonService.getDepartmentById(tp.getTpDept()));
		tp.setStatus(commonService.getProjectStatusById(tp.getTpStatus()));
						
		return this.resetSearchTenderProject(mapping, dform, request, response);
	}
	
	public ActionForward addAttachment(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		ProjectAttachment pa = tenderManageService.createProjectAttachment();
		pa.setPaAttachmentCategory(AttachmentCategory.TENDER_ATTACHMENT);
		pa.setCategory(commonService.getAttachmentCategoryById(AttachmentCategory.TENDER_ATTACHMENT));
		tp.setCurrentAttachment(pa);	

		request.setAttribute("attachmentCategoryList", commonService.getAttachmentCategoryList());
		request.setAttribute("type", request.getParameter("type"));
							
		return mapping.findForward("addAttachment");		
	}

	public ActionForward refreshTenderProject(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		String bd = tp.getTpBeginDate();
		String ed = tp.getTpEndDate();
		
		if(bd != null && !bd.isEmpty() && ed != null && !ed.isEmpty())
		{			
			Date beginDate = DateFormat.getDateInstance().parse(bd);
			Date endDate = DateFormat.getDateInstance().parse(ed);
			
			int workDayCount = DateUtil.getWorkDayCount(beginDate, endDate);
			tp.setTpWorkdayCount(workDayCount);			
		}
		this.prepareMetaData(request);

		return mapping.findForward("input");
	}
	
	public ActionForward refreshTenderBase(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		String bd = tp.getTpBeginDate();
		String ed = tp.getTpEndDate();
		
		if(bd != null && !bd.isEmpty() && ed != null && !ed.isEmpty())
		{			
			Date beginDate = DateFormat.getDateInstance().parse(bd);
			Date endDate = DateFormat.getDateInstance().parse(ed);
			
			int workDayCount = DateUtil.getWorkDayCount(beginDate, endDate);
			tp.setTpWorkdayCount(workDayCount);			
		}
		
		this.prepareMetaData(request);
		
		return mapping.findForward("editBaseTenderProject");
	}
	
		
	public ActionForward refreshTenderResult(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		
		return mapping.findForward("editTenderResult");
	}
	
	public ActionForward refreshAttachmentList(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		

		return mapping.findForward("attachmentList");
	}

	public ActionForward confirmAttachment(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		ProjectAttachment pa = tp.getCurrentAttachment();
		pa.setPaCreateUser(user.getId());
		pa.setCreateUser(user);
		tp.getAttachmentList().add(pa);
		tp.setCurrentAttachment(null);
		pa.setPaInputInterface(CommonService.INPUT_INTERFACE_BASE);
		
		return mapping.findForward("refeshBase");
	}
	
	public ActionForward confirmBaseAttachment(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		Account user = (Account) request.getSession().getAttribute("accountPerson");
		
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		ProjectAttachment pa = tp.getCurrentAttachment();
				
		pa.setPaCreateUser(user.getId());
		pa.setCreateUser(user);
		if(pa.getPaId() == null || pa.getPaId() == 0)
		{
			tp.getAttachmentList().add(pa);
		}
		else
		{
			pa.setModified(true);
		}
		
		tp.setCurrentAttachment(null);
		

		String type = request.getParameter("type");
		
		if("attachmentInput".equals(type))
		{			
			return mapping.findForward("refeshAttachment");
		}
		else if("tenderResult".equals(type))
		{
			pa.setPaInputInterface(CommonService.INPUT_INTERFACE_INPUT);
			
			return mapping.findForward("refeshResultreturn");
		}
		else if("contractInput".equals(type))
		{
			pa.setPaInputInterface(CommonService.INPUT_INTERFACE_CONTRACT);
			tp.getTenderContract().getAttachmentList().add(pa);
			
			return mapping.findForward("refreshContract");
		}
		
		pa.setPaInputInterface(CommonService.INPUT_INTERFACE_BASE);
		return mapping.findForward("refreshTenderBase");
	}
	
	public ActionForward deleteProjectAttachmentByIndex(ActionMapping mapping, ActionForm form,	HttpServletRequest request, HttpServletResponse response)
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		String index = request.getParameter("index");
				
		tp.getAttachmentList().remove(Integer.parseInt(index));
						
		return mapping.findForward("attachmentList");
	}

	public ActionForward deleteAttachment(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		String index = request.getParameter("i");

		ProjectAttachment pa = tp.getAttachmentList().get(
				Integer.parseInt(index));
		if (pa.getPaId() == null) {
			tp.getAttachmentList().remove(pa);
		} else {
			tenderManageService.deleteProjectAttachment(pa);
		}
		tp.getAttachmentList().remove(pa);

		String type = request.getParameter("type");
		
		if(type == null || "".equals(type)){
			return mapping.findForward("input");
		} else {
			return editBaseTenderProject(mapping, form, request, response);
		}
	}

	public ActionForward searchCustomer(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String page = request.getParameter("pager.offset");
		Customer searchInfo = (Customer) dform.get("customerInfo");

		Object[] args = { searchInfo, page };

		ArrayList<Customer> gList = commonService.searchReferCompany(args);
		Integer count = commonService.searchReferCompanyCount(args);
		if (count > 0) {
			request.setAttribute("customerList", gList);
			request.setAttribute("customerCount", count);
		}
		
		request.setAttribute("typeList", commonService.getCustomerTypeList());
		request.setAttribute("tradeTypeList", commonService.getTradeTypeList());

		return mapping.findForward("searchContacter");
	}
	
	public ActionForward searchSupplier(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String page = request.getParameter("pager.offset");
		Customer searchInfo = (Customer) dform.get("customerInfo");

		Object[] args = { searchInfo, page };

		ArrayList<Customer> gList = commonService.searchSupplier(args);
		Integer count = commonService.searchSupplierCount(args);
		if (count > 0) {
			request.setAttribute("customerList", gList);
			request.setAttribute("customerCount", count);
		}
		request.setAttribute("tradeTypeList", commonService.getTradeTypeList());

		return mapping.findForward("searchSupplier");
	}
	
	public ActionForward searchCompetitor(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String page = request.getParameter("pager.offset");
		Customer searchInfo = (Customer) dform.get("customerInfo");

		Object[] args = { searchInfo, page };

		ArrayList<Customer> gList = commonService.searchCompetitor(args);
		Integer count = commonService.searchCompetitorCount(args);
		if (count > 0) {
			request.setAttribute("customerList", gList);
			request.setAttribute("customerCount", count);
		}

		return mapping.findForward("searchCompetitor");
	}
	
	public ActionForward resetSearchCustomer(ActionMapping mapping,	ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		Customer searchInfo = new Customer();
		dform.set("customerInfo",searchInfo);
		
		Object[] args = { searchInfo, null };

		ArrayList<Customer> gList = commonService.searchReferCompany(args);
		Integer count = commonService.searchReferCompanyCount(args);
		if (count > 0) {
			request.setAttribute("customerList", gList);
			request.setAttribute("customerCount", count);
		}
		
		request.setAttribute("typeList", commonService.getCustomerTypeList());
		request.setAttribute("tradeTypeList", commonService.getTradeTypeList());

		return mapping.findForward("searchContacter");
	}
	
	public ActionForward resetSearchSupplier(ActionMapping mapping,	ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String page = request.getParameter("pager.offset");
		Customer searchInfo = new Customer();
		
		dform.set("customerInfo",searchInfo);

		Object[] args = { searchInfo, page };

		ArrayList<Customer> gList = commonService.searchSupplier(args);
		Integer count = commonService.searchSupplierCount(args);
		if (count > 0) {
			request.setAttribute("customerList", gList);
			request.setAttribute("customerCount", count);
		}
		request.setAttribute("tradeTypeList", commonService.getTradeTypeList());

		return mapping.findForward("searchSupplier");
	}
	
	public ActionForward resetSearchCompetitor(ActionMapping mapping,	ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String page = request.getParameter("pager.offset");
		Customer searchInfo = (Customer) dform.get("customerInfo");
		
		searchInfo.setCName(null);

		Object[] args = { searchInfo, page };

		ArrayList<Customer> gList = commonService.searchCompetitor(args);
		Integer count = commonService.searchCompetitorCount(args);
		if (count > 0) {
			request.setAttribute("customerList", gList);
			request.setAttribute("customerCount", count);
		}

		return mapping.findForward("searchCompetitor");
	}

	public ActionForward confirmCustomer(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		String gId = request.getParameter("id");
		Customer c = commonService.getCustomerById(Integer.valueOf(gId));
		
		String type = request.getParameter("type");
		
		if("tenderInput".equals(type))
		{
			tp.setTpCustomer(c.getCId());
			tp.setCustomer(c);
			
			return mapping.findForward("refeshBase");
		}
		else if("contractInput".equals(type))
		{
			tp.getTenderContract().setPcCustomer(c.getCId());
			tp.getTenderContract().setCustomer(c);
			
			return mapping.findForward("refreshContract");
		}
		else
		{
			tp.setTpCustomer(c.getCId());
			tp.setCustomer(c);
		}
		
		return mapping.findForward("uploadReturn");
	}
	
	public ActionForward createCustomer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{				
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		Customer c = customerService.createCustomer();	
		c.setSaleman(new Account());
		dform.set("customerInfo",c);
		
		this.prepareMetaData(request);
						
		return  mapping.findForward("customerInput");		
	}
	
	
	public ActionForward saveCustomer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		Customer c = (Customer) dform.get("customerInfo");
		
		String userId = (String) request.getSession().getAttribute("account");
		if(c.getCId() == null || c.getCId() == 0)		
		{
			c.setCCreateUser(userId);
		}
		
		try{
			customerService.saveCustomer(c);	
			
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
			
			return mapping.findForward("customerInput");
		}
		
		//---------------
		
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		tp.setTpCustomer(c.getCId());
		tp.setCustomer(c);
				
		return mapping.findForward("refeshBase");
	}
	
	private void prepareMetaData(HttpServletRequest request)
	{
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		
		request.setAttribute("typeList", commonService.getCustomerTypeList());
		request.setAttribute("tradeTypeList", commonService.getTradeTypeList());
		request.setAttribute("natureList", commonService.getCustomerNatureList());
		request.setAttribute("departmentList", commonService.getDepartmentList());
		
		request.setAttribute("statusList", commonService.getProjectStatusList());
		request.setAttribute("deptList", user.getCreatePowerDepartmentList());
	}
	
	
	public ActionForward confirmSupplier(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		String gId = request.getParameter("id");
		Customer c = commonService.getCustomerById(Integer.valueOf(gId));
		
		DeviceListDetail detailDevice = (DeviceListDetail) dform.get("detailDeviceInfo");
		
		String type = request.getParameter("opType");
		
		if("first".equals(type))
		{
			detailDevice.setDldFirstSelectedSupplierId(c.getCId());
			detailDevice.setFirstSelectedSupplier(c);
		}
		else if("second".equals(type))
		{
			detailDevice.setDldSecondSelectedSupplierId(c.getCId());
			detailDevice.setSecondSelectedSupplier(c);
		}
		else if("third".equals(type))
		{
			detailDevice.setDldThirdSelectedSupplierId(c.getCId());
			detailDevice.setThirdSelectedSupplier(c);
		}

		return mapping.findForward("refreshDetailDeviceReportPrice");
	}
	
	public ActionForward confirmCompetitor(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		String gId = request.getParameter("id");
		Customer c = commonService.getCustomerById(Integer.valueOf(gId));

		String index = request.getParameter("index");
		
		if("result".equals(index))
		{
			dform.set("competitorInfo", c);
			
			return mapping.findForward("refeshCompetitorreturn");
		}
		
	
		return mapping.findForward("refeshResultreturn");
	}

	
	public ActionForward searchAccount(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String page = request.getParameter("pager.offset");
		Account searchInfo = (Account) dform.get("accountInfo");
		
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		String opType = request.getParameter("type");	
		
		Object[] args = { searchInfo, page };

		if("quoteprice".equals(opType))
		{
			ArrayList<Account> gList = commonService.searchTeamMemberAccount(args,tp.getTpId());
			Integer count = commonService.searchTeamMemberAccountCount(args,tp.getTpId());
			if(count > 0)		
			{
				request.setAttribute("accountList",gList);			
				request.setAttribute("accountCount",count);
			}
		}
		else
		{
			ArrayList<Account> gList = commonService.searchAccount(args);
			Integer count = commonService.searchAccountCount(args);
			if (count > 0) {
				request.setAttribute("accountList", gList);
				request.setAttribute("accountCount", count);
			}
		}
		
		request.setAttribute("departmentList", commonService.getDepartmentList());
		request.setAttribute("postList", commonService.getPostList());
		
		return mapping.findForward("searchAccount");
	}

	public ActionForward resetSearchAccount(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String page = request.getParameter("pager.offset");
		Account searchInfo = new Account();
		
		dform.set("accountInfo",searchInfo);
		
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		String opType = request.getParameter("type");	
		
		Object[] args = { searchInfo, page };

		if("quoteprice".equals(opType))
		{
			ArrayList<Account> gList = commonService.searchTeamMemberAccount(args,tp.getTpId());
			Integer count = commonService.searchTeamMemberAccountCount(args,tp.getTpId());
			if(count > 0)		
			{
				request.setAttribute("accountList",gList);			
				request.setAttribute("accountCount",count);
			}
		}
		else
		{
			ArrayList<Account> gList = commonService.searchAccount(args);
			Integer count = commonService.searchAccountCount(args);
			if (count > 0) {
				request.setAttribute("accountList", gList);
				request.setAttribute("accountCount", count);
			}
		}
		
		request.setAttribute("departmentList", commonService.getDepartmentList());

		return mapping.findForward("searchAccount");
	}

	public ActionForward confirmAccount(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		
		String gId = request.getParameter("id");
		Account a = commonService.getAccountById(gId);
		
		String type = request.getParameter("type");
		if(type.startsWith("tenderInput"))
		{		
			if("tenderInputManager".equals(type))
			{
				tp.setTpManager(a.getId());
				tp.setManager(a);
			}
			else if("tenderInputMarketManager".equals(type))
			{
				tp.setTpMarketManager(a.getId());
				tp.setMarketManager(a);
			}
			
			return mapping.findForward("refeshBase");
		}
		else if(type.startsWith("tenderEdit"))
		{			
			if("tenderEditManager".equals(type))
			{
				tp.setTpManager(a.getId());
				tp.setManager(a);
			}
			else if("tenderEditMarketManager".equals(type))
			{
				tp.setTpMarketManager(a.getId());
				tp.setMarketManager(a);
			}
						
			return mapping.findForward("uploadReturn");
		}
		else if("quoteprice".equals(type))
		{
			DeviceListDetail detailDevice = (DeviceListDetail) dform.get("detailDeviceInfo");
			detailDevice.setDldResponsiblePerson(a.getId());
			detailDevice.setResponsiblePerson(a);	
			
			return mapping.findForward("refreshEditBaseQuotedPrice");
		}
		else if("teamMember".equals(type))
		{
			tp.getCurrentProjectTeam().setAccount(a);
			tp.getCurrentProjectTeam().setPtAccount(a.getId());
			
			return mapping.findForward("refreshTeam");
		}
		
		return null;
	}
	
	private void prepareTeamMetadata(HttpServletRequest request)
	{
		request.setAttribute("tenderRoleList", commonService.getTenderRoleList());
		request.setAttribute("jobResponsibilityList", commonService.getJobResponsibilityList());
	}
		
	
	public ActionForward refreshProjectTeamEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		this.prepareTeamMetadata(request);
		
		return  mapping.findForward("teamMemberInput");
	}
	
	public ActionForward searchTenderProject(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		String page = request.getParameter("pager.offset");
		
		Object[] args = {tp, page,user };
		
		List<TenderProject> projectList = tenderManageService.searchTenderProject(args);
		int count = tenderManageService.searchTenderProjectCount(args);
		if(count > 0){
			request.setAttribute("projectList", projectList);
			request.setAttribute("projectCount", count);
		}
		
		this.prepareMetaData(request);
		
		return mapping.findForward("searchTender");
	}
	
	public ActionForward searchTenderResult(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
	
		Customer searchInfo = (Customer) dform.get("competitorInfo");
				
		return mapping.findForward("editCompetitor");
	}
	
	public ActionForward resetSearchTenderProject(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		TenderProject tp = tenderManageService.createTenderProject();
		tp.setTpStatus(null);
		
		dform.set("tenderInfo", tp);
		
		return this.searchTenderProject(mapping, dform, request, response);
	}
	
	public ActionForward deleteProjectTeamMember(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		
		String id = request.getParameter("id");
			
		for(ProjectTeam t:tp.getTeamMemberList())
		{
			if(t.getPtId().equals(Integer.valueOf(id)))
			{
				t.setPtFlag(CommonService.DELETE_FLAG);
				t.setModified(true);
								
				break;
			}
		}
						
		this.prepareTeamMetadata(request);
			
		return  mapping.findForward("teamMemberInput");
	}
	
	public ActionForward deleteProjectTeamMemberByIndex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		
		String index = request.getParameter("index");
			
		tp.getTeamMemberList().remove(Integer.parseInt(index));
						
		this.prepareTeamMetadata(request);
			
		return  mapping.findForward("teamMemberInput");
	}
	
	public ActionForward saveProjectDocument(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
	{
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String serverPath = request.getSession().getServletContext().getRealPath("/");
		String uploadPath = request.getSession().getServletContext().getInitParameter("uploadFilePath");
		if(!uploadPath.endsWith("\\"))
		{
			uploadPath = uploadPath + "\\";
		}
		
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		
		tenderManageService.saveProjectDocument(tp, serverPath, uploadPath);
		
		return  this.searchAttachmentList(mapping, dform, request, response);
	}
	
/* tenderProjectManage.do start this */	
	public ActionForward editBaseTenderProject(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		String id = request.getParameter("id");
				
		TenderProject tp = tenderManageService.getTenderProjectById(Integer.valueOf(id));
		
		if(tp.getMarketManager() == null)
		{
			tp.setMarketManager(new Account());
		}
		
		if(!user.getCreatePowerDepartmentList().contains(tp.getDepartment()))
		{			
			ActionMessages errors = new ActionMessages();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.nopower"));
			this.saveErrors(request, errors);		
			
			return this.resetSearchTenderProject(mapping, dform, request, response);
		}
					
		dform.set("tenderInfo", tp);
		
		String userId = (String) request.getSession().getAttribute("account");
		if(!userId.equals("0"))
		{
			for(ProjectTeam pt:tp.getTeamMemberList())
			{
				if(pt.getPtAccount().equals(userId))
				{
					dform.set("teamMemberInfo", pt);
				}
			}			
		}
		
		this.prepareMetaData(request);
		
		return mapping.findForward("editBaseTenderProject");
	}
	
	public ActionForward transferTenderProject(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		String id = request.getParameter("id");
				
		TenderProject tp = tenderManageService.getTenderProjectById(Integer.valueOf(id));
		
		if(!user.getCreatePowerDepartmentList().contains(tp.getDepartment()))
		{			
			ActionMessages errors = new ActionMessages();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.nopower"));
			this.saveErrors(request, errors);		
			
			return mapping.findForward("msg");
		}
					
		dform.set("tenderInfo", tp);
		
		Account searchInfo = new Account();
		searchInfo.getPerson().setPost(Post.POST_PROJECT_SECRETARY);
		Object[] args = { searchInfo, null };		
		
		ArrayList<Account> gList = commonService.searchAccount(args);
		Integer count = commonService.searchAccountCount(args);
		if (count > 0) {
			request.setAttribute("accountList", gList);
			request.setAttribute("accountCount", count);
		}
		
		return mapping.findForward("transferObjectList");
	}
	
	public ActionForward confirmTransferTenderProject(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		
		String gId = request.getParameter("id");		
		Account a = commonService.getAccountById(gId);
		tp.setTpCreateUser(gId);
		for(UsrPost up:a.getUsrPostList())
		{
			if(up.getPost().equals(Post.POST_PROJECT_SECRETARY))
			{
				tp.setTpDept(up.getDept());
				break;
			}
		}
		
		tp.setTpStatus(ProjectStatus.PROJECT_STATUS_ACT);
		tenderManageService.updateTenderProject(tp);
		
		return mapping.findForward("refreshTenderProjectList");
	}
	
	public ActionForward editTenderBase(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		
		if(tp.getMarketManager() == null)
		{
			tp.setMarketManager(new Account());
		}
		this.prepareMetaData(request);
		
		return mapping.findForward("editBaseTenderProject");
	}
	
	public ActionForward saveBaseTenderProject(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;

		String uploadPath = request.getSession().getServletContext().getInitParameter("uploadFilePath")	+ "\\";
				
		TenderProject tp = (TenderProject) dform.get("tenderInfo");

		Account user = (Account) request.getSession().getAttribute("accountPerson");
		if (tp.getTpCreateUser() == null || tp.getTpCreateUser().equals("")) {
			tp.setTpCreateUser(user.getId());
		}

		if (tp.getTpId() == null) {
			for (ProjectAttachment pa : tp.getAttachmentList()) {
				pa.setPaCreateUser(user.getId());
				pa.setCreateUser(user);
			}
		}
		
		tenderManageService.saveTenderProject(tp,uploadPath);
		if(tp.getTpManager() != null && !tp.getTpManager().isEmpty())
		{
			projectManageService.saveProjectManagerAsTeam(tp, commonService);
		}
		
		if(tp.getOldManager() != null)
		{
			projectManageService.deleteTenderMember(tp, tp.getOldManager());
			tp.setOldManager(null);
		}
		
		dform.set("tenderInfo", tp);

		this.prepareMetaData(request);

		return mapping.findForward("editBaseTenderProject");
	}
	
		
	public ActionForward searchAttachmentList(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		return mapping.findForward("attachmentList");
	}
	
	public ActionForward resetSearchAttachment(ActionMapping mapping, ActionForm form,HttpServletRequest request,	HttpServletResponse response){
		ProjectAttachment rtn = new ProjectAttachment();
		rtn.setPaFlag(CommonService.NORMAL_FLAG);
		
		String page = request.getParameter("pager.offset");
		DynaValidatorForm dform = (DynaValidatorForm) form;
		TenderProject tp = (TenderProject)dform.get("tenderInfo");
		if(tp != null){
			rtn.setPaTenderProject(tp.getTpId());
		}
		
		Object[] args = {rtn, page};

		List<ProjectAttachment> gList = tenderManageService.searchAttachment(args);
		int count = tenderManageService.searchAttachmentCount(args);
		if (count > 0) {
			request.setAttribute("attachmentList", gList);
			request.setAttribute("attachmentCount", count);
		}

		request.setAttribute("attachmentCategoryList", commonService.getAttachmentCategoryList());
				
		return mapping.findForward("attachmentList");		
	}
	
	public ActionForward downloadAttachment(ActionMapping mapping, ActionForm form,HttpServletRequest request,	HttpServletResponse response){
		String id = request.getParameter("id");
		ProjectAttachment pa = tenderManageService.getProjectAttachmentById(Integer.parseInt(id));
						
		String fileName = pa.getPaUrl();
				
		try {
			response.setContentType(FileUtil.getContentType(fileName));
//			response.setHeader("Content-Disposition", "attachment;filename=" + fileName.substring(fileName.lastIndexOf("\\")+1));			
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(pa.getPaName().getBytes("GB2312"), "ISO_8859_1") + fileName.substring(fileName.lastIndexOf(".")));
			
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
	
	public ActionForward modifyAttachment(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String id = request.getParameter("id");
				
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		for(ProjectAttachment pa:tp.getAttachmentList())
		{
			if(pa.getPaId().equals(Integer.parseInt(id)))
			{
				tp.setCurrentAttachment(pa);
			}
		}
				
		request.setAttribute("attachmentCategoryList", commonService.getAttachmentCategoryList());
		request.setAttribute("type", request.getParameter("type"));
								
		return  mapping.findForward("editAttachment");
	}
	
	public ActionForward deleteDocument(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");		
		
		ProjectAttachment c = tenderManageService.getProjectAttachmentById(Integer.valueOf(id));
		tenderManageService.deleteProjectAttachment(c);
								
		return mapping.findForward("searchAttachmentList");
	}
	
	public ActionForward editCompetitor(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		dform.set("competitorInfo", new Customer());
		
		return mapping.findForward("editCompetitor");
	}
	
	public ActionForward refreshEditCompetitor(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		

		//立即检索
		Customer searchInfo = (Customer) dform.get("competitorInfo");
		
		
		
		return mapping.findForward("editCompetitor");
	}
	
	public ActionForward editTenderResult(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		

		return mapping.findForward("editTenderResult");
	}
	
	public ActionForward saveTenderResult(ActionMapping mapping, ActionForm form,HttpServletRequest request,HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		
	

		return editTenderResult(mapping, form, request, response);
	}
/* tenderProjectManage.do end this */
		
	public ActionForward searchBudgetProjectByStatus(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
	
		String page = request.getParameter("pager.offset");
		String statusId = request.getParameter("id");
		
		TenderProject tp = new TenderProject();
		if(statusId != null && !"".equals(statusId)){
			
		}
		Object[] args = {tp, page };
		
		List<TenderProject> projectList = tenderManageService.searchTenderProject(args);
		int count = tenderManageService.searchTenderProjectCount(args);
		if(count > 0){
			request.setAttribute("projectList", projectList);
			request.setAttribute("projectCount", count);
		}
		
		request.setAttribute("statusId", statusId);
		
		return mapping.findForward("searchBudgetProject");
	}
	
	public ActionForward editTenderBugetItem(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		String id = request.getParameter("id");
		
		if (id == null || "".equals(id)) {
			TenderProject tp = (TenderProject) dform.get("tenderInfo");
			id = (tp.getTpId() == null ? null : tp.getTpId().toString());

			if (id == null || "".equals(id)) {
				ActionMessages errors = new ActionMessages();
				errors.add("UnknowError", new ActionMessage("errors.UnKnowError"));
				saveErrors(request, errors);
				saveToken(request);

				return mapping.findForward("faile");
			}
		}
		TenderProject tp = tenderManageService.getTenderProjectById(Integer.valueOf(id));
		
		//---for old data
		if(tp.getTenderBudget() == null)
		{
			ProjectBudget pbs = tenderManageService.modifyCreateBudgetItems();
			pbs.setPbType(ProjectBudget.BUDGET_TYPE_TENDER);
			pbs.setPbProject(tp.getTpId());
			tp.setTenderBudget(pbs);
			tenderManageService.saveProjectBudget(pbs);
		}
				
		dform.set("tenderInfo", tp);
		
		List<CheckTask> taskList = tenderManageService.getAllStepCheckTask(tp,String.valueOf(CheckTask.TASK_TYPE_TENDER_BUDGET_CHECK));
		projectManageService.retrieveTaskCheckableUserList(taskList);
		request.setAttribute("taskList", taskList);
		
		return mapping.findForward("editTenderBugetItem");
	}
	
	
	public ActionForward editDetailTenderBugetItem(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
	
		String id = request.getParameter("id");
		
		BudgetItem item = tenderManageService.getBudgetItemById(Integer.valueOf(id));
		
		if(item == null){
			ActionMessages errors = new ActionMessages();
			errors.add("UnknowError", new ActionMessage("errors.UnKnowError"));
			saveErrors(request, errors);
			saveToken(request);

			return mapping.findForward("faile");
		}
		dform.set("budgetItemInfo", item);
		
		return mapping.findForward("editDetailBudgetItem");
	}
	
	public ActionForward saveTenderBudget(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		TenderProject tenderProject = (TenderProject)dform.get("tenderInfo");
		
		tenderManageService.saveProjectBudget(tenderProject.getTenderBudget());
				
		return this.editTenderBugetItem(mapping, dform, request, response);
	}
	
	public ActionForward saveBudgetItem(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		BudgetItem item = (BudgetItem)dform.get("budgetItemInfo");
		
		tenderManageService.saveBudgetItem(item);
				
		return mapping.findForward("refreshBudgetItem");
	}
	
		
	public ActionForward editDeviceList(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String id = request.getParameter("id");
		if (id == null || "".equals(id)) {
			TenderProject tp = (TenderProject) dform.get("tenderInfo");
			id = (tp.getTpId() == null ? null : tp.getTpId().toString());

			if (id == null || "".equals(id)) {
				ActionMessages errors = new ActionMessages();
				errors.add("UnknowError", new ActionMessage("errors.UnKnowError"));
				saveErrors(request, errors);
				saveToken(request);

				return mapping.findForward("faile");
			}
		}
		TenderProject tp = tenderManageService.getTenderProjectById(Integer.valueOf(id));
		if(tp != null){
			List<DeviceList1st> deviceList = tp.getDeviceList();
			if(deviceList == null){
				request.setAttribute("deviceList", new ArrayList<DeviceList1st>());
			} else {
				filterDeviceListByTime(deviceList);
				sortDeviceListByTime(deviceList);
				request.setAttribute("deviceList", deviceList);
			}
			calcDeviceCount(deviceList, request);
		}
		
		return mapping.findForward("editDeviceList");
	}
	
	private void filterDeviceListByTime(List<DeviceList1st> deviceList){
		if(deviceList.isEmpty()){
			return;
		}
		
		//temporarily undo 
	}
	
	private void sortDeviceListByTime(List<DeviceList1st> deviceList) {
		if (deviceList.isEmpty()) {
			return;
		}
		Comparator<DeviceList1st> comp = new Comparator<DeviceList1st>() {
			public int compare(DeviceList1st o1, DeviceList1st o2) {
				if (o1.getDlfCreateTime() == null || o2.getDlfCreateTime() == null) {
					return 0;
				}

				if (o1.getDlfCreateTime().getTime()	- o2.getDlfCreateTime().getTime() > 0) {
					return 1;
				} else if (o1.getDlfCreateTime().getTime() - o2.getDlfCreateTime().getTime() < 0) {
					return -1;
				}
				return 0;
			}
		};
		Collections.sort(deviceList, comp);
		for (DeviceList1st deviceList1st : deviceList) {
			List<DeviceList2nd> device2ndList = deviceList1st.getDevicList2ndList();
			if (device2ndList == null || device2ndList.isEmpty()) {
				continue;
			}
			Comparator<DeviceList2nd> comp2nd = new Comparator<DeviceList2nd>() {
				public int compare(DeviceList2nd o1, DeviceList2nd o2) {
					if (o1.getDlsCreateTime() == null || o2.getDlsCreateTime() == null) {
						return 0;
					}

					if (o1.getDlsCreateTime().getTime() - o2.getDlsCreateTime().getTime() > 0) {
						return 1;
					} else if (o1.getDlsCreateTime().getTime() - o2.getDlsCreateTime().getTime() < 0) {
						return -1;
					}
					return 0;
				}
			};
			Collections.sort(device2ndList, comp2nd);
			for(DeviceList2nd deviceList2nd : device2ndList){
				List<DeviceListDetail> deviceDetailList = deviceList2nd.getDeviceListDetailList();
				if(deviceDetailList == null || deviceDetailList.isEmpty()){
					continue;
				}
				Comparator<DeviceListDetail> compDetail = new Comparator<DeviceListDetail>() {
					public int compare(DeviceListDetail o1, DeviceListDetail o2) {
						if (o1.getDldCreateTime() == null || o2.getDldCreateTime() == null) {
							return 0;
						}

						if (o1.getDldCreateTime().getTime() - o2.getDldCreateTime().getTime() > 0) {
							return 1;
						} else if (o1.getDldCreateTime().getTime() - o2.getDldCreateTime().getTime() < 0) {
							return -1;
						}
						return 0;
					}
				};
				Collections.sort(deviceDetailList, compDetail);
			}
		}
	}
	
	private void calcDeviceCount(List<DeviceList1st> deviceList, HttpServletRequest request){
		if(deviceList == null || deviceList.isEmpty()){
			request.setAttribute("firstdeviceCount", 0);
			request.setAttribute("secondDeviceCount", 0);
			return;
		}
		
		int count = 0;
		for(DeviceList1st firstDevice: deviceList){
			count += firstDevice.getDeviceList2ndSet() == null ? 0 : firstDevice.getDeviceList2ndSet().size();
			count += firstDevice.getDeviceListDetailSet() == null ? 0 : firstDevice.getDeviceListDetailSet().size();
		}
		
		request.setAttribute("firstdeviceCount", deviceList.size());
		request.setAttribute("secondDeviceCount", count);
	}
	
	public ActionForward editDeviceQuotedPrice(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String id = request.getParameter("id");
		
		if (id == null || "".equals(id)) {
			TenderProject tp = (TenderProject) dform.get("tenderInfo");
			id = (tp.getTpId() == null ? null : tp.getTpId().toString());

			if (id == null || "".equals(id)) {
				ActionMessages errors = new ActionMessages();
				errors.add("UnknowError", new ActionMessage("errors.UnKnowError"));
				saveErrors(request, errors);
				saveToken(request);

				return mapping.findForward("faile");
			}
		}
		TenderProject tp = tenderManageService.getTenderProjectById(Integer.valueOf(id));
		if(tp != null){
			List<DeviceList1st> deviceList = tp.getDeviceList();
			if(deviceList == null){
				request.setAttribute("deviceList", new ArrayList<DeviceList1st>());
			} else {
				filterDeviceListByTime(deviceList);
				sortDeviceListByTime(deviceList);
				request.setAttribute("deviceList", deviceList);
			}
			calcDeviceCount(deviceList, request);
		}
		
		return mapping.findForward("deviceQuotedPrice");
	}
	
	public ActionForward editConfirmSupplier(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String id = request.getParameter("id");
		
		if (id == null || "".equals(id)) {
			TenderProject tp = (TenderProject) dform.get("tenderInfo");
			id = (tp.getTpId() == null ? null : tp.getTpId().toString());

			if (id == null || "".equals(id)) {
				ActionMessages errors = new ActionMessages();
				errors.add("UnknowError", new ActionMessage("errors.UnKnowError"));
				saveErrors(request, errors);
				saveToken(request);

				return mapping.findForward("faile");
			}
		}
		TenderProject tp = tenderManageService.getTenderProjectById(Integer.valueOf(id));
		if(tp != null){
			List<DeviceList1st> deviceList = tp.getDeviceList();
			if(deviceList == null){
				request.setAttribute("deviceList", new ArrayList<DeviceList1st>());
			} else {
				filterDeviceListByTime(deviceList);
				sortDeviceListByTime(deviceList);
				request.setAttribute("deviceList", deviceList);
			}
			calcDeviceCount(deviceList, request);
		}
		
		return mapping.findForward("confirmsupplier");
	}
	
	public ActionForward addDeviceListFirst(ActionMapping mapping, ActionForm form,	HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		DeviceList1st firstDevice = new DeviceList1st();
		firstDevice.setDlfStatus(CommonService.NORMAL_FLAG);
		
		dform.set("firstDeviceInfo", firstDevice);
		
		return mapping.findForward("addFirstDevice");
	}
	
	public ActionForward editDeviceListFirst(ActionMapping mapping, ActionForm form,	HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String id = request.getParameter("id");
		
		DeviceList1st firstDevice = tenderManageService.getDeviceList1stById(Integer.parseInt(id));
				
		dform.set("firstDeviceInfo", firstDevice);
		tenderManageService.saveDeviceList1st(firstDevice);
		
		return mapping.findForward("addFirstDevice");
	}
	
	public ActionForward deleteDeviceListFirst(ActionMapping mapping, ActionForm form,	HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String id = request.getParameter("id");
		
		DeviceList1st firstDevice = tenderManageService.getDeviceList1stById(Integer.parseInt(id));
		firstDevice.setDlfStatus(CommonService.DELETE_FLAG);
		tenderManageService.updateDeviceList1st(firstDevice);
				
		TenderProject tp = tenderManageService.getTenderProjectById(firstDevice.getDlfTenderProjectId());
		if(tp != null){
			List<DeviceList1st> deviceList = tp.getDeviceList();
			if(deviceList == null){
				request.setAttribute("deviceList", new ArrayList<DeviceList1st>());
			} else {
				filterDeviceListByTime(deviceList);
				sortDeviceListByTime(deviceList);
				request.setAttribute("deviceList", deviceList);
			}
			calcDeviceCount(deviceList, request);
		}
		
		return mapping.findForward("editDeviceList");
	}
	
	public ActionForward confirmFirstDevice(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		DeviceList1st firstDevice = (DeviceList1st)dform.get("firstDeviceInfo");
		
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		
		String userId = (String) request.getSession().getAttribute("account");
		
		if(firstDevice.getDlfId() != null){
			firstDevice.setDlfUpdateUser(userId);
			tenderManageService.updateDeviceList1st(firstDevice);
		} else {
			firstDevice.setDlfTenderProjectId(tp.getTpId());
			firstDevice.setDlfCreateUser(userId);
			tenderManageService.saveDeviceList1st(firstDevice);
		}
		
		return mapping.findForward("refreshDeviceList");
	}
	
	public ActionForward addSecondDevice(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String id = request.getParameter("id");
		
		DeviceList2nd secondDevice = new DeviceList2nd();
		if(id != null && !"".equals(id)){
			secondDevice.setDlsFirstEquipmentId(Integer.valueOf(id));
		} else {
			ActionMessages errors = new ActionMessages();
			errors.add("UnknowError", new ActionMessage("errors.UnKnowError"));
			saveErrors(request, errors);
			saveToken(request);

			return mapping.findForward("faile");
		}
		
		secondDevice.setDlsStatus(CommonService.NORMAL_FLAG);
		
		dform.set("secondDeviceInfo", secondDevice);
		
		return mapping.findForward("addSecondDevice");
	}
	
	public ActionForward editSecondDevice(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String id = request.getParameter("id");
		
		DeviceList2nd secondDevice = tenderManageService.getDeviceList2ndById(Integer.parseInt(id));
		
		dform.set("secondDeviceInfo", secondDevice);
		
		return mapping.findForward("addSecondDevice");
	}
	
	public ActionForward deleteSecondDevice(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String id = request.getParameter("id");
		
		DeviceList2nd secondDevice = tenderManageService.getDeviceList2ndById(Integer.parseInt(id));
		secondDevice.setDlsStatus(CommonService.DELETE_FLAG);
		tenderManageService.updateDeviceList2nd(secondDevice);
		
		
		DeviceList1st firstDevice = tenderManageService.getDeviceList1stById(secondDevice.getDlsFirstEquipmentId());
		firstDevice.setDlfStatus(CommonService.DELETE_FLAG);				
		TenderProject tp = tenderManageService.getTenderProjectById(firstDevice.getDlfTenderProjectId());
		if(tp != null){
			List<DeviceList1st> deviceList = tp.getDeviceList();
			if(deviceList == null){
				request.setAttribute("deviceList", new ArrayList<DeviceList1st>());
			} else {
				filterDeviceListByTime(deviceList);
				sortDeviceListByTime(deviceList);
				request.setAttribute("deviceList", deviceList);
			}
			calcDeviceCount(deviceList, request);
		}
		
		return mapping.findForward("editDeviceList");
	}
	
	public ActionForward confirmSecondDevice(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		DeviceList2nd secondDevice = (DeviceList2nd)dform.get("secondDeviceInfo");
		
		String userId = (String) request.getSession().getAttribute("account");
		
		if(secondDevice.getDlsId() != null){
			secondDevice.setDlsCreateUser(userId);
			tenderManageService.updateDeviceList2nd(secondDevice);
		} else {
			secondDevice.setDlsCreateUser(userId);
			tenderManageService.saveDeviceList2nd(secondDevice);
		}
		
		return mapping.findForward("refreshDeviceList");
	}
	
	public ActionForward addDetailDevice(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String id = request.getParameter("id");
		String onType = request.getParameter("onType");
		
		DeviceListDetail detailDevice = new DeviceListDetail();
		if("first".equals(onType)){
			detailDevice.setDldFirstEquipmentId(Integer.valueOf(id));
		} else if("second".equals(onType)){
			detailDevice.setDldSecondEquipmentId(Integer.valueOf(id));
		} else {
			ActionMessages errors = new ActionMessages();
			errors.add("UnknowError", new ActionMessage("errors.UnKnowError"));
			saveErrors(request, errors);
			saveToken(request);

			return mapping.findForward("faile");
		}
		
		detailDevice.setDldStatus(CommonService.NORMAL_FLAG);
		
		dform.set("detailDeviceInfo", detailDevice);
		request.setAttribute("unitList", commonService.getDeviceUnitList());
		
		return mapping.findForward("addDetailDevice");
	}
	
	public ActionForward editDetailDevice(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String id = request.getParameter("id");
		
		if(id == null || "".equals(id)){
			ActionMessages errors = new ActionMessages();
			errors.add("UnknowError", new ActionMessage("errors.UnKnowError"));
			saveErrors(request, errors);
			saveToken(request);

			return mapping.findForward("faile");
		}
		
		DeviceListDetail detailDevice = tenderManageService.findDeviceListDetailById(Integer.valueOf(id));
		
		if(detailDevice == null){
			ActionMessages errors = new ActionMessages();
			errors.add("UnknowError", new ActionMessage("errors.UnKnowError"));
			saveErrors(request, errors);
			saveToken(request);

			return mapping.findForward("faile");
		}
		String statusId = "";
		if(detailDevice.getFirstSelected() == 1){
			statusId = "1";
		} else if(detailDevice.getSecondSelected() == 1){
			statusId = "2";
		} else if(detailDevice.getThirdSelected() == 1){
			statusId = "3";
		}
		request.setAttribute("statusId", statusId);
		dform.set("detailDeviceInfo", detailDevice);
		request.setAttribute("unitList", commonService.getDeviceUnitList());
		
		return mapping.findForward("addDetailDevice");
	}
	
	public ActionForward deleteDetailDevice(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String id = request.getParameter("id");
		
		if(id == null || "".equals(id)){
			ActionMessages errors = new ActionMessages();
			errors.add("UnknowError", new ActionMessage("errors.UnKnowError"));
			saveErrors(request, errors);
			saveToken(request);

			return mapping.findForward("faile");
		}
		
		DeviceListDetail detailDevice = tenderManageService.findDeviceListDetailById(Integer.valueOf(id));
		detailDevice.setDldStatus(CommonService.DELETE_FLAG);
		tenderManageService.updateDeviceListDetail(detailDevice);
				
		Integer firstDeviceId = detailDevice.getDldFirstEquipmentId();
		if(firstDeviceId == null)
		{
			DeviceList2nd secondDevice = tenderManageService.getDeviceList2ndById(detailDevice.getDldSecondEquipmentId());
			firstDeviceId = secondDevice.getDlsFirstEquipmentId();
		}
		DeviceList1st firstDevice = tenderManageService.getDeviceList1stById(firstDeviceId);
		firstDevice.setDlfStatus(CommonService.DELETE_FLAG);		
				
		TenderProject tp = tenderManageService.getTenderProjectById(firstDevice.getDlfTenderProjectId());
		if(tp != null){
			List<DeviceList1st> deviceList = tp.getDeviceList();
			if(deviceList == null){
				request.setAttribute("deviceList", new ArrayList<DeviceList1st>());
			} else {
				filterDeviceListByTime(deviceList);
				sortDeviceListByTime(deviceList);
				request.setAttribute("deviceList", deviceList);
			}
			calcDeviceCount(deviceList, request);
		}
		
		return mapping.findForward("editDeviceList");
	}
	
	public ActionForward detailDeviceReportPrice(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String id = request.getParameter("id");
		
		if(id == null || "".equals(id)){
			ActionMessages errors = new ActionMessages();
			errors.add("UnknowError", new ActionMessage("errors.UnKnowError"));
			saveErrors(request, errors);
			saveToken(request);

			return mapping.findForward("faile");
		}
		
		DeviceListDetail detailDevice = tenderManageService.findDeviceListDetailById(Integer.valueOf(id));
		
		if(detailDevice == null){
			ActionMessages errors = new ActionMessages();
			errors.add("UnknowError", new ActionMessage("errors.UnKnowError"));
			saveErrors(request, errors);
			saveToken(request);

			return mapping.findForward("faile");
		}
		String statusId = "";
		if(detailDevice.getFirstSelected() == 1){
			statusId = "1";
		} else if(detailDevice.getSecondSelected() == 1){
			statusId = "2";
		} else if(detailDevice.getThirdSelected() == 1){
			statusId = "3";
		}
		request.setAttribute("statusId", statusId);
		dform.set("detailDeviceInfo", detailDevice);
		
		if(detailDevice.getFirstSelectedSupplier() == null)
		{
			detailDevice.setFirstSelectedSupplier(new Customer());
		}
		if(detailDevice.getSecondSelectedSupplier() == null)
		{
			detailDevice.setSecondSelectedSupplier(new Customer());
		}
		if(detailDevice.getThirdSelectedSupplier() == null)
		{
			detailDevice.setThirdSelectedSupplier(new Customer());
		}
		
		return mapping.findForward("detailDeviceReportPrice");
	}
	
	public ActionForward chooseDetailDeviceSupplier(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String id = request.getParameter("id");
		
		if(id == null || "".equals(id)){
			ActionMessages errors = new ActionMessages();
			errors.add("UnknowError", new ActionMessage("errors.UnKnowError"));
			saveErrors(request, errors);
			saveToken(request);

			return mapping.findForward("faile");
		}
		
		DeviceListDetail detailDevice = tenderManageService.findDeviceListDetailById(Integer.valueOf(id));
		
		if(detailDevice == null){
			ActionMessages errors = new ActionMessages();
			errors.add("UnknowError", new ActionMessage("errors.UnKnowError"));
			saveErrors(request, errors);
			saveToken(request);

			return mapping.findForward("faile");
		}
		String statusId = "";
		if(detailDevice.getFirstSelected() == 1){
			statusId = "1";
		} else if(detailDevice.getSecondSelected() == 1){
			statusId = "2";
		} else if(detailDevice.getThirdSelected() == 1){
			statusId = "3";
		}
		request.setAttribute("statusId", statusId);
		dform.set("detailDeviceInfo", detailDevice);
		
		return mapping.findForward("chooseDetailDeviceSupplier");
	}
	
	public ActionForward confirmDetailDevice(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		String radioId = request.getParameter("radioId");
		
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		DeviceListDetail detailDevice = (DeviceListDetail)dform.get("detailDeviceInfo");
		judgeSupplySelect(radioId, detailDevice);
		
		String userId = (String) request.getSession().getAttribute("account");
		
		if(detailDevice.getDldId() != null){
			detailDevice.setDldUpdateUser(userId);
			tenderManageService.updateDeviceListDetail(detailDevice);
		} else {
			detailDevice.setDldCreateUser(userId);
			tenderManageService.saveDeviceListDetail(detailDevice);
		}
		
		return mapping.findForward("refreshDeviceList");
	}
	
	public ActionForward confirmChooseDetailDeviceSupplier(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		String radioId = request.getParameter("facRadio");
		
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		DeviceListDetail detailDevice = (DeviceListDetail)dform.get("detailDeviceInfo");
		judgeSupplySelect(radioId, detailDevice);
		
		String userId = (String) request.getSession().getAttribute("account");
		
		if(detailDevice.getDldId() != null){
			detailDevice.setDldUpdateUser(userId);
			tenderManageService.updateDeviceListDetail(detailDevice);
		} else {
			detailDevice.setDldCreateUser(userId);
			tenderManageService.saveDeviceListDetail(detailDevice);
		}
		//根据选择的供应商,更新投标预算里设备金额
		double  amount = 0f;
		TenderProject tp = (TenderProject) dform.get("tenderInfo");
		tp = tenderManageService.getTenderProjectById(tp.getTpId());
		if(tp != null){
			List<DeviceList1st> deviceList = tp.getDeviceList();
			for(DeviceList1st d1:deviceList)
			{
				if(d1.getDlfStatus() != CommonService.DELETE_FLAG)
				{
					amount = amount + this.calculateDeviceAmount(d1.getDeviceListDetailList());
					
					for(DeviceList2nd d2:d1.getDevicList2ndList())
					{
						if(d2.getDlsStatus() != CommonService.DELETE_FLAG)
						{
							amount = amount + this.calculateDeviceAmount(d2.getDeviceListDetailList());
						}
					}
				}				
			}
			
			BudgetItem currentsb = tp.getTenderBudget().getE11();
			currentsb.setBiAmount(new BigDecimal(amount));
			tenderManageService.saveBudgetItem(currentsb);						
		}
		
		
		return mapping.findForward("refreshChooseSupplier");
	}
	
	private double calculateDeviceAmount(List<DeviceListDetail> detailList)
	{
		double  rtn = 0d;
		
		for(DeviceListDetail dd:detailList)
		{
			if(dd.getDldStatus() == CommonService.DELETE_FLAG)
			{
				continue;
			}
			if(dd.getFirstSelected() == 1)
			{
				rtn = rtn + dd.getDldFirstSelectedSupplierPrice().doubleValue() * dd.getDldEquipmentCount();
			}
			else if(dd.getSecondSelected() == 1)
			{
				rtn = rtn + dd.getDldSecondSelectedSupplierPrice().doubleValue() * dd.getDldEquipmentCount();
			}
			else if(dd.getThirdSelected() == 1)
			{
				rtn = rtn + dd.getDldThirdSelectedSupplierPrice().doubleValue() * dd.getDldEquipmentCount();
			}
			
		}
		
		return rtn;
	}
	
	private void judgeSupplySelect(String radioId, DeviceListDetail detailDevice){
		if(radioId == null || "".equals(radioId)){
			detailDevice.setFirstSelected(0);
			detailDevice.setSecondSelected(0);
			detailDevice.setThirdSelected(0);
		} else if("1".equals(radioId)){
			detailDevice.setFirstSelected(1);
			detailDevice.setSecondSelected(0);
			detailDevice.setThirdSelected(0);
		} else if("2".equals(radioId)){
			detailDevice.setFirstSelected(0);
			detailDevice.setSecondSelected(1);
			detailDevice.setThirdSelected(0);
		} else if("3".equals(radioId)){
			detailDevice.setFirstSelected(0);
			detailDevice.setSecondSelected(0);
			detailDevice.setThirdSelected(1);
		}
	}
	
	public ActionForward editBaseDetailDevice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String id = request.getParameter("id");
		
		if(id == null || "".equals(id)){
			ActionMessages errors = new ActionMessages();
			errors.add("UnknowError", new ActionMessage("errors.UnKnowError"));
			saveErrors(request, errors);
			saveToken(request);

			return mapping.findForward("faile");
		}
		
		DeviceListDetail detailDevice = tenderManageService.findDeviceListDetailById(Integer.valueOf(id));
		
		if(detailDevice == null){
			ActionMessages errors = new ActionMessages();
			errors.add("UnknowError", new ActionMessage("errors.UnKnowError"));
			saveErrors(request, errors);
			saveToken(request);

			return mapping.findForward("faile");
		}
		dform.set("detailDeviceInfo", detailDevice);
		
		return mapping.findForward("addBaseDetailDevice");
	}
	
	public ActionForward editBaseQuotedPriceInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String id = request.getParameter("id");
		
		if(id == null || "".equals(id)){
			ActionMessages errors = new ActionMessages();
			errors.add("UnknowError", new ActionMessage("errors.UnKnowError"));
			saveErrors(request, errors);
			saveToken(request);

			return mapping.findForward("faile");
		}
		
		DeviceListDetail detailDevice = tenderManageService.findDeviceListDetailById(Integer.valueOf(id));
		
		if(detailDevice == null){
			ActionMessages errors = new ActionMessages();
			errors.add("UnknowError", new ActionMessage("errors.UnKnowError"));
			saveErrors(request, errors);
			saveToken(request);

			return mapping.findForward("faile");
		}
		if(detailDevice.getResponsiblePerson() == null)
		{
			detailDevice.setResponsiblePerson(new Account());
		}
		
		dform.set("detailDeviceInfo", detailDevice);
		
		return mapping.findForward("editBaseQuotedPrice");
	}
	
	public ActionForward confirmBaseDetailDevice(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		DeviceListDetail detailDevice = (DeviceListDetail)dform.get("detailDeviceInfo");
		
		String userId = (String) request.getSession().getAttribute("account");
		
		if(detailDevice.getDldId() != null){
			detailDevice.setDldUpdateUser(userId);
			tenderManageService.updateDeviceListDetail(detailDevice);
		} else {
			detailDevice.setDldCreateUser(userId);
			tenderManageService.saveDeviceListDetail(detailDevice);
		}
		
		return mapping.findForward("refreshDeviceQuotedPrice");
	}
	
	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public TenderManageService getTenderManageService() {
		return tenderManageService;
	}

	public void setTenderManageService(TenderManageService tenderManageService) {
		this.tenderManageService = tenderManageService;
	}

	public ProjectManageService getProjectManageService() {
		return projectManageService;
	}

	public void setProjectManageService(ProjectManageService projectManageService) {
		this.projectManageService = projectManageService;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
}
