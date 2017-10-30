package org.king.common.attachmentcategory;

/**
 * AttachmentCategory entity. @author MyEclipse Persistence Tools
 */

public class AttachmentCategory extends org.king.framework.domain.BaseObject
		implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	public static Integer TENDER_ATTACHMENT = 1;
	public static Integer TENDER_DOC_ATTACHMENT = 6;
	private static final long serialVersionUID = 1L;
	private Integer acId;
	private String acName;
	private Integer acFlag;

	// Constructors

	/** default constructor */
	public AttachmentCategory() {
	}

	/** full constructor */
	public AttachmentCategory(String acName, Integer acFlag) {
		this.acName = acName;
		this.acFlag = acFlag;
	}

	// Property accessors

	public Integer getAcId() {
		return this.acId;
	}

	public void setAcId(Integer acId) {
		this.acId = acId;
	}

	public String getAcName() {
		return this.acName;
	}

	public void setAcName(String acName) {
		this.acName = acName;
	}

	public Integer getAcFlag() {
		return this.acFlag;
	}

	public void setAcFlag(Integer acFlag) {
		this.acFlag = acFlag;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof AttachmentCategory)
		{
			AttachmentCategory ac = (AttachmentCategory) arg0;
			if(ac.getAcId().equals(acId))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.acId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.acId + this.acName;
	}

}