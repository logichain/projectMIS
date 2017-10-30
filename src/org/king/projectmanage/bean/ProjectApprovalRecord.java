package org.king.projectmanage.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.king.department.bean.Department;
import org.king.security.domain.Account;


/**
 * ProjectApprovalRecord entity. @author MyEclipse Persistence Tools
 */

public class ProjectApprovalRecord extends org.king.common.SearchBaseObject implements java.io.Serializable {

	public static int PAR_STATUS_INIT = 99;
	public static int PAR_STATUS_APPROVAL_CHECKING = 1;//立项审批
	public static int PAR_STATUS_APPROVAL_RETURN = 2;
	public static int PAR_STATUS_APPROVAL_CHECKED = 3;
	public static int PAR_STATUS_TENDER_CHECKING = 4;//投标审批
	public static int PAR_STATUS_TENDER_RETURN = 5;
	public static int PAR_STATUS_TENDER_CHECKED = 6;
	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer parId;
	private String parProjectName;
	private String parProjectOwner;
	private String parDesignCompany;
	private String parTenderCompany;
	private double parTenderPrice;
	private String parTenderDate;
	private double parOperatingExpense;
	private String parBusinessType;
	private String parBusinessRelation;
	private String parProjectRemark;
	private String parRiskControl;
	private String parActionability;
	private Integer parStatus = null;
	private String parCreateUser;
	private Date parCreateTime;
	private Integer parCheckLegalperson;
	private Integer parCheckQualification;
	private Integer parCheckSystem;
	private Integer parCheckAchievements;
	private Integer parCheckRegisterCapital;
	private Integer parCheckTenderType;
	private Integer parCheckAuthorityCertificate;
	private Integer parIllegalRecord;
	private String parTenderMember;
	private Integer parCommitDept;
	private String parTenderRemark;
	private String parManager;
	private Integer parDept;
	private Integer parImplementDept;
	private String parMarketManager;
	
	private ArrayList<ProjectAttachment> attachmentList = new ArrayList<ProjectAttachment>();
	private Account createUser;
	private Department commitDept;
	private Account manager;
	private Account marketManager;
	private Department department;
	private Department implementDepartment;

	// Constructors

	/** default constructor */
	public ProjectApprovalRecord() {
	}
	
	// Property accessors

	public Integer getParId() {
		return this.parId;
	}

	public void setParId(Integer parId) {
		this.parId = parId;
	}

	public String getParProjectName() {
		return this.parProjectName;
	}

	public void setParProjectName(String parProjectName) {
		this.parProjectName = parProjectName;
	}

	public String getParProjectOwner() {
		return this.parProjectOwner;
	}

	public void setParProjectOwner(String parProjectOwner) {
		this.parProjectOwner = parProjectOwner;
	}

	public String getParDesignCompany() {
		return this.parDesignCompany;
	}

	public void setParDesignCompany(String parDesignCompany) {
		this.parDesignCompany = parDesignCompany;
	}

	public String getParTenderCompany() {
		return this.parTenderCompany;
	}

	public void setParTenderCompany(String parTenderCompany) {
		this.parTenderCompany = parTenderCompany;
	}

	public double getParTenderPrice() {
		return this.parTenderPrice;
	}

	public void setParTenderPrice(double parTenderPrice) {
		this.parTenderPrice = parTenderPrice;
	}

	public String getParTenderDate() {
		return this.parTenderDate;
	}

	public void setParTenderDate(String parTenderDate) {
		this.parTenderDate = parTenderDate;
	}

	public double getParOperatingExpense() {
		return this.parOperatingExpense;
	}

	public void setParOperatingExpense(double parOperatingExpense) {
		this.parOperatingExpense = parOperatingExpense;
	}

	public String getParBusinessType() {
		return this.parBusinessType;
	}

	public void setParBusinessType(String parBusinessType) {
		this.parBusinessType = parBusinessType;
	}

	public String getParBusinessRelation() {
		return this.parBusinessRelation;
	}

	public void setParBusinessRelation(String parBusinessRelation) {
		this.parBusinessRelation = parBusinessRelation;
	}

	public String getParProjectRemark() {
		return this.parProjectRemark;
	}

	public void setParProjectRemark(String parProjectRemark) {
		this.parProjectRemark = parProjectRemark;
	}

	public String getParRiskControl() {
		return this.parRiskControl;
	}

	public void setParRiskControl(String parRiskControl) {
		this.parRiskControl = parRiskControl;
	}

	public String getParActionability() {
		return this.parActionability;
	}

	public void setParActionability(String parActionability) {
		this.parActionability = parActionability;
	}

	public Integer getParStatus() {
		return this.parStatus;
	}

	public void setParStatus(Integer parStatus) {
		this.parStatus = parStatus;
	}

	public String getParCreateUser() {
		return this.parCreateUser;
	}

