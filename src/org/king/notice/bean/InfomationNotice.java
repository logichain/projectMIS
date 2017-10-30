package org.king.notice.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.king.common.noticetype.NoticeType;
import org.king.common.service.CommonService;
import org.king.department.bean.Department;

/**
 * InfomationNotice entity. @author MyEclipse Persistence Tools
 */

public class InfomationNotice extends org.king.common.SearchBaseObject
		implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer inId;
	private String inTheme;
	private Integer inDept;
	private Integer inSecretDegree;
	private Integer inType;
	private String inReleaseDate;
	private String inRemark;
	private Integer inFlag = CommonService.NORMAL_FLAG;
	private String inCreateUser;
	private Date inCreateTime;
	
	private NoticeType noticeType;
	private Department department;
	
	private ArrayList<NoticeAttachment> attachmentList = new ArrayList<NoticeAttachment>();
	private NoticeAttachment currentAttachment;
	
	// Constructors

	/** default constructor */
	public InfomationNotice() {
	}

	/** full constructor */
	public InfomationNotice(String inTheme, Integer inDept,
			Integer inSecretDegree, Integer inType, String inReleaseDate,
			String inRemark, String inCreateUser, Date inCreateTime) {
	
		this.inTheme = inTheme;
		this.inDept = inDept;
		this.inSecretDegree = inSecretDegree;
		this.inType = inType;
		this.inReleaseDate = inReleaseDate;
		this.inRemark = inRemark;
		this.inCreateUser = inCreateUser;
		this.inCreateTime = inCreateTime;
	}

	// Property accessors

	public Integer getInId() {
		return this.inId;
	}

	public void setInId(Integer inId) {
		this.inId = inId;
	}
	
	public String getInTheme() {
		return this.inTheme;
	}

	public void setInTheme(String inTheme) {
		this.inTheme = inTheme;
	}

	public Integer getInDept() {
		return this.inDept;
	}

	public void setInDept(Integer inDept) {
		this.inDept = inDept;
	}

	public Integer getInSecretDegree() {
		return this.inSecretDegree;
	}

	public void setInSecretDegree(Integer inSecretDegree) {
		this.inSecretDegree = inSecretDegree;
	}

	public Integer getInType() {
		return this.inType;
	}

	public void setInType(Integer inType) {
		this.inType = inType;
	}

	public String getInReleaseDate() {
		return this.inReleaseDate;
	}

	public void setInReleaseDate(String inReleaseDate) {
		this.inReleaseDate = inReleaseDate;
	}

	public String getInRemark() {
		return this.inRemark;
	}

	public void setInRemark(String inRemark) {
		this.inRemark = inRemark;
	}

	public String getInCreateUser() {
		return this.inCreateUser;
	}

	public void setInCreateUser(String inCreateUser) {
		this.inCreateUser = inCreateUser;
	}

	public Date getInCreateTime() {
		return this.inCreateTime;
	}

	public void setInCreateTime(Date inCreateTime) {
		this.inCreateTime = inCreateTime;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof InfomationNotice)
		{
			InfomationNotice in = (InfomationNotice)arg0;
			
			return inId.equals(in.getInId());
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return inId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return inId + "," + inTheme;
	}

	public void setInFlag(Integer inFlag) {
		this.inFlag = inFlag;
	}

	public Integer getInFlag() {
		return inFlag;
	}
	
	public void setAttachmentList(ArrayList<NoticeAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public ArrayList<NoticeAttachment> getAttachmentList() {
	
		return attachmentList;
	}
	
	public Set<NoticeAttachment> getAttachmentSet() {
//		return attachmentSet;
		Set<NoticeAttachment> detailSet = new HashSet<NoticeAttachment>();
		detailSet.addAll(attachmentList);
		
		return detailSet;
	}
	
	public void setAttachmentSet(Set<NoticeAttachment> attachmentSet) {
		attachmentList.clear();
		attachmentList.addAll(attachmentSet);
	}

	public void setCurrentAttachment(NoticeAttachment currentAttachment) {
		this.currentAttachment = currentAttachment;
	}

	public NoticeAttachment getCurrentAttachment() {
		return currentAttachment;
	}

	public void setNoticeType(NoticeType noticeType) {
		this.noticeType = noticeType;
	}

	public NoticeType getNoticeType() {
		return noticeType;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Department getDepartment() {
		return department;
	}

}