package org.king.common.tenderrole;

import org.king.common.jobresponsibility.JobResponsibility;

/**
 * TenderRole entity. @author MyEclipse Persistence Tools
 */

public class TenderRole extends org.king.framework.domain.BaseObject implements
		java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer trId;
	
	private String trName;
	private Integer trFlag;
	private Integer trJobResponsibility;
	private JobResponsibility jobResponsibility;

	// Constructors

	/** default constructor */
	public TenderRole() {
	}

	/** full constructor */
	public TenderRole(String prName, Integer prFlag) {
		this.trName = prName;
		this.setTrFlag(prFlag);
	}

	// Property accessors

	public Integer getTrId() {
		return trId;
	}

	public void setTrId(Integer trId) {
		this.trId = trId;
	}

	public String getTrName() {
		return trName;
	}

	public void setTrName(String trName) {
		this.trName = trName;
	}


	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof TenderRole)
		{
			TenderRole pr = (TenderRole)arg0;
			if(pr.getTrId().equals(trId))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.trId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.trId + this.trName;
	}


	public void setJobResponsibility(JobResponsibility jobResponsibility) {
		this.jobResponsibility = jobResponsibility;
	}

	public JobResponsibility getJobResponsibility() {
		return jobResponsibility;
	}

	public void setTrFlag(Integer trFlag) {
		this.trFlag = trFlag;
	}

	public Integer getTrFlag() {
		return trFlag;
	}

	public void setTrJobResponsibility(Integer trJobResponsibility) {
		this.trJobResponsibility = trJobResponsibility;
	}

	public Integer getTrJobResponsibility() {
		return trJobResponsibility;
	}

}