package org.king.projectmanage.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.king.accountmanage.bean.AccountFeeTax;
import org.king.accountmanage.bean.AccountPurchaseContract;
import org.king.accountmanage.bean.AccountSaleContract;
import org.king.common.checktask.CheckTask;
import org.king.common.customertype.CustomerType;
import org.king.common.projectrole.ProjectRole;
import org.king.common.projectstatus.ProjectStatus;
import org.king.common.service.CommonService;
import org.king.common.service.impl.CommonServiceImpl;
import org.king.customer.bean.Customer;
import org.king.customer.service.CustomerService;
import org.king.framework.web.action.BaseAction;
import org.king.projectmanage.bean.ProjectApprovalRecord;
import org.king.projectmanage.bean.ProjectAttachment;
import org.king.projectmanage.bean.ProjectContract;
import org.king.projectmanage.bean.ProjectScheduleStage;
import org.king.projectmanage.bean.ProjectScheduleTask;
import org.king.projectmanage.bean.ProjectTeam;
import org.king.projectmanage.bean.TaskImplementRecord;
import org.king.projectmanage.bean.TenderProject;
import org.king.projectmanage.service.ProjectManageService;
import org.king.security.domain.Account;
import org.king.security.service.AccountService;
import org.king.tender.bean.BudgetItem;
import org.king.tender.bean.ProjectBudget;
import org.king.tender.bean.ShortfallAmount;
import org.king.tender.service.TenderManageService;
import org.king.utils.DateUtil;
import org.king.utils.FileUtil;
import org.king.utils.MailTool;

/**
 * 项目管理、投标管理、立项备案管理、台账统计
 * @author xy91126
 *
 */
public class ProjectManageAction extends BaseAction {
	private TenderManageService tenderManageService;
	private ProjectManageService projectManageService;
	private CommonService commonService;
	private CustomerService customerService;
	private AccountService accountService;
	
	public ActionForward showBudgetChecktaskList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		String type = request.getParameter("type");
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");	
		
		List<CheckTask> taskList = tenderManageService.getAllStepCheckTask(tp,type);
		projectManageService.retrieveTaskCheckableUserList(taskList);
		request.setAttribute("taskList", taskList);
			
