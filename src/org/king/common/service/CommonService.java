package org.king.common.service;

import java.util.ArrayList;
import java.util.List;

import org.king.common.attachmentcategory.AttachmentCategory;
import org.king.common.customernature.CustomerNature;
import org.king.common.customertype.CustomerType;
import org.king.common.deviceunit.DeviceUnit;
import org.king.common.education.EducationBackground;
import org.king.common.jobresponsibility.JobResponsibility;
import org.king.common.noticetype.NoticeType;
import org.king.common.paymenttype.PaymentType;
import org.king.common.personstatus.PersonStatus;
import org.king.common.politicalstatus.PoliticalStatus;
import org.king.common.post.Post;
import org.king.common.projectrole.ProjectRole;
import org.king.common.projectstatus.ProjectStatus;
import org.king.common.tenderrole.TenderRole;
import org.king.common.tradetype.TradeType;
import org.king.customer.bean.Customer;
import org.king.department.bean.Department;
import org.king.framework.service.Service;
import org.king.security.domain.Account;

public interface CommonService extends Service {
	int DELETE_FLAG = -1;
	int NORMAL_FLAG = 0;
	
	long DAY_MILLISECOND = 24 *60 *60 *1000;
	
	int INPUT_INTERFACE_INPUT = 1;
	int INPUT_INTERFACE_BASE = 2;
	int INPUT_INTERFACE_CONTRACT = 3;
	int INPUT_INTERFACE_PAR = 4;
			
	public List<JobResponsibility> getJobResponsibilityList();
	public List<ProjectRole> getProjectRoleList();
	public List<TenderRole> getTenderRoleList();
	public List<ProjectStatus> getProjectStatusList();
	public List<AttachmentCategory> getAttachmentCategoryList();
	public List<CustomerType> getCustomerTypeList();
	public List<TradeType> getTradeTypeList();
	public List<Department> getDepartmentList();
	public List<Department> getNoticeSubmitDepartmentList();
	public List<PersonStatus> getPersonstatusList();
	public List<PoliticalStatus> getPoliticalstatusList();
	public List<EducationBackground> getEducationList();
	public List<Post> getPostList();
	public List<CustomerNature> getCustomerNatureList();
	public List<DeviceUnit> getDeviceUnitList();
	
	public TenderRole getTenderRoleById(Integer id);
	public ProjectRole getProjectRoleById(Integer id);
	public JobResponsibility getJobResponsibilityById(Integer id);
	public AttachmentCategory getAttachmentCategoryById(Integer id);
	public CustomerType getCustomerTypeById(Integer id);
	public TradeType getTradeTypeById(Integer id);
	public Department getDepartmentById(Integer id);
	public ProjectStatus getProjectStatusById(Integer id);
	public List<PaymentType> getPaymentTypeList();
	
	public ArrayList<Account> searchAllAccount(Account accountInfo);
	public ArrayList<Account> searchAccount(Object[] args);
	public int searchAccountCount(Object[] args);
	public Account getAccountById(String id);
	public ArrayList<Account> searchTeamMemberAccount(Object[] args,int tpId);
	public int searchTeamMemberAccountCount(Object[] args,int tpId);
		
	public ArrayList<Customer> searchReferCompany(Object[] args);
	public int searchReferCompanyCount(Object[] args);
	public ArrayList<Customer> searchCustomer(Object[] args);
	public int searchCustomerCount(Object[] args);
	public Customer getCustomerById(Integer id);
	
	public ArrayList<Customer> searchCompetitor(Object[] args);
	public int searchCompetitorCount(Object[] args);
	
	public ArrayList<Customer> searchSupplier(Object[] args);
	public int searchSupplierCount(Object[] args);	
		
	public List<NoticeType> getNoticeTypeList();
	public NoticeType getNoticeTypeById(Integer id);
}
