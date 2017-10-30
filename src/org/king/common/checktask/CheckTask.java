package org.king.common.checktask;

import java.util.Date;


import org.king.common.post.Post;
import org.king.department.bean.Department;
import org.king.projectmanage.bean.ProjectApprovalRecord;
import org.king.projectmanage.bean.ProjectContract;
import org.king.projectmanage.bean.TenderProject;
import org.king.security.domain.Account;
import org.king.tender.bean.ProjectBudget;

/**
 * CheckTaskList entity. @author MyEclipse Persistence Tools
 */

public class CheckTask extends org.king.common.SearchBaseObject implements java.io.Serializable {

	public static int TASK_TYPE_PROJECT_CONTRACT_CHECK = 1;// 合同评审
	public static int TASK_TYPE_PROJECT_APPROVAL_RECORD_CHECK = 2;// 立项备案
	public static int TASK_TYPE_PROJECT_APPROVAL_TENDER_CHECK = 3;// 投标评审
	public static int TASK_TYPE_TENDER_DOCUMENT_CHECK = 4;// 标书评审
	public static int TASK_TYPE_TENDER_BUDGET_CHECK = 5;// 预算标前评审
	public static int TASK_TYPE_APPLY_BUDGET_CHECK = 6;// 预算执行评审
	
	
	public static int TASK_STATUS_INIT = 0;
	public static int TASK_STATUS_CURRENT_CHECK = 1;	
	public static int TASK_STATUS_CHECKED = 2;
	public static int TASK_STATUS_DELETE = -1;

	public static int TASK_CHECK_RESULT_OK = 1;
	public static int TASK_CHECK_RESULT_NO = 2;
	
	//{部门，职务}部门0：项目创建部门；部门-1：市场/环保->环保|新能源/技术->新能源；职务0：部门长
	//订进
	public static int[][] CHECK_STEP_CONTRACT_PURCHASE = {{0,14},{},{6,0},{0,6},{8,5}};
	public static int[][] CHECK_STEP_CONTRACT_PURCHASE_1 = {{6,8},{-1,0},{3,0},{1,0},{4,0}};
		
	//订出 
	public static int[][] CHECK_STEP_CONTRACT_SALE = {{0,3},{},{6,0},{0,6},{8,5}};
	public static int[][] CHECK_STEP_CONTRACT_SALE_1 = {{1,0},{6,8},{-1,0},{4,0}};
	
	//立项备案
	public static int[][] CHECK_STEP_PROJECT_APPROVAL_RECORD = {{0,14},{3,0},{4,6},{0,6},{8,5}};
	
	//投标评审
	public static int[][] CHECK_STEP_PROJECT_TENDER = {{0,14},{},{0,6},{8,5}};
	public static int[][] CHECK_STEP_PROJECT_TENDER_1 = {{6,0},{6,8},{-1,0},{3,0},{1,0},{4,0}};
	
	//投标预算
	public static int[][] CHECK_STEP_TENDER_BUDGET_DELAYPAY = {{0,14},{3,0},{0,3},{},{4,6},{3,6},{8,5}};
	public static int[][] CHECK_STEP_TENDER_BUDGET_PREPAY = {{0,14},{3,0},{0,3},{},{3,6},{8,5}};
	public static int[][] CHECK_STEP_TENDER_BUDGET_1 = {{-1,0},{4,0}};
	
	//市场测算
	public static int[][] CHECK_STEP_APPLY_BUDGET = {{0,14},{3,0},{3,6},{0,3},{},{},{8,5}};
	public static int[][] CHECK_STEP_APPLY_BUDGET_1 = {{-1,0},{4,0},{6,0}};
	public static int[][] CHECK_STEP_APPLY_BUDGET_2 = {{-1,6},{4,6},{6,6}};
	
	//标书审评
	public static int[][] CHECK_STEP_TENDER_DOCUMENT = {{0,14},{0,0},{0,6},{8,5}};
	
	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer ctId;
	private Integer ctProject;
	private Integer ctTaskType;
	private Integer ctCheckStep;
	private Integer ctReceiveDept;
	private Integer ctReceivePost;
	private Integer ctStatus = CheckTask.TASK_STATUS_INIT;
	private String ctRemark;
	private String ctCheckUser;
	private Date ctCheckTime;
	private Integer ctCheckResult;
	private String ctCheckTitle;
	private String ctMonth;
	private Integer ctContract;
	private String ctSendUser;
	private Date ctSendTime;
	private Integer ctProjectApprovalRecord;
	private Integer ctProjectBudget;
	
	private Account checkUser;
	private Account sendUser;
	private TenderProject project;
	private ProjectContract contract;
	private Department receiveDepartment;
	private Post receivePost;
	private String sendTimeBegin;
	private String sendTimeEnd;
	private String checkTimeBegin;
	private String checkTimeEnd;
	private ProjectApprovalRecord projectApprovalRecord;
	private ProjectBudget projectBudget;
	
	private String checkableUserList = "";

	
	// Constructors

	/** default constructor */
	public CheckTask() {
	}

	// Property accessors

	public Integer getCtId() {
		return this.ctId;
	}

	public void setCtId(Integer ctId) {
		this.ctId = ctId;
	}

	public Integer getCtProject() {
		return this.ctProject;
	}

	public void setCtProject(Integer ctProject) {
		this.ctProject = ctProject;
	}

	public Integer getCtStatus() {
		return this.ctStatus;
	}

	public void setCtStatus(Integer ctStatus) {
		this.ctStatus = ctStatus;
	}

