package org.king.projectmanage.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.king.customer.bean.Customer;

/**
 * ProjectContract entity. @author MyEclipse Persistence Tools
 */

public class ProjectContract extends org.king.framework.domain.BaseObject implements java.io.Serializable {

	public static int Contract_Status_INIT = 0;
	public static int Contract_Status_CHECKING = 1;// 评审中
	public static int Contract_Status_RETURN = 2;// 驳回
	public static int Contract_Status_CHECKED = 3;// 通过
	
	public static int Contract_TYPE_PURCHASE = 2;
	public static int Contract_TYPE_SALE = 1;
		
	public static int Contract_CATEGORY_PROJECT = 1;
	public static int Contract_CATEGORY_TENDER = 2;
	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer pcId;
	private Integer pcProject;
	private String pcTitle;
	private String pcRemark = "";
	private Integer pcStatus = 0;
	private Integer pcCustomer;
	private Integer pcType;
	private Date pcCheckBegin;
	private Date pcCheckEnd;
	
	private Double pcContractPrice;
	private Double pcPriceDiff;
	private String pcPayType;
	private Integer pcQualityAmountYear;
	private Float pcQualityAmountPercent;
	private Integer pcCategory;
	
	private Customer customer;
	private TenderProject tenderProject;
	private ArrayList<ProjectAttachment> attachmentList = new ArrayList<ProjectAttachment>();

	// Constructors

	/** default constructor */
	public ProjectContract() {
	}

	/** full constructor */
	public ProjectContract(Integer pcProject, String pcTitle, String pcRemark, Integer pcStatus) {
		this.pcProject = pcProject;
		this.pcTitle = pcTitle;
		this.pcRemark = pcRemark;
		this.pcStatus = pcStatus;
	}

	// Property accessors

	public Integer getPcId() {
		return this.pcId;
	}

	public void setPcId(Integer pcId) {
		this.pcId = pcId;
	}

	public Integer getPcProject() {
		return this.pcProject;
	}

	public void setPcProject(Integer pcProject) {
		this.pcProject = pcProject;
	}

	public String getPcTitle() {
		return this.pcTitle;
	}

	public void setPcTitle(String pcTitle) {
		this.pcTitle = pcTitle;
	}

	public String getPcRemark() {
		return this.pcRemark;
	}

	public void setPcRemark(String pcRemark) {
		this.pcRemark = pcRemark;
	}

	public Integer getPcStatus() {
		return this.pcStatus;
	}

	public void setPcStatus(Integer pcStatus) {
		this.pcStatus = pcStatus;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if (arg0 != null && arg0 instanceof ProjectContract) {
			ProjectContract pc = (ProjectContract) arg0;
			return pc.getPcId().equals(pcId);
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return pcId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return pcId + pcTitle;
	}

	public void setTenderProject(TenderProject tenderProject) {
		this.tenderProject = tenderProject;
	}

	public TenderProject getTenderProject() {
		return tenderProject;
	}

	public void setPcCustomer(Integer pcCustomer) {
		this.pcCustomer = pcCustomer;
	}

	public Integer getPcCustomer() {
		return pcCustomer;
	}

	public void setPcType(Integer pcType) {
		this.pcType = pcType;
	}

	public Integer getPcType() {
		return pcType;
	}

	public void setPcCheckBegin(Date pcCheckBegin) {
		this.pcCheckBegin = pcCheckBegin;
	}

	public Date getPcCheckBegin() {
		return pcCheckBegin;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setAttachmentList(ArrayList<ProjectAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public ArrayList<ProjectAttachment> getAttachmentList() {
		return attachmentList;
	}

	public Set<ProjectAttachment> getAttachmentSet() {
		// return attachmentSet;
		Set<ProjectAttachment> detailSet = new HashSet<ProjectAttachment>();
		detailSet.addAll(attachmentList);

		return detailSet;
	}

	public void setAttachmentSet(Set<ProjectAttachment> attachmentSet) {
		attachmentList.clear();
		attachmentList.addAll(attachmentSet);
	}

	public void setPcCategory(Integer pcCategory) {
		this.pcCategory = pcCategory;
	}

	public Integer getPcCategory() {
		return pcCategory;
	}

	public void setPcCheckEnd(Date pcCheckEnd) {
		this.pcCheckEnd = pcCheckEnd;
	}

	public Date getPcCheckEnd() {
		return pcCheckEnd;
	}

	public void setPcContractPrice(Double pcContractPrice) {
		this.pcContractPrice = pcContractPrice;
	}

	public Double getPcContractPrice() {
		return pcContractPrice;
	}

	public void setPcPriceDiff(Double pcPriceDiff) {
		this.pcPriceDiff = pcPriceDiff;
	}

	public Double getPcPriceDiff() {
		return pcPriceDiff;
	}

	public void setPcPayType(String pcPayType) {
		this.pcPayType = pcPayType;
	}

	public String getPcPayType() {
		return pcPayType;
	}

	public void setPcQualityAmountYear(Integer pcQualityAmountYear) {
		this.pcQualityAmountYear = pcQualityAmountYear;
	}

	public Integer getPcQualityAmountYear() {
		return pcQualityAmountYear;
	}

	public void setPcQualityAmountPercent(Float pcQualityAmountPercent) {
		this.pcQualityAmountPercent = pcQualityAmountPercent;
	}

	public Float getPcQualityAmountPercent() {
		return pcQualityAmountPercent;
	}

}