package org.jchlabs.gharonda.domain.pom.dao;

import org.hibernate.Session;
import org.jchlabs.gharonda.domain.pom.base.BaseContentitemsDAO;


public class ContentitemsDAO extends BaseContentitemsDAO implements
		org.jchlabs.gharonda.domain.pom.dao.iface.ContentitemsDAO {

	public ContentitemsDAO() {
	}

	public ContentitemsDAO(Session session) {
		super(session);
	}

}