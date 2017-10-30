package org.king.projectmanage.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.king.accountmanage.bean.AccountFeeTax;
import org.king.accountmanage.bean.AccountPurchaseContract;
import org.king.accountmanage.bean.AccountSaleContract;
import org.king.accountmanage.dao.AccountFeeTaxDAO;
import org.king.accountmanage.dao.AccountPurchaseContractDAO;
import org.king.accountmanage.dao.AccountSaleContractDAO;
import org.king.common.checktask.CheckTask;
import org.king.common.checktask.CheckTaskDAO;
import org.king.common.post.Post;
import org.king.common.post.PostDAO;
import org.king.common.projectrole.ProjectRole;
import org.king.common.projectstatus.ProjectStatus;
import org.king.common.service.CommonService;
import org.king.common.service.impl.CommonServiceImpl;
import org.king.customer.bean.Customer;
import org.king.department.bean.Department;
import org.king.department.dao.DepartmentDAO;
import org.king.framework.dao.MyQuery;
import org.king.framework.exception.BusinessException;
import org.king.framework.service.impl.BaseService;
import org.king.projectmanage.bean.ProjectApprovalRecord;
import org.king.projectmanage.bean.ProjectAttachment;
import org.king.projectmanage.bean.ProjectContract;
import org.king.projectmanage.bean.ProjectScheduleStage;
import org.king.projectmanage.bean.ProjectScheduleTask;
import org.king.projectmanage.bean.ProjectTeam;
import org.king.projectmanage.bean.TaskImplementRecord;
import org.king.projectmanage.bean.TenderAttachment;
import org.king.projectmanage.bean.TenderProject;
import org.king.projectmanage.dao.ProjectApprovalRecordDAO;
import org.king.projectmanage.dao.ProjectAttachmentDAO;
import org.king.projectmanage.dao.ProjectContractDAO;
import org.king.projectmanage.dao.ProjectScheduleStageDAO;
import org.king.projectmanage.dao.ProjectScheduleTaskDAO;
import org.king.projectmanage.dao.ProjectTeamDAO;
import org.king.projectmanage.dao.TaskChangeRecordDAO;
import org.king.projectmanage.dao.TaskImplementRecordDAO;
import org.king.projectmanage.dao.TenderAttachmentDAO;
import org.king.projectmanage.dao.TenderProjectDAO;
import org.king.projectmanage.service.ProjectManageService;
import org.king.security.dao.AccountDAO;
import org.king.security.domain.Account;
import org.king.security.domain.UsrPost;
import org.king.tender.bean.ProjectBudget;
import org.king.utils.DateUtil;
import org.king.utils.FileUtil;
import org.king.utils.MailTool;

public class ProjectManageServiceImpl extends BaseService implements ProjectManageService {
	private static long dayTime = 24 * 60 * 60 * 1000;
	private TenderProjectDAO tenderProjectDAO;
	private ProjectAttachmentDAO projectAttachmentDAO;
	private ProjectTeamDAO projectTeamDAO;
	private ProjectScheduleStageDAO projectScheduleStageDAO;
	private ProjectScheduleTaskDAO projectScheduleTaskDAO;
	private TaskChangeRecordDAO taskChangeRecordDAO;
	private TaskImplementRecordDAO taskImplementRecordDAO;
	private ProjectContractDAO projectContractDAO;
	private AccountSaleContractDAO accountSaleContractDAO;
	private AccountPurchaseContractDAO accountPurchaseContractDAO;
	private AccountFeeTaxDAO accountFeeTaxDAO;
	private DepartmentDAO departmentDAO;
	private CheckTaskDAO checkTaskDAO;
	private ProjectApprovalRecordDAO projectApprovalRecordDAO;
	private AccountDAO accountDAO;
	private PostDAO postDAO;
	private TenderAttachmentDAO tenderAttachmentDAO;


	public TenderProject createTenderProject() {
		// TODO Auto-generated method stub
		TenderProject rtn = new TenderProject();
		rtn.setTpCreateTime(new Date());
		rtn.setManager(new Account());
		rtn.setCustomer(new Customer());
		rtn.setStatus(new ProjectStatus());
		rtn.setTpStatus(ProjectStatus.PROJECT_STATUS_ACT);
		rtn.setMarketManager(new Account());
		
		return rtn;
	}
	

	public ProjectAttachment createProjectAttachment() {
		// TODO Auto-generated method stub
		ProjectAttachment rtn = new ProjectAttachment();
		rtn.setPaFlag(CommonService.NORMAL_FLAG);
		rtn.setPaCreateTime(new Date());
		
		return rtn;
	}


	public int searchProjectCount(Object[] args) {
		// TODO Auto-generated method stub
	
    	TenderProject searchInfo = (TenderProject) args[1];    		           
    	Account user = (Account) args[2];
		
		String hqlStr = null;
		
		if(user.getId().equals("0"))
		{
			hqlStr = "select count(a) from TenderProject a where a.tpStatus != " + ProjectStatus.PROJECT_STATUS_TENDER + " and a.tpStatus != " + ProjectStatus.PROJECT_STATUS_TENDER_FAIL;
		}
		else
		{
			hqlStr = "select count(a) from TenderProject a where a.tpStatus != " + CommonService.DELETE_FLAG + " and a.tpStatus != " + ProjectStatus.PROJECT_STATUS_TENDER + " and a.tpStatus != " + ProjectStatus.PROJECT_STATUS_TENDER_FAIL;
		}
		
    	hqlStr += (StringUtils.isNotEmpty(searchInfo.getTpName())?" and a.tpName like '%" + searchInfo.getTpName().trim() + "%'" : "");
    	hqlStr += (StringUtils.isNotEmpty(searchInfo.getManager().getPerson().getPersonName())?" and a.manager.person.personName like '%" + searchInfo.getManager().getPerson().getPersonName() + "%'" : "");
    	    	    	
    	hqlStr += ((searchInfo.getTpStatus() != null && searchInfo.getTpStatus() != 0)?" and a.tpStatus = " + searchInfo.getTpStatus() : "");
    	hqlStr += ((searchInfo.getTpDept() != null && searchInfo.getTpDept() != 0)?" and a.tpDept = " + searchInfo.getTpDept() : "");
    	
    	MyQuery myQuery = new MyQuery();
        myQuery.setQueryString(hqlStr);
            
        return tenderProjectDAO.getFindCount(myQuery);	
	}


	public List<TenderProject> searchProjectList(Object[] args) {
		// TODO Auto-generated method stub
		String page = (String) args[0];
		TenderProject searchInfo = (TenderProject) args[1];
		Account user = (Account) args[2];
		
		String hqlStr = null;
		
		if(user.getId().equals("0"))
		{
			hqlStr = "from TenderProject a where a.tpStatus != " + ProjectStatus.PROJECT_STATUS_TENDER + " and a.tpStatus != " + ProjectStatus.PROJECT_STATUS_TENDER_FAIL;
		}
		else
		{
			hqlStr = "from TenderProject a where a.tpStatus != " + CommonService.DELETE_FLAG + " and a.tpStatus != " + ProjectStatus.PROJECT_STATUS_TENDER + " and a.tpStatus != " + ProjectStatus.PROJECT_STATUS_TENDER_FAIL;
		}
		
    	hqlStr += (StringUtils.isNotEmpty(searchInfo.getTpName())?" and a.tpName like '%" + searchInfo.getTpName().trim() + "%'" : "");
    	hqlStr += (StringUtils.isNotEmpty(searchInfo.getManager().getPerson().getPersonName())?" and a.manager.person.personName like '%" + searchInfo.getManager().getPerson().getPersonName() + "%'" : "");
    	    	    	
    	hqlStr += ((searchInfo.getTpStatus() != null && searchInfo.getTpStatus() != 0)?" and a.tpStatus = " + searchInfo.getTpStatus() : "");
    	hqlStr += ((searchInfo.getTpDept() != null && searchInfo.getTpDept() != 0)?" and a.tpDept = " + searchInfo.getTpDept() : "");
    	
    	MyQuery myQuery = new MyQuery();
    	myQuery.setPageSize(searchInfo.getPageItemCount());
    	
        if (StringUtils.isNumeric(page)) {
        	myQuery.setPageStartNo(Integer.parseInt(page));
        }else {
        	myQuery.setPageStartNo(0);
        }
        
        if(searchInfo.getOrderColumn().isEmpty())
        {
        	myQuery.setOrderby(" order by a.tpId desc");
        }
        else
        {
        	if(searchInfo.isAscFlag())
        	{
        		myQuery.setOrderby(" order by a." + searchInfo.getOrderColumn());
        	}
        	else
        	{
        		myQuery.setOrderby(" order by a." + searchInfo.getOrderColumn() + " desc");
        	}
        }   
        
        myQuery.setQueryString(hqlStr);

        myQuery.setOffset(true);
        
        return tenderProjectDAO.find(myQuery);
	}
	
		
	public List<TenderProject> searchAccountStatistics(Object[] args) {
		// TODO Auto-generated method stub
		String page = (String) args[0];
		TenderProject searchInfo = (TenderProject) args[1];
		
		Account user = (Account) args[2];
		
		String hqlStr = null;
				
		if(user.haveNoAnyPost() || user.isHavePost(Post.POST_PROJECT_MANAGER) || user.isHavePost(Post.POST_PROJECT_SECRETARY) || user.isHavePost(Post.POST_GENERAL_USER))
		{		
			hqlStr = "select distinct a from TenderProject a,ProjectTeam b where (a.tpId = b.ptTenderProject and a.tpStatus != " + CommonService.DELETE_FLAG +
					" and b.ptFlag != " + CommonService.DELETE_FLAG + " and b.ptAccount = '" + user.getId() + "')";						
		}
		
		if(user.isHavePost(Post.POST_GENERAL_MANAGER) || user.isHavePost(Post.POST_ASSIST_DEPT_MANAGER) || user.isHavePost(Post.POST_STAMPER) || user.isHavePost(Post.POST_PROJECT) || user.isHavePost(Post.POST_EXPENSE) || user.isHavePost(Post.POST_LEGAL))
		{			
			if(hqlStr == null)
			{
				hqlStr = "select distinct a from TenderProject a where ( a.tpStatus != " + CommonService.DELETE_FLAG + ")";
			}
			else
			{
				hqlStr = hqlStr + " or ( a.tpStatus != " + CommonService.DELETE_FLAG + ")";
			}					
		}		
		
		if(user.isHavePost(Post.POST_BUSINESS_DEPT_MANAGER))
		{
			if(hqlStr == null)
			{
				hqlStr = "select distinct a from TenderProject a where ( a.tpStatus != " + CommonService.DELETE_FLAG +
						" and a.tpDept in (" + user.getDeptSQLListByPost(Post.POST_BUSINESS_DEPT_MANAGER) + "))";
			}
			else
			{
				hqlStr = hqlStr + " or (a.tpStatus != " + CommonService.DELETE_FLAG +
						" and a.tpDept in (" + user.getDeptSQLListByPost(Post.POST_BUSINESS_DEPT_MANAGER) + "))";
			}
			
		}
		
		if(user.isHavePost(Post.POST_VICE_GENERAL_MANAGER))
		{
			String deptList = user.getDeptSQLListByPost(Post.POST_VICE_GENERAL_MANAGER);
			if(hqlStr == null)
			{
				if(deptList != null)
				{
					hqlStr = " select distinct a from TenderProject a where (a.tpStatus != " + CommonService.DELETE_FLAG + " and a.tpDept in (" + deptList + "))";
				}
				else
				{
					hqlStr = "select distinct a from TenderProject a where (1 = 2)";
				}
			}
			else
			{
				if(deptList != null)
				{
					hqlStr = hqlStr +  " or (a.tpStatus != " + CommonService.DELETE_FLAG + " and a.tpDept in (" + deptList + "))";
				}
			}			
		}		
		
		
		
    	hqlStr += (StringUtils.isNotEmpty(searchInfo.getTpName())?" and a.tpName like '%" + searchInfo.getTpName().trim() + "%'" : "");
    	
    	hqlStr += (StringUtils.isNotEmpty(searchInfo.getManager().getPerson().getPersonName())?" and a.manager.person.personName like '%" + searchInfo.getManager().getPerson().getPersonName() + "%'" : "");
    	       	
    	hqlStr += ((searchInfo.getTpStatus() != null && searchInfo.getTpStatus() != 0)?" and a.tpStatus = " + searchInfo.getTpStatus() : "");
    	hqlStr += ((searchInfo.getTpDept() != null && searchInfo.getTpDept() != 0)?" and a.tpDept = " + searchInfo.getTpDept() : "");
    	
    	MyQuery myQuery = new MyQuery();
    	
        myQuery.setOrderby(" order by a.tpId desc");
        myQuery.setQueryString(hqlStr);

        myQuery.setOffset(false);
        
        return tenderProjectDAO.find(myQuery);
	}

	
	public void deleteTenderProject(TenderProject tp) {
		// TODO Auto-generated method stub
		tp.setTpStatus(CommonService.DELETE_FLAG);		
		tenderProjectDAO.save(tp);
	}


	
	public void saveTenderProject(TenderProject tp,String uploadPath) {
		// TODO Auto-generated method stub
		ArrayList<ProjectAttachment> attachmentList = tp.getAttachmentList();
		tp.setAttachmentList(new ArrayList<ProjectAttachment>());
		
		Date createTime = new Date();
		if(tp.getTpId() == null)
		{			
			tenderProjectDAO.save(tp);
		}
		else
		{
			tenderProjectDAO.update(tp);
		}
		tp.setAttachmentList(attachmentList);
		
		for(ProjectAttachment pa:attachmentList)
		{
			if(pa.getPaId() == null)
			{
				String fileName = FileUtil.saveUploadFile(pa.getAttachmentFile(),uploadPath + tp.getTpId());
				
				pa.setPaTenderProject(tp.getTpId());
				pa.setPaCreateTime(createTime);
				pa.setPaUrl(uploadPath + tp.getTpId() + "\\" + fileName);
				pa.setPaSubmitDepartment(tp.getTpDept());
												
				projectAttachmentDAO.save(pa);
				
				int count = this.getProjectAttachmentCountByCategory(pa.getPaAttachmentCategory());
				pa.setPaCode(CommonServiceImpl.getProjectAttachmentCode(pa.getPaAttachmentCategory(),count));
				projectAttachmentDAO.update(pa);
			}	
			else if(pa.isModified())
			{
				projectAttachmentDAO.update(pa);
				pa.setModified(false);
			}
		}		
	}
	
	
	public void saveProjectManagerAsTeam(TenderProject tp,CommonService commonService)
	{
		boolean managerTeamFlag = false;
		String managerId = tp.getTpManager();
		for(ProjectTeam pt:tp.getTeamMemberList())
		{
			if(managerId.equals(pt.getPtAccount()) && pt.getPtFlag() != CommonService.DELETE_FLAG)
			{
				managerTeamFlag = true;
				break;
			}
		}
		
		if(!managerTeamFlag)
		{
			ProjectTeam pt = new ProjectTeam();
			pt.setPtFlag(CommonService.NORMAL_FLAG);
			pt.setPtCreateTime(new Date());
			pt.setPtAccount(tp.getTpManager());
			pt.setAccount(commonService.getAccountById(tp.getTpManager()));
			
			ProjectRole pr = commonService.getProjectRoleById(ProjectRole.PROJECT_ROLE_MANAGER);
			
			pt.setPtCreateUser(tp.getTpCreateUser());
			pt.setPtTenderProject(tp.getTpId());
			pt.setPtJobResponsibility(pr.getPrJobResponsibility());
			pt.setPtProjectRole(pr.getPrId());
			pt.setJobResponsibility(pr.getJobResponsibility());
			pt.setProjectRole(pr);
			
			this.projectTeamDAO.save(pt);
			
			tp.getTeamMemberList().add(pt);
		}
	}

