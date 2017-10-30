package org.king.common.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.king.common.attachmentcategory.AttachmentCategory;
import org.king.common.attachmentcategory.AttachmentCategoryDAO;
import org.king.common.checktask.CheckTaskDAO;
import org.king.common.customernature.CustomerNature;
import org.king.common.customernature.CustomerNatureDAO;
import org.king.common.customertype.CustomerType;
import org.king.common.customertype.CustomerTypeDAO;
import org.king.common.deviceunit.DeviceUnit;
import org.king.common.deviceunit.DeviceUnitDAO;
import org.king.common.education.EducationBackground;
import org.king.common.education.EducationBackgroundDAO;
import org.king.common.jobresponsibility.JobResponsibility;
import org.king.common.jobresponsibility.JobResponsibilityDAO;
import org.king.common.noticetype.NoticeType;
import org.king.common.noticetype.NoticeTypeDAO;
import org.king.common.paymenttype.PaymentType;
import org.king.common.paymenttype.PaymentTypeDAO;
import org.king.common.personstatus.PersonStatus;
import org.king.common.personstatus.PersonStatusDAO;
import org.king.common.politicalstatus.PoliticalStatus;
import org.king.common.politicalstatus.PoliticalStatusDAO;
import org.king.common.post.Post;
import org.king.common.post.PostDAO;
import org.king.common.projectrole.ProjectRole;
import org.king.common.projectrole.ProjectRoleDAO;
import org.king.common.projectstatus.ProjectStatus;
import org.king.common.projectstatus.ProjectStatusDAO;
import org.king.common.service.CommonService;
import org.king.common.tenderrole.TenderRole;
import org.king.common.tenderrole.TenderRoleDAO;
import org.king.common.tradetype.TradeType;
import org.king.common.tradetype.TradeTypeDAO;
import org.king.customer.bean.Customer;
import org.king.customer.dao.CustomerDAO;
import org.king.department.bean.Department;
import org.king.department.dao.DepartmentDAO;
import org.king.framework.dao.MyQuery;
import org.king.framework.service.impl.BaseService;
import org.king.projectmanage.bean.ProjectAttachment;
import org.king.security.dao.AccountDAO;
import org.king.security.domain.Account;

public class CommonServiceImpl extends BaseService implements CommonService {
	private JobResponsibilityDAO jobResponsibilityDAO;
	private ProjectRoleDAO projectRoleDAO;
	private TenderRoleDAO tenderRoleDAO;
	private ProjectStatusDAO projectStatusDAO;
	private AttachmentCategoryDAO attachmentCategoryDAO;
	private CustomerTypeDAO customerTypeDAO;
	private DepartmentDAO departmentDAO;

	private PaymentTypeDAO paymentTypeDAO;
	private TradeTypeDAO tradeTypeDAO;
	private EducationBackgroundDAO educationBackgroundDAO;
	private PostDAO postDAO;
	
	private AccountDAO accountDAO;
	
	private PersonStatusDAO personStatusDAO;
	private PoliticalStatusDAO politicalStatusDAO;
	private CustomerNatureDAO customerNatureDAO;
	
	private CustomerDAO customerDAO;
	private DeviceUnitDAO deviceUnitDAO;

	private CheckTaskDAO checkTaskDAO;
	private NoticeTypeDAO noticeTypeDAO;


	public List<JobResponsibility> getJobResponsibilityList() {
		// TODO Auto-generated method stub
		String sql = "from JobResponsibility a where a.jrFlag != " + DELETE_FLAG;
		return jobResponsibilityDAO.find(sql);
	}

	public List<ProjectRole> getProjectRoleList() {
		// TODO Auto-generated method stub
		String sql = "from ProjectRole a where a.prFlag != " + DELETE_FLAG;
		return projectRoleDAO.find(sql);
	}
	
	public List<TenderRole> getTenderRoleList() {
		// TODO Auto-generated method stub
		String sql = "from TenderRole a where a.trFlag != " + DELETE_FLAG;
		return tenderRoleDAO.find(sql);
	}

