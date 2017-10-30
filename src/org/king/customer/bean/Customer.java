package org.king.customer.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.king.common.customernature.CustomerNature;
import org.king.common.customertype.CustomerType;
import org.king.common.tradetype.TradeType;
import org.king.security.domain.Account;

/**
 * Customer entity. @author MyEclipse Persistence Tools
 */

public class Customer extends org.king.common.SearchBaseObject implements
		java.io.Serializable {
	
	public static final Integer CUSTOMER_FLAG_ALTERNATIVE = 0;
	public static final Integer CUSTOMER_FLAG_QUALIFIED = 1;
	public static final Integer CUSTOMER_FLAG_CHECKING = 7;
	public static final Integer CUSTOMER_FLAG_CHECKED_OK = 8;
	public static final Integer CUSTOMER_FLAG_CHECKED_NO = 9;

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer CId;
	private Integer CType;
	private String CName;
	private String CAddress;
	private String CPhone;
	private String CFax;
	private String CPostcode;
	private String CSaleman;
	private String CContactPerson;
	private String CCellphone;
	private String CMail;
	private String CRemarks;
	private Integer CFlag;
	private String CCreateUser;
	private Date CCreateTime;
	private Integer CTradeType;
	private String CShortName;
	private String CScale;
	private Integer CNature;
	private String COrganizationCode;
	
	private CustomerType customerType;
	private TradeType tradeType;
	private Account saleman;
	private CustomerNature customerNature;
	
	private ArrayList<CustomerAttachment> attachmentList = new ArrayList<CustomerAttachment>();
	private CustomerAttachment currentAttachment;
	// Constructors


	/** default constructor */
	public Customer() {
	}

	/** minimal constructor */
	public Customer(Integer CType, String CName, String CAddress,
			String CPhone, Integer CFlag, String CCreateUser, Date CCreateTime) {
		this.CType = CType;
		this.CName = CName;
		this.CAddress = CAddress;
		this.CPhone = CPhone;
		this.CFlag = CFlag;
		this.CCreateUser = CCreateUser;
		this.CCreateTime = CCreateTime;
	}


	// Property accessors

	public Integer getCId() {
		return this.CId;
	}

	public void setCId(Integer CId) {
		this.CId = CId;
	}

	public Integer getCType() {
		return this.CType;
	}

	public void setCType(Integer CType) {
		this.CType = CType;
	}

	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	public String getCAddress() {
		return this.CAddress;
	}

	public void setCAddress(String CAddress) {
		this.CAddress = CAddress;
	}

	public String getCPhone() {
		return this.CPhone;
	}

	public void setCPhone(String CPhone) {
		this.CPhone = CPhone;
	}

	public String getCFax() {
		return this.CFax;
	}

	public void setCFax(String CFax) {
		this.CFax = CFax;
	}

	public String getCPostcode() {
		return this.CPostcode;
	}

	public void setCPostcode(String CPostcode) {
		this.CPostcode = CPostcode;
	}

	public String getCSaleman() {
		return this.CSaleman;
	}

	public void setCSaleman(String CSaleman) {
		this.CSaleman = CSaleman;
	}

	public String getCContactPerson() {
		return this.CContactPerson;
	}

	public void setCContactPerson(String CContactPerson) {
		this.CContactPerson = CContactPerson;
	}

	public String getCCellphone() {
		return this.CCellphone;
	}

	public void setCCellphone(String CCellphone) {
		this.CCellphone = CCellphone;
	}

	public String getCMail() {
		return this.CMail;
	}

	public void setCMail(String CMail) {
		this.CMail = CMail;
	}

	public String getCRemarks() {
		return this.CRemarks;
	}

	public void setCRemarks(String CRemarks) {
		this.CRemarks = CRemarks;
	}

	public Integer getCFlag() {
		return this.CFlag;
	}

	public void setCFlag(Integer CFlag) {
		this.CFlag = CFlag;
	}

	public String getCCreateUser() {
		return this.CCreateUser;
	}

	public void setCCreateUser(String CCreateUser) {
		this.CCreateUser = CCreateUser;
	}

	public Date getCCreateTime() {
		return this.CCreateTime;
	}

	public void setCCreateTime(Date CCreateTime) {
		this.CCreateTime = CCreateTime;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof Customer)
		{
			Customer c = (Customer) arg0;
			if(c.getCId().equals(this.CId))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.CId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.CId + this.CName;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCTradeType(Integer cTradeType) {
		CTradeType = cTradeType;
	}

	public Integer getCTradeType() {
		if(CTradeType != null && CTradeType == 0)
		{
			return null;
		}
		return CTradeType;
	}

	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

	public TradeType getTradeType() {
		return tradeType;
	}

	public void setSaleman(Account saleman) {
		this.saleman = saleman;
	}

	public Account getSaleman() {
		return saleman;
	}

	public void setCShortName(String cShortName) {
		CShortName = cShortName;
	}

	public String getCShortName() {
		return CShortName;
	}

	public void setCScale(String cScale) {
		CScale = cScale;
	}

	public String getCScale() {
		return CScale;
	}

	public void setCNature(Integer cNature) {
		CNature = cNature;
	}

	public Integer getCNature() {
		if(CNature != null && CNature.equals(0))
		{
			return null;
		}
		return CNature;
	}

	public void setCOrganizationCode(String cOrganizationCode) {
		COrganizationCode = cOrganizationCode;
	}

	public String getCOrganizationCode() {
		return COrganizationCode;
	}

	public void setCustomerNature(CustomerNature customerNature) {
		this.customerNature = customerNature;
	}

	public CustomerNature getCustomerNature() {
		return customerNature;
	}
	
	public Set<CustomerAttachment> getAttachmentSet() {
		// return attachmentSet;
		Set<CustomerAttachment> detailSet = new HashSet<CustomerAttachment>();
		detailSet.addAll(attachmentList);

		return detailSet;
	}

	public void setAttachmentSet(Set<CustomerAttachment> attachmentSet) {
		attachmentList.clear();
		attachmentList.addAll(attachmentSet);
	}
	

	public ArrayList<CustomerAttachment> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(ArrayList<CustomerAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public CustomerAttachment getCurrentAttachment() {
		return currentAttachment;
	}

	public void setCurrentAttachment(CustomerAttachment currentAttachment) {
		this.currentAttachment = currentAttachment;
	}
}