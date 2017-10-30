package org.king.common.paymenttype;


public class PaymentType extends org.king.framework.domain.BaseObject
implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer ptId;
	private String ptName;
	private Integer ptFlag;
	
	public PaymentType(){
		
	}
	
	public PaymentType(String ptName, Integer ptFlag){
		this.ptName = ptName;
		this.ptFlag = ptFlag;
	}
	
	public Integer getPtId() {
		return ptId;
	}

	public void setPtId(Integer ptId) {
		this.ptId = ptId;
	}

	public String getPtName() {
		return ptName;
	}

	public void setPtName(String ptName) {
		this.ptName = ptName;
	}

	public Integer getPtFlag() {
		return ptFlag;
	}

	public void setPtFlag(Integer ptFlag) {
		this.ptFlag = ptFlag;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 != null && arg0 instanceof PaymentType) {
			if(arg0 == this){
				return true;
			}
			PaymentType tp = (PaymentType) arg0;
			if (tp.getPtId() == this.ptId) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();				
		sb.append(this.ptId);
		sb.append(".");
		sb.append(this.ptName);
		sb.append(".");
		sb.append(this.ptFlag);
		
		return sb.toString();
	}

}
