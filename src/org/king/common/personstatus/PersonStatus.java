package org.king.common.personstatus;

/**
 * PersonStatus entity. @author MyEclipse Persistence Tools
 */

public class PersonStatus extends org.king.framework.domain.BaseObject
		implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer psId;
	private String psName;
	private Integer psFlag;

	// Constructors

	/** default constructor */
	public PersonStatus() {
	}

	/** full constructor */
	public PersonStatus(String psName, Integer psFlag) {
		this.psName = psName;
		this.psFlag = psFlag;
	}

	// Property accessors

	public Integer getPsId() {
		return this.psId;
	}

	public void setPsId(Integer psId) {
		this.psId = psId;
	}

	public String getPsName() {
		return this.psName;
	}

	public void setPsName(String psName) {
		this.psName = psName;
	}

	public Integer getPsFlag() {
		return this.psFlag;
	}

	public void setPsFlag(Integer psFlag) {
		this.psFlag = psFlag;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof PersonStatus)
		{
			PersonStatus ps = (PersonStatus)arg0;
			if(ps.getPsId().equals(psId))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return psId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.psId + this.psName;
	}

}