package org.king.projectmanage.dao;

import java.io.Serializable;
import java.util.List;

import org.king.common.service.CommonService;
import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;
import org.king.projectmanage.bean.ProjectScheduleChangeRecord;

public class ProjectScheduleChangeRecordDAO implements DAO {
	private BaseDAO baseDAO;
	
	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	public void delete(ProjectScheduleChangeRecord persistentInstance) {
		// TODO Auto-generated method stub
//		this.delete(persistentInstance);
		persistentInstance.setPscrFlag(CommonService.DELETE_FLAG);
		this.update(persistentInstance);
	}

	public List<ProjectScheduleChangeRecord> find(MyQuery myQuery) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(myQuery);
	}

	public List<ProjectScheduleChangeRecord> find(String query) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(query);
	}

	public ProjectScheduleChangeRecord get(Serializable id) {
		// TODO Auto-generated method stub
		return (ProjectScheduleChangeRecord) baseDAO.getEntity(ProjectScheduleChangeRecord.class,id);
	}

	
	public void save(ProjectScheduleChangeRecord transientInstance) {
		// TODO Auto-generated method stub
		baseDAO.saveEntity(transientInstance);
	}

	public void update(ProjectScheduleChangeRecord transientInstance) {
		// TODO Auto-generated method stub
		baseDAO.updateEntity(transientInstance);
	}
	
}
