package org.king.tender.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;
import org.king.tender.bean.BudgetItemModel;

/**
 * Data access object (DAO) for domain model class BudgetItemModel.
 * @see org.king.tender.bean.BudgetItemModel
 * @author MyEclipse - Hibernate Tools
 */
public class BudgetItemModelDAO implements DAO {

	private static final Log log = LogFactory
			.getLog(BudgetItemModelDAO.class);

	private BaseDAO baseDAO;

	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public void save(BudgetItemModel transientInstance) {
		log.debug("saving BudgetItemModel instance");
		baseDAO.saveEntity(transientInstance);
	}

	public void update(BudgetItemModel transientInstance) {
		log.debug("update BudgetItemModel instance");
		baseDAO.updateEntity(transientInstance);
	}

	public void delete(BudgetItemModel persistentInstance) {
		log.debug("deleting BudgetItemModel instance");
		baseDAO.removeEntity(persistentInstance);
	}

	public BudgetItemModel findById(java.lang.Integer id) {
		log.debug("getting BudgetItemModel instance with id: " + id);
		return (BudgetItemModel) baseDAO.getEntity(
				BudgetItemModel.class, id);
	}

	public List<BudgetItemModel> find(MyQuery myQuery) {
		log.debug("find BudgetItemModel instances by MyQuery");
		return baseDAO.findEntity(myQuery);
	}

	public List<BudgetItemModel> find(String query) {
		log.debug("find BudgetItemModel instances by query");
		return baseDAO.findEntity(query);
	}
}