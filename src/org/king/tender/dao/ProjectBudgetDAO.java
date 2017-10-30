package org.king.tender.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;
import org.king.tender.bean.ProjectBudget;

/**
 * Data access object (DAO) for domain model class ProjectBudget.
 * @see org.king.tender.bean.ProjectBudget
 * @author MyEclipse - Hibernate Tools
 */
public class ProjectBudgetDAO implements DAO {

	private static final Log log = LogFactory.getLog(ProjectBudgetDAO.class);

	private BaseDAO baseDAO;

	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public void save(ProjectBudget transientInstance) {
		log.debug("saving ProjectBudget instance");
		baseDAO.saveEntity(transientInstance);
	}

	public void update(ProjectBudget transientInstance) {
		log.debug("update ProjectBudget instance");
		baseDAO.updateEntity(transientInstance);
	}

	public void delete(ProjectBudget persistentInstance) {
		log.debug("deleting ProjectBudget instance");
		baseDAO.removeEntity(persistentInstance);
	}

	public ProjectBudget findById(java.lang.Integer id) {
		log.debug("getting ProjectBudget instance with id: " + id);
		return (ProjectBudget) baseDAO.getEntity(
				ProjectBudget.class, id);
	}

	public List<ProjectBudget> find(MyQuery myQuery) {
		log.debug("find ProjectBudget instances by MyQuery");
		return baseDAO.findEntity(myQuery);
	}

	public List<ProjectBudget> find(String query) {
		log.debug("find ProjectBudget instances by query");
		return baseDAO.findEntity(query);
	}
}