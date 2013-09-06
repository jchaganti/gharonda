package org.jchlabs.gharonda.guice;

import org.jchlabs.gharonda.domain.pom.dao.ContentitemsDAO;

import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class ContentitemsDAOProvider implements Provider<org.jchlabs.gharonda.domain.pom.dao.iface.ContentitemsDAO> {

	public org.jchlabs.gharonda.domain.pom.dao.iface.ContentitemsDAO get() {
		return ContentitemsDAO.getInstance();
	}

}