	public List<ProjectStatus> getProjectStatusList() {
		// TODO Auto-generated method stub
		String sql = "from ProjectStatus a where a.psFlag != " + DELETE_FLAG;
		return projectStatusDAO.find(sql);
	}


	public List<AttachmentCategory> getAttachmentCategoryList() {
		// TODO Auto-generated method stub
		String sql = "from AttachmentCategory a where a.acFlag != " + DELETE_FLAG;
		return attachmentCategoryDAO.find(sql);
	}
	
	public Account getAccountById(String id) {
		// TODO Auto-generated method stub
		return this.accountDAO.get(id);
	}
	
	public ArrayList<Account> searchAllAccount(Account accountInfo) {
		// TODO Auto-generated method stub
    	        
    	String hqlStr = "select distinct a from Account a,UsrPost b where a.id=b.usrId  and a.flag != " + CommonService.DELETE_FLAG;
    	
    	if(accountInfo != null && accountInfo.getPerson().getDept() != null && accountInfo.getPerson().getDept() != 0)
    	{
    		hqlStr += " and b.dept = " + accountInfo.getPerson().getDept();
    	}
        	
    	MyQuery myQuery = new MyQuery();
    	
        myQuery.setOrderby(" order by a.name");
        myQuery.setQueryString(hqlStr);
        
		return (ArrayList<Account>) accountDAO.find(myQuery);
	}

	public ArrayList<Account> searchAccount(Object[] args) {
		// TODO Auto-generated method stub
		Account accountInfo = (Account)args[0];
    	String page = (String)args[1];
    	        
    	String hqlStr = "select distinct a from Account a,UsrPost b where a.id = b.usrId and a.flag != " + CommonService.DELETE_FLAG;
      	
    	hqlStr += (StringUtils.isNotEmpty(accountInfo.getPerson().getPersonCode())?" and a.person.personCode like '%" + accountInfo.getPerson().getPersonCode() + "%'" : "");
    	
    	hqlStr += (StringUtils.isNotEmpty(accountInfo.getPerson().getPersonName())?" and a.person.personName like '%" + accountInfo.getPerson().getPersonName() + "%'" : "");
    	
    	if(accountInfo.getPerson().getDept() != null && accountInfo.getPerson().getDept() != 0)
    	{
    		hqlStr += " and b.dept = " + accountInfo.getPerson().getDept();
    	}
    	if(accountInfo.getPerson().getPost() != null && accountInfo.getPerson().getPost() != 0)
    	{
    		hqlStr += " and b.post = " + accountInfo.getPerson().getPost();
    	}
    	
    	MyQuery myQuery = new MyQuery();
    	myQuery.setPageSize(10);
    	
        if (StringUtils.isNumeric(page)) {
        	myQuery.setPageStartNo(Integer.parseInt(page));
        }else {
        	myQuery.setPageStartNo(0);
        }
        myQuery.setOrderby(" order by a.name");
        myQuery.setQueryString(hqlStr);

        myQuery.setOffset(true);
        
		return (ArrayList<Account>) accountDAO.find(myQuery);
	}


	public int searchAccountCount(Object[] args) {
		// TODO Auto-generated method stub
		Account accountInfo = (Account)args[0];
    	    	        
    	String hqlStr = "select count(distinct a) from Account a,UsrPost b where a.id = b.usrId and a.flag != " + CommonService.DELETE_FLAG;
      	
    	hqlStr += (StringUtils.isNotEmpty(accountInfo.getPerson().getPersonCode())?" and a.person.personCode like '%" + accountInfo.getPerson().getPersonCode() + "%'" : "");
    	
    	hqlStr += (StringUtils.isNotEmpty(accountInfo.getPerson().getPersonName())?" and a.person.personName like '%" + accountInfo.getPerson().getPersonName() + "%'" : "");
      	
    	if(accountInfo.getPerson().getDept() != null && accountInfo.getPerson().getDept() != 0)
    	{
    		hqlStr += " and b.dept = " + accountInfo.getPerson().getDept();
    	}
    	if(accountInfo.getPerson().getPost() != null && accountInfo.getPerson().getPost() != 0)
    	{
    		hqlStr += " and b.post = " + accountInfo.getPerson().getPost();
    	}
    	
    	MyQuery myQuery = new MyQuery();
                
        myQuery.setQueryString(hqlStr);       
        
		return accountDAO.getFindCount(myQuery);
	}
	
