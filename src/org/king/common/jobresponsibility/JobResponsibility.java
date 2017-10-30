package org.king.common.jobresponsibility;

/**
 * JobResponsibility entity. @author MyEclipse Persistence Tools
 */

public class JobResponsibility extends org.king.framework.domain.BaseObject
		implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer jrId;
	private String jrName;
	private Integer jrFlag;

	// Constructors

	/** default constructor */
	public JobResponsibility() {
	}

	/** full constructor */
	public JobResponsibility(String jrName, Integer jrFlag) {
		this.jrName = jrName;
		this.jrFlag = jrFlag;
	}

	// Property accessors

	public Integer getJrId() {
		return this.jrId;
	}

	public void setJrId(Integer jrId) {
		this.jrId = jrId;
	}

	public String getJrName() {
		return this.jrName;
	}

	public void setJrName(String jrName) {
		this.jrName = jrName;
	}

	public Integer getJrFlag() {
		return this.jrFlag;
	}

	public void setJrFlag(Integer jrFlag) {
		this.jrFlag = jrFlag;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof JobResponsibility)
		{
			JobResponsibility jr = (JobResponsibility)arg0;
			if(jr.getJrId().equals(jrId))
			{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.jrId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.jrId + this.jrName;
	}

}