package org.king.tender.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.king.common.checktask.CheckTask;
import org.king.common.checktask.CheckTaskDAO;
import org.king.common.projectstatus.ProjectStatus;
import org.king.common.service.CommonService;
import org.king.common.service.impl.CommonServiceImpl;
import org.king.customer.bean.Customer;
import org.king.framework.dao.MyQuery;
import org.king.projectmanage.bean.ProjectAttachment;
import org.king.projectmanage.bean.ProjectContract;
import org.king.projectmanage.bean.TenderProject;
import org.king.projectmanage.dao.ProjectAttachmentDAO;
import org.king.projectmanage.dao.TenderProjectDAO;
import org.king.security.domain.Account;
import org.king.tender.bean.BudgetItem;
import org.king.tender.bean.BudgetItemModel;
import org.king.tender.bean.DeviceList1st;
import org.king.tender.bean.DeviceList2nd;
import org.king.tender.bean.DeviceListDetail;
import org.king.tender.bean.ProjectBudget;
import org.king.tender.dao.BudgetItemDAO;
import org.king.tender.dao.BudgetItemModelDAO;
import org.king.tender.dao.DeviceList1stDAO;
import org.king.tender.dao.DeviceList2ndDAO;
import org.king.tender.dao.DeviceListDetailDAO;
import org.king.tender.dao.ProjectBudgetDAO;
import org.king.tender.service.TenderManageService;
import org.king.utils.FileUtil;

public class TenderManageServiceImpl implements TenderManageService {
		
	private TenderProjectDAO tenderProjectDAO;
	
	private DeviceList1stDAO deviceList1stDAO;
	
	private DeviceList2ndDAO deviceList2ndDAO;
	
	private DeviceListDetailDAO deviceListDetailDAO;
	
	private BudgetItemDAO budgetItemDAO;
	
	private BudgetItemModelDAO budgetItemModelDAO;
	
	private ProjectBudgetDAO projectBudgetDAO;
	
	private ProjectAttachmentDAO projectAttachmentDAO;
	
	private CheckTaskDAO checkTaskDAO;
		
		
	public CheckTaskDAO getCheckTaskDAO() {
		return checkTaskDAO;
	}

	public void setCheckTaskDAO(CheckTaskDAO checkTaskDAO) {
		this.checkTaskDAO = checkTaskDAO;
	}

	public TenderProject createTenderProject() {
		TenderProject rtn = new TenderProject();
		rtn.setTpCreateTime(new Date());
		rtn.setManager(new Account());
		rtn.setCustomer(new Customer());
		rtn.setStatus(new ProjectStatus());
		rtn.setTpStatus(ProjectStatus.PROJECT_STATUS_TENDER);
		rtn.setMarketManager(new Account());
		
		return rtn;
	}
	
	public TenderProject getTenderProjectById(Serializable id){
		return tenderProjectDAO.get(id);
	}
	
	public void deleteTenderProject(TenderProject tp) {
		// TODO Auto-generated method stub
		tp.setTpStatus(CommonService.DELETE_FLAG);		
		tenderProjectDAO.save(tp);
	}
	
