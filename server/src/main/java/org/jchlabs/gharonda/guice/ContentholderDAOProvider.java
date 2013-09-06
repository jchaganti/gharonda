package org.jchlabs.gharonda.guice;

import org.jchlabs.gharonda.domain.pom.dao.ContentholderDAO;

import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ContentholderDAOProvider implements Provider<org.jchlabs.gharonda.domain.pom.dao.iface.ContentholderDAO> {

	public org.jchlabs.gharonda.domain.pom.dao.iface.ContentholderDAO get() {
		return ContentholderDAO.getInstance();
	}

}
