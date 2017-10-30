package org.king.projectmanage.bean;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.king.accountmanage.bean.AccountFeeTax;
import org.king.accountmanage.bean.AccountPurchaseContract;
import org.king.accountmanage.bean.AccountSaleContract;
import org.king.common.projectstatus.ProjectStatus;
import org.king.customer.bean.Customer;
import org.king.department.bean.Department;
import org.king.security.domain.Account;
import org.king.tender.bean.DeviceList1st;
import org.king.tender.bean.ProjectBudget;
import org.king.tender.bean.SettlementAmount;
import org.king.tender.bean.ShortfallAmount;

import com.ibm.icu.text.NumberFormat;

/**
 * TenderProject entity. @author MyEclipse Persistence Tools
 */

public class TenderProject extends org.king.common.SearchBaseObject implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer tpId;
	private String tpName;
	private Integer tpCustomer;
	private Integer tpDept;
	private String tpManager;
	private String tpAddress;
	private String tpDescription;
	private String tpBeginDate;
	private String tpEndDate;
	private Integer tpWorkdayCount;
	private BigDecimal tpContractAmount = BigDecimal.ZERO;

	private String tpMarketManager;
	private Integer tpStatus;
	private String tpCreateUser;
	private Date tpCreateTime;
	private Integer tpTenderdocCheckStatus = ProjectStatus.TENDERDOC_CHECK_STATUS_INIT;

	private ArrayList<ProjectTeam> teamMemberList = new ArrayList<ProjectTeam>();
	private ArrayList<ProjectScheduleStage> scheduleStageList = new ArrayList<ProjectScheduleStage>();
	private ArrayList<ProjectAttachment> attachmentList = new ArrayList<ProjectAttachment>();
	private ArrayList<TenderAttachment> tenderAttachmentList = new ArrayList<TenderAttachment>();
	
	private Customer customer;
	private Department department;
	private Account createUser;
	private Account manager;
	private Account oldManager;
	private ProjectStatus status;
	private Account marketManager;
	
	private ProjectAttachment currentAttachment;
	private TenderAttachment currentTenderAttachment;
	private ProjectTeam currentProjectTeam;
	private ProjectScheduleStage currentScheduleStage;

	private float schedulePlanPercent = 0.0f;
	private float schedulePercent = 0.0f;
	private int doDayCount = 0;
	private int remainDayCount = 0;
	private int beyondDayCount = 0;
	private float remainPercent = 0.0f;

	private ProjectBudget tenderBudget;
	private ProjectBudget applyBudget;
	private ArrayList<ProjectBudget> monthBudgetList = new ArrayList<ProjectBudget>();
	private ArrayList<ProjectBudget> monthSettlementList = new ArrayList<ProjectBudget>();
	private ProjectBudget currentMonthBudget;
	private ProjectBudget currentMonthSettlement;
	private SettlementAmount settlementAmount;
	private ShortfallAmount shortfallAmount;
	private String currentMonth = "";

	private List<DeviceList1st> deviceList = new ArrayList<DeviceList1st>();

	private ProjectContract projectContract;
	private ArrayList<ProjectContract> projectContractList = new ArrayList<ProjectContract>();
	private ProjectContract tenderContract;
	private ArrayList<ProjectContract> tenderContractList = new ArrayList<ProjectContract>();
	
	private AccountSaleContract accountSaleContract;
	private ArrayList<AccountSaleContract> accountSaleContractList = new ArrayList<AccountSaleContract>();
	
	private AccountPurchaseContract accountPurchaseContract;
	private ArrayList<AccountPurchaseContract> accountPurchaseContractList = new ArrayList<AccountPurchaseContract>();
	
	private AccountFeeTax accountFeeTax;
	private ArrayList<AccountFeeTax> accountFeeTaxList = new ArrayList<AccountFeeTax>();

	// Constructors

	/** default constructor */
	public TenderProject() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		this.currentMonth = sdf.format(new Date());
	}

	/** minimal constructor */
	public TenderProject(String tpName, Integer tpStatus, String tpCreateUser, Date tpCreateTime) {
		this.tpName = tpName;
		this.tpStatus = tpStatus;
		this.tpCreateUser = tpCreateUser;
		this.tpCreateTime = tpCreateTime;
	}

	// Property accessors

	public Integer getTpId() {
		return this.tpId;
	}

	public void setTpId(Integer tpId) {
		this.tpId = tpId;
	}

	public String getTpName() {
		return this.tpName;
	}

	public void setTpName(String tpName) {
		this.tpName = tpName;
	}



	public Integer getTpCustomer() {
		return this.tpCustomer;
	}

	public void setTpCustomer(Integer tpCustomer) {
		this.tpCustomer = tpCustomer;
	}


	public String getTpManager() {
		if (tpManager != null && tpManager.equals("")) {
			return null;
		}
		return this.tpManager;
	}

	public void setTpManager(String tpManager) {
		this.tpManager = tpManager;
	}

	public String getTpAddress() {
		return this.tpAddress;
	}

	public void setTpAddress(String tpAddress) {
		this.tpAddress = tpAddress;
	}

	public String getTpDescription() {
		return this.tpDescription;
	}

	public void setTpDescription(String tpDescription) {
		this.tpDescription = tpDescription;
	}
	public Integer getTpWorkdayCount() {
		return this.tpWorkdayCount;
	}

	public void setTpWorkdayCount(Integer tpWorkdayCount) {
		this.tpWorkdayCount = tpWorkdayCount;
	}

	public BigDecimal getTpContractAmount() {
		return this.tpContractAmount;
	}

	public void setTpContractAmount(BigDecimal tpContractAmount) {
		this.tpContractAmount = tpContractAmount;
	}

	public Integer getTpStatus() {
		return this.tpStatus;
	}

	public void setTpStatus(Integer tpStatus) {
		this.tpStatus = tpStatus;
	}

	public String getTpCreateUser() {
		return this.tpCreateUser;
	}

	public void setTpCreateUser(String tpCreateUser) {
		this.tpCreateUser = tpCreateUser;
	}

	public Date getTpCreateTime() {
		return this.tpCreateTime;
	}

	public void setTpCreateTime(Date tpCreateTime) {
		this.tpCreateTime = tpCreateTime;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if (arg0 != null && arg0 instanceof TenderProject) {
			TenderProject tp = (TenderProject) arg0;
			if (tp.getTpId().equals(tpId)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.tpId.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.tpId + this.tpName;
	}

	public void setScheduleStageList(ArrayList<ProjectScheduleStage> scheduleStageList) {
		this.scheduleStageList = scheduleStageList;
	}

	public ArrayList<ProjectScheduleStage> getScheduleStageList() {
		return scheduleStageList;
	}

	public void setTeamMemberList(ArrayList<ProjectTeam> teamMemberList) {
		this.teamMemberList = teamMemberList;
	}

	public ArrayList<ProjectTeam> getTeamMemberList() {
		return teamMemberList;
	}

	public Set<ProjectScheduleStage> getScheduleStageSet() {
		// return scheduleStageSet;
		Set<ProjectScheduleStage> teamMemberSet = new HashSet<ProjectScheduleStage>();
		teamMemberSet.addAll(scheduleStageList);

		return teamMemberSet;
	}

	public void setScheduleStageSet(Set<ProjectScheduleStage> scheduleStageSet) {
		scheduleStageList.clear();
		scheduleStageList.addAll(scheduleStageSet);
	}

	public Set<ProjectTeam> getTeamMemberSet() {
		// return teamMemberSet;
		Set<ProjectTeam> teamMemberSet = new HashSet<ProjectTeam>();
		teamMemberSet.addAll(teamMemberList);

		return teamMemberSet;
	}

	public void setTeamMemberSet(Set<ProjectTeam> teamMemberSet) {
		teamMemberList.clear();
		teamMemberList.addAll(teamMemberSet);
	}

	public void setAttachmentList(ArrayList<ProjectAttachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public ArrayList<ProjectAttachment> getAttachmentList() {
		// ArrayList<ProjectAttachment> rtn = new
		// ArrayList<ProjectAttachment>();
		//		
		// if(this.tpStatus.equals(ProjectStatus.PROJECT_STATUS_TENDER))
		// {
		// for(ProjectAttachment pa:attachmentList)
		// {
		// if(pa.getPaAttachmentCategory().equals(AttachmentCategory.
		// TENDER_ATTACHMENT))
		// {
		// rtn.add(pa);
		// }
		// }
		// }
		// else
		// {
		// for(ProjectAttachment pa:attachmentList)
		// {
		// if(!pa.getPaAttachmentCategory().equals(AttachmentCategory.
		// TENDER_ATTACHMENT))
		// {
		// rtn.add(pa);
		// }
		// }
		// }
		//		
		// return rtn;

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

	public void setTenderAttachmentList(ArrayList<TenderAttachment> attachmentList) {
		this.tenderAttachmentList = attachmentList;
	}

	public ArrayList<TenderAttachment> getTenderAttachmentList() {
		return tenderAttachmentList;
	}

	public Set<TenderAttachment> getTenderAttachmentSet() {
		// return attachmentSet;
		Set<TenderAttachment> detailSet = new HashSet<TenderAttachment>();
		detailSet.addAll(tenderAttachmentList);

		return detailSet;
	}

	public void setTenderAttachmentSet(Set<TenderAttachment> attachmentSet) {
		tenderAttachmentList.clear();
		tenderAttachmentList.addAll(attachmentSet);
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setManager(Account manager) {
		this.manager = manager;
	}

	public Account getManager() {
		return manager;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setCurrentAttachment(ProjectAttachment currentAttachment) {
		this.currentAttachment = currentAttachment;
	}

	public ProjectAttachment getCurrentAttachment() {
		return currentAttachment;
	}

	public void setTpBeginDate(String tpBeginDate) {
		this.tpBeginDate = tpBeginDate;
	}

	public String getTpBeginDate() {
		return tpBeginDate;
	}

	public void setTpEndDate(String tpEndDate) {
		this.tpEndDate = tpEndDate;
	}

	public String getTpEndDate() {
		return tpEndDate;
	}

	public void setSchedulePlanPercent(float schedulePlanPercent) {
		this.schedulePlanPercent = schedulePlanPercent;
	}

	public float getSchedulePlanPercent() {
		return schedulePlanPercent * 100;
	}

	public String getSchedulePlanPercentStr() {
		return NumberFormat.getPercentInstance().format(schedulePlanPercent);
	}

	public void setSchedulePercent(float schedulePercent) {
		this.schedulePercent = schedulePercent;
	}

	public float getSchedulePercent() {
		return schedulePercent * 100;
	}

	public String getSchedulePercentStr() {
		return NumberFormat.getPercentInstance().format(schedulePercent);
	}

	public void setDoDayCount(int doDayCount) {
		this.doDayCount = doDayCount;
	}

	public int getDoDayCount() {
		return doDayCount;
	}

	public void setBeyondDayCount(int beyondDayCount) {
		this.beyondDayCount = beyondDayCount;
	}

	public int getBeyondDayCount() {
		return beyondDayCount;
	}

	public void setRemainPercent(float remainPercent) {
		this.remainPercent = remainPercent;
	}

	public float getRemainPercent() {
		return (1 - schedulePercent) * 100;
	}

	public String getRemainPercentStr() {
		return NumberFormat.getPercentInstance().format(1 - schedulePercent);
	}

	public void setRemainDayCount(int remainDayCount) {
		this.remainDayCount = remainDayCount;
	}

	public int getRemainDayCount() {
		return remainDayCount;
	}

	public void setCreateUser(Account createUser) {
		this.createUser = createUser;
	}

	public Account getCreateUser() {
		return createUser;
	}

	public void setCurrentProjectTeam(ProjectTeam currentProjectTeam) {
		this.currentProjectTeam = currentProjectTeam;
	}

	public ProjectTeam getCurrentProjectTeam() {
		return currentProjectTeam;
	}

	public void setCurrentScheduleStage(ProjectScheduleStage currentScheduleStage) {
		this.currentScheduleStage = currentScheduleStage;
	}

	public ProjectScheduleStage getCurrentScheduleStage() {
		return currentScheduleStage;
	}

	public List<DeviceList1st> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<DeviceList1st> deviceList) {
		this.deviceList = deviceList;
	}

	public void addDeviceList1st(DeviceList1st device) {
		if (deviceList == null) {
			deviceList = new ArrayList<DeviceList1st>();
		}
		deviceList.add(device);
	}

	public Set<DeviceList1st> getDeviceListSet() {
		Set<DeviceList1st> set = new HashSet<DeviceList1st>();
		set.addAll(deviceList);

		return set;
	}

	public void setDeviceListSet(Set<DeviceList1st> set) {
		deviceList.clear();
		deviceList.addAll(set);
	}
	
	public void setProjectBudgetSet(Set<ProjectBudget> projectBudgetSet) {
		monthBudgetList.clear();
		monthSettlementList.clear();

		for (ProjectBudget pb : projectBudgetSet) {
			if (pb == null) {
				continue;
			}
			if (pb.getPbType().equals(ProjectBudget.BUDGET_TYPE_TENDER)) {
				this.tenderBudget = pb;
			} else if (pb.getPbType().equals(ProjectBudget.BUDGET_TYPE_MONTH_BUDGET)) {
				monthBudgetList.add(pb);
			} else if (pb.getPbType().equals(ProjectBudget.BUDGET_TYPE_MONTH_SETTLEMENT)) {
				monthSettlementList.add(pb);
			} else if (pb.getPbType().equals(ProjectBudget.BUDGET_TYPE_APPLY)) {
				applyBudget = pb;
			}
		}

		settlementAmount = new SettlementAmount(monthSettlementList);
	}

	public Set<ProjectBudget> getProjectBudgetSet() {
		Set<ProjectBudget> rtn = new HashSet<ProjectBudget>();
		rtn.addAll(monthBudgetList);
		rtn.addAll(monthSettlementList);
		rtn.add(tenderBudget);
		rtn.add(applyBudget);

		return rtn;
	}

	public void setTenderBudget(ProjectBudget tenderBudget) {
		this.tenderBudget = tenderBudget;
	}

	public ProjectBudget getTenderBudget() {
		return tenderBudget;
	}

	public void setMonthBudgetList(ArrayList<ProjectBudget> monthBudgetList) {
		this.monthBudgetList = monthBudgetList;
	}

	public ArrayList<ProjectBudget> getMonthBudgetList() {
		return monthBudgetList;
	}

	public void setMonthSettlementList(ArrayList<ProjectBudget> monthSettlementList) {
		this.monthSettlementList = monthSettlementList;
	}

	public ArrayList<ProjectBudget> getMonthSettlementList() {
		return monthSettlementList;
	}

	public void setCurrentMonthBudget(ProjectBudget currentMonthBudget) {
		this.currentMonthBudget = currentMonthBudget;
	}

	public ProjectBudget getCurrentMonthBudget() {
		return currentMonthBudget;
	}

	public void setCurrentMonthSettlement(ProjectBudget currentMonthSettlement) {
		this.currentMonthSettlement = currentMonthSettlement;
	}

	public ProjectBudget getCurrentMonthSettlement() {
		return currentMonthSettlement;
	}

	public void setSettlementAmount(SettlementAmount settlementAmount) {
		this.settlementAmount = settlementAmount;
	}

	public SettlementAmount getSettlementAmount() {
		return settlementAmount;
	}

	public void setShortfallAmount(ShortfallAmount shortfallAmount) {
		this.shortfallAmount = shortfallAmount;
	}

	public ShortfallAmount getShortfallAmount() {
		return shortfallAmount;
	}

	public void setProjectContract(ProjectContract projectContract) {
		this.projectContract = projectContract;
	}

	public ProjectContract getProjectContract() {
		return projectContract;
	}

	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}

	public String getCurrentMonth() {
		return currentMonth;
	}

	public void setProjectContractList(ArrayList<ProjectContract> projectContractList) {
		this.projectContractList = projectContractList;
	}

	public ArrayList<ProjectContract> getProjectContractList() {
		return projectContractList;
	}

	public void setProjectContractSet(Set<ProjectContract> projectContractSet) {
		this.projectContractList.clear();		
		this.projectContractList.addAll(projectContractSet);			
	}

	public Set<ProjectContract> getProjectContractSet() {
		Set<ProjectContract> rtn = new HashSet<ProjectContract>();
		rtn.addAll(projectContractList);

		return rtn;
	}

	public void setApplyBudget(ProjectBudget applyBudget) {
		this.applyBudget = applyBudget;
	}

	public ProjectBudget getApplyBudget() {
		return applyBudget;
	}

	public void setTenderContract(ProjectContract tenderContract) {
		this.tenderContract = tenderContract;
	}

	public ProjectContract getTenderContract() {
		return tenderContract;
	}

	public void setTenderContractSet(Set<ProjectContract> tenderContractSet) {
		this.tenderContractList.clear();		
		this.tenderContractList.addAll(tenderContractSet);			
	}

	public Set<ProjectContract> getTenderContractSet() {
		Set<ProjectContract> rtn = new HashSet<ProjectContract>();
		rtn.addAll(tenderContractList);

		return rtn;
	}
	public void setTenderContractList(ArrayList<ProjectContract> tenderContractList) {
		this.tenderContractList = tenderContractList;
	}

	public ArrayList<ProjectContract> getTenderContractList() {
		return tenderContractList;
	}

	public void setOldManager(Account oldManager) {
		this.oldManager = oldManager;
	}

	public Account getOldManager() {
		return oldManager;
	}
	public void setAccountSaleContractList(ArrayList<AccountSaleContract> accountSaleContractList) {
		this.accountSaleContractList = accountSaleContractList;
	}

	public ArrayList<AccountSaleContract> getAccountSaleContractList() {
		return accountSaleContractList;
	}
	
	public void setAccountSaleContractSet(Set<AccountSaleContract> accountSaleContractSet) {
		accountSaleContractList.clear();		
		accountSaleContractList.addAll(accountSaleContractSet);
	}

	public Set<AccountSaleContract> getAccountSaleContractSet() {
		Set<AccountSaleContract> rtn = new HashSet<AccountSaleContract>();
		rtn.addAll(accountSaleContractList);
		
		return rtn;
	}

	public void setAccountSaleContract(AccountSaleContract accountSaleContract) {
		this.accountSaleContract = accountSaleContract;
	}

	public AccountSaleContract getAccountSaleContract() {
		return accountSaleContract;
	}

	public void setAccountPurchaseContract(AccountPurchaseContract accountPurchaseContract) {
		this.accountPurchaseContract = accountPurchaseContract;
	}

	public AccountPurchaseContract getAccountPurchaseContract() {
		return accountPurchaseContract;
	}

	public void setAccountPurchaseContractList(ArrayList<AccountPurchaseContract> accountPurchaseContractList) {
		this.accountPurchaseContractList = accountPurchaseContractList;
	}

	public ArrayList<AccountPurchaseContract> getAccountPurchaseContractList() {
		return accountPurchaseContractList;
	}
	public void setAccountPurchaseContractSet(Set<AccountPurchaseContract> accountSaleContractSet) {
		accountPurchaseContractList.clear();		
		accountPurchaseContractList.addAll(accountSaleContractSet);
	}

	public Set<AccountPurchaseContract> getAccountPurchaseContractSet() {
		Set<AccountPurchaseContract> rtn = new HashSet<AccountPurchaseContract>();
		rtn.addAll(accountPurchaseContractList);
		
		return rtn;
	}

	public void setAccountFeeTax(AccountFeeTax accountFeeTax) {
		this.accountFeeTax = accountFeeTax;
	}

	public AccountFeeTax getAccountFeeTax() {
		return accountFeeTax;
	}

	public void setAccountFeeTaxList(ArrayList<AccountFeeTax> accountFeeTaxList) {
		this.accountFeeTaxList = accountFeeTaxList;
	}

	public ArrayList<AccountFeeTax> getAccountFeeTaxList() {
		return accountFeeTaxList;
	}
	
	public void setAccountFeeTaxSet(Set<AccountFeeTax> accountFeeTaxSet) {
		accountFeeTaxList.clear();		
		accountFeeTaxList.addAll(accountFeeTaxSet);
	}

	public Set<AccountFeeTax> getAccountFeeTaxSet() {
		Set<AccountFeeTax> rtn = new HashSet<AccountFeeTax>();
		rtn.addAll(accountFeeTaxList);
		
		return rtn;
	}

	public Integer getTpDept() {
		return tpDept;
	}

	public void setTpDept(Integer tpDept) {
		this.tpDept = tpDept;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Integer getTpTenderdocCheckStatus() {
		return tpTenderdocCheckStatus;
	}

	public void setTpTenderdocCheckStatus(Integer tpTenderdocCheckStatus) {
		this.tpTenderdocCheckStatus = tpTenderdocCheckStatus;
	}

	public TenderAttachment getCurrentTenderAttachment() {
		return currentTenderAttachment;
	}

	public void setCurrentTenderAttachment(TenderAttachment currentTenderAttachment) {
		this.currentTenderAttachment = currentTenderAttachment;
	}

	public Account getMarketManager() {
		return marketManager;
	}

	public void setMarketManager(Account marketManager) {
		this.marketManager = marketManager;
	}

	public String getTpMarketManager() {
		return tpMarketManager;
	}

	public void setTpMarketManager(String tpMarketManager) {
		this.tpMarketManager = tpMarketManager;
	}

}