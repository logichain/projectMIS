package org.king.accountmanage.dao;

import java.io.Serializable;
import java.util.List;

import org.king.accountmanage.bean.AccountSaleContract;
import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;

public class AccountSaleContractDAO implements DAO {
	private BaseDAO baseDAO;
	
	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	public void delete(AccountSaleContract persistentInstance) {
		// TODO Auto-generated method stub
//		this.delete(persistentInstance);
	}

	public List<AccountSaleContract> find(MyQuery myQuery) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(myQuery);
	}

	public List<AccountSaleContract> find(String query) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(query);
	}

	public AccountSaleContract get(Serializable id) {
		// TODO Auto-generated method stub
		return (AccountSaleContract) baseDAO.getEntity(AccountSaleContract.class,id);
	}

	
	public void save(AccountSaleContract transientInstance) {
		// TODO Auto-generated method stub
		baseDAO.saveEntity(transientInstance);
	}

	public void update(AccountSaleContract transientInstance) {
		// TODO Auto-generated method stub
		baseDAO.updateEntity(transientInstance);
	}
	
}
