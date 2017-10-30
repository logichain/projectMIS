package org.king.common.deviceunit;

/**
 * DeviceUnit entity. @author MyEclipse Persistence Tools
 */

public class DeviceUnit extends org.king.framework.domain.BaseObject implements
		java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer duId;
	private String duName;
	private Integer duFlag;

	// Constructors

	/** default constructor */
	public DeviceUnit() {
	}

	/** full constructor */
	public DeviceUnit(String duName, Integer duFlag) {
		this.duName = duName;
		this.duFlag = duFlag;
	}

	// Property accessors

	public Integer getDuId() {
		return this.duId;
	}

	public void setDuId(Integer duId) {
		this.duId = duId;
	}

	public String getDuName() {
		return this.duName;
	}

	public void setDuName(String duName) {
		this.duName = duName;
	}

	public Integer getDuFlag() {
		return this.duFlag;
	}

	public void setDuFlag(Integer duFlag) {
		this.duFlag = duFlag;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof DeviceUnit)
		{
			DeviceUnit du = (DeviceUnit)arg0;
			if(du.getDuId().equals(duId))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return duId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.duId + this.duName;
	}

}