package org.jchlabs.gharonda.guice;

import org.jchlabs.gharonda.domain.pom.dao.ServiceproviderdetailsDAO;

import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ServiceproviderdetailsDAOProvider implements
		Provider<org.jchlabs.gharonda.domain.pom.dao.iface.ServiceproviderdetailsDAO> {

	public org.jchlabs.gharonda.domain.pom.dao.iface.ServiceproviderdetailsDAO get() {
		return ServiceproviderdetailsDAO.getInstance();
	}

}
