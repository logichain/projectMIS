package org.king.department.service;

import java.util.ArrayList;

import org.king.department.bean.Department;
import org.king.framework.service.Service;
import org.king.security.domain.Account;

public interface DepartmentService extends Service {
	public Department createDepartment();
	
	public void saveDepartment(Department c);
	
	public void deleteDepartment(Department c);
	
	public ArrayList<Department> searchDepartment(String[] args);
	public int searchDepartmentCount(String[] args);
	
	public ArrayList<Department> getAllDepartment();
			
	public Department getDepartmentById(int id);
	
}
