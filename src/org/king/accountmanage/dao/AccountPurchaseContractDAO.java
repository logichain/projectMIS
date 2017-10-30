package org.king.accountmanage.dao;

import java.io.Serializable;
import java.util.List;

import org.king.accountmanage.bean.AccountPurchaseContract;
import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;

public class AccountPurchaseContractDAO implements DAO {
	private BaseDAO baseDAO;
	
	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	public void delete(AccountPurchaseContract persistentInstance) {
		// TODO Auto-generated method stub
//		this.delete(persistentInstance);
	}

	public List<AccountPurchaseContract> find(MyQuery myQuery) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(myQuery);
	}

	public List<AccountPurchaseContract> find(String query) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(query);
	}

	public AccountPurchaseContract get(Serializable id) {
		// TODO Auto-generated method stub
		return (AccountPurchaseContract) baseDAO.getEntity(AccountPurchaseContract.class,id);
	}

	
	public void save(AccountPurchaseContract transientInstance) {
		// TODO Auto-generated method stub
		baseDAO.saveEntity(transientInstance);
	}

	public void update(AccountPurchaseContract transientInstance) {
		// TODO Auto-generated method stub
		baseDAO.updateEntity(transientInstance);
	}
	
}
