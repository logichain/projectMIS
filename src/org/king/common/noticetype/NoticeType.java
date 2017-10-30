package org.king.common.noticetype;

/**
 * NoticeType entity. @author MyEclipse Persistence Tools
 */

public class NoticeType extends org.king.framework.domain.BaseObject implements
		java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer ntId;
	private String ntName;
	private Integer ntFlag;

	// Constructors

	/** default constructor */
	public NoticeType() {
	}

	/** full constructor */
	public NoticeType(String ntName, Integer ntFlag) {
		this.ntName = ntName;
		this.ntFlag = ntFlag;
	}

	// Property accessors

	public Integer getNtId() {
		return this.ntId;
	}

	public void setNtId(Integer ntId) {
		this.ntId = ntId;
	}

	public String getNtName() {
		return this.ntName;
	}

	public void setNtName(String ntName) {
		this.ntName = ntName;
	}

	public Integer getNtFlag() {
		return this.ntFlag;
	}

	public void setNtFlag(Integer ntFlag) {
		this.ntFlag = ntFlag;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof NoticeType)
		{
			NoticeType nt = (NoticeType)arg0;
			
			return ntId.equals(nt.getNtId());
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return ntId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.ntId + "," + this.ntName;
	}

}