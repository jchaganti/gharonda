package org.jchlabs.gharonda.guice;

import org.jchlabs.gharonda.domain.pom.dao.PropertiesDAO;

import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class PropertiesDAOProvider implements Provider<org.jchlabs.gharonda.domain.pom.dao.iface.PropertiesDAO> {

	public org.jchlabs.gharonda.domain.pom.dao.iface.PropertiesDAO get() {
		return PropertiesDAO.getInstance();
	}

}
