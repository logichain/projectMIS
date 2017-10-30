package org.king.accountmanage.bean;

import java.util.Date;

/**
 * AccountFeeTax entity. @author MyEclipse Persistence Tools
 */

public class AccountFeeTax extends org.king.framework.domain.BaseObject implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer aftId;
	private Integer aftProject;
	private String aftCode;
	private double aftSaleFee;
	private double aftFinanceFee;
	private double aftImplementFee;
	private double aftSalaryFee;
	private double aftSaleTax;
	private double aftPurchaseTax;
	private double aftPayableTax;
	private double aftBusinessTax;
	private double aftAdditionalTax;
	private String aftRemarks;
	private Integer aftStatus = 0;
	private Date aftCreateTime;
	private String aftCreateUser;

	// Constructors

	/** default constructor */
	public AccountFeeTax() {
	}

	/** full constructor */
	public AccountFeeTax(Integer aftProject, String aftCode, double aftSaleFee, double aftFinanceFee, double aftImplementFee, double aftSalaryFee, double aftSaleTax, double aftPurchaseTax,
			double aftPayableTax, double aftBusinessTax, double aftAdditionalTax, String aftRemarks, Integer aftStatus, Date aftCreateTime, String aftCreateUser) {
		this.aftProject = aftProject;
		this.aftCode = aftCode;
		this.aftSaleFee = aftSaleFee;
		this.aftFinanceFee = aftFinanceFee;
		this.aftImplementFee = aftImplementFee;
		this.aftSalaryFee = aftSalaryFee;
		this.aftSaleTax = aftSaleTax;
		this.aftPurchaseTax = aftPurchaseTax;
		this.aftPayableTax = aftPayableTax;
		this.aftBusinessTax = aftBusinessTax;
		this.aftAdditionalTax = aftAdditionalTax;
		this.aftRemarks = aftRemarks;
		this.aftStatus = aftStatus;
		this.aftCreateTime = aftCreateTime;
		this.aftCreateUser = aftCreateUser;
	}

	// Property accessors

	public Integer getAftId() {
		return this.aftId;
	}

	public void setAftId(Integer aftId) {
		this.aftId = aftId;
	}

	public Integer getAftProject() {
		return this.aftProject;
	}

	public void setAftProject(Integer aftProject) {
		this.aftProject = aftProject;
	}

	public String getAftCode() {
		return this.aftCode;
	}

	public void setAftCode(String aftCode) {
		this.aftCode = aftCode;
	}

	public double getAftSaleFee() {
		return this.aftSaleFee;
	}

	public void setAftSaleFee(double aftSaleFee) {
		this.aftSaleFee = aftSaleFee;
	}

	public double getAftFinanceFee() {
		return this.aftFinanceFee;
	}

	public void setAftFinanceFee(double aftFinanceFee) {
		this.aftFinanceFee = aftFinanceFee;
	}

	public double getAftImplementFee() {
		return this.aftImplementFee;
	}

	public void setAftImplementFee(double aftImplementFee) {
		this.aftImplementFee = aftImplementFee;
	}

	public double getAftSalaryFee() {
		return this.aftSalaryFee;
	}

	public void setAftSalaryFee(double aftSalaryFee) {
		this.aftSalaryFee = aftSalaryFee;
	}

	public double getAftSaleTax() {
		return this.aftSaleTax;
	}

	public void setAftSaleTax(double aftSaleTax) {
		this.aftSaleTax = aftSaleTax;
	}

	public double getAftPurchaseTax() {
		return this.aftPurchaseTax;
	}

	public void setAftPurchaseTax(double aftPurchaseTax) {
		this.aftPurchaseTax = aftPurchaseTax;
	}

	public double getAftPayableTax() {
//		return this.aftPayableTax;
		double rtn = this.aftSaleTax - this.aftPurchaseTax;
		
		rtn = ((int)(rtn *10000))/10000.0d;
		
		return rtn;
	}

	public void setAftPayableTax(double aftPayableTax) {
		this.aftPayableTax = aftPayableTax;
	}

	public double getAftBusinessTax() {
		return this.aftBusinessTax;
	}

	public void setAftBusinessTax(double aftBusinessTax) {
		this.aftBusinessTax = aftBusinessTax;
	}

	public double getAftAdditionalTax() {
		return this.aftAdditionalTax;
	}

	public void setAftAdditionalTax(double aftAdditionalTax) {
		this.aftAdditionalTax = aftAdditionalTax;
	}

	public String getAftRemarks() {
		return this.aftRemarks;
	}

	public void setAftRemarks(String aftRemarks) {
		this.aftRemarks = aftRemarks;
	}

	public Integer getAftStatus() {
		return this.aftStatus;
	}

	public void setAftStatus(Integer aftStatus) {
		this.aftStatus = aftStatus;
	}

	public Date getAftCreateTime() {
		return this.aftCreateTime;
	}

	public void setAftCreateTime(Date aftCreateTime) {
		this.aftCreateTime = aftCreateTime;
	}

	public String getAftCreateUser() {
		return this.aftCreateUser;
	}

	public void setAftCreateUser(String aftCreateUser) {
		this.aftCreateUser = aftCreateUser;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 != null && arg0 instanceof AccountFeeTax)
		{
			AccountFeeTax aft = (AccountFeeTax)arg0;
			if(aftId.equals(aft.getAftId()))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return aftId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.aftId + "," + this.aftCode;
	}
	
	public double getManageFee()
	{
		return this.aftImplementFee + this.aftSalaryFee;
	}

}