	public void saveTenderProject(TenderProject tp,String uploadPath) {		
		ArrayList<ProjectAttachment> attachmentList = tp.getAttachmentList();
		tp.setAttachmentList(new ArrayList<ProjectAttachment>());

		Date createTime = new Date();
		if (tp.getTpId() == null) {						
			tp.setTpCreateTime(createTime);
			tenderProjectDAO.save(tp);
			
			//生成投标预算
			ProjectBudget pb = this.modifyCreateBudgetItems();
			pb.setPbType(ProjectBudget.BUDGET_TYPE_TENDER);
			pb.setPbProject(tp.getTpId());
			tp.setTenderBudget(pb);
			projectBudgetDAO.save(pb);
			
			//生成投标测算
			ProjectBudget pbs = this.modifyCreateBudgetItems();
			pbs.setPbType(ProjectBudget.BUDGET_TYPE_APPLY);
			pbs.setPbProject(tp.getTpId());
			tp.setApplyBudget(pbs);
			projectBudgetDAO.save(pbs);
		} else {
			tenderProjectDAO.update(tp);
		}
		tp.setAttachmentList(attachmentList);

		for (ProjectAttachment pa : attachmentList) {
			if (pa.getPaId() == null) {
				String fileName = FileUtil.saveUploadFile(pa.getAttachmentFile(), uploadPath + tp.getTpId());
				pa.setPaTenderProject(tp.getTpId());
				pa.setPaCreateTime(createTime);
				pa.setPaUrl(uploadPath + tp.getTpId() + "\\" + fileName);
				pa.setPaCreateUser(tp.getTpCreateUser());
				pa.setCreateUser(tp.getCreateUser());
								
				projectAttachmentDAO.save(pa);
				
				int count = this.getProjectAttachmentCountByCategory(pa.getPaAttachmentCategory());
				pa.setPaCode(CommonServiceImpl.getProjectAttachmentCode(pa.getPaAttachmentCategory(),count));
				projectAttachmentDAO.update(pa);
			}
			else if(pa.isModified())
			{				
				projectAttachmentDAO.update(pa);
				pa.setModified(false);
			}
		}		
	}
	
		
	public void updateTenderProject(TenderProject tp) {		
		tenderProjectDAO.update(tp);		
	}
	