	public void saveProjectCreaterAsTeam(TenderProject tp,CommonService commonService)
	{		
		ProjectTeam pt = new ProjectTeam();
		pt.setPtFlag(CommonService.NORMAL_FLAG);
		pt.setPtCreateTime(new Date());
		pt.setPtAccount(tp.getTpCreateUser());
		pt.setAccount(commonService.getAccountById(tp.getTpCreateUser()));
		
		ProjectRole pr = commonService.getProjectRoleById(ProjectRole.PROJECT_ROLE_SECRETARY);
		
		pt.setPtCreateUser(tp.getTpCreateUser());
		pt.setPtTenderProject(tp.getTpId());
		pt.setPtJobResponsibility(pr.getPrJobResponsibility());
		pt.setPtProjectRole(pr.getPrId());
		pt.setJobResponsibility(pr.getJobResponsibility());
		pt.setProjectRole(pr);
		
		this.projectTeamDAO.save(pt);
		
		tp.getTeamMemberList().add(pt);		
	}
	
	
	public void updateProjectContract(ProjectContract pc)
	{
		projectContractDAO.update(pc);
	}
	
	public void saveTenderContract(TenderProject tp,String uploadPath) {
		// TODO Auto-generated method stub
		ArrayList<ProjectAttachment> attachmentList = tp.getTenderContract().getAttachmentList();	
		tp.getTenderContract().setAttachmentList(new ArrayList<ProjectAttachment>());
		Date createTime = new Date();

		if(tp.getTenderContract().getPcId() == null)
		{
			projectContractDAO.save(tp.getTenderContract());
			tp.getTenderContractList().add(tp.getTenderContract());
		}
		else
		{
			projectContractDAO.update(tp.getTenderContract());
		}
	}
		
	public void saveProjectContract(TenderProject tp,String uploadPath) {
		// TODO Auto-generated method stub
		
		for(ProjectContract pc:tp.getProjectContractList())
		{
			ArrayList<ProjectAttachment> attachmentList = pc.getAttachmentList();	
			pc.setAttachmentList(new ArrayList<ProjectAttachment>());
			Date createTime = new Date();

			if(pc.getPcId() == null)
			{
				projectContractDAO.save(pc);
			}
			else
			{
				projectContractDAO.update(pc);
			}
			
			pc.setAttachmentList(attachmentList);
			for(ProjectAttachment pa:attachmentList)
			{
				if(pa.getPaId() == null)
				{
					String fileName = FileUtil.saveUploadFile(pa.getAttachmentFile(),uploadPath + tp.getTpId());
					
					pa.setPaTenderProject(tp.getTpId());
					pa.setPaCreateTime(createTime);
					pa.setPaUrl(uploadPath + tp.getTpId() + "\\" + fileName);
					
					pa.setPaContract(pc.getPcId());
					pa.setPaSubmitDepartment(tp.getTpDept());					
					projectAttachmentDAO.save(pa);		
					
					int count = this.getProjectAttachmentCountByCategory(pa.getPaAttachmentCategory());
					pa.setPaCode(CommonServiceImpl.getProjectAttachmentCode(pa.getPaAttachmentCategory(),count));
					projectAttachmentDAO.update(pa);
				}	
				else if(pa.isModified())
				{
					projectAttachmentDAO.update(pa);
					pa.setModified(false);
				}
			}
		}		
	}
	
	private int getProjectAttachmentCountByCategory(int category)
	{				
		String hqlStr = "select count(a) from ProjectAttachment a where a.paAttachmentCategory = " + category;			
				
    	MyQuery myQuery = new MyQuery();
        myQuery.setQueryString(hqlStr);
            
        return projectAttachmentDAO.getFindCount(myQuery);	
	}

	public void saveProjectDocument(TenderProject tp,String uploadPath) {
		// TODO Auto-generated method stub
		Date createTime = new Date();		
		for(ProjectAttachment pa:tp.getAttachmentList())
		{
			if(pa.getPaId() == null)
			{
				String fileName = FileUtil.saveUploadFile(pa.getAttachmentFile(),uploadPath + tp.getTpId());
				
				pa.setPaTenderProject(tp.getTpId());
				pa.setPaCreateTime(createTime);
				pa.setPaUrl(uploadPath + tp.getTpId() + "\\" + fileName);
				pa.setPaSubmitDepartment(tp.getTpDept());
								
				projectAttachmentDAO.save(pa);
				
				int count = this.getProjectAttachmentCountByCategory(pa.getPaAttachmentCategory());
				pa.setPaCode(CommonServiceImpl.getProjectAttachmentCode(pa.getPaAttachmentCategory(),count));
				projectAttachmentDAO.update(pa);
			}	
			else if(pa.isModified())
			{
				if(pa.getAttachmentFile() != null && !pa.getAttachmentFile().getFileName().isEmpty())
				{
					String fileName = FileUtil.saveUploadFile(pa.getAttachmentFile(),uploadPath + tp.getTpId());					
					pa.setPaUrl(uploadPath + tp.getTpId() + "\\" + fileName);
				}
				projectAttachmentDAO.update(pa);
				pa.setModified(false);
			}
		}
	}
	
	
	public void saveTenderDocument(TenderProject tp,String uploadPath) {
		// TODO Auto-generated method stub
		Date createTime = new Date();		
		for(TenderAttachment pa:tp.getTenderAttachmentList())
		{
			if(pa.getTaId() == null)
			{
				String fileName = FileUtil.saveUploadFile(pa.getAttachmentFile(),uploadPath + tp.getTpId());
				
				pa.setTaTenderProject(tp.getTpId());
				pa.setTaCreateTime(createTime);
				pa.setTaUrl(uploadPath + tp.getTpId() + "\\" + fileName);
												
				tenderAttachmentDAO.save(pa);
				
				int count = this.getProjectAttachmentCountByCategory(pa.getTaAttachmentCategory());
				pa.setTaCode(CommonServiceImpl.getProjectAttachmentCode(pa.getTaAttachmentCategory(),count));
				tenderAttachmentDAO.update(pa);
			}	
			else if(pa.isModified())
			{
				if(pa.getAttachmentFile() != null && !pa.getAttachmentFile().getFileName().isEmpty())
				{
					String fileName = FileUtil.saveUploadFile(pa.getAttachmentFile(),uploadPath + tp.getTpId());					
					pa.setTaUrl(uploadPath + tp.getTpId() + "\\" + fileName);
				}
				tenderAttachmentDAO.update(pa);
				pa.setModified(false);
			}
		}
	}
	

	public TenderProject getProjectById(Integer id) {
		// TODO Auto-generated method stub
		return tenderProjectDAO.get(id);
	}


	public ProjectAttachmentDAO getProjectAttachmentDAO() {
		return projectAttachmentDAO;
	}

	public void setProjectAttachmentDAO(ProjectAttachmentDAO projectAttachmentDAO) {
		this.projectAttachmentDAO = projectAttachmentDAO;
	}

	public ProjectTeamDAO getProjectTeamDAO() {
		return projectTeamDAO;
	}

	public void setProjectTeamDAO(ProjectTeamDAO projectTeamDAO) {
		this.projectTeamDAO = projectTeamDAO;
	}

	public ProjectScheduleStageDAO getProjectScheduleStageDAO() {
		return projectScheduleStageDAO;
	}

	public void setProjectScheduleStageDAO(
			ProjectScheduleStageDAO projectScheduleStageDAO) {
		this.projectScheduleStageDAO = projectScheduleStageDAO;
	}

	public ProjectScheduleTaskDAO getProjectScheduleTaskDAO() {
		return projectScheduleTaskDAO;
	}

	public void setProjectScheduleTaskDAO(
			ProjectScheduleTaskDAO projectScheduleTaskDAO) {
		this.projectScheduleTaskDAO = projectScheduleTaskDAO;
	}

	public void setTenderProjectDAO(TenderProjectDAO tenderProjectDAO) {
		this.tenderProjectDAO = tenderProjectDAO;
	}

	public TenderProjectDAO getTenderProjectDAO() {
		return tenderProjectDAO;
	}


	public void calculateProjectTimeSchedule(List<TenderProject> projectList) {
		// TODO Auto-generated method stub
		for(TenderProject tp:projectList)
		{
			calculateProjectTimeSchedule(tp);		
		}
	}
	
