package org.king.common.politicalstatus;

import java.io.Serializable;
import java.util.List;

import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;

public class PoliticalStatusDAO implements DAO {
	private BaseDAO baseDAO;
	
	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	public void delete(PoliticalStatus persistentInstance) {
		// TODO Auto-generated method stub
//		this.delete(persistentInstance);
	}

	public List<PoliticalStatus> find(MyQuery myQuery) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(myQuery);
	}

	public List<PoliticalStatus> find(String query) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(query);
	}

	public PoliticalStatus get(Serializable id) {
		// TODO Auto-generated method stub
		return (PoliticalStatus) baseDAO.getEntity(PoliticalStatus.class,id);
	}

	
	public void save(PoliticalStatus transientInstance) {
		// TODO Auto-generated method stub
//		baseDAO.saveEntity(transientInstance);
	}

	public void update(PoliticalStatus transientInstance) {
		// TODO Auto-generated method stub
//		baseDAO.updateEntity(transientInstance);
	}
	
}
