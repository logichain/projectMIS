package org.king.common.customertype;

/**
 * CustomerType entity. @author MyEclipse Persistence Tools
 */

public class CustomerType extends org.king.framework.domain.BaseObject
		implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	public static Integer CUSTOMER_TYPE_TENDERER = 1;
	public static Integer CUSTOMER_TYPE_COMPETITOR = 3;
	public static Integer CUSTOMER_TYPE_SUPPLIER = 2;
	private static final long serialVersionUID = 1L;
	private Integer ctId;
	private String ctName;
	private Integer ctFlag;

	// Constructors

	/** default constructor */
	public CustomerType() {
	}

	/** full constructor */
	public CustomerType(String ctName, Integer ctFlag) {
		this.ctName = ctName;
		this.ctFlag = ctFlag;
	}

	// Property accessors

	public Integer getCtId() {
		return this.ctId;
	}

	public void setCtId(Integer ctId) {
		this.ctId = ctId;
	}

	public String getCtName() {
		return this.ctName;
	}

	public void setCtName(String ctName) {
		this.ctName = ctName;
	}

	public Integer getCtFlag() {
		return this.ctFlag;
	}

	public void setCtFlag(Integer ctFlag) {
		this.ctFlag = ctFlag;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof CustomerType)
		{
			CustomerType ct = (CustomerType) arg0;
			if(ct.getCtId().equals(ctId))
			{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.ctId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.ctId + this.ctName;
	}

}