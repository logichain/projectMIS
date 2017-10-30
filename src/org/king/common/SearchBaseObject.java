package org.king.common;

import org.king.framework.domain.BaseObject;

public class SearchBaseObject extends BaseObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderColumn = "";
	private boolean ascFlag = true;
	private Integer pageItemCount = 20;
	private int offset = 0;

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public boolean isAscFlag() {
		return ascFlag;
	}

	public void setAscFlag(boolean ascFlag) {
		this.ascFlag = ascFlag;
	}

	public Integer getPageItemCount() {
		return pageItemCount;
	}

	public void setPageItemCount(Integer pageItemCount) {
		this.pageItemCount = pageItemCount;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

}
