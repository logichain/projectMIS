package org.king.dossier.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.king.common.attachmentcategory.AttachmentCategory;
import org.king.common.service.CommonService;
import org.king.common.service.impl.CommonServiceImpl;
import org.king.department.bean.Department;
import org.king.department.dao.DepartmentDAO;
import org.king.dossier.bean.DocumentStatistics;
import org.king.dossier.service.DossierManageService;
import org.king.framework.dao.MyQuery;
import org.king.framework.service.impl.BaseService;
import org.king.projectmanage.bean.ProjectAttachment;
import org.king.projectmanage.dao.ProjectAttachmentDAO;
import org.king.security.domain.Account;
import org.king.utils.FileUtil;

public class DossierManageServiceImpl extends BaseService implements DossierManageService {
	private ProjectAttachmentDAO projectAttachmentDAO; 
	private DepartmentDAO departmentDAO;
		
	public ProjectAttachment createProjectAttachment() {
		// TODO Auto-generated method stub
		ProjectAttachment rtn = new ProjectAttachment();
		rtn.setPaFlag(CommonService.NORMAL_FLAG);
		rtn.setPaCreateTime(new Date());
		
		return rtn;
	}
		
	public void saveProjectDocument(ProjectAttachment pa,String uploadPath) {
		// TODO Auto-generated method stub
		Date createTime = new Date();		
		
		if(pa.getPaId() == null)
		{
			String fileName = FileUtil.saveUploadFile(pa.getAttachmentFile(),uploadPath);
			
			pa.setPaCreateTime(createTime);
			pa.setPaUrl(uploadPath + "\\" + fileName);
			
			int count = this.getProjectAttachmentCountByCategory(pa.getPaAttachmentCategory());					
			pa.setPaCode(CommonServiceImpl.getProjectAttachmentCode(pa.getPaAttachmentCategory(),count));
			
			projectAttachmentDAO.save(pa);			
		}	
		else
		{
			if(!pa.getAttachmentFile().getFileName().isEmpty())
			{
				String fileName = FileUtil.saveUploadFile(pa.getAttachmentFile(),uploadPath);					
				pa.setPaUrl(uploadPath + "\\" + fileName);
			}
			
			projectAttachmentDAO.update(pa);			
		}
		
	}


	public ProjectAttachment getProjectAttachmentById(Integer id) {
		// TODO Auto-generated method stub
		return projectAttachmentDAO.get(id);
	}


