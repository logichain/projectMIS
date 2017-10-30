package org.king.tender.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;
import org.king.tender.bean.DeviceList2nd;

/**
 * Data access object (DAO) for domain model class DeviceList2nd.
 * @see org.king.tender.bean.DeviceList2nd
 * @author MyEclipse - Hibernate Tools
 */
public class DeviceList2ndDAO implements DAO {

	private static final Log log = LogFactory.getLog(DeviceList2ndDAO.class);

	private BaseDAO baseDAO;

	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public void save(DeviceList2nd transientInstance) {
		log.debug("saving DeviceList2nd instance");
		baseDAO.saveEntity(transientInstance);
	}

	public void update(DeviceList2nd transientInstance) {
		log.debug("update DeviceList2nd instance");
		baseDAO.updateEntity(transientInstance);
	}

	public void delete(DeviceList2nd persistentInstance) {
		log.debug("deleting DeviceList2nd instance");
		baseDAO.removeEntity(persistentInstance);
	}

	public DeviceList2nd findById(java.lang.Integer id) {
		log.debug("getting DeviceList2nd instance with id: " + id);
		return (DeviceList2nd) baseDAO.getEntity(DeviceList2nd.class, id);
	}

	public List<DeviceList2nd> find(MyQuery myQuery) {
		log.debug("find DeviceList2nd instances by MyQuery");
		return baseDAO.findEntity(myQuery);
	}

	public List<DeviceList2nd> find(String query) {
		log.debug("find DeviceList2nd instances by query");
		return baseDAO.findEntity(query);
	}
}