	public ProjectBudget modifyCreateBudgetItems() {				
		ProjectBudget pb = new ProjectBudget();

		String sql = "from BudgetItemModel a order by a.bimOrder";
		List<BudgetItemModel> modelList = budgetItemModelDAO.find(sql);
		for(BudgetItemModel bim:modelList)
		{
			BudgetItem bi = new BudgetItem();
			bi.setBiModel(bim.getBimId());
			bi.setItemModel(bim);
			this.saveBudgetItem(bi);
			
			switch(bim.getBimOrder())
			{
			case 1:
				pb.setPbE7(bi.getBiId());
				pb.setE7(bi);
				break;
			case 2:
				pb.setPbE8(bi.getBiId());
				pb.setE8(bi);
				break;
			case 3:
				pb.setPbE9(bi.getBiId());
				pb.setE9(bi);
				break;
			case 4:
				pb.setPbE10(bi.getBiId());
				pb.setE10(bi);
				break;
			case 5:
				pb.setPbE11(bi.getBiId());
				pb.setE11(bi);
				break;
			case 6:
				pb.setPbE12(bi.getBiId());
				pb.setE12(bi);
				break;
			case 7:
				pb.setPbE13(bi.getBiId());
				pb.setE13(bi);
				break;
			case 8:
				pb.setPbE14(bi.getBiId());
				pb.setE14(bi);
				break;
			case 9:
				pb.setPbE15(bi.getBiId());
				pb.setE15(bi);
				break;
			case 10:
				pb.setPbE16(bi.getBiId());
				pb.setE16(bi);
				break;
			case 11:
				pb.setPbE17(bi.getBiId());
				pb.setE17(bi);
				break;
			case 12:
				pb.setPbE18(bi.getBiId());
				pb.setE18(bi);
				break;
			case 13:
				pb.setPbE19(bi.getBiId());
				pb.setE19(bi);
				break;
			case 14:
				pb.setPbE20(bi.getBiId());
				pb.setE20(bi);
				break;
			case 15:
				pb.setPbE21(bi.getBiId());
				pb.setE21(bi);
				break;
			case 16:
				pb.setPbE22(bi.getBiId());
				pb.setE22(bi);
				break;
			case 17:
				pb.setPbE23(bi.getBiId());
				pb.setE23(bi);
				break;
			case 18:
				pb.setPbE24(bi.getBiId());
				pb.setE24(bi);
				break;
			case 19:
				pb.setPbE25(bi.getBiId());
				pb.setE25(bi);
				break;
			case 20:
				pb.setPbE26(bi.getBiId());
				pb.setE26(bi);
				break;
			case 21:
				pb.setPbE27(bi.getBiId());
				pb.setE27(bi);
				break;
			case 22:
				pb.setPbE28(bi.getBiId());
				pb.setE28(bi);
				break;
			case 23:
				pb.setPbE29(bi.getBiId());
				pb.setE29(bi);
				break;
			case 24:
				pb.setPbE30(bi.getBiId());
				pb.setE30(bi);
				break;
			case 25:
				pb.setPbE31(bi.getBiId());
				pb.setE31(bi);
				break;
			case 26:
				pb.setPbE32(bi.getBiId());
				pb.setE32(bi);
				break;
			case 27:
				pb.setPbE33(bi.getBiId());
				pb.setE33(bi);
				break;
			case 28:
				pb.setPbE34(bi.getBiId());
				pb.setE34(bi);
				break;
			case 29:
				pb.setPbE35(bi.getBiId());
				pb.setE35(bi);
				break;
			case 30:
				pb.setPbE36(bi.getBiId());
				pb.setE36(bi);
				break;
			case 31:
				pb.setPbE37(bi.getBiId());
				pb.setE37(bi);
				break;
			case 32:
				pb.setPbE38(bi.getBiId());
				pb.setE38(bi);
				break;
			case 33:
				pb.setPbE39(bi.getBiId());
				pb.setE39(bi);
				break;
			case 34:
				pb.setPbE40(bi.getBiId());
				pb.setE40(bi);
				break;
			case 35:
				pb.setPbE41(bi.getBiId());
				pb.setE41(bi);
				break;
			case 36:
				pb.setPbE42(bi.getBiId());
				pb.setE42(bi);
				break;
			case 37:
				pb.setPbE43(bi.getBiId());
				pb.setE43(bi);
				break;
			case 38:
				pb.setPbE44(bi.getBiId());
				pb.setE44(bi);
				break;
			case 39:
				pb.setPbE45(bi.getBiId());
				pb.setE45(bi);
				break;
			case 40:
				pb.setPbE46(bi.getBiId());
				pb.setE46(bi);
				break;
			case 41:
				pb.setPbE47(bi.getBiId());
				pb.setE47(bi);
				break;
			default:
				System.err.println("BudgetItemModel apply error");
				break;
			}
		}
		
		return pb;
	}
	
		
	public List<TenderProject> searchTenderProject(Object[] args){
		TenderProject tenderInfo = (TenderProject) args[0];
		String page = (String) args[1];
		Account user = (Account) args[2];
		
		String hqlStr = "";
		if(user.getId().equals("0"))
		{
			hqlStr = "from TenderProject a where 1=1";
		}
		else
		{
			hqlStr = "from TenderProject a where a.tpStatus != " + CommonService.DELETE_FLAG;
		}				
		hqlStr += (StringUtils.isNotEmpty(tenderInfo.getTpName()) ? " and a.tpName like '%"	+ tenderInfo.getTpName() + "%'"	: "");
		hqlStr += (StringUtils.isNotEmpty(tenderInfo.getManager().getPerson().getPersonName())?" and a.manager.person.personName like '%" + tenderInfo.getManager().getPerson().getPersonName() + "%'" : "");
    	hqlStr += ((tenderInfo.getTpStatus() != null && tenderInfo.getTpStatus() != 0)?" and a.tpStatus = " + tenderInfo.getTpStatus() : "");
    	hqlStr += ((tenderInfo.getTpDept() != null && tenderInfo.getTpDept() != 0)?" and a.tpDept = " + tenderInfo.getTpDept() : "");
    	
		MyQuery myQuery = new MyQuery();
		myQuery.setPageSize(tenderInfo.getPageItemCount());

		if (StringUtils.isNumeric(page)) {
			myQuery.setPageStartNo(Integer.parseInt(page));
		} else {
			myQuery.setPageStartNo(0);
		}
		
		if(tenderInfo.getOrderColumn().isEmpty())
        {
        	myQuery.setOrderby(" order by a.tpId desc");
        }
        else
        {
        	if(tenderInfo.isAscFlag())
        	{
        		myQuery.setOrderby(" order by a." + tenderInfo.getOrderColumn());
        	}
        	else
        	{
        		myQuery.setOrderby(" order by a." + tenderInfo.getOrderColumn() + " desc");
        	}
        }   
		
		myQuery.setQueryString(hqlStr);

		myQuery.setOffset(true);

		return (List<TenderProject>) tenderProjectDAO.find(myQuery);
	}
	
