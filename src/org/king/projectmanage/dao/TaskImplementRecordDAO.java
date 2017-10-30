package org.king.projectmanage.dao;

import java.io.Serializable;
import java.util.List;

import org.king.common.service.CommonService;
import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;
import org.king.projectmanage.bean.TaskImplementRecord;

public class TaskImplementRecordDAO implements DAO {
	private BaseDAO baseDAO;
	
	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	public void delete(TaskImplementRecord persistentInstance) {
		// TODO Auto-generated method stub
//		this.delete(persistentInstance);
		persistentInstance.setTirFlag(CommonService.DELETE_FLAG);
		this.update(persistentInstance);
	}

	public List<TaskImplementRecord> find(MyQuery myQuery) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(myQuery);
	}

	public List<TaskImplementRecord> find(String query) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(query);
	}

	public TaskImplementRecord get(Serializable id) {
		// TODO Auto-generated method stub
		return (TaskImplementRecord) baseDAO.getEntity(TaskImplementRecord.class,id);
	}

	
	public void save(TaskImplementRecord transientInstance) {
		// TODO Auto-generated method stub
		baseDAO.saveEntity(transientInstance);
	}

	public void update(TaskImplementRecord transientInstance) {
		// TODO Auto-generated method stub
		baseDAO.updateEntity(transientInstance);
	}
	
}
