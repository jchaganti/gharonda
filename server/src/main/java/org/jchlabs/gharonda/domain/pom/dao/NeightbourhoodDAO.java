package org.jchlabs.gharonda.domain.pom.dao;

import org.hibernate.Session;
import org.jchlabs.gharonda.domain.pom.base.BaseNeightbourhoodDAO;


public class NeightbourhoodDAO extends BaseNeightbourhoodDAO implements
		org.jchlabs.gharonda.domain.pom.dao.iface.NeightbourhoodDAO {

	public NeightbourhoodDAO() {
	}

	public NeightbourhoodDAO(Session session) {
		super(session);
	}

}