	public int searchTenderProjectCount(Object[] args){
		TenderProject tenderInfo = (TenderProject) args[0];
		Account user = (Account) args[2];
		
		String hqlStr = "";
		if(user.getId().equals("0"))
		{
			hqlStr = "select count(a) from TenderProject a where 1=1";
		}
		else
		{
			hqlStr = "select count(a) from TenderProject a where a.tpStatus != " + CommonService.DELETE_FLAG;
		}
		
		hqlStr += (StringUtils.isNotEmpty(tenderInfo.getTpName()) ? " and a.tpName like '%"	+ tenderInfo.getTpName() + "%'"	: "");
		hqlStr += (StringUtils.isNotEmpty(tenderInfo.getManager().getPerson().getPersonName())?" and a.manager.person.personName like '%" + tenderInfo.getManager().getPerson().getPersonName() + "%'" : "");
    	hqlStr += ((tenderInfo.getTpStatus() != null && tenderInfo.getTpStatus() != 0)?" and a.tpStatus = " + tenderInfo.getTpStatus() : "");
    			
		MyQuery myQuery = new MyQuery();
		myQuery.setQueryString(hqlStr);
		
		return tenderProjectDAO.getFindCount(myQuery);
	}
	
	public ProjectAttachment createProjectAttachment(){
		ProjectAttachment rtn = new ProjectAttachment();
		rtn.setPaFlag(CommonService.NORMAL_FLAG);
		
		return rtn;
	}
	
	public void deleteProjectAttachment(ProjectAttachment pa) {
		projectAttachmentDAO.delete(pa);
	}
	
	public Customer createCustomer(){
		Customer customer = new Customer();
		customer.setCFlag(CommonService.NORMAL_FLAG);
		
		return customer;
	}
	
	public void saveProjectDocument(TenderProject tp,String serverPath,String uploadPath) {
		
		Date createTime = new Date();		
		for(ProjectAttachment pa:tp.getAttachmentList())
		{
			if(pa.getPaId() == null)
			{
				String fileName = FileUtil.saveUploadFile(pa.getAttachmentFile(),uploadPath + tp.getTpId());
				
				pa.setPaTenderProject(tp.getTpId());
				pa.setPaCreateTime(createTime);
				pa.setPaUrl(uploadPath + tp.getTpId() + "\\" + fileName);
				pa.setPaSubmitDepartment(tp.getTpDept());
												
				projectAttachmentDAO.save(pa);
				//---2014/08/01----
				int count = this.getProjectAttachmentCountByCategory(pa.getPaAttachmentCategory());
				pa.setPaCode(CommonServiceImpl.getProjectAttachmentCode(pa.getPaAttachmentCategory(),count));
				projectAttachmentDAO.update(pa);
			}	
			else if(pa.isModified())
			{
				if(!pa.getAttachmentFile().getFileName().isEmpty())
				{
					String fileName = FileUtil.saveUploadFile(pa.getAttachmentFile(),uploadPath + tp.getTpId());					
					pa.setPaUrl(uploadPath + tp.getTpId() + "\\" + fileName);
				}
				projectAttachmentDAO.update(pa);
				pa.setModified(false);
			}
		}
	}
	private int getProjectAttachmentCountByCategory(int category)
	{				
		String hqlStr = "select count(a) from ProjectAttachment a where a.paAttachmentCategory = " + category;			
				
    	MyQuery myQuery = new MyQuery();
        myQuery.setQueryString(hqlStr);
            
        return projectAttachmentDAO.getFindCount(myQuery);	
	}
		
	public void saveDeviceList1st(DeviceList1st firstDevice){
		Date createTime = new Date();
		firstDevice.setDlfCreateTime(createTime);
		deviceList1stDAO.save(firstDevice);
	}
	
	public void updateDeviceList1st(DeviceList1st firstDevice){
		Date updateTime = new Date();
		firstDevice.setDlfUpdateTime(updateTime);
		deviceList1stDAO.update(firstDevice);
	}
	
