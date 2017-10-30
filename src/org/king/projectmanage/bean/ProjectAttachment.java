package org.king.projectmanage.bean;

import java.util.Date;

import org.apache.struts.upload.FormFile;
import org.king.common.attachmentcategory.AttachmentCategory;
import org.king.department.bean.Department;
import org.king.security.domain.Account;

import com.ibm.icu.text.SimpleDateFormat;

/**
 * ProjectAttachment entity. @author MyEclipse Persistence Tools
 */

public class ProjectAttachment extends org.king.common.SearchBaseObject
		implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer paId;
	private Integer paTenderProject;
	private Integer paAttachmentCategory;
	private String paCode;
	private String paName;
	private String paUrl;
	private Integer paFlag;
	private String paCreateUser;
	private Date paCreateTime;
	private String paLocalUrl;
	
	private String paSubmitDate = SimpleDateFormat.getDateInstance().format(new Date());
	private String paDescription;
	private Integer paSubmitDepartment;
	private Integer paInputInterface;
	private Integer paContract;
	private Integer paProjectApprovalRecord;
	
	private FormFile attachmentFile;
	private boolean modified = false;
	
	private AttachmentCategory category;
	private Department submitDepartment;
	private Account createUser;
		
	// Constructors


	/** default constructor */
	public ProjectAttachment() {
	}

	/** minimal constructor */
	public ProjectAttachment(Integer paFlag, String paCreateUser,
			Date paCreateTime) {
		this.paFlag = paFlag;
		this.paCreateUser = paCreateUser;
		this.paCreateTime = paCreateTime;
	}

	/** full constructor */
	public ProjectAttachment(Integer paTenderProject,
			Integer paAttachmentCategory, String paCode, String paName,
			String paUrl, Integer paFlag, String paCreateUser, Date paCreateTime) {
		this.paTenderProject = paTenderProject;
		this.paAttachmentCategory = paAttachmentCategory;
		this.paCode = paCode;
		this.paName = paName;
		this.paUrl = paUrl;
		this.paFlag = paFlag;
		this.paCreateUser = paCreateUser;
		this.paCreateTime = paCreateTime;
	}

	// Property accessors

	public Integer getPaId() {
		return this.paId;
	}

	public void setPaId(Integer paId) {
		this.paId = paId;
	}

	public Integer getPaTenderProject() {
		return this.paTenderProject;
	}

	public void setPaTenderProject(Integer paTenderProject) {
		this.paTenderProject = paTenderProject;
	}

	public Integer getPaAttachmentCategory() {
		return this.paAttachmentCategory;
	}

	public void setPaAttachmentCategory(Integer paAttachmentCategory) {
		this.paAttachmentCategory = paAttachmentCategory;
	}

	public String getPaCode() {
		return this.paCode;
	}

	public void setPaCode(String paCode) {
		this.paCode = paCode;
	}

	public String getPaName() {
		return this.paName;
	}

	public void setPaName(String paName) {
		this.paName = paName;
	}

	public String getPaUrl() {
		return this.paUrl;
	}

	public void setPaUrl(String paUrl) {
		this.paUrl = paUrl;
	}

	public Integer getPaFlag() {
		return this.paFlag;
	}

	public void setPaFlag(Integer paFlag) {
		this.paFlag = paFlag;
	}

	public String getPaCreateUser() {
		return this.paCreateUser;
	}

	public void setPaCreateUser(String paCreateUser) {
		this.paCreateUser = paCreateUser;
	}

	public Date getPaCreateTime() {
		return this.paCreateTime;
	}

	public void setPaCreateTime(Date paCreateTime) {
		this.paCreateTime = paCreateTime;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if (arg0 != null && arg0 instanceof ProjectAttachment) {
			if(arg0 == this){
				return true;
			}
			ProjectAttachment pa = (ProjectAttachment) arg0;
			if (pa.getPaId().equals(paId)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return paId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.paId + this.paName;
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

	public void setPaLocalUrl(String paLocalUrl) {
		this.paLocalUrl = paLocalUrl;
	}

	public String getPaLocalUrl() {
		return paLocalUrl;
	}

	public void setPaSubmitDate(String paSubmitDate) {
		this.paSubmitDate = paSubmitDate;
	}

	public String getPaSubmitDate() {
		return paSubmitDate;
	}

	public void setPaDescription(String paDescription) {
		this.paDescription = paDescription;
	}

	public String getPaDescription() {
		return paDescription;
	}

	public void setSubmitDepartment(Department submitDepartment) {
		this.submitDepartment = submitDepartment;
	}

	public Department getSubmitDepartment() {				
		return submitDepartment;
	}

	public void setPaSubmitDepartment(Integer paSubmitDepartment) {
		this.paSubmitDepartment = paSubmitDepartment;
	}

	public Integer getPaSubmitDepartment() {
		if(paSubmitDepartment != null && paSubmitDepartment.equals(0))
		{
			return null;
		}
		return paSubmitDepartment;
	}

	public void setPaInputInterface(Integer paInputInterface) {
		this.paInputInterface = paInputInterface;
	}

	public Integer getPaInputInterface() {
		return paInputInterface;
	}

	public void setPaContract(Integer paContract) {
		this.paContract = paContract;
	}

	public Integer getPaContract() {
		return paContract;
	}

	public void setCreateUser(Account createUser) {
		this.createUser = createUser;
	}

	public Account getCreateUser() {
		return createUser;
	}

	public void setPaProjectApprovalRecord(Integer paProjectApprovalRecord) {
		this.paProjectApprovalRecord = paProjectApprovalRecord;
	}

	public Integer getPaProjectApprovalRecord() {
		return paProjectApprovalRecord;
	}

}