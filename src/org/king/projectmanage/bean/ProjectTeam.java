package org.king.projectmanage.bean;

import java.util.Date;

import org.king.common.jobresponsibility.JobResponsibility;
import org.king.common.projectrole.ProjectRole;
import org.king.common.tenderrole.TenderRole;
import org.king.security.domain.Account;

/**
 * ProjectTeam entity. @author MyEclipse Persistence Tools
 */

public class ProjectTeam extends org.king.framework.domain.BaseObject implements
		java.io.Serializable {
	public static int TENDER_TEAM_MEMEBER_TYPE = 1;
	public static int PROJECT_TEAM_MEMEBER_TYPE = 0;
	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer ptId;
	private Integer ptTenderProject;
	private String ptAccount;
	private Integer ptProjectRole;
	private Integer ptTenderRole;
	private Integer ptJobResponsibility;
	private String ptCreateUser;
	private Date ptCreateTime;
	private Integer ptFlag;
	private String ptRemark;
	private Integer ptTeamType;
	
	private Account account;
	private ProjectRole projectRole;
	private TenderRole tenderRole;
	private JobResponsibility jobResponsibility;
	
	private boolean modified = false;

	// Constructors

	/** default constructor */
	public ProjectTeam() {
	}

	/** minimal constructor */
	public ProjectTeam(Integer ptTenderProject, String ptAccount,
			String ptCreateUser, Date ptCreateTime, Integer ptFlag) {
		this.ptTenderProject = ptTenderProject;
		this.ptAccount = ptAccount;
		this.ptCreateUser = ptCreateUser;
		this.ptCreateTime = ptCreateTime;
		this.ptFlag = ptFlag;
	}

	/** full constructor */
	public ProjectTeam(Integer ptTenderProject, String ptAccount,
			Integer ptProjectRole, Integer ptJobResponsibility, String ptCreateUser, Date ptCreateTime,
			Integer ptFlag) {
		this.ptTenderProject = ptTenderProject;
		this.ptAccount = ptAccount;
		this.ptProjectRole = ptProjectRole;
		this.ptJobResponsibility = ptJobResponsibility;
		this.ptCreateUser = ptCreateUser;
		this.ptCreateTime = ptCreateTime;
		this.ptFlag = ptFlag;
	}

	// Property accessors

	public Integer getPtId() {
		return this.ptId;
	}

	public void setPtId(Integer ptId) {
		this.ptId = ptId;
	}

	public Integer getPtTenderProject() {
		return this.ptTenderProject;
	}

	public void setPtTenderProject(Integer ptTenderProject) {
		this.ptTenderProject = ptTenderProject;
	}

	public String getPtAccount() {
		return this.ptAccount;
	}

	public void setPtAccount(String ptAccount) {
		this.ptAccount = ptAccount;
	}

	public Integer getPtProjectRole() {
		return this.ptProjectRole;
	}

	public void setPtProjectRole(Integer ptProjectRole) {
		this.ptProjectRole = ptProjectRole;
	}

	public Integer getPtJobResponsibility() {
		return this.ptJobResponsibility;
	}

	public void setPtJobResponsibility(Integer ptJobResponsibility) {
		this.ptJobResponsibility = ptJobResponsibility;
	}

	public String getPtCreateUser() {
		return this.ptCreateUser;
	}

	public void setPtCreateUser(String ptCreateUser) {
		this.ptCreateUser = ptCreateUser;
	}

	public Date getPtCreateTime() {
		return this.ptCreateTime;
	}

	public void setPtCreateTime(Date ptCreateTime) {
		this.ptCreateTime = ptCreateTime;
	}

	public Integer getPtFlag() {
		return this.ptFlag;
	}

	public void setPtFlag(Integer ptFlag) {
		this.ptFlag = ptFlag;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof ProjectTeam)
		{
			ProjectTeam pt = (ProjectTeam) arg0;
			if(pt.getPtId().equals(ptId))
			{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.ptId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.ptId + this.ptAccount;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Account getAccount() {
		return account;
	}

	public void setProjectRole(ProjectRole projectRole) {
		this.projectRole = projectRole;
	}

	public ProjectRole getProjectRole() {
		return projectRole;
	}

	public void setJobResponsibility(JobResponsibility jobResponsibility) {
		this.jobResponsibility = jobResponsibility;
	}

	public JobResponsibility getJobResponsibility() {
		return jobResponsibility;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public boolean isModified() {
		return modified;
	}

	public void setPtTeamType(Integer ptTeamType) {
		this.ptTeamType = ptTeamType;
	}

	public Integer getPtTeamType() {
		return ptTeamType;
	}

	public void setPtTenderRole(Integer ptTenderRole) {
		this.ptTenderRole = ptTenderRole;
	}

	public Integer getPtTenderRole() {
		return ptTenderRole;
	}

	public void setTenderRole(TenderRole tenderRole) {
		this.tenderRole = tenderRole;
	}

	public TenderRole getTenderRole() {
		return tenderRole;
	}

	public void setPtRemark(String ptRemark) {
		this.ptRemark = ptRemark;
	}

	public String getPtRemark() {
		return ptRemark;
	}

}