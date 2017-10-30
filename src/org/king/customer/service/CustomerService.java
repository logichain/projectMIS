package org.king.customer.service;

import java.util.ArrayList;

import org.king.customer.bean.Customer;
import org.king.customer.bean.CustomerAttachment;
import org.king.framework.service.Service;

public interface CustomerService extends Service {
	public Customer createCustomer();
	
	public void saveCustomer(Customer c,String uploadPath);
	public void saveCustomer(Customer c);
	
	public void deleteCustomer(Customer c);
	
	public ArrayList<Customer> searchCustomer(Object[] args);
	public int searchCustomerCount(Object[] args);
	
	public ArrayList<Customer> getAllCustomer();
			
	public Customer getCustomerById(int id);
	public Customer searchCustomerByName(String customerName);
	public CustomerAttachment getCustomerAttachmentById(Integer id);
}
