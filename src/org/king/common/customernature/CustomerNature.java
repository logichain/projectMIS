package org.king.common.customernature;

/**
 * CustomerNature entity. @author MyEclipse Persistence Tools
 */

public class CustomerNature extends org.king.framework.domain.BaseObject
		implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer cnId;
	private String cnName;
	private Integer cnFlag;

	// Constructors

	/** default constructor */
	public CustomerNature() {
	}

	/** full constructor */
	public CustomerNature(String cnName, Integer cnFlag) {
		this.cnName = cnName;
		this.cnFlag = cnFlag;
	}

	// Property accessors

	public Integer getCnId() {
		return this.cnId;
	}

	public void setCnId(Integer cnId) {
		this.cnId = cnId;
	}

	public String getCnName() {
		return this.cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public Integer getCnFlag() {
		return this.cnFlag;
	}

	public void setCnFlag(Integer cnFlag) {
		this.cnFlag = cnFlag;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof CustomerNature)
		{
			CustomerNature cn = (CustomerNature) arg0;
			if(cn.getCnId().equals(cnId))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return cnId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.cnId + this.cnName;
	}

}