	public ArrayList<ProjectAttachment> searchDocument(Object[] args) {
		// TODO Auto-generated method stub
		ProjectAttachment searchInfo = (ProjectAttachment) args[0];
    	String page = (String) args[1];
    	Account user = (Account) args[2];
    	
    	String hqlStr = "from ProjectAttachment a where a.paFlag != " + CommonService.DELETE_FLAG;			
				
		hqlStr +=  " and a.paInputInterface != " + CommonService.INPUT_INTERFACE_CONTRACT;
    	      	
    	hqlStr += (StringUtils.isNotEmpty(searchInfo.getPaCode())?" and a.paCode like '%" + searchInfo.getPaCode() + "%'" : "");
    	
    	hqlStr += (StringUtils.isNotEmpty(searchInfo.getPaName())?" and a.paName like '%" + searchInfo.getPaName() + "%'" : "");
    	
    	
    	if(searchInfo.getPaAttachmentCategory() != null && searchInfo.getPaAttachmentCategory() != 0)
    	{
    		hqlStr += " and a.paAttachmentCategory = " + searchInfo.getPaAttachmentCategory();
    	}
    	if(searchInfo.getPaSubmitDepartment() != null && searchInfo.getPaSubmitDepartment() != 0)
    	{
    		hqlStr += " and a.paSubmitDepartment = " + searchInfo.getPaSubmitDepartment();
    	}
    	
    	hqlStr += (StringUtils.isNotEmpty(searchInfo.getPaSubmitDate())?" and a.paSubmitDate = '" + searchInfo.getPaSubmitDate() + "'" : "");
    	
    	MyQuery myQuery = new MyQuery();
    	myQuery.setPageSize(searchInfo.getPageItemCount());
        if (StringUtils.isNumeric(page)) {
        	myQuery.setPageStartNo(Integer.parseInt(page));
        }else {
        	myQuery.setPageStartNo(0);
        }
        
        if(searchInfo.getOrderColumn().isEmpty())
        {
        	myQuery.setOrderby(" order by a.paSubmitDate desc");
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
        
		return (ArrayList<ProjectAttachment>) projectAttachmentDAO.find(myQuery);
	}


	public Integer searchDocumentCount(Object[] args) {
		// TODO Auto-generated method stub
		ProjectAttachment searchInfo = (ProjectAttachment) args[0];    	        
		Account user = (Account) args[2];
    	
    	String hqlStr =  "select count(*) from ProjectAttachment a where a.paFlag != " + CommonService.DELETE_FLAG;
				
		hqlStr +=  " and a.paInputInterface != " + CommonService.INPUT_INTERFACE_CONTRACT;
		
    	hqlStr += (StringUtils.isNotEmpty(searchInfo.getPaCode())?" and a.paCode like '%" + searchInfo.getPaCode() + "%'" : "");
    	
    	hqlStr += (StringUtils.isNotEmpty(searchInfo.getPaName())?" and a.paName like '%" + searchInfo.getPaName() + "%'" : "");
    	
    
    	if(searchInfo.getPaAttachmentCategory() != null && searchInfo.getPaAttachmentCategory() != 0)
    	{
    		hqlStr += " and a.paAttachmentCategory = " + searchInfo.getPaAttachmentCategory();
    	}
    	if(searchInfo.getPaSubmitDepartment() != null && searchInfo.getPaSubmitDepartment() != 0)
    	{
    		hqlStr += " and a.paSubmitDepartment = " + searchInfo.getPaSubmitDepartment();
    	}
    	
    	hqlStr += (StringUtils.isNotEmpty(searchInfo.getPaSubmitDate())?" and a.paSubmitDate = '" + searchInfo.getPaSubmitDate() + "'" : "");
    	
    	MyQuery myQuery = new MyQuery();
              
        myQuery.setQueryString(hqlStr);       
        
		return projectAttachmentDAO.getFindCount(myQuery);
	}

	private String getDeptsManagedByViceGeneralManager(String viceGeneralManager)
	{
		String rtn = "(";
		
		String sql = "from Department a where a.DViceGeneralManager = '" + viceGeneralManager + "'";
		List<Department> dList = departmentDAO.find(sql);
		
		for(Department d:dList)
		{
			rtn += d.getDId() + ",";
		}
		
		if(rtn.indexOf(",") > 0)
		{
			rtn = rtn.substring(0,rtn.length() -1) + ")";
		}
		else
		{
			rtn = null;
		}
		
		return rtn;
	}

	public void setProjectAttachmentDAO(ProjectAttachmentDAO projectAttachmentDAO) {
		this.projectAttachmentDAO = projectAttachmentDAO;
	}


	public ProjectAttachmentDAO getProjectAttachmentDAO() {
		return projectAttachmentDAO;
	}


	public void deleteProjectAttachment(ProjectAttachment pa) {
		// TODO Auto-generated method stub
		pa.setPaFlag(CommonService.DELETE_FLAG);
		projectAttachmentDAO.update(pa);
	}


	@Override
	public ArrayList<DocumentStatistics> calculateDocumentStatistics() {
		// TODO Auto-generated method stub
		ArrayList<DocumentStatistics> rtn = new ArrayList<DocumentStatistics>();
		
		String hqlStr = "select a.category,count(a.paId) from ProjectAttachment a where a.paFlag != " + CommonService.DELETE_FLAG
			+ " group by a.category";
		
		List<Object> list  = projectAttachmentDAO.getBaseDAO().findEntity(hqlStr);
		for(Object o:list)
		{
			DocumentStatistics ds = new DocumentStatistics();
			ds.setDocumentCategory((AttachmentCategory)((Object[])o)[0]);
			ds.setDocumentCount((Integer)((Object[])o)[1]);
			
			rtn.add(ds);
		}		
		
		return rtn;
	}
	
	private int getProjectAttachmentCountByCategory(int category)
	{				
		String hqlStr = "select count(a) from ProjectAttachment a where a.paAttachmentCategory = " + category;			
				
    	MyQuery myQuery = new MyQuery();
        myQuery.setQueryString(hqlStr);
            
        return projectAttachmentDAO.getFindCount(myQuery);	
	}
	
	public DepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}


	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}
}
