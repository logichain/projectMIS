package org.king.projectmanage.dao;

import java.io.Serializable;
import java.util.List;

import org.king.common.service.CommonService;
import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;
import org.king.projectmanage.bean.TaskChangeRecord;

public class TaskChangeRecordDAO implements DAO {
	private BaseDAO baseDAO;
	
	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	public void delete(TaskChangeRecord persistentInstance) {
		// TODO Auto-generated method stub
//		this.delete(persistentInstance);
		persistentInstance.setTcrFlag(CommonService.DELETE_FLAG);
		this.update(persistentInstance);
	}

	public List<TaskChangeRecord> find(MyQuery myQuery) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(myQuery);
	}

	public List<TaskChangeRecord> find(String query) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(query);
	}

	public TaskChangeRecord get(Serializable id) {
		// TODO Auto-generated method stub
		return (TaskChangeRecord) baseDAO.getEntity(TaskChangeRecord.class,id);
	}

	
	public void save(TaskChangeRecord transientInstance) {
		// TODO Auto-generated method stub
		baseDAO.saveEntity(transientInstance);
	}

	public void update(TaskChangeRecord transientInstance) {
		// TODO Auto-generated method stub
		baseDAO.updateEntity(transientInstance);
	}
	
}
