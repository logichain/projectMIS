package org.king.accountmanage.dao;

import java.io.Serializable;
import java.util.List;

import org.king.accountmanage.bean.AccountFeeTax;
import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;

public class AccountFeeTaxDAO implements DAO {
	private BaseDAO baseDAO;
	
	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	public void delete(AccountFeeTax persistentInstance) {
		// TODO Auto-generated method stub
//		this.delete(persistentInstance);
	}

	public List<AccountFeeTax> find(MyQuery myQuery) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(myQuery);
	}

	public List<AccountFeeTax> find(String query) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(query);
	}

	public AccountFeeTax get(Serializable id) {
		// TODO Auto-generated method stub
		return (AccountFeeTax) baseDAO.getEntity(AccountFeeTax.class,id);
	}

	
	public void save(AccountFeeTax transientInstance) {
		// TODO Auto-generated method stub
		baseDAO.saveEntity(transientInstance);
	}

	public void update(AccountFeeTax transientInstance) {
		// TODO Auto-generated method stub
		baseDAO.updateEntity(transientInstance);
	}
	
}