	public String getCtRemark() {
		return this.ctRemark;
	}

	public void setCtRemark(String ctRemark) {
		this.ctRemark = ctRemark;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if (arg0 != null && arg0 instanceof CheckTask) {
			CheckTask ct = (CheckTask) arg0;
			return ct.getCtId().equals(ctId);			
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return ctId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ctId + ctCheckTitle;
	}

	public void setCtCheckTitle(String ctCheckTitle) {
		this.ctCheckTitle = ctCheckTitle;
	}

	public String getCtCheckTitle() {
		return ctCheckTitle;
	}

	public void setCtMonth(String ctMonth) {
		this.ctMonth = ctMonth;
	}

	public String getCtMonth() {
		return ctMonth;
	}

	public void setCtContract(Integer ctContract) {
		this.ctContract = ctContract;
	}

	public Integer getCtContract() {
		return ctContract;
	}

	public void setCtCheckStep(Integer ctCheckStep) {
		this.ctCheckStep = ctCheckStep;
	}

	public Integer getCtCheckStep() {
		return ctCheckStep;
	}

	public void setCtCheckUser(String ctCheckUser) {
		this.ctCheckUser = ctCheckUser;
	}

	public String getCtCheckUser() {
		return ctCheckUser;
	}

	public void setCtCheckTime(Date ctCheckTime) {
		this.ctCheckTime = ctCheckTime;
	}

	public Date getCtCheckTime() {
		return ctCheckTime;
	}

	public void setCtCheckResult(Integer ctCheckResult) {
		this.ctCheckResult = ctCheckResult;
	}

	public Integer getCtCheckResult() {
		return ctCheckResult;
	}

	public void setCheckUser(Account checkUser) {
		this.checkUser = checkUser;
	}

	public Account getCheckUser() {
		return checkUser;
	}

	public void setCtReceiveDept(Integer ctReceiveDept) {
		this.ctReceiveDept = ctReceiveDept;
	}

	public Integer getCtReceiveDept() {
		return ctReceiveDept;
	}

	public void setCtReceivePost(Integer ctReceivePost) {
		this.ctReceivePost = ctReceivePost;
	}

	public Integer getCtReceivePost() {
		return ctReceivePost;
	}

	public void setCtSendUser(String ctSendUser) {
		this.ctSendUser = ctSendUser;
	}

	public String getCtSendUser() {
		return ctSendUser;
	}

	public void setCtSendTime(Date ctSendTime) {
		this.ctSendTime = ctSendTime;
	}

	public Date getCtSendTime() {
		return ctSendTime;
	}

	public void setSendUser(Account sendUser) {
		this.sendUser = sendUser;
	}

	public Account getSendUser() {
		return sendUser;
	}

	public void setProject(TenderProject project) {
		this.project = project;
	}

	public TenderProject getProject() {
		return project;
	}

	public void setContract(ProjectContract contract) {
		this.contract = contract;
	}

	public ProjectContract getContract() {
		return contract;
	}

	public void setCtTaskType(Integer ctTaskType) {
		this.ctTaskType = ctTaskType;
	}

	public Integer getCtTaskType() {
		if(ctTaskType != null && ctTaskType == 0)
		{
			return null;
		}
		return ctTaskType;
	}

	public void setReceiveDepartment(Department receiveDepartment) {
		this.receiveDepartment = receiveDepartment;
	}

	public Department getReceiveDepartment() {
		return receiveDepartment;
	}

	public void setReceivePost(Post receivePost) {
		this.receivePost = receivePost;
	}

	public Post getReceivePost() {
		return receivePost;
	}

	public void setSendTimeBegin(String sendTimeBegin) {
		this.sendTimeBegin = sendTimeBegin;
	}

	public String getSendTimeBegin() {
		return sendTimeBegin;
	}

	public void setSendTimeEnd(String sendTimeEnd) {
		this.sendTimeEnd = sendTimeEnd;
	}

	public String getSendTimeEnd() {
		return sendTimeEnd;
	}

	public void setCheckTimeBegin(String checkTimeBegin) {
		this.checkTimeBegin = checkTimeBegin;
	}

	public String getCheckTimeBegin() {
		return checkTimeBegin;
	}

	public void setCheckTimeEnd(String checkTimeEnd) {
		this.checkTimeEnd = checkTimeEnd;
	}

	public String getCheckTimeEnd() {
		return checkTimeEnd;
	}

	public void setCtProjectApprovalRecord(Integer ctProjectApprovalRecord) {
		this.ctProjectApprovalRecord = ctProjectApprovalRecord;
	}

	public Integer getCtProjectApprovalRecord() {
		return ctProjectApprovalRecord;
	}

	public void setProjectApprovalRecord(ProjectApprovalRecord projectApprovalRecord) {
		this.projectApprovalRecord = projectApprovalRecord;
	}

	public ProjectApprovalRecord getProjectApprovalRecord() {
		return projectApprovalRecord;
	}

	public String getCheckableUserList() {
		return checkableUserList;
	}

	public void setCheckableUserList(String checkableUserList) {
		this.checkableUserList = checkableUserList;
	}

	public Integer getCtProjectBudget() {
		return ctProjectBudget;
	}

	public void setCtProjectBudget(Integer ctProjectBudget) {
		this.ctProjectBudget = ctProjectBudget;
	}

	public ProjectBudget getProjectBudget() {
		return projectBudget;
	}

	public void setProjectBudget(ProjectBudget projectBudget) {
		this.projectBudget = projectBudget;
	}

	
}