	public void setParCreateUser(String parCreateUser) {
		this.parCreateUser = parCreateUser;
	}

	public Date getParCreateTime() {
		return this.parCreateTime;
	}

	public void setParCreateTime(Date parCreateTime) {
		this.parCreateTime = parCreateTime;
	}

	public Integer getParCheckLegalperson() {
		return this.parCheckLegalperson;
	}

	public void setParCheckLegalperson(Integer parCheckLegalperson) {
		this.parCheckLegalperson = parCheckLegalperson;
	}

	public Integer getParCheckQualification() {
		return this.parCheckQualification;
	}

	public void setParCheckQualification(Integer parCheckQualification) {
		this.parCheckQualification = parCheckQualification;
	}

	public Integer getParCheckSystem() {
		return this.parCheckSystem;
	}

	public void setParCheckSystem(Integer parCheckSystem) {
		this.parCheckSystem = parCheckSystem;
	}

	public Integer getParCheckAchievements() {
		return this.parCheckAchievements;
	}

	public void setParCheckAchievements(Integer parCheckAchievements) {
		this.parCheckAchievements = parCheckAchievements;
	}

	public Integer getParCheckRegisterCapital() {
		return this.parCheckRegisterCapital;
	}

	public void setParCheckRegisterCapital(Integer parCheckRegisterCapital) {
		this.parCheckRegisterCapital = parCheckRegisterCapital;
	}

	public Integer getParCheckTenderType() {
		return this.parCheckTenderType;
	}

	public void setParCheckTenderType(Integer parCheckTenderType) {
		this.parCheckTenderType = parCheckTenderType;
	}

	public Integer getParCheckAuthorityCertificate() {
		return this.parCheckAuthorityCertificate;
	}

	public void setParCheckAuthorityCertificate(Integer parCheckAuthorityCertificate) {
		this.parCheckAuthorityCertificate = parCheckAuthorityCertificate;
	}

	public Integer getParIllegalRecord() {
		return this.parIllegalRecord;
	}

	public void setParIllegalRecord(Integer parIllegalRecord) {
		this.parIllegalRecord = parIllegalRecord;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof ProjectApprovalRecord)
		{
			ProjectApprovalRecord par = (ProjectApprovalRecord)arg0;
			if(this.parId.equals(par.getParId()))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return parId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.parId + this.parProjectName;
	}
	
	public void setAttachmentList(ArrayList<ProjectAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public ArrayList<ProjectAttachment> getAttachmentList() {
		return attachmentList;
	}

	public Set<ProjectAttachment> getAttachmentSet() {
		// return attachmentSet;
		Set<ProjectAttachment> detailSet = new HashSet<ProjectAttachment>();
		detailSet.addAll(attachmentList);

		return detailSet;
	}

	public void setAttachmentSet(Set<ProjectAttachment> attachmentSet) {
		attachmentList.clear();
		attachmentList.addAll(attachmentSet);
	}

	public void setCreateUser(Account createUser) {
		this.createUser = createUser;
	}

	public Account getCreateUser() {
		return createUser;
	}

	public void setParTenderMember(String parTenderMember) {
		this.parTenderMember = parTenderMember;
	}

	public String getParTenderMember() {
		return parTenderMember;
	}

	public void setParCommitDept(Integer parCommitDept) {
		this.parCommitDept = parCommitDept;
	}

	public Integer getParCommitDept() {
		return parCommitDept;
	}

	public void setParTenderRemark(String parTenderRemark) {
		this.parTenderRemark = parTenderRemark;
	}

	public String getParTenderRemark() {
		return parTenderRemark;
	}

	public void setCommitDept(Department commitDept) {
		this.commitDept = commitDept;
	}

	public Department getCommitDept() {
		return commitDept;
	}

	public void setManager(Account manager) {
		this.manager = manager;
	}

	public Account getManager() {
		return manager;
	}

	public void setParManager(String parManager) {
		this.parManager = parManager;
	}

	public String getParManager() {
		return parManager;
	}

	public Integer getParDept() {
		return parDept;
	}

	public void setParDept(Integer parDept) {
		this.parDept = parDept;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Integer getParImplementDept() {
		return parImplementDept;
	}

	public void setParImplementDept(Integer parImplementDept) {
		this.parImplementDept = parImplementDept;
	}

	public Department getImplementDepartment() {
		return implementDepartment;
	}

	public void setImplementDepartment(Department implementDepartment) {
		this.implementDepartment = implementDepartment;
	}

	public Account getMarketManager() {
		return marketManager;
	}

	public void setMarketManager(Account marketManager) {
		this.marketManager = marketManager;
	}

	public String getParMarketManager() {
		return parMarketManager;
	}

	public void setParMarketManager(String parMarketManager) {
		this.parMarketManager = parMarketManager;
	}

}