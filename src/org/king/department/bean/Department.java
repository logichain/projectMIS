package org.king.department.bean;

import java.util.Date;

import org.king.security.domain.Account;

/**
 * Department entity. @author MyEclipse Persistence Tools
 */

public class Department extends org.king.framework.domain.BaseObject implements
		java.io.Serializable {

	// Fields
	
	public static int DEPARTMENT_JI_SHU= 1;
	public static int DEPARTMENT_HUAN_BAO = 2;
	public static int DEPARTMENT_SHI_CHANG = 3;
	public static int DEPARTMENT_CAI_WU = 4;
	public static int DEPARTMENT_XIN_NENG_YUAN = 5;
	public static int DEPARTMENT_XIANGMU_GUANLI = 6;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer DId;
	private String DName;
	private String DPhone;
	private String DFax;
	private String DDescription;
	private Integer DFlag;
	private String DCreateUser;
	private Date DCreateTime;
	
	// Constructors

	

	/** default constructor */
	public Department() {
	}

	/** minimal constructor */
	public Department(String DName, Integer DFlag) {
		this.DName = DName;
		this.DFlag = DFlag;
	}

	/** full constructor */
	public Department(String DName, String DPhone, String DFax,
			String DDescription, Integer DFlag) {
		this.DName = DName;
		this.DPhone = DPhone;
		this.DFax = DFax;
		this.DDescription = DDescription;
		this.DFlag = DFlag;
	}

	// Property accessors

	public Integer getDId() {
		return this.DId;
	}

	public void setDId(Integer DId) {
		this.DId = DId;
	}

	public String getDName() {
		return this.DName;
	}

	public void setDName(String DName) {
		this.DName = DName;
	}

	public String getDPhone() {
		return this.DPhone;
	}

	public void setDPhone(String DPhone) {
		this.DPhone = DPhone;
	}

	public String getDFax() {
		return this.DFax;
	}

	public void setDFax(String DFax) {
		this.DFax = DFax;
	}

	public String getDDescription() {
		return this.DDescription;
	}

	public void setDDescription(String DDescription) {
		this.DDescription = DDescription;
	}

	public Integer getDFlag() {
		return this.DFlag;
	}

	public void setDFlag(Integer DFlag) {
		this.DFlag = DFlag;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof Department)
		{
			Department d = (Department)arg0;
			if(d.getDId().equals(DId))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return DId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.DId + this.DName;
	}

	public void setDCreateUser(String dCreateUser) {
		DCreateUser = dCreateUser;
	}

	public String getDCreateUser() {
		return DCreateUser;
	}

	public void setDCreateTime(Date dCreateTime) {
		DCreateTime = dCreateTime;
	}

	public Date getDCreateTime() {
		return DCreateTime;
	}

}