	public DeviceList1st getDeviceList1stById(Integer id){		
		return deviceList1stDAO.findById(id);
	}
	
	public void saveDeviceList2nd(DeviceList2nd secondDevice){
		Date createTime = new Date();
		secondDevice.setDlsCreateTime(createTime);
		deviceList2ndDAO.save(secondDevice);
	}
	
	public void updateDeviceList2nd(DeviceList2nd secondDevice){
		Date updateTime = new Date();
		secondDevice.setDlsUpdateTime(updateTime);
		deviceList2ndDAO.update(secondDevice);
	}
	
	public DeviceList2nd getDeviceList2ndById(Integer id){		
		return deviceList2ndDAO.findById(id);
	}
	
	public void saveDeviceListDetail(DeviceListDetail detailDevice){
		Date createTime = new Date();
		detailDevice.setDldCreateTime(createTime);
		deviceListDetailDAO.save(detailDevice);
	}
	
	public void updateDeviceListDetail(DeviceListDetail detailDevice){
		Date updateTime = new Date();
		detailDevice.setDldUpdateTime(updateTime);
		deviceListDetailDAO.update(detailDevice);
	}
	
	public DeviceListDetail findDeviceListDetailById(Integer id){
		return deviceListDetailDAO.findById(id);
	}
	
	public BudgetItem getBudgetItemById(Serializable id) {
		return budgetItemDAO.findById((Integer) id);
	}

	
	public DeviceList1stDAO getDeviceList1stDAO() {
		return deviceList1stDAO;
	}

	public void setDeviceList1stDAO(DeviceList1stDAO deviceList1stDAO) {
		this.deviceList1stDAO = deviceList1stDAO;
	}

	public DeviceListDetailDAO getDeviceListDetailDAO() {
		return deviceListDetailDAO;
	}

	public void setDeviceListDetailDAO(DeviceListDetailDAO deviceListDetailDAO) {
		this.deviceListDetailDAO = deviceListDetailDAO;
	}

	public TenderProjectDAO getTenderProjectDAO() {
		return tenderProjectDAO;
	}

	public void setTenderProjectDAO(TenderProjectDAO tenderProjectDAO) {
		this.tenderProjectDAO = tenderProjectDAO;
	}
	public DeviceList2ndDAO getDeviceList2ndDAO() {
		return deviceList2ndDAO;
	}

	public void setDeviceList2ndDAO(DeviceList2ndDAO deviceList2ndDAO) {
		this.deviceList2ndDAO = deviceList2ndDAO;
	}
	
	public ProjectAttachmentDAO getProjectAttachmentDAO() {
		return projectAttachmentDAO;
	}

	public void setProjectAttachmentDAO(ProjectAttachmentDAO projectAttachmentDAO) {
		this.projectAttachmentDAO = projectAttachmentDAO;
	}

	
	public List<ProjectAttachment> searchAttachment(Object[] args) {
		ProjectAttachment searchInfo = (ProjectAttachment) args[0];
		String page = (String) args[1];

		String hqlStr = "from ProjectAttachment a where a.paFlag != "
				+ CommonService.DELETE_FLAG;

		if (searchInfo.getPaTenderProject() != null && searchInfo.getPaTenderProject() != 0) {
			hqlStr += " and a.paTenderProject = " + searchInfo.getPaTenderProject();
		}
		
		hqlStr += (StringUtils.isNotEmpty(searchInfo.getPaCode()) ? " and a.paCode like '%"
				+ searchInfo.getPaCode() + "%'"
				: "");

		hqlStr += (StringUtils.isNotEmpty(searchInfo.getPaName()) ? " and a.paName like '%"
				+ searchInfo.getPaName() + "%'"
				: "");
	
		if (searchInfo.getPaAttachmentCategory() != null
				&& searchInfo.getPaAttachmentCategory() != 0) {
			hqlStr += " and a.paAttachmentCategory = "
					+ searchInfo.getPaAttachmentCategory();
		}
	
		hqlStr += (StringUtils.isNotEmpty(searchInfo.getPaSubmitDate()) ? " and a.paSubmitDate = '"
				+ searchInfo.getPaSubmitDate() + "'"
				: "");

		MyQuery myQuery = new MyQuery();

		if (StringUtils.isNumeric(page)) {
			myQuery.setPageStartNo(Integer.parseInt(page));
		} else {
			myQuery.setPageStartNo(0);
		}
		myQuery.setOrderby(" order by a.paCode");
		myQuery.setQueryString(hqlStr);

		myQuery.setOffset(true);

		return (ArrayList<ProjectAttachment>) projectAttachmentDAO.find(myQuery);

	}

