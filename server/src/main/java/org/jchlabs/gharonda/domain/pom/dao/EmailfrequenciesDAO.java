package org.jchlabs.gharonda.domain.pom.dao;

import org.hibernate.Session;
import org.jchlabs.gharonda.domain.pom.base.BaseEmailfrequenciesDAO;


public class EmailfrequenciesDAO extends BaseEmailfrequenciesDAO implements
		org.jchlabs.gharonda.domain.pom.dao.iface.EmailfrequenciesDAO {

	public EmailfrequenciesDAO() {
	}

	public EmailfrequenciesDAO(Session session) {
		super(session);
	}

}