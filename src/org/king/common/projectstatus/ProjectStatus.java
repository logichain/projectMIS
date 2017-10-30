package org.king.common.projectstatus;

/**
 * ProjectStatus entity. @author MyEclipse Persistence Tools
 */

public class ProjectStatus extends org.king.framework.domain.BaseObject
		implements java.io.Serializable {

// Fields
	public static final int PROJECT_STATUS_FOLLOW = 1;//跟踪期
	public static final int PROJECT_STATUS_TENDER = 2;//投标期	
	public static final int PROJECT_STATUS_ACT = 3;//施工期
	public static final int PROJECT_STATUS_MAINTAIN = 4;//质保期
	public static final int PROJECT_STATUS_TENDER_NOT = 5;//未投标
	public static final int PROJECT_STATUS_TENDER_FAIL = 6;//未中标
	
	public static int TENDERDOC_CHECK_STATUS_INIT = 0;
	public static int TENDERDOC_CHECK_STATUS_CHECKING = 1;	
	public static int TENDERDOC_CHECK_STATUS_REJECT = 2;
	public static int TENDERDOC_CHECK_STATUS_CHECKED = 3;	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer psId;
	private String psName;
	private Integer psFlag;

	// Constructors

	/** default constructor */
	public ProjectStatus() {
	}

	/** full constructor */
	public ProjectStatus(String psName, Integer psFlag) {
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
		if(arg0 != null && arg0 instanceof ProjectStatus)
		{
			ProjectStatus ps = (ProjectStatus)arg0;
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
		return this.psId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.psId + this.psName;
	}

}