	public int searchAttachmentCount(Object[] args) {
		ProjectAttachment searchInfo = (ProjectAttachment) args[0];

		String hqlStr = "select count(*) from ProjectAttachment a where a.paFlag != "
				+ CommonService.DELETE_FLAG;

		if (searchInfo.getPaTenderProject() != null && searchInfo.getPaTenderProject() != 0) {
			hqlStr += " and a.paTenderProject = " + searchInfo.getPaTenderProject();
		}
		
		hqlStr += (StringUtils.isNotEmpty(searchInfo.getPaCode()) ? " and a.paCode like '%"
				+ searchInfo.getPaCode() + "%'"
				: "");

		hqlStr += (StringUtils.isNotEmpty(searchInfo.getPaName()) ? " and a.paName like '%"
				+ searchInfo.getPaName() + "%'"
				: "");
		
		if (searchInfo.getPaAttachmentCategory() != null
				&& searchInfo.getPaAttachmentCategory() != 0) {
			hqlStr += " and a.paAttachmentCategory = "
					+ searchInfo.getPaAttachmentCategory();
		}
	
		hqlStr += (StringUtils.isNotEmpty(searchInfo.getPaSubmitDate()) ? " and a.paSubmitDate = '"
				+ searchInfo.getPaSubmitDate() + "'"
				: "");

		MyQuery myQuery = new MyQuery();

		myQuery.setQueryString(hqlStr);

		return projectAttachmentDAO.getFindCount(myQuery);

	}
	
	public ProjectAttachment getProjectAttachmentById(Integer id){
		return projectAttachmentDAO.get(id);
	}

	public void setBudgetItemDAO(BudgetItemDAO budgetItemDAO) {
		this.budgetItemDAO = budgetItemDAO;
	}

	public BudgetItemDAO getBudgetItemDAO() {
		return budgetItemDAO;
	}

	public void setBudgetItemModelDAO(BudgetItemModelDAO budgetItemModelDAO) {
		this.budgetItemModelDAO = budgetItemModelDAO;
	}

	public BudgetItemModelDAO getBudgetItemModelDAO() {
		return budgetItemModelDAO;
	}

	public void setProjectBudgetDAO(ProjectBudgetDAO projectBudgetDAO) {
		this.projectBudgetDAO = projectBudgetDAO;
	}

	public ProjectBudgetDAO getProjectBudgetDAO() {
		return projectBudgetDAO;
	}

	@Override
	public void saveProjectBudget(ProjectBudget projectBudget) {
		// TODO Auto-generated method stub
		projectBudgetDAO.save(projectBudget);
	}
	
	@Override
	public void updateProjectBudget(ProjectBudget projectBudget) {
		// TODO Auto-generated method stub
		projectBudgetDAO.update(projectBudget);
	}

	@Override
	public void saveBudgetItem(BudgetItem budgetItem) {
		// TODO Auto-generated method stub
		if(budgetItem.getBiId() == null)
		{
			budgetItemDAO.save(budgetItem);
		}
		else
		{
			budgetItemDAO.update(budgetItem);
		}
		
	}
	
	@Override
	public List<CheckTask> getAllStepCheckTask(TenderProject project,String type) {
		// TODO Auto-generated method stub
		String hqlStr = "from CheckTask a where a.ctProject = " + project.getTpId() + " and a.ctTaskType = " + type;			
				      	    			
		MyQuery myQuery = new MyQuery();
			
	    myQuery.setOrderby(" order by a.ctId");
	    myQuery.setQueryString(hqlStr);
		    
	    return checkTaskDAO.find(myQuery);
	}


}
