package org.king.accountmanage.bean;

import java.util.Date;

/**
 * AccountSaleContract entity. @author MyEclipse Persistence Tools
 */

public class AccountSaleContract extends org.king.framework.domain.BaseObject implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer ascId;
	private Integer ascProject;
	private String ascCode;
	private Integer ascCustomer;
	private String ascCustomerName;
	private String ascObject;
	private String ascSignDate;
	private double ascAmount;
	private double ascDeviceAmount;
	private double ascInstallAmount;
	private double ascServiceAmount;
	private double ascAddAmount;
	private double ascInvoiceAmount;
	private double ascGatheringAmount;
	private Integer ascPeriod;
	private String ascPaymentMethod = "";
	private Date ascCreateTime;
	private String ascCreateUser;
	private Integer ascStatus = 0;

	// Constructors

	/** default constructor */
	public AccountSaleContract() {
	}

	/** minimal constructor */
	public AccountSaleContract(Integer ascProject) {
		this.ascProject = ascProject;
	}

	/** full constructor */
	public AccountSaleContract(Integer ascProject, String ascCode, Integer ascCustomer, String ascCustomerName, String ascObject, String ascSignDate, double ascAmount, double ascDeviceAmount,
			double ascInstallAmount, double ascServiceAmount, double ascAddAmount, double ascInvoiceAmount, double ascGatheringAmount, Integer ascPeriod, String ascPaymentMethod, Date ascCreateTime,
			String ascCreateUser) {
		this.ascProject = ascProject;
		this.ascCode = ascCode;
		this.ascCustomer = ascCustomer;
		this.ascCustomerName = ascCustomerName;
		this.ascObject = ascObject;
		this.ascSignDate = ascSignDate;
		this.ascAmount = ascAmount;
		this.ascDeviceAmount = ascDeviceAmount;
		this.ascInstallAmount = ascInstallAmount;
		this.ascServiceAmount = ascServiceAmount;
		this.ascAddAmount = ascAddAmount;
		this.ascInvoiceAmount = ascInvoiceAmount;
		this.ascGatheringAmount = ascGatheringAmount;
		this.ascPeriod = ascPeriod;
		this.ascPaymentMethod = ascPaymentMethod;
		this.ascCreateTime = ascCreateTime;
		this.ascCreateUser = ascCreateUser;
	}

	// Property accessors

	public Integer getAscId() {
		return this.ascId;
	}

	public void setAscId(Integer ascId) {
		this.ascId = ascId;
	}

	public Integer getAscProject() {
		return this.ascProject;
	}

	public void setAscProject(Integer ascProject) {
		this.ascProject = ascProject;
	}

	public String getAscCode() {
		return this.ascCode;
	}

	public void setAscCode(String ascCode) {
		this.ascCode = ascCode;
	}

	public Integer getAscCustomer() {
		return this.ascCustomer;
	}

	public void setAscCustomer(Integer ascCustomer) {
		this.ascCustomer = ascCustomer;
	}

	public String getAscCustomerName() {
		return this.ascCustomerName;
	}

	public void setAscCustomerName(String ascCustomerName) {
		this.ascCustomerName = ascCustomerName;
	}

	public String getAscObject() {
		return this.ascObject;
	}

	public void setAscObject(String ascObject) {
		this.ascObject = ascObject;
	}

	public String getAscSignDate() {
		return this.ascSignDate;
	}

	public void setAscSignDate(String ascSignDate) {
		this.ascSignDate = ascSignDate;
	}

	public double getAscAmount() {
//		return this.ascAmount;
		double rtn = this.ascDeviceAmount + this.ascInstallAmount + this.ascServiceAmount + this.ascAddAmount;
		
		rtn = ((int)(rtn *10000))/10000.0d;
		
		return rtn;
	}

	public void setAscAmount(double ascAmount) {
		this.ascAmount = ascAmount;
	}

	public double getAscDeviceAmount() {
		return this.ascDeviceAmount;
	}

	public void setAscDeviceAmount(double ascDeviceAmount) {
		this.ascDeviceAmount = ascDeviceAmount;
	}

	public double getAscInstallAmount() {
		return this.ascInstallAmount;
	}

	public void setAscInstallAmount(double ascInstallAmount) {
		this.ascInstallAmount = ascInstallAmount;
	}

	public double getAscServiceAmount() {
		return this.ascServiceAmount;
	}

	public void setAscServiceAmount(double ascServiceAmount) {
		this.ascServiceAmount = ascServiceAmount;
	}

	public double getAscAddAmount() {
		return this.ascAddAmount;
	}

	public void setAscAddAmount(double ascAddAmount) {
		this.ascAddAmount = ascAddAmount;
	}

	public double getAscInvoiceAmount() {
		return this.ascInvoiceAmount;
	}

	public void setAscInvoiceAmount(double ascInvoiceAmount) {
		this.ascInvoiceAmount = ascInvoiceAmount;
	}

	public double getAscGatheringAmount() {
		return this.ascGatheringAmount;
	}

	public void setAscGatheringAmount(double ascGatheringAmount) {
		this.ascGatheringAmount = ascGatheringAmount;
	}

	public Integer getAscPeriod() {
		if(ascPeriod != null && ascPeriod == 0)
		{
			return null;
		}
		return this.ascPeriod;
	}

	public void setAscPeriod(Integer ascPeriod) {
		this.ascPeriod = ascPeriod;
	}

	public String getAscPaymentMethod() {
		return this.ascPaymentMethod;
	}

	public void setAscPaymentMethod(String ascPaymentMethod) {
		this.ascPaymentMethod = ascPaymentMethod;
	}

	public Date getAscCreateTime() {
		return this.ascCreateTime;
	}

	public void setAscCreateTime(Date ascCreateTime) {
		this.ascCreateTime = ascCreateTime;
	}

	public String getAscCreateUser() {
		return this.ascCreateUser;
	}

	public void setAscCreateUser(String ascCreateUser) {
		this.ascCreateUser = ascCreateUser;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof AccountSaleContract)
		{
			AccountSaleContract asc = (AccountSaleContract)arg0;
			if(this.ascId.equals(asc.getAscId()))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return ascId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.ascCode + "," + this.ascCustomerName;
	}

	public void setAscStatus(Integer ascStatus) {
		this.ascStatus = ascStatus;
	}

	public Integer getAscStatus() {
		return ascStatus;
	}

}