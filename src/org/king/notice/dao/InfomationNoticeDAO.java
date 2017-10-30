package org.king.notice.dao;

import java.io.Serializable;
import java.util.List;

import org.king.common.service.CommonService;
import org.king.customer.bean.Customer;
import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;
import org.king.notice.bean.InfomationNotice;

public class InfomationNoticeDAO implements DAO {
	private BaseDAO baseDAO;
	
	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	public void delete(InfomationNotice persistentInstance) {
		// TODO Auto-generated method stub
//		this.delete(persistentInstance);
		persistentInstance.setInFlag(CommonService.DELETE_FLAG);
		this.update(persistentInstance);
	}

	public List<InfomationNotice> find(MyQuery myQuery) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(myQuery);
	}

	public List<InfomationNotice> find(String query) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(query);
	}

	public InfomationNotice get(Serializable id) {
		// TODO Auto-generated method stub
		return (InfomationNotice) baseDAO.getEntity(InfomationNotice.class,id);
	}

	
	public void save(InfomationNotice transientInstance) {
		// TODO Auto-generated method stub
		baseDAO.saveEntity(transientInstance);
	}

	public void update(InfomationNotice transientInstance) {
		// TODO Auto-generated method stub
		baseDAO.updateEntity(transientInstance);
	}
	
	public int getFindCount(MyQuery myQuery) {
		// TODO Auto-generated method stub
		int rtn = 0;
		List list = baseDAO.findEntity(myQuery);
		if(list != null && !list.isEmpty())
		{
			if(list.get(0) instanceof Long)
			{
				rtn = ((Long)list.get(0)).intValue();
			}
			else
			{
				rtn = (Integer)list.get(0);
			}			
		}
		
		return rtn;
	}
	
}
