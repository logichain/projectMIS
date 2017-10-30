package org.king.department.service.impl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.king.common.service.CommonService;
import org.king.department.bean.Department;
import org.king.department.dao.DepartmentDAO;
import org.king.department.service.DepartmentService;
import org.king.framework.dao.MyQuery;
import org.king.framework.service.impl.BaseService;
import org.king.security.dao.AccountDAO;

public class DepartmentServiceImpl extends BaseService implements DepartmentService {
	private DepartmentDAO DepartmentDAO;
	private AccountDAO accountDAO;


	public Department createDepartment() {
		// TODO Auto-generated method stub
		Department c = new Department();
		c.setDFlag(CommonService.NORMAL_FLAG);
		
		return c;
	}

	public void deleteDepartment(Department c) {
		// TODO Auto-generated method stub
		DepartmentDAO.delete(c);
	}

	public ArrayList<Department> getAllDepartment() {
		// TODO Auto-generated method stub
		return (ArrayList<Department>) DepartmentDAO.find("from Department a where a.CFlag != '-1'");
	}

	public Department getDepartmentById(int id) {
		// TODO Auto-generated method stub
		return DepartmentDAO.get(id);
	}

	public void saveDepartment(Department c) {
		// TODO Auto-generated method stub
		if(c.getDId() == null || c.getDId() == 0)
		{
			c.setDCreateTime(new Date());
			DepartmentDAO.save(c);
		}
		else
		{
			DepartmentDAO.update(c);
		}
	}

	public ArrayList<Department> searchDepartment(String[] args) {
		// TODO Auto-generated method stub
		String name = args[0];    
    	String page = args[1];
    	
    	String hqlStr = "from Department a where a.DFlag != " + CommonService.DELETE_FLAG;
      	    	
    	hqlStr += (StringUtils.isNotEmpty(name)?" and a.DName like '%" + name + "%'" : "");
    	
    	
    	MyQuery myQuery = new MyQuery();
    	
        if (StringUtils.isNumeric(page)) {
        	myQuery.setPageStartNo(Integer.parseInt(page));
        }else {
        	myQuery.setPageStartNo(0);
        }
        myQuery.setOrderby(" order by a.DId");
        myQuery.setQueryString(hqlStr);

        myQuery.setOffset(true);
        
		return (ArrayList<Department>) DepartmentDAO.find(myQuery);
	}

	public int searchDepartmentCount(String[] args) {
		// TODO Auto-generated method stub
		String name = args[0];    
    	        
    	String hqlStr = "select count(*) from Department a where a.DFlag != " + CommonService.DELETE_FLAG;
      	
    	hqlStr += (StringUtils.isNotEmpty(name)?" and a.DName like '%" + name + "%'" : "");
    	
    	
    	MyQuery myQuery = new MyQuery();
              
        myQuery.setQueryString(hqlStr);       
        
		return DepartmentDAO.getFindCount(myQuery);
	}
	
	public AccountDAO getAccountDAO() {
		return accountDAO;
	}

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	public DepartmentDAO getDepartmentDAO() {
		return DepartmentDAO;
	}

	public void setDepartmentDAO(DepartmentDAO DepartmentDAO) {
		this.DepartmentDAO = DepartmentDAO;
	}
}
