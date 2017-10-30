package org.king.tender.bean;

/**
 * BudgetItemModel entity. @author MyEclipse Persistence Tools
 */

public class BudgetItemModel extends org.king.framework.domain.BaseObject
		implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer bimId;
	private String bimCode;
	private String bimName;
	private Integer bimTenderEditable;
	private Integer bimApplyEditable;
	private Integer bimOrder;
	private Integer bimGatherFlag;
	private Integer bimTenderFlag;
	private Integer bimApplyFlag;
	// Constructors

	/** default constructor */
	public BudgetItemModel() {
	}

	/** full constructor */
	public BudgetItemModel(String bimCode, String bimName, Integer bimEditable,
			Integer bimOrder) {
		this.bimCode = bimCode;
		this.bimName = bimName;
		this.bimOrder = bimOrder;
	}

	// Property accessors

	public Integer getBimId() {
		return this.bimId;
	}

	public void setBimId(Integer bimId) {
		this.bimId = bimId;
	}

	public String getBimCode() {
		return this.bimCode;
	}

	public void setBimCode(String bimCode) {
		this.bimCode = bimCode;
	}

	public String getBimName() {
		return this.bimName;
	}

	public void setBimName(String bimName) {
		this.bimName = bimName;
	}

	public Integer getBimOrder() {
		return this.bimOrder;
	}

	public void setBimOrder(Integer bimOrder) {
		this.bimOrder = bimOrder;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof BudgetItemModel)
		{
			BudgetItemModel bim = (BudgetItemModel)arg0;
			return bim.getBimId().equals(bimId);
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return bimId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return bimId + bimName;
	}

	public void setBimGatherFlag(Integer bimGatherFlag) {
		this.bimGatherFlag = bimGatherFlag;
	}

	public Integer getBimGatherFlag() {
		return bimGatherFlag;
	}

	public Integer getBimTenderFlag() {
		return bimTenderFlag;
	}

	public void setBimTenderFlag(Integer bimTenderFlag) {
		this.bimTenderFlag = bimTenderFlag;
	}

	public Integer getBimApplyFlag() {
		return bimApplyFlag;
	}

	public void setBimApplyFlag(Integer bimApplyFlag) {
		this.bimApplyFlag = bimApplyFlag;
	}

	public Integer getBimTenderEditable() {
		return bimTenderEditable;
	}

	public void setBimTenderEditable(Integer bimTenderEditable) {
		this.bimTenderEditable = bimTenderEditable;
	}

	public Integer getBimApplyEditable() {
		return bimApplyEditable;
	}

	public void setBimApplyEditable(Integer bimApplyEditable) {
		this.bimApplyEditable = bimApplyEditable;
	}

}