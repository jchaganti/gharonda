package org.jchlabs.gharonda.guice;

import org.jchlabs.gharonda.domain.pom.dao.FavoritesDAO;

import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class FavoritesDAOProvider implements Provider<org.jchlabs.gharonda.domain.pom.dao.iface.FavoritesDAO> {

	public org.jchlabs.gharonda.domain.pom.dao.iface.FavoritesDAO get() {
		return FavoritesDAO.getInstance();
	}

}
