package org.king.common.jobresponsibility;

import java.io.Serializable;
import java.util.List;

import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;

public class JobResponsibilityDAO implements DAO {
	private BaseDAO baseDAO;
	
	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	public void delete(JobResponsibility persistentInstance) {
		// TODO Auto-generated method stub
//		this.delete(persistentInstance);
	}

	public List<JobResponsibility> find(MyQuery myQuery) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(myQuery);
	}

	public List<JobResponsibility> find(String query) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(query);
	}

	public JobResponsibility get(Serializable id) {
		// TODO Auto-generated method stub
		return (JobResponsibility) baseDAO.getEntity(JobResponsibility.class,id);
	}

	
	public void save(JobResponsibility transientInstance) {
		// TODO Auto-generated method stub
//		baseDAO.saveEntity(transientInstance);
	}

	public void update(JobResponsibility transientInstance) {
		// TODO Auto-generated method stub
//		baseDAO.updateEntity(transientInstance);
	}
	
}
