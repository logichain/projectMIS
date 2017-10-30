package org.king.common.tenderrole;

import java.io.Serializable;
import java.util.List;

import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;

public class TenderRoleDAO implements DAO {
	private BaseDAO baseDAO;
	
	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	public void delete(TenderRole persistentInstance) {
		// TODO Auto-generated method stub
//		this.delete(persistentInstance);
	}

	public List<TenderRole> find(MyQuery myQuery) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(myQuery);
	}

	public List<TenderRole> find(String query) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(query);
	}

	public TenderRole get(Serializable id) {
		// TODO Auto-generated method stub
		return (TenderRole) baseDAO.getEntity(TenderRole.class,id);
	}

	
	public void save(TenderRole transientInstance) {
		// TODO Auto-generated method stub
//		baseDAO.saveEntity(transientInstance);
	}

	public void update(TenderRole transientInstance) {
		// TODO Auto-generated method stub
//		baseDAO.updateEntity(transientInstance);
	}
	
}
