package org.jchlabs.gharonda.domain.pom.dao;

import org.hibernate.Session;
import org.jchlabs.gharonda.domain.pom.base.BaseUsersDAO;


public class UsersDAO extends BaseUsersDAO implements org.jchlabs.gharonda.domain.pom.dao.iface.UsersDAO {

	public UsersDAO() {
	}

	public UsersDAO(Session session) {
		super(session);
	}

}