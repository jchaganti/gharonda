package org.jchlabs.gharonda.guice;

import org.jchlabs.gharonda.domain.pom.dao.NeightbourhoodDAO;

import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class NeighbourhoodDAOProvider implements Provider<org.jchlabs.gharonda.domain.pom.dao.iface.NeightbourhoodDAO> {

	public org.jchlabs.gharonda.domain.pom.dao.iface.NeightbourhoodDAO get() {
		return NeightbourhoodDAO.getInstance();
	}

}
