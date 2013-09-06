package org.jchlabs.gharonda.domain.pom.dao;

import org.hibernate.Session;
import org.jchlabs.gharonda.domain.pom.base.BaseFavoritesDAO;


public class FavoritesDAO extends BaseFavoritesDAO implements org.jchlabs.gharonda.domain.pom.dao.iface.FavoritesDAO {

	public FavoritesDAO() {
	}

	public FavoritesDAO(Session session) {
		super(session);
	}

}