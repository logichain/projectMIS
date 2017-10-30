package org.king.common.projectrole;

import org.king.common.jobresponsibility.JobResponsibility;

/**
 * ProjectRole entity. @author MyEclipse Persistence Tools
 */

public class ProjectRole extends org.king.framework.domain.BaseObject implements
		java.io.Serializable {

	// Fields
	public static final int PROJECT_ROLE_SECRETARY = 11;
	public static final int PROJECT_ROLE_MANAGER = 1;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer prId;
	private String prName;
	private Integer prFlag;
	private Integer prJobResponsibility;
	private JobResponsibility jobResponsibility;

	// Constructors

	/** default constructor */
	public ProjectRole() {
	}

	/** full constructor */
	public ProjectRole(String prName, Integer prFlag) {
		this.prName = prName;
		this.prFlag = prFlag;
	}

	// Property accessors

	public Integer getPrId() {
		return this.prId;
	}

	public void setPrId(Integer prId) {
		this.prId = prId;
	}

	public String getPrName() {
		return this.prName;
	}

	public void setPrName(String prName) {
		this.prName = prName;
	}

	public Integer getPrFlag() {
		return this.prFlag;
	}

	public void setPrFlag(Integer prFlag) {
		this.prFlag = prFlag;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof ProjectRole)
		{
			ProjectRole pr = (ProjectRole)arg0;
			if(pr.getPrId().equals(prId))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.prId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.prId + this.prName;
	}

	public void setPrJobResponsibility(Integer prJobResponsibility) {
		this.prJobResponsibility = prJobResponsibility;
	}

	public Integer getPrJobResponsibility() {
		return prJobResponsibility;
	}

	public void setJobResponsibility(JobResponsibility jobResponsibility) {
		this.jobResponsibility = jobResponsibility;
	}

	public JobResponsibility getJobResponsibility() {
		return jobResponsibility;
	}

}