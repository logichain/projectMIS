package org.king.customer.bean;

import java.util.Date;

import org.apache.struts.upload.FormFile;

/**
 * ProjectAttachment entity. @author MyEclipse Persistence Tools
 */

public class CustomerAttachment extends org.king.common.SearchBaseObject
		implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer caId;
	
	private Integer caCustomer;
	private String caName;
	private String caUrl;
	private String caLocalUrl;
	private Integer caFlag;
	private String caCreateUser;
	private Date caCreateTime;
	
	private FormFile attachmentFile;
	private boolean modified = false;	
		
	// Constructors


	/** default constructor */
	public CustomerAttachment() {
	}

	public Integer getCaId() {
		return caId;
	}


	public void setCaId(Integer caId) {
		this.caId = caId;
	}


	public Integer getCaCustomer() {
		return caCustomer;
	}


	public void setCaCustomer(Integer caCustomer) {
		this.caCustomer = caCustomer;
	}


	public String getCaName() {
		return caName;
	}


	public void setCaName(String caName) {
		this.caName = caName;
	}


	public String getCaUrl() {
		return caUrl;
	}


	public void setCaUrl(String caUrl) {
		this.caUrl = caUrl;
	}


	public Integer getCaFlag() {
		return caFlag;
	}


	public void setCaFlag(Integer caFlag) {
		this.caFlag = caFlag;
	}


	public String getCaCreateUser() {
		return caCreateUser;
	}


	public void setCaCreateUser(String caCreateUser) {
		this.caCreateUser = caCreateUser;
	}


	public Date getCaCreateTime() {
		return caCreateTime;
	}


	public void setCaCreateTime(Date caCreateTime) {
		this.caCreateTime = caCreateTime;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if (arg0 != null && arg0 instanceof CustomerAttachment) {
			if(arg0 == this){
				return true;
			}
			CustomerAttachment pa = (CustomerAttachment) arg0;
			if (pa.getCaId().equals(caId)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return caId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.caId + this.caName;
	}


	public FormFile getAttachmentFile() {
		return attachmentFile;
	}

	public void setAttachmentFile(FormFile attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public boolean isModified() {
		return modified;
	}

	public String getCaLocalUrl() {
		return caLocalUrl;
	}

	public void setCaLocalUrl(String caLocalUrl) {
		this.caLocalUrl = caLocalUrl;
	}

}