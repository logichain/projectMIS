package org.king.projectmanage.dao;

import java.io.Serializable;
import java.util.List;

import org.king.common.service.CommonService;
import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;
import org.king.projectmanage.bean.ProjectScheduleStage;

public class ProjectScheduleStageDAO implements DAO {
	private BaseDAO baseDAO;
	
	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	public void delete(ProjectScheduleStage persistentInstance) {
		// TODO Auto-generated method stub
//		this.delete(persistentInstance);
		persistentInstance.setPssFlag(CommonService.DELETE_FLAG);
		this.update(persistentInstance);
	}

	public List<ProjectScheduleStage> find(MyQuery myQuery) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(myQuery);
	}

	public List<ProjectScheduleStage> find(String query) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(query);
	}

	public ProjectScheduleStage get(Serializable id) {
		// TODO Auto-generated method stub
		return (ProjectScheduleStage) baseDAO.getEntity(ProjectScheduleStage.class,id);
	}

	
	public void save(ProjectScheduleStage transientInstance) {
		// TODO Auto-generated method stub
		baseDAO.saveEntity(transientInstance);
	}

	public void update(ProjectScheduleStage transientInstance) {
		// TODO Auto-generated method stub
		baseDAO.updateEntity(transientInstance);
	}
	
}
