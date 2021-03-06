package org.king.tender.bean;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;

import org.king.common.deviceunit.DeviceUnit;
import org.king.customer.bean.Customer;
import org.king.security.domain.Account;

/**
 * DeviceListDetail generated by MyEclipse - Hibernate Tools
 */

public class DeviceListDetail extends org.king.framework.domain.BaseObject
		implements java.io.Serializable {

	// Fields    

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer dldId;
	
	private Integer dldFirstEquipmentId;

	private Integer dldSecondEquipmentId;

	private String dldEquipmentName;

	private String dldEquipmentStandard;

	private Integer dldEquipmentCount;

	private Integer dldEquipmentUnit;
	
	private Integer firstSelected = 0;;

	private String dldFirstSelectedSupplier;

	private Integer dldFirstSelectedSupplierId;

	private BigDecimal dldFirstSelectedSupplierPrice = BigDecimal.ZERO;
	
	private Integer secondSelected = 0;

	private String dldSecondSelectedSupplier;

	private Integer dldSecondSelectedSupplierId;

	private BigDecimal dldSecondSelectedSupplierPrice = BigDecimal.ZERO;
	
	private Integer thirdSelected = 0;

	private String dldThirdSelectedSupplier;

	private Integer dldThirdSelectedSupplierId;

	private BigDecimal dldThirdSelectedSupplierPrice = BigDecimal.ZERO;

	private Integer dldStatus;

	private String dldCreateUser;

	private Date dldCreateTime;

	private String dldUpdateUser;

	private Date dldUpdateTime;
	
	private String dldBeginDate;
	private String dldEndDate;
	private String dldResponsiblePerson;
	private String dldComment;
	
	private Account responsiblePerson;
	
	private Customer firstSelectedSupplier;
	private Customer secondSelectedSupplier;
	private Customer thirdSelectedSupplier;
	
	private DeviceUnit deviceUnit;
	private double deviceAmount = 0d;

	// Constructors

	/** default constructor */
	public DeviceListDetail() {
	}

	/** minimal constructor */
	public DeviceListDetail(Integer dldStatus) {
		this.dldStatus = dldStatus;
	}

	/** full constructor */
	public DeviceListDetail(Integer dldSecondEquipmentId,
			String dldEquipmentName, String dldEquipmentStandard,
			Integer dldEquipmentCount, Integer dldEquipmentUnit,
			String dldFirstSelectedSupplier,
			Integer dldFirstSelectedSupplierId,
			BigDecimal dldFirstSelectedSupplierPrice,
			String dldSecondSelectedSupplier,
			Integer dldSecondSelectedSupplierId,
			BigDecimal dldSecondSelectedSupplierPrice,
			String dldThirdSelectedSupplier,
			Integer dldThirdSelectedSupplierId,
			BigDecimal dldThirdSelectedSupplierPrice, Integer dldStatus,
			String dldCreateUser, Date dldCreateTime, String dldUpdateUser,
			Date dldUpdateTime) {
		this.dldSecondEquipmentId = dldSecondEquipmentId;
		this.dldEquipmentName = dldEquipmentName;
		this.dldEquipmentStandard = dldEquipmentStandard;
		this.dldEquipmentCount = dldEquipmentCount;
		this.dldEquipmentUnit = dldEquipmentUnit;
		this.dldFirstSelectedSupplier = dldFirstSelectedSupplier;
		this.dldFirstSelectedSupplierId = dldFirstSelectedSupplierId;
		this.dldFirstSelectedSupplierPrice = dldFirstSelectedSupplierPrice;
		this.dldSecondSelectedSupplier = dldSecondSelectedSupplier;
		this.dldSecondSelectedSupplierId = dldSecondSelectedSupplierId;
		this.dldSecondSelectedSupplierPrice = dldSecondSelectedSupplierPrice;
		this.dldThirdSelectedSupplier = dldThirdSelectedSupplier;
		this.dldThirdSelectedSupplierId = dldThirdSelectedSupplierId;
		this.dldThirdSelectedSupplierPrice = dldThirdSelectedSupplierPrice;
		this.dldStatus = dldStatus;
		this.dldCreateUser = dldCreateUser;
		this.dldCreateTime = dldCreateTime;
		this.dldUpdateUser = dldUpdateUser;
		this.dldUpdateTime = dldUpdateTime;
	}

	// Property accessors

	public Integer getDldId() {
		return this.dldId;
	}

	public void setDldId(Integer dldId) {
		this.dldId = dldId;
	}

	public Integer getDldSecondEquipmentId() {
		return this.dldSecondEquipmentId;
	}

	public void setDldSecondEquipmentId(Integer dldSecondEquipmentId) {
		this.dldSecondEquipmentId = dldSecondEquipmentId;
	}

	public String getDldEquipmentName() {
		return this.dldEquipmentName;
	}

	public void setDldEquipmentName(String dldEquipmentName) {
		this.dldEquipmentName = dldEquipmentName;
	}

	public String getDldEquipmentStandard() {
		return this.dldEquipmentStandard;
	}

	public void setDldEquipmentStandard(String dldEquipmentStandard) {
		this.dldEquipmentStandard = dldEquipmentStandard;
	}

	public Integer getDldEquipmentCount() {
		return this.dldEquipmentCount;
	}

	public void setDldEquipmentCount(Integer dldEquipmentCount) {
		this.dldEquipmentCount = dldEquipmentCount;
	}

	public Integer getDldEquipmentUnit() {
		if(dldEquipmentUnit != null && dldEquipmentUnit == 0)
		{
			return null;
		}
		return this.dldEquipmentUnit;
	}

	public void setDldEquipmentUnit(Integer dldEquipmentUnit) {
		this.dldEquipmentUnit = dldEquipmentUnit;
	}

	public String getDldFirstSelectedSupplier() {
		return this.dldFirstSelectedSupplier;
	}

	public void setDldFirstSelectedSupplier(String dldFirstSelectedSupplier) {
		this.dldFirstSelectedSupplier = dldFirstSelectedSupplier;
	}

	public Integer getDldFirstSelectedSupplierId() {
		return this.dldFirstSelectedSupplierId;
	}

	public void setDldFirstSelectedSupplierId(Integer dldFirstSelectedSupplierId) {
		this.dldFirstSelectedSupplierId = dldFirstSelectedSupplierId;
	}

	public BigDecimal getDldFirstSelectedSupplierPrice() {
		return this.dldFirstSelectedSupplierPrice;
	}

	public void setDldFirstSelectedSupplierPrice(BigDecimal dldFirstSelectedSupplierPrice) {
		this.dldFirstSelectedSupplierPrice = dldFirstSelectedSupplierPrice;
	}
	
	public String getDldFirstSelectedSupplierPriceStr() {
		return NumberFormat.getInstance().format(this.dldFirstSelectedSupplierPrice);
	}

	public void setDldFirstSelectedSupplierPriceStr(String dldFirstSelectedSupplierPrice) {
		try {
			this.dldFirstSelectedSupplierPrice = new BigDecimal(NumberFormat.getInstance().parse(dldFirstSelectedSupplierPrice).doubleValue());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getDldSecondSelectedSupplier() {
		return this.dldSecondSelectedSupplier;
	}

	public void setDldSecondSelectedSupplier(String dldSecondSelectedSupplier) {
		this.dldSecondSelectedSupplier = dldSecondSelectedSupplier;
	}

	public Integer getDldSecondSelectedSupplierId() {
		return this.dldSecondSelectedSupplierId;
	}

	public void setDldSecondSelectedSupplierId(Integer dldSecondSelectedSupplierId) {
		this.dldSecondSelectedSupplierId = dldSecondSelectedSupplierId;
	}

	public BigDecimal getDldSecondSelectedSupplierPrice() {
		return this.dldSecondSelectedSupplierPrice;
	}

	public void setDldSecondSelectedSupplierPrice(BigDecimal dldSecondSelectedSupplierPrice) {
		this.dldSecondSelectedSupplierPrice = dldSecondSelectedSupplierPrice;
	}
	
	public String getDldSecondSelectedSupplierPriceStr() {
		return NumberFormat.getInstance().format(this.dldSecondSelectedSupplierPrice);
	}

	public void setDldSecondSelectedSupplierPriceStr(String dldSecondSelectedSupplierPrice) {
		try {
			this.dldSecondSelectedSupplierPrice = new BigDecimal(NumberFormat.getInstance().parse(dldSecondSelectedSupplierPrice).doubleValue());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getDldThirdSelectedSupplier() {
		return this.dldThirdSelectedSupplier;
	}

	public void setDldThirdSelectedSupplier(String dldThirdSelectedSupplier) {
		this.dldThirdSelectedSupplier = dldThirdSelectedSupplier;
	}

	public Integer getDldThirdSelectedSupplierId() {
		return this.dldThirdSelectedSupplierId;
	}

	public void setDldThirdSelectedSupplierId(Integer dldThirdSelectedSupplierId) {
		this.dldThirdSelectedSupplierId = dldThirdSelectedSupplierId;
	}

	public BigDecimal getDldThirdSelectedSupplierPrice() {
		return this.dldThirdSelectedSupplierPrice;
	}

	public void setDldThirdSelectedSupplierPrice(BigDecimal dldThirdSelectedSupplierPrice) {
		this.dldThirdSelectedSupplierPrice = dldThirdSelectedSupplierPrice;
	}
	
	public String getDldThirdSelectedSupplierPriceStr() {
		return NumberFormat.getInstance().format(this.dldThirdSelectedSupplierPrice);
	}

	public void setDldThirdSelectedSupplierPriceStr(String dldThirdSelectedSupplierPrice) {
		try {
			this.dldThirdSelectedSupplierPrice = new BigDecimal(NumberFormat.getInstance().parse(dldThirdSelectedSupplierPrice).doubleValue());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Integer getDldStatus() {
		return this.dldStatus;
	}

	public void setDldStatus(Integer dldStatus) {
		this.dldStatus = dldStatus;
	}

	public String getDldCreateUser() {
		return this.dldCreateUser;
	}

	public void setDldCreateUser(String dldCreateUser) {
		this.dldCreateUser = dldCreateUser;
	}

	public Date getDldCreateTime() {
		return this.dldCreateTime;
	}

	public void setDldCreateTime(Date dldCreateTime) {
		this.dldCreateTime = dldCreateTime;
	}

	public String getDldUpdateUser() {
		return this.dldUpdateUser;
	}

	public void setDldUpdateUser(String dldUpdateUser) {
		this.dldUpdateUser = dldUpdateUser;
	}

	public Date getDldUpdateTime() {
		return this.dldUpdateTime;
	}

	public void setDldUpdateTime(Date dldUpdateTime) {
		this.dldUpdateTime = dldUpdateTime;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 != null && arg0 instanceof DeviceListDetail) {
			DeviceListDetail tp = (DeviceListDetail) arg0;
			if (tp.getDldId().equals(dldId)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return dldId.hashCode();
	}

	@Override
	public String toString() {
		return dldId + " " + dldEquipmentName;
	}

	public Integer getFirstSelected() {
		return firstSelected;
	}

	public void setFirstSelected(Integer firstSelected) {
		this.firstSelected = firstSelected;
	}

	public Integer getSecondSelected() {
		return secondSelected;
	}

	public void setSecondSelected(Integer secondSelected) {
		this.secondSelected = secondSelected;
	}

	public Integer getThirdSelected() {
		return thirdSelected;
	}

	public void setThirdSelected(Integer thirdSelected) {
		this.thirdSelected = thirdSelected;
	}

	public Integer getDldFirstEquipmentId() {
		return dldFirstEquipmentId;
	}

	public void setDldFirstEquipmentId(Integer dldFirstEquipmentId) {
		this.dldFirstEquipmentId = dldFirstEquipmentId;
	}

	public String getDldBeginDate() {
		return dldBeginDate;
	}

	public void setDldBeginDate(String dldBeginDate) {
		this.dldBeginDate = dldBeginDate;
	}

	public String getDldComment() {
		return dldComment;
	}

	public void setDldComment(String dldComment) {
		this.dldComment = dldComment;
	}

	public String getDldEndDate() {
		return dldEndDate;
	}

	public void setDldEndDate(String dldEndDate) {
		this.dldEndDate = dldEndDate;
	}

	public String getDldResponsiblePerson() {
		return dldResponsiblePerson;
	}

	public void setDldResponsiblePerson(String dldResponsiblePerson) {
		this.dldResponsiblePerson = dldResponsiblePerson;
	}


	public Integer getQuotedPriceFlag() {
		if(dldFirstSelectedSupplierId != null && dldFirstSelectedSupplierId != 0 && dldFirstSelectedSupplierPrice.doubleValue() > 0)			
		{
			return 1;
		}
		else if(dldSecondSelectedSupplierId != null && dldSecondSelectedSupplierId != 0 && dldSecondSelectedSupplierPrice.doubleValue() > 0)
		{
			return 1;
		}
		else if(dldThirdSelectedSupplierId != null && dldThirdSelectedSupplierId != 0 && dldThirdSelectedSupplierPrice.doubleValue() > 0)
		{
			return 1;
		}
				
		return 0;
	}

	public void setResponsiblePerson(Account responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	public Account getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setFirstSelectedSupplier(Customer firstSelectedSupplier) {
		this.firstSelectedSupplier = firstSelectedSupplier;
	}

	public Customer getFirstSelectedSupplier() {
		return firstSelectedSupplier;
	}

	public void setSecondSelectedSupplier(Customer secondSelectedSupplier) {
		this.secondSelectedSupplier = secondSelectedSupplier;
	}

	public Customer getSecondSelectedSupplier() {
		return secondSelectedSupplier;
	}

	public void setThirdSelectedSupplier(Customer thirdSelectedSupplier) {
		this.thirdSelectedSupplier = thirdSelectedSupplier;
	}

	public Customer getThirdSelectedSupplier() {
		return thirdSelectedSupplier;
	}

	public void setDeviceUnit(DeviceUnit deviceUnit) {
		this.deviceUnit = deviceUnit;
	}

	public DeviceUnit getDeviceUnit() {
		return deviceUnit;
	}

	public void setDeviceAmount(double deviceAmount) {
		this.deviceAmount = deviceAmount;
	}

	public String getDeviceAmount() {
		if(this.firstSelected == 1)
		{
			deviceAmount = this.dldEquipmentCount * this.dldFirstSelectedSupplierPrice.doubleValue();
		}
		else if(this.secondSelected == 1)
		{
			deviceAmount = this.dldEquipmentCount * this.dldSecondSelectedSupplierPrice.doubleValue();
		}
		else if(this.thirdSelected == 1)
		{
			deviceAmount = this.dldEquipmentCount * this.dldThirdSelectedSupplierPrice.doubleValue();
		}
					
		return NumberFormat.getInstance().format(deviceAmount);
	}
	
	public Integer getSupplierCount()
	{
		return firstSelected + secondSelected + thirdSelected;
	}

}