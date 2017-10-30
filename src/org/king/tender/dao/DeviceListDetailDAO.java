package org.king.tender.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;
import org.king.tender.bean.DeviceListDetail;

/**
 * Data access object (DAO) for domain model class DeviceListDetail.
 * @see org.king.tender.bean.DeviceListDetail
 * @author MyEclipse - Hibernate Tools
 */
public class DeviceListDetailDAO implements DAO {

    private static final Log log = LogFactory.getLog(DeviceListDetailDAO.class);

    private BaseDAO baseDAO;

	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

    
    public void save(DeviceListDetail transientInstance) {
        log.debug("saving DeviceListDetail instance");
        baseDAO.saveEntity(transientInstance);
    }
    
    public void update(DeviceListDetail transientInstance) {
		log.debug("update DeviceListDetail instance");
		baseDAO.updateEntity(transientInstance);
	}
    
	public void delete(DeviceListDetail persistentInstance) {
        log.debug("deleting DeviceListDetail instance");
        baseDAO.removeEntity(persistentInstance);
    }
    
    public DeviceListDetail findById( java.lang.Integer id) {
        log.debug("getting DeviceListDetail instance with id: " + id);
        return (DeviceListDetail)baseDAO.getEntity(DeviceListDetail.class, id);
    }
    
    public List<DeviceListDetail> find(MyQuery myQuery) {
		log.debug("find DeviceListDetail instances by MyQuery");
		return baseDAO.findEntity(myQuery);
	}

	public List<DeviceListDetail> find(String query) {
		log.debug("find DeviceListDetail instances by query");
		return baseDAO.findEntity(query);
	}
}