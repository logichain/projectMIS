package org.king.projectmanage.service;

import java.util.ArrayList;
import java.util.List;

import org.king.accountmanage.bean.AccountSaleContract;
import org.king.common.checktask.CheckTask;
import org.king.common.service.CommonService;
import org.king.framework.service.Service;
import org.king.projectmanage.bean.ProjectApprovalRecord;
import org.king.projectmanage.bean.ProjectAttachment;
import org.king.projectmanage.bean.ProjectContract;
import org.king.projectmanage.bean.ProjectScheduleStage;
import org.king.projectmanage.bean.ProjectScheduleTask;
import org.king.projectmanage.bean.ProjectTeam;
import org.king.projectmanage.bean.TaskImplementRecord;
import org.king.projectmanage.bean.TenderAttachment;
import org.king.projectmanage.bean.TenderProject;
import org.king.security.domain.Account;
import org.king.tender.bean.ProjectBudget;

public interface ProjectManageService extends Service {
	
	public TenderProject createTenderProject();
	
	public ProjectAttachment createProjectAttachment();
	public ProjectAttachment getProjectAttachmentById(Integer id);
	public TenderAttachment getTenderAttachmentById(Integer id);
	public ProjectScheduleStage createProjectScheduleStage();
	public ProjectScheduleTask createProjectScheduleTask();
	public TaskImplementRecord createTaskImplementRecord();
	public ProjectTeam createProjectTeam();
	
	public void saveTenderContract(TenderProject tp,String uploadPath);
	public void saveProjectContract(TenderProject tp,String uploadPath);
	
	public void saveProjectTeam(TenderProject tp);	
	public void saveSchedule(TenderProject tp);
	public void saveSchedulePlan(TenderProject tp);
	public void saveProjectDocument(TenderProject tp,String uploadPath);
	public void saveTenderDocument(TenderProject tp,String uploadPath);
		
	public void saveTenderProject(TenderProject tp,String uploadPath);
	
	public void deleteTenderProject(TenderProject tp);					
	public List<TenderProject> searchProjectList(Object[] args);
	public int searchProjectCount(Object[] args);	
	public TenderProject getProjectById(Integer id);
	
	public List<TenderProject> searchAccountStatistics(Object[] args);	
				
	public void calculateProjectTimeSchedule(List<TenderProject> projectList);
	public void calculateProjectTimeSchedule(TenderProject tp);
	public void calculateProjectDoSchedule(TenderProject tp);
	
	public void modifyCloseTenderProject(TenderProject tp);
	public void updateProjectContract(ProjectContract pc);
	public void saveProjectManagerAsTeam(TenderProject tp,CommonService commonService);
	public void saveProjectCreaterAsTeam(TenderProject tp,CommonService commonService);
	public void deleteTenderMember(TenderProject tp,Account a);
	public void deleteProjectMember(TenderProject tp,Account a);
	
	public void copyDataFromTenderBudget(ProjectBudget projectBudget,ProjectBudget tenderBudget);
	public void saveAccountSaleContract(TenderProject tp);
	public void saveAccountPurchaseContract(TenderProject tp);
	public void saveAccountFeeTax(TenderProject tp);
	
	public List<CheckTask> searchAboutCheckTask(Object[] args);
	public void saveCheckTaskList(List<CheckTask> taskList);
	public List<CheckTask> searchCheckTask(Object[] args);
	public int searchCheckTaskCount(Object[] args);
	public CheckTask getCheckTaskById(Integer taskId);
	public void updateCheckTask(CheckTask checkTaskInfo);
	
	public List<CheckTask> getNextStepCheckTask(CheckTask currentTask);
	public boolean isCurrentStepCheckTaskFinished(CheckTask currentTask);
	public List<CheckTask> getAllStepCheckTask(CheckTask currentTask);
	public List<CheckTask> getAllStepCheckTask(ProjectContract projectContract);
	public List<CheckTask> getAllStepCheckTask(ProjectApprovalRecord par);
	public AccountSaleContract getAccountSaleContractById(Integer id);
	
	public ProjectApprovalRecord createProjectApprovalRecord();
	public void updateProjectApprovalRecord(ProjectApprovalRecord par);
	public void saveProjectApprovalRecord(ProjectApprovalRecord par,String uploadPath);
	public void deleteProjectApprovalRecord(ProjectApprovalRecord par);					
	public List<ProjectApprovalRecord> searchProjectApprovalRecordList(Object[] args);
	public int searchProjectApprovalRecordCount(Object[] args);	
	public ProjectApprovalRecord getProjectApprovalRecordById(Integer id);
	public List<ProjectApprovalRecord> searchProjectApprovalCheckList(Object[] args);
	public int searchProjectApprovalCheckCount(Object[] args);	
	
	public void updateProjectAttachmentCode(ArrayList<ProjectAttachment> attachmentList);
	public void updateTenderAttachmentCode(ArrayList<TenderAttachment> attachmentList);
	
	public ArrayList<CheckTask> getProjectApprovalRecordCheckStep(ProjectApprovalRecord par,Account user);
	public ArrayList<CheckTask> getPurchaseContractCheckStep(ProjectContract contract,Account user);
	public ArrayList<CheckTask> getSaleContractCheckStep(ProjectContract contract,Account user);
	public ArrayList<CheckTask> getProjectApprovalTenderCheckStep(ProjectApprovalRecord par,Account user);
	public void sendCheckTaskMail(String mailList,CheckTask ct);
	public String getChecktaskMail(CheckTask ct);
	public ArrayList<CheckTask> getTenderBudgetCheckStep(ProjectBudget tenderBudget,Account user,Integer dept);
	public ArrayList<CheckTask> getApplyBudgetCheckStep(ProjectBudget applyBudget,Account user,Integer dept);
	public ArrayList<CheckTask> getTenderDocumentCheckStep(TenderProject tp,Account user,Integer dept);
	
	public void retrieveTaskCheckableUserList(List<CheckTask> checkTaskList);
	public void retrieveTaskCheckableUserList(CheckTask ct);
}
