package org.king.common.customernature;

import java.io.Serializable;
import java.util.List;

import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;

public class CustomerNatureDAO implements DAO {
	private BaseDAO baseDAO;
	
	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	public void delete(CustomerNature persistentInstance) {
		// TODO Auto-generated method stub
//		this.delete(persistentInstance);
	}

	public List<CustomerNature> find(MyQuery myQuery) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(myQuery);
	}

	public List<CustomerNature> find(String query) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(query);
	}

	public CustomerNature get(Serializable id) {
		// TODO Auto-generated method stub
		return (CustomerNature) baseDAO.getEntity(CustomerNature.class,id);
	}

	
	public void save(CustomerNature transientInstance) {
		// TODO Auto-generated method stub
//		baseDAO.saveEntity(transientInstance);
	}

	public void update(CustomerNature transientInstance) {
		// TODO Auto-generated method stub
//		baseDAO.updateEntity(transientInstance);
	}
	
}
