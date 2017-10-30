package org.king.tender.bean;

import java.math.BigDecimal;

/**
 * BudgetItem entity. @author MyEclipse Persistence Tools
 */

public class BudgetItem extends org.king.framework.domain.BaseObject implements
		java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer biId;
	private Integer biModel;
	private BigDecimal biAmount = BigDecimal.ZERO;
	private String biRemark;
	
	private BudgetItemModel itemModel;

	// Constructors

	/** default constructor */
	public BudgetItem() {
	}

	/** full constructor */
	public BudgetItem(Integer biModel, BigDecimal biAmount, String biRemark) {
		this.biModel = biModel;
		this.biAmount = biAmount;
		this.biRemark = biRemark;
	}

	// Property accessors

	public Integer getBiId() {
		return this.biId;
	}

	public void setBiId(Integer biId) {
		this.biId = biId;
	}

	public Integer getBiModel() {
		return this.biModel;
	}

	public void setBiModel(Integer biModel) {
		this.biModel = biModel;
	}

	public BigDecimal getBiAmount() {
		return this.biAmount;
	}

	public void setBiAmount(BigDecimal biAmount) {
		this.biAmount = biAmount;
	}

	public String getBiRemark() {
		return this.biRemark;
	}

	public void setBiRemark(String biRemark) {
		this.biRemark = biRemark;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof BudgetItem)
		{
			BudgetItem bi = (BudgetItem)arg0;
			return bi.getBiId().equals(biId);
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return biId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return biId + biRemark;
	}

	public void setItemModel(BudgetItemModel itemModel) {
		this.itemModel = itemModel;
	}

	public BudgetItemModel getItemModel() {
		return itemModel;
	}

}