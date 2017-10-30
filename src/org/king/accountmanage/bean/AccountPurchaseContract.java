package org.king.accountmanage.bean;

import java.util.Date;

/**
 * AccountPurchaseContract entity. @author MyEclipse Persistence Tools
 */

public class AccountPurchaseContract extends org.king.framework.domain.BaseObject implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer apcId;
	private Integer apcProject;
	private String apcCode;
	private Integer apcCustomer;
	private String apcCustomerName;
	private String apcObject;
	private String apcSignDate;
	private double apcAmount;
	private double apcDeviceAmount;
	private double apcInstallAmount;
	private double apcServiceAmount;
	private double apcAddAmount;
	private double apcInvoiceAmount;
	private double apcPaymentAmount;
	private Integer apcPeriod;
	private String apcPaymentMethod = "";
	private Date apcCreateTime;
	private String apcCreateUser;
	private Integer apcStatus = 0;

	// Constructors

	/** default constructor */
	public AccountPurchaseContract() {
	}

	/** minimal constructor */
	public AccountPurchaseContract(Integer apcProject) {
		this.apcProject = apcProject;
	}

	/** full constructor */
	public AccountPurchaseContract(Integer apcProject, String apcCode, Integer apcCustomer, String apcCustomerName, String apcObject, String apcSignDate, double apcAmount, double apcDeviceAmount,
			double apcInstallAmount, double apcServiceAmount, double apcAddAmount, double apcInvoiceAmount, double apcPaymentAmount, Integer apcPeriod, String apcPaymentMethod, Date apcCreateTime,
			String apcCreateUser, Integer apcStatus) {
		this.apcProject = apcProject;
		this.apcCode = apcCode;
		this.apcCustomer = apcCustomer;
		this.apcCustomerName = apcCustomerName;
		this.apcObject = apcObject;
		this.apcSignDate = apcSignDate;
		this.apcAmount = apcAmount;
		this.apcDeviceAmount = apcDeviceAmount;
		this.apcInstallAmount = apcInstallAmount;
		this.apcServiceAmount = apcServiceAmount;
		this.apcAddAmount = apcAddAmount;
		this.apcInvoiceAmount = apcInvoiceAmount;
		this.apcPaymentAmount = apcPaymentAmount;
		this.apcPeriod = apcPeriod;
		this.apcPaymentMethod = apcPaymentMethod;
		this.apcCreateTime = apcCreateTime;
		this.apcCreateUser = apcCreateUser;
		this.apcStatus = apcStatus;
	}

	// Property accessors

	public Integer getApcId() {
		return this.apcId;
	}

	public void setApcId(Integer apcId) {
		this.apcId = apcId;
	}

	public Integer getApcProject() {
		return this.apcProject;
	}

	public void setApcProject(Integer apcProject) {
		this.apcProject = apcProject;
	}

	public String getApcCode() {
		return this.apcCode;
	}

	public void setApcCode(String apcCode) {
		this.apcCode = apcCode;
	}

	public Integer getApcCustomer() {
		return this.apcCustomer;
	}

	public void setApcCustomer(Integer apcCustomer) {
		this.apcCustomer = apcCustomer;
	}

	public String getApcCustomerName() {
		return this.apcCustomerName;
	}

	public void setApcCustomerName(String apcCustomerName) {
		this.apcCustomerName = apcCustomerName;
	}

	public String getApcObject() {
		return this.apcObject;
	}

	public void setApcObject(String apcObject) {
		this.apcObject = apcObject;
	}

	public String getApcSignDate() {
		return this.apcSignDate;
	}

	public void setApcSignDate(String apcSignDate) {
		this.apcSignDate = apcSignDate;
	}

	public double getApcAmount() {
//		return this.apcAmount;
		double rtn = this.apcDeviceAmount + this.apcInstallAmount + this.apcServiceAmount + this.apcAddAmount;
		
		rtn = ((int)(rtn *10000))/10000.0d;
		
		return rtn;
	}

	public void setApcAmount(double apcAmount) {
		this.apcAmount = apcAmount;
	}

	public double getApcDeviceAmount() {
		return this.apcDeviceAmount;
	}

	public void setApcDeviceAmount(double apcDeviceAmount) {
		this.apcDeviceAmount = apcDeviceAmount;
	}

	public double getApcInstallAmount() {
		return this.apcInstallAmount;
	}

	public void setApcInstallAmount(double apcInstallAmount) {
		this.apcInstallAmount = apcInstallAmount;
	}

	public double getApcServiceAmount() {
		return this.apcServiceAmount;
	}

	public void setApcServiceAmount(double apcServiceAmount) {
		this.apcServiceAmount = apcServiceAmount;
	}

	public double getApcAddAmount() {
		return this.apcAddAmount;
	}

	public void setApcAddAmount(double apcAddAmount) {
		this.apcAddAmount = apcAddAmount;
	}

	public double getApcInvoiceAmount() {
		return this.apcInvoiceAmount;
	}

	public void setApcInvoiceAmount(double apcInvoiceAmount) {
		this.apcInvoiceAmount = apcInvoiceAmount;
	}

	public double getApcPaymentAmount() {
		return this.apcPaymentAmount;
	}

	public void setApcPaymentAmount(double apcPaymentAmount) {
		this.apcPaymentAmount = apcPaymentAmount;
	}

	public Integer getApcPeriod() {
		return this.apcPeriod;
	}

	public void setApcPeriod(Integer apcPeriod) {
		this.apcPeriod = apcPeriod;
	}

	public String getApcPaymentMethod() {
		return this.apcPaymentMethod;
	}

	public void setApcPaymentMethod(String apcPaymentMethod) {
		this.apcPaymentMethod = apcPaymentMethod;
	}

	public Date getApcCreateTime() {
		return this.apcCreateTime;
	}

	public void setApcCreateTime(Date apcCreateTime) {
		this.apcCreateTime = apcCreateTime;
	}

	public String getApcCreateUser() {
		return this.apcCreateUser;
	}

	public void setApcCreateUser(String apcCreateUser) {
		this.apcCreateUser = apcCreateUser;
	}

	public Integer getApcStatus() {
		return this.apcStatus;
	}

	public void setApcStatus(Integer apcStatus) {
		this.apcStatus = apcStatus;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof AccountPurchaseContract)
		{
			AccountPurchaseContract apc = (AccountPurchaseContract)arg0;
			if(this.apcId.equals(apc.getApcId()))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return apcId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.apcCode + "," + this.apcCustomerName;
	}

}