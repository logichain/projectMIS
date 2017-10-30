package org.king.security.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.king.framework.dao.BaseDAO;
import org.king.framework.dao.DAO;
import org.king.framework.dao.MyQuery;
import org.king.framework.util.MyUtils;
import org.king.security.domain.Person;

public class PersonDAO implements DAO {
	private static final Log log = LogFactory.getLog(PersonDAO.class);

	private BaseDAO baseDAO;

	protected void initDao() {
		//do nothing
	}

	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public List find(MyQuery myQuery) {
		log.debug("finding Person instance by myQuery");
		return baseDAO.findEntity(myQuery);
	}

	public List find(String query) {
		log.debug("finding Person instance by query");
		return baseDAO.findEntity(query);
	}

	public Person get(Serializable id) {
		log.debug("getting Person instance by id");
		return (Person) baseDAO.getEntity(Person.class, id);
	}

	public List getAll() {
		String allHql = "from Person";
		return baseDAO.findEntity(allHql);
	}

	public void save(Person transientInstance) {
		log.debug("saving Person instance");
		baseDAO.saveEntity(transientInstance);
	}

	public void update(Person transientInstance) {
		log.debug("updating Person instance");
		baseDAO.updateEntity(transientInstance);
	}

	public void delete(Person persistentInstance) {
		log.debug("deleting Person instance");
		baseDAO.removeEntity(persistentInstance);
	}

	public Person findPersonByName(String name) {
		if (MyUtils.isBlank(name)) {
			return null;
		}

		List persons = find("from Person a where a.name='" + name
				+ "' order by a.name");

		if (MyUtils.isBlank(persons)) {
			return null;
		}

		return (Person) persons.get(0);
	}
}