	public ArrayList<Account> searchTeamMemberAccount(Object[] args,int tpId) {
		// TODO Auto-generated method stub
		Account accountInfo = (Account)args[0];
    	String page = (String)args[1];
    	        
    	String hqlStr = "select a from Account a,ProjectTeam b where a.id = b.ptAccount and b.ptTenderProject = " + tpId + " and b.ptFlag != " + CommonService.DELETE_FLAG;
      	
    	hqlStr += (StringUtils.isNotEmpty(accountInfo.getPerson().getPersonCode())?" and a.person.personCode like '%" + accountInfo.getPerson().getPersonCode() + "%'" : "");
    	
    	hqlStr += (StringUtils.isNotEmpty(accountInfo.getPerson().getPersonName())?" and a.person.personName like '%" + accountInfo.getPerson().getPersonName() + "%'" : "");
    	
    	MyQuery myQuery = new MyQuery();
    	myQuery.setPageSize(10);
    	
        if (StringUtils.isNumeric(page)) {
        	myQuery.setPageStartNo(Integer.parseInt(page));
        }else {
        	myQuery.setPageStartNo(0);
        }
        myQuery.setOrderby(" order by a.name");
        myQuery.setQueryString(hqlStr);

        myQuery.setOffset(true);
        
		return (ArrayList<Account>) accountDAO.find(myQuery);
	}


	public int searchTeamMemberAccountCount(Object[] args,int tpId) {
		// TODO Auto-generated method stub
		Account accountInfo = (Account)args[0];
    	    	        
    	String hqlStr = "select count(*) from Account a,ProjectTeam b where a.id = b.ptAccount and b.ptTenderProject = " + tpId + " and b.ptFlag != " + CommonService.DELETE_FLAG;
      	
    	hqlStr += (StringUtils.isNotEmpty(accountInfo.getPerson().getPersonCode())?" and a.person.personCode like '%" + accountInfo.getPerson().getPersonCode() + "%'" : "");
    	
    	hqlStr += (StringUtils.isNotEmpty(accountInfo.getPerson().getPersonName())?" and a.person.personName like '%" + accountInfo.getPerson().getPersonName() + "%'" : "");
    	    	
    	MyQuery myQuery = new MyQuery();
                
        myQuery.setQueryString(hqlStr);       
        
		return accountDAO.getFindCount(myQuery);
	}
		
	public void setJobResponsibilityDAO(JobResponsibilityDAO jobResponsibilityDAO) {
		this.jobResponsibilityDAO = jobResponsibilityDAO;
	}

	public JobResponsibilityDAO getJobResponsibilityDAO() {
		return jobResponsibilityDAO;
	}

	public void setProjectRoleDAO(ProjectRoleDAO projectRoleDAO) {
		this.projectRoleDAO = projectRoleDAO;
	}

	public ProjectRoleDAO getProjectRoleDAO() {
		return projectRoleDAO;
	}

	public void setProjectStatusDAO(ProjectStatusDAO projectStatusDAO) {
		this.projectStatusDAO = projectStatusDAO;
	}

	public ProjectStatusDAO getProjectStatusDAO() {
		return projectStatusDAO;
	}
	
	public AttachmentCategoryDAO getAttachmentCategoryDAO() {
		return attachmentCategoryDAO;
	}

	public void setAttachmentCategoryDAO(AttachmentCategoryDAO attachmentCategoryDAO) {
		this.attachmentCategoryDAO = attachmentCategoryDAO;
	}
		
