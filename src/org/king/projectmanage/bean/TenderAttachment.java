package org.king.projectmanage.bean;

import java.util.Date;

import org.apache.struts.upload.FormFile;
import org.king.common.attachmentcategory.AttachmentCategory;
import org.king.security.domain.Account;

/**
 * ProjectAttachment entity. @author MyEclipse Persistence Tools
 */

public class TenderAttachment extends org.king.common.SearchBaseObject
		implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer taId;
	
	private Integer taTenderProject;
	private Integer taAttachmentCategory;
	private String taCode;
	private String taName;
	private String taUrl;
	private Integer taFlag;
	private String taCreateUser;
	private Date taCreateTime;
	private String taLocalUrl;
	
	private FormFile attachmentFile;
	private boolean modified = false;
	
	private AttachmentCategory category;
	private Account createUser;
		
	// Constructors


	/** default constructor */
	public TenderAttachment() {
	}

	public Integer getTaId() {
		return taId;
	}



	public void setTaId(Integer taId) {
		this.taId = taId;
	}



	public Integer getTaAttachmentCategory() {
		return taAttachmentCategory;
	}



	public void setTaAttachmentCategory(Integer taAttachmentCategory) {
		this.taAttachmentCategory = taAttachmentCategory;
	}



	public String getTaCode() {
		return taCode;
	}



	public void setTaCode(String taCode) {
		this.taCode = taCode;
	}



	public String getTaName() {
		return taName;
	}



	public void setTaName(String taName) {
		this.taName = taName;
	}



	public String getTaUrl() {
		return taUrl;
	}



	public void setTaUrl(String taUrl) {
		this.taUrl = taUrl;
	}



	public Integer getTaFlag() {
		return taFlag;
	}



	public void setTaFlag(Integer taFlag) {
		this.taFlag = taFlag;
	}



	public String getTaCreateUser() {
		return taCreateUser;
	}



	public void setTaCreateUser(String taCreateUser) {
		this.taCreateUser = taCreateUser;
	}



	public Date getTaCreateTime() {
		return taCreateTime;
	}



	public void setTaCreateTime(Date taCreateTime) {
		this.taCreateTime = taCreateTime;
	}


	
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if (arg0 != null && arg0 instanceof TenderAttachment) {
			if(arg0 == this){
				return true;
			}
			TenderAttachment pa = (TenderAttachment) arg0;
			if (pa.getTaId().equals(taId)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return taId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.taId + this.taName;
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

	public void setCategory(AttachmentCategory category) {
		this.category = category;
	}

	public AttachmentCategory getCategory() {
		return category;
	}

	public void setCreateUser(Account createUser) {
		this.createUser = createUser;
	}

	public Account getCreateUser() {
		return createUser;
	}



	public Integer getTaTenderProject() {
		return taTenderProject;
	}



	public void setTaTenderProject(Integer taTenderProject) {
		this.taTenderProject = taTenderProject;
	}

	public String getTaLocalUrl() {
		return taLocalUrl;
	}

	public void setTaLocalUrl(String taLocalUrl) {
		this.taLocalUrl = taLocalUrl;
	}

}