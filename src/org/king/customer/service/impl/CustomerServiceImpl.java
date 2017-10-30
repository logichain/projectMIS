package org.king.customer.service.impl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.king.common.service.CommonService;
import org.king.common.service.impl.CommonServiceImpl;
import org.king.customer.bean.Customer;
import org.king.customer.bean.CustomerAttachment;
import org.king.customer.dao.CustomerAttachmentDAO;
import org.king.customer.dao.CustomerDAO;
import org.king.customer.service.CustomerService;
import org.king.framework.dao.MyQuery;
import org.king.framework.service.impl.BaseService;
import org.king.projectmanage.bean.ProjectAttachment;
import org.king.security.dao.AccountDAO;
import org.king.security.domain.Account;
import org.king.utils.FileUtil;

public class CustomerServiceImpl extends BaseService implements CustomerService {
	private CustomerDAO customerDAO;
	private AccountDAO accountDAO;
	private CustomerAttachmentDAO customerAttachmentDAO;


	public Customer createCustomer() {
		// TODO Auto-generated method stub
		Customer c = new Customer();
		c.setCFlag(CommonService.NORMAL_FLAG);
		
		return c;
	}

	public void deleteCustomer(Customer c) {
		// TODO Auto-generated method stub
		customerDAO.delete(c);
	}

	public ArrayList<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return (ArrayList<Customer>) customerDAO.find("from Customer a where a.CFlag != '-1'");
	}

	public Customer getCustomerById(int id) {
		// TODO Auto-generated method stub
		return customerDAO.get(id);
	}

	public void saveCustomer(Customer c,String uploadPath) {
		// TODO Auto-generated method stub
		ArrayList<CustomerAttachment> attachmentList = c.getAttachmentList();
		c.setAttachmentList(new ArrayList<CustomerAttachment>());
		
		Date createTime = new Date();
		if(c.getCId() == null || c.getCId() == 0)
		{
			c.setCCreateTime(createTime);
			customerDAO.save(c);
		}
		else
		{
			customerDAO.update(c);
		}
		
		c.setAttachmentList(attachmentList);
		
		for (CustomerAttachment pa : attachmentList) {
			if (pa.getCaId() == null) {
				String fileName = FileUtil.saveUploadFile(pa.getAttachmentFile(), uploadPath + c.getCId());
				pa.setCaCustomer(c.getCId());
				pa.setCaCreateTime(createTime);
				pa.setCaUrl(uploadPath + c.getCId() + "\\" + fileName);
				pa.setCaCreateUser(c.getCCreateUser());
								
				customerAttachmentDAO.save(pa);				
			}
			else if(pa.isModified())
			{				
				customerAttachmentDAO.update(pa);
				pa.setModified(false);
			}
		}
	}
	
	public void saveCustomer(Customer c) {
		// TODO Auto-generated method stub			
		Date createTime = new Date();
		if(c.getCId() == null || c.getCId() == 0)
		{
			c.setCCreateTime(createTime);
			customerDAO.save(c);
		}
		else
		{
			customerDAO.update(c);
		}		
	}

	public ArrayList<Customer> searchCustomer(Object[] args) {
		// TODO Auto-generated method stub
		Customer searchInfo = (Customer) args[0];
    	Account user = (Account) args[1];
    	String page = (String) args[2];
    	
    	String hqlStr = "";
    	if(user.getId().equals("0"))
    	{
    		hqlStr = "from Customer a where 1=1 ";
    	}
    	else
    	{
    		hqlStr = "from Customer a where a.CFlag != " + CommonService.DELETE_FLAG;
    	}
      	    	
    	hqlStr += (StringUtils.isNotEmpty(searchInfo.getCName())?" and a.CName like '%" + searchInfo.getCName() + "%'" : "");
    	
    	hqlStr += (searchInfo.getCFlag() != null)?" and a.CFlag = " + searchInfo.getCFlag() : " and a.CFlag != " + Customer.CUSTOMER_FLAG_QUALIFIED;
    	hqlStr += (searchInfo.getCType() != null && !searchInfo.getCType().equals(0))?" and a.CType = " + searchInfo.getCType() : "";
    	hqlStr += (searchInfo.getCTradeType() != null && !searchInfo.getCTradeType().equals(0))?" and a.CTradeType = " + searchInfo.getCTradeType() : "";
    	
    	MyQuery myQuery = new MyQuery();
    	myQuery.setPageSize(searchInfo.getPageItemCount());
        if (StringUtils.isNumeric(page)) {
        	myQuery.setPageStartNo(Integer.parseInt(page));
        }else {
        	myQuery.setPageStartNo(0);
        }
        
        if(searchInfo.getOrderColumn().isEmpty())
        {
        	myQuery.setOrderby(" order by a.CId desc");
        }
        else
        {
        	if(searchInfo.isAscFlag())
        	{
        		myQuery.setOrderby(" order by a." + searchInfo.getOrderColumn());
        	}
        	else
        	{
        		myQuery.setOrderby(" order by a." + searchInfo.getOrderColumn() + " desc");
        	}
        }
                
        myQuery.setQueryString(hqlStr);

        myQuery.setOffset(true);
        
		return (ArrayList<Customer>) customerDAO.find(myQuery);
	}

	public int searchCustomerCount(Object[] args) {
		// TODO Auto-generated method stub
		Customer searchInfo = (Customer) args[0];
    	Account user = (Account) args[1];
    	String page = (String) args[2];
    	              	    	
    	String hqlStr = "";
    	if(user.getId().equals("0"))
    	{
    		hqlStr = "select count(*) from Customer a where 1=1 ";
    	}
    	else
    	{
    		hqlStr = "select count(*) from Customer a where a.CFlag != " + CommonService.DELETE_FLAG;
    	}
      	    	
    	hqlStr += (StringUtils.isNotEmpty(searchInfo.getCName())?" and a.CName like '%" + searchInfo.getCName() + "%'" : "");
    	
    	hqlStr += (searchInfo.getCFlag() != null)?" and a.CFlag = " + searchInfo.getCFlag() : " and a.CFlag != " + Customer.CUSTOMER_FLAG_QUALIFIED;
    	hqlStr += (searchInfo.getCType() != null && !searchInfo.getCType().equals(0))?" and a.CType = " + searchInfo.getCType() : "";
    	hqlStr += (searchInfo.getCTradeType() != null && !searchInfo.getCTradeType().equals(0))?" and a.CTradeType = " + searchInfo.getCTradeType() : "";
    	
    	
    	MyQuery myQuery = new MyQuery();
              
        myQuery.setQueryString(hqlStr);       
        
		return customerDAO.getFindCount(myQuery);
	}
	
	public AccountDAO getAccountDAO() {
		return accountDAO;
	}

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	@Override
	public Customer searchCustomerByName(String customerName) {
		// TODO Auto-generated method stub
		ArrayList<Customer> list = (ArrayList<Customer>) customerDAO.find("from Customer a where a.CFlag != '-1' and a.CName = '" + customerName + "'");
		
		if(list.size() > 0)
		{
			return list.get(0);
		}
		
		return null;
	}

	public CustomerAttachmentDAO getCustomerAttachmentDAO() {
		return customerAttachmentDAO;
	}

	public void setCustomerAttachmentDAO(CustomerAttachmentDAO customerAttachmentDAO) {
		this.customerAttachmentDAO = customerAttachmentDAO;
	}

	@Override
	public CustomerAttachment getCustomerAttachmentById(Integer id) {
		// TODO Auto-generated method stub
		return customerAttachmentDAO.get(id);
	}
}