	public JobResponsibility getJobResponsibilityById(Integer id) {
		// TODO Auto-generated method stub
		return jobResponsibilityDAO.get(id);
	}

	
	public ProjectRole getProjectRoleById(Integer id) {
		// TODO Auto-generated method stub
		return projectRoleDAO.get(id);
	}

	public List<CustomerType> getCustomerTypeList() {
		// TODO Auto-generated method stub
		String sql = "from CustomerType a where a.ctFlag != " + DELETE_FLAG;
		return customerTypeDAO.find(sql);
	}
	
	public CustomerTypeDAO getCustomerTypeDAO() {
		return customerTypeDAO;
	}

	public void setCustomerTypeDAO(CustomerTypeDAO customerTypeDAO) {
		this.customerTypeDAO = customerTypeDAO;
	}

	public AttachmentCategory getAttachmentCategoryById(Integer id) {
		// TODO Auto-generated method stub
		return this.attachmentCategoryDAO.get(id);
	}
	
	public List<PaymentType> getPaymentTypeList() {
		String sql = "from PaymentType a where a.ptFlag != " + DELETE_FLAG;
		return paymentTypeDAO.find(sql);
	}
	
	public PaymentTypeDAO getPaymentTypeDAO() {
		return paymentTypeDAO;
	}

	public void setPaymentTypeDAO(PaymentTypeDAO paymentTypeDAO) {
		this.paymentTypeDAO = paymentTypeDAO;
	}

	@Override
	public List<TradeType> getTradeTypeList() {
		// TODO Auto-generated method stub
		String sql = "from TradeType a where a.ttFlag != " + DELETE_FLAG;
		return tradeTypeDAO.find(sql);
	}

	public void setTradeTypeDAO(TradeTypeDAO tradeTypeDAO) {
		this.tradeTypeDAO = tradeTypeDAO;
	}