		return mapping.findForward("checkTaskList");
	}
	
	
	public ActionForward checkApplyBudgetBegin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");	
		ProjectBudget applyBudget = tp.getApplyBudget();
		Account user = (Account) request.getSession().getAttribute("accountPerson");
				
		//发送
		ArrayList<CheckTask> taskList = projectManageService.getApplyBudgetCheckStep(tp.getApplyBudget(), user, tp.getTpDept());
				
		if(taskList.size() > 0)//无意义？
		{			
			projectManageService.saveCheckTaskList(taskList);
			
			applyBudget.setPbCheckResult(ProjectBudget.BUDGET_CHECK_STATUS_CHECKING);				
			tenderManageService.updateProjectBudget(applyBudget);
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
		
		return this.editApplyTenderBudget(mapping, dform, request, response);
	}
	
	public ActionForward editApplyTenderBudget(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		String id = request.getParameter("id");
		
		if (id == null || "".equals(id)) {
			TenderProject tp = (TenderProject) dform.get("projectInfo");
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
		if(tp.getApplyBudget() == null)
		{
			ProjectBudget pbs = tenderManageService.modifyCreateBudgetItems();
			pbs.setPbType(ProjectBudget.BUDGET_TYPE_APPLY);
			pbs.setPbProject(tp.getTpId());
			tp.setApplyBudget(pbs);
			tenderManageService.saveProjectBudget(pbs);
		}
		
		dform.set("projectInfo", tp);
		
		List<CheckTask> taskList = tenderManageService.getAllStepCheckTask(tp,String.valueOf(CheckTask.TASK_TYPE_APPLY_BUDGET_CHECK));
		projectManageService.retrieveTaskCheckableUserList(taskList);
		request.setAttribute("taskList", taskList);
		
		return mapping.findForward("editApplyBudget");
	}

	
	public ActionForward displayTenderCostsum(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		
		if(tp.getApplyBudget() == null)
		{
			ProjectBudget pbs = tenderManageService.modifyCreateBudgetItems();
			pbs.setPbType(ProjectBudget.BUDGET_TYPE_APPLY);
			pbs.setPbProject(tp.getTpId());
			tp.setApplyBudget(pbs);
		}
		
		List<CheckTask> taskList = tenderManageService.getAllStepCheckTask(tp,String.valueOf(CheckTask.TASK_TYPE_APPLY_BUDGET_CHECK));
		projectManageService.retrieveTaskCheckableUserList(taskList);
		request.setAttribute("taskList", taskList);

		return mapping.findForward("costsumDisplay");
	}
		
	
	/**
	 * 显示审查任务列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward readCheckTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String taskId = request.getParameter("id");
		
		CheckTask task = projectManageService.getCheckTaskById(Integer.valueOf(taskId));
		request.setAttribute("checkTaskInfo",task);
		
		List<CheckTask> taskList = projectManageService.getAllStepCheckTask(task);
		projectManageService.retrieveTaskCheckableUserList(taskList);
		
		request.setAttribute("taskList", taskList);
		
		return mapping.findForward("checkTaskDisplay");
	}
	/**
	 * 显示审查任务内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward displayCheckTaskDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		
		String taskId = request.getParameter("id");
		
		CheckTask task = projectManageService.getCheckTaskById(Integer.valueOf(taskId));
		projectManageService.retrieveTaskCheckableUserList(task);
		
		request.setAttribute("task",task);
				
		return mapping.findForward("checkTaskDetail");
	}
	
	/**
	 * 提交审查
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward confirmCheckTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		CheckTask checkTaskInfo = (CheckTask) dform.get("checkTaskInfo");
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		String src = request.getParameter("src");
				
		checkTaskInfo.setCtCheckTime(new Date());
		checkTaskInfo.setCtCheckUser(user.getId());
		checkTaskInfo.setCtStatus(CheckTask.TASK_STATUS_CHECKED);
		projectManageService.updateCheckTask(checkTaskInfo);
		
		if(checkTaskInfo.getCtCheckResult().equals(CheckTask.TASK_CHECK_RESULT_NO))
		{
			if(checkTaskInfo.getCtTaskType().equals(CheckTask.TASK_TYPE_PROJECT_CONTRACT_CHECK))
			{
				checkTaskInfo.getContract().setPcStatus(ProjectContract.Contract_Status_RETURN);
				projectManageService.updateProjectContract(checkTaskInfo.getContract());
			}
			else if(checkTaskInfo.getCtTaskType().equals(CheckTask.TASK_TYPE_PROJECT_APPROVAL_RECORD_CHECK))
			{
				checkTaskInfo.getProjectApprovalRecord().setParStatus(ProjectApprovalRecord.PAR_STATUS_APPROVAL_RETURN);
				projectManageService.updateProjectApprovalRecord(checkTaskInfo.getProjectApprovalRecord());
			}						
			else if(checkTaskInfo.getCtTaskType().equals(CheckTask.TASK_TYPE_PROJECT_APPROVAL_TENDER_CHECK))
			{
				checkTaskInfo.getProjectApprovalRecord().setParStatus(ProjectApprovalRecord.PAR_STATUS_TENDER_RETURN);
				projectManageService.updateProjectApprovalRecord(checkTaskInfo.getProjectApprovalRecord());
			}
			else if(checkTaskInfo.getCtTaskType().equals(CheckTask.TASK_TYPE_TENDER_BUDGET_CHECK) || checkTaskInfo.getCtTaskType().equals(CheckTask.TASK_TYPE_APPLY_BUDGET_CHECK))
			{
				checkTaskInfo.getProjectBudget().setPbCheckResult(ProjectBudget.BUDGET_CHECK_STATUS_RETURN);
				tenderManageService.updateProjectBudget(checkTaskInfo.getProjectBudget());
			}	
			else if(checkTaskInfo.getCtTaskType().equals(CheckTask.TASK_TYPE_TENDER_DOCUMENT_CHECK))
			{
				checkTaskInfo.getProject().setTpTenderdocCheckStatus(ProjectStatus.TENDERDOC_CHECK_STATUS_REJECT);				
				tenderManageService.updateTenderProject(checkTaskInfo.getProject());
			}
			
			List<CheckTask> taskList = projectManageService.getAllStepCheckTask(checkTaskInfo);
			
			for(CheckTask ct:taskList)
			{
				if(ct.getCtStatus().equals(CheckTask.TASK_STATUS_INIT))
				{
					ct.setCtStatus(CheckTask.TASK_STATUS_DELETE);
				}
			}
			
			projectManageService.saveCheckTaskList(taskList);
		}
		else if(checkTaskInfo.getCtCheckResult().equals(CheckTask.TASK_CHECK_RESULT_OK))
		{
			if(projectManageService.isCurrentStepCheckTaskFinished(checkTaskInfo))
			{
				String mailList = "";
				
				List<CheckTask> nextTaskList = projectManageService.getNextStepCheckTask(checkTaskInfo);
				if(nextTaskList.size() > 0)
				{
					for(CheckTask ct:nextTaskList)
					{
						ct.setCtStatus(CheckTask.TASK_STATUS_CURRENT_CHECK);						
						mailList = mailList + MailTool.MAIL_ADDRESS_SEPARATER + projectManageService.getChecktaskMail(ct);						
					}
					projectManageService.saveCheckTaskList(nextTaskList);
					projectManageService.sendCheckTaskMail(mailList, nextTaskList.get(0));	
				}
				else
				{
					if(checkTaskInfo.getCtTaskType().equals(CheckTask.TASK_TYPE_PROJECT_CONTRACT_CHECK))
					{
						checkTaskInfo.getContract().setPcStatus(ProjectContract.Contract_Status_CHECKED);
						checkTaskInfo.getContract().setPcCheckEnd(new Date());
						projectManageService.updateProjectContract(checkTaskInfo.getContract());
					}
					else if(checkTaskInfo.getCtTaskType().equals(CheckTask.TASK_TYPE_PROJECT_APPROVAL_RECORD_CHECK))
					{							
						ProjectApprovalRecord par = projectManageService.getProjectApprovalRecordById(checkTaskInfo.getProjectApprovalRecord().getParId());
						par.setParStatus(ProjectApprovalRecord.PAR_STATUS_APPROVAL_CHECKED);
						projectManageService.updateProjectApprovalRecord(par);
					}
					else if(checkTaskInfo.getCtTaskType().equals(CheckTask.TASK_TYPE_PROJECT_APPROVAL_TENDER_CHECK))
					{
						ProjectApprovalRecord par = projectManageService.getProjectApprovalRecordById(checkTaskInfo.getProjectApprovalRecord().getParId());
						par.setParStatus(ProjectApprovalRecord.PAR_STATUS_TENDER_CHECKED);
						projectManageService.updateProjectApprovalRecord(par);
						
						//将立项信息转换为项目信息
						String projectOwner = par.getParProjectOwner();
						Customer customer = customerService.searchCustomerByName(projectOwner);
						Date createTime = new Date();
						if(customer == null)
						{
							customer = customerService.createCustomer();
							customer.setCType(CustomerType.CUSTOMER_TYPE_SUPPLIER);
							customer.setCName(projectOwner);
							customer.setCCreateUser(par.getParCreateUser());
							customer.setCCreateTime(createTime);
							customerService.saveCustomer(customer);							
						}
											
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						TenderProject tp = projectManageService.createTenderProject();
						
						tp.setTpCustomer(customer.getCId());
						tp.setTpName(par.getParProjectName());
						tp.setTpCreateUser(par.getParCreateUser());
						tp.setTpCreateTime(createTime);
						tp.setTpBeginDate(format.format(createTime));
						tp.setTpEndDate(format.format(createTime));
						tp.setTpDept(par.getParDept());
						tp.setTpManager(par.getParManager());
						tp.setTpStatus(ProjectStatus.PROJECT_STATUS_TENDER);
						tp.setTpMarketManager(par.getParMarketManager());
						
						tenderManageService.saveTenderProject(tp, null);	
						projectManageService.saveProjectCreaterAsTeam(tp, commonService);
					}
					else if(checkTaskInfo.getCtTaskType().equals(CheckTask.TASK_TYPE_TENDER_BUDGET_CHECK) || checkTaskInfo.getCtTaskType().equals(CheckTask.TASK_TYPE_APPLY_BUDGET_CHECK))
					{
						checkTaskInfo.getProjectBudget().setPbStatus(ProjectBudget.BUDGET_STATUS_CHECKED);
						checkTaskInfo.getProjectBudget().setPbCheckResult(ProjectBudget.BUDGET_CHECK_STATUS_CHECKED);
						tenderManageService.updateProjectBudget(checkTaskInfo.getProjectBudget());
					}
					else if(checkTaskInfo.getCtTaskType().equals(CheckTask.TASK_TYPE_TENDER_DOCUMENT_CHECK))
					{
						TenderProject tp = tenderManageService.getTenderProjectById(checkTaskInfo.getCtProject());
						tp.setTpTenderdocCheckStatus(ProjectStatus.TENDERDOC_CHECK_STATUS_CHECKED);
						tenderManageService.updateTenderProject(tp);						
					}
				}			
			}		
		}
		
		if("about".equals(src))
		{
			return mapping.findForward("refreshAbout");
		}
		
		return mapping.findForward("refreshCurrentTask");
	}
	
	
	
	
	/**
	 * 刷新首页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refreshAboutPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		
		CheckTask checkTaskInfo = new CheckTask();
		checkTaskInfo.setCtStatus(Integer.valueOf(CheckTask.TASK_STATUS_CURRENT_CHECK));
		
		if(user.haveNoAnyPost())
		{
			return null;			
		}
		
		Object[] args = {checkTaskInfo,user};				
				
		List<CheckTask> taskList = projectManageService.searchAboutCheckTask(args);		
		projectManageService.retrieveTaskCheckableUserList(taskList);
		
		request.setAttribute("currentTaskList", taskList);
			
		return mapping.findForward("aboutCurrentTask");
	}
	
	/**
	 * 执行审查
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doCheckTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String taskId = request.getParameter("id");
		
		CheckTask task = projectManageService.getCheckTaskById(Integer.valueOf(taskId));
		dform.set("checkTaskInfo",task);
				
		return mapping.findForward("doCheckTask");
	}
	
	/**
	 * 首页审查任务列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward aboutSearchCheckTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		String status = request.getParameter("status");
		
		CheckTask checkTaskInfo = new CheckTask();
		checkTaskInfo.setCtStatus(Integer.valueOf(status));
				
		Object[] args = {checkTaskInfo,user};				
		ActionForward forward = null;
		if(checkTaskInfo.getCtStatus().equals(CheckTask.TASK_STATUS_CURRENT_CHECK))
		{
			List<CheckTask> taskList = projectManageService.searchAboutCheckTask(args);	
			projectManageService.retrieveTaskCheckableUserList(taskList);
			
			request.setAttribute("currentTaskList", taskList);
			
			forward = mapping.findForward("aboutCurrentTask");
		}
		else if(checkTaskInfo.getCtStatus().equals(CheckTask.TASK_STATUS_INIT))
		{
			List<CheckTask> taskList = projectManageService.searchAboutCheckTask(args);		
			projectManageService.retrieveTaskCheckableUserList(taskList);
			
			request.setAttribute("futureTaskList", taskList);
			
			forward = mapping.findForward("aboutFutureTask");
		}
		else if(checkTaskInfo.getCtStatus().equals(CheckTask.TASK_STATUS_CHECKED))
		{
			List<CheckTask> taskList = projectManageService.searchAboutCheckTask(args);	
			projectManageService.retrieveTaskCheckableUserList(taskList);
			
			request.setAttribute("checkedTaskList", taskList);
			
			forward = mapping.findForward("aboutCheckedTask");
		}
		
		return forward;		
	}
	
	/**
	 * 重置审查任务查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward resetSearchCheckTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		String status = request.getParameter("status");
		
		CheckTask checkTaskInfo = new CheckTask();
		checkTaskInfo.setContract(new ProjectContract());
		checkTaskInfo.setProject(new TenderProject());
		checkTaskInfo.setSendUser(new Account());
		checkTaskInfo.setCtStatus(Integer.valueOf(status));		
				
		dform.set("checkTaskInfo",checkTaskInfo);
					
		return this.searchCheckTask(mapping, form, request, response);
	}
		
	/**
	 * 查询审核任务列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward searchCheckTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		
		CheckTask checkTaskInfo = (CheckTask) dform.get("checkTaskInfo");
		String page = request.getParameter("pager.offset");
		
		Object[] args = {checkTaskInfo,page,user};
		List<CheckTask> taskList = projectManageService.searchCheckTask(args);
		projectManageService.retrieveTaskCheckableUserList(taskList);
		
		Integer taskCount = projectManageService.searchCheckTaskCount(args);
		
		request.setAttribute("deptList", commonService.getDepartmentList());
		request.setAttribute("taskList", taskList);
		request.setAttribute("taskCount", taskCount);
		
		ActionForward forward = null;
		if(checkTaskInfo.getCtStatus().equals(CheckTask.TASK_STATUS_CURRENT_CHECK))
		{
			forward = mapping.findForward("currentTask");
		}
		else if(checkTaskInfo.getCtStatus().equals(CheckTask.TASK_STATUS_INIT))
		{
			forward = mapping.findForward("futureTask");
		}
		else if(checkTaskInfo.getCtStatus().equals(CheckTask.TASK_STATUS_CHECKED))
		{
			forward = mapping.findForward("checkedTask");
		}
					
		return forward;
	}

	/**
	 * 创建项目合同审核任务
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward checkProjectContractBegin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		
		ProjectContract contract = tp.getProjectContract();
				
		ArrayList<CheckTask> taskList = null;
		
		if(contract.getPcType().equals(ProjectContract.Contract_TYPE_SALE))
		{
			taskList = projectManageService.getSaleContractCheckStep(contract, user);
		}
		else
		{
			taskList = projectManageService.getPurchaseContractCheckStep(contract, user);
		}					
		
		if(taskList.size() > 0)
		{
			projectManageService.saveCheckTaskList(taskList);
			
			contract.setPcStatus(ProjectContract.Contract_Status_CHECKING);
			contract.setPcCheckBegin(new Date());			
			projectManageService.updateProjectContract(contract);
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
		
		return mapping.findForward("refreshProjectContract");
	}
	
	
	
	/**
	 * 创建立项备案审核任务
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward checkProjectApprovalRecordBegin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		ProjectApprovalRecord par = (ProjectApprovalRecord) dform.get("projectApprovalRecordInfo");		
		Account user = (Account) request.getSession().getAttribute("accountPerson");
				
		//不保存直接发送时，保存
		String uploadPath = request.getSession().getServletContext().getInitParameter("uploadFilePath");
		if (!uploadPath.endsWith("\\")) {
			uploadPath = uploadPath + "\\";
		}
		if (par.getParCreateUser() == null || par.getParCreateUser().equals("")) {
			par.setParCreateUser(user.getId());					
		}
		projectManageService.saveProjectApprovalRecord(par, uploadPath);
		
		
		//发送
		ArrayList<CheckTask> taskList = projectManageService.getProjectApprovalRecordCheckStep(par, user);
				
		if(taskList.size() > 0)//无意义？
		{
			if(par.getParTenderPrice() <= 5000 && par.getParOperatingExpense() <= 50000)
			{
				taskList.remove(taskList.size() -1);
			}
			projectManageService.saveCheckTaskList(taskList);
			
			par.setParStatus(ProjectApprovalRecord.PAR_STATUS_APPROVAL_CHECKING);				
			projectManageService.updateProjectApprovalRecord(par);
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
		
		return this.resetSearchProjectApprovalRecord(mapping, dform, request, response);
	}
	
	/**
	 * 开始立项备案投标审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward checkProjectApprovalTenderBegin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		ProjectApprovalRecord par = (ProjectApprovalRecord) dform.get("projectApprovalRecordInfo");		
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		
		//不保存直接发送时，保存		
		String uploadPath = request.getSession().getServletContext().getInitParameter("uploadFilePath");
		if (!uploadPath.endsWith("\\")) {
			uploadPath = uploadPath + "\\";
		}
		if (par.getParCreateUser() == null || par.getParCreateUser().equals("")) {
			par.setParCreateUser(user.getId());					
		}
		par.setParStatus(ProjectApprovalRecord.PAR_STATUS_TENDER_CHECKING);	
		projectManageService.saveProjectApprovalRecord(par, uploadPath);
		
		//产生评审记录		
		ArrayList<CheckTask> taskList = projectManageService.getProjectApprovalTenderCheckStep(par, user);
		
		if(taskList.size() > 0)
		{
			projectManageService.saveCheckTaskList(taskList);			
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
		
				
		return this.resetSearchProjectApprovalCheck(mapping, dform, request, response);
	}
	
	/**
	 * 提交理想备案投标审核任务
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward saveProjectApprovalTender(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		ProjectApprovalRecord par = (ProjectApprovalRecord) dform.get("projectApprovalRecordInfo");		
					
		String uploadPath = request.getSession().getServletContext().getInitParameter("uploadFilePath");
		if (!uploadPath.endsWith("\\")) {
			uploadPath = uploadPath + "\\";
		}
						
		projectManageService.saveProjectApprovalRecord(par, uploadPath);
		
		ArrayList<ProjectAttachment> attachmentList = new ArrayList<ProjectAttachment>();
		attachmentList.addAll(par.getAttachmentList());			
		projectManageService.updateProjectAttachmentCode(attachmentList);
				
		
		return this.resetSearchProjectApprovalCheck(mapping, dform, request, response);
	}
	
	
	
	/**
	 * 创建项目
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward createTenderProject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = projectManageService.createTenderProject();
		dform.set("projectInfo", tp);

		this.prepareMetadata(request);

		return mapping.findForward("baseInput");
	}
	
	/**
	 * 创建立项备案
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward createProjectApprovalRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		ProjectApprovalRecord par = projectManageService.createProjectApprovalRecord();
		par.setManager(new Account());
		par.setMarketManager(new Account());
		dform.set("projectApprovalRecordInfo", par);
		
		this.prepareMetadata(request);

		return mapping.findForward("projectApprovalRecordEdit");
	}

	/**
	 * 刷新项目输入页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refreshProjectInput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String bd = tp.getTpBeginDate();
		String ed = tp.getTpEndDate();

		if (bd != null && !bd.isEmpty() && ed != null && !ed.isEmpty()) {			
			try {
				Date beginDate = DateFormat.getDateInstance().parse(bd);
				Date endDate = DateFormat.getDateInstance().parse(ed);
				int workDayCount = DateUtil.getWorkDayCount(beginDate, endDate);
				tp.setTpWorkdayCount(workDayCount);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			

			projectManageService.calculateProjectTimeSchedule(tp);
			projectManageService.calculateProjectDoSchedule(tp);
		}

		this.prepareMetadata(request);

		return mapping.findForward("baseInput");
	}
	
	/**
	 * 刷新项目基本信息输入页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refreshProjectBase(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String bd = tp.getTpBeginDate();
		String ed = tp.getTpEndDate();

		if (bd != null && !bd.isEmpty() && ed != null && !ed.isEmpty()) {
			try {
				Date beginDate = DateFormat.getDateInstance().parse(bd);
				Date endDate = DateFormat.getDateInstance().parse(ed);

				int workDayCount = DateUtil.getWorkDayCount(beginDate, endDate);
				tp.setTpWorkdayCount(workDayCount);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			projectManageService.calculateProjectTimeSchedule(tp);
			projectManageService.calculateProjectDoSchedule(tp);
		}

		this.prepareMetadata(request);

		return mapping.findForward("baseEdit");
	}

	/**
	 * 刷新项目成员编辑页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refreshProjectTeamEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		this.prepareTeamMetadata(request);

		return mapping.findForward("teamMemberInput");
	}

	/**
	 * 刷新项目日程计划编辑页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refreshProjectSchedulePlanEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		TenderProject tp = (TenderProject) dform.get("projectInfo");

		projectManageService.calculateProjectDoSchedule(tp);

		return mapping.findForward("schedulePlanInput");
	}

	/**
	 * 刷新项目日程编辑页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refreshProjectScheduleEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		TenderProject tp = (TenderProject) dform.get("projectInfo");

		projectManageService.calculateProjectDoSchedule(tp);

		return mapping.findForward("scheduleInput");
	}

	/**
	 * 刷新日程阶段页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refreshScheduleStage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		return mapping.findForward("addScheduleStage");
	}
	
	/**
	 * 刷新销售合同输入页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refreshSaleContractInput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		return mapping.findForward("saleContractInput");
	}
	
	/**
	 * 刷新销售合同输入页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refreshPurchaseContractInput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		return mapping.findForward("purchaseContractInput");
	}
	/**
	 * 刷新立项备案编辑页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refreshProjectApprovalRecordEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		this.prepareMetadata(request);

		return mapping.findForward("projectApprovalRecordEdit");
	}

	/**
	 * 刷新日程任务页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refreshScheduleTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		this.prepareTaskList(request, tp);

		return mapping.findForward("addScheduleTask");
	}

	/**
	 * 刷新项目文档页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refreshProjectDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		return mapping.findForward("documentInput");
	}
	
	/**
	 * 刷新销售合同页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refreshSaleContract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		
		this.calculateSaleContractAmount(tp);

		request.setAttribute("tabpageId", "saleContract");
		
		return mapping.findForward("accountManage");
	}
	
	/**
	 * 刷新采购合同页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refreshPurchaseContract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		
		this.calculatePurchaseContractAmount(tp);
		
		request.setAttribute("tabpageId", "purchaseContract");
		
		return mapping.findForward("accountManage");
	}
	
	/**
	 * 刷新项目费税页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refreshFeeTax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		
		this.calculateFeeTaxAmount(tp);
		
		request.setAttribute("tabpageId", "feeTax");
		
		return mapping.findForward("accountManage");
	}
		

	/**
	 * 保存项目信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward saveTenderProject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String uploadPath = request.getSession().getServletContext().getInitParameter("uploadFilePath");
		if (!uploadPath.endsWith("\\")) {
			uploadPath = uploadPath + "\\";
		}

		String userId = (String) request.getSession().getAttribute("account");

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		if (tp.getTpCreateUser() == null || tp.getTpCreateUser().equals("")) {
			tp.setTpCreateUser(userId);			
			
		}

		tenderManageService.saveTenderProject(tp, uploadPath);
		tp.setDepartment(commonService.getDepartmentById(tp.getTpDept()));
		tp.setStatus(commonService.getProjectStatusById(tp.getTpStatus()));
		
		ArrayList<ProjectAttachment> attachmentList = new ArrayList<ProjectAttachment>();			
		attachmentList.addAll(tp.getAttachmentList());						
		projectManageService.updateProjectAttachmentCode(attachmentList);
		
		projectManageService.saveProjectCreaterAsTeam(tp, commonService);

		if (!userId.equals(tp.getTpManager())) {
			projectManageService.saveProjectManagerAsTeam(tp, commonService);
		}

		//生成项目预算
		// ProjectBudget pb = tenderManageService.modifyCreateBudgetItems();
		// pb.setPbProject(tp.getTpId());
		// pb.setPbType(ProjectBudget.BUDGET_TYPE_PROJECT);
		// //拷贝投标预算
		//projectManageService.copyDataFromTenderBudget(pb,tp.getTenderBudget());
		//		
		// tp.setProjectBudget(pb);
		// tenderManageService.saveProjectBudget(pb);

		this.prepareMetadata(request);

		return this.resetSearchTenderProject(mapping, dform, request, response);
	}
	
	/**
	 * 保存立项备案信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward saveProjectApprovalRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String uploadPath = request.getSession().getServletContext().getInitParameter("uploadFilePath");
		if (!uploadPath.endsWith("\\")) {
			uploadPath = uploadPath + "\\";
		}

		String userId = (String) request.getSession().getAttribute("account");

		ProjectApprovalRecord par = (ProjectApprovalRecord) dform.get("projectApprovalRecordInfo");

		if (par.getParCreateUser() == null || par.getParCreateUser().equals("")) {
			par.setParCreateUser(userId);					
		}
		if(par.getParTenderDate().isEmpty())
		{
			par.setParTenderDate(null);
		}
		
		projectManageService.saveProjectApprovalRecord(par, uploadPath);
		par.setDepartment(commonService.getDepartmentById(par.getParDept()));
		
		this.prepareMetadata(request);

		return this.resetSearchProjectApprovalRecord(mapping, dform, request, response);
	}

	/**
	 * 保存项目文档信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward saveProjectDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		String uploadPath = request.getSession().getServletContext().getInitParameter("uploadFilePath");
		if (!uploadPath.endsWith("\\")) {
			uploadPath = uploadPath + "\\";
		}

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		projectManageService.saveProjectDocument(tp, uploadPath);
		
		ArrayList<ProjectAttachment> attachmentList = new ArrayList<ProjectAttachment>();			
		attachmentList.addAll(tp.getAttachmentList());						
		projectManageService.updateProjectAttachmentCode(attachmentList);

		return mapping.findForward("documentInput");
	}

	/**
	 * 保存项目基本信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward saveProjectBase(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String uploadPath = request.getSession().getServletContext().getInitParameter("uploadFilePath");
		if (!uploadPath.endsWith("\\")) {
			uploadPath = uploadPath + "\\";
		}

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		projectManageService.saveTenderProject(tp, uploadPath);
		
		ArrayList<ProjectAttachment> attachmentList = new ArrayList<ProjectAttachment>();			
		attachmentList.addAll(tp.getAttachmentList());						
		projectManageService.updateProjectAttachmentCode(attachmentList);
		
		projectManageService.saveProjectManagerAsTeam(tp, commonService);

		projectManageService.calculateProjectTimeSchedule(tp);
		projectManageService.calculateProjectDoSchedule(tp);

		if (tp.getOldManager() != null) {
			projectManageService.deleteProjectMember(tp, tp.getOldManager());
			tp.setOldManager(null);
		}

		this.prepareMetadata(request);

		return mapping.findForward("baseEdit");
	}

	/**
	 * 保存项目成员信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward saveTeamMember(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		projectManageService.saveProjectTeam(tp);

		this.prepareTeamMetadata(request);

		return mapping.findForward("teamMemberInput");
	}

	/**
	 * 增加项目成员
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addTeamMember(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String userId = (String) request.getSession().getAttribute("account");

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		ProjectTeam pt = tp.getCurrentProjectTeam();

		ProjectRole pr = commonService.getProjectRoleById(pt.getPtProjectRole());
		pt.setProjectRole(pr);
		pt.setPtJobResponsibility(pr.getPrJobResponsibility());
		pt.setJobResponsibility(pr.getJobResponsibility());
		pt.setPtTenderProject(tp.getTpId());
		pt.setPtCreateUser(userId);

		tp.getTeamMemberList().add(pt);

		tp.setCurrentProjectTeam(projectManageService.createProjectTeam());

		this.prepareTeamMetadata(request);

		return mapping.findForward("teamMemberInput");
	}

	/**
	 * 保存日程计划信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward saveSchedulePlan(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		projectManageService.saveSchedulePlan(tp);

		this.prepareScheduleDisplayHeader(request, tp);

		return mapping.findForward("schedulePlanInput");
	}

	/**
	 * 保存日程信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward saveSchedule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		projectManageService.saveSchedule(tp);

		this.prepareScheduleDisplayHeader(request, tp);

		return mapping.findForward("scheduleInput");
	}

	private void prepareMetadata(HttpServletRequest request) {
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		request.setAttribute("deptList", user.getCreatePowerDepartmentList());
		
		request.setAttribute("statusList", commonService.getProjectStatusList());
		request.setAttribute("departmentList", commonService.getDepartmentList());		
		request.setAttribute("postList", commonService.getPostList());
	}

	private void prepareTeamMetadata(HttpServletRequest request) {
		request.setAttribute("projectRoleList", commonService.getProjectRoleList());
		request.setAttribute("jobResponsibilityList", commonService.getJobResponsibilityList());
	}

	/**
	 * 重置项目查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward resetSearchTenderProject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		TenderProject searchInfo = projectManageService.createTenderProject();
		searchInfo.setTpStatus(null);
		
		dform.set("searchProjectInfo", searchInfo);

		return this.searchTenderProject(mapping, dform, request, response);
	}

	/**
	 * 查询项目信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward searchTenderProject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String page = request.getParameter("pager.offset");
		TenderProject searchInfo = (TenderProject) dform.get("searchProjectInfo");

		Account user = (Account) request.getSession().getAttribute("accountPerson");

		Object[] args = { page, searchInfo, user };

		List<TenderProject> gList = projectManageService.searchProjectList(args);
		Integer count = projectManageService.searchProjectCount(args);
		projectManageService.calculateProjectTimeSchedule(gList);
		request.setAttribute("projectList", gList);
		request.setAttribute("projectCount", count);

		this.prepareMetadata(request);

		return mapping.findForward("list");
	}
	
	/**
	 * 重置立项备案查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward resetSearchProjectApprovalRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		ProjectApprovalRecord searchInfo = projectManageService.createProjectApprovalRecord();
		searchInfo.setParStatus(null);
		
		dform.set("projectApprovalRecordInfo", searchInfo);

		return this.searchProjectApprovalRecord(mapping, dform, request, response);
	}

	/**
	 * 立项备案查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward searchProjectApprovalRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String page = request.getParameter("pager.offset");
		ProjectApprovalRecord searchInfo = (ProjectApprovalRecord) dform.get("projectApprovalRecordInfo");

		Account user = (Account) request.getSession().getAttribute("accountPerson");

		Object[] args = { page, searchInfo, user };

		List<ProjectApprovalRecord> gList = projectManageService.searchProjectApprovalRecordList(args);
		Integer count = projectManageService.searchProjectApprovalRecordCount(args);
		
		request.setAttribute("projectApprovalRecordList", gList);
		request.setAttribute("projectApprovalRecordCount", count);

		return mapping.findForward("projectApprovalRecordList");
	}
	
	/**
	 * 重置立项评审查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward resetSearchProjectApprovalCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		ProjectApprovalRecord searchInfo = projectManageService.createProjectApprovalRecord();
		searchInfo.setParStatus(null);
		
		dform.set("projectApprovalRecordInfo", searchInfo);

		return this.searchProjectApprovalCheck(mapping, dform, request, response);
	}

	/**
	 * 立项评审查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward searchProjectApprovalCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String page = request.getParameter("pager.offset");
		ProjectApprovalRecord searchInfo = (ProjectApprovalRecord) dform.get("projectApprovalRecordInfo");
		
		Account user = (Account) request.getSession().getAttribute("accountPerson");

		Object[] args = { page, searchInfo, user };

		List<ProjectApprovalRecord> gList = projectManageService.searchProjectApprovalCheckList(args);
		Integer count = projectManageService.searchProjectApprovalCheckCount(args);
		
		request.setAttribute("projectApprovalRecordList", gList);
		request.setAttribute("projectApprovalRecordCount", count);

		return mapping.findForward("projectApprovalCheckList");
	}

	/**
	 * 重置台账统计查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward resetSearchAccountStatistics(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		TenderProject searchInfo = projectManageService.createTenderProject();
		searchInfo.setTpStatus(null);
		searchInfo.setCreateUser(new Account());
		dform.set("projectInfo", searchInfo);
		
		return this.searchAccountStatistics(mapping, dform, request, response);
	}

	/**
	 * 台账统计查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward searchAccountStatistics(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String page = request.getParameter("pager.offset");
		TenderProject searchInfo = (TenderProject) dform.get("projectInfo");

		Account user = (Account) request.getSession().getAttribute("accountPerson");

		Object[] args = { page, searchInfo, user };

		List<TenderProject> gList = projectManageService.searchAccountStatistics(args);

		for(TenderProject tp:gList)
		{
			this.calculateSaleContractAmount(tp);
			this.calculatePurchaseContractAmount(tp);
			this.calculateFeeTaxAmount(tp);
		}
				
		request.setAttribute("projectList", gList);
		request.setAttribute("totalAccountSaleContract", this.calculateSaleContractAmount(gList));
		request.setAttribute("totalAccountPurchaseContract", this.calculatePurchaseContractAmount(gList));
		request.setAttribute("totalAccountFeeTax", this.calculateFeeTaxAmount(gList));
		
		this.prepareMetadata(request);

		return mapping.findForward("accountStatistics");
	}

	/**
	 * 删除项目信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteTenderProject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		String id = request.getParameter("id");
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		
		TenderProject tp = projectManageService.getProjectById(Integer.parseInt(id));
		if(!user.getId().equals("0") && !user.getCreatePowerDepartmentList().contains(tp.getDepartment()))
		{
			ActionMessages errors = new ActionMessages();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.nopower"));
			this.saveErrors(request, errors);			
		}
		else
		{
			projectManageService.deleteTenderProject(tp);
		}	

		return this.searchTenderProject(mapping, form, request, response);
	}
	
	public ActionForward cancelDeletedTenderProject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		String id = request.getParameter("id");
		
		TenderProject tp = projectManageService.getProjectById(Integer.parseInt(id));
		tp.setTpStatus(ProjectStatus.PROJECT_STATUS_ACT);
		tenderManageService.updateTenderProject(tp);
		
		return this.searchTenderProject(mapping, form, request, response);
	}
	
	/**
	 * 删除立项备案记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteProjectApprovalRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		String id = request.getParameter("id");
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		ProjectApprovalRecord par = projectManageService.getProjectApprovalRecordById(Integer.parseInt(id));
		
		if(!user.getId().equals("0") && !user.getCreatePowerDepartmentList().contains(par.getDepartment()))
		{
			ActionMessages errors = new ActionMessages();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.nopower"));
			this.saveErrors(request, errors);			
		}
		else
		{
			projectManageService.deleteProjectApprovalRecord(par);
		}	
		

		return this.searchProjectApprovalRecord(mapping, form, request, response);
	}
	
	public ActionForward cancelDeletedProjectApprovalRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		String id = request.getParameter("id");
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		ProjectApprovalRecord par = projectManageService.getProjectApprovalRecordById(Integer.parseInt(id));
		
		par.setParStatus(ProjectApprovalRecord.PAR_STATUS_INIT);
		projectManageService.updateProjectApprovalRecord(par);		

		return this.searchProjectApprovalRecord(mapping, form, request, response);
	}

	/**
	 * 编辑项目信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editTenderProject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String id = request.getParameter("id");
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		
		TenderProject tp = projectManageService.getProjectById(Integer.parseInt(id));
		
		if(!user.getCreatePowerDepartmentList().contains(tp.getDepartment()))
		{			
			ActionMessages errors = new ActionMessages();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.nopower"));
			this.saveErrors(request, errors);		
			
			return this.searchTenderProject(mapping, dform, request, response);
		}

		projectManageService.calculateProjectTimeSchedule(tp);
		projectManageService.calculateProjectDoSchedule(tp);
		if(tp.getManager() == null)
		{
			tp.setManager(new Account());
		}
		if(tp.getMarketManager() == null)
		{
			tp.setMarketManager(new Account());
		}

		dform.set("projectInfo", tp);

		String userId = (String) request.getSession().getAttribute("account");
		if (!userId.equals("0")) {
			for (ProjectTeam pt : tp.getTeamMemberList()) {
				if (pt.getPtAccount().equals(userId)) {
					dform.set("teamMemberInfo", pt);
				}
			}
		}

		this.prepareMetadata(request);

		return mapping.findForward("baseEdit");
	}
	
	/**
	 * 编辑立项备案记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editProjectApprovalRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String id = request.getParameter("id");
		Account user = (Account) request.getSession().getAttribute("accountPerson");
				
		ProjectApprovalRecord par = projectManageService.getProjectApprovalRecordById(Integer.parseInt(id));
		if(!user.getCreatePowerDepartmentList().contains(par.getDepartment()))
		{			
			ActionMessages errors = new ActionMessages();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.nopower"));
			this.saveErrors(request, errors);		
			
			return this.searchProjectApprovalRecord(mapping, form, request, response);
		}
		
		if(par.getManager() == null)
		{
			par.setManager(new Account());
		}
		dform.set("projectApprovalRecordInfo", par);
		
		this.prepareMetadata(request);

		return mapping.findForward("projectApprovalRecordEdit");
	}
	
	/**
	 * 编辑立项评审
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editProjectApprovalCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String id = request.getParameter("id");
		Account user = (Account) request.getSession().getAttribute("accountPerson");
				
		ProjectApprovalRecord par = projectManageService.getProjectApprovalRecordById(Integer.parseInt(id));
		if(!user.getCreatePowerDepartmentList().contains(par.getDepartment()))
		{			
			ActionMessages errors = new ActionMessages();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.nopower"));
			this.saveErrors(request, errors);		
			
			return this.searchProjectApprovalCheck(mapping, form, request, response);
		}
		
		if(par.getManager() == null)
		{
			par.setManager(new Account());
		}
		if(par.getParCommitDept() == null)
		{
			par.setParCommitDept(par.getParDept());
		}
		dform.set("projectApprovalRecordInfo", par);
		
		this.prepareMetadata(request);

		return mapping.findForward("projectApprovalTenderEdit");
	}
	
	/**
	 * 显示项目信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward displayTenderProject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		String id = request.getParameter("id");

		TenderProject tp = projectManageService.getProjectById(Integer.parseInt(id));

		projectManageService.calculateProjectTimeSchedule(tp);
		projectManageService.calculateProjectDoSchedule(tp);
		dform.set("projectInfo", tp);

		return mapping.findForward("baseDisplay");
	}
	
	/**
	 * 显示立项备案记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward displayProjectApprovalRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		String id = request.getParameter("id");

		ProjectApprovalRecord par = projectManageService.getProjectApprovalRecordById(Integer.parseInt(id));
		if(par.getManager() == null)
		{
			par.setManager(new Account());
		}
		request.setAttribute("projectApprovalRecordInfo", par);
		
		List<CheckTask> taskList = projectManageService.getAllStepCheckTask(par);
		projectManageService.retrieveTaskCheckableUserList(taskList);
		
		request.setAttribute("taskList", taskList);

		return mapping.findForward("projectApprovalRecordDisplay");
	}

	/**
	 * 编辑项目合同
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editProjectContract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		String id = request.getParameter("id");
		
		if (id != null && !id.isEmpty()) {
			for (ProjectContract pc : tp.getProjectContractList()) {
				if (pc.getPcId().equals(Integer.parseInt(id))) {
					tp.setProjectContract(pc);
					break;
				}
			}
		}

		return mapping.findForward("projectContractInput");
	}
	
	/**
	 * 显示项目合同信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward readProjectContract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		String id = request.getParameter("id");
		for (ProjectContract pc : tp.getProjectContractList()) {
			if (pc.getPcId().equals(Integer.parseInt(id))) {
				tp.setProjectContract(pc);
				break;
			}
		}
			
		List<CheckTask> taskList = projectManageService.getAllStepCheckTask(tp.getProjectContract());
		projectManageService.retrieveTaskCheckableUserList(taskList);
		request.setAttribute("taskList", taskList);

		return mapping.findForward("projectContractInfo");
	}
	
	/**
	 * 编辑项目合同管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editProjectContractManage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		
		return mapping.findForward("projectContract");
	}

	/**
	 * 显示项目合同管理页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward displayProjectContractManage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		
		return mapping.findForward("projectContractDisplay");
	}

	/**
	 * 删除项目合同
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteProjectContract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		String id = request.getParameter("id");
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		ProjectContract deletePc = null;
		for (ProjectContract pc : tp.getProjectContractList()) {
			if (pc.getPcId().equals(Integer.valueOf(id))) {
				deletePc = pc;
				break;
			}
		}

		if (deletePc != null) {
			deletePc.setPcStatus(CommonService.DELETE_FLAG);
			projectManageService.updateProjectContract(deletePc);
			tp.getProjectContractList().remove(deletePc);
		}

		return mapping.findForward("projectContract");
	}

	/**
	 * 保存项目合同
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward saveProjectContract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String uploadPath = request.getSession().getServletContext().getInitParameter("uploadFilePath");
		if (!uploadPath.endsWith("\\")) {
			uploadPath = uploadPath + "\\";
		}

		TenderProject tp = (TenderProject) dform.get("projectInfo");		

		projectManageService.saveProjectContract(tp, uploadPath);
		
		ArrayList<ProjectAttachment> attachmentList = new ArrayList<ProjectAttachment>();		
		for(ProjectContract pc:tp.getProjectContractList())
		{
			attachmentList.addAll(pc.getAttachmentList());		
		}			
		projectManageService.updateProjectAttachmentCode(attachmentList);
		
		return mapping.findForward("projectContract");
	}

	/**
	 * 创建项目合同
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward createProjectContract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		ProjectContract pc = new ProjectContract();
		pc.setPcCategory(ProjectContract.Contract_CATEGORY_PROJECT);
		pc.setPcStatus(ProjectContract.Contract_Status_INIT);
		pc.setPcProject(tp.getTpId());
		pc.setTenderProject(tp);
		pc.setCustomer(new Customer());
		tp.setProjectContract(pc);		

		return mapping.findForward("projectContractInput");
	}
	
	/**
	 * 创建台账销售合同
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward createAccountSaleContract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		
		AccountSaleContract sc = new AccountSaleContract();
		sc.setAscProject(tp.getTpId());
		sc.setAscCreateUser(user.getId());
		
		tp.setAccountSaleContract(sc);

		return mapping.findForward("saleContractInput");
	}
	
	/**
	 * 创建台账采购合同
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward createAccountPurchaseContract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		
		AccountPurchaseContract sc = new AccountPurchaseContract();
		sc.setApcProject(tp.getTpId());
		sc.setApcCreateUser(user.getId());
		
		tp.setAccountPurchaseContract(sc);

		return mapping.findForward("purchaseContractInput");
	}
	
	/**
	 * 创建台账费税
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward createAccountFeeTax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		Account user = (Account) request.getSession().getAttribute("accountPerson");
		
		AccountFeeTax sc = new AccountFeeTax();
		sc.setAftProject(tp.getTpId());
		sc.setAftCreateUser(user.getId());
		
		tp.setAccountFeeTax(sc);

		return mapping.findForward("feeTaxInput");
	}
		
	/**
	 * 显示台账销售合同
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward displayAccountSaleContract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		String id = request.getParameter("id");
		
		AccountSaleContract asc = projectManageService.getAccountSaleContractById(Integer.valueOf(id));
		request.setAttribute("asc", asc);

		return mapping.findForward("saleContractDisplay");
	}
	
	/**
	 * 显示用户账号信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward displayUserAccountInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		String id = request.getParameter("id");
		
		Account userAccount = commonService.getAccountById(id);
		request.setAttribute("userAccount", userAccount);

		return mapping.findForward("userAccountDisplay");
	}
	
	/**
	 * 编辑台账销售合同
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editAccountSaleContract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String id = request.getParameter("id");
		
		for(AccountSaleContract sc:tp.getAccountSaleContractList())
		{
			if(Integer.valueOf(id).equals(sc.getAscId()))
			{
				tp.setAccountSaleContract(sc);
				break;
			}
		}		

		return mapping.findForward("saleContractInput");
	}
	
	/**
	 * 显示台账销售合同付款信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward displayAccountSaleContractPayment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String id = request.getParameter("id");
		
		for(AccountSaleContract sc:tp.getAccountSaleContractList())
		{
			if(Integer.valueOf(id).equals(sc.getAscId()))
			{
				tp.setAccountSaleContract(sc);
				break;
			}
		}		

		return mapping.findForward("saleContractPaymentDisplay");
	}
	
	/**
	 * 编辑台账采购合同
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editAccountPurchaseContract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String id = request.getParameter("id");
		
		for(AccountPurchaseContract sc:tp.getAccountPurchaseContractList())
		{
			if(Integer.valueOf(id).equals(sc.getApcId()))
			{
				tp.setAccountPurchaseContract(sc);
				break;
			}
		}		

		return mapping.findForward("purchaseContractInput");
	}
	
	/**
	 * 显示台账采购合同付款信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward displayAccountPurchaseContractPayment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String id = request.getParameter("id");
		
		for(AccountPurchaseContract sc:tp.getAccountPurchaseContractList())
		{
			if(Integer.valueOf(id).equals(sc.getApcId()))
			{
				tp.setAccountPurchaseContract(sc);
				break;
			}
		}		

		return mapping.findForward("purchaseContractPaymentDisplay");
	}
	
	/**
	 * 编辑台账费税
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editAccountFeeTax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String id = request.getParameter("id");
		
		for(AccountFeeTax sc:tp.getAccountFeeTaxList())
		{
			if(Integer.valueOf(id).equals(sc.getAftId()))
			{
				tp.setAccountFeeTax(sc);
				break;
			}
		}		

		return mapping.findForward("feeTaxInput");
	}
	
	/**
	 * 删除台账销售合同
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteAccountSaleContract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String id = request.getParameter("id");
		
		for(AccountSaleContract sc:tp.getAccountSaleContractList())
		{
			if(sc.getAscId().equals(Integer.parseInt(id)))
			{
				sc.setAscStatus(CommonService.DELETE_FLAG);
				break;
			}
		}		
		
		this.calculateSaleContractAmount(tp);
		
		request.setAttribute("tabpageId", "saleContract");
		
		return mapping.findForward("accountManage");
	}
	
	/**
	 * 删除台账采购合同
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteAccountPurchaseContract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String id = request.getParameter("id");
		
		for(AccountPurchaseContract sc:tp.getAccountPurchaseContractList())
		{
			if(sc.getApcId().equals(Integer.parseInt(id)))
			{
				sc.setApcStatus(CommonService.DELETE_FLAG);
				break;
			}
		}		
		
		this.calculatePurchaseContractAmount(tp);
		
		request.setAttribute("tabpageId", "purchaseContract");

		return mapping.findForward("accountManage");
	}
	
	/**
	 * 删除台账费税
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteAccountFeeTax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String id = request.getParameter("id");
		
		for(AccountFeeTax sc:tp.getAccountFeeTaxList())
		{
			if(sc.getAftId().equals(Integer.parseInt(id)))
			{
				sc.setAftStatus(CommonService.DELETE_FLAG);
				break;
			}
		}		
		
		this.calculateFeeTaxAmount(tp);
		
		request.setAttribute("tabpageId", "feeTax");
		
		return mapping.findForward("accountManage");
	}
	
	/**
	 * 保存台账销售合同
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward saveAccountSaleContract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");		
		projectManageService.saveAccountSaleContract(tp);
		
		request.setAttribute("tabpageId", "saleContract");
		
		return mapping.findForward("accountManage");
	}
	
	/**
	 * 保存台账采购合同
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward saveAccountPurchaseContract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");		
		projectManageService.saveAccountPurchaseContract(tp);
		
		request.setAttribute("tabpageId", "purchaseContract");
		
		return mapping.findForward("accountManage");
	}
	
	/**
	 * 保存台账费税
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward saveAccountFeeTax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");		
		projectManageService.saveAccountFeeTax(tp);
		
		request.setAttribute("tabpageId", "feeTax");
		
		return mapping.findForward("accountManage");
	}
	
	/**
	 * 刷新项目合同页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refreshProjectContract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		return mapping.findForward("projectContract");
	}
	/**
	 * 刷新项目合同输入页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refreshProjectContractInput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		return mapping.findForward("projectContractInput");
	}

	/**
	 * 刷新项目月度预算页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refreshProjectMonthBudget(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String month = tp.getCurrentMonth();

		tp = projectManageService.getProjectById(tp.getTpId());

		tp.setCurrentMonth(month);
		if (tp.getCurrentMonthBudget() == null || !month.equals(tp.getCurrentMonthBudget().getPbMonth())) {
			for (ProjectBudget pb : tp.getMonthBudgetList()) {
				if (month.equals(pb.getPbMonth())) {
					tp.setCurrentMonthBudget(pb);
					break;
				}
			}
		}

		dform.set("projectInfo", tp);

		return mapping.findForward("monthBudget");
	}

	/**
	 * 编辑项目月度预算
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editProjectMonthBudget(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String month = tp.getCurrentMonth();

		// for check
		if (tp.getTpId() == null) {
			String id = request.getParameter("id");
			month = request.getParameter("month");
			tp = projectManageService.getProjectById(Integer.parseInt(id));

			dform.set("projectInfo", tp);
		} else {
			tp = tenderManageService.getTenderProjectById(tp.getTpId());
		}

		tp.setCurrentMonth(month);
		// -------------
		if (tp.getCurrentMonthBudget() == null || !month.equals(tp.getCurrentMonthBudget().getPbMonth())) {
			tp.setCurrentMonthBudget(null);
			for (ProjectBudget pb : tp.getMonthBudgetList()) {
				if (month.equals(pb.getPbMonth())) {
					tp.setCurrentMonthBudget(pb);
				}
			}

			if (tp.getCurrentMonthBudget() == null) {
				ProjectBudget pb = tenderManageService.modifyCreateBudgetItems();
				pb.setPbProject(tp.getTpId());
				pb.setPbMonth(month);
				pb.setPbType(ProjectBudget.BUDGET_TYPE_MONTH_BUDGET);
				tenderManageService.saveProjectBudget(pb);

				tp.setCurrentMonthBudget(pb);
				tp.getMonthBudgetList().add(pb);
			}
		}
		// ----------------

		dform.set("projectInfo", tp);

		return mapping.findForward("monthBudget");
	}

	/**
	 * 刷新项目月度决算页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refreshProjectMonthSettlement(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String month = tp.getCurrentMonth();

		tp = projectManageService.getProjectById(tp.getTpId());

		tp.setCurrentMonth(month);
		// -------------
		if (tp.getCurrentMonthBudget() == null || !month.equals(tp.getCurrentMonthBudget().getPbMonth())) {
			for (ProjectBudget pb : tp.getMonthBudgetList()) {
				if (month.equals(pb.getPbMonth())) {
					tp.setCurrentMonthBudget(pb);
					break;
				}
			}
		}
		// ----------------

		// -------------
		if (tp.getCurrentMonthSettlement() == null || !month.equals(tp.getCurrentMonthSettlement().getPbMonth())) {
			for (ProjectBudget pb : tp.getMonthSettlementList()) {
				if (month.equals(pb.getPbMonth())) {
					tp.setCurrentMonthSettlement(pb);
					break;
				}
			}
		}
		// ----------------

		ShortfallAmount shortfallAmount = new ShortfallAmount(tp.getCurrentMonthBudget(), tp.getCurrentMonthSettlement());
		tp.setShortfallAmount(shortfallAmount);

		dform.set("projectInfo", tp);

		return mapping.findForward("monthSettlement");
	}

	/**
	 * 编辑项目月度决算
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editProjectMonthSettlement(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String month = tp.getCurrentMonth();

		tp = tenderManageService.getTenderProjectById(tp.getTpId());

		tp.setCurrentMonth(month);
		// -------------
		if (tp.getCurrentMonthBudget() == null || !month.equals(tp.getCurrentMonthBudget().getPbMonth())) {
			tp.setCurrentMonthBudget(null);
			for (ProjectBudget pb : tp.getMonthBudgetList()) {
				if (month.equals(pb.getPbMonth())) {
					tp.setCurrentMonthBudget(pb);
				}
			}

			if (tp.getCurrentMonthBudget() == null) {
				ProjectBudget pb = tenderManageService.modifyCreateBudgetItems();
				pb.setPbProject(tp.getTpId());
				pb.setPbMonth(month);
				pb.setPbType(ProjectBudget.BUDGET_TYPE_MONTH_BUDGET);
				tenderManageService.saveProjectBudget(pb);

				tp.setCurrentMonthBudget(pb);
				tp.getMonthBudgetList().add(pb);
			}
		}
		// ----------------

		// -------------
		if (tp.getCurrentMonthSettlement() == null || !month.equals(tp.getCurrentMonthSettlement().getPbMonth())) {
			tp.setCurrentMonthSettlement(null);
			for (ProjectBudget pb : tp.getMonthSettlementList()) {
				if (month.equals(pb.getPbMonth())) {
					tp.setCurrentMonthSettlement(pb);
				}
			}

			if (tp.getCurrentMonthSettlement() == null) {
				ProjectBudget pb = tenderManageService.modifyCreateBudgetItems();
				pb.setPbProject(tp.getTpId());
				pb.setPbMonth(month);
				pb.setPbType(ProjectBudget.BUDGET_TYPE_MONTH_SETTLEMENT);
				tenderManageService.saveProjectBudget(pb);

				tp.setCurrentMonthSettlement(pb);
				tp.getMonthBudgetList().add(pb);
			}
		}
		// ----------------

		ShortfallAmount shortfallAmount = new ShortfallAmount(tp.getCurrentMonthBudget(), tp.getCurrentMonthSettlement());
		tp.setShortfallAmount(shortfallAmount);

		dform.set("projectInfo", tp);

		return mapping.findForward("monthSettlement");
	}

	/**
	 * 编辑项目基本信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editProjectBase(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		
		if(tp.getMarketManager() == null)
		{
			tp.setMarketManager(new Account());
		}

		this.prepareMetadata(request);

		return mapping.findForward("baseEdit");
	}

	/**
	 * 显示项目基本信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward displayProjectBase(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		this.prepareMetadata(request);

		return mapping.findForward("baseDisplay");
	}

	/**
	 * 编辑项目成员
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editProjectTeamMember(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String userId = (String) request.getSession().getAttribute("account");

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		ProjectTeam pt = projectManageService.createProjectTeam();
		pt.setPtCreateUser(userId);
		pt.setPtTenderProject(tp.getTpId());

		tp.setCurrentProjectTeam(pt);

		this.prepareTeamMetadata(request);

		return mapping.findForward("teamMemberInput");
	}

	/**
	 * 显示项目成员
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward displayProjectTeamMember(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String userId = (String) request.getSession().getAttribute("account");

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		ProjectTeam pt = projectManageService.createProjectTeam();
		pt.setPtCreateUser(userId);
		pt.setPtTenderProject(tp.getTpId());

		tp.setCurrentProjectTeam(pt);

		this.prepareTeamMetadata(request);

		return mapping.findForward("teamMemberDisplay");
	}

	/**
	 * 删除项目成员
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteProjectTeamMember(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		String id = request.getParameter("id");

		for (ProjectTeam t : tp.getTeamMemberList()) {
			if (t.getPtId().equals(Integer.valueOf(id))) {
				t.setPtFlag(CommonService.DELETE_FLAG);
				t.setModified(true);

				break;
			}
		}

		this.prepareTeamMetadata(request);

		return mapping.findForward("teamMemberInput");
	}

	/**
	 * 删除项目成员
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteProjectTeamMemberByIndex(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		String index = request.getParameter("index");

		tp.getTeamMemberList().remove(Integer.parseInt(index));

		this.prepareTeamMetadata(request);

		return mapping.findForward("teamMemberInput");
	}

	/**
	 * 编辑项目日程计划
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editProjectSchedulePlan(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		this.prepareScheduleDisplayHeader(request, tp);

		return mapping.findForward("schedulePlanInput");
	}

	/**
	 * 显示项目日程计划
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward displayProjectSchedulePlan(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		this.prepareScheduleDisplayHeader(request, tp);

		return mapping.findForward("schedulePlanDisplay");
	}

	/**
	 * 显示回退日程
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward displayScheduleGoBack(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		Calendar c = Calendar.getInstance();
		c.setTime(tp.getScheduleStageList().get(0).getDisplayBeginDate());

		c.add(Calendar.MONTH, -1);

		for (ProjectScheduleStage pss : tp.getScheduleStageList()) {
			pss.setDisplayBeginDate(c.getTime());
			for (ProjectScheduleTask pst : pss.getScheduleTaskList()) {
				pst.setDisplayBeginDate(c.getTime());
			}
		}

		this.prepareScheduleDisplayHeader(request, tp);

		return mapping.findForward("schedulePlanInput");
	}

	/**
	 * 显示前进日程
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward displayScheduleGoOn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		Calendar c = Calendar.getInstance();
		c.setTime(tp.getScheduleStageList().get(0).getDisplayBeginDate());

		c.add(Calendar.MONTH, 1);

		for (ProjectScheduleStage pss : tp.getScheduleStageList()) {
			pss.setDisplayBeginDate(c.getTime());
			for (ProjectScheduleTask pst : pss.getScheduleTaskList()) {
				pst.setDisplayBeginDate(c.getTime());
			}
		}

		this.prepareScheduleDisplayHeader(request, tp);

		return mapping.findForward("schedulePlanInput");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward displayScheduleGoBackForFinish(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		Calendar c = Calendar.getInstance();
		c.setTime(tp.getScheduleStageList().get(0).getDisplayBeginDate());

		c.add(Calendar.MONTH, -1);

		for (ProjectScheduleStage pss : tp.getScheduleStageList()) {
			pss.setDisplayBeginDate(c.getTime());
			for (ProjectScheduleTask pst : pss.getScheduleTaskList()) {
				pst.setDisplayBeginDate(c.getTime());
			}
		}

		this.prepareScheduleDisplayHeader(request, tp);

		return mapping.findForward("schedulePlanDisplay");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward displayScheduleGoOnForFinish(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		Calendar c = Calendar.getInstance();
		c.setTime(tp.getScheduleStageList().get(0).getDisplayBeginDate());

		c.add(Calendar.MONTH, 1);

		for (ProjectScheduleStage pss : tp.getScheduleStageList()) {
			pss.setDisplayBeginDate(c.getTime());
			for (ProjectScheduleTask pst : pss.getScheduleTaskList()) {
				pst.setDisplayBeginDate(c.getTime());
			}
		}

		this.prepareScheduleDisplayHeader(request, tp);

		return mapping.findForward("schedulePlanDisplay");
	}

	/**
	 * 展开日程-阶段
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward expendScheduleStage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		String id = request.getParameter("id");
		for (ProjectScheduleStage pss : tp.getScheduleStageList()) {
			if (pss.getPssId().equals(Integer.valueOf(id))) {
				pss.setTaskExpendStatus("+");
			}
		}

		this.prepareScheduleDisplayHeader(request, tp);

		return mapping.findForward("schedulePlanInput");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward expendScheduleStageForFinish(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		String id = request.getParameter("id");
		for (ProjectScheduleStage pss : tp.getScheduleStageList()) {
			if (pss.getPssId().equals(Integer.valueOf(id))) {
				pss.setTaskExpendStatus("+");
			}
		}

		this.prepareScheduleDisplayHeader(request, tp);

		return mapping.findForward("schedulePlanDisplay");
	}

	/**
	 * 展开日程-任务
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward expendScheduleTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		String id = request.getParameter("id");
		for (ProjectScheduleStage pss : tp.getScheduleStageList()) {
			for (ProjectScheduleTask pst : pss.getScheduleTaskList()) {
				if (pst.getPstId().equals(Integer.valueOf(id))) {
					pst.setRecordExpendStatus("+");
				}
			}
		}

		this.prepareScheduleDisplayHeader(request, tp);

		return mapping.findForward("scheduleInput");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward expendScheduleTaskForFinish(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		String id = request.getParameter("id");
		for (ProjectScheduleStage pss : tp.getScheduleStageList()) {
			for (ProjectScheduleTask pst : pss.getScheduleTaskList()) {
				if (pst.getPstId().equals(Integer.valueOf(id))) {
					pst.setRecordExpendStatus("+");
				}
			}
		}

		this.prepareScheduleDisplayHeader(request, tp);

		return mapping.findForward("scheduleDisplay");
	}

	/**
	 * 合并日程-阶段
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward collectScheduleStage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		String id = request.getParameter("id");
		for (ProjectScheduleStage pss : tp.getScheduleStageList()) {
			if (pss.getPssId().equals(Integer.valueOf(id))) {
				pss.setTaskExpendStatus("-");
			}
		}

		this.prepareScheduleDisplayHeader(request, tp);

		return mapping.findForward("schedulePlanInput");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward collectScheduleStageForFinish(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		String id = request.getParameter("id");
		for (ProjectScheduleStage pss : tp.getScheduleStageList()) {
			if (pss.getPssId().equals(Integer.valueOf(id))) {
				pss.setTaskExpendStatus("-");
			}
		}

		this.prepareScheduleDisplayHeader(request, tp);

		return mapping.findForward("schedulePlanDisplay");
	}

	/**
	 * 合并日程-任务
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward collectScheduleTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		String id = request.getParameter("id");
		for (ProjectScheduleStage pss : tp.getScheduleStageList()) {
			for (ProjectScheduleTask pst : pss.getScheduleTaskList()) {
				if (pst.getPstId().equals(Integer.valueOf(id))) {
					pst.setRecordExpendStatus("-");
				}
			}
		}

		this.prepareScheduleDisplayHeader(request, tp);

		return mapping.findForward("scheduleInput");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward collectScheduleTaskForFinish(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		String id = request.getParameter("id");
		for (ProjectScheduleStage pss : tp.getScheduleStageList()) {
			for (ProjectScheduleTask pst : pss.getScheduleTaskList()) {
				if (pst.getPstId().equals(Integer.valueOf(id))) {
					pst.setRecordExpendStatus("-");
				}
			}
		}

		this.prepareScheduleDisplayHeader(request, tp);

		return mapping.findForward("scheduleDisplay");
	}

	/**
	 * 准备日程表头
	 * @param request
	 * @param tp
	 */
	private void prepareScheduleDisplayHeader(HttpServletRequest request, TenderProject tp) {
		ArrayList<String> thList = new ArrayList<String>();
		Date date = new Date();
		Calendar c = Calendar.getInstance();

		if (tp.getScheduleStageList().isEmpty()) {
			date = CommonServiceImpl.transformDisplayDate(date);
		} else {
			date = tp.getScheduleStageList().get(0).getDisplayBeginDate();
		}

		c.setTime(date);
		for (int i = 0; i < 12; i++) {
			String th = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());
			thList.add(th.substring(0, th.lastIndexOf("-")));
			c.add(Calendar.MONTH, 1);
		}

		request.getSession().setAttribute("tableHeader", thList);
	}

	/**
	 * 编辑日程-阶段
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editScheduleStage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		String id = request.getParameter("id");
		for (ProjectScheduleStage s : tp.getScheduleStageList()) {
			if (s.getPssId().equals(Integer.valueOf(id))) {
				tp.setCurrentScheduleStage(s);
				break;
			}
		}

		return mapping.findForward("addScheduleStage");
	}

	/**
	 * 编辑日程-任务
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editScheduleTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		String id = request.getParameter("id");
		for (ProjectScheduleStage s : tp.getScheduleStageList()) {
			for (ProjectScheduleTask t : s.getScheduleTaskList()) {
				if (t.getPstId().equals(Integer.valueOf(id))) {
					tp.setCurrentScheduleStage(s);
					s.setCurrentScheduleTask(t);
					break;
				}
			}
		}

		this.prepareTaskList(request, tp);

		return mapping.findForward("addScheduleTask");
	}

	/**
	 * 追加任务实现记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addTaskImplementRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String userId = (String) request.getSession().getAttribute("account");
		Account account = (Account) request.getSession().getAttribute("accountPerson");

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		String id = request.getParameter("id");
		for (ProjectScheduleStage s : tp.getScheduleStageList()) {
			for (ProjectScheduleTask t : s.getScheduleTaskList()) {
				if (t.getPstId().equals(Integer.valueOf(id))) {
					tp.setCurrentScheduleStage(s);
					s.setCurrentScheduleTask(t);

					TaskImplementRecord tir = projectManageService.createTaskImplementRecord();
					tir.setTirCreateUser(userId);
					tir.setCreateUser(account);

					t.setCurrentImplementRecord(tir);
					break;
				}
			}
		}

		return mapping.findForward("addTaskImplementRecord");
	}

	/**
	 * 编辑任务实现记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editTaskImplementRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		String id = request.getParameter("id");
		for (ProjectScheduleStage s : tp.getScheduleStageList()) {
			for (ProjectScheduleTask t : s.getScheduleTaskList()) {
				for (TaskImplementRecord r : t.getImplementRecordList()) {
					if (r.getTirId().equals(Integer.valueOf(id))) {
						tp.setCurrentScheduleStage(s);
						s.setCurrentScheduleTask(t);
						t.setCurrentImplementRecord(r);

						break;
					}
				}
			}
		}

		return mapping.findForward("addTaskImplementRecord");
	}

	/**
	 * 删除任务实现记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteTaskImplementRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String id = request.getParameter("id");

		for (ProjectScheduleStage pss : tp.getScheduleStageList()) {
			for (ProjectScheduleTask pst : pss.getScheduleTaskList()) {
				for (TaskImplementRecord r : pst.getImplementRecordList()) {
					if (r.getTirId().equals(Integer.valueOf(id))) {
						r.setTirFlag(CommonService.DELETE_FLAG);
						r.setModified(true);

						pst.setDisplayBeginDate(pst.getDisplayBeginDate());

						break;
					}
				}
			}
		}

		return mapping.findForward("scheduleInput");
	}

	/**
	 * 删除任务实现记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteTaskImplementRecordByIndex(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String stageIndex = request.getParameter("stageIndex");
		String taskIndex = request.getParameter("taskIndex");
		String index = request.getParameter("index");

		tp.getScheduleStageList().get(Integer.parseInt(stageIndex)).getScheduleTaskList().get(Integer.parseInt(taskIndex)).getImplementRecordList().remove(Integer.parseInt(index));

		return mapping.findForward("scheduleInput");
	}

	/**
	 * 提交任务实现记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward confirmTaskImplementRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		TenderProject tp = (TenderProject) dform.get("projectInfo");

		Date createTime = new Date();

		ProjectScheduleTask pst = tp.getCurrentScheduleStage().getCurrentScheduleTask();
		TaskImplementRecord tir = pst.getCurrentImplementRecord();

		if (tir.getTirId() == null) {
			tir.setTirCreateDate(createTime);
			tir.setTirTask(pst.getPstId());
			pst.setPstFinishChangeTime(createTime);
			pst.getImplementRecordList().add(tir);
		} else {
			tir.setModified(true);
		}

		int percent = 0;
		for (TaskImplementRecord r : pst.getImplementRecordList()) {
			percent += r.getTirImplementPercent();
		}

		pst.setPstFinishPercentry(percent);
		pst.setModified(true);

		this.finishImplementRecord(mapping, dform, request, response);

		return mapping.findForward("refreshSchedule");
	}

	/**
	 * 删除日程-阶段
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteScheduleStage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String id = request.getParameter("id");

		for (ProjectScheduleStage s : tp.getScheduleStageList()) {
			if (s.getPssId().equals(Integer.valueOf(id))) {
				s.setPssFlag(CommonService.DELETE_FLAG);
				s.setModified(true);

				break;
			}
		}

		return mapping.findForward("schedulePlanInput");
	}

	/**
	 * 删除日程-阶段
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteScheduleStageByIndex(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String index = request.getParameter("index");

		tp.getScheduleStageList().remove(Integer.parseInt(index));

		return mapping.findForward("schedulePlanInput");
	}

	/**
	 * 删除日程-任务
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteScheduleTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String id = request.getParameter("id");

		for (ProjectScheduleStage pss : tp.getScheduleStageList()) {
			for (ProjectScheduleTask t : pss.getScheduleTaskList()) {
				if (t.getPstId().equals(Integer.parseInt(id))) {
					t.setPstFlag(CommonService.DELETE_FLAG);
					t.setModified(true);

					break;
				}
			}
		}

		return mapping.findForward("schedulePlanInput");
	}

	/**
	 * 删除日程-任务
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteScheduleTaskByIndex(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String stageIndex = request.getParameter("stageIndex");
		String index = request.getParameter("index");

		tp.getScheduleStageList().get(Integer.parseInt(stageIndex)).getScheduleTaskList().remove(Integer.parseInt(index));

		return mapping.findForward("schedulePlanInput");
	}

	/**
	 * 编辑项目日程
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editProjectSchedule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		return mapping.findForward("scheduleInput");
	}

	/**
	 * 显示项目日程
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward displayProjectSchedule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		return mapping.findForward("scheduleDisplay");
	}

	/**
	 * 编辑项目履历
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editProjectRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		return mapping.findForward("RecordList");
	}

	/**
	 * 显示项目履历
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward displayProjectRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		return mapping.findForward("RecordListDisplay");
	}

	/**
	 * 编辑项目预算
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editProjectBudget(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		if (tp.getTpId() == null) {
			String id = request.getParameter("id");
			tp = projectManageService.getProjectById(Integer.parseInt(id));
			dform.set("projectInfo", tp);
		}

		dform.set("projectInfo", tenderManageService.getTenderProjectById(tp.getTpId()));

		return mapping.findForward("budgetInput");
	}

	/**
	 * 刷新项目预算页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refreshProjectBudget(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {

		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		dform.set("projectInfo", tenderManageService.getTenderProjectById(tp.getTpId()));

		return mapping.findForward("budgetInput");
	}

	/**
	 * 保存预算项目
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward saveBudgetItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		BudgetItem item = (BudgetItem)dform.get("budgetItemInfo");
		
		tenderManageService.saveBudgetItem(item);
				
		return mapping.findForward("refreshApplyBudget");		
	}

	/**
	 * 编辑项目投标预算项目
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editDetailTenderBugetItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		String id = request.getParameter("id");

		BudgetItem item = tenderManageService.getBudgetItemById(Integer.valueOf(id));

		if (item == null) {
			ActionMessages errors = new ActionMessages();
			errors.add("UnknowError", new ActionMessage("errors.UnKnowError"));
			saveErrors(request, errors);
			saveToken(request);

			return mapping.findForward("faile");
		}
		dform.set("budgetItemInfo", item);

		return mapping.findForward("editDetailBudgetItem");
	}

	/**
	 * 显示项目预算
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward displayProjectBudget(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		return mapping.findForward("budgetDisplay");
	}

	/**
	 * 编辑项目台账
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editProjectAccount(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		
		this.calculateSaleContractAmount(tp);
		this.calculatePurchaseContractAmount(tp);
		this.calculateFeeTaxAmount(tp);
		
		return mapping.findForward("accountManage");
	}
	
	/**
	 * 显示项目台账
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward displayProjectAccount(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		
		this.calculateSaleContractAmount(tp);
		this.calculatePurchaseContractAmount(tp);
		this.calculateFeeTaxAmount(tp);
		
		return mapping.findForward("projectAccountDisplay");
	}
	
	/**
	 * @param tp
	 */
	private void calculateSaleContractAmount(TenderProject tp)
	{
		AccountSaleContract total = new AccountSaleContract();
		double amount = 0.0d;
		for(AccountSaleContract sc:tp.getAccountSaleContractList())
		{
			if(sc.getAscStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getAscAmount();
			}
		}
		total.setAscAmount(amount);
		
		amount = 0.0d;
		for(AccountSaleContract sc:tp.getAccountSaleContractList())
		{
			if(sc.getAscStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getAscDeviceAmount();
			}
		}
		total.setAscDeviceAmount(amount);
		
		amount = 0.0d;
		for(AccountSaleContract sc:tp.getAccountSaleContractList())
		{
			if(sc.getAscStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getAscInstallAmount();
			}
		}
		total.setAscInstallAmount(amount);
		
		amount = 0.0d;
		for(AccountSaleContract sc:tp.getAccountSaleContractList())
		{
			if(sc.getAscStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getAscServiceAmount();
			}
		}
		total.setAscServiceAmount(amount);
		
		amount = 0.0d;
		for(AccountSaleContract sc:tp.getAccountSaleContractList())
		{
			if(sc.getAscStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getAscAddAmount();
			}
		}
		total.setAscAddAmount(amount);

		amount = 0.0d;
		for(AccountSaleContract sc:tp.getAccountSaleContractList())
		{
			if(sc.getAscStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getAscInvoiceAmount();
			}
		}
		total.setAscInvoiceAmount(amount);
		
		amount = 0.0d;
		for(AccountSaleContract sc:tp.getAccountSaleContractList())
		{
			if(sc.getAscStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getAscGatheringAmount();
			}
		}
		total.setAscGatheringAmount(amount);
		
		tp.setAccountSaleContract(total);
	}
	
	/**
	 * @param tpList
	 * @return
	 */
	private AccountSaleContract calculateSaleContractAmount(List<TenderProject> tpList)
	{
		AccountSaleContract total = new AccountSaleContract();
		double amount = 0.0d;
		for(TenderProject tp:tpList)
		{
			amount += tp.getAccountSaleContract().getAscAmount();			
		}
		total.setAscAmount(amount);
		
		amount = 0.0d;
		for(TenderProject tp:tpList)
		{			
			amount += tp.getAccountSaleContract().getAscDeviceAmount();			
		}
		total.setAscDeviceAmount(amount);
		
		amount = 0.0d;
		for(TenderProject tp:tpList)
		{
			amount += tp.getAccountSaleContract().getAscInstallAmount();			
		}
		total.setAscInstallAmount(amount);
		
		amount = 0.0d;
		for(TenderProject tp:tpList)
		{
			amount += tp.getAccountSaleContract().getAscServiceAmount();
		}
		total.setAscServiceAmount(amount);
		
		amount = 0.0d;
		for(TenderProject tp:tpList)
		{
			amount += tp.getAccountSaleContract().getAscAddAmount();
		}
		total.setAscAddAmount(amount);

		amount = 0.0d;
		for(TenderProject tp:tpList)
		{
			amount += tp.getAccountSaleContract().getAscInvoiceAmount();
		}
		total.setAscInvoiceAmount(amount);
		
		amount = 0.0d;
		for(TenderProject tp:tpList)
		{
			amount += tp.getAccountSaleContract().getAscGatheringAmount();
		}
		total.setAscGatheringAmount(amount);
		
		return total;
	}
	
	/**
	 * @param tp
	 */
	private void calculatePurchaseContractAmount(TenderProject tp)
	{
		AccountPurchaseContract total = new AccountPurchaseContract();
		double amount = 0.0d;
		for(AccountPurchaseContract sc:tp.getAccountPurchaseContractList())
		{
			if(sc.getApcStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getApcAmount();
			}
		}
		total.setApcAmount(amount);
		
		amount = 0.0d;
		for(AccountPurchaseContract sc:tp.getAccountPurchaseContractList())
		{
			if(sc.getApcStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getApcDeviceAmount();
			}
		}
		total.setApcDeviceAmount(amount);
		
		amount = 0.0d;
		for(AccountPurchaseContract sc:tp.getAccountPurchaseContractList())
		{
			if(sc.getApcStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getApcInstallAmount();
			}
		}
		total.setApcInstallAmount(amount);
		
		amount = 0.0d;
		for(AccountPurchaseContract sc:tp.getAccountPurchaseContractList())
		{
			if(sc.getApcStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getApcServiceAmount();
			}
		}
		total.setApcServiceAmount(amount);
		
		amount = 0.0d;
		for(AccountPurchaseContract sc:tp.getAccountPurchaseContractList())
		{
			if(sc.getApcStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getApcAddAmount();
			}
		}
		total.setApcAddAmount(amount);

		amount = 0.0d;
		for(AccountPurchaseContract sc:tp.getAccountPurchaseContractList())
		{
			if(sc.getApcStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getApcInvoiceAmount();
			}
		}
		total.setApcInvoiceAmount(amount);
		
		amount = 0.0d;
		for(AccountPurchaseContract sc:tp.getAccountPurchaseContractList())
		{
			if(sc.getApcStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getApcPaymentAmount();
			}
		}
		total.setApcPaymentAmount(amount);
		
		tp.setAccountPurchaseContract(total);
	}
	
	/**
	 * @param tpList
	 * @return
	 */
	private AccountPurchaseContract calculatePurchaseContractAmount(List<TenderProject> tpList)
	{
		AccountPurchaseContract total = new AccountPurchaseContract();
		double amount = 0.0d;
		for(TenderProject tp:tpList)
		{
			amount += tp.getAccountPurchaseContract().getApcAmount();
		}
		total.setApcAmount(amount);
		
		amount = 0.0d;
		for(TenderProject tp:tpList)
		{
			amount += tp.getAccountPurchaseContract().getApcDeviceAmount();
		}
		total.setApcDeviceAmount(amount);
		
		amount = 0.0d;
		for(TenderProject tp:tpList)
		{
			amount += tp.getAccountPurchaseContract().getApcInstallAmount();
		}
		total.setApcInstallAmount(amount);
		
		amount = 0.0d;
		for(TenderProject tp:tpList)
		{
			amount += tp.getAccountPurchaseContract().getApcServiceAmount();
		}
		total.setApcServiceAmount(amount);
		
		amount = 0.0d;
		for(TenderProject tp:tpList)
		{
			amount += tp.getAccountPurchaseContract().getApcAddAmount();
		}
		total.setApcAddAmount(amount);

		amount = 0.0d;
		for(TenderProject tp:tpList)
		{
			amount += tp.getAccountPurchaseContract().getApcInvoiceAmount();
		}
		total.setApcInvoiceAmount(amount);
		
		amount = 0.0d;
		for(TenderProject tp:tpList)
		{
			amount += tp.getAccountPurchaseContract().getApcPaymentAmount();
		}
		total.setApcPaymentAmount(amount);
		
		return total;
	}
	
	/**
	 * @param tp
	 */
	private void calculateFeeTaxAmount(TenderProject tp)
	{
		AccountFeeTax total = new AccountFeeTax();
		double amount = 0.0d;
		for(AccountFeeTax sc:tp.getAccountFeeTaxList())
		{
			if(sc.getAftStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getAftAdditionalTax();
			}
		}
		total.setAftAdditionalTax(amount);
		
		amount = 0.0d;
		for(AccountFeeTax sc:tp.getAccountFeeTaxList())
		{
			if(sc.getAftStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getAftBusinessTax();
			}
		}
		total.setAftBusinessTax(amount);
		
		amount = 0.0d;
		for(AccountFeeTax sc:tp.getAccountFeeTaxList())
		{
			if(sc.getAftStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getAftFinanceFee();
			}
		}
		total.setAftFinanceFee(amount);
		
		amount = 0.0d;
		for(AccountFeeTax sc:tp.getAccountFeeTaxList())
		{
			if(sc.getAftStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getAftImplementFee();
			}
		}
		total.setAftImplementFee(amount);
		
		amount = 0.0d;
		for(AccountFeeTax sc:tp.getAccountFeeTaxList())
		{
			if(sc.getAftStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getAftPayableTax();
			}
		}
		total.setAftPayableTax(amount);

		amount = 0.0d;
		for(AccountFeeTax sc:tp.getAccountFeeTaxList())
		{
			if(sc.getAftStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getAftPurchaseTax();
			}
		}
		total.setAftPurchaseTax(amount);
		
		amount = 0.0d;
		for(AccountFeeTax sc:tp.getAccountFeeTaxList())
		{
			if(sc.getAftStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getAftSalaryFee();
			}
		}
		total.setAftSalaryFee(amount);
		
		amount = 0.0d;
		for(AccountFeeTax sc:tp.getAccountFeeTaxList())
		{
			if(sc.getAftStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getAftSaleFee();
			}
		}
		total.setAftSaleFee(amount);
		
		amount = 0.0d;
		for(AccountFeeTax sc:tp.getAccountFeeTaxList())
		{
			if(sc.getAftStatus() != CommonService.DELETE_FLAG)
			{
				amount += sc.getAftSaleTax();
			}
		}
		total.setAftSaleTax(amount);
		
		tp.setAccountFeeTax(total);
	}
	
	/**
	 * @param tpList
	 * @return
	 */
	private AccountFeeTax calculateFeeTaxAmount(List<TenderProject> tpList)
	{
		AccountFeeTax total = new AccountFeeTax();
		double amount = 0.0d;
		for(TenderProject tp:tpList)
		{
			amount += tp.getAccountFeeTax().getAftAdditionalTax();
		}
		total.setAftAdditionalTax(amount);
		
		amount = 0.0d;
		for(TenderProject tp:tpList)
		{
			amount += tp.getAccountFeeTax().getAftBusinessTax();
		}
		total.setAftBusinessTax(amount);
		
		amount = 0.0d;
		for(TenderProject tp:tpList)
		{
			amount += tp.getAccountFeeTax().getAftFinanceFee();
		}
		total.setAftFinanceFee(amount);
		
		amount = 0.0d;
		for(TenderProject tp:tpList)
		{
			amount += tp.getAccountFeeTax().getAftImplementFee();
		}
		total.setAftImplementFee(amount);
		
		amount = 0.0d;
		for(TenderProject tp:tpList)
		{
			amount += tp.getAccountFeeTax().getAftPayableTax();
		}
		total.setAftPayableTax(amount);

		amount = 0.0d;
		for(TenderProject tp:tpList)
		{
			amount += tp.getAccountFeeTax().getAftPurchaseTax();
		}
		total.setAftPurchaseTax(amount);
		
		amount = 0.0d;
		for(TenderProject tp:tpList)
		{
			amount += tp.getAccountFeeTax().getAftSalaryFee();
		}
		total.setAftSalaryFee(amount);
		
		amount = 0.0d;
		for(TenderProject tp:tpList)
		{
			amount += tp.getAccountFeeTax().getAftSaleFee();
		}
		total.setAftSaleFee(amount);
		
		amount = 0.0d;
		for(TenderProject tp:tpList)
		{
			amount += tp.getAccountFeeTax().getAftSaleTax();
		}
		total.setAftSaleTax(amount);
		
		return total;
	}
	
	/**
	 * 编辑项目文档
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editProjectDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		return mapping.findForward("documentInput");
	}


	/**
	 * 显示项目文档
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward displayProjectDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		return mapping.findForward("documentDisplay");
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

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		ProjectAttachment pa = projectManageService.createProjectAttachment();
		pa.setPaCreateUser(user.getId());
		pa.setCreateUser(user);
		pa.setPaTenderProject(tp.getTpId());
		tp.setCurrentAttachment(pa);

		request.setAttribute("attachmentCategoryList", commonService.getAttachmentCategoryList());

		return mapping.findForward("uploadAttachment");
	}

	/**
	 * 增加项目附件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addProjectAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		Account user = (Account) request.getSession().getAttribute("accountPerson");

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		ProjectAttachment pa = projectManageService.createProjectAttachment();
		pa.setPaCreateUser(user.getId());
		pa.setCreateUser(user);
		pa.setPaTenderProject(tp.getTpId());
		tp.setCurrentAttachment(pa);

		request.setAttribute("attachmentCategoryList", commonService.getAttachmentCategoryList());

		return mapping.findForward("addAttachment");
	}

	/**
	 * 编辑项目附件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward editProjectAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		
		String id = request.getParameter("id");

		for (ProjectAttachment pa : tp.getAttachmentList()) {
			if (pa.getPaId().equals(Integer.valueOf(id))) {
				tp.setCurrentAttachment(pa);
				break;
			}
		}

		request.setAttribute("attachmentCategoryList", commonService.getAttachmentCategoryList());

		return mapping.findForward("editAttachment");
	}

	/**
	 * 增加日程-阶段
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addScheduleStage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String userId = (String) request.getSession().getAttribute("account");

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		ProjectScheduleStage pss = projectManageService.createProjectScheduleStage();
		pss.setPssCreateUser(userId);
		pss.setPssTenderProject(tp.getTpId());

		tp.setCurrentScheduleStage(pss);

		return mapping.findForward("addScheduleStage");
	}

	/**
	 * 增加日程-任务
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addScheduleTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		String userId = (String) request.getSession().getAttribute("account");

		String id = request.getParameter("id");
		TenderProject tp = (TenderProject) dform.get("projectInfo");

		ProjectScheduleStage pss = null;
		for (ProjectScheduleStage s : tp.getScheduleStageList()) {
			if (s.getPssId().equals(Integer.parseInt(id))) {
				pss = s;
				break;
			}
		}
		ProjectScheduleTask pst = projectManageService.createProjectScheduleTask();
		pst.setPstCreateUser(userId);
		pst.setPstProjectScheduleStage(pss.getPssId());

		pss.setCurrentScheduleTask(pst);
		tp.setCurrentScheduleStage(pss);

		this.prepareTaskList(request, tp);

		return mapping.findForward("addScheduleTask");
	}

	/**
	 * @param request
	 * @param tp
	 */
	private void prepareTaskList(HttpServletRequest request, TenderProject tp) {
		ArrayList<ProjectScheduleTask> taskList = new ArrayList<ProjectScheduleTask>();
		for (ProjectScheduleStage s : tp.getScheduleStageList()) {
			taskList.addAll(s.getScheduleTaskList());
		}

		ProjectScheduleTask pst = tp.getCurrentScheduleStage().getCurrentScheduleTask();
		if (pst.getPstId() != null && pst.getPstId() != 0) {
			taskList.remove(pst);
		}

		request.setAttribute("taskList", taskList);
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

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String id = request.getParameter("id");

		for (ProjectAttachment p : tp.getAttachmentList()) {
			if (p.getPaId().equals(Integer.valueOf(id))) {
				p.setPaFlag(CommonService.DELETE_FLAG);
				p.setModified(true);

				break;
			}
		}

		this.prepareMetadata(request);

		return mapping.findForward("baseEdit");
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

		this.prepareMetadata(request);
		
		String iface = request.getParameter("if");		
		if(Integer.valueOf(iface).equals(CommonService.INPUT_INTERFACE_PAR))
		{
			ProjectApprovalRecord par = (ProjectApprovalRecord) dform.get("projectApprovalRecordInfo");
			String index = request.getParameter("index");

			par.getAttachmentList().remove(Integer.parseInt(index));
			
			return mapping.findForward("projectApprovalTenderEdit");
		}
		else
		{
			TenderProject tp = (TenderProject) dform.get("projectInfo");
			String index = request.getParameter("index");

			tp.getAttachmentList().remove(Integer.parseInt(index));
			
			if(Integer.valueOf(iface).equals(CommonService.INPUT_INTERFACE_INPUT))
			{
				return mapping.findForward("baseInput");
			}
			else if(Integer.valueOf(iface).equals(CommonService.INPUT_INTERFACE_BASE))
			{
				return mapping.findForward("baseEdit");
			}
		}
		
			
		return null;
	}

	/**
	 * 删除附件：文档管理用
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteProjectAttachmentByIndex(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String index = request.getParameter("index");

		tp.getAttachmentList().remove(Integer.parseInt(index));

		this.prepareMetadata(request);

		return mapping.findForward("documentInput");
	}
	/**
	 * 删除附件：文档管理用
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteProjectAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String id = request.getParameter("id");

		for (ProjectAttachment p : tp.getAttachmentList()) {
			if (p.getPaId().equals(Integer.valueOf(id))) {
				p.setPaFlag(CommonService.DELETE_FLAG);
				p.setModified(true);

				break;
			}
		}

		this.prepareMetadata(request);

		return mapping.findForward("documentInput");
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

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		ProjectAttachment pa = tp.getCurrentAttachment();
		pa.setCategory(commonService.getAttachmentCategoryById(pa.getPaAttachmentCategory()));
				
		String iface = request.getParameter("if");
		if (iface != null) {
			pa.setPaInputInterface(Integer.valueOf(iface));
		} else {
			pa.setPaInputInterface(CommonService.INPUT_INTERFACE_BASE);
		}

		this.finishAttachment(mapping, dform, request, response);

		if (Integer.valueOf(iface).equals(CommonService.INPUT_INTERFACE_CONTRACT)) {
			tp.getProjectContract().getAttachmentList().add(pa);
			return mapping.findForward("refreshProjectContractInput");
		}
		else if(Integer.valueOf(iface).equals(CommonService.INPUT_INTERFACE_INPUT))
		{
			tp.getAttachmentList().add(pa);
			return mapping.findForward("refreshProjectInput");
		}
		else if(Integer.valueOf(iface).equals(CommonService.INPUT_INTERFACE_BASE))
		{
			tp.getAttachmentList().add(pa);
			return mapping.findForward("refreshProjectBase");
		}
		else if(Integer.valueOf(iface).equals(CommonService.INPUT_INTERFACE_PAR))
		{
			ProjectApprovalRecord par = (ProjectApprovalRecord) dform.get("projectApprovalRecordInfo");
			par.getAttachmentList().add(pa);
			
			return mapping.findForward("refreshProjectTenderEdit");
		}

		return null;
	}
	
	/**
	 * 刷新项目投标页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward refreshProjectTenderEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		this.prepareMetadata(request);

		return mapping.findForward("projectApprovalTenderEdit");
	}

	/**
	 * 提交项目附件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward confirmProjectAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		ProjectAttachment pa = tp.getCurrentAttachment();

		if (pa.getPaId() == null) {
			pa.setCategory(commonService.getAttachmentCategoryById(pa.getPaAttachmentCategory()));
			tp.getAttachmentList().add(pa);
		} else {
			pa.setModified(true);
		}

		pa.setPaInputInterface(CommonService.INPUT_INTERFACE_BASE);
		this.finishAttachment(mapping, dform, request, response);

		return mapping.findForward("refreshProjectDocument");
	}
	
	/**
	 * 提交台账销售合同
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward confirmAccountSaleContract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		AccountSaleContract asc = tp.getAccountSaleContract();
		if(asc.getAscId() == null)
		{
			tp.getAccountSaleContractList().add(asc);
		}		

		return mapping.findForward("refreshSaleContract");
	}
	/**
	 * 提交台账采购合同
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward confirmAccountPurchaseContract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		AccountPurchaseContract asc = tp.getAccountPurchaseContract();
		if(asc.getApcId() == null)
		{
			tp.getAccountPurchaseContractList().add(asc);
		}		
		
		return mapping.findForward("refreshPurchaseContract");
	}
	
	/**
	 * 提交项目合同
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward confirmProjectContract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		ProjectContract asc = tp.getProjectContract();
		if(asc.getPcId() == null)
		{
			tp.getProjectContractList().add(asc);
		}		
		
		return mapping.findForward("refreshProjectContract");
	}
	
	/**
	 * 提交台账费税
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward confirmAccountFeeTax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		AccountFeeTax asc = tp.getAccountFeeTax();
		if(asc.getAftId() == null)
		{
			tp.getAccountFeeTaxList().add(asc);
		}		
		
		return mapping.findForward("refreshFeeTax");
	}

	/**
	 * 提交日程-阶段
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward confirmScheduleStage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		ProjectScheduleStage pss = tp.getCurrentScheduleStage();
		String bd = pss.getPssBeginDate();
		String ed = pss.getPssEndDate();

		if (!bd.isEmpty() && !ed.isEmpty()) {
			try {
				Date beginDate = DateFormat.getDateInstance().parse(bd);
				Date endDate = DateFormat.getDateInstance().parse(ed);

				if (beginDate.getTime() > endDate.getTime()) {
					pss.setPssEndDate(pss.getPssBeginDate());
					pss.setPssWorkPeriod(0);
				} else {
					int workDayCount = DateUtil.getWorkDayCount(beginDate, endDate);
					pss.setPssWorkPeriod(workDayCount);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (pss.getPssId() == null) {
			tp.getScheduleStageList().add(pss);
		} else {
			pss.setModified(true);
		}

		this.finishScheduleStage(mapping, dform, request, response);

		this.prepareScheduleDisplayHeader(request, tp);

		return mapping.findForward("refreshSchedulePlan");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward finishAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		tp.setCurrentAttachment(null);

		System.err.println("------************-------finishAttachment--------**********---------");

		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward finishScheduleStage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		tp.setCurrentScheduleStage(null);

		System.err.println("------************-------finishScheduleStage--------**********---------");

		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward finishScheduleTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		tp.getCurrentScheduleStage().setCurrentScheduleTask(null);
		tp.setCurrentScheduleStage(null);

		System.err.println("------************-------finishScheduleTask--------**********---------");

		return null;
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward finishImplementRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		tp.getCurrentScheduleStage().getCurrentScheduleTask().setCurrentImplementRecord(null);
		tp.getCurrentScheduleStage().setCurrentScheduleTask(null);
		tp.setCurrentScheduleStage(null);

		System.err.println("------************-------finishImplementRecord--------**********---------");

		return null;
	}

	/**
	 * 提交日程-任务
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward confirmScheduleTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");

		ProjectScheduleStage pss = tp.getCurrentScheduleStage();
		ProjectScheduleTask pst = pss.getCurrentScheduleTask();

		String sbd = pss.getPssBeginDate();
		String sed = pss.getPssEndDate();

		String bd = pst.getPstBeginDate();
		String ed = pst.getPstEndDate();

		try {
			// 任务自身时间有效性
			Date beginDate = DateFormat.getDateInstance().parse(bd);
			Date endDate = DateFormat.getDateInstance().parse(ed);

			if (beginDate.getTime() > endDate.getTime()) {
				endDate = beginDate;
			}
			// 任务与前置任务时间有效性
			Integer prePstId = pst.getPstPreTask();
			if (prePstId != null) {
				for (ProjectScheduleStage s : tp.getScheduleStageList()) {
					for (ProjectScheduleTask t : s.getScheduleTaskList()) {
						if (prePstId.equals(t.getPstId())) {
							String ted = t.getPstEndDate();

							Date tendDate = DateFormat.getDateInstance().parse(ted);

							if (beginDate.getTime() < tendDate.getTime()) {
								beginDate = tendDate;
							}
							if (endDate.getTime() < tendDate.getTime()) {
								endDate = tendDate;
							}

							break;
						}
					}
				}
			}

			// 任务与阶段间时间有效性

			Date sbeginDate = DateFormat.getDateInstance().parse(sbd);
			Date sendDate = DateFormat.getDateInstance().parse(sed);

			if (beginDate.getTime() < sbeginDate.getTime()) {
				beginDate = sbeginDate;
			}
			if (beginDate.getTime() > sendDate.getTime()) {
				beginDate = sendDate;
			}

			if (endDate.getTime() < sbeginDate.getTime()) {
				endDate = sbeginDate;
			}
			if (endDate.getTime() > sendDate.getTime()) {
				endDate = sendDate;
			}

			int workDayCount = DateUtil.getWorkDayCount(beginDate, endDate);
			pst.setPstBeginDate(DateFormat.getDateInstance().format(beginDate));
			pst.setPstEndDate(DateFormat.getDateInstance().format(endDate));
			pst.setPstWorkPeriod(workDayCount);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (pst.getPstId() == null) {
			pss.getScheduleTaskList().add(pst);
		} else {
			pst.setModified(true);
		}

		this.finishScheduleTask(mapping, dform, request, response);

		this.prepareScheduleDisplayHeader(request, tp);

		return mapping.findForward("refreshSchedulePlan");
	}

	/**
	 * 客户查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward searchCustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
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

		return mapping.findForward("searchCustomer");
	}

	/**
	 * 重置客户查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward resetSearchCustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		Customer searchInfo = new Customer();
		dform.set("customerInfo", searchInfo);

		Object[] args = { searchInfo, null };

		ArrayList<Customer> gList = commonService.searchReferCompany(args);
		Integer count = commonService.searchReferCompanyCount(args);
		if (count > 0) {
			request.setAttribute("customerList", gList);
			request.setAttribute("customerCount", count);
		}

		request.setAttribute("typeList", commonService.getCustomerTypeList());
		request.setAttribute("tradeTypeList", commonService.getTradeTypeList());

		return mapping.findForward("searchCustomer");
	}

	/**
	 * 提交选择的客户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward confirmCustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String gId = request.getParameter("id");
		Customer c = commonService.getCustomerById(Integer.valueOf(gId));
		
		String opType = request.getParameter("opType");
		if("saleContract".equals(opType))
		{
			tp.getAccountSaleContract().setAscCustomer(c.getCId());
			tp.getAccountSaleContract().setAscCustomerName(c.getCName());
			
			return mapping.findForward("refreshSaleContractInput");
		}
		else if("purchaseContract".equals(opType))
		{
			tp.getAccountPurchaseContract().setApcCustomer(c.getCId());
			tp.getAccountPurchaseContract().setApcCustomerName(c.getCName());
			
			return mapping.findForward("refreshPurchaseContractInput");
		}
		else if("projectContract".equals(opType))
		{
			tp.getProjectContract().setPcCustomer(c.getCId());
			tp.getProjectContract().setCustomer(c);
			
			return mapping.findForward("refreshProjectContractInput");
		}
				
		tp.setTpCustomer(c.getCId());
		tp.setCustomer(c);

		return mapping.findForward("refreshProjectInput");
	}


	/**
	 * 用户查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward searchAccount(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		TenderProject tp = (TenderProject) dform.get("projectInfo");

		String page = request.getParameter("pager.offset");
		Account searchInfo = (Account) dform.get("accountInfo");
		String opType = request.getParameter("opType");

		Object[] args = { searchInfo, page };

		if ("stageRes".equals(opType) || "taskRes".equals(opType)) {
			ArrayList<Account> gList = commonService.searchTeamMemberAccount(args, tp.getTpId());
			Integer count = commonService.searchTeamMemberAccountCount(args, tp.getTpId());
			if (count > 0) {
				request.setAttribute("accountList", gList);
				request.setAttribute("accountCount", count);
			}
		} else {
			ArrayList<Account> gList = commonService.searchAccount(args);
			Integer count = commonService.searchAccountCount(args);
			if (count > 0) {
				request.setAttribute("accountList", gList);
				request.setAttribute("accountCount", count);
			}
		}

		this.prepareMetadata(request);

		return mapping.findForward("searchAccount");
	}

	/**
	 * 重置用户查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward resetSearchAccount(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		Account searchInfo = new Account();

		dform.set("accountInfo", searchInfo);

		return this.searchAccount(mapping, dform, request, response);
	}

	/**
	 * 提交选择的用户
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward confirmAccount(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		DynaValidatorForm dform = (DynaValidatorForm) form;
		
		this.prepareMetadata(request);

		TenderProject tp = (TenderProject) dform.get("projectInfo");
		String gId = request.getParameter("id");
		String opType = request.getParameter("opType");

		Account a = commonService.getAccountById(gId);

		if (opType.startsWith("projectInput")) {
			
			if("projectInputManager".equals(opType))
			{
				tp.setOldManager(tp.getManager());
				tp.setManager(a);
				tp.setTpManager(a.getId());
			}
			else if("projectInputMarketManager".equals(opType))
			{
				tp.setMarketManager(a);
				tp.setTpMarketManager(a.getId());
			}
						
			return mapping.findForward("refreshProjectInput");
		}
		else if(opType.startsWith("projectBase"))
		{
			if("projectBaseManager".equals(opType))
			{
				tp.setOldManager(tp.getManager());
				tp.setManager(a);
				tp.setTpManager(a.getId());
			}
			else if("projectBaseMarketManager".equals(opType))
			{
				tp.setMarketManager(a);
				tp.setTpMarketManager(a.getId());
			}
			
			return mapping.findForward("refreshProjectBase");
		}		
		else if ("teamMember".equals(opType)) {
			tp.getCurrentProjectTeam().setAccount(a);
			tp.getCurrentProjectTeam().setPtAccount(a.getId());

			return mapping.findForward("refreshTeam");
		} else if ("stageRes".equals(opType)) {
			tp.getCurrentScheduleStage().setResponsiblePerson(a);
			tp.getCurrentScheduleStage().setPssResponsiblePerson(a.getId());

			return mapping.findForward("refreshScheduleStage");
		} else if ("taskRes".equals(opType)) {
			tp.getCurrentScheduleStage().getCurrentScheduleTask().setResponsiblePerson(a);
			tp.getCurrentScheduleStage().getCurrentScheduleTask().setPstResponsiblePerson(a.getId());

			return mapping.findForward("refreshScheduleTask");
		} else if ("parManagerEdit".equals(opType)) {
			ProjectApprovalRecord projectApprovalRecord = (ProjectApprovalRecord) dform.get("projectApprovalRecordInfo");
			projectApprovalRecord.setParManager(a.getId());
			projectApprovalRecord.setManager(a);
			
			return mapping.findForward("refreshProjectApprovalRecordEdit");
		}
		 else if ("parMarketManagerEdit".equals(opType)) {
				ProjectApprovalRecord projectApprovalRecord = (ProjectApprovalRecord) dform.get("projectApprovalRecordInfo");
				projectApprovalRecord.setParMarketManager(a.getId());
				projectApprovalRecord.setMarketManager(a);
				
				return mapping.findForward("refreshProjectApprovalRecordEdit");
			}

		return null;
	}

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
		ProjectAttachment pa = projectManageService.getProjectAttachmentById(Integer.parseInt(id));
		
		String fileName = pa.getPaUrl();	

		try {
			response.setContentType(FileUtil.getContentType(fileName));
//			response.setHeader("Content-Disposition", "attachment;filename=" + fileName.substring(fileName.lastIndexOf("\\") + 1));	
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

	public ProjectManageService getProjectManageService() {
		return projectManageService;
	}

	public void setProjectManageService(ProjectManageService projectManageService) {
		this.projectManageService = projectManageService;
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
	public CustomerService getCustomerService() {
		return customerService;
	}
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
}
