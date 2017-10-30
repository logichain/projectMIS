package org.king.common.tenderstatus;

/**
 * TenderStatus entity. @author MyEclipse Persistence Tools
 */

public class TenderStatus extends org.king.framework.domain.BaseObject
		implements java.io.Serializable {

	// Fields

	public static int TENDER_STATUS_WIN = 1;
	public static int TENDER_STATUS_FALSE = 2;
	public static int TENDER_STATUS_NOTENDER = 3;
	public static int TENDER_STATUS_TENDERING = 4;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer tsId;
	private String tsName;
	private Integer tsFlag;
	private Integer tsTenderResult;

	// Constructors

	/** default constructor */
	public TenderStatus() {
	}

	/** full constructor */
	public TenderStatus(String tsName, Integer tsFlag) {
		this.tsName = tsName;
		this.tsFlag = tsFlag;
	}

	// Property accessors

	public Integer getTsId() {
		return this.tsId;
	}

	public void setTsId(Integer tsId) {
		this.tsId = tsId;
	}

	public String getTsName() {
		return this.tsName;
	}

	public void setTsName(String tsName) {
		this.tsName = tsName;
	}

	public Integer getTsFlag() {
		return this.tsFlag;
	}

	public void setTsFlag(Integer tsFlag) {
		this.tsFlag = tsFlag;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof TenderStatus)
		{
			TenderStatus ts = (TenderStatus) arg0;
			if(ts.getTsId().equals(tsId))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.tsId;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.tsId + this.tsName;
	}

	public void setTsTenderResult(Integer tsTenderResult) {
		this.tsTenderResult = tsTenderResult;
	}

	public Integer getTsTenderResult() {
		return tsTenderResult;
	}

}