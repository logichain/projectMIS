package org.king.tender.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;
import org.king.tender.bean.DeviceList1st;

/**
 * Data access object (DAO) for domain model class DeviceList1st.
 * 
 * @see org.king.tender.bean.DeviceList1st
 * @author MyEclipse - Hibernate Tools
 */
public class DeviceList1stDAO implements DAO {

	private static final Log log = LogFactory.getLog(DeviceList1stDAO.class);

	private BaseDAO baseDAO;

	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public void save(DeviceList1st transientInstance) {
		log.debug("saving DeviceList1st instance");
		this.baseDAO.saveEntity(transientInstance);
	}

	public void update(DeviceList1st transientInstance) {
		log.debug("update DeviceList1st instance");
		baseDAO.updateEntity(transientInstance);
	}

	public void delete(DeviceList1st persistentInstance) {
		log.debug("deleting DeviceList1st instance");
		baseDAO.removeEntity(persistentInstance);
	}

	public DeviceList1st findById(java.lang.Integer id) {
		log.debug("getting DeviceList1st instance with id: " + id);
		return (DeviceList1st) baseDAO.getEntity(DeviceList1st.class, id);
	}

	public List<DeviceList1st> find(MyQuery myQuery) {
		log.debug("find DeviceList1st instances by MyQuery");
		return baseDAO.findEntity(myQuery);
	}

	public List<DeviceList1st> find(String query) {
		log.debug("find DeviceList1st instances by query");
		return baseDAO.findEntity(query);
	}
}