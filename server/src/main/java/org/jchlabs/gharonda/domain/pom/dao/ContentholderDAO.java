package org.jchlabs.gharonda.domain.pom.dao;

import org.hibernate.Session;
import org.jchlabs.gharonda.domain.pom.base.BaseContentholderDAO;


public class ContentholderDAO extends BaseContentholderDAO implements
		org.jchlabs.gharonda.domain.pom.dao.iface.ContentholderDAO {

	public ContentholderDAO() {
	}

	public ContentholderDAO(Session session) {
		super(session);
	}

}