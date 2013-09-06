package org.jchlabs.gharonda.domain.pom.dao;

import org.hibernate.Session;
import org.jchlabs.gharonda.domain.pom.base.BaseNotifierprofilesDAO;


public class NotifierprofilesDAO extends BaseNotifierprofilesDAO implements
		org.jchlabs.gharonda.domain.pom.dao.iface.NotifierprofilesDAO {

	public NotifierprofilesDAO() {
	}

	public NotifierprofilesDAO(Session session) {
		super(session);
	}

}