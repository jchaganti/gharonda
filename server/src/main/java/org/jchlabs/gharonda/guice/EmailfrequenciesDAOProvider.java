package org.jchlabs.gharonda.guice;

import org.jchlabs.gharonda.domain.pom.dao.EmailfrequenciesDAO;

import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class EmailfrequenciesDAOProvider implements Provider<org.jchlabs.gharonda.domain.pom.dao.iface.EmailfrequenciesDAO> {

	public org.jchlabs.gharonda.domain.pom.dao.iface.EmailfrequenciesDAO get() {
		return EmailfrequenciesDAO.getInstance();
	}

}
