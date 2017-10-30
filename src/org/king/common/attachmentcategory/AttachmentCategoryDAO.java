package org.king.common.attachmentcategory;

import java.io.Serializable;
import java.util.List;

import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;

public class AttachmentCategoryDAO implements DAO {
	private BaseDAO baseDAO;
	
	public BaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	
	public void delete(AttachmentCategory persistentInstance) {
		// TODO Auto-generated method stub
//		this.delete(persistentInstance);
	}

	public List<AttachmentCategory> find(MyQuery myQuery) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(myQuery);
	}

	public List<AttachmentCategory> find(String query) {
		// TODO Auto-generated method stub
		return baseDAO.findEntity(query);
	}

	public AttachmentCategory get(Serializable id) {
		// TODO Auto-generated method stub
		return (AttachmentCategory) baseDAO.getEntity(AttachmentCategory.class,id);
	}

	
	public void save(AttachmentCategory transientInstance) {
		// TODO Auto-generated method stub
//		baseDAO.saveEntity(transientInstance);
	}

	public void update(AttachmentCategory transientInstance) {
		// TODO Auto-generated method stub
//		baseDAO.updateEntity(transientInstance);
	}
	
}