	/* 
	 * 计算时间进度
	 */
	public void calculateProjectTimeSchedule(TenderProject tp)
	{
		if(tp.getTpBeginDate() == null || tp.getTpEndDate() == null)
		{
			return;
		}
		
		try {
			long bd = DateFormat.getDateInstance().parse(tp.getTpBeginDate()).getTime();
			long ed = DateFormat.getDateInstance().parse(tp.getTpEndDate()).getTime();
			
			long now = System.currentTimeMillis();
			if(now > bd)
			{
				int doDayCount = DateUtil.getWorkDayCount(new Date(bd), new Date(now));
				tp.setDoDayCount(doDayCount);
				if(tp.getTpWorkdayCount() != null)
				{
					tp.setRemainDayCount(tp.getTpWorkdayCount() - doDayCount);
				}				
				if(now < ed)
				{
					float per = (float)((now - bd)* 1.0d / (ed - bd + dayTime));
					tp.setSchedulePlanPercent(per);
				}
				else
				{
					tp.setSchedulePlanPercent(1);
					if(tp.getTpWorkdayCount() != null)
					{
						tp.setBeyondDayCount(doDayCount -tp.getTpWorkdayCount());
					}					
				}		
				
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* 
	 * 计算执行进度
	 */
	public void calculateProjectDoSchedule(TenderProject tp)
	{
		int planDayCount = 0;
		int doDayCount = 0;
		
		for(ProjectScheduleStage pss:tp.getScheduleStageList())
		{
			int stagePlanDayCount = 0;
			int stageDoDayCount = 0;
			for(ProjectScheduleTask pst:pss.getScheduleTaskList())
			{
				stagePlanDayCount += pst.getPstWorkPeriod();
				planDayCount += pst.getPstWorkPeriod();
				if(pst.getPstFinishPercentry() != null)
				{
					doDayCount += pst.getPstWorkPeriod() * pst.getPstFinishPercentry()/100;
					stageDoDayCount += pst.getPstWorkPeriod() * pst.getPstFinishPercentry()/100;
				}				
			}
			
			if(stagePlanDayCount > 0)
			{
				pss.setSchedulePercent((stageDoDayCount *100.0f)/(stagePlanDayCount*100));
			}
		}
		
		if(planDayCount > 0)
		{
			tp.setSchedulePercent((doDayCount *100.0f)/(planDayCount*100));
		}		
	}

	
	public ProjectAttachment getProjectAttachment(Integer id) {
		// TODO Auto-generated method stub
		return projectAttachmentDAO.get(id);
	}


	public ProjectTeam createProjectTeam() {
		// TODO Auto-generated method stub
		ProjectTeam rtn = new ProjectTeam();
		
		rtn.setPtFlag(CommonService.NORMAL_FLAG);
		rtn.setAccount(new Account());
		rtn.setPtCreateTime(new Date());
		
		return rtn;
	}


	public void saveProjectTeam(TenderProject tp) {
		// TODO Auto-generated method stub

		Date createTime = new Date();
		for(ProjectTeam pt:tp.getTeamMemberList())
		{
			if(pt.getPtId() == null)
			{
				pt.setPtCreateTime(createTime);
				this.projectTeamDAO.save(pt);
			}			
			else if(pt.isModified())
			{
				projectTeamDAO.update(pt);
				pt.setModified(false);
			}
		}		
		
	}


	public ProjectTeam getProjectTeamById(Integer ptId) {
		// TODO Auto-generated method stub
		return projectTeamDAO.get(ptId);
	}


	public ProjectScheduleStage createProjectScheduleStage() {
		// TODO Auto-generated method stub
		ProjectScheduleStage rtn = new ProjectScheduleStage();
		
		rtn.setPssFlag(CommonService.NORMAL_FLAG);
		rtn.setResponsiblePerson(new Account());
		rtn.setPssCreateTime(new Date());
		
		return rtn;
	}

	public ProjectScheduleTask createProjectScheduleTask() {
		// TODO Auto-generated method stub
		ProjectScheduleTask rtn = new ProjectScheduleTask();
		
		rtn.setPstFlag(CommonService.NORMAL_FLAG);
		rtn.setResponsiblePerson(new Account());
		rtn.setPstCreateTime(new Date());
		
		return rtn;
	}


	public ProjectScheduleStage getScheduleStageById(Integer pssId) {
		// TODO Auto-generated method stub
		return projectScheduleStageDAO.get(pssId);
	}
	

	public ProjectScheduleTask getScheduleTaskById(Integer pstId) {
		// TODO Auto-generated method stub
		return projectScheduleTaskDAO.get(pstId);
	}


	public void saveSchedulePlan(TenderProject tp) {
		// TODO Auto-generated method stub
		for(ProjectScheduleStage pss:tp.getScheduleStageList())
		{
			ArrayList<ProjectScheduleTask> taskList = pss.getScheduleTaskList();
							
			pss.setScheduleTaskList(new ArrayList<ProjectScheduleTask>());	
			if(pss.getPssId() == null)
			{
				projectScheduleStageDAO.save(pss);	
			}
			else if(pss.isModified())
			{
				projectScheduleStageDAO.update(pss);
				pss.setModified(false);
			}
			pss.setScheduleTaskList(taskList);
			
			
			for(ProjectScheduleTask pst:taskList)
			{				
				if(pst.getPstId() == null)
				{
					projectScheduleTaskDAO.save(pst);
				}
				else if(pst.isModified())
				{
					projectScheduleTaskDAO.update(pst);	
					pst.setModified(false);
				}				
			}
		}
		
		this.updateTenderProjectStatus(tp);
	}
	
	public void saveSchedule(TenderProject tp) {
		// TODO Auto-generated method stub
		for(ProjectScheduleStage pss:tp.getScheduleStageList())
		{			
			for(ProjectScheduleTask pst:pss.getScheduleTaskList())
			{			
				for(TaskImplementRecord tir:pst.getImplementRecordList())
				{
					if(tir.getTirId() == null)
					{						
						taskImplementRecordDAO.save(tir);
					}
					else if(tir.isModified())
					{
						taskImplementRecordDAO.update(tir);
						tir.setModified(false);
					}
				}
				
				if(pst.isModified())
				{
					projectScheduleTaskDAO.update(pst);
					pst.setModified(false);
				}
			}
		}
		
		this.updateTenderProjectStatus(tp);
	}
	
	private void updateTenderProjectStatus(TenderProject tp)
	{
		if(tp.getTpStatus().equals(ProjectStatus.PROJECT_STATUS_ACT))
		{
			boolean finish = this.isProjectActFinish(tp);
								
			if(finish)
			{
				tp.setTpStatus(ProjectStatus.PROJECT_STATUS_MAINTAIN);
				tenderProjectDAO.update(tp);
			}			
		}
		else if(tp.getTpStatus().equals(ProjectStatus.PROJECT_STATUS_MAINTAIN))
		{
			boolean finish = this.isProjectActFinish(tp);
						
			if(!finish)
			{
				tp.setTpStatus(ProjectStatus.PROJECT_STATUS_ACT);
				tenderProjectDAO.update(tp);
			}
		}
	}
	
	private boolean isProjectActFinish(TenderProject tp)
	{
		boolean finish = true;
		
		for(ProjectScheduleStage s:tp.getScheduleStageList())
		{
			if(s.getPssFlag().equals(CommonService.DELETE_FLAG))
			{
				continue;
			}
			for(ProjectScheduleTask t:s.getScheduleTaskList())
			{
				if(t.getPstFlag().equals(CommonService.DELETE_FLAG))
				{
					continue;
				}
				if(t.getPstFinishPercentry() == null || t.getPstFinishPercentry() < 100)
				{
					finish = false;
					break;
				}
			}
			
			if(!finish)
			{
				break;
			}
		}
		
		return finish;
	}


	public void setTaskChangeRecordDAO(TaskChangeRecordDAO taskChangeRecordDAO) {
		this.taskChangeRecordDAO = taskChangeRecordDAO;
	}


	public TaskChangeRecordDAO getTaskChangeRecordDAO() {
		return taskChangeRecordDAO;
	}


	public void setTaskImplementRecordDAO(TaskImplementRecordDAO taskImplementRecordDAO) {
		this.taskImplementRecordDAO = taskImplementRecordDAO;
	}


	public TaskImplementRecordDAO getTaskImplementRecordDAO() {
		return taskImplementRecordDAO;
	}


	public TaskImplementRecord createTaskImplementRecord() {
		// TODO Auto-generated method stub
		TaskImplementRecord rtn = new TaskImplementRecord();
		
		rtn.setTirFlag(CommonService.NORMAL_FLAG);
		
		return rtn;
	}


	public TaskImplementRecord getTaskImplementRecordById(Integer tirId) {
		// TODO Auto-generated method stub
		return taskImplementRecordDAO.get(tirId);
	}


	@Override
	public void modifyCloseTenderProject(TenderProject tp) {
		// TODO Auto-generated method stub
		tp.setTpStatus(ProjectStatus.PROJECT_STATUS_MAINTAIN);
		tenderProjectDAO.update(tp);
	}
	
	public ProjectContractDAO getProjectContractDAO() {
		return projectContractDAO;
	}


	public void setProjectContractDAO(ProjectContractDAO projectContractDAO) {
		this.projectContractDAO = projectContractDAO;
	}


	@Override
	public void copyDataFromTenderBudget(ProjectBudget projectBudget,ProjectBudget tenderBudget) {
		// TODO Auto-generated method stub
		projectBudget.getE7().setBiAmount(tenderBudget.getE7().getBiAmount());
		projectBudget.getE8().setBiAmount(tenderBudget.getE8().getBiAmount());
		projectBudget.getE9().setBiAmount(tenderBudget.getE9().getBiAmount());
		projectBudget.getE10().setBiAmount(tenderBudget.getE10().getBiAmount());
		projectBudget.getE11().setBiAmount(tenderBudget.getE11().getBiAmount());
		projectBudget.getE12().setBiAmount(tenderBudget.getE12().getBiAmount());
		projectBudget.getE13().setBiAmount(tenderBudget.getE13().getBiAmount());
		projectBudget.getE14().setBiAmount(tenderBudget.getE14().getBiAmount());
		projectBudget.getE15().setBiAmount(tenderBudget.getE15().getBiAmount());
		projectBudget.getE16().setBiAmount(tenderBudget.getE16().getBiAmount());
		projectBudget.getE17().setBiAmount(tenderBudget.getE17().getBiAmount());
		projectBudget.getE18().setBiAmount(tenderBudget.getE18().getBiAmount());
		projectBudget.getE19().setBiAmount(tenderBudget.getE19().getBiAmount());
		projectBudget.getE20().setBiAmount(tenderBudget.getE20().getBiAmount());
		projectBudget.getE21().setBiAmount(tenderBudget.getE21().getBiAmount());
		projectBudget.getE22().setBiAmount(tenderBudget.getE22().getBiAmount());
		projectBudget.getE23().setBiAmount(tenderBudget.getE23().getBiAmount());
		projectBudget.getE24().setBiAmount(tenderBudget.getE24().getBiAmount());
		projectBudget.getE25().setBiAmount(tenderBudget.getE25().getBiAmount());
		projectBudget.getE26().setBiAmount(tenderBudget.getE26().getBiAmount());
		projectBudget.getE27().setBiAmount(tenderBudget.getE27().getBiAmount());
		projectBudget.getE28().setBiAmount(tenderBudget.getE28().getBiAmount());
		projectBudget.getE29().setBiAmount(tenderBudget.getE29().getBiAmount());
		projectBudget.getE30().setBiAmount(tenderBudget.getE30().getBiAmount());
		projectBudget.getE31().setBiAmount(tenderBudget.getE31().getBiAmount());
		projectBudget.getE32().setBiAmount(tenderBudget.getE32().getBiAmount());
		projectBudget.getE33().setBiAmount(tenderBudget.getE33().getBiAmount());
		projectBudget.getE34().setBiAmount(tenderBudget.getE34().getBiAmount());
		projectBudget.getE35().setBiAmount(tenderBudget.getE35().getBiAmount());
		projectBudget.getE36().setBiAmount(tenderBudget.getE36().getBiAmount());
		projectBudget.getE37().setBiAmount(tenderBudget.getE37().getBiAmount());
		projectBudget.getE38().setBiAmount(tenderBudget.getE38().getBiAmount());
		projectBudget.getE39().setBiAmount(tenderBudget.getE39().getBiAmount());
		projectBudget.getE40().setBiAmount(tenderBudget.getE40().getBiAmount());
		projectBudget.getE41().setBiAmount(tenderBudget.getE41().getBiAmount());
		projectBudget.getE42().setBiAmount(tenderBudget.getE42().getBiAmount());
		projectBudget.getE43().setBiAmount(tenderBudget.getE43().getBiAmount());
		projectBudget.getE44().setBiAmount(tenderBudget.getE44().getBiAmount());
		projectBudget.getE45().setBiAmount(tenderBudget.getE45().getBiAmount());
		projectBudget.getE46().setBiAmount(tenderBudget.getE46().getBiAmount());
		projectBudget.getE47().setBiAmount(tenderBudget.getE47().getBiAmount());		
	}

	@Override
	public void deleteTenderMember(TenderProject tp, Account a) {
		// TODO Auto-generated method stub
		for(ProjectTeam pt:tp.getTeamMemberList())
		{
			if(pt.getPtAccount().equals(a.getId()))
			{
				pt.setPtFlag(CommonService.DELETE_FLAG);				
				projectTeamDAO.update(pt);				
			}
		}		
	}


	@Override
	public void deleteProjectMember(TenderProject tp, Account a) {
		// TODO Auto-generated method stub
		for(ProjectTeam pt:tp.getTeamMemberList())
		{
			if(pt.getPtAccount().equals(a.getId()))
			{
				pt.setPtFlag(CommonService.DELETE_FLAG);				
				projectTeamDAO.update(pt);				
			}
		}
	}


	public void setAccountSaleContractDAO(AccountSaleContractDAO accountSaleContractDAO) {
		this.accountSaleContractDAO = accountSaleContractDAO;
	}


	public AccountSaleContractDAO getAccountSaleContractDAO() {
		return accountSaleContractDAO;
	}


	@Override
	public void saveAccountSaleContract(TenderProject tp) {
		// TODO Auto-generated method stub
		
		for(AccountSaleContract asc:tp.getAccountSaleContractList())
		{
			if(asc.getAscId() == null)
			{
				asc.setAscCreateTime(new Date());
				accountSaleContractDAO.save(asc);
			}
			else
			{
				accountSaleContractDAO.update(asc);
			}
		}		
		
	}
	
	@Override
	public void saveAccountPurchaseContract(TenderProject tp) {
		// TODO Auto-generated method stub
		
		for(AccountPurchaseContract asc:tp.getAccountPurchaseContractList())
		{
			if(asc.getApcId() == null)
			{
				asc.setApcCreateTime(new Date());
				accountPurchaseContractDAO.save(asc);
			}
			else
			{
				accountPurchaseContractDAO.update(asc);
			}
		}		
		
	}
	
	@Override
	public void saveAccountFeeTax(TenderProject tp) {
		// TODO Auto-generated method stub
		
		for(AccountFeeTax asc:tp.getAccountFeeTaxList())
		{
			if(asc.getAftId() == null)
			{
				asc.setAftCreateTime(new Date());
				accountFeeTaxDAO.save(asc);
			}
			else
			{
				accountFeeTaxDAO.update(asc);
			}
		}		
		
	}


	public void setAccountPurchaseContractDAO(AccountPurchaseContractDAO accountPurchaseContractDAO) {
		this.accountPurchaseContractDAO = accountPurchaseContractDAO;
	}


	public AccountPurchaseContractDAO getAccountPurchaseContractDAO() {
		return accountPurchaseContractDAO;
	}


	public void setAccountFeeTaxDAO(AccountFeeTaxDAO accountFeeTaxDAO) {
		this.accountFeeTaxDAO = accountFeeTaxDAO;
	}


	public AccountFeeTaxDAO getAccountFeeTaxDAO() {
		return accountFeeTaxDAO;
	}

	public DepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}


	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}


	@Override
	public void saveCheckTaskList(List<CheckTask> taskList) {
		// TODO Auto-generated method stub
		for(CheckTask ct:taskList)
		{
			if(ct.getCtId() == null)
			{
				checkTaskDAO.save(ct);
			}
			else
			{
				checkTaskDAO.update(ct);
			}
		}
	}


	public void setCheckTaskDAO(CheckTaskDAO checkTaskDAO) {
		this.checkTaskDAO = checkTaskDAO;
	}


	public CheckTaskDAO getCheckTaskDAO() {
		return checkTaskDAO;
	}

	private String getCheckTaskSQL(CheckTask searchInfo,Account user)
	{
		String hqlStr = "";
		
		if(user.getId().equals("0"))
		{
			hqlStr = "select a from CheckTask a left join a.project left join a.contract left join a.projectApprovalRecord left join a.projectBudget where a.ctStatus = " + searchInfo.getCtStatus();
		}
		else
		{				
			//项目秘书，接收驳回的任务
			if(user.isHavePost(Post.POST_PROJECT_SECRETARY))
			{
				String sql = "";
				if(searchInfo.getCtStatus().equals(CheckTask.TASK_STATUS_CURRENT_CHECK))
				{
					sql = "select a from CheckTask a left join a.project left join a.contract left join a.projectApprovalRecord left join a.projectBudget where (a.ctStatus = " + CheckTask.TASK_STATUS_CHECKED + 
							" and a.ctCheckResult = " + CheckTask.TASK_CHECK_RESULT_NO + " and a.ctSendUser = '" + user.getId() + "'" + 
							" and (a.contract.pcStatus = " + ProjectContract.Contract_Status_RETURN + " or a.projectApprovalRecord.parStatus = " + ProjectApprovalRecord.PAR_STATUS_APPROVAL_RETURN + " or a.projectApprovalRecord.parStatus = " + ProjectApprovalRecord.PAR_STATUS_TENDER_RETURN + 
							" or  a.projectBudget.pbCheckResult = " + ProjectBudget.BUDGET_CHECK_STATUS_RETURN + " or  a.project.tpTenderdocCheckStatus = " + ProjectStatus.TENDERDOC_CHECK_STATUS_REJECT + "))";		
				}
				else
				{
					sql = "select a from CheckTask a left join a.project left join a.contract left join a.projectApprovalRecord left join a.projectBudget where (a.ctStatus = " + searchInfo.getCtStatus() + 
							 " and a.ctReceivePost = " + Post.POST_PROJECT_SECRETARY + " and a.ctSendUser = '" + user.getId() + "')";
				}
				if(hqlStr.isEmpty())
				{
					hqlStr = sql;
				}
				else
				{
					if(searchInfo.getCtStatus().equals(CheckTask.TASK_STATUS_CURRENT_CHECK))
					{
						hqlStr = hqlStr + " or (a.ctStatus = " + CheckTask.TASK_STATUS_CHECKED + " and a.ctCheckResult = " + CheckTask.TASK_CHECK_RESULT_NO + " and a.ctSendUser = '" + user.getId() + "'" + 
								" and (a.contract.pcStatus = " + ProjectContract.Contract_Status_RETURN + " or a.projectApprovalRecord.parStatus = " + ProjectApprovalRecord.PAR_STATUS_APPROVAL_RETURN + " or a.projectApprovalRecord.parStatus = " + ProjectApprovalRecord.PAR_STATUS_TENDER_RETURN + 
								" or  a.projectBudget.pbCheckResult = " + ProjectBudget.BUDGET_CHECK_STATUS_RETURN + " or  a.project.tpTenderdocCheckStatus = " + ProjectStatus.TENDERDOC_CHECK_STATUS_REJECT + "))";
					}
					else
					{
						hqlStr = hqlStr + " or (a.ctStatus = " + searchInfo.getCtStatus() + 
								 " and a.ctReceivePost = " + Post.POST_PROJECT_SECRETARY + " and a.ctSendUser = '" + user.getId() + "')";
					}
				}
			}
			
			//项目经理，具体到个人
			if(user.isHavePost(Post.POST_PROJECT_MANAGER))
			{
				String sql = "select a from CheckTask a left join a.project left join a.contract left join a.projectApprovalRecord left join a.projectBudget where (a.ctStatus = " + searchInfo.getCtStatus() + 
				" and a.ctReceivePost = " + Post.POST_PROJECT_MANAGER +
				" and (a.project.tpManager = '" + user.getId() + "' or a.projectApprovalRecord.parManager = '" + user.getId() + "'))";
				
				if(hqlStr.isEmpty())
				{
					hqlStr = sql;
				}
				else
				{
					hqlStr = hqlStr + " or (a.ctStatus = " + searchInfo.getCtStatus() + " and a.ctReceivePost = " + Post.POST_PROJECT_MANAGER +
							" and (a.project.tpManager = '" + user.getId() + "' or a.projectApprovalRecord.parManager = '" + user.getId() + "'))";
				}
			}
			
			
			//部门经理，包括辅助部门经理和业务部门经理
			if(user.isHavePost(Post.POST_ASSIST_DEPT_MANAGER) || user.isHavePost(Post.POST_BUSINESS_DEPT_MANAGER))
			{
				String deptList = user.getDeptSQLListByPost(Post.POST_ASSIST_DEPT_MANAGER);
				if(!deptList.isEmpty())
				{
					if(!user.getDeptSQLListByPost(Post.POST_BUSINESS_DEPT_MANAGER).isEmpty())
					{
						deptList =  deptList + "," + user.getDeptSQLListByPost(Post.POST_BUSINESS_DEPT_MANAGER);
					}					
				}
				else
				{
					deptList = user.getDeptSQLListByPost(Post.POST_BUSINESS_DEPT_MANAGER);
				}
				
				String sql = "select a from CheckTask a left join a.project left join a.contract left join a.projectApprovalRecord left join a.projectBudget where (a.ctStatus = " + searchInfo.getCtStatus() + " and a.ctReceiveDept in (" + deptList + ")" +
				" and a.ctReceivePost is null)";	
				
				if(hqlStr.isEmpty())
				{
					hqlStr = sql;
				}
				else
				{
					hqlStr = hqlStr + " or (a.ctStatus = " + searchInfo.getCtStatus() + " and a.ctReceiveDept in (" + deptList + ")" +
							" and a.ctReceivePost is null)";	
				}
			}
			
			//项目市场经理，具体到个人
			if(user.isHavePost(Post.POST_PROJECT_MARKET_MANAGER))
			{
				String sql = "select a from CheckTask a left join a.project left join a.contract left join a.projectApprovalRecord left join a.projectBudget where (a.ctStatus = " + searchInfo.getCtStatus() + 
				" and a.ctReceivePost = " + Post.POST_PROJECT_MARKET_MANAGER + 
				" and (a.project.tpMarketManager = '" + user.getId() + "' or a.projectApprovalRecord.parMarketManager = '" + user.getId() + "'))";
				
				//针对没有市场经理的项目评审
				for(UsrPost up:user.getUsrPostList())
				{
					Integer post = up.getPost();
					if(post != null && post.equals(Post.POST_PROJECT_MARKET_MANAGER))
					{
						sql = sql + " or (a.project != null and a.project.tpMarketManager = null and a.ctStatus = " + searchInfo.getCtStatus() + " and a.ctReceiveDept = " + up.getDept() + " and a.ctReceivePost = " + up.getPost() + ")";
					}					
				}
				
				
				if(hqlStr.isEmpty())
				{
					hqlStr = sql;
				}
				else
				{
					hqlStr = hqlStr + " or (a.ctStatus = " + searchInfo.getCtStatus() + 
							" and a.ctReceivePost = " + Post.POST_PROJECT_MARKET_MANAGER + 
							" and (a.project.tpMarketManager = '" + user.getId() + "' or a.projectApprovalRecord.parMarketManager = '" + user.getId() + "'))";
					
					//针对没有市场经理的项目评审
					for(UsrPost up:user.getUsrPostList())
					{
						Integer post = up.getPost();
						if(post != null && post.equals(Post.POST_PROJECT_MARKET_MANAGER))
						{
							hqlStr = hqlStr + " or (a.project != null and a.project.tpMarketManager = null and a.ctStatus = " + searchInfo.getCtStatus() + " and a.ctReceiveDept = " + up.getDept() + " and a.ctReceivePost = " + up.getPost() + ")";
						}					
					}
				}
			}
			
			//副总经理，跨部门
			if(user.isHavePost(Post.POST_VICE_GENERAL_MANAGER))
			{
				String deptList = user.getDeptSQLListByPost(Post.POST_VICE_GENERAL_MANAGER);
				String sql = "select a from CheckTask a left join a.project left join a.contract left join a.projectApprovalRecord left join a.projectBudget where (a.ctStatus = " + searchInfo.getCtStatus() +
				" and a.ctReceivePost = " + Post.POST_VICE_GENERAL_MANAGER + " and a.ctReceiveDept in (" + deptList + "))";
				
				if(hqlStr.isEmpty())
				{
					hqlStr = sql;
				}
				else
				{
					hqlStr = hqlStr + " or (a.ctStatus = " + searchInfo.getCtStatus() +
							" and a.ctReceivePost = " + Post.POST_VICE_GENERAL_MANAGER + " and a.ctReceiveDept in (" + deptList + "))";
				}
			}			
			
			if(!user.haveNoAnyPost())
			{				
				for(UsrPost up:user.getUsrPostList())
				{
					Integer post = up.getPost();
					//除去上面已经考虑的职务外，其他职务的
					if(up.getDept() != null && post != null && !post.equals(Post.POST_VICE_GENERAL_MANAGER) && !post.equals(Post.POST_PROJECT_MANAGER) &&
							!post.equals(Post.POST_PROJECT_SECRETARY) && !post.equals(Post.POST_ASSIST_DEPT_MANAGER) && !post.equals(Post.POST_BUSINESS_DEPT_MANAGER)
							&& !post.equals(Post.POST_PROJECT_MARKET_MANAGER))
					{
						String sql = "select a from CheckTask a left join a.project left join a.contract left join a.projectApprovalRecord left join a.projectBudget where (a.ctStatus = " + searchInfo.getCtStatus() + " and a.ctReceiveDept = " + up.getDept() +
								" and a.ctReceivePost = " + up.getPost() + ")";
						
						if(hqlStr.isEmpty())
						{
							hqlStr = sql;
						}
						else
						{
							hqlStr = hqlStr + " or (a.ctStatus = " + searchInfo.getCtStatus() + " and a.ctReceiveDept = " + up.getDept() +
									" and a.ctReceivePost = " + up.getPost() + ")";
						}
					}					
				}								
			}				
			else
			{
				hqlStr = "select a from CheckTask a left join a.project left join a.contract left join a.projectApprovalRecord left join a.projectBudget where a.ctStatus = 99";
			}		
		}
		
		
		hqlStr = hqlStr.replace("where", "where (");
		hqlStr = hqlStr + ") ";
						
		return hqlStr;
	}

	@Override
	public List<CheckTask> searchCheckTask(Object[] args) {
		// TODO Auto-generated method stub
		CheckTask searchInfo = (CheckTask) args[0];
		String page = (String) args[1];
		Account user = (Account) args[2];
		
		String hqlStr = this.getCheckTaskSQL(searchInfo, user);
				
		if(searchInfo.getCtReceiveDept() != null)
		{
			hqlStr += " and a.ctReceiveDept = " + searchInfo.getCtReceiveDept(); 
		}
		if(searchInfo.getSendUser().getPerson().getPersonName() != null && !searchInfo.getSendUser().getPerson().getPersonName().isEmpty())
		{
			hqlStr += " and a.sendUser.person.personName like '%" + searchInfo.getSendUser().getPerson().getPersonName() + "%'"; 
		}	
		if(searchInfo.getContract() != null && searchInfo.getContract().getPcTitle() != null && !searchInfo.getContract().getPcTitle().isEmpty())
		{
			hqlStr += " and (a.contract.pcTitle like '%" + searchInfo.getContract().getPcTitle() + "%'";
			hqlStr += " or a.projectApprovalRecord.parProjectName like '%" + searchInfo.getContract().getPcTitle() + "%')"; 
		}
		if(searchInfo.getSendTimeBegin() != null && !searchInfo.getSendTimeBegin().isEmpty())
		{
			hqlStr += " and a.ctSendTime >= '" + searchInfo.getSendTimeBegin() + "'"; 
		}
		if(searchInfo.getSendTimeEnd() != null && !searchInfo.getSendTimeEnd().isEmpty())
		{
			hqlStr += " and a.ctSendTime <= '" + searchInfo.getSendTimeEnd() + "'"; 
		}
		if(searchInfo.getCheckTimeBegin() != null && !searchInfo.getCheckTimeBegin().isEmpty())
		{
			hqlStr += " and a.ctCheckTime >= '" + searchInfo.getCheckTimeBegin() + "'"; 
		}
		if(searchInfo.getCheckTimeEnd() != null && !searchInfo.getCheckTimeEnd().isEmpty())
		{
			hqlStr += " and a.ctCheckTime <= '" + searchInfo.getCheckTimeEnd() + "'"; 
		}
		if(searchInfo.getCtTaskType() != null)
		{
			hqlStr += " and a.ctTaskType = " + searchInfo.getCtTaskType(); 
		}
		
    	
    	MyQuery myQuery = new MyQuery();
    	myQuery.setPageSize(searchInfo.getPageItemCount());
    	
        if (StringUtils.isNumeric(page)) {
        	myQuery.setPageStartNo(Integer.parseInt(page));
        }else {
        	myQuery.setPageStartNo(0);
        }
        
        if(searchInfo.getOrderColumn().isEmpty())
        {
        	myQuery.setOrderby(" order by a.ctId desc");
        }
        else
        {
        	if(searchInfo.isAscFlag())
        	{
        		myQuery.setOrderby(" order by a." + searchInfo.getOrderColumn());
        	}
        	else
        	{
        		myQuery.setOrderby(" order by a." + searchInfo.getOrderColumn() + " desc");
        	}
        }        	
        
        myQuery.setQueryString(hqlStr);

        myQuery.setOffset(true);
        
        return checkTaskDAO.find(myQuery);		
	}


	@Override
	public int searchCheckTaskCount(Object[] args) {
		// TODO Auto-generated method stub
		CheckTask searchInfo = (CheckTask) args[0];			
		Account user = (Account) args[2];
		
		String hqlStr = this.getCheckTaskSQL(searchInfo, user);
		hqlStr = hqlStr.replaceAll("select a","select count(a)");
		
		if(searchInfo.getCtReceiveDept() != null)
		{
			hqlStr += " and a.ctReceiveDept = " + searchInfo.getCtReceiveDept(); 
		}
		if(searchInfo.getSendUser().getPerson().getPersonName() != null && !searchInfo.getSendUser().getPerson().getPersonName().isEmpty())
		{
			hqlStr += " and a.sendUser.person.personName like '%" + searchInfo.getSendUser().getPerson().getPersonName() + "%'"; 
		}	
		if(searchInfo.getContract() !=null && searchInfo.getContract().getPcTitle() != null && !searchInfo.getContract().getPcTitle().isEmpty())
		{
			hqlStr += " and (a.contract.pcTitle like '%" + searchInfo.getContract().getPcTitle() + "%'";
			hqlStr += " or a.projectApprovalRecord.parProjectName like '%" + searchInfo.getContract().getPcTitle() + "%')"; 
		}
		if(searchInfo.getSendTimeBegin() != null && !searchInfo.getSendTimeBegin().isEmpty())
		{
			hqlStr += " and a.ctSendTime >= '" + searchInfo.getSendTimeBegin() + "'"; 
		}
		if(searchInfo.getSendTimeEnd() != null && !searchInfo.getSendTimeEnd().isEmpty())
		{
			hqlStr += " and a.ctSendTime <= '" + searchInfo.getSendTimeEnd() + "'"; 
		}
		if(searchInfo.getCheckTimeBegin() != null && !searchInfo.getCheckTimeBegin().isEmpty())
		{
			hqlStr += " and a.ctCheckTime >= '" + searchInfo.getCheckTimeBegin() + "'"; 
		}
		if(searchInfo.getCheckTimeEnd() != null && !searchInfo.getCheckTimeEnd().isEmpty())
		{
			hqlStr += " and a.ctCheckTime <= '" + searchInfo.getCheckTimeEnd() + "'"; 
		}
		if(searchInfo.getCtTaskType() != null)
		{
			hqlStr += " and a.ctTaskType = " + searchInfo.getCtTaskType(); 
		}
					
		MyQuery myQuery = new MyQuery();
        myQuery.setQueryString(hqlStr);
            
        return checkTaskDAO.getFindCount(myQuery);	
	}

	@Override
	public CheckTask getCheckTaskById(Integer taskId) {
		// TODO Auto-generated method stub
		return checkTaskDAO.get(taskId);
	}


	@Override
	public void updateCheckTask(CheckTask checkTaskInfo) {
		// TODO Auto-generated method stub
		checkTaskDAO.update(checkTaskInfo);
	}


	@Override
	public List<CheckTask> getNextStepCheckTask(CheckTask currentTask) {
		// TODO Auto-generated method stub
		String hqlStr = "";
		if(currentTask.getCtTaskType().equals(CheckTask.TASK_TYPE_PROJECT_CONTRACT_CHECK))
		{
			hqlStr = "from CheckTask a where a.ctProject = " + currentTask.getCtProject() + " and a.ctTaskType = " + currentTask.getCtTaskType() +
			" and a.ctContract = " + currentTask.getCtContract() + " and a.ctStatus = " + CheckTask.TASK_STATUS_INIT + " and a.ctCheckStep = " + (currentTask.getCtCheckStep() +1);			
		}
		else if(currentTask.getCtTaskType().equals(CheckTask.TASK_TYPE_PROJECT_APPROVAL_RECORD_CHECK) || currentTask.getCtTaskType().equals(CheckTask.TASK_TYPE_PROJECT_APPROVAL_TENDER_CHECK))
		{
			hqlStr = "from CheckTask a where a.ctProjectApprovalRecord = " + currentTask.getCtProjectApprovalRecord() + " and a.ctTaskType = " + currentTask.getCtTaskType() +
			" and a.ctStatus = " + CheckTask.TASK_STATUS_INIT + " and a.ctCheckStep = " + (currentTask.getCtCheckStep() +1);	
		}
		else if(currentTask.getCtTaskType().equals(CheckTask.TASK_TYPE_TENDER_BUDGET_CHECK) || currentTask.getCtTaskType().equals(CheckTask.TASK_TYPE_APPLY_BUDGET_CHECK))
		{
			hqlStr = "from CheckTask a where a.ctProjectBudget = " + currentTask.getCtProjectBudget() + " and a.ctTaskType = " + currentTask.getCtTaskType() +
					" and a.ctStatus = " + CheckTask.TASK_STATUS_INIT + " and a.ctCheckStep = " + (currentTask.getCtCheckStep() +1);
		}
		else if(currentTask.getCtTaskType().equals(CheckTask.TASK_TYPE_TENDER_DOCUMENT_CHECK))
		{
			hqlStr = "from CheckTask a where a.ctProject = " + currentTask.getCtProject() + " and a.ctTaskType = " + currentTask.getCtTaskType() +
			" and a.ctStatus = " + CheckTask.TASK_STATUS_INIT + " and a.ctCheckStep = " + (currentTask.getCtCheckStep() +1);			
		}
		
		MyQuery myQuery = new MyQuery();
			
	    myQuery.setOrderby(" order by a.ctId");
	    myQuery.setQueryString(hqlStr);
		    
	    return checkTaskDAO.find(myQuery);
	}


	@Override
	public boolean isCurrentStepCheckTaskFinished(CheckTask currentTask) {
		// TODO Auto-generated method stub
		boolean rtn = true;
		String hqlStr = "";
		if(currentTask.getCtTaskType().equals(CheckTask.TASK_TYPE_PROJECT_CONTRACT_CHECK))
		{
			hqlStr = "from CheckTask a where a.ctProject = " + currentTask.getCtProject() + " and a.ctTaskType = " + currentTask.getCtTaskType() +
			" and a.ctContract = " + currentTask.getCtContract() + " and a.ctCheckStep = " + currentTask.getCtCheckStep();		
		}
		else if(currentTask.getCtTaskType().equals(CheckTask.TASK_TYPE_TENDER_BUDGET_CHECK) || currentTask.getCtTaskType().equals(CheckTask.TASK_TYPE_APPLY_BUDGET_CHECK))
		{
			hqlStr = "from CheckTask a where a.ctProject = " + currentTask.getCtProject() + " and a.ctTaskType = " + currentTask.getCtTaskType() +
			" and a.ctProjectBudget = " + currentTask.getCtProjectBudget() + " and a.ctCheckStep = " + currentTask.getCtCheckStep();
		}
		else  if(currentTask.getCtTaskType().equals(CheckTask.TASK_TYPE_PROJECT_APPROVAL_RECORD_CHECK) || currentTask.getCtTaskType().equals(CheckTask.TASK_TYPE_PROJECT_APPROVAL_TENDER_CHECK))
		{
			hqlStr = "from CheckTask a where a.ctProjectApprovalRecord = " + currentTask.getCtProjectApprovalRecord() + " and a.ctTaskType = " + currentTask.getCtTaskType() +
			" and a.ctCheckStep = " + currentTask.getCtCheckStep();	
		}
		else  if(currentTask.getCtTaskType().equals(CheckTask.TASK_TYPE_TENDER_DOCUMENT_CHECK))
		{
			hqlStr = "from CheckTask a where a.ctProject = " + currentTask.getCtProject() + " and a.ctTaskType = " + currentTask.getCtTaskType() +
			" and a.ctCheckStep = " + currentTask.getCtCheckStep();
		}
			      	    			
		MyQuery myQuery = new MyQuery();
			
	    myQuery.setOrderby(" order by a.ctId");
	    myQuery.setQueryString(hqlStr);
		    
	    List<CheckTask> taskList =  checkTaskDAO.find(myQuery);
	    for(CheckTask ct:taskList)
	    {
	    	if(ct.getCtStatus().equals(CheckTask.TASK_STATUS_CURRENT_CHECK))
	    	{
	    		rtn = false;
	    		break;
	    	}	    	
	    }
	    	    
	    return rtn;	    
	}


	@Override
	public List<CheckTask> getAllStepCheckTask(CheckTask currentTask) {
		// TODO Auto-generated method stub
		
		String hqlStr = "";
		if(currentTask.getCtTaskType().equals(CheckTask.TASK_TYPE_PROJECT_CONTRACT_CHECK))
		{
			hqlStr = "from CheckTask a where a.ctProject = " + currentTask.getCtProject() + " and a.ctTaskType = " + currentTask.getCtTaskType() +
			" and a.ctContract = " + currentTask.getCtContract();
		}		
		else if(currentTask.getCtTaskType().equals(CheckTask.TASK_TYPE_PROJECT_APPROVAL_RECORD_CHECK) || currentTask.getCtTaskType().equals(CheckTask.TASK_TYPE_PROJECT_APPROVAL_TENDER_CHECK))
		{
			hqlStr = "from CheckTask a where a.ctProjectApprovalRecord = " + currentTask.getCtProjectApprovalRecord() + " and a.ctTaskType = " + currentTask.getCtTaskType();
		}
		else
		{
			hqlStr = "from CheckTask a where a.ctProject = " + currentTask.getCtProject() + " and a.ctTaskType = " + currentTask.getCtTaskType();
		}
							      	    			
		MyQuery myQuery = new MyQuery();
			
	    myQuery.setOrderby(" order by a.ctId");
	    myQuery.setQueryString(hqlStr);
		    
	    return checkTaskDAO.find(myQuery);
	}


	@Override
	public List<CheckTask> getAllStepCheckTask(ProjectContract projectContract) {
		// TODO Auto-generated method stub
		String hqlStr = "from CheckTask a where a.ctProject = " + projectContract.getPcProject() + " and a.ctTaskType = " + CheckTask.TASK_TYPE_PROJECT_CONTRACT_CHECK +
			" and a.ctContract = " + projectContract.getPcId();			
				      	    			
		MyQuery myQuery = new MyQuery();
			
	    myQuery.setOrderby(" order by a.ctId");
	    myQuery.setQueryString(hqlStr);
		    
	    return checkTaskDAO.find(myQuery);
	}
	
	@Override
	public List<CheckTask> getAllStepCheckTask(ProjectApprovalRecord par) {
		// TODO Auto-generated method stub
		String hqlStr = "";
		
		if(par.getParStatus() >= 4)
		{
			hqlStr = "from CheckTask a where a.ctTaskType = " + CheckTask.TASK_TYPE_PROJECT_APPROVAL_TENDER_CHECK + " and a.ctProjectApprovalRecord = " + par.getParId();
		}
		else
		{
			hqlStr = "from CheckTask a where a.ctTaskType = " + CheckTask.TASK_TYPE_PROJECT_APPROVAL_RECORD_CHECK + " and a.ctProjectApprovalRecord = " + par.getParId();
		}
					
				      	    			
		MyQuery myQuery = new MyQuery();
			
	    myQuery.setOrderby(" order by a.ctId");
	    myQuery.setQueryString(hqlStr);
		    
	    return checkTaskDAO.find(myQuery);
	}


	@Override
	public List<CheckTask> searchAboutCheckTask(Object[] args) {
		// TODO Auto-generated method stub
		CheckTask searchInfo = (CheckTask) args[0];	
		Account user = (Account) args[1];
		
		String hqlStr = this.getCheckTaskSQL(searchInfo, user);										      	    	
    	
    	MyQuery myQuery = new MyQuery();    	
        myQuery.setOrderby(" order by a.ctId desc");
        myQuery.setQueryString(hqlStr);
        myQuery.setPageSize(10);
        myQuery.setOffset(true);
        myQuery.setPageStartNo(0);
        
        return checkTaskDAO.find(myQuery);	
	}


	@Override
	public AccountSaleContract getAccountSaleContractById(Integer id) {
		// TODO Auto-generated method stub
		return accountSaleContractDAO.get(id);
	}


	@Override
	public ProjectAttachment getProjectAttachmentById(Integer id) {
		// TODO Auto-generated method stub
		return projectAttachmentDAO.get(id);
	}
	
	@Override
	public TenderAttachment getTenderAttachmentById(Integer id) {
		// TODO Auto-generated method stub
		return tenderAttachmentDAO.get(id);
	}


	@Override
	public void deleteProjectApprovalRecord(ProjectApprovalRecord par) {
		// TODO Auto-generated method stub
		par.setParStatus(CommonService.DELETE_FLAG);		
		projectApprovalRecordDAO.update(par);
	}


	@Override
	public ProjectApprovalRecord getProjectApprovalRecordById(Integer id) {
		// TODO Auto-generated method stub
		return projectApprovalRecordDAO.get(id);
	}
	
	@Override
	public void saveProjectApprovalRecord(ProjectApprovalRecord par,String uploadPath) {
		// TODO Auto-generated method stub
		Date createTime = new Date();
		
		ArrayList<ProjectAttachment> attachmentList = par.getAttachmentList();
		par.setAttachmentList(new ArrayList<ProjectAttachment>());
		if(par.getParId() == null)
		{
			par.setParCreateTime(new Date());
			projectApprovalRecordDAO.save(par);
		}
		else
		{
			projectApprovalRecordDAO.update(par);
		}
		
		par.setAttachmentList(attachmentList);
		
		for(ProjectAttachment pa:attachmentList)
		{
			if(pa.getPaId() == null)
			{
				String fileName = FileUtil.saveUploadFile(pa.getAttachmentFile(),uploadPath + par.getParId());				
				pa.setPaProjectApprovalRecord(par.getParId());
				pa.setPaCreateTime(createTime);
				pa.setPaUrl(uploadPath + par.getParId() + "\\" + fileName);
				pa.setPaSubmitDepartment(par.getParCommitDept());	
												
				projectAttachmentDAO.save(pa);
				
				int count = this.getProjectAttachmentCountByCategory(pa.getPaAttachmentCategory());
				pa.setPaCode(CommonServiceImpl.getProjectAttachmentCode(pa.getPaAttachmentCategory(),count));
				projectAttachmentDAO.update(pa);
			}	
			else if(pa.isModified())
			{
				projectAttachmentDAO.update(pa);
				pa.setModified(false);
			}
		}	
	}
	
	@Override
	public void updateProjectAttachmentCode(ArrayList<ProjectAttachment> attachmentList)
	{		
		for(ProjectAttachment pa:attachmentList)
		{
			if(pa.getPaCode() == null || pa.getPaCode().isEmpty())
			{				
				int count = this.getProjectAttachmentCountByCategory(pa.getPaAttachmentCategory());			
				
				pa.setPaCode(CommonServiceImpl.getProjectAttachmentCode(pa.getPaAttachmentCategory(),count));
				projectAttachmentDAO.update(pa);
			}				
		}
	}
	
	@Override
	public void updateTenderAttachmentCode(ArrayList<TenderAttachment> attachmentList)
	{		
		for(TenderAttachment pa:attachmentList)
		{
			if(pa.getTaCode() == null || pa.getTaCode().isEmpty())
			{				
				int count = this.getProjectAttachmentCountByCategory(pa.getTaAttachmentCategory());			
				
				pa.setTaCode(CommonServiceImpl.getProjectAttachmentCode(pa.getTaAttachmentCategory(),count));
				tenderAttachmentDAO.update(pa);
			}				
		}
	}

	@Override
	public void updateProjectApprovalRecord(ProjectApprovalRecord par) {
		// TODO Auto-generated method stub		
		projectApprovalRecordDAO.update(par);				
	}

	@Override
	public int searchProjectApprovalRecordCount(Object[] args) {
		// TODO Auto-generated method stub
		ProjectApprovalRecord searchInfo = (ProjectApprovalRecord) args[1];
		
		Account user = (Account) args[2];
		
		String hqlStr = null;
		
		if(user.getId().equals("0"))
		{
			hqlStr = "select count(a) from ProjectApprovalRecord a where 1=1";
		}
		else
		{
			hqlStr = "select count(a) from ProjectApprovalRecord a where a.parStatus != -1";
		}
						
		hqlStr = this.processProjectApprovalRecordSearchSQL(searchInfo, hqlStr);
		    	    	
    	MyQuery myQuery = new MyQuery();
        myQuery.setQueryString(hqlStr);
            
        return tenderProjectDAO.getFindCount(myQuery);	
	}
	
	private String processProjectApprovalRecordSearchSQL(ProjectApprovalRecord searchInfo,String hqlStr)
	{
		if(searchInfo.getParProjectName() != null)
		{
			hqlStr += " and a.parProjectName like '%" + searchInfo.getParProjectName() + "%'"; 
		}
		if(searchInfo.getParProjectOwner() != null)
		{
			hqlStr += " and a.parProjectOwner like '%" + searchInfo.getParProjectOwner() + "%'"; 
		}
		if(searchInfo.getParDesignCompany() != null)
		{
			hqlStr += " and a.parDesignCompany like '%" + searchInfo.getParDesignCompany() + "%'"; 
		}
		if(searchInfo.getParTenderCompany() != null)
		{
			hqlStr += " and a.parTenderCompany like '%" + searchInfo.getParTenderCompany() + "%'"; 
		}
		
		if(searchInfo.getParStatus() != null && searchInfo.getParStatus() != 0)
		{
			hqlStr += " and a.parStatus = " + searchInfo.getParStatus(); 
		}
		
		return hqlStr;
	}


	@Override
	public List<ProjectApprovalRecord> searchProjectApprovalRecordList(Object[] args) {
		// TODO Auto-generated method stub
		String page = (String) args[0];
		ProjectApprovalRecord searchInfo = (ProjectApprovalRecord) args[1];
		
		Account user = (Account) args[2];
		
		String hqlStr = null;
			
		if(user.getId().equals("0"))
		{
			hqlStr = "from ProjectApprovalRecord a where 1=1";
		}
		else
		{
			hqlStr = "from ProjectApprovalRecord a where a.parStatus != -1";
		}		
		hqlStr = this.processProjectApprovalRecordSearchSQL(searchInfo, hqlStr);
		
    	MyQuery myQuery = new MyQuery();
    	myQuery.setPageSize(searchInfo.getPageItemCount());
    	
        if (StringUtils.isNumeric(page)) {
        	myQuery.setPageStartNo(Integer.parseInt(page));
        }else {
        	myQuery.setPageStartNo(0);
        }
       
        if(searchInfo.getOrderColumn().isEmpty())
        {
        	 myQuery.setOrderby(" order by a.parId desc");
        }
        else
        {
        	if(searchInfo.isAscFlag())
        	{
        		myQuery.setOrderby(" order by a." + searchInfo.getOrderColumn());
        	}
        	else
        	{
        		myQuery.setOrderby(" order by a." + searchInfo.getOrderColumn() + " desc");
        	}
        }   
        myQuery.setQueryString(hqlStr);

        myQuery.setOffset(true);
        
        return projectApprovalRecordDAO.find(myQuery);
	}
	
	
	@Override
	public List<ProjectApprovalRecord> searchProjectApprovalCheckList(Object[] args) {
		// TODO Auto-generated method stub
		String page = (String) args[0];
		ProjectApprovalRecord searchInfo = (ProjectApprovalRecord) args[1];
				
		String hqlStr = "from ProjectApprovalRecord a where a.parStatus >= " + ProjectApprovalRecord.PAR_STATUS_APPROVAL_CHECKED;
				
		hqlStr = this.processProjectApprovalRecordSearchSQL(searchInfo, hqlStr);
		
    	MyQuery myQuery = new MyQuery();
    	myQuery.setPageSize(searchInfo.getPageItemCount());
    	
        if (StringUtils.isNumeric(page)) {
        	myQuery.setPageStartNo(Integer.parseInt(page));
        }else {
        	myQuery.setPageStartNo(0);
        }
        
        if(searchInfo.getOrderColumn().isEmpty())
        {
        	 myQuery.setOrderby(" order by a.parId desc");
        }
        else
        {
        	if(searchInfo.isAscFlag())
        	{
        		myQuery.setOrderby(" order by a." + searchInfo.getOrderColumn());
        	}
        	else
        	{
        		myQuery.setOrderby(" order by a." + searchInfo.getOrderColumn() + " desc");
        	}
        }   
        myQuery.setQueryString(hqlStr);

        myQuery.setOffset(true);
        
        return projectApprovalRecordDAO.find(myQuery);
	}
	
	@Override
	public int searchProjectApprovalCheckCount(Object[] args) {
		// TODO Auto-generated method stub
		ProjectApprovalRecord searchInfo = (ProjectApprovalRecord) args[1];
		
		
		String hqlStr = "select count(a) from ProjectApprovalRecord a where a.parStatus >= " + ProjectApprovalRecord.PAR_STATUS_APPROVAL_CHECKED;		
						
		hqlStr = this.processProjectApprovalRecordSearchSQL(searchInfo, hqlStr);
		    	    	
    	MyQuery myQuery = new MyQuery();
        myQuery.setQueryString(hqlStr);
            
        return tenderProjectDAO.getFindCount(myQuery);	
	}


	public void setProjectApprovalRecordDAO(ProjectApprovalRecordDAO projectApprovalRecordDAO) {
		this.projectApprovalRecordDAO = projectApprovalRecordDAO;
	}


	public ProjectApprovalRecordDAO getProjectApprovalRecordDAO() {
		return projectApprovalRecordDAO;
	}


	@Override
	public ProjectApprovalRecord createProjectApprovalRecord() {
		// TODO Auto-generated method stub
		ProjectApprovalRecord par = new ProjectApprovalRecord();
		par.setParStatus(ProjectApprovalRecord.PAR_STATUS_INIT);
		
		return par;
	}
	
	/**
	 * 获取立项备案审核任务列表
	 * @param parId
	 * @param user
	 * @return
	 */
	public ArrayList<CheckTask> getProjectApprovalRecordCheckStep(ProjectApprovalRecord par,Account user)
	{
		ArrayList<CheckTask> taskList = new ArrayList<CheckTask>();
		CheckTask sct = this.createUserSelfCheckTask(null, user,par.getParDept(),CheckTask.TASK_TYPE_PROJECT_APPROVAL_RECORD_CHECK);
		sct.setCtProjectApprovalRecord(par.getParId());
		taskList.add(sct);
		
		for(int i=0;i<CheckTask.CHECK_STEP_PROJECT_APPROVAL_RECORD.length;i++)
		{
			int[] deptPost = CheckTask.CHECK_STEP_PROJECT_APPROVAL_RECORD[i];
			
			CheckTask ct = new CheckTask();
			ct.setCtProjectApprovalRecord(par.getParId());
			ct.setCtCheckStep(i+2);					
			ct.setCtTaskType(CheckTask.TASK_TYPE_PROJECT_APPROVAL_RECORD_CHECK);
						
			if(deptPost[0] == -1 && par.getParImplementDept() != null)
			{
				ct.setCtReceiveDept(par.getParImplementDept());
			}
			else
			{
				ct.setCtReceiveDept(deptPost[0]);
			}
			
			this.adjustReceiveDept(ct, par.getParDept());
			
			if(deptPost[1] != 0)
			{
				ct.setCtReceivePost(deptPost[1]);
			}
			
			ct.setCtSendUser(user.getId());
			ct.setCtSendTime(new Date());
			if(i==0)
			{
				ct.setCtStatus(CheckTask.TASK_STATUS_CURRENT_CHECK);
			}
			
			taskList.add(ct);			
		}
		
		return taskList;
	}
	
	private void adjustReceiveDept(CheckTask ct,Integer dept)
	{
		if(ct.getCtReceiveDept() == 0)
		{
			ct.setCtReceiveDept(dept);
		}
		else if(ct.getCtReceiveDept() == -1)
		{
			if(dept.equals(Department.DEPARTMENT_SHI_CHANG) || dept.equals(Department.DEPARTMENT_HUAN_BAO))
			{
				ct.setCtReceiveDept(Department.DEPARTMENT_HUAN_BAO);
			}
			else if(dept.equals(Department.DEPARTMENT_XIN_NENG_YUAN) || dept.equals(Department.DEPARTMENT_JI_SHU))
			{
				ct.setCtReceiveDept(Department.DEPARTMENT_XIN_NENG_YUAN);
			}
		}
		
	}
		
	/**
	 * 获取采购合同的审核任务列表
	 * @param projectId
	 * @param contractId
	 * @param user
	 * @param subStep
	 * @return
	 */
	public ArrayList<CheckTask> getPurchaseContractCheckStep(ProjectContract contract,Account user)
	{
		ArrayList<CheckTask> taskList = new ArrayList<CheckTask>();
		CheckTask sct = this.createUserSelfCheckTask(contract.getPcProject(),user,contract.getTenderProject().getTpDept(),CheckTask.TASK_TYPE_PROJECT_CONTRACT_CHECK);
		sct.setCtContract(contract.getPcId());
		taskList.add(sct);
		
		for(int i=0;i<CheckTask.CHECK_STEP_CONTRACT_PURCHASE.length;i++)
		{
			int[] deptPost = CheckTask.CHECK_STEP_CONTRACT_PURCHASE[i];
			if(deptPost.length > 0)
			{
				CheckTask ct = new CheckTask();
				ct.setCtProject(contract.getPcProject());
				ct.setCtContract(contract.getPcId());
				ct.setCtCheckStep(i+2);					
				ct.setCtTaskType(CheckTask.TASK_TYPE_PROJECT_CONTRACT_CHECK);
								
				ct.setCtReceiveDept(deptPost[0]);
				this.adjustReceiveDept(ct, contract.getTenderProject().getTpDept());
									
				if(deptPost[1] != 0)
				{
					ct.setCtReceivePost(deptPost[1]);
				}
				
				ct.setCtSendUser(user.getId());
				ct.setCtSendTime(new Date());
				if(i==0)
				{
					ct.setCtStatus(CheckTask.TASK_STATUS_CURRENT_CHECK);
				}
				
				taskList.add(ct);
			}
			else
			{				
				for(int j=0;j<CheckTask.CHECK_STEP_CONTRACT_PURCHASE_1.length;j++)
				{
					int[] subDeptPost = CheckTask.CHECK_STEP_CONTRACT_PURCHASE_1[j];
					
					CheckTask ct = new CheckTask();
					ct.setCtProject(contract.getPcProject());
					ct.setCtContract(contract.getPcId());
					ct.setCtCheckStep(i+2);					
					ct.setCtTaskType(CheckTask.TASK_TYPE_PROJECT_CONTRACT_CHECK);						
						
					ct.setCtReceiveDept(subDeptPost[0]);
					this.adjustReceiveDept(ct, contract.getTenderProject().getTpDept());
					
					if(subDeptPost[1] != 0)
					{
						ct.setCtReceivePost(subDeptPost[1]);
					}
					ct.setCtSendUser(user.getId());
					ct.setCtSendTime(new Date());
					
					taskList.add(ct);
				}				
			}
		}
		
		return taskList;
	}	
	
	/**
	 * 获取销售合同审核任务列表
	 * @param projectId
	 * @param contractId
	 * @param user
	 * @param subStep
	 * @return
	 */
	public ArrayList<CheckTask> getSaleContractCheckStep(ProjectContract contract,Account user)
	{
		ArrayList<CheckTask> taskList = new ArrayList<CheckTask>();		
		CheckTask sct = this.createUserSelfCheckTask(contract.getPcProject(),user,contract.getTenderProject().getTpDept(),CheckTask.TASK_TYPE_PROJECT_CONTRACT_CHECK);
		sct.setCtContract(contract.getPcId());
		taskList.add(sct);
		
		for(int i=0;i<CheckTask.CHECK_STEP_CONTRACT_SALE.length;i++)
		{
			int[] deptPost = CheckTask.CHECK_STEP_CONTRACT_SALE[i];
			if(deptPost.length > 0)
			{
				CheckTask ct = new CheckTask();
				ct.setCtProject(contract.getPcProject());
				ct.setCtContract(contract.getPcId());
				ct.setCtCheckStep(i+2);					
				ct.setCtTaskType(CheckTask.TASK_TYPE_PROJECT_CONTRACT_CHECK);
								
				ct.setCtReceiveDept(deptPost[0]);
				this.adjustReceiveDept(ct, contract.getTenderProject().getTpDept());
				
				if(deptPost[1] != 0)
				{
					ct.setCtReceivePost(deptPost[1]);
				}
				
				ct.setCtSendUser(user.getId());
				ct.setCtSendTime(new Date());
				if(i==0)
				{
					ct.setCtStatus(CheckTask.TASK_STATUS_CURRENT_CHECK);
				}
				
				taskList.add(ct);
			}
			else
			{				
				for(int j=0;j<CheckTask.CHECK_STEP_CONTRACT_SALE_1.length;j++)
				{
					int[] subDeptPost = CheckTask.CHECK_STEP_CONTRACT_SALE_1[j];
					
					CheckTask ct = new CheckTask();
					ct.setCtProject(contract.getPcProject());
					ct.setCtContract(contract.getPcId());
					ct.setCtCheckStep(i+2);					
					ct.setCtTaskType(CheckTask.TASK_TYPE_PROJECT_CONTRACT_CHECK);						
								
					ct.setCtReceiveDept(subDeptPost[0]);
					this.adjustReceiveDept(ct, contract.getTenderProject().getTpDept());
					
					if(subDeptPost[1] != 0)
					{
						ct.setCtReceivePost(subDeptPost[1]);
					}
					ct.setCtSendUser(user.getId());
					ct.setCtSendTime(new Date());
					
					taskList.add(ct);
				}				
			}
		}
		
		return taskList;
	}
		
	
	
	
	/**
	 * 获取立项备案投标审核任务列表
	 * @param parId
	 * @param user
	 * @return
	 */
	public ArrayList<CheckTask> getProjectApprovalTenderCheckStep(ProjectApprovalRecord par,Account user)
	{
		ArrayList<CheckTask> taskList = new ArrayList<CheckTask>();
		CheckTask sct = this.createUserSelfCheckTask(null, user,par.getParDept(),CheckTask.TASK_TYPE_PROJECT_APPROVAL_TENDER_CHECK);
		sct.setCtProjectApprovalRecord(par.getParId());
		taskList.add(sct);
		
		for(int i=0;i<CheckTask.CHECK_STEP_PROJECT_TENDER.length;i++)
		{
			int[] deptPost = CheckTask.CHECK_STEP_PROJECT_TENDER[i];
			
			if(deptPost.length > 0)
			{
				CheckTask ct = new CheckTask();
				ct.setCtProjectApprovalRecord(par.getParId());
				ct.setCtCheckStep(i+2);					
				ct.setCtTaskType(CheckTask.TASK_TYPE_PROJECT_APPROVAL_TENDER_CHECK);
							
				if(deptPost[0] == -1 && par.getParImplementDept() != null)
				{
					ct.setCtReceiveDept(par.getParImplementDept());
				}
				else
				{
					ct.setCtReceiveDept(deptPost[0]);
				}
				
				this.adjustReceiveDept(ct, par.getParDept());
				
				if(deptPost[1] != 0)
				{
					ct.setCtReceivePost(deptPost[1]);
				}
				
				ct.setCtSendUser(user.getId());
				ct.setCtSendTime(new Date());
				if(i==0)
				{
					ct.setCtStatus(CheckTask.TASK_STATUS_CURRENT_CHECK);
				}
				
				taskList.add(ct);
			}
			else
			{
				for(int j=0;j<CheckTask.CHECK_STEP_PROJECT_TENDER_1.length;j++)
				{
					int[] subDeptPost = CheckTask.CHECK_STEP_PROJECT_TENDER_1[j];
					
					CheckTask ct = new CheckTask();
					ct.setCtProjectApprovalRecord(par.getParId());
					ct.setCtCheckStep(i+2);					
					ct.setCtTaskType(CheckTask.TASK_TYPE_PROJECT_APPROVAL_TENDER_CHECK);	
					
					ct.setCtReceiveDept(subDeptPost[0]);
					this.adjustReceiveDept(ct, par.getParDept());		
					
					if(subDeptPost[1] != 0)
					{
						ct.setCtReceivePost(subDeptPost[1]);
					}
					ct.setCtSendUser(user.getId());
					ct.setCtSendTime(new Date());
					
					taskList.add(ct);
				}
			}
						
		}
		
		return taskList;
	}
	
	/**
	 * 发送评审任务mail
	 * @param mailList
	 * @param ct
	 */
	public void sendCheckTaskMail(String mailList,CheckTask ct)
	{
		String mailTitle = "";
		String mailContent = "您好，有一项评审需要您尽快处理。<br>";
		
		if(!mailList.isEmpty())
		{
			//获取mail主题、内容			
			if(ct.getCtTaskType().equals(CheckTask.TASK_TYPE_PROJECT_CONTRACT_CHECK))
			{
				mailTitle = "合同评审";
				TenderProject tp = ct.getProject();
				if(tp == null)
				{
					tp = this.getProjectById(ct.getCtProject());
				}
				for(ProjectContract pc:tp.getProjectContractList())
				{
					if(pc.getPcId().equals(ct.getCtContract()))
					{
						mailContent = mailContent + mailTitle + ":" + pc.getPcTitle();
						
						break;
					}
				}				
			}
			else if(ct.getCtTaskType().equals(CheckTask.TASK_TYPE_PROJECT_APPROVAL_RECORD_CHECK))
			{
				mailTitle = "立项备案评审";
				ProjectApprovalRecord par = ct.getProjectApprovalRecord();
				if(par == null)
				{
					par = this.getProjectApprovalRecordById(ct.getCtProjectApprovalRecord());
				}	
				mailContent = mailContent + mailTitle + ":" + par.getParProjectName();
			}
			else if(ct.getCtTaskType().equals(CheckTask.TASK_TYPE_PROJECT_APPROVAL_TENDER_CHECK))
			{
				mailTitle = "投标评审";
				ProjectApprovalRecord par = ct.getProjectApprovalRecord();
				if(par == null)
				{
					par = this.getProjectApprovalRecordById(ct.getCtProjectApprovalRecord());
				}	
				mailContent = mailContent + mailTitle + ":" + par.getParProjectName();
			}
			//发送评审mail
			try {
				if(mailList.contains("@"))
				{
					MailTool.sendCheckTaskMail(mailList, mailTitle, mailContent + "<br>" + new Date());
				}				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取评审人员的mail
	 * @param ct
	 * @return
	 */
	public String getChecktaskMail(CheckTask ct)
	{
		String mailList = "";
		
		Integer dept = ct.getCtReceiveDept();
		Integer post = ct.getCtReceivePost();
		
		if(dept.equals(0))
		{
			if(ct.getCtTaskType().equals(CheckTask.TASK_TYPE_PROJECT_APPROVAL_RECORD_CHECK) || ct.getCtTaskType().equals(CheckTask.TASK_TYPE_PROJECT_APPROVAL_TENDER_CHECK)) 
			{
				ProjectApprovalRecord par = ct.getProjectApprovalRecord();
				if(par == null)
				{
					par = this.getProjectApprovalRecordById(ct.getCtProjectApprovalRecord());
				}					
				dept = par.getParDept();
			}
			else
			{
				TenderProject tp = ct.getProject();
				if(tp == null)
				{
					tp = this.getProjectById(ct.getCtProject());
				}				
				dept = tp.getTpDept();
			}
		}
		
		if(post == null || post.equals(0))
		{
			post = Post.POST_BUSINESS_DEPT_MANAGER;
		}
		
		Account sa = new Account();
		if(post.equals(Post.POST_PROJECT_MANAGER))
		{
			String manager = "";
			if(ct.getCtTaskType().equals(CheckTask.TASK_TYPE_PROJECT_APPROVAL_RECORD_CHECK) || ct.getCtTaskType().equals(CheckTask.TASK_TYPE_PROJECT_APPROVAL_TENDER_CHECK))
			{
				ProjectApprovalRecord par = ct.getProjectApprovalRecord();
				if(par == null)
				{
					par = this.getProjectApprovalRecordById(ct.getCtProjectApprovalRecord());
				}	
				manager = par.getParManager();
			}
			else
			{
				TenderProject tp = ct.getProject();
				if(tp == null)
				{
					tp = this.getProjectById(ct.getCtProject());
				}
				manager = tp.getTpManager();
			}
			
			mailList = accountDAO.get(manager).getPerson().getEmail();
		}
		else
		{
			sa.getPerson().setDept(dept);
			sa.getPerson().setPost(post);	
									
			Object[] args = {sa,"0",null};
			try {							
				List<Account> userList = this.findAccount(args);
				for(Account a:userList)
				{
					if(a.getPerson().getEmail() != null)
					{
						mailList = mailList + MailTool.MAIL_ADDRESS_SEPARATER + a.getPerson().getEmail();
					}
				}							
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
				
		return mailList;
	}

	public List<Account> findAccount(Object[] args) throws BusinessException {
    	Account accountInfo = (Account) args[0];
    	String page = (String) args[1];
    	Account user = (Account)args[2];
        
    	String hqlStr = "";
    	if(user != null && user.getId().equals("0"))
    	{
    		hqlStr = "select distinct a from Account a,UsrPost b where a.id=b.usrId";
    	}
    	else
    	{
    		hqlStr = "select distinct a from Account a,UsrPost b where a.id=b.usrId and a.flag != " + CommonService.DELETE_FLAG;
    	}
    	 
    	hqlStr += (StringUtils.isNotEmpty(accountInfo.getName())?" and a.name like '%" + accountInfo.getName() + "%'" : "");
    	
    	hqlStr += (StringUtils.isNotEmpty(accountInfo.getPerson().getPersonName())?" and a.person.personName like '%" + accountInfo.getPerson().getPersonName() + "%'" : "");
    	
    	hqlStr += (accountInfo.getPerson().getDept() != null)?" and b.dept = " + accountInfo.getPerson().getDept() : "";
    	hqlStr += (accountInfo.getPerson().getPost() != null)?" and b.post = " + accountInfo.getPerson().getPost() : "";
    	
    	MyQuery myQuery = new MyQuery();
    	myQuery.setQueryString(hqlStr);
    	myQuery.setPageSize(accountInfo.getPageItemCount());
    	 
        if (StringUtils.isNumeric(page)) {
        	myQuery.setPageStartNo(Integer.parseInt(page));
        }else {
        	myQuery.setPageStartNo(0);
        }
        myQuery.setOrderby(" order by a.name");
       
        myQuery.setOffset(true);
        
        return accountDAO.find(myQuery);        
	}

	public AccountDAO getAccountDAO() {
		return accountDAO;
	}


	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	/**
	 * 创建createUser的审核记录
	 * @param projectId
	 * @param contractId
	 * @param user
	 * @return
	 */
	private CheckTask createUserSelfCheckTask(Integer projectId,Account user,Integer dept,int taskType)
	{
		CheckTask ct = new CheckTask();
		ct.setCtProject(projectId);
		ct.setCtCheckStep(1);					
		ct.setCtTaskType(taskType);
		ct.setCtReceiveDept(dept);					
		ct.setCtReceivePost(1);		
		ct.setCtStatus(CheckTask.TASK_STATUS_CHECKED);
		ct.setCtCheckResult(CheckTask.TASK_CHECK_RESULT_OK);
		ct.setCtSendUser(user.getId());
		ct.setCtSendTime(new Date());
		ct.setCtCheckTime(ct.getCtSendTime());
		ct.setCtCheckUser(user.getId());
		
		return ct;
	}

	@Override
	public ArrayList<CheckTask> getTenderBudgetCheckStep(ProjectBudget tenderBudget, Account user, Integer dept) {
		// TODO Auto-generated method stub
		ArrayList<CheckTask> taskList = new ArrayList<CheckTask>();
		CheckTask sct = this.createUserSelfCheckTask(tenderBudget.getPbProject(), user,dept,CheckTask.TASK_TYPE_TENDER_BUDGET_CHECK);
		sct.setCtProjectBudget(tenderBudget.getPbId());
		taskList.add(sct);
		
		if(tenderBudget.getPbOnAccount().equals(ProjectBudget.BUDGET_PAYMENT_TYPE_DELAYPAY))
		{
			for(int i=0;i<CheckTask.CHECK_STEP_TENDER_BUDGET_DELAYPAY.length;i++)
			{
				this.createBudgetCheckStep(taskList,CheckTask.CHECK_STEP_TENDER_BUDGET_DELAYPAY[i],i, tenderBudget, user, dept);					
			}			
		}
		else if(tenderBudget.getPbOnAccount().equals(ProjectBudget.BUDGET_PAYMENT_TYPE_PREPAY))
		{
			for(int i=0;i<CheckTask.CHECK_STEP_TENDER_BUDGET_PREPAY.length;i++)
			{
				this.createBudgetCheckStep(taskList,CheckTask.CHECK_STEP_TENDER_BUDGET_PREPAY[i],i, tenderBudget, user, dept);					
			}
		}		
		
		return taskList;
	}
	
	private void createBudgetCheckStep(ArrayList<CheckTask> taskList,int[] deptPost,int i,ProjectBudget tenderBudget, Account user, Integer dept)	
	{		
		if(deptPost.length > 0)
		{
			CheckTask ct = new CheckTask();
			ct.setCtProject(tenderBudget.getPbProject());
			ct.setCtCheckStep(i+2);					
			ct.setCtTaskType(CheckTask.TASK_TYPE_TENDER_BUDGET_CHECK);
			ct.setCtProjectBudget(tenderBudget.getPbId());
			
			ct.setCtReceiveDept(deptPost[0]);
			this.adjustReceiveDept(ct, dept);
			
			if(deptPost[1] != 0)
			{
				ct.setCtReceivePost(deptPost[1]);
			}
			
			ct.setCtSendUser(user.getId());
			ct.setCtSendTime(new Date());
			if(i==0)
			{
				ct.setCtStatus(CheckTask.TASK_STATUS_CURRENT_CHECK);
			}
			
			taskList.add(ct);	
		}
		else
		{
			for(int j=0;j<CheckTask.CHECK_STEP_TENDER_BUDGET_1.length;j++)
			{
				int[] subDeptPost = CheckTask.CHECK_STEP_TENDER_BUDGET_1[j];
				
				CheckTask ct = new CheckTask();
				ct.setCtProject(tenderBudget.getPbProject());
				ct.setCtCheckStep(i+2);					
				ct.setCtTaskType(CheckTask.TASK_TYPE_TENDER_BUDGET_CHECK);	
				ct.setCtProjectBudget(tenderBudget.getPbId());
				
				ct.setCtReceiveDept(subDeptPost[0]);
				this.adjustReceiveDept(ct, dept);
				
				if(subDeptPost[1] != 0)
				{
					ct.setCtReceivePost(subDeptPost[1]);
				}
				ct.setCtSendUser(user.getId());
				ct.setCtSendTime(new Date());
				
				taskList.add(ct);
			}
		}
	}

	@Override
	public ArrayList<CheckTask> getApplyBudgetCheckStep(ProjectBudget applyBudget, Account user, Integer dept) {
		// TODO Auto-generated method stub
		ArrayList<CheckTask> taskList = new ArrayList<CheckTask>();
		CheckTask sct = this.createUserSelfCheckTask(applyBudget.getPbProject(), user,dept,CheckTask.TASK_TYPE_APPLY_BUDGET_CHECK);
		sct.setCtProjectBudget(applyBudget.getPbId());
		taskList.add(sct);
		int onetwoFlag = 0;
		for(int i=0;i<CheckTask.CHECK_STEP_APPLY_BUDGET.length;i++)
		{
			int[] deptPost = CheckTask.CHECK_STEP_APPLY_BUDGET[i];
			
			if(deptPost.length > 0)
			{
				CheckTask ct = new CheckTask();
				ct.setCtProject(applyBudget.getPbProject());
				ct.setCtCheckStep(i+2);					
				ct.setCtTaskType(CheckTask.TASK_TYPE_APPLY_BUDGET_CHECK);
				ct.setCtProjectBudget(applyBudget.getPbId());
				
				ct.setCtReceiveDept(deptPost[0]);
				this.adjustReceiveDept(ct, dept);
				
				if(deptPost[1] != 0)
				{
					ct.setCtReceivePost(deptPost[1]);
				}
				
				ct.setCtSendUser(user.getId());
				ct.setCtSendTime(new Date());
				if(i==0)
				{
					ct.setCtStatus(CheckTask.TASK_STATUS_CURRENT_CHECK);
				}
				
				taskList.add(ct);	
			}
			else
			{
				if(onetwoFlag == 0)
				{
					for(int j=0;j<CheckTask.CHECK_STEP_APPLY_BUDGET_1.length;j++)
					{
						int[] subDeptPost = CheckTask.CHECK_STEP_APPLY_BUDGET_1[j];
						
						CheckTask ct = new CheckTask();
						ct.setCtProject(applyBudget.getPbProject());
						ct.setCtCheckStep(i+2);					
						ct.setCtTaskType(CheckTask.TASK_TYPE_APPLY_BUDGET_CHECK);
						ct.setCtProjectBudget(applyBudget.getPbId());
						
						ct.setCtReceiveDept(subDeptPost[0]);
						this.adjustReceiveDept(ct, dept);
						
						if(subDeptPost[1] != 0)
						{
							ct.setCtReceivePost(subDeptPost[1]);
						}
						ct.setCtSendUser(user.getId());
						ct.setCtSendTime(new Date());
						
						taskList.add(ct);
					}
					
					onetwoFlag = 1;
				}
				else
				{
					for(int j=0;j<CheckTask.CHECK_STEP_APPLY_BUDGET_2.length;j++)
					{
						int[] subDeptPost = CheckTask.CHECK_STEP_APPLY_BUDGET_2[j];
						
						CheckTask ct = new CheckTask();
						ct.setCtProject(applyBudget.getPbProject());
						ct.setCtCheckStep(i+2);					
						ct.setCtTaskType(CheckTask.TASK_TYPE_APPLY_BUDGET_CHECK);						
						ct.setCtProjectBudget(applyBudget.getPbId());
						
						ct.setCtReceiveDept(subDeptPost[0]);
						this.adjustReceiveDept(ct, dept);
						
						if(subDeptPost[1] != 0)
						{
							ct.setCtReceivePost(subDeptPost[1]);
						}
						ct.setCtSendUser(user.getId());
						ct.setCtSendTime(new Date());
						
						taskList.add(ct);
					}					
				}
				
			}					
		}
		
		return taskList;
	}

	@Override
	public ArrayList<CheckTask> getTenderDocumentCheckStep(TenderProject project, Account user, Integer dept) {
		// TODO Auto-generated method stub
		ArrayList<CheckTask> taskList = new ArrayList<CheckTask>();
		CheckTask sct = this.createUserSelfCheckTask(project.getTpId(), user,dept,CheckTask.TASK_TYPE_TENDER_DOCUMENT_CHECK);
		
		taskList.add(sct);
		for(int i=0;i<CheckTask.CHECK_STEP_TENDER_DOCUMENT.length;i++)
		{
			int[] deptPost = CheckTask.CHECK_STEP_TENDER_DOCUMENT[i];
			
			if(deptPost.length > 0)
			{
				CheckTask ct = new CheckTask();
				ct.setCtProject(project.getTpId());
				ct.setCtCheckStep(i+2);					
				ct.setCtTaskType(CheckTask.TASK_TYPE_TENDER_DOCUMENT_CHECK);
								
				ct.setCtReceiveDept(deptPost[0]);
				this.adjustReceiveDept(ct, dept);
				
				if(deptPost[1] != 0)
				{
					ct.setCtReceivePost(deptPost[1]);
				}
				
				ct.setCtSendUser(user.getId());
				ct.setCtSendTime(new Date());
				if(i==0)
				{
					ct.setCtStatus(CheckTask.TASK_STATUS_CURRENT_CHECK);
				}
				
				taskList.add(ct);	
			}						
		}
		
		return taskList;
	}
	
	public void retrieveTaskCheckableUserList(List<CheckTask> checkTaskList)
	{		
		for(CheckTask ct:checkTaskList)
		{
			this.retrieveTaskCheckableUserList(ct);
		}		
	}
	
	public void retrieveTaskCheckableUserList(CheckTask ct)
	{
		try {
			Account searchInfo = new Account();
			
			if(ct.getCtReceivePost() != null)
			{
				if(ct.getCtReceivePost().equals(Post.POST_PROJECT_MANAGER))
				{
					if(ct.getProjectApprovalRecord() != null && ct.getCtReceiveDept().equals(ct.getProjectApprovalRecord().getParDept()))
					{
						ct.setCheckableUserList(ct.getProjectApprovalRecord().getManager().getPerson().getPersonName());
					}
					else if(ct.getProject() != null && ct.getCtReceiveDept().equals(ct.getProject().getTpDept()))
					{
						ct.setCheckableUserList(ct.getProject().getManager().getPerson().getPersonName());
					}
				}
				else if(ct.getCtReceivePost().equals(Post.POST_PROJECT_MARKET_MANAGER))
				{
					if(ct.getProjectApprovalRecord() != null)
					{
						ct.setCheckableUserList(ct.getProjectApprovalRecord().getMarketManager().getPerson().getPersonName());
					}					
					else if(ct.getProject() != null && ct.getProject().getMarketManager() != null)
					{
						ct.setCheckableUserList(ct.getProject().getMarketManager().getPerson().getPersonName());
					}
				}
				else if(ct.getCtReceivePost().equals(Post.POST_PROJECT_SECRETARY))
				{
					if(ct.getProjectApprovalRecord() != null)
					{
						ct.setCheckableUserList(ct.getProjectApprovalRecord().getCreateUser().getPerson().getPersonName());
					}
					else if(ct.getProject() != null)
					{
						ct.setCheckableUserList(ct.getProject().getCreateUser().getPerson().getPersonName());
					}
				}							
			}
			
			if(ct.getCheckableUserList().isEmpty())
			{
				searchInfo.getPerson().setDept(ct.getCtReceiveDept());
				if(ct.getCtReceivePost() == null)
				{
					searchInfo.getPerson().setPost(Post.POST_BUSINESS_DEPT_MANAGER);					
				}
				else
				{
					searchInfo.getPerson().setPost(ct.getCtReceivePost());
				}
				
				List<Account> userList = this.findTaskCheckableAccount(searchInfo);				
				String userNameList = "";
				for(Account a:userList)
				{
					userNameList = userNameList + a.getPerson().getPersonName() + ",";
				}
				
				if(!userNameList.isEmpty())
				{
					userNameList = userNameList.substring(0, userNameList.length()-1);
				}
				ct.setCheckableUserList(userNameList);
			}			
			
			if(ct.getReceiveDepartment() == null)
			{
				ct.setReceiveDepartment(departmentDAO.get(ct.getCtReceiveDept()));
			}
			if(ct.getReceivePost() == null && ct.getCtReceivePost() != null)
			{
				ct.setReceivePost(postDAO.get(ct.getCtReceivePost()));
			}
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private List<Account> findTaskCheckableAccount(Account accountInfo) throws BusinessException 
	{        
    	String hqlStr = "select distinct a from Account a,UsrPost b where a.id=b.usrId and a.enabled = '1' and a.flag != " + CommonService.DELETE_FLAG;
    	
    	hqlStr = hqlStr + " and b.dept = " + accountInfo.getPerson().getDept();
    	if(accountInfo.getPerson().getPost().equals(Post.POST_BUSINESS_DEPT_MANAGER))
    	{
    		hqlStr = hqlStr + " and b.post in (" + Post.POST_BUSINESS_DEPT_MANAGER + "," + Post.POST_ASSIST_DEPT_MANAGER + ")";
    	}
    	else
    	{
    		hqlStr = hqlStr + " and b.post = " + accountInfo.getPerson().getPost();
    	}    	
    	
    	MyQuery myQuery = new MyQuery();
    	myQuery.setQueryString(hqlStr);      
        myQuery.setOrderby(" order by a.name"); 
        myQuery.setOffset(false);
        
        return accountDAO.find(myQuery);        
	}


	public PostDAO getPostDAO() {
		return postDAO;
	}


	public void setPostDAO(PostDAO postDAO) {
		this.postDAO = postDAO;
	}
	

	public TenderAttachmentDAO getTenderAttachmentDAO() {
		return tenderAttachmentDAO;
	}


	public void setTenderAttachmentDAO(TenderAttachmentDAO tenderAttachmentDAO) {
		this.tenderAttachmentDAO = tenderAttachmentDAO;
	}



}
