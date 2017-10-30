package org.king.tender.service;

import java.io.Serializable;
import java.util.List;

import org.king.common.checktask.CheckTask;
import org.king.framework.service.Service;
import org.king.projectmanage.bean.ProjectAttachment;
import org.king.projectmanage.bean.TenderProject;
import org.king.tender.bean.BudgetItem;
import org.king.tender.bean.DeviceList1st;
import org.king.tender.bean.DeviceList2nd;
import org.king.tender.bean.DeviceListDetail;
import org.king.tender.bean.ProjectBudget;

public interface TenderManageService extends Service{
	public TenderProject createTenderProject();

	public TenderProject getTenderProjectById(Serializable id);
	public void saveTenderProject(TenderProject tp,String uploadPath);
	public void updateTenderProject(TenderProject tp);
	public void deleteTenderProject(TenderProject tp);
	
	public ProjectAttachment createProjectAttachment();
	public void deleteProjectAttachment(ProjectAttachment pa);
	public List<TenderProject> searchTenderProject(Object[] args);
	public int searchTenderProjectCount(Object[] args);
				
	public BudgetItem getBudgetItemById(Serializable id);
		
	public void saveDeviceList1st(DeviceList1st firstDevice);
	public void updateDeviceList1st(DeviceList1st firstDevice);
	public DeviceList1st getDeviceList1stById(Integer id);
	
	public void saveDeviceList2nd(DeviceList2nd secondDevice);
	public void updateDeviceList2nd(DeviceList2nd secondDevice);
	public DeviceList2nd getDeviceList2ndById(Integer id);
	
	public void saveDeviceListDetail(DeviceListDetail detailDevice);
	public void updateDeviceListDetail(DeviceListDetail detailDevice);
	public DeviceListDetail findDeviceListDetailById(Integer id);
	
	public List<ProjectAttachment> searchAttachment(Object[] args);
	public int searchAttachmentCount(Object[] args);
	public ProjectAttachment getProjectAttachmentById(Integer id);
	
	public void saveProjectDocument(TenderProject tp,String serverPath,String uploadPath);
	
	public ProjectBudget modifyCreateBudgetItems();
	public void saveProjectBudget(ProjectBudget projectBudget);
	public void saveBudgetItem(BudgetItem budgetItem);
	public void updateProjectBudget(ProjectBudget projectBudget);
	
	public List<CheckTask> getAllStepCheckTask(TenderProject tp,String type);
}
