package org.king.common.education;

/**
 * EducationBackground entity. @author MyEclipse Persistence Tools
 */

public class EducationBackground extends org.king.framework.domain.BaseObject
		implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer ebId;
	private String ebName;
	private String ebFlag;

	// Constructors

	/** default constructor */
	public EducationBackground() {
	}

	/** full constructor */
	public EducationBackground(String ebName, String ebFlag) {
		this.ebName = ebName;
		this.ebFlag = ebFlag;
	}

	// Property accessors

	public Integer getEbId() {
		return this.ebId;
	}

	public void setEbId(Integer ebId) {
		this.ebId = ebId;
	}

	public String getEbName() {
		return this.ebName;
	}

	public void setEbName(String ebName) {
		this.ebName = ebName;
	}

	public String getEbFlag() {
		return this.ebFlag;
	}

	public void setEbFlag(String ebFlag) {
		this.ebFlag = ebFlag;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof EducationBackground)
		{
			EducationBackground eb = (EducationBackground)arg0;
			if(eb.getEbId().equals(ebId))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return ebId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.ebId + this.ebName;
	}

}