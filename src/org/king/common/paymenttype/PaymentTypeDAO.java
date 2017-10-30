package org.king.common.paymenttype;

import java.io.Serializable;
import java.util.List;

import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;

public class PaymentTypeDAO implements DAO {
private BaseDAO baseDAO;
	
	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	public void delete(PaymentType persistentInstance) {
//		this.delete(persistentInstance);
	}

	public List<PaymentType> find(MyQuery myQuery) {
		return baseDAO.findEntity(myQuery);
	}

	public List<PaymentType> find(String query) {
		return baseDAO.findEntity(query);
	}

	public PaymentType get(Serializable id) {
		// TODO Auto-generated method stub
		return (PaymentType) baseDAO.getEntity(PaymentType.class,id);
	}

	
	public void save(PaymentType transientInstance) {
		// TODO Auto-generated method stub
//		baseDAO.saveEntity(transientInstance);
	}

	public void update(PaymentType transientInstance) {
		// TODO Auto-generated method stub
//		baseDAO.updateEntity(transientInstance);
	}

}
