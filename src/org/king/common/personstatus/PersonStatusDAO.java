package org.king.common.personstatus;

import java.io.Serializable;
import java.util.List;

import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;

public class PersonStatusDAO implements DAO {
	private BaseDAO baseDAO;
	
	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	public void delete(PersonStatus persistentInstance) {
		// TODO Auto-generated method stub
//		this.delete(persistentInstance);
	}

	public List<PersonStatus> find(MyQuery myQuery) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(myQuery);
	}

	public List<PersonStatus> find(String query) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(query);
	}

	public PersonStatus get(Serializable id) {
		// TODO Auto-generated method stub
		return (PersonStatus) baseDAO.getEntity(PersonStatus.class,id);
	}

	
	public void save(PersonStatus transientInstance) {
		// TODO Auto-generated method stub
//		baseDAO.saveEntity(transientInstance);
	}

	public void update(PersonStatus transientInstance) {
		// TODO Auto-generated method stub
//		baseDAO.updateEntity(transientInstance);
	}
	
}
