package org.king.common.tradetype;

/**
 * TradeType entity. @author MyEclipse Persistence Tools
 */

public class TradeType extends org.king.framework.domain.BaseObject implements
		java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer ttId;
	private String ttName;
	private Integer ttFlag;

	// Constructors

	/** default constructor */
	public TradeType() {
	}

	/** full constructor */
	public TradeType(String ttName, Integer ttFlag) {
		this.ttName = ttName;
		this.ttFlag = ttFlag;
	}

	// Property accessors

	public Integer getTtId() {
		return this.ttId;
	}

	public void setTtId(Integer ttId) {
		this.ttId = ttId;
	}

	public String getTtName() {
		return this.ttName;
	}

	public void setTtName(String ttName) {
		this.ttName = ttName;
	}

	public Integer getTtFlag() {
		return this.ttFlag;
	}

	public void setTtFlag(Integer ttFlag) {
		this.ttFlag = ttFlag;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof TradeType)
		{
			TradeType tt = (TradeType) arg0;
			if(tt.getTtId().equals(ttId))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return ttId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ttId + ttName;
	}

}