package org.king.notice.bean;

import java.util.Date;

import org.apache.struts.upload.FormFile;
import org.king.common.service.CommonService;

/**
 * NoticeAttachment entity. @author MyEclipse Persistence Tools
 */

public class NoticeAttachment extends org.king.framework.domain.BaseObject
		implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer naId;
	private Integer naInfomationNotice;
	private Integer naAttachmentCategory;
	private String naCode;
	private String naName;
	private String naUrl;
	private Integer naFlag = CommonService.NORMAL_FLAG;
	private String naCreateUser;
	private Date naCreateTime;
	private String naLocalUrl;
	private Integer naSecretDegree;
	private Integer naDossier;
	private Date naSubmitDate;
	private String naDescription;
	private Integer naSubmitDepartment;
	private Integer naInputInerface;
	private Integer naContract;
	
	private FormFile attachmentFile;
	private boolean modified = false;

	// Constructors

	/** default constructor */
	public NoticeAttachment() {
	}

	/** minimal constructor */
	public NoticeAttachment(Integer naFlag, String naCreateUser,
			Date naCreateTime) {
		this.naFlag = naFlag;
		this.naCreateUser = naCreateUser;
		this.naCreateTime = naCreateTime;
	}

	/** full constructor */
	public NoticeAttachment(Integer naInfomationNotice,
			Integer naAttachmentCategory, String naCode, String naName,
			String naUrl, Integer naFlag, String naCreateUser,
			Date naCreateTime, String naLocalUrl, Integer naSecretDegree,
			Integer naDossier, Date naSubmitDate, String naDescription,
			Integer naSubmitDepartment, Integer naInputInerface,
			Integer naContract) {
		this.setNaInfomationNotice(naInfomationNotice);
		this.naAttachmentCategory = naAttachmentCategory;
		this.naCode = naCode;
		this.naName = naName;
		this.naUrl = naUrl;
		this.naFlag = naFlag;
		this.naCreateUser = naCreateUser;
		this.naCreateTime = naCreateTime;
		this.naLocalUrl = naLocalUrl;
		this.naSecretDegree = naSecretDegree;
		this.naDossier = naDossier;
		this.naSubmitDate = naSubmitDate;
		this.naDescription = naDescription;
		this.naSubmitDepartment = naSubmitDepartment;
		this.naInputInerface = naInputInerface;
		this.naContract = naContract;
	}

	// Property accessors

	public Integer getNaId() {
		return this.naId;
	}

	public void setNaId(Integer naId) {
		this.naId = naId;
	}

	public Integer getNaAttachmentCategory() {
		return this.naAttachmentCategory;
	}

	public void setNaAttachmentCategory(Integer naAttachmentCategory) {
		this.naAttachmentCategory = naAttachmentCategory;
	}

	public String getNaCode() {
		return this.naCode;
	}

	public void setNaCode(String naCode) {
		this.naCode = naCode;
	}

	public String getNaName() {
		return this.naName;
	}

	public void setNaName(String naName) {
		this.naName = naName;
	}

	public String getNaUrl() {
		return this.naUrl;
	}

	public void setNaUrl(String naUrl) {
		this.naUrl = naUrl;
	}

	public Integer getNaFlag() {
		return this.naFlag;
	}

	public void setNaFlag(Integer naFlag) {
		this.naFlag = naFlag;
	}

	public String getNaCreateUser() {
		return this.naCreateUser;
	}

	public void setNaCreateUser(String naCreateUser) {
		this.naCreateUser = naCreateUser;
	}

	public Date getNaCreateTime() {
		return this.naCreateTime;
	}

	public void setNaCreateTime(Date naCreateTime) {
		this.naCreateTime = naCreateTime;
	}

	public String getNaLocalUrl() {
		return this.naLocalUrl;
	}

	public void setNaLocalUrl(String naLocalUrl) {
		this.naLocalUrl = naLocalUrl;
	}

	public Integer getNaSecretDegree() {
		return this.naSecretDegree;
	}

	public void setNaSecretDegree(Integer naSecretDegree) {
		this.naSecretDegree = naSecretDegree;
	}

	public Integer getNaDossier() {
		return this.naDossier;
	}

	public void setNaDossier(Integer naDossier) {
		this.naDossier = naDossier;
	}

	public Date getNaSubmitDate() {
		return this.naSubmitDate;
	}

	public void setNaSubmitDate(Date naSubmitDate) {
		this.naSubmitDate = naSubmitDate;
	}

	public String getNaDescription() {
		return this.naDescription;
	}

	public void setNaDescription(String naDescription) {
		this.naDescription = naDescription;
	}

	public Integer getNaSubmitDepartment() {
		return this.naSubmitDepartment;
	}

	public void setNaSubmitDepartment(Integer naSubmitDepartment) {
		this.naSubmitDepartment = naSubmitDepartment;
	}

	public Integer getNaInputInerface() {
		return this.naInputInerface;
	}

	public void setNaInputInerface(Integer naInputInerface) {
		this.naInputInerface = naInputInerface;
	}

	public Integer getNaContract() {
		return this.naContract;
	}

	public void setNaContract(Integer naContract) {
		this.naContract = naContract;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof NoticeAttachment)
		{
			NoticeAttachment na = (NoticeAttachment)arg0;
			
			return naId.equals(na.getNaId());
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return naId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.naId + "," + this.naInfomationNotice;
	}

	public void setNaInfomationNotice(Integer naInfomationNotice) {
		this.naInfomationNotice = naInfomationNotice;
	}

	public Integer getNaInfomationNotice() {
		return naInfomationNotice;
	}

	public void setAttachmentFile(FormFile attachmentFile) {
		this.attachmentFile = attachmentFile;
	}

	public FormFile getAttachmentFile() {
		return attachmentFile;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public boolean isModified() {
		return modified;
	}

}