	public TradeTypeDAO getTradeTypeDAO() {
		return tradeTypeDAO;
	}
	

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}


	public AccountDAO getAccountDAO() {
		return accountDAO;
	}

	@Override
	public List<Department> getDepartmentList() {
		// TODO Auto-generated method stub
		String sql = "from Department a where a.DFlag != " + DELETE_FLAG;
		return departmentDAO.find(sql);
	}
	
	@Override
	public List<Department> getNoticeSubmitDepartmentList() {
		// TODO Auto-generated method stub
		String sql = "from Department a where a.DFlag = 1";
		return departmentDAO.find(sql);
	}

	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	public DepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}

	@Override
	public List<PersonStatus> getPersonstatusList() {
		// TODO Auto-generated method stub
		String sql = "from PersonStatus a where a.psFlag != " + DELETE_FLAG;
		return personStatusDAO.find(sql);
	}

	@Override
	public List<PoliticalStatus> getPoliticalstatusList() {
		// TODO Auto-generated method stub
		String sql = "from PoliticalStatus a where a.psFlag != " + DELETE_FLAG;
		return politicalStatusDAO.find(sql);
	}

	public void setPersonStatusDAO(PersonStatusDAO personStatusDAO) {
		this.personStatusDAO = personStatusDAO;
	}

	public PersonStatusDAO getPersonStatusDAO() {
		return personStatusDAO;
	}

	public void setPoliticalStatusDAO(PoliticalStatusDAO politicalStatusDAO) {
		this.politicalStatusDAO = politicalStatusDAO;
	}

	public PoliticalStatusDAO getPoliticalStatusDAO() {
		return politicalStatusDAO;
	}

	@Override
	public List<EducationBackground> getEducationList() {
		// TODO Auto-generated method stub
		String sql = "from EducationBackground a where a.ebFlag != " + DELETE_FLAG;
		return educationBackgroundDAO.find(sql);
	}

	@Override
	public List<Post> getPostList() {
		// TODO Auto-generated method stub
		String sql = "from Post a where a.PFlag != " + DELETE_FLAG;
		return postDAO.find(sql);
	}

	public void setEducationBackgroundDAO(EducationBackgroundDAO educationBackgroundDAO) {
		this.educationBackgroundDAO = educationBackgroundDAO;
	}

	public EducationBackgroundDAO getEducationBackgroundDAO() {
		return educationBackgroundDAO;
	}

	public void setPostDAO(PostDAO postDAO) {
		this.postDAO = postDAO;
	}

	public PostDAO getPostDAO() {
		return postDAO;
	}

	@Override
	public List<CustomerNature> getCustomerNatureList() {
		// TODO Auto-generated method stub
		String sql = "from CustomerNature a where a.cnFlag != " + DELETE_FLAG;
		return customerNatureDAO.find(sql);
	}

	public void setCustomerNatureDAO(CustomerNatureDAO customerNatureDAO) {
		this.customerNatureDAO = customerNatureDAO;
	}

	public CustomerNatureDAO getCustomerNatureDAO() {
		return customerNatureDAO;
	}
	
	@Override
	public CustomerType getCustomerTypeById(Integer id) {
		// TODO Auto-generated method stub
		return customerTypeDAO.get(id);
	}

	@Override
	public TradeType getTradeTypeById(Integer id) {
		// TODO Auto-generated method stub
		return tradeTypeDAO.get(id);
	}
	
	public ArrayList<Customer> searchReferCompany(Object[] args) {
		Customer customerInfo = (Customer) args[0];
		String page = (String) args[1];

		String hqlStr = "from Customer a where a.CFlag != "	+ CommonService.DELETE_FLAG;

		hqlStr += (StringUtils.isNotEmpty(customerInfo.getCName()) ? " and a.CName like '%"	+ customerInfo.getCName() + "%'": "");
		
		hqlStr += ((customerInfo.getCType() != null && customerInfo.getCType() != 0)?" and a.CType = " + customerInfo.getCType() : "");
    	
    	hqlStr += ((customerInfo.getCTradeType() != null && customerInfo.getCTradeType() != 0)?" and a.CTradeType = " + customerInfo.getCTradeType() : "");

		MyQuery myQuery = new MyQuery();
		myQuery.setPageSize(10);

		if (StringUtils.isNumeric(page)) {
			myQuery.setPageStartNo(Integer.parseInt(page));
		} else {
			myQuery.setPageStartNo(0);
		}
		myQuery.setOrderby(" order by a.CId");
		myQuery.setQueryString(hqlStr);

		myQuery.setOffset(true);

		return (ArrayList<Customer>) customerDAO.find(myQuery);
	}
	
	public int searchReferCompanyCount(Object[] args) {
		Customer customerInfo = (Customer) args[0];

		String hqlStr = "select count(*) from Customer a where a.CFlag != "+ CommonService.DELETE_FLAG;
	
		hqlStr += (StringUtils.isNotEmpty(customerInfo.getCName()) ? " and a.CName like '%"	+ customerInfo.getCName() + "%'": "");
		
		hqlStr += ((customerInfo.getCType() != null && customerInfo.getCType() != 0)?" and a.CType = " + customerInfo.getCType() : "");
    	
    	hqlStr += ((customerInfo.getCTradeType() != null && customerInfo.getCTradeType() != 0)?" and a.CTradeType = " + customerInfo.getCTradeType() : "");

		MyQuery myQuery = new MyQuery();

		myQuery.setQueryString(hqlStr);

		return customerDAO.getFindCount(myQuery);
	}
	
	public ArrayList<Customer> searchCustomer(Object[] args) {
		Customer customerInfo = (Customer) args[0];
		String page = (String) args[1];

		String hqlStr = "from Customer a where a.CType = " + CustomerType.CUSTOMER_TYPE_TENDERER + " and a.CFlag != "	+ CommonService.DELETE_FLAG;

		hqlStr += (StringUtils.isNotEmpty(customerInfo.getCName()) ? " and a.CName like '%"	+ customerInfo.getCName() + "%'": "");

		MyQuery myQuery = new MyQuery();
		myQuery.setPageSize(10);

		if (StringUtils.isNumeric(page)) {
			myQuery.setPageStartNo(Integer.parseInt(page));
		} else {
			myQuery.setPageStartNo(0);
		}
		myQuery.setOrderby(" order by a.CId");
		myQuery.setQueryString(hqlStr);

		myQuery.setOffset(true);

		return (ArrayList<Customer>) customerDAO.find(myQuery);
	}

	public int searchCustomerCount(Object[] args) {
		Customer customerInfo = (Customer) args[0];

		String hqlStr = "select count(*) from Customer a where a.CType = " + CustomerType.CUSTOMER_TYPE_TENDERER + " and a.CFlag != "+ CommonService.DELETE_FLAG;

		hqlStr += (StringUtils.isNotEmpty(customerInfo.getCName()) ? " and a.CName like '%"	+ customerInfo.getCName() + "%'": "");

		MyQuery myQuery = new MyQuery();

		myQuery.setQueryString(hqlStr);

		return customerDAO.getFindCount(myQuery);
	}
	
	public ArrayList<Customer> searchCompetitor(Object[] args) {
		Customer customerInfo = (Customer) args[0];
		String page = (String) args[1];

		String hqlStr = "from Customer a where a.CType = " + CustomerType.CUSTOMER_TYPE_COMPETITOR + " and a.CFlag != "	+ CommonService.DELETE_FLAG;

		hqlStr += (StringUtils.isNotEmpty(customerInfo.getCName()) ? " and a.CName like '%"	+ customerInfo.getCName() + "%'": "");

		MyQuery myQuery = new MyQuery();
		myQuery.setPageSize(10);

		if (StringUtils.isNumeric(page)) {
			myQuery.setPageStartNo(Integer.parseInt(page));
		} else {
			myQuery.setPageStartNo(0);
		}
		myQuery.setOrderby(" order by a.CId");
		myQuery.setQueryString(hqlStr);

		myQuery.setOffset(true);

		return (ArrayList<Customer>) customerDAO.find(myQuery);
	}

	public int searchCompetitorCount(Object[] args) {
		Customer customerInfo = (Customer) args[0];

		String hqlStr = "select count(*) from Customer a where a.CType = " + CustomerType.CUSTOMER_TYPE_COMPETITOR + " and a.CFlag != "+ CommonService.DELETE_FLAG;
		
		hqlStr += (StringUtils.isNotEmpty(customerInfo.getCName()) ? " and a.CName like '%"	+ customerInfo.getCName() + "%'": "");

		MyQuery myQuery = new MyQuery();

		myQuery.setQueryString(hqlStr);

		return customerDAO.getFindCount(myQuery);
	}
	
	public ArrayList<Customer> searchSupplier(Object[] args) {
		Customer customerInfo = (Customer) args[0];
		String page = (String) args[1];

		String hqlStr = "from Customer a where a.CType = " + CustomerType.CUSTOMER_TYPE_SUPPLIER + " and a.CFlag != "	+ CommonService.DELETE_FLAG;

		hqlStr += (StringUtils.isNotEmpty(customerInfo.getCName()) ? " and a.CName like '%"	+ customerInfo.getCName() + "%'": "");
		
		hqlStr += ((customerInfo.getCTradeType() != null && customerInfo.getCTradeType() != 0) ? " and a.CTradeType = " + customerInfo.getCTradeType() : "");

		MyQuery myQuery = new MyQuery();
		myQuery.setPageSize(10);

		if (StringUtils.isNumeric(page)) {
			myQuery.setPageStartNo(Integer.parseInt(page));
		} else {
			myQuery.setPageStartNo(0);
		}
		myQuery.setOrderby(" order by a.CId");
		myQuery.setQueryString(hqlStr);

		myQuery.setOffset(true);

		return (ArrayList<Customer>) customerDAO.find(myQuery);
	}

	public int searchSupplierCount(Object[] args) {
		Customer customerInfo = (Customer) args[0];

		String hqlStr = "select count(*) from Customer a where a.CType = " + CustomerType.CUSTOMER_TYPE_SUPPLIER + " and a.CFlag != "+ CommonService.DELETE_FLAG;

		hqlStr += (StringUtils.isNotEmpty(customerInfo.getCName()) ? " and a.CName like '%"	+ customerInfo.getCName() + "%'": "");
		
		hqlStr += ((customerInfo.getCTradeType() != null && customerInfo.getCTradeType() != 0) ? " and a.CTradeType = " + customerInfo.getCTradeType() : "");

		MyQuery myQuery = new MyQuery();

		myQuery.setQueryString(hqlStr);

		return customerDAO.getFindCount(myQuery);
	}

	public Customer getCustomerById(Integer id) {
		return customerDAO.get(id);
	}
	

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	@Override
	public List<DeviceUnit> getDeviceUnitList() {
		// TODO Auto-generated method stub
		String sql = "from DeviceUnit a where a.duFlag != " + DELETE_FLAG;
		return deviceUnitDAO.find(sql);
	}

	public void setDeviceUnitDAO(DeviceUnitDAO deviceUnitDAO) {
		this.deviceUnitDAO = deviceUnitDAO;
	}

	public DeviceUnitDAO getDeviceUnitDAO() {
		return deviceUnitDAO;
	}
	
	public static Date transformDisplayDate(Date disDate)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(disDate);		
		c.set(Calendar.DAY_OF_MONTH,1);
		c.add(Calendar.MONTH, -6);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		return c.getTime();
	}
	
	public void setCheckTaskDAO(CheckTaskDAO checkTaskDAO) {
		this.checkTaskDAO = checkTaskDAO;
	}

	public CheckTaskDAO getCheckTaskDAO() {
		return checkTaskDAO;
	}
	
	public void setTenderRoleDAO(TenderRoleDAO tenderRoleDAO) {
		this.tenderRoleDAO = tenderRoleDAO;
	}

	public TenderRoleDAO getTenderRoleDAO() {
		return tenderRoleDAO;
	}

	@Override
	public TenderRole getTenderRoleById(Integer id) {
		// TODO Auto-generated method stub
		return tenderRoleDAO.get(id);
	}

	@Override
	public List<NoticeType> getNoticeTypeList() {
		// TODO Auto-generated method stub
		String sql = "from NoticeType a where a.ntFlag != " + DELETE_FLAG;
		return noticeTypeDAO.find(sql);
	}


	@Override
	public NoticeType getNoticeTypeById(Integer id) {
		// TODO Auto-generated method stub
		return noticeTypeDAO.get(id);
	}	
	
	
	public NoticeTypeDAO getNoticeTypeDAO() {
		return noticeTypeDAO;
	}

	public void setNoticeTypeDAO(NoticeTypeDAO noticeTypeDAO) {
		this.noticeTypeDAO = noticeTypeDAO;
	}


	public static String getProjectAttachmentCode(Integer paCategory,int no)
	{
		String rtn = "0000" + (no +1);
		
		rtn = rtn.substring(rtn.length() -4);
		
		if(paCategory == 1)
		{
			rtn = "A-" + rtn;
		}
		else if(paCategory == 2)
		{
			rtn = "B-" + rtn;
		}
		else if(paCategory == 3)
		{
			rtn = "C-" + rtn;
		}
		else if(paCategory == 4)
		{
			rtn = "D-" + rtn;
		}
		else
		{
			rtn = "Z-" + rtn;
		}
				
		return rtn;
	}

	@Override
	public Department getDepartmentById(Integer id) {
		// TODO Auto-generated method stub
		return departmentDAO.get(id);
	}

	@Override
	public ProjectStatus getProjectStatusById(Integer id) {
		// TODO Auto-generated method stub
		return projectStatusDAO.get(id);
	}
}
