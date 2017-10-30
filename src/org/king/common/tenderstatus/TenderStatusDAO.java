package org.king.common.tenderstatus;

import java.io.Serializable;
import java.util.List;

import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;

public class TenderStatusDAO implements DAO {
	private BaseDAO baseDAO;
	
	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	public void delete(TenderStatus persistentInstance) {
		// TODO Auto-generated method stub
//		this.delete(persistentInstance);
	}

	public List<TenderStatus> find(MyQuery myQuery) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(myQuery);
	}

	public List<TenderStatus> find(String query) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(query);
	}

	public TenderStatus get(Serializable id) {
		// TODO Auto-generated method stub
		return (TenderStatus) baseDAO.getEntity(TenderStatus.class,id);
	}

	
	public void save(TenderStatus transientInstance) {
		// TODO Auto-generated method stub
//		baseDAO.saveEntity(transientInstance);
	}

	public void update(TenderStatus transientInstance) {
		// TODO Auto-generated method stub
//		baseDAO.updateEntity(transientInstance);
	}
	
}
