package org.jchlabs.gharonda.domain.pom.dao;

import org.hibernate.Session;
import org.jchlabs.gharonda.domain.pom.base.BasePropertiesDAO;


public class PropertiesDAO extends BasePropertiesDAO implements org.jchlabs.gharonda.domain.pom.dao.iface.PropertiesDAO {

	public PropertiesDAO() {
	}

	public PropertiesDAO(Session session) {
		super(session);
	}

}