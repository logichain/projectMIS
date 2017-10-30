package org.king.tender.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;
import org.king.tender.bean.BudgetItem;

/**
 * Data access object (DAO) for domain model class BudgetItem.
 * 
 * @see org.king.tender.bean.BudgetItem
 * @author MyEclipse - Hibernate Tools
 */
public class BudgetItemDAO implements DAO {

	private static final Log log = LogFactory.getLog(BudgetItemDAO.class);

	private BaseDAO baseDAO;

	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public void save(BudgetItem transientInstance) {
		log.debug("saving BudgetItem instance");
		baseDAO.saveEntity(transientInstance);
	}

	public void update(BudgetItem transientInstance) {
		log.debug("update BudgetItem instance");
		baseDAO.updateEntity(transientInstance);
	}

	public void delete(BudgetItem persistentInstance) {
		log.debug("deleting BudgetItem instance");
		baseDAO.removeEntity(persistentInstance);
	}

	public BudgetItem findById(java.lang.Integer id) {
		log.debug("getting BudgetItem instance with id: " + id);
		return (BudgetItem) baseDAO.getEntity(BudgetItem.class, id);
	}

	public List<BudgetItem> find(MyQuery myQuery) {
		log.debug("find BudgetItem instances by MyQuery");
		return baseDAO.findEntity(myQuery);
	}

	public List<BudgetItem> find(String query) {
		log.debug("find BudgetItem instances by query");
		return baseDAO.findEntity